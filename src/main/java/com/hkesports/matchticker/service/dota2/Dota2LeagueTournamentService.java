package com.hkesports.matchticker.service.dota2;

import java.util.List;
import java.util.Map;

import com.hkesports.matchticker.model.batchJob.Tourament;
import com.hkesports.matchticker.model.json.dota2.leagueList.LeagueJson;

public interface Dota2LeagueTournamentService {
	public void updateLeagueTournamentForApi() throws Exception;

	public List<LeagueJson> getApiLeagueList() throws Exception;

	public List<Tourament> findAllTourament();

	public Map<Long, Tourament> findTouramentDataToMap();
	
	/**
	 * 執行 GetScheduledLeagueGames Api(coming up) & GetLiveLeagueGames Api 來產生資料
	 * @throws Exception
	 */
	public void saveScheduleLiveLeague() throws Exception;
	
}
