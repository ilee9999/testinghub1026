/**
 * 
 */
package com.hkesports.matchticker.service.lol;

import java.util.List;

import com.hkesports.matchticker.model.batchJob.Hero;
import com.hkesports.matchticker.model.json.lol.champion.ChampionListJson;

/**
 * @author manboyu
 *
 */
public interface LoLHeroService {

	public ChampionListJson getChampionListJson() throws Exception;
	
	public List<Hero> findHeros();
	
	public void saveHero() throws Exception;
}
