package com.hkesports.matchticker.service.impl;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.google.gson.reflect.TypeToken;
import com.hkesports.matchticker.common.utils.ImageUtils;
import com.hkesports.matchticker.common.utils.JsoupUtils;
import com.hkesports.matchticker.common.utils.MessageUtils;
import com.hkesports.matchticker.config.PropertiesConfig;
import com.hkesports.matchticker.enums.GameTypeEnum;
import com.hkesports.matchticker.model.Data;
import com.hkesports.matchticker.model.batchJob.League;
import com.hkesports.matchticker.model.batchJob.Player;
import com.hkesports.matchticker.model.batchJob.Team;
import com.hkesports.matchticker.model.json.dota2.ugcFileDetails.UgcFileDetails;
import com.hkesports.matchticker.model.json.dota2.ugcFileDetails.UgcFileDetailsData;
import com.hkesports.matchticker.model.json.factory.JsonFactory;
import com.hkesports.matchticker.repository.LeagueDao;
import com.hkesports.matchticker.repository.PlayerDao;
import com.hkesports.matchticker.repository.TeamDao;
import com.hkesports.matchticker.service.CodeDataService;
import com.hkesports.matchticker.service.ImageService;

/**
 * 圖片處理
 */
@Service("imageService")
public class ImageServiceImpl implements ImageService {

	@Resource
	private PlayerDao playerDao;
	
	@Resource
	private TeamDao teamDao;
	
	@Resource
	private LeagueDao leagueDao;
	
	@Resource(name="codeDataService")
	private CodeDataService codeDataService;
	
	@Resource(name = "propertiesConfig")
	private PropertiesConfig propertiesConfig;
	
	@Resource(name = "jsoupUtils")
	private JsoupUtils jsoupUtils;
	
	private String apiImageSizeType = "Original";
	
	/**
	 * 玩家圖檔從官方API下載, 並作圖片縮放處理
	 */
	@Override
	public void batchPlayerPhoto() throws Exception {
		String imageType = "Player";
		List<Player> list = playerDao.findAllByPhoto();

		if(list == null || list.size() < 1)
			return;
		
		String filePath = codeDataService.getCodeDataValue("ImageSetting", "filePath");
		if(filePath == null)
			throw new Exception("DB code data is not setting data!");
		Map<String, String> imageSizeMap = getImageSizeMap(imageType);
		
		StringBuilder errorMessage = new StringBuilder();
		for(Player player : list){
			Map<String, String> imageTmpPathMap = new HashMap<String, String>();
			imageTmpPathMap.put(apiImageSizeType, ImageUtils.getTmpFilePath(filePath, player.getPhotoUrl(), imageType, player.getId(), apiImageSizeType));
			
			handlePhoto(imageType, player.getId(), player.getPhotoUrl(), filePath, imageSizeMap, imageTmpPathMap, errorMessage);
			deleteTmp(imageTmpPathMap);
		}
		
		if(errorMessage.length() > 0){
			throw new Exception(errorMessage.toString());
		}
	}
	
	/**
	 * 團隊LOGO從官方API下載, 並作圖片縮放處理
	 */
	@Override
	public void batchTeamLogo() throws Exception {
		String imageType = "Team";
		List<Team> list = teamDao.findAllByLogo();

		if(list == null || list.size() < 1)
			return;
		
		String filePath = codeDataService.getCodeDataValue("ImageSetting", "filePath");
		if(filePath == null)
			throw new Exception("DB code data is not setting data!");
		Map<String, String> imageSizeMap = getImageSizeMap(imageType);
		
		StringBuilder errorMessage = new StringBuilder();
		for(Team team : list){
			String logoUrl = team.getLogoUrl();
			
			//DOTA2的Logo url只有key, 要另外去取回URL
			try{
				if(team.getGameType() == GameTypeEnum.DOTA2){
					UgcFileDetailsData data = getDota2UGC(logoUrl);
					if(data.getUrl() == null)
						continue;
					else
						logoUrl = data.getUrl();
				}
			} catch(Exception e) {
				e.printStackTrace();
				MessageUtils.setImageBatchErrorMessage(errorMessage, imageType, team.getId(), e);
				continue;
			}
			
			Map<String, String> imageTmpPathMap = new HashMap<String, String>();
			imageTmpPathMap.put(apiImageSizeType, ImageUtils.getTmpFilePath(filePath, logoUrl, imageType, team.getId(), apiImageSizeType));
			
			handlePhoto(imageType, team.getId(), logoUrl, filePath, imageSizeMap, imageTmpPathMap, errorMessage);
			deleteTmp(imageTmpPathMap);
		}
		
		if(errorMessage.length() > 0){
			throw new Exception(errorMessage.toString());
		}
	}
	
