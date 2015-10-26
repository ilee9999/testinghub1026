package com.hkesports.matchticker.service.dota2.impl;

import static com.hkesports.matchticker.common.utils.AppUtils.bindTo64Bit;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.google.gson.reflect.TypeToken;
import com.hkesports.matchticker.common.utils.JsoupUtils;
import com.hkesports.matchticker.common.utils.MessageUtils;
import com.hkesports.matchticker.config.PropertiesConfig;
import com.hkesports.matchticker.enums.Dota2FileType;
import com.hkesports.matchticker.model.json.dota2.leagueList.LeagueJson;
import com.hkesports.matchticker.model.json.dota2.leagueList.LeagueListJson;
import com.hkesports.matchticker.model.json.dota2.matchDetails.MatchDetailsJson;
import com.hkesports.matchticker.model.json.dota2.matchDetails.MatchDetailsResultJson;
import com.hkesports.matchticker.model.json.dota2.matchDetails.PlayerJson;
import com.hkesports.matchticker.model.json.dota2.matchHistory.MatchHistoryJson;
import com.hkesports.matchticker.model.json.dota2.matchHistory.MatchJson;
import com.hkesports.matchticker.model.json.dota2.playerSummaries.PlayerSummariesJson;
import com.hkesports.matchticker.model.json.dota2.teamInfo.TeamInfoJson;
import com.hkesports.matchticker.model.json.dota2.teamInfo.TeamInfoResultJson;
import com.hkesports.matchticker.model.json.factory.JsonFactory;
import com.hkesports.matchticker.service.dota2.Dota2JsonFileService;


@Service("dota2JsonFileService")
public class Dota2JsonFileServiceImpl implements Dota2JsonFileService {
	
	private static final Log logger = LogFactory.getLog(Dota2JsonFileServiceImpl.class);
	
	@Resource(name = "jsoupUtils")
	private JsoupUtils jsoupUtils;
	
	@Resource(name = "propertiesConfig")
	private PropertiesConfig propertiesConfig;
	
	
	private Set<Long> playerSet = new HashSet<>();
	
	public void fetchFromHerosApi() throws Exception {
		String url = this.propertiesConfig.getProperty("dota2.hero.url");
		String jsonPath = this.jsoupUtils.getDota2JsonFilePath(Dota2FileType.Hero);
		String jsonStr = this.jsoupUtils.getJsonString(url);
		this.jsoupUtils.writeStrToFile(new File(jsonPath, "hero.json"), jsonStr);
	}
	
	public void fetchFromAbilityApi() throws Exception {
		String url = this.propertiesConfig.getProperty("dota2.abilities.url");
		String jsonPath = this.jsoupUtils.getDota2JsonFilePath(Dota2FileType.Ability);
		String jsonStr = this.jsoupUtils.getJsonString(url);
		this.jsoupUtils.writeStrToFile(new File(jsonPath, "abilities.json"), jsonStr);
	}
	
	public void fetchFromItemApi() throws Exception {
		String url = this.propertiesConfig.getProperty("dota2.item.url");
		String jsonPath = this.jsoupUtils.getDota2JsonFilePath(Dota2FileType.Item);
		String jsonStr = this.jsoupUtils.getJsonString(url);
		this.jsoupUtils.writeStrToFile(new File(jsonPath, "item.json"), jsonStr);
	}
	
	public List<LeagueJson> fetchFromLeagueListApi() throws Exception {
		String url = this.propertiesConfig.getProperty("dota2.leagueList.url");
		String jsonPath = this.jsoupUtils.getDota2JsonFilePath(Dota2FileType.LeagueList);
		String jsonStr = this.jsoupUtils.getJsonString(url);
		this.jsoupUtils.writeStrToFile(new File(jsonPath, "leagueList.json"), jsonStr);
		
		LeagueListJson jsonObj = JsonFactory.fromJson(jsonStr, new TypeToken<LeagueListJson>() {}.getType());
		if(jsonObj==null || jsonObj.getResult()==null) {
			return null;
		}
		return jsonObj.getResult().getLeagues();
	}

	@Override
	public List<MatchJson> fetchFromMatchHistoryApi(Long leagueId) throws Exception {
		if(leagueId==null)
			throw new NullPointerException("leagueId is null");
		
		String jsonPath = this.jsoupUtils.getDota2JsonFilePath(Dota2FileType.MatchHistory, leagueId);
		File jsonFile = new File(jsonPath);
		String jsonStr = null;
		if(jsonFile.exists()) {
			logger.info("MatchHistory file is exists");
			jsonStr = this.jsoupUtils.readFileToStr(jsonFile);
		} else {
			String url = StringUtils.replace(this.propertiesConfig.getProperty("dota2.matchHistory.url"), "{leagueId}", leagueId.toString());
			jsonStr = this.jsoupUtils.getJsonString(url);
			this.jsoupUtils.writeStrToFile(jsonFile, jsonStr);
		}
		
		MatchHistoryJson jsonObj = JsonFactory.fromJson(jsonStr, new TypeToken<MatchHistoryJson>() {}.getType());
		if(jsonObj==null || jsonObj.getResult()==null) {
			return null;
		}
		return jsonObj.getResult().getMatches();
	}
	
