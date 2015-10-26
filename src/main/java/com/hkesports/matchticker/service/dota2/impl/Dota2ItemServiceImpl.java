package com.hkesports.matchticker.service.dota2.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.reflect.TypeToken;
import com.hkesports.matchticker.common.utils.JsoupUtils;
import com.hkesports.matchticker.common.utils.MessageUtils;
import com.hkesports.matchticker.config.PropertiesConfig;
import com.hkesports.matchticker.enums.GameTypeEnum;
import com.hkesports.matchticker.model.Game;
import com.hkesports.matchticker.model.batchJob.ItemData;
import com.hkesports.matchticker.model.json.dota2.gameItem.GameItemJson;
import com.hkesports.matchticker.model.json.dota2.gameItem.GameItemsJson;
import com.hkesports.matchticker.model.json.factory.JsonFactory;
import com.hkesports.matchticker.repository.ItemDataDao;
import com.hkesports.matchticker.service.GameService;
import com.hkesports.matchticker.service.dota2.Dota2ItemService;

@Service("dota2ItemService")
public class Dota2ItemServiceImpl implements Dota2ItemService {
	private static final Log logger = LogFactory.getLog(Dota2ItemServiceImpl.class);
	
	@Resource(name = "jsoupUtils")
	private JsoupUtils jsoupUtils;
	
	@Resource(name = "propertiesConfig")
	private PropertiesConfig propertiesConfig;
	
	@Resource(name = "itemDataDao")
	private ItemDataDao itemDataDao;
	
	@Resource
	private GameService gameService;
	
	@Override
	public void updateItemForApi() throws Exception {
		List<ItemData> itemDataList = new ArrayList<ItemData>();
		mergeItemData(itemDataList, getApiItemList());		
		String message = saveItemList(itemDataList);
		
		if(message != null && message.length() > 0){
			throw new Exception(message);
		}
	}

	@Override
	public List<GameItemJson> getApiItemList() throws Exception {
		String url = propertiesConfig.getProperty("dota2.item.url");
		JsonFactory<GameItemsJson> jsonObj = new JsonFactory<>(jsoupUtils.getJsonString(url), new TypeToken<GameItemsJson>(){}.getType());
		GameItemsJson items = jsonObj.getData();
		
		if(items.getResult().getStatus() != 200){
			String message = "get Dota2 item api, response http status: " + items.getResult().getStatus();
			logger.info(message);
			throw new Exception(message);
		}
		
		return items.getResult().getItems();
	}
	
	public List<ItemData> findAllItemData(){
		Game game = gameService.findByGameCode(GameTypeEnum.DOTA2);
		return itemDataDao.findByGameId(game.getId());
	}
	
	public Map<Long, ItemData> findItemDataToMap(){
		List<ItemData> itemList = findAllItemData();
		Map<Long, ItemData> map = new HashMap<Long, ItemData>();
		for(ItemData item : itemList)
			map.put(item.getApiId(), item);
		return map;
	}
	
	private void mergeItemData(List<ItemData> itemDataList, List<GameItemJson> itemJsonList) {
		Map<Long, ItemData> oldItemMap = findItemDataToMap();
		Game game = gameService.findByGameCode(GameTypeEnum.DOTA2);
		//merge api data and db data
		for(GameItemJson itemJson : itemJsonList){
			ItemData oldItem = oldItemMap.get(itemJson.getId());
			if(oldItem == null)
				itemDataList.add(bindJsonDataToModel(game, itemJson));
			else if(!oldItem.equals(itemJson))
				itemDataList.add(bindJsonDataToModel(game, itemJson, oldItem));
		}
		
		//db data not exist api data, set expire date
		for(Long apiId : oldItemMap.keySet()){
			ItemData item = oldItemMap.get(apiId);
			if(!itemJsonList.contains(item) && item.getExpireDate() == null){
				item.setExpireDate(new Date());
				itemDataList.add(item);
			}
		}
	}
	
	private ItemData bindJsonDataToModel(Game game, GameItemJson json) {
		return bindJsonDataToModel(game, json, new ItemData());
	}
	
	private ItemData bindJsonDataToModel(Game game, GameItemJson json, ItemData item) {
		item.setGame(game);
		item.setApiId(json.getId());
		item.setName(json.getLocalizedName());
		item.setEnName(json.getName());
		item.setCost(json.getCost());
		
		return item;
	}
	
	@Transactional
	private String saveItemList(List<ItemData> itemDataList){
		StringBuilder message = new StringBuilder("");
		
		for(ItemData item : itemDataList){
			try{
				itemDataDao.save(item);
			}catch(DataAccessException e){
				logger.error(e);
				MessageUtils.setApiSaveErrorMessage(message, GameTypeEnum.DOTA2, "Item", item.getApiId(), e);
			}
		}
		
		return message.toString();
	}
}
