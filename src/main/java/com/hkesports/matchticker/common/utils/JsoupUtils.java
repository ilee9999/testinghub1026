package com.hkesports.matchticker.common.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import com.hkesports.matchticker.enums.Dota2FileType;

/**
 * @author manboyu
 *
 */
public class JsoupUtils {
	
	private static final Logger logger = LoggerFactory.getLogger(JsoupUtils.class); 
	
	
	private Integer maxRetryCount;
	private Integer retrySleep;
	private Integer connetionTimeOut;
	private String filePath;
	
	public JsoupUtils(Integer maxRetryCount, Integer retrySleep, Integer connetionTimeOut, String filePath) {
		this.maxRetryCount = maxRetryCount;
		this.retrySleep = retrySleep;
		this.connetionTimeOut = connetionTimeOut;
		this.filePath = filePath;
	}
	
	public Connection connection(String url) {
		return Jsoup.connect(url).ignoreContentType(true).userAgent("chrome");
	}
	
	public String getJsonString(String url) throws Exception {
		return executeGetJsonString(url, 1);
	}
	
	public String getJsonString(String url, Dota2FileType type, String apiId) throws Exception {
		String result = getDota2JsonFileContent(type, apiId);
		return result==null ? executeGetJsonString(url, 1) : result;
	}
	
	public List<String> getJsonString(String url, String searchString, Dota2FileType type, List<?> apiIds) throws Exception {
		if(CollectionUtils.isEmpty(apiIds)) {
			return null;
		}
		
		List<String> resultList = new ArrayList<>(apiIds.size());
		List<String> nonFileList = new ArrayList<>(apiIds.size());
		for(Object obj:apiIds) {
			if(obj==null)
				continue;
			String id = obj.toString();
			File file = new File(getDota2JsonFilePath(type, id));
			String result = null;
			if(file.exists()) {
				result = readFileToStr(file);
			} else {
				if(type != Dota2FileType.Player) {
					if(StringUtils.isNotBlank(searchString)) {
						url = StringUtils.replace(url, searchString, id);
					}
					result = executeGetJsonString(url, 1);
				} else {
					nonFileList.add(id);
				}
			}
			if(StringUtils.isNotBlank(result)) {
				resultList.add(result);
			}
		}
		if(!CollectionUtils.isEmpty(nonFileList)) {
			String ids = MessageUtils.joinListToString(nonFileList);
			resultList.add(executeGetJsonString(StringUtils.replace(url, searchString, ids), 1));
		}
		return resultList;
	}
	
	private String executeGetJsonString(String url, int count) throws Exception {
		if(StringUtils.isBlank(url) || !url.startsWith("http")) {
			return StringUtils.EMPTY;
		}
		Connection connection = null;
		try {
			connection = connection(url);
			connection.timeout(connetionTimeOut);
			return connection.execute().body();
		} catch(org.jsoup.HttpStatusException e) {
			logger.error("executeGetJson HttpStatusException!!\nurl:{} error!\nstatus code:{}", url, e.getStatusCode());
			if(e.getStatusCode()!=404) {
				retryConnection(url, count, e);
			}
		} catch (Exception e) {
			logger.error("executeGetJson \nurl:{} error! \nretry count: {}", url, count, e);
			retryConnection(url, count, e);
		}
		return StringUtils.EMPTY;
	}
	
	public String retryConnection(String url, int count, Exception e) throws Exception {
		if(this.maxRetryCount > count) {
			try {
				Thread.sleep(retrySleep);
				logger.debug("url:{} \nretry count: {}", url, count);
				return executeGetJsonString(url, count + 1);
			} catch (InterruptedException e1) {
				logger.error("retry jsoup connection exception : ", e1);
			}
		} else {
			throw e;
		}
		return null;
	}
	
	public Document getDocument(String url) throws IOException {
		return getDocument(url, 1);
	}
	
	private Document getDocument(String url, int count) throws IOException {
		if(!StringUtils.isBlank(url) && url.startsWith("http")) {
			try {
				return Jsoup.connect(url).get();
			} catch(IOException e) {
				if(this.maxRetryCount > count) {
					try {
						Thread.sleep(retrySleep);
						return getDocument(url, count + 1);
					} catch (InterruptedException e1) {
						logger.error("retry jsoup connection exception : ", e);
					}
				} else {
					throw e;
				}
			}
		}
		return null;
	}
	
	public String getDota2JsonFilePath(Dota2FileType type) {
		return getDota2JsonFilePath(type, "");
	}
	
	public String getDota2JsonFilePath(Dota2FileType type, Long apiId) {
		return getDota2JsonFilePath(type, apiId.toString());
	}
	
	public String getDota2JsonFilePath(Dota2FileType type, String apiId) {
		StringBuilder path = new StringBuilder();
		path.append(filePath);
		path.append(File.separator);
		path.append(DateUtils.parserDateToString(new Date(), "yyyy_MM"));
		path.append(File.separator);
		switch(type) {
			case TeamInfo:
			case MatchHistory:
			case MatchDetail:
			case Player:
				path.append(type);
				path.append(File.separator);
				path.append(apiId);
				break;
			
			case Hero:
			case Ability:
			case Item:
			case LeagueList:
				break;
			default:
				return null;		
		} 
		
		return path.toString();
	}
	
	public String getDota2JsonFileContent(Dota2FileType type, String apiId) throws IOException {
		if(StringUtils.isBlank(apiId)) {
			return null;
		}
		return readFileToStr(new File(getDota2JsonFilePath(type, apiId)));
	}
	
	public String readFileToStr(File file) throws IOException {
		if(file==null || !file.exists()) {
			return null;
		}
		if(!file.isFile()) {
			return null;
		}
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			StringBuilder result = new StringBuilder();
			String content = null;
			while((content = reader.readLine())!=null) {
				result.append(content);
			}
			return result.toString();
		} finally {
			if(reader!=null) {
				reader.close();	
			}
		}
	}
	
	public void writeStrToFile(File file, String str) throws IOException {
		if(file==null || StringUtils.isBlank(str)) {
			return;
		}
		if(!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		}
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(file));
			writer.write(str);
		} finally {
			if(writer!=null) {
				writer.close();	
			}
		}
	}
	
}