	@Override
	public MatchDetailsResultJson fetchFromMatchDetailApi(MatchJson matchJson) throws Exception {
		if(matchJson==null || matchJson.getMatchId() == null)
			throw new NullPointerException("matchJson or matchId is null");
		
		String matchId = matchJson.getMatchId().toString();
		String jsonPath = this.jsoupUtils.getDota2JsonFilePath(Dota2FileType.MatchDetail, matchId);
		File jsonFile = new File(jsonPath);
		String jsonStr = null;
		if(jsonFile.exists()) {
			logger.info("MatchDetails file is exists");
			jsonStr = this.jsoupUtils.readFileToStr(jsonFile);
		} else {
			String url = StringUtils.replace(this.propertiesConfig.getProperty("dota2.matchDetail.url"), "{matchId}", matchId);
			jsonStr = this.jsoupUtils.getJsonString(url);
			this.jsoupUtils.writeStrToFile(jsonFile, jsonStr);
		}
		
		MatchDetailsJson jsonObj = JsonFactory.fromJson(jsonStr, new TypeToken<MatchDetailsJson>() {}.getType());
		if(jsonObj!=null) {
			return jsonObj.getResult();
		}
		return null;
	}
	
	@Override
	public void fetchFromPlayerApi(MatchDetailsResultJson matchDetailJson) throws Exception {
		if(matchDetailJson==null) 
			throw new NullPointerException("matchDetailJson is null");
		
		List<PlayerJson> players = matchDetailJson.getPlayers();
		if(CollectionUtils.isEmpty(players)) {
			logger.info("players is empty");
			return;
		}
		logger.info("Players size:" + players.size());
		String org_url = this.propertiesConfig.getProperty("dota2.player.url");
		for(PlayerJson player:players) {
			if(player.getAccountId()==null)
				continue;
			if(playerSet.contains(player.getAccountId())) {
				continue;
			} else {
				playerSet.add(player.getAccountId());
			}
			
			String playerId = bindTo64Bit(player.getAccountId()).toString();
			logger.info("playerId:" + playerId);
			String jsonPath = this.jsoupUtils.getDota2JsonFilePath(Dota2FileType.Player, playerId);
			File jsonFile = new File(jsonPath);
			if(jsonFile.exists()) {
				logger.info("player file is exists");
				return;
			}
			
			String url = StringUtils.replace(org_url, "{playerId}", playerId);
			String jsonStr = this.jsoupUtils.getJsonString(url);
			if(StringUtils.isBlank(jsonStr)) {
				logger.error("playerId:" + playerId + " 找不到player");
			} else {
				this.jsoupUtils.writeStrToFile(jsonFile, jsonStr);
			}
		}
	}
	
	@Override
	public void fetchFromPlayerApi(Collection<Long> playerSet) throws Exception {
		if(CollectionUtils.isEmpty(playerSet)) {
			logger.info("playerSet is empty");
			return ;
		}
		logger.info("Players size:" + playerSet.size());
		StringBuilder message = new StringBuilder();
		String org_url = this.propertiesConfig.getProperty("dota2.player.url");
		int i = 0;
		for(Long playerId:playerSet) {
			playerId = bindTo64Bit(playerId);
			if(!new File(this.jsoupUtils.getDota2JsonFilePath(Dota2FileType.Player, playerId)).exists()) {
				MessageUtils.joinToString(message, playerId);
			}
			if((i!=0 && i%100==0) || i==playerSet.size()-1) {
				if(message.length()==0) {
					continue;//86079
				}
				String playerIds = message.toString();
				logger.info("playerId:" + playerIds);
				String jsonStr = this.jsoupUtils.getJsonString(StringUtils.replace(org_url, "{playerId}", playerIds));
				if(StringUtils.isBlank(jsonStr)) {
					logger.error("playerId:" + playerIds + " 找不到player");
					continue;
				} 
				PlayerSummariesJson jsonObj = JsonFactory.fromJson(jsonStr, new TypeToken<PlayerSummariesJson>() {}.getType());
				if(jsonObj!=null && jsonObj.getResponse()!=null) {
					List<com.hkesports.matchticker.model.json.dota2.playerSummaries.PlayerJson> playerJsons = jsonObj.getResponse().getPlayers();
					if(!CollectionUtils.isEmpty(playerJsons)) {
						for(com.hkesports.matchticker.model.json.dota2.playerSummaries.PlayerJson playerJson:playerJsons) {
							String tmpJsonStr = JsonFactory.toJson(playerJson, new TypeToken<com.hkesports.matchticker.model.json.dota2.playerSummaries.PlayerJson>() {}.getType());
							String jsonPath = this.jsoupUtils.getDota2JsonFilePath(Dota2FileType.Player, playerJson.getSteamid());
							this.jsoupUtils.writeStrToFile(new File(jsonPath), tmpJsonStr);
						}
					}
					
				}
				message = new StringBuilder();
			}
			i++;
		}
	}
	
