package com.hkesports.matchticker.service.dota2;

import java.util.List;
import java.util.Map;

import com.hkesports.matchticker.model.batchJob.Hero;
import com.hkesports.matchticker.model.json.dota2.hero.HeroJson;

public interface Dota2HeroService {

	public void updateHerosForApi() throws Exception;
	
	public List<HeroJson> getApiHeroList() throws Exception;
	
	public List<Hero> findAllHero();
	
	public Map<Long, Hero> findAllHeroToMap();
}
