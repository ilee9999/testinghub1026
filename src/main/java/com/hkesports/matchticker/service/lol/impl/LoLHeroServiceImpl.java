/**
 * 
 */
package com.hkesports.matchticker.service.lol.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.reflect.TypeToken;
import com.hkesports.matchticker.common.utils.AppUtils;
import com.hkesports.matchticker.common.utils.JsoupUtils;
import com.hkesports.matchticker.common.utils.MessageUtils;
import com.hkesports.matchticker.config.PropertiesConfig;
import com.hkesports.matchticker.enums.GameTypeEnum;
import com.hkesports.matchticker.model.Game;
import com.hkesports.matchticker.model.batchJob.Hero;
import com.hkesports.matchticker.model.json.factory.JsonFactory;
import com.hkesports.matchticker.model.json.lol.champion.ChampionImageJson;
import com.hkesports.matchticker.model.json.lol.champion.ChampionJson;
import com.hkesports.matchticker.model.json.lol.champion.ChampionListJson;
import com.hkesports.matchticker.repository.HeroDao;
import com.hkesports.matchticker.service.GameService;
import com.hkesports.matchticker.service.lol.LoLHeroService;
import com.hkesports.matchticker.service.lol.LoLItemDataService;

/**
 * @author manboyu
 *
 */
@Service("loLHeroService")
public class LoLHeroServiceImpl implements LoLHeroService {

	private static final Logger logger = LoggerFactory.getLogger(LoLHeroServiceImpl.class); 
	
	@Resource(name = "propertiesConfig")
	private PropertiesConfig propertiesConfig;
	@Resource(name = "jsoupUtils")
	private JsoupUtils jsoupUtils;
	
	@Resource(name = "heroDao")
	private HeroDao heroDao;
	
	@Resource(name = "loLItemDataService")
	private LoLItemDataService loLItemDataService;
	
	@Resource
	private GameService gameService;
	
	@Override
	public ChampionListJson getChampionListJson() throws Exception {
		String championUrl = this.propertiesConfig.getProperty("lol.champion.url");
		JsonFactory<ChampionListJson> jsonObj = new JsonFactory<>(this.jsoupUtils.getJsonString(championUrl), new TypeToken<ChampionListJson>(){}.getType());
		return !jsonObj.isEmpty() ? jsonObj.getData() : null;
	}

	@Override
	public List<Hero> findHeros() {
		Game game = gameService.findByGameCode(GameTypeEnum.LOL);
		return this.heroDao.findByGameId(game.getId());
	}

	@Override
	public void saveHero() throws Exception {
		StringBuilder message = new StringBuilder();
		List<Hero> heros = findHeros();
		Map<Long, Hero> map = AppUtils.putModelByApiId(heros);
		ChampionListJson championListJson = getChampionListJson();
		if(championListJson == null){
			throw new Exception("there's no champion data from URL : " + this.propertiesConfig.getProperty("lol.champion.url"));
		}
			
		Map<String, ChampionJson> data = championListJson.getData();
		Game game = gameService.findByGameCode(GameTypeEnum.LOL);
		for (Map.Entry<String, ChampionJson> entry : data.entrySet()) {
			ChampionJson championJson = entry.getValue();
			Hero oHero = map.get(championJson.getId().longValue());
			try {
				if(oHero != null && StringUtils.equals(oHero.getVersion(), championListJson.getVersion()) && oHero.equals(championJson)) {
					continue;
				}
				this.doSaveHero(game, oHero, championJson, championListJson.getVersion());
			} catch (DataAccessException e) {
				logger.error("save Hero - apiId [ " + championJson.getId() + " ] occur exception : ", e);
				MessageUtils.setApiSaveErrorMessage(message, GameTypeEnum.LOL, "Hero", championJson.getId().longValue(), e);
			}
		}
		if(StringUtils.isNotBlank(message)){
			throw new Exception(message.toString());
		}
	}
	
	@Transactional
	private Hero doSaveHero(Game game, Hero hero, ChampionJson championJson, String version) {
		if(hero == null) {
			hero = new Hero();
			hero.setApiId(championJson.getId().longValue());
			hero.setGame(game);
		}
		hero.setVersion(version);
		hero.setName(championJson.getName());
		hero.setEnName(championJson.getKey());
		hero.setTitle(championJson.getTitle());
		if(championJson.getImage() != null){
			ChampionImageJson imageJson = championJson.getImage();
			hero.setImageFullName(imageJson.getFull());
			hero.setImageW(imageJson.getW());
			hero.setImageH(imageJson.getH());
		}
		hero = this.heroDao.save(hero);
		logger.info("saveHero success : " + hero);
		return hero;
	}

}
