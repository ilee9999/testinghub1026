package com.hkesports.matchticker.service.batch;

import java.io.IOException;

/**
 * @author manboyu
 *
 */
public interface BatchForLolService {

	/**
	 * batchLolLeagues - league(model in tourament) data
	 * @throws IOException 
	 */
	public void batchLolLeagues() throws Exception;
	
	/**
	 * batchLolTouraments - [schedule, game, team, player] data By ScheduleEndTime
	 */
	public void batchLolTouraments() throws Exception;
	
	/**
	 * batchLolAllTouraments - [schedule, game, team, player] data full update
	 */
	public void batchLolAllTouraments() throws Exception;
	
	/**
	 * batchLolChampions - champion data
	 * @throws Exception
	 */
	public void batchLolChampions() throws Exception;
	
	/**
	 * batchLolItems - item data
	 * @throws IOException 
	 */
	public void batchLolItems() throws Exception;
	
	/**
	 * batchLolSpells - summonerSpell data
	 */
	public void batchLolSpells() throws Exception;
	
	/**
	 * batchLolRunes - rune data
	 */
	public void batchLolRunes() throws Exception;
}
