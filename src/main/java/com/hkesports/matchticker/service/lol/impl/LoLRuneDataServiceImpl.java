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
import com.hkesports.matchticker.model.batchJob.RuneData;
import com.hkesports.matchticker.model.json.factory.JsonFactory;
import com.hkesports.matchticker.model.json.lol.rune.RuneDetailJson;
import com.hkesports.matchticker.model.json.lol.rune.RuneImageJson;
import com.hkesports.matchticker.model.json.lol.rune.RuneJson;
import com.hkesports.matchticker.model.json.lol.rune.RuneListJson;
import com.hkesports.matchticker.repository.RuneDataDao;
import com.hkesports.matchticker.service.GameService;
import com.hkesports.matchticker.service.lol.LoLRuneDataService;

/**
 * @author manboyu
 *
 */
@Service("loLRuneDataService")
public class LoLRuneDataServiceImpl implements LoLRuneDataService {

	private static final Log logger = LogFactory.getLog(LoLRuneDataServiceImpl.class);
	
	@Resource(name = "propertiesConfig")
	private PropertiesConfig propertiesConfig;
	@Resource(name = "jsoupUtils")
	private JsoupUtils jsoupUtils;
	
	@Resource(name = "runeDataDao")
	private RuneDataDao runeDataDao;

	@Resource
	private GameService gameService;
	
	@Override
	public RuneListJson getRuneListJosn() throws Exception {
		String runeUrl = this.propertiesConfig.getProperty("lol.rune.url");
		JsonFactory<RuneListJson> jsonObj = new JsonFactory<>(this.jsoupUtils.getJsonString(runeUrl), new TypeToken<RuneListJson>(){}.getType());
		return !jsonObj.isEmpty() ? jsonObj.getData() : null;
	}

	@Override
	public List<RuneData> findRuneDatas() {
		Game game = gameService.findByGameCode(GameTypeEnum.LOL);
		return this.runeDataDao.findByGameId(game.getId());
	}

	@Override
	public void saveRuneData() throws Exception {
		StringBuilder message = new StringBuilder();
		List<RuneData> runeDatas = findRuneDatas();
		Map<Long, RuneData> map = AppUtils.putModelByApiId(runeDatas);
		RuneListJson runeListJosn = getRuneListJosn();
		if(runeListJosn == null){
			throw new Exception("there's no Rune data from URL : " + this.propertiesConfig.getProperty("lol.rune.url"));
		}
		
		Map<String, RuneJson> data = runeListJosn.getData();
		Game game = gameService.findByGameCode(GameTypeEnum.LOL);
		for (Map.Entry<String, RuneJson> entry : data.entrySet()) {
			RuneJson runeJson = entry.getValue();
			RuneData oRuneData = map.get(runeJson.getId().longValue());
			try {
				if(oRuneData != null && StringUtils.equals(oRuneData.getVersion(), runeListJosn.getVersion()) && oRuneData.equals(runeJson)){
					continue;
				}
				this.doSaveRuneData(game, oRuneData, runeJson, runeListJosn.getVersion());
			} catch (Exception e) {
				logger.error("save RuneData - apiId [ " + runeJson.getId() + " ] occur exception : ", e);
				MessageUtils.setApiSaveErrorMessage(message, GameTypeEnum.LOL, "RuneData", runeJson.getId().longValue(), e);
			}
		}
		if(StringUtils.isNotBlank(message)){
			throw new Exception(message.toString());
		}
	}

	@Transactional
	private RuneData doSaveRuneData(Game game, RuneData runeData, RuneJson runeJson, String version) {
		if(runeData == null) {
			runeData = new RuneData();
			runeData.setApiId(runeJson.getId().longValue());
			runeData.setGame(game);
		}
		runeData.setVersion(version);
		runeData.setDescription(runeJson.getDescription());
		runeData.setName(runeJson.getName());
		if(runeJson.getImage() != null){
			RuneImageJson imageJson = runeJson.getImage();
			runeData.setImageFullName(imageJson.getFull());
			runeData.setImageW(imageJson.getW());
			runeData.setImageH(imageJson.getH());
		}
		if(runeJson.getRune() != null){
			RuneDetailJson runeDetailJson = runeJson.getRune();
			runeData.setIsRune(runeDetailJson.getIsRune());
			runeData.setTier(runeDetailJson.getTier());
			runeData.setType(runeDetailJson.getType());
		}
		runeData = this.runeDataDao.save(runeData);
		if(logger.isDebugEnabled())logger.debug("saveRuneData success : " + runeData);
		return runeData;
	}
}
