package com.hkesports.matchticker.service.dota2;
import java.util.List;

import com.hkesports.matchticker.model.json.dota2.matchDetails.MatchDetailsResultJson;
import com.hkesports.matchticker.model.json.dota2.matchHistory.MatchJson;
import com.hkesports.matchticker.model.json.dota2.playerSummaries.PlayerJson;
import com.hkesports.matchticker.model.json.dota2.playerSummaries.PlayerSummariesResultJson;
import com.hkesports.matchticker.model.json.dota2.teamInfo.TeamInfoResultJson;

public interface Dota2MatchService {
	/**
	 * 執行 GetScheduledLeagueGames Api 來產生資料(coming up)
	 * @throws Exception
	 */
	public void updateMatchScheduleByBatchjobTmp() throws Exception;
	
	public void updateMatchScheduleForApi() throws Exception;
	
	
	public void updateMatchScheduleForApi(String leagueId) throws Exception;
	public void updateMatchScheduleForMatchApi(String matchId) throws Exception;
	public List<MatchJson> getMatchHistoryForApi(String LeagueId) throws Exception;
	public MatchDetailsResultJson getMatchDetailForApi(String matchId) throws Exception;
	public PlayerSummariesResultJson getPlayerForApi(List<Long> playerIds) throws Exception;
	public PlayerJson getPlayerForApi(Long playerId) throws Exception;
	public TeamInfoResultJson getTeamForApi(String teamId) throws Exception;
}