	/**
	 * 聯賽圖檔從API下載, 並作圖片縮放處理
	 */
	@Override
	public void batchLeaguePhoto() throws Exception {
		String imageType = "League";
		List<League> list = leagueDao.findAllByLeagueImage();

		if(list == null || list.size() < 1)
			return;
		
		String filePath = codeDataService.getCodeDataValue("ImageSetting", "filePath");
		if(filePath == null)
			throw new Exception("DB code data is not setting data!");
		Map<String, String> imageSizeMap = getImageSizeMap(imageType);
		
		StringBuilder errorMessage = new StringBuilder();
		for(League league : list){
			Map<String, String> imageTmpPathMap = new HashMap<String, String>();
			imageTmpPathMap.put(apiImageSizeType, ImageUtils.getTmpFilePath(filePath, league.getLeagueImage(), imageType, league.getId(), apiImageSizeType));
			
			handlePhoto(imageType, league.getId(), league.getLeagueImage(), filePath, imageSizeMap, imageTmpPathMap, errorMessage);
			deleteTmp(imageTmpPathMap);
		}
		
		if(errorMessage.length() > 0){
			throw new Exception(errorMessage.toString());
		}
	}
	
	/**
	 * DOTA2的圖檔API
	 * @param ugcid		DOTA2官方給的ID
	 * @return
	 * @throws Exception
	 */
	private UgcFileDetailsData getDota2UGC(String ugcid) throws Exception {
		String url = StringUtils.replace(propertiesConfig.getProperty("dota2.ugcFileDetails.url"), "{ugcid}", ugcid);
		UgcFileDetails data = JsonFactory.fromJson(jsoupUtils.getJsonString(url), new TypeToken<UgcFileDetails>() {}.getType());
		return data == null ? null : data.getData();
	}
	
	/**
	 * 取得圖片縮放尺寸設定檔
	 * @param imageType	圖片類別
	 * @return
	 */
	private Map<String, String> getImageSizeMap(String imageType) {
		List<Data> imageSizeDatas = codeDataService.getCodeData("ImageSetting", imageType);
		
		Map<String, String> imageSizeMap = new HashMap<String, String>();
		imageSizeMap.put(apiImageSizeType, null);
		for(Data data : imageSizeDatas){
			imageSizeMap.put(data.getDataName(), data.getDataValue());
		}
		
		return imageSizeMap;
	}
	
	/**
	 * 圖片處理
	 * @param imageType			圖檔類別, 來源的domain name
	 * @param id				DB PK
	 * @param photoUrl			API圖檔路徑
	 * @param filePath			圖檔根目錄
	 * @param imageSizeMap		圖檔尺寸設定檔
	 * @param imageTmpPathMap	圖檔暫存路徑設定檔
	 * @param errorMessage		錯誤訊息, 用來收集錯誤訊息的容器
	 */
	private void handlePhoto(String imageType, Long id, String photoUrl, String filePath, Map<String, String> imageSizeMap, Map<String, String> imageTmpPathMap, StringBuilder errorMessage){
		try{
			//下載API圖檔
			File apiFile = ImageUtils.getImageByUrl(imageTmpPathMap.get(apiImageSizeType), photoUrl);
			if(apiFile == null)
				return;
			
			//比對圖檔的一致性, 一致則不處理
			String playerFilePath = ImageUtils.getFilePath(filePath, photoUrl, imageType, id, apiImageSizeType);
			File oldOriginalFile = new File(playerFilePath);
			if(oldOriginalFile.exists() && ImageUtils.contentEquals(apiFile, oldOriginalFile))
				return;
			
			Map<String, String> imagePathMap = new HashMap<String, String>();
			imagePathMap.put(apiImageSizeType, playerFilePath);
		
			//縮圖
			for(String sizeKey : imageSizeMap.keySet()){
				if(imageSizeMap.get(sizeKey) == null)
					continue;
				
				String[] size = imageSizeMap.get(sizeKey).split("\\*");
				imageTmpPathMap.put(sizeKey, ImageUtils.getTmpFilePath(filePath, photoUrl, imageType, id, sizeKey));
				imagePathMap.put(sizeKey, ImageUtils.getFilePath(filePath, photoUrl, imageType, id, sizeKey));
				ImageUtils.convertImageSize(imageTmpPathMap.get(sizeKey), imagePathMap.get(sizeKey), size[0], size[1]);
			}
			
			//將圖檔從暫存區移到正式區
			for(String sizeKey : imageSizeMap.keySet()){
				File tmpFile = new File(imageTmpPathMap.get(sizeKey));
				File oldFile = new File(imagePathMap.get(sizeKey));
				if(tmpFile.exists())
					ImageUtils.moveImageByTmp(new File(imagePathMap.get(sizeKey)), tmpFile);
				else if(oldFile.exists())
					new File(imagePathMap.get(sizeKey)).delete();
			}
		}catch(Exception e){
			e.printStackTrace();
			MessageUtils.setImageBatchErrorMessage(errorMessage, imageType, id, e);
		}
	}
	
	/**
	 * 刪除所有的暫存圖檔
	 * @param imageTmpPathMap 暫存檔路徑
	 */
	private void deleteTmp(Map<String, String> imageTmpPathMap){
		for(String sizeKey : imageTmpPathMap.keySet()){
			File file = new File(imageTmpPathMap.get(sizeKey));
			if(file.exists())
				file.delete();
		}
	}
}
