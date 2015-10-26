package com.hkesports.matchticker.service.dota2;

import java.util.Collection;
import java.util.List;

import com.hkesports.matchticker.model.json.dota2.matchDetails.MatchDetailsResultJson;
import com.hkesports.matchticker.model.json.dota2.matchHistory.MatchJson;

public interface Dota2JsonFileService {
	
	public List<MatchJson> fetchFromMatchHistoryApi(Long leagueId) throws Exception;
	
	public MatchDetailsResultJson fetchFromMatchDetailApi(MatchJson matchJson) throws Exception;
	
	public void fetchFromPlayerApi(MatchDetailsResultJson matchDetailJson) throws Exception;
	
	public void fetchFromPlayerApi(Collection<Long> playerSet) throws Exception;
	
	public void fetchFromTeamApi(Long teamId) throws Exception;
	
	public void fetchAllTeamFromTeamApi() throws Exception;
	
	public void doFetchAll() throws Exception;
}
