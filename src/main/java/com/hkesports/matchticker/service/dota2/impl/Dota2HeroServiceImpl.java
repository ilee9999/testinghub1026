package com.hkesports.matchticker.service.dota2.impl;

import java.util.ArrayList;
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
import com.hkesports.matchticker.model.batchJob.Hero;
import com.hkesports.matchticker.model.json.dota2.hero.HeroJson;
import com.hkesports.matchticker.model.json.dota2.hero.HeroesJson;
import com.hkesports.matchticker.model.json.factory.JsonFactory;
import com.hkesports.matchticker.repository.HeroDao;
import com.hkesports.matchticker.service.GameService;
import com.hkesports.matchticker.service.dota2.Dota2HeroService;

@Service("dota2HeroService")
public class Dota2HeroServiceImpl implements Dota2HeroService {
	private static final Log logger = LogFactory.getLog(Dota2HeroServiceImpl.class);
	
	@Resource(name = "jsoupUtils")
	private JsoupUtils jsoupUtils;
	
	@Resource(name = "propertiesConfig")
	private PropertiesConfig propertiesConfig;
	
	@Resource(name = "heroDao")
	private HeroDao heroDao;

	@Resource
	private GameService gameService;
	
	@Override
	public void updateHerosForApi() throws Exception {
		List<Hero> heroList = new ArrayList<Hero>();
		mergeApiHeroAndDbHero(heroList, getApiHeroList());
		String message = saveHero(heroList);
		
		if(message != null && message.length() > 0){
			throw new Exception(message);
		}
	}
	
	@Override
	public List<HeroJson> getApiHeroList() throws Exception {
		String heroUrl = propertiesConfig.getProperty("dota2.hero.url");
		HeroesJson heroes = JsonFactory.fromJson(jsoupUtils.getJsonString(heroUrl), new TypeToken<HeroesJson>(){}.getType());
		if(heroes.getResult().getStatus() != 200){
			String message = "get Dota2 hero api, response http status: " + heroes.getResult().getStatus();
			logger.info(message);
			throw new Exception(message);
		}
		
		return heroes.getResult().getHeroes();
	}
	
	@Override
	public List<Hero> findAllHero() {
		Game game = gameService.findByGameCode(GameTypeEnum.DOTA2);
		return heroDao.findByGameId(game.getId());
	}
	
	@Override
	public Map<Long, Hero> findAllHeroToMap(){
		List<Hero> heroList = findAllHero();
		Map<Long, Hero> map = new HashMap<Long, Hero>();
		for(Hero hero : heroList)
			map.put(hero.getApiId(), hero);
		return map;
	}
	
	private void mergeApiHeroAndDbHero(List<Hero> heroList, List<HeroJson> apiHeroList){
		Map<Long, Hero> oldHeroMap = findAllHeroToMap();
		Game game = gameService.findByGameCode(GameTypeEnum.DOTA2);
		for(HeroJson json : apiHeroList){
			Hero oldHero = null;
			if(oldHeroMap.get(json.getId()) != null)
				oldHero = (Hero) oldHeroMap.get(json.getId());

			if(oldHero == null)
				heroList.add(bindHeroJsonToHero(game, json));
			else if(!oldHero.equals(json))
				heroList.add(bindHeroJsonToHero(game, json, oldHero));
		}
	}
	
	private Hero bindHeroJsonToHero(Game game, HeroJson json){		
		return bindHeroJsonToHero(game, json, new Hero());
	}
	
	private Hero bindHeroJsonToHero(Game game, HeroJson json, Hero hero){
		hero.setGame(game);
		hero.setApiId(Long.valueOf(json.getId()));
		hero.setEnName(json.getName());
		hero.setName(json.getLocalizedName());
		
		return hero;
	}
	
	@Transactional
	private String saveHero(List<Hero> heroList){
		StringBuilder message = new StringBuilder("");
		
		for(Hero hero : heroList){
			try{
				heroDao.save(hero);
			}catch(DataAccessException e){
				logger.error(e);
				MessageUtils.setApiSaveErrorMessage(message, GameTypeEnum.DOTA2, "Hero", hero.getApiId(), e);
			}
		}
		
		return message.toString();
	}
}
