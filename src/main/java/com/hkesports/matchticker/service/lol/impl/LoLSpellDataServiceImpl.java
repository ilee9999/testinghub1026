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
import com.hkesports.matchticker.model.batchJob.SpellData;
import com.hkesports.matchticker.model.json.factory.JsonFactory;
import com.hkesports.matchticker.model.json.lol.spell.SpellImageJson;
import com.hkesports.matchticker.model.json.lol.spell.SpellJson;
import com.hkesports.matchticker.model.json.lol.spell.SpellListJson;
import com.hkesports.matchticker.repository.SpellDataDao;
import com.hkesports.matchticker.service.GameService;
import com.hkesports.matchticker.service.lol.LoLSpellDataService;

/**
 * @author manboyu
 *
 */
@Service("loLSpellDataService")
public class LoLSpellDataServiceImpl implements LoLSpellDataService {

	private static final Log logger = LogFactory.getLog(LoLSpellDataServiceImpl.class);
	
	@Resource(name = "propertiesConfig")
	private PropertiesConfig propertiesConfig;
	@Resource(name = "jsoupUtils")
	private JsoupUtils jsoupUtils;
	
	@Resource(name = "spellDataDao")
	private SpellDataDao spellDataDao;
	
	@Resource
	private GameService gameService;
	
	@Override
	public SpellListJson getSpellListJson() throws Exception {
		String spellUrl = this.propertiesConfig.getProperty("lol.summonerSpell.url");
		JsonFactory<SpellListJson> jsonObj = new JsonFactory<>(this.jsoupUtils.getJsonString(spellUrl), new TypeToken<SpellListJson>(){}.getType());
		return !jsonObj.isEmpty() ? jsonObj.getData() : null;
	}

	@Override
	public List<SpellData> findSpellDatas() {
		Game game = gameService.findByGameCode(GameTypeEnum.LOL);
		return this.spellDataDao.findByGameId(game.getId());
	}

	@Override
	public void saveSpellData() throws Exception {
		StringBuilder message = new StringBuilder();
		List<SpellData> spellDatas = findSpellDatas();
		Map<Long, SpellData> map = AppUtils.putModelByApiId(spellDatas);
		SpellListJson spellListJson = getSpellListJson();
		if(spellListJson == null){
			throw new Exception("there's no spell data from URL : " + this.propertiesConfig.getProperty("lol.summonerSpell.url"));
		}
		
		Map<String, SpellJson> data = spellListJson.getData();
		Game game = gameService.findByGameCode(GameTypeEnum.LOL);
		for (Map.Entry<String, SpellJson> entry : data.entrySet()) {
			SpellJson spellJson = entry.getValue();
			SpellData oSpellData = map.get(spellJson.getId().longValue());
			try {
				if(oSpellData != null && StringUtils.equals(oSpellData.getVersion(), spellListJson.getVersion()) && oSpellData.equals(spellJson)){
					continue;
				}
				this.doSaveSpellData(game, oSpellData, spellJson, spellListJson.getVersion());
			} catch (Exception e) {
				logger.error("save SpellData - apiId [ " + spellJson.getId() + " ] occur exception : ", e);
				MessageUtils.setApiSaveErrorMessage(message, GameTypeEnum.LOL, "SpellData", spellJson.getId().longValue(), e);
			}
		}
		if(StringUtils.isNotBlank(message)){
			throw new Exception(message.toString());
		}
	}

	@Transactional
	private SpellData doSaveSpellData(Game game, SpellData spellData, SpellJson spellJson, String version) {
		if(spellData == null) {
			spellData = new SpellData();
			spellData.setApiId(spellJson.getId().longValue());
			spellData.setGame(game);
		}
		spellData.setVersion(version);
		spellData.setName(spellJson.getName());
		spellData.setEnName(spellJson.getName());
		spellData.setDescription(spellJson.getDescription());
		spellData.setSummonerLevel(spellJson.getSummonerLevel());
		if(spellJson.getImage() != null){
			SpellImageJson imageJson = spellJson.getImage();
			spellData.setImageFullName(imageJson.getFull());
			spellData.setImageW(imageJson.getW());
			spellData.setImageH(imageJson.getH());
		}
		spellData = this.spellDataDao.save(spellData);
		if(logger.isDebugEnabled())logger.debug("saveSpellData success : " + spellData);
		return spellData;
	}

}
