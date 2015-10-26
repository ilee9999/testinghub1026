package com.hkesports.matchticker.service.lol.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.reflect.TypeToken;
import com.hkesports.matchticker.common.utils.AppUtils;
import com.hkesports.matchticker.common.utils.JsoupUtils;
import com.hkesports.matchticker.common.utils.MessageUtils;
import com.hkesports.matchticker.config.PropertiesConfig;
import com.hkesports.matchticker.enums.GameTypeEnum;
import com.hkesports.matchticker.model.Game;
import com.hkesports.matchticker.model.batchJob.ItemData;
import com.hkesports.matchticker.model.json.factory.JsonFactory;
import com.hkesports.matchticker.model.json.lol.item.ItemImageJson;
import com.hkesports.matchticker.model.json.lol.item.ItemJson;
import com.hkesports.matchticker.model.json.lol.item.ItemListJson;
import com.hkesports.matchticker.repository.ItemDataDao;
import com.hkesports.matchticker.service.GameService;
import com.hkesports.matchticker.service.lol.LoLItemDataService;

/**
 * @author manboyu
 *
 */
@Service("loLItemDataService")
public class LoLItemDataServiceImpl implements LoLItemDataService {

	private static final Log logger = LogFactory.getLog(LoLItemDataServiceImpl.class);
	
	@Resource(name = "propertiesConfig")
	private PropertiesConfig propertiesConfig;
	@Resource(name = "jsoupUtils")
	private JsoupUtils jsoupUtils;
	
	@Resource(name = "itemDataDao")
	private ItemDataDao itemDataDao;

	@Resource
	private GameService gameService;
	
	@Override
	public ItemListJson getItemListJson() throws Exception {
		String itemUrl = this.propertiesConfig.getProperty("lol.item.url");
		JsonFactory<ItemListJson> jsonObj = new JsonFactory<>(this.jsoupUtils.getJsonString(itemUrl), new TypeToken<ItemListJson>(){}.getType());
		return !jsonObj.isEmpty() ? jsonObj.getData() : null;
	}

	@Override
	public List<ItemData> findItemDatas() {
		Game game = gameService.findByGameCode(GameTypeEnum.LOL);
		return this.itemDataDao.findByGameId(game.getId());
	}

	@Override
	public void saveItemData() throws Exception {
		StringBuilder message = new StringBuilder();
		List<ItemData> itemDatas = findItemDatas();
		Map<Long, ItemData> map = AppUtils.putModelByApiId(itemDatas);
		ItemListJson itemListJson = getItemListJson();
		if(itemListJson == null){
			throw new Exception("there's no item data from URL : " + this.propertiesConfig.getProperty("lol.item.url"));
		}
		
		Map<String, ItemJson> data = itemListJson.getData();
		Game game = gameService.findByGameCode(GameTypeEnum.LOL);
		for (Map.Entry<String, ItemJson> entry : data.entrySet()) {
			ItemJson itemJson = entry.getValue();
			ItemData oItemData = map.get(itemJson.getId().longValue());
			try {
				if(oItemData != null && StringUtils.equals(oItemData.getVersion(), itemListJson.getVersion()) && oItemData.equals(itemJson)){
					continue;
				}
				this.doSaveItemData(game, oItemData, itemJson, itemListJson.getVersion());
			} catch (Exception e) {
				logger.error("save ItemData - apiId [ " + itemJson.getId() + " ] occur exception : ", e);
				MessageUtils.setApiSaveErrorMessage(message, GameTypeEnum.LOL, "ItemData", itemJson.getId().longValue(), e);
			}
		}
		if(StringUtils.isNotBlank(message)){
			throw new Exception(message.toString());
		}
	}

	@Transactional
	private ItemData doSaveItemData(Game game, ItemData itemData, ItemJson itemJson, String version) {
		if(itemData == null) {
			itemData = new ItemData();
			itemData.setApiId(itemJson.getId().longValue());
			itemData.setGame(game);
		}
		itemData.setVersion(version);
		itemData.setDescription(itemJson.getDescription());
		itemData.setName(itemJson.getName());
		itemData.setEnName(itemJson.getName());
		itemData.setPlaintext(itemJson.getPlaintext());
		if(itemJson.getImage() != null){
			ItemImageJson imageJson = itemJson.getImage();
			itemData.setImageFullName(imageJson.getFull());
			itemData.setImageW(imageJson.getW());
			itemData.setImageH(imageJson.getH());
		}
		itemData = this.itemDataDao.save(itemData);
		if(logger.isDebugEnabled())logger.debug("saveItemData success : " + itemData);
		return itemData;
	}
}