	@Override
	public void fetchFromTeamApi(Long teamId) throws Exception {
		if(teamId==null) 
			throw new NullPointerException("teamId is null");
		
		String url = StringUtils.replace(this.propertiesConfig.getProperty("dota2.teamInfo.url"), "{teamId}", teamId.toString());
		String jsonPath = this.jsoupUtils.getDota2JsonFilePath(Dota2FileType.TeamInfo, teamId);
		String jsonStr = this.jsoupUtils.getJsonString(url);
		this.jsoupUtils.writeStrToFile(new File(jsonPath), jsonStr);
	}
	
	public void fetchAllTeamFromTeamApi() throws Exception {
		String urlTeamId = "1";
		String orgUrl = this.propertiesConfig.getProperty("dota2.teamInfo2.url");
		while(true) {
			String url = StringUtils.replace(orgUrl, "{teamId}", urlTeamId);
			String jsonStr = this.jsoupUtils.getJsonString(url);
			if(StringUtils.isBlank(jsonStr)) {
				break;
			}
			TeamInfoJson jsonObj = JsonFactory.fromJson(jsonStr, new TypeToken<TeamInfoJson>() {}.getType());
			if(jsonObj==null || jsonObj.getResult()==null) {
				break;
			}
			List<Map<String, String>> teams = jsonObj.getResult().getTeams();
			if(CollectionUtils.isEmpty(teams)) {
				break;
			}
			List<Long> tmpTeamIdList = new ArrayList<>();
			for(Map<String, String> team:teams) {
				if(!CollectionUtils.isEmpty(team)) {
					Object teamId = team.get("team_id");
					if(teamId==null) {
						continue;
					}
					String jsonPath = this.jsoupUtils.getDota2JsonFilePath(Dota2FileType.TeamInfo, teamId.toString());
					TeamInfoJson tmpTeamInfo = new TeamInfoJson();
					TeamInfoResultJson tmpResult = new TeamInfoResultJson();
					tmpTeamInfo.setResult(tmpResult);
					tmpResult.setTeams(Arrays.asList(team));
					this.jsoupUtils.writeStrToFile(new File(jsonPath), JsonFactory.toJson(tmpTeamInfo, new TypeToken<TeamInfoJson>() {}.getType()));
					tmpTeamIdList.add(Long.valueOf(teamId.toString()));
				}
			}
			Collections.sort(tmpTeamIdList);
			logger.info("start_id:" + urlTeamId + " start_with:" + tmpTeamIdList.get(0) + " end_with:" + tmpTeamIdList.get(tmpTeamIdList.size()-1));
			urlTeamId = String.valueOf((tmpTeamIdList.get(tmpTeamIdList.size()-1) + 1));
		}
	}
	
	public void doFetchAll() throws Exception {
		/*
		logger.info("do fetch hero");
		fetchFromHerosApi();
		logger.info("do fetch abilities");
		fetchFromAbilityApi();
		logger.info("do fetch item");
		fetchFromItemApi();
		 */
		
		logger.info("do fetch LeagueList");
		List<LeagueJson> leagueList = fetchFromLeagueListApi();
		if(!CollectionUtils.isEmpty(leagueList)) {
			Set<Long> playerSet = new HashSet<>();
			int count = 1;
			int size = leagueList.size();
			for(LeagueJson league:leagueList) {
				if(league==null) {
					continue;
				}
				logger.info("do fetch MatchHistory leagueid:" + league.getLeagueid() + "  (" + count + "/" + size + ")");
				List<MatchJson> matchList = fetchFromMatchHistoryApi(league.getLeagueid());
				if(CollectionUtils.isEmpty(matchList)) {
					continue;
				}
				
				for(MatchJson matchJson:matchList) {
					logger.info("do fetch MatchDetail matchId:" + matchJson.getMatchId() + "  (" + count + "/" + size + ")");
					MatchDetailsResultJson matchDetailsResult = fetchFromMatchDetailApi(matchJson);
					if(matchDetailsResult==null) {
						continue;
					}
					if(!CollectionUtils.isEmpty(matchDetailsResult.getPlayers())) {
						for(PlayerJson player:matchDetailsResult.getPlayers()) {
							if(player.getAccountId()!=null)
								playerSet.add(player.getAccountId());
							else
								logger.warn("AccountId is null leagueid:" + league.getLeagueid() + ", matchId:" + matchDetailsResult.getMatchId());
						}
					}
					
//					if(matchDetailsResult.getRadiantTeamId()!=null) {
//						logger.info("do fetch RadiantTeam TeamId:" + matchDetailsResult.getRadiantTeamId());
//						fetchFromTeamApi(matchDetailsResult.getRadiantTeamId());
//					}
//					if(matchDetailsResult.getDireTeamId()!=null) {
//						logger.info("do fetch DireTeam TeamId:" + matchDetailsResult.getDireTeamId());
//						fetchFromTeamApi(matchDetailsResult.getDireTeamId());
//					}
				}
				count++;
			}
			fetchFromPlayerApi(playerSet);
		}
		
	}
}
