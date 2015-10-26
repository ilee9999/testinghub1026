package com.hkesports.matchticker.common.utils;

import java.io.File;
import java.net.URL;

import org.apache.commons.io.FileUtils;

/**
 * 圖檔工具
 */
public class ImageUtils {
	
	/**
	 * 取得暫存圖檔路徑, 缺少暫存資料夾時會在這時建立起來
	 * @param path			圖庫根目錄
	 * @param apiUrl		API的網址, 為了取得副檔名
	 * @param id			DB的主鍵
	 * @param imageSizeType	圖檔的尺寸類別
	 * @return
	 */
	public static String getTmpFilePath(String path, String apiUrl, String imageType, Long id, String imageSizeType){
		StringBuilder filePath = new StringBuilder(path);
		if(path.lastIndexOf(File.separator) != path.length() - 1)
			filePath.append(File.separator);
		filePath.append("tmp").append(File.separator).append(imageType).append(File.separator);
		
		File file = new File(filePath.toString());
		if(!file.exists())
			file.mkdirs();
		
		setFileName(filePath, apiUrl, id, imageSizeType);
		return filePath.toString();
	}
	
	/**
	 * 取得系統上的圖檔路徑, 缺少的父層資料夾會在這時建立起來
	 * @param path			圖庫根目錄
	 * @param apiUrl		API的網址, 為了取得副檔名
	 * @param imageType		圖檔類別
	 * @param id			DB的主鍵
	 * @param imageSizeType	圖檔的尺寸類別
	 * @return
	 */
	public static String getFilePath(String path, String apiUrl, String imageType, Long id, String imageSizeType){
		StringBuilder filePath = new StringBuilder(path);
		if(path.lastIndexOf(File.separator) != path.length() - 1)
			filePath.append(File.separator);
		filePath.append(imageType).append(File.separator);
		filePath.append(getSubFileName(id)).append(File.separator);
		
		File file = new File(filePath.toString());
		if(!file.exists())
			file.mkdirs();
		
		setFileName(filePath, apiUrl, id, imageSizeType);
		return filePath.toString();
	}
	
	/**
	 * 取得圖檔的網址
	 * @param url			File Server http路徑
	 * @param apiUrl		API的網址, 為了取得副檔名
	 * @param imageType		圖檔類別
	 * @param id			DB主鍵
	 * @param imageSizeType	圖檔尺寸類別
	 * @return
	 */
	public static String getFileUrl(String url, String apiUrl, String imageType, Long id, String imageSizeType){
		StringBuilder fileUrl = new StringBuilder(url);
		if(url.lastIndexOf("/") != url.length() - 1)
			fileUrl.append("/");
		
		fileUrl.append(imageType).append("/");
		fileUrl.append(getSubFileName(id)).append("/");
		setFileName(fileUrl, apiUrl, id, imageSizeType);
		return fileUrl.toString();
	}
	
	/**
	 * 計算檔案的子類別目錄
	 * @param id	DB主鍵
	 * @return
	 */
	private static int getSubFileName(Long id){
		return (int) (id / 10000 + 1);
	}
	
	/**
	 * 塞入圖檔的檔名到StringBuilder物件
	 * @param sb			路徑的StringBuilder物件
	 * @param apiUrl		API的網址
	 * @param id			DB主鍵
	 * @param imageSizeType	圖檔尺寸類別
	 */
	private static void setFileName(StringBuilder sb, String apiUrl, Long id, String imageSizeType){
		sb.append(id).append("_").append(imageSizeType).append(".").append(getFileExtension(apiUrl));
	}
	
	/**
	 * 取得副檔名
	 * @param apiUrl api網址
	 * @return
	 */
	private static String getFileExtension(String apiUrl){
		if(haveFileExtension(apiUrl))
			return apiUrl.substring(apiUrl.lastIndexOf(".") + 1, apiUrl.length());
		else
			return "jpg";
	}
	
	/**
	 * 判斷網址是否有副檔名, 因DOTA2不給副檔名
	 * @param apiUrl
	 * @return
	 */
	private static boolean haveFileExtension(String apiUrl){
		if(apiUrl == null)
			return false;
		
		if(apiUrl.substring(apiUrl.length() - 1).equals("/"))
			return false;
		
		String[] urls = apiUrl.split("/");
		if(urls[urls.length - 1].indexOf(".") == -1)
			return false;
		
		return true;
	}
	
	/**
	 * 用URL取回圖檔
	 * @param fileName 本機存檔的檔名
	 * @param url 圖檔的網路路徑
	 * @return
	 */
	public static File getImageByUrl(String fileName, String url){
		try{
			File file = new File(fileName);
			FileUtils.copyURLToFile(new URL(url), file);					
			return file;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 比對二個圖檔是否一致
	 * @param file1
	 * @param file2
	 * @return	一致時回傳true
	 */
	public static boolean contentEquals(File file1, File file2){
		try{
			return FileUtils.contentEquals(file1, file2);
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 等比例縮圖, 例用Linux的convert軟體縮圖, 防止Java ImageIO使用到資訊不足的圖檔造成壓圖失敗
	 * Windows系統, 不會執行縮圖動作
	 * @param srcFile	來源圖檔
	 * @param newFile	縮圖後的圖檔
	 * @param width		新的寛
	 * @param height	新的高
	 */
	public static void convertImageSize(String srcFile, String newFile, String width, String height) throws Exception {
		if(System.getProperty("os.name").toLowerCase().indexOf("win") != -1)
			return;
		
		String cmd = "/usr/local/bin/convert " + srcFile + " -resize " + width + "x" + height + " " + newFile;
		Runtime.getRuntime().exec(cmd);
	}
	
	/**
	 * 移動圖檔
	 * @param oldFile 來源圖檔
	 * @param newFile 移動目的圖檔
	 */
	public static void moveImageByTmp(File oldFile, File newFile) throws Exception {		
		if(oldFile.exists())
			oldFile.delete();
		FileUtils.moveFile(newFile, oldFile);
	}
}
