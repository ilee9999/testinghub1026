package com.hkesports.matchticker.service.dota2.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hkesports.matchticker.common.utils.JsoupUtils;
import com.hkesports.matchticker.common.utils.MessageUtils;
import com.hkesports.matchticker.config.PropertiesConfig;
import com.hkesports.matchticker.enums.GameTypeEnum;
import com.hkesports.matchticker.model.Game;
import com.hkesports.matchticker.model.batchJob.Hero;
import com.hkesports.matchticker.model.batchJob.SpellData;
import com.hkesports.matchticker.model.json.dota2.ability.Ability;
import com.hkesports.matchticker.repository.SpellDataDao;
import com.hkesports.matchticker.service.GameService;
import com.hkesports.matchticker.service.dota2.Dota2AbilityService;
import com.hkesports.matchticker.service.dota2.Dota2HeroService;

@Service("dota2AbilityService")
public class Dota2AbilityServiceImpl implements Dota2AbilityService {
	private static final Log logger = LogFactory.getLog(Dota2AbilityServiceImpl.class);
	
	@Resource(name = "jsoupUtils")
	private JsoupUtils jsoupUtils;
	
	@Resource(name = "propertiesConfig")
	private PropertiesConfig propertiesConfig;
	
	@Resource(name = "spellDataDao")
	private SpellDataDao spellDataDao;
	
	@Resource(name = "dota2HeroService")
	private Dota2HeroService dota2HeroService;
	
	@Resource
	private GameService gameService;

	@Override
	public void updateAbilityForApi() throws Exception {
		List<SpellData> spellDataList = new ArrayList<SpellData>();
		String message = mergeSpellData(spellDataList, getAbilityListForApi());
		String message2 = saveSpellData(spellDataList);
		
		if(message == null)
			message = message2;
		else
			message += message2;
		
		if(message != null && message.length() > 0){
			throw new Exception(message);
		}
	}

	@Override
	public List<Ability> getAbilityListForApi() throws Exception{
		List<Ability> abilityList = new ArrayList<Ability>();
		
		Document doc = jsoupUtils.getDocument(propertiesConfig.getProperty("dota2.abilities.url"));
		Elements table = doc.select("table tbody tr .js-file-line");
		
		Ability ability = null;
		for(Element node : table){
			node.select(".pl-pds").remove();
			Elements data = node.select("span");
			if(data.size() == 2){
				String key = data.get(0).text();
				if(key.equals("id")){
					ability = new Ability();
					abilityList.add(ability);
				}
				bindApiDataToBean(ability, key, data.get(1).text());
			}
		}
		
		return abilityList;
	}
	
	private void bindApiDataToBean(Ability ability, String key, String value){
		logger.debug("Dota2AbilityServiceImpl.bindApiDataToBean(Ability, String, String) is support JDK 1.7 up");
		switch (key) {
			case "id":
				ability.setId(Long.valueOf(value));
				break;
			case "heroId":
				ability.setHeroId(Long.valueOf(value));
				break;
			case "name":
				ability.setName(value);
				break;
			case "localizedName":
				ability.setLocalizedName(value);
				break;
			default:
				break;
		}
	}
	
	private String mergeSpellData(List<SpellData> spellDataList, List<Ability> abilityList){
		Map<Long, Hero> heroMap = dota2HeroService.findAllHeroToMap();
		Map<Long, SpellData> oldSpellDataMap = findAllSpellDataToMap();
		
		StringBuilder message = new StringBuilder();
		Game game = gameService.findByGameCode(GameTypeEnum.DOTA2);
		for(Ability ability : abilityList){
			SpellData spellData = null;
			if(oldSpellDataMap.get(ability.getId()) != null)
				spellData = oldSpellDataMap.get(ability.getId());
			
			Long heroId = null;
			Hero hero = null;
			if(ability.getHeroId() != null){
				hero = heroMap.get(ability.getHeroId());
				if(hero == null)
					message.append("hero api id ").append(ability.getHeroId()).append(" have not data in DB \r\n");
				else
					heroId = hero.getId();
			}
			
			Long oldHeroId = null;
			if(spellData != null && spellData.getHero() != null){
				oldHeroId = spellData.getHero().getId();
			}
			
			if(spellData == null)
				spellDataList.add(bindJsonToModel(game, ability, hero));
			else if(!spellData.equals(ability) || heroId != oldHeroId)
				spellDataList.add(bindJsonToModel(game, ability, hero, spellData));
		}
		
		for(Long apiId : oldSpellDataMap.keySet()){
			SpellData spell = oldSpellDataMap.get(apiId);
			if(!abilityList.contains(spell) && spell.getExpireDate() == null){
				spell.setExpireDate(new Date());
				spellDataList.add(spell);
			}
		}
		
		return message.toString();
	}
	
	private Map<Long, SpellData> findAllSpellDataToMap(){
		Map<Long, SpellData> map = new HashMap<Long, SpellData>();
		Game game = gameService.findByGameCode(GameTypeEnum.DOTA2);
		List<SpellData> spellDataList = spellDataDao.findByGameId(game.getId());
		for(SpellData spellData : spellDataList){
			map.put(spellData.getApiId(), spellData);
		}
		return map;
	}

	private SpellData bindJsonToModel(Game game, Ability ability, Hero hero){		
		return bindJsonToModel(game, ability, hero, new SpellData());
	}
	
	private SpellData bindJsonToModel(Game game, Ability ability, Hero hero, SpellData spellData){
		spellData.setGame(game);
		spellData.setApiId(ability.getId());
		spellData.setEnName(ability.getName());
		spellData.setName(ability.getLocalizedName());
		spellData.setHero(hero);
		
		return spellData;
	}
	
	/** 
	 * save spellData list
	 * @param spellDataList is save to DB
	 */
	@Transactional
	private String saveSpellData(List<SpellData> spellDataList){
		StringBuilder message = new StringBuilder("");
		
		for(SpellData spellData : spellDataList){
			try{
				spellDataDao.save(spellData);
			}catch(DataAccessException e){
				logger.error(e);
				MessageUtils.setApiSaveErrorMessage(message, GameTypeEnum.DOTA2, "Ability", spellData.getApiId(), e);
			}
		}
		
		return message.toString();
	}
}
