package com.hkesports.matchticker.service.dota2.impl;

import static com.hkesports.matchticker.common.utils.AppUtils.bindTo32Bit;
import static com.hkesports.matchticker.common.utils.AppUtils.bindTo64Bit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.google.gson.reflect.TypeToken;
import com.hkesports.matchticker.common.utils.AppUtils;
import com.hkesports.matchticker.common.utils.JsoupUtils;
import com.hkesports.matchticker.common.utils.MessageUtils;
import com.hkesports.matchticker.common.utils.RandomUtils;
import com.hkesports.matchticker.config.PropertiesConfig;
import com.hkesports.matchticker.enums.Dota2FileType;
import com.hkesports.matchticker.enums.Dota2TeamType;
import com.hkesports.matchticker.enums.GameTypeEnum;
import com.hkesports.matchticker.enums.GameWinTypeEnum;
import com.hkesports.matchticker.enums.ScheduleStatus;
import com.hkesports.matchticker.model.Game;
import com.hkesports.matchticker.model.basic.BasicApiInfo;
import com.hkesports.matchticker.model.batchJob.AbilityUpgrades;
import com.hkesports.matchticker.model.batchJob.GamePicksBans;
import com.hkesports.matchticker.model.batchJob.Hero;
import com.hkesports.matchticker.model.batchJob.Player;
import com.hkesports.matchticker.model.batchJob.Schedule;
import com.hkesports.matchticker.model.batchJob.ScheduleGame;
import com.hkesports.matchticker.model.batchJob.ScheduleGameDetail;
import com.hkesports.matchticker.model.batchJob.ScheduleGamePlayerDetail;
import com.hkesports.matchticker.model.batchJob.ScheduleGamePlayerItems;
import com.hkesports.matchticker.model.batchJob.Team;
import com.hkesports.matchticker.model.batchJob.TeamMember;
import com.hkesports.matchticker.model.batchJob.Tourament;
import com.hkesports.matchticker.model.batchJob.TournamentBatchjobTmp;
import com.hkesports.matchticker.model.json.dota2.matchDetails.AbilityUpgradesJson;
import com.hkesports.matchticker.model.json.dota2.matchDetails.MatchDetailsJson;
import com.hkesports.matchticker.model.json.dota2.matchDetails.MatchDetailsResultJson;
import com.hkesports.matchticker.model.json.dota2.matchDetails.PicksBansJson;
import com.hkesports.matchticker.model.json.dota2.matchDetails.PlayerJson;
import com.hkesports.matchticker.model.json.dota2.matchHistory.MatchHistoryJson;
import com.hkesports.matchticker.model.json.dota2.matchHistory.MatchHistoryResultJson;
import com.hkesports.matchticker.model.json.dota2.matchHistory.MatchJson;
import com.hkesports.matchticker.model.json.dota2.playerSummaries.PlayerSummariesJson;
import com.hkesports.matchticker.model.json.dota2.playerSummaries.PlayerSummariesResultJson;
import com.hkesports.matchticker.model.json.dota2.teamInfo.TeamInfoJson;
import com.hkesports.matchticker.model.json.dota2.teamInfo.TeamInfoResultJson;
import com.hkesports.matchticker.model.json.factory.JsonFactory;
import com.hkesports.matchticker.repository.TournamentBatchjobTmpDao;
import com.hkesports.matchticker.service.AbilityUpgradesService;
import com.hkesports.matchticker.service.GamePicksBansService;
import com.hkesports.matchticker.service.GameService;
import com.hkesports.matchticker.service.PlayerService;
import com.hkesports.matchticker.service.ScheduleGameDetailService;
import com.hkesports.matchticker.service.ScheduleGamePlayerDetailService;
import com.hkesports.matchticker.service.ScheduleGamePlayerItemsService;
import com.hkesports.matchticker.service.ScheduleGameService;
import com.hkesports.matchticker.service.ScheduleService;
import com.hkesports.matchticker.service.TeamMemberService;
import com.hkesports.matchticker.service.TeamService;
import com.hkesports.matchticker.service.TouramentService;
import com.hkesports.matchticker.service.dota2.Dota2HeroService;
import com.hkesports.matchticker.service.dota2.Dota2LeagueTournamentService;
import com.hkesports.matchticker.service.dota2.Dota2MatchService;
import com.hkesports.matchticker.vo.Dota2Item;
import com.hkesports.matchticker.vo.Dota2MatchGroupVo;
import com.hkesports.matchticker.vo.Dota2TeamJsonVo;

@Service("dota2MatchService")
public class Dota2MatchServiceImpl implements Dota2MatchService {
	
	private static final Logger logger = LoggerFactory.getLogger(Dota2MatchServiceImpl.class);
	
	@Resource(name = "jsoupUtils")
	private JsoupUtils jsoupUtils;
	
	@Resource(name = "propertiesConfig")
	private PropertiesConfig propertiesConfig;
	
	@Resource
	private TouramentService touramentService;

	@Resource
	private ScheduleService scheduleService;
	
	@Resource
	private GameService gameService;
	
	@Resource
	private ScheduleGameService scheduleGameService;
	
	@Resource
	private ScheduleGameDetailService scheduleGameDetailService;
	
	@Resource
	private ScheduleGamePlayerDetailService scheduleGamePlayerDetailService;
	
	@Resource
	private ScheduleGamePlayerItemsService scheduleGamePlayerItemsService;
	
	@Resource
	private AbilityUpgradesService abilityUpgradesService;
	
	@Resource
	private GamePicksBansService gamePicksBansService;
	
	@Resource
	private TeamService teamService;
	
	@Resource
	private PlayerService playerService;
	
//	@Resource
//	private ApiIdTmpService apiIdTmpService;
	
	@Resource
	private TeamMemberService teamMemberService;
	
	@Resource(name = "dota2LeagueTournamentService")
	private Dota2LeagueTournamentService dota2LeagueTournamentService;
	
	@Resource(name = "dota2HeroService")
	private Dota2HeroService dota2HeroService;
	
	private Game gameCache = null;
	
	@Resource
	private TournamentBatchjobTmpDao tournamentBatchjobTmpDao;
	
	@Override
	public List<MatchJson> getMatchHistoryForApi(String leagueId) throws Exception {
		String url = StringUtils.replace(this.propertiesConfig.getProperty("dota2.matchHistory.url"), "{leagueId}", leagueId);
		String jsonStr = this.jsoupUtils.getJsonString(url, Dota2FileType.MatchHistory, leagueId);
		MatchHistoryJson data = JsonFactory.fromJson(jsonStr, new TypeToken<MatchHistoryJson>() {}.getType());
		if(data==null) {
			return new ArrayList<MatchJson>(0);
		}
		MatchHistoryResultJson jsonData = data.getResult();
		return jsonData == null ? new ArrayList<MatchJson>(0) : jsonData.getMatches();
	}

	@Override
	public MatchDetailsResultJson getMatchDetailForApi(String matchId) throws Exception {
		String url = StringUtils.replace(this.propertiesConfig.getProperty("dota2.matchDetail.url"), "{matchId}", matchId);
		String jsonStr = this.jsoupUtils.getJsonString(url, Dota2FileType.MatchDetail, matchId);
		MatchDetailsJson data = JsonFactory.fromJson(jsonStr, new TypeToken<MatchDetailsJson>() {}.getType());
		return data == null ? null : data.getResult();
	}
	
	@Override
	public PlayerSummariesResultJson getPlayerForApi(List<Long> playerIds) throws Exception {
		if(CollectionUtils.isEmpty(playerIds))
			return null;
		List<Long> tmpIds = new ArrayList<>(playerIds.size());
		for(Long id:playerIds) {
			tmpIds.add(bindTo64Bit(id));
		}
		String url = this.propertiesConfig.getProperty("dota2.player.url");
		List<String> jsonList = this.jsoupUtils.getJsonString(url, "{playerId}", Dota2FileType.Player, tmpIds);
		if(CollectionUtils.isEmpty(jsonList)) {
			return null;
		}
		PlayerSummariesResultJson result = new PlayerSummariesResultJson();
		List<com.hkesports.matchticker.model.json.dota2.playerSummaries.PlayerJson> resultList = new ArrayList<>();
		for(String json:jsonList) {
			if(StringUtils.isNotBlank(json) && json.contains("response")) {
				PlayerSummariesJson tmpResult = JsonFactory.fromJson(json, new TypeToken<com.hkesports.matchticker.model.json.dota2.playerSummaries.PlayerSummariesJson>() {}.getType());
				if(tmpResult!=null && tmpResult.getResponse()!=null && tmpResult.getResponse().getPlayers()!=null) {
					for(com.hkesports.matchticker.model.json.dota2.playerSummaries.PlayerJson tmpJsonObj:tmpResult.getResponse().getPlayers()) {
						resultList.add(tmpJsonObj);
					}
				}
			} else {
				com.hkesports.matchticker.model.json.dota2.playerSummaries.PlayerJson jsonObj = JsonFactory.fromJson(json, new TypeToken<com.hkesports.matchticker.model.json.dota2.playerSummaries.PlayerJson>() {}.getType());
				resultList.add(jsonObj);
			}
		}
		
		result.setPlayers(resultList);
		return result;
	}
	
	@Override
	public com.hkesports.matchticker.model.json.dota2.playerSummaries.PlayerJson getPlayerForApi(Long playerId) throws Exception {
		String playerId64 = bindTo64Bit(playerId).toString();
		String url = StringUtils.replace(this.propertiesConfig.getProperty("dota2.player.url"), "{playerId}", playerId64);
		String jsonStr = this.jsoupUtils.getJsonString(url, Dota2FileType.Player, playerId64);
		PlayerSummariesJson data = JsonFactory.fromJson(jsonStr, new TypeToken<PlayerSummariesJson>() {}.getType());
		PlayerSummariesResultJson resultJson = data == null ? null : data.getResponse();
		if(resultJson == null) {
			return null;
		}
		return CollectionUtils.isEmpty(resultJson.getPlayers()) ? null : resultJson.getPlayers().get(0);
	}
	
	@Override
	public TeamInfoResultJson getTeamForApi(String teamId) throws Exception {
		String url = StringUtils.replace(this.propertiesConfig.getProperty("dota2.teamInfo.url"), "{teamId}", teamId);
		String jsonStr = this.jsoupUtils.getJsonString(url, Dota2FileType.TeamInfo, teamId);
		TeamInfoJson data = JsonFactory.fromJson(jsonStr, new TypeToken<TeamInfoJson>() {}.getType());
		return data == null ? null : data.getResult();
	}
	
	//job in here 
	@Override
	public void updateMatchScheduleForApi() throws Exception {
		List<Tourament> touramentList = dota2LeagueTournamentService.findAllTourament();
		int count = 1;
		int size = touramentList.size();
//		int start_index = 57;
		int start_index = 0;
		for(Tourament tourament : touramentList) {
			if(count >= start_index && tourament.getApiId() != null) {
				logger.info("Tourament Api id:{} ({}/{})", tourament.getApiId() , count, size);
				updateMatchsForLeagueId(tourament);
			}
			count++;
		}
	}
	
	@Override
	public void updateMatchScheduleByBatchjobTmp() throws Exception {
		String hourConfig = propertiesConfig.getProperty("batchjob.limit.hour");
		
		int hour = StringUtils.isNotBlank(hourConfig) ? Integer.parseInt(hourConfig) : -72;
		
		List<TournamentBatchjobTmp> touramentList = touramentService.findAllTmpByCreateDate(GameTypeEnum.DOTA2, hour);
		if(CollectionUtils.isEmpty(touramentList)) {
			logger.warn("GetApiScheduleLeagueList - list is empty");
			return;
		}
		int count = 1;
		int size = touramentList.size();
		int start_index = 0;
		StringBuilder errorMsg = new StringBuilder();
		for(TournamentBatchjobTmp tmp : touramentList) {
			if(count >= start_index && tmp.getApiId() != null) {
				logger.info("Schedule Tourament, leagueId:{} ({}/{})", tmp.getApiId() , count, size);
				Tourament tourament = touramentService.findByApiIdAndGameType(tmp.getApiId(), GameTypeEnum.DOTA2);
				if(tourament!=null) {
					errorMsg.append(updateMatchsForLeagueId(tourament));
				} else {
					logger.warn("Schedule Tourament, leagueId:{}, tourament is null ", tmp.getApiId() , count, size);
				}
				
			}
			count++;
		}
		if(errorMsg.length()>0) {
			throw new Exception(errorMsg.toString());
		}
	}
	
	//預留
	@Override
	public void updateMatchScheduleForApi(String leagueId) throws Exception {
		long t1 = System.nanoTime();
		Tourament tourament = touramentService.findByApiIdAndGameType(Long.valueOf(leagueId), GameTypeEnum.DOTA2);
		if(tourament != null) {
			StringBuilder errorMsg = updateMatchsForLeagueId(tourament);
			if(errorMsg!=null && errorMsg.length()>0) {
				throw new Exception(errorMsg.toString());
			}
		}
		long t2 = System.nanoTime();
		logger.info("tourament leagueId:{} 總共耗費:{} s", leagueId, ((t2-t1)/1000000000.0));
	}
	
	@Override
	public void updateMatchScheduleForMatchApi(String matchId) throws Exception {
		throw new Exception("match api 無記錄league資料, 會取不到league name");
	}
	
	private StringBuilder updateMatchsForLeagueId(Tourament tourament) throws Exception {
		StringBuilder errorMsg = new StringBuilder();
		long t1 = System.nanoTime();
		String procId = RandomUtils.getRandomCode(10);//create procId!!
		logger.info("Start!!  tourament leagueId:{}, procId:{} ", tourament.getApiId(), procId);
		try {
			List<MatchJson> matchHistoryList = getMatchHistoryForApi(tourament.getApiId().toString());
			List<Long> matchIds = new ArrayList<>(matchHistoryList.size());
			if(!CollectionUtils.isEmpty(matchHistoryList)) {
				for(MatchJson matchJson : matchHistoryList) {
					matchIds.add(matchJson.getMatchId());
				}
				errorMsg.append(updateAllMatchDetail(tourament, procId, matchIds));
			} else {
				logger.warn("tourament leagueId:{}  matchHistoryList is empty", tourament.getApiId());
			}
		} catch(Exception e) {
			MessageUtils.setApiSaveErrorMessage(errorMsg, GameTypeEnum.DOTA2, "updateMatchsForLeagueId", 0L, e);
		} finally {
//			logger.debug("deleteByProcId, tourament leagueId:{}, procId:{}", tourament.getApiId(), procId);
//			try {
//				apiIdTmpService.deleteByGameTypeAndProcId(GameTypeEnum.DOTA2, procId);
//			} catch (Exception e) {
//				logger.error("deleteByProcId error!! procId:{}", procId, e);
//			}
		}
		long t2 = System.nanoTime();
		logger.info("End!!  tourament leagueId:{}, procId:{} 總共耗費:{} s\n", tourament.getApiId(), procId, ((t2-t1)/1000000000.0));
		return errorMsg;
	}
	
	private boolean checkPlayer(MatchDetailsResultJson jsonData) {
		if(!CollectionUtils.isEmpty(jsonData.getPlayers())) {
			Set<Long> playerIds = new HashSet<>();
			List<Long> playerIds2 = new ArrayList<>();
			for(PlayerJson player:jsonData.getPlayers()) {
				if(player!=null && player.getAccountId()!=null) {
					playerIds.add(player.getAccountId());
					playerIds2.add(player.getAccountId());
				} else {
					logger.debug("matchId:" + jsonData.getMatchId() + " player slot:" + player.getPlayerSlot() + " , hero Id:" + player.getHeroId() + "  has no account Id");
				}
			}
			if(playerIds.size()!=playerIds2.size()) {
 				return false;
			}
		}
		return true;
	}
	
	//一個League底下所有的Match或單一Match
	public StringBuilder updateAllMatchDetail(Tourament tourament, String procId, List<Long> matchIdList) throws Exception {
		StringBuilder errorMsg = new StringBuilder();
		long t1 = System.currentTimeMillis();
		List<MatchDetailsResultJson> matchDetailList = new ArrayList<>();
		long minStartTime = Long.MAX_VALUE;
		long maxEndTime = 0L;
		for(Long matchId : matchIdList) {
			//get match detail
			try {
				MatchDetailsResultJson jsonData = getMatchDetailForApi(matchId.toString());
				if(jsonData!=null) {
					if(checkPlayer(jsonData)) {
						if(jsonData.getStartTime()!=null && jsonData.getStartTime() < minStartTime) {
							minStartTime = jsonData.getStartTime();
						}
						long endTime = (jsonData.getStartTime()==null ? 0L : jsonData.getStartTime().longValue()) + (jsonData.getDuration()==null ? 0L : jsonData.getDuration().intValue());
						if(endTime > maxEndTime) {
							maxEndTime = endTime;
						}
						matchDetailList.add(jsonData);
					} else {
						logger.warn("tourament leagueId:{} , matchId:{}  player has duplicate", tourament.getApiId(), jsonData.getMatchId());
					}
				}
			} catch (Exception e) {
				logger.error("matchId:{}", matchId, e);
			}
		}
		boolean updateFlag = false;
		if(updateFlag |= (minStartTime != Long.MAX_VALUE || tourament.getTouramentFromDate()==null || tourament.getTouramentFromDate().getTime()!=minStartTime)) {
			tourament.setTouramentFromDate(new Date(minStartTime * 1000));
		}
		if(updateFlag |= (maxEndTime > 0 || tourament.getTouramentToDate()==null || tourament.getTouramentToDate().getTime()!=maxEndTime)) {
			tourament.setTouramentToDate(new Date(maxEndTime * 1000));
		}
		if(updateFlag) {
			touramentService.save(tourament);
		}
		
		
		long t2 = System.currentTimeMillis();
		logger.debug("matchDetailList 共耗費:{} ms", (t2-t1));
		
		List<Dota2MatchGroupVo> matchGroupList = bindMatchGroup(matchDetailList);
		long t3 = System.currentTimeMillis();
		logger.debug("bindMatchGroup 共耗費:{} ms", (t3-t2));
		
		
		List<Long> teamIds = new ArrayList<>();
		List<Long> playerIds = new ArrayList<>();
		for(MatchDetailsResultJson matchDetailJson : matchDetailList) {
			if(matchDetailJson.getRadiantTeamId()!=null) {
				setLongToListNotDoubleSet(teamIds, matchDetailJson.getRadiantTeamId());
			} else if(matchDetailJson.getRadiantLeaderId() != null) {
				setLongToListNotDoubleSet(teamIds, bindTo64Bit(matchDetailJson.getRadiantLeaderId()));
			}
			
			if(matchDetailJson.getDireTeamId()!=null) {
				setLongToListNotDoubleSet(teamIds, matchDetailJson.getDireTeamId());
			} else if(matchDetailJson.getDireLeaderId() != null) {
				setLongToListNotDoubleSet(teamIds, bindTo64Bit(matchDetailJson.getDireLeaderId()));
			}
			
			for(PlayerJson playerJson : matchDetailJson.getPlayers())
				setLongToListNotDoubleSet(playerIds, playerJson.getAccountId());
		}
		
		long t4 = System.currentTimeMillis();
		logger.debug("setLongToListNotDoubleSet 共耗費:{} ms", (t4-t3));
		
		//Player
//		apiIdTmpService.clearApiIdTmpByDOTA2(playerIds, "player", procId);
//		Map<Long, Player> playerMap = bindListModelToMap(playerService.findByDota2(playerIds, "player", procId));
		Map<Long, Player> playerMap = bindListModelToMap(playerService.findAllByGameTypeAndApiIds(getDota2Game().getGameCode(), playerIds));
		errorMsg.append(mergePlayer(playerMap, playerIds));
		long t5 = System.currentTimeMillis();
		logger.debug("mergePlayer 共耗費:{} ms", (t5-t4));


		//Team Info
//		apiIdTmpService.clearApiIdTmpByDOTA2(teamIds, "team", procId);
//		Map<Long, Team> teamMap = bindListModelToMap(teamService.findByDota2(teamIds, "team", procId));
		Map<Long, Team> teamMap = bindListModelToMap(teamService.findAllByGameTypeAndApiIds(getDota2Game().getGameCode(), teamIds));
		
		//Team member
		Map<Long, Map<Long, TeamMember>> teamMemberMap = bindTeamMemberToMap(teamMemberService.findByTournamentAndGameTypeAndApiIds(tourament, getDota2Game().getGameCode(), teamIds));
		
		errorMsg.append(mergeTeamAndTeamMember(tourament, teamMap, teamIds, teamMemberMap, playerMap));
		long t6 = System.currentTimeMillis();
		logger.debug("mergeTeamAndTeamMember 共耗費:{} ms", (t6-t5));
		
//		apiIdTmpService.clearApiIdTmpByDOTA2(matchIdList, "match", procId);
//		Map<Long, Schedule> scheduleMap = bindListModelToMap(scheduleService.findByDota2(matchIdList, "match", procId));
		Map<Long, Schedule> scheduleMap = bindListModelToMap(scheduleService.findAllByGameTypeAndApiIds(getDota2Game().getGameCode(), matchIdList));
//		Map<Long, ScheduleGame> scheduleGameMap = bindListModelToMap(scheduleGameService.findByDota2(matchIdList, "match", procId));
		Map<Long, ScheduleGame> scheduleGameMap = bindListModelToMap(scheduleGameService.findAllByGameTypeAndApiIds(getDota2Game().getGameCode(), matchIdList));
		//一場賽事
		errorMsg.append(mergeSchedule(tourament, matchGroupList, scheduleMap, teamMap, playerMap));
		//一場比賽
		errorMsg.append(mergeScheduleGame(matchGroupList, scheduleGameMap, scheduleMap));
		
		long t8 = System.currentTimeMillis();
		logger.debug("mergeScheduleGame 共耗費:{} ms", (t8-t6));
		
		
		//團隊單場比賽紀錄
		Map<Long, Map<Long, ScheduleGameDetail>> scheduleGameDetailMap = bindScheduleGameDetailToMap(scheduleGameDetailService.findAllByGameTypeAndApiIds(getDota2Game().getGameCode(), matchIdList));
		errorMsg.append(mergeScheduleGameDetail(matchGroupList, scheduleGameDetailMap, scheduleGameMap, teamMap));
		long t9 = System.currentTimeMillis();
		logger.debug("mergeScheduleGameDetail 共耗費:{} ms", (t9-t8));
		
		//玩家單場比賽紀錄
		Map<Long, Map<Long, ScheduleGamePlayerDetail>> scheduleGamePlayerDetailMap = bindScheduleGamePlayerDetailToMap(scheduleGamePlayerDetailService.findAllByGameTypeAndApiIds(getDota2Game().getGameCode(), matchIdList));
		errorMsg.append(mergeScheduleGamePlayerDetail(matchGroupList, scheduleGamePlayerDetailMap, scheduleGameMap, playerMap, scheduleGameDetailMap));
		long t10 = System.currentTimeMillis();
		logger.debug("mergeScheduleGamePlayerDetail 共耗費:{} ms", (t10-t9));
		
		
		//使用的物品及英雄
		Map<Long, Map<Long, List<ScheduleGamePlayerItems>>> scheduleGamePlayerItemsMap = bindPlayerItemsToMap(scheduleGamePlayerItemsService.findAllByGameTypeAndApiIds(getDota2Game().getGameCode(), matchIdList));
		errorMsg.append(mergePlayerItems(matchGroupList, scheduleGamePlayerItemsMap, scheduleGameMap, playerMap));
		long t11 = System.currentTimeMillis();
		logger.debug("mergePlayerItems 共耗費:{} ms",(t11-t10));

		//計算Schedule.results 
		//因會使用到ScheduleGameDetail故要等ScheduleGameDetail跑完後再執行
		try {
			for(Entry<Long, Schedule> entry:scheduleMap.entrySet()) {
				scheduleService.doSaveScheduleResults(entry.getValue());
			}
		} catch (Exception e) {
			MessageUtils.setApiSaveErrorMessage(errorMsg, GameTypeEnum.DOTA2, "doSaveScheduleResults", 0L, e);
		}
		
		//升級表
		/*
		Map<Long, Map<Long, List<AbilityUpgrades>>> abilityUpgradesMap = bindAbilityUpgradesToMap(abilityUpgradesService.findAllByGameTypeAndApiIds(getDota2Game().getGameCode(), matchIdList));
		errorMsg.append(mergeAbilityUpgrades(matchGroupList, abilityUpgradesMap, scheduleGameMap, playerMap));
		long t12 = System.currentTimeMillis();
		logger.debug("mergeAbilityUpgrades 共耗費:{} ms", (t12-t11));
		*/
		//禁賽英雄
		Map<Long, Map<Long, List<GamePicksBans>>> gamePicksBansMap = bindGamePicksBansToMap(gamePicksBansService.findAllByGameTypeAndApiIds(getDota2Game().getGameCode(), matchIdList));
		errorMsg.append(mergeGamePicksBans(matchGroupList, gamePicksBansMap, scheduleGameMap, teamMap, dota2HeroService.findAllHeroToMap()));
		long t13 = System.currentTimeMillis();
		logger.debug("mergeGamePicksBans 共耗費:{} ms", (t13-t11));
//		logger.info("******共耗費:{} s", (t13-t1)/1000.0);
		return errorMsg;
	}
	
	private void setLongToListNotDoubleSet(Collection<Long> list, Long value) {
		if(value != null && !list.contains(value.longValue()))
			list.add(value);
	}
	
	private List<Dota2MatchGroupVo> bindMatchGroup(List<MatchDetailsResultJson> matchDetailList) {
		List<Dota2MatchGroupVo> groupList = new ArrayList<>();
		
		for(MatchDetailsResultJson json : matchDetailList) {
			try {
				if(CollectionUtils.isEmpty(json.getPlayers())) {
					continue;
				}
				Dota2MatchGroupVo groupVo = AppUtils.get(groupList, json);
				if(groupVo!=null) {
					groupVo.addDetail(json);
				} else {
					Dota2MatchGroupVo vo = new Dota2MatchGroupVo();
					vo.addDetail(json);
					groupList.add(vo);
				}
			} catch(Exception e) {
				logger.error("Dota2 match detail api id " + json.getMatchId() + " have error for bindMatchGroup!");
				throw e;
			}
		}
		
		return groupList;
	}
	
	private String mergeSchedule(Tourament tourament, List<Dota2MatchGroupVo> matchGroupList, Map<Long, Schedule> scheduleMap, Map<Long, Team> teamMap, Map<Long, Player> playerMap) {
		List<Schedule> scheduleList = new ArrayList<>(matchGroupList.size());
		for(Dota2MatchGroupVo vo : matchGroupList) {
			String matchName = getMatchName(vo, teamMap, playerMap);
			if(StringUtils.isBlank(matchName)) {
				logger.warn("matchName is null!! touramentId:{}, matchId:{}", tourament.getId(), vo.getFirstGame().getMatchId());
				continue;
			}
			
			Schedule schedule = scheduleMap.get(vo.getFirstGame().getMatchId());
			if(schedule == null) {
				schedule = bindScheduleJsonToModel(vo, matchName, tourament, teamMap, playerMap);
				scheduleMap.put(schedule.getApiId(), schedule);
				scheduleList.add(schedule);
			} else if(!schedule.equals(vo) 
					|| schedule.getTourament() ==null 
					|| !tourament.getId().equals(schedule.getTourament().getId())
					|| (vo.isTeamGame() && (schedule.getTeamAId()==null || schedule.getTeamBId()==null))) {
				scheduleList.add(bindScheduleJsonToModel(vo, matchName, tourament, teamMap, playerMap, schedule));
			}
		}
		StringBuilder sb = new StringBuilder();
		if(scheduleList.size() > 0)
			sb.append(saveSchedule(scheduleList));
		return sb.toString();
	}
	
	private String getMatchName(Dota2MatchGroupVo vo, Map<Long, Team> teamMap, Map<Long, Player> playerMap) {
		try {
			if(vo.isTeamGame()) {
				//無法預期是否有一隊沒值, 固用這種取法
				if(vo.getFirstGame() == null)
					return null;
				
				String radiantName = vo.getFirstGame().getRadiantName();
				String direName = vo.getFirstGame().getDireName();
				if(radiantName == null) {
					Long radiantLeaderId = vo.getFirstGame().getRadiantLeaderId();
					if(radiantLeaderId != null) {
						Player player = playerMap.get(radiantLeaderId);
						if(player != null)
							radiantName = player.getPlayerName();
						else
							radiantName = "Leader Id " + radiantLeaderId.toString();
					}
				}
				
				if(direName == null) {
					Long direLeaderId = vo.getFirstGame().getDireLeaderId();
					if(direLeaderId != null) {
						Player player = playerMap.get(direLeaderId);
						if(player != null)
							direName = player.getPlayerName();
						else
							direName = "Leader Id " + direLeaderId.toString();
					}
				}
				
				if(radiantName != null && direName != null)
					return radiantName + " vs " + direName;
				else if(radiantName != null)
					return radiantName;
				else if(direName != null)
					return direName;
				else
					return "";
			} else {
				//match_id 648137243 有發生只有一員選手的資料, 648172614 二名選手, 八個疑似電腦
				if(playerMap == null) {
					logger.error("getMatchName here error api id " + vo.getFirstGame().getMatchId());
				}
				
				if(vo.getPlayers().size() == 1) {
					return playerMap.get(vo.getPlayers().get(0)).getPlayerName();
				} else if(vo.getPlayers().size() > 1) {
					Long radiantLeaderId = vo.getRadiantLeaderId();
					Long direLeaderId = vo.getDireLeaderId();
					String radiantLeaderName = null;
					String direLeaderName = null;
					
					if(radiantLeaderId != null) {
						Player radiantLeader = playerMap.get(radiantLeaderId);
						radiantLeaderName = radiantLeader!=null ? radiantLeader.getPlayerName() : radiantLeaderId.toString();
					}	
						
					if(direLeaderId != null) {
						Player direLeader = playerMap.get(direLeaderId);
						direLeaderName = direLeader!=null?direLeader.getPlayerName(): direLeaderId.toString();
					}	
					if(radiantLeaderId != null && direLeaderId == null)
						return radiantLeaderName;
					else if(radiantLeaderId == null && direLeaderId != null)
						return direLeaderName;
					else
						return radiantLeaderName + " vs " + direLeaderName;
				}
			}
		} catch(Exception e) {
			logger.error("Dota2MatchGroupVo : " + vo);
			logger.error("teamMap : " + MessageUtils.joinMapToString(teamMap));
			logger.error("playerMap : " + MessageUtils.joinMapToString(playerMap));
			throw e;
		}
		return null;
	}
	
	private String mergeScheduleGame(List<Dota2MatchGroupVo> matchGroupList, Map<Long, ScheduleGame> scheduleGameMap, Map<Long, Schedule> scheduleMap) {
		List<ScheduleGame> list = new ArrayList<>();
		for(Dota2MatchGroupVo vo : matchGroupList) {
			Schedule schedule = scheduleMap.get(vo.getFirstGame().getMatchId()); 
			//error message 待補
			if(schedule == null) { 
				continue;
			}
			for(MatchDetailsResultJson json : vo.getDetails()) {
				ScheduleGame scheduleGame = scheduleGameMap.get(json.getMatchId());
				if(scheduleGame == null) {
					scheduleGame = bindScheduleGameJsonToModel(json, schedule);
					scheduleGameMap.put(json.getMatchId(), scheduleGame);
					list.add(scheduleGame);
				} else if(!scheduleGame.equals(json)) {
					list.add(bindScheduleGameJsonToModel(json, schedule, scheduleGame));
				}
			}
		}
		
		if(list.size() > 0)
			return saveScheduleGame(list);
		
		return StringUtils.EMPTY;
	}
	
	private String mergeScheduleGameDetail(List<Dota2MatchGroupVo> matchGroupList, Map<Long, Map<Long, ScheduleGameDetail>> scheduleGameDetailMap, Map<Long, ScheduleGame> scheduleGameMap, Map<Long, Team> teamMap) throws IOException{
		List<ScheduleGameDetail> list = new ArrayList<>();
		
		for(Dota2MatchGroupVo vo : matchGroupList) {
			for(MatchDetailsResultJson json : vo.getDetails()) {
				ScheduleGame scheduleGame = scheduleGameMap.get(json.getMatchId());
				//error message 待補
				if(scheduleGame == null)
					break;
				
				Map<Long, ScheduleGameDetail> detailMap = scheduleGameDetailMap.get(json.getMatchId());
				if(detailMap == null) {
					detailMap = new HashMap<Long, ScheduleGameDetail>();
					scheduleGameDetailMap.put(json.getMatchId(), detailMap);
				}
				Long radiantTeamId = getRadiantTeamId(vo, json);
				if(radiantTeamId!=null) {
					Team radiantTeam = teamMap.get(radiantTeamId);
					if(radiantTeam == null) {
						radiantTeam = teamService.findByApiIdAndGameType(radiantTeamId, getDota2Game().getGameCode());
						if(radiantTeam == null) {
							radiantTeam = bindTeamJsonToModel(radiantTeamId, json.getRadiantName());
							saveTeam(radiantTeam);
						}
						teamMap.put(radiantTeamId, radiantTeam);
					}
					
					ScheduleGameDetail radiantDetail = detailMap.get(radiantTeamId);	
					if(radiantDetail == null) {
						radiantDetail = bindScheduleGameDetailJsonToModel(json, scheduleGame, radiantTeam, Dota2TeamType.RADIANT);
						detailMap.put(radiantTeamId, radiantDetail);
						list.add(radiantDetail);
					} else if(!radiantDetail.equals(json, scheduleGame, radiantTeam, Dota2TeamType.RADIANT)) {
						list.add(bindScheduleGameDetailJsonToModel(json, radiantDetail, scheduleGame, radiantTeam, Dota2TeamType.RADIANT));
					}
				}
					
				Long direTeamId = getDireTeamId(vo, json);
				if(direTeamId!=null) {
					Team direTeam = teamMap.get(direTeamId);
					if(direTeam == null) {
						direTeam = teamService.findByApiIdAndGameType(direTeamId, getDota2Game().getGameCode());
						if(direTeam == null) {
							direTeam = bindTeamJsonToModel(direTeamId, json.getDireName());
							saveTeam(direTeam);
						}
						teamMap.put(direTeamId, direTeam);
					}
					
					ScheduleGameDetail direDetail = detailMap.get(direTeamId);
					if(direDetail == null) {
						direDetail = bindScheduleGameDetailJsonToModel(json, scheduleGame, direTeam, Dota2TeamType.DIRE);
						detailMap.put(direTeamId, direDetail);
						list.add(direDetail);
					} else if(!direDetail.equals(json, scheduleGame, direTeam, Dota2TeamType.DIRE)) {
						list.add(bindScheduleGameDetailJsonToModel(json, direDetail, scheduleGame, direTeam, Dota2TeamType.DIRE));
					}
				}
			}
		}
		
		if(list.size() > 0)
			return saveScheduleGameDetail(list);
		
		return StringUtils.EMPTY;
	}
	
	private Long getRadiantTeamId(Dota2MatchGroupVo vo, MatchDetailsResultJson json) {
		Long radiantTeamId = json.getRadiantTeamId();
		if(radiantTeamId == null && json.getRadiantLeaderId() != null){
			radiantTeamId = vo.getTeamId(json.getRadiantLeaderId());
			if(radiantTeamId==null) {
				radiantTeamId =  bindTo64Bit(json.getRadiantLeaderId());
			}
		}
		return radiantTeamId;
	}
	
	private Long getDireTeamId(Dota2MatchGroupVo vo, MatchDetailsResultJson json) {
		Long direTeamId = json.getDireTeamId();
		if(direTeamId == null && json.getDireLeaderId() != null) {
			direTeamId = vo.getTeamId(json.getDireLeaderId());
			if(direTeamId==null) {
				direTeamId = bindTo64Bit(json.getDireLeaderId());
			}
		}
		return direTeamId;
	}
	
	private String mergeScheduleGamePlayerDetail(List<Dota2MatchGroupVo> matchGroupList, Map<Long, Map<Long, ScheduleGamePlayerDetail>> playerDetailMap, Map<Long, ScheduleGame> scheduleGameMap, Map<Long, Player> playerMap, Map<Long, Map<Long, ScheduleGameDetail>> scheduleGameDetailMap) throws IOException{
		List<ScheduleGamePlayerDetail> list = new ArrayList<>();
		for(Dota2MatchGroupVo vo : matchGroupList) {
			for(MatchDetailsResultJson json : vo.getDetails()) {
				ScheduleGame scheduleGame = scheduleGameMap.get(json.getMatchId());
				Map<Long, ScheduleGameDetail> gameDetailMap = scheduleGameDetailMap.get(json.getMatchId());
				Long radiantTeamId = getRadiantTeamId(vo, json);
				Long direTeamId = getDireTeamId(vo, json);
				for(PlayerJson playerJson : json.getPlayers()) {
					Player player = playerMap.get(playerJson.getAccountId());
					if(player==null) {
						continue;
					}
					if(scheduleGame==null) {
						logger.warn("ScheduleGamePlayerDetail MatchId:" + json.getMatchId() + " scheduleGame is null");
						logger.warn("{}", vo);
						logger.warn("{}", json);
						continue;
					}
					Map<Long, ScheduleGamePlayerDetail> detailMap = playerDetailMap.get(scheduleGame.getApiId());
					if(detailMap == null) {
						detailMap = new HashMap<Long, ScheduleGamePlayerDetail>();
						playerDetailMap.put(scheduleGame.getApiId(), detailMap);
					}
					ScheduleGameDetail gameDetail = null;
					if(playerJson.isRadiantTeam() && radiantTeamId != null) {
						gameDetail = gameDetailMap.get(radiantTeamId);
					} else if(!playerJson.isRadiantTeam() && direTeamId != null) {
						gameDetail = gameDetailMap.get(direTeamId);
					}
					
					ScheduleGamePlayerDetail playerDetail = detailMap.get(playerJson.getAccountId());
					if(playerDetail == null) {
						playerDetail = bindPlayerDetailJsonToModel(playerJson, scheduleGame, gameDetail, player);
						detailMap.put(playerJson.getAccountId(), playerDetail);
						list.add(playerDetail);
					} else if(!playerDetail.equals(playerJson, scheduleGame, gameDetail, player)) {
						list.add(bindPlayerDetailJsonToModel(playerJson, playerDetail, scheduleGame, gameDetail, player));
					}
				}
			}
		}
		
		if(list.size() > 0)
			return savePlayerDetail(list);
		
		return StringUtils.EMPTY;
	}
	
	private String mergePlayerItems(List<Dota2MatchGroupVo> matchGroupList, Map<Long, Map<Long, List<ScheduleGamePlayerItems>>> itemMap, Map<Long, ScheduleGame> scheduleGameMap, Map<Long, Player> playerMap) throws IOException{
		List<ScheduleGamePlayerItems> list = new ArrayList<>();
		for(Dota2MatchGroupVo vo : matchGroupList) {
			for(MatchDetailsResultJson json : vo.getDetails()) {
				ScheduleGame scheduleGame = scheduleGameMap.get(json.getMatchId());
				if(scheduleGame==null) {
					continue;
				}
				for(PlayerJson playerJson : json.getPlayers()) {
					if(playerJson.getAccountId() == null) {
						continue;
					}
					
					Map<Long, List<ScheduleGamePlayerItems>> playerItemMap = itemMap.get(scheduleGame.getApiId());
					if(playerItemMap==null) {
						playerItemMap = new HashMap<>();
						itemMap.put(scheduleGame.getApiId(), playerItemMap);
					}
					List<ScheduleGamePlayerItems> playerItemlList = playerItemMap.get(playerJson.getAccountId());
					if(playerItemlList == null) {
						playerItemlList = new ArrayList<>();
						playerItemMap.put(playerJson.getAccountId(), playerItemlList);
					}
					
					Player player = playerMap.get(playerJson.getAccountId());
					for(Dota2Item item : playerJson.getItemList()) {
						if(item.getItemId()==null || item.getItemId().longValue() == 0) {
							continue;
						}
						ScheduleGamePlayerItems playerDetail = AppUtils.get(playerItemlList, item);
						if(playerDetail == null) {
							playerDetail = bindPlayerItemJsonToModel(item, scheduleGame, player);
							playerItemlList.add(playerDetail);
							list.add(playerDetail);
						} else if(!playerDetail.equals(item, scheduleGame.getId(), player.getId())) {
							list.add(bindPlayerItemJsonToModel(item, playerDetail, scheduleGame, player));
						}
					}
				}
			}
		}
		if(list.size() > 0)
			return savePlayerItem(list);
		
		return StringUtils.EMPTY;
	}
	
	private String mergeAbilityUpgrades(List<Dota2MatchGroupVo> matchGroupList, Map<Long, Map<Long, List<AbilityUpgrades>>> abilityUpgradesMap, Map<Long, ScheduleGame> scheduleGameMap, Map<Long, Player> playerMap) throws IOException{
		List<AbilityUpgrades> list = new ArrayList<>();
		for(Dota2MatchGroupVo vo : matchGroupList) {
			for(MatchDetailsResultJson json : vo.getDetails()) {
				ScheduleGame scheduleGame = scheduleGameMap.get(json.getMatchId());
				if(scheduleGame==null) {
					continue;
				}
				for(PlayerJson playerJson : json.getPlayers()) {
					if(CollectionUtils.isEmpty(playerJson.getAbilityUpgrades())) {
						continue;
					}
					Map<Long, List<AbilityUpgrades>> upgradesMap = abilityUpgradesMap.get(scheduleGame.getApiId());
					
					if(upgradesMap == null) {
						upgradesMap = new HashMap<>();
						abilityUpgradesMap.put(scheduleGame.getApiId(), upgradesMap);
						
					}
					
					List<AbilityUpgrades> abilityUpgradesList = upgradesMap.get(playerJson.getAccountId());
					if(abilityUpgradesList == null) {
						abilityUpgradesList = new ArrayList<>();
						upgradesMap.put(playerJson.getAccountId(), abilityUpgradesList);
					}
					
					Player player = playerMap.get(playerJson.getAccountId());
					for(AbilityUpgradesJson au : playerJson.getAbilityUpgrades()) {
						AbilityUpgrades abilityUpgrades = AppUtils.get(abilityUpgradesList, au);
						if(abilityUpgrades == null) {
							abilityUpgrades = bindAbilityUpgradesJsonToModel(au, scheduleGame, player);
							abilityUpgradesList.add(abilityUpgrades);
							list.add(abilityUpgrades);
						} else if(!abilityUpgrades.equals(au, scheduleGame, player)) {
							list.add(bindAbilityUpgradesJsonToModel(au, abilityUpgrades, scheduleGame, player));
						}
					}
				}
			}
		}
		
		if(list.size() > 0)
			return saveAbilityUpgrades(list);
		
		return StringUtils.EMPTY;
	}
	
	private String mergeGamePicksBans(List<Dota2MatchGroupVo> matchGroupList, Map<Long, Map<Long, List<GamePicksBans>>> gamePicksBansMap, Map<Long, ScheduleGame> scheduleGameMap, Map<Long, Team> teamMap, Map<Long, Hero> heroMap) {
		List<GamePicksBans> list = new ArrayList<>();
		
		for(Dota2MatchGroupVo vo : matchGroupList) {
			for(MatchDetailsResultJson json : vo.getDetails()) {
				if(CollectionUtils.isEmpty(json.getPicksBans()))
					continue;
				
				ScheduleGame scheduleGame = scheduleGameMap.get(json.getMatchId());
				if(scheduleGame == null) {
					logger.warn("GamePicksBans MatchId:" + json.getMatchId() + " scheduleGame is null");
					logger.warn("{}", vo);
					logger.warn("{}", json);
					continue;
				}
				for(PicksBansJson ban : json.getPicksBans()) {
					Team team = null;
					if("0".equals(ban.getTeam())) {
						if(json.getRadiantTeamId()!=null) {
							team = teamMap.get(json.getRadiantTeamId());
						} else if(json.getRadiantLeaderId()!=null) {
							team = teamMap.get(bindTo64Bit(json.getRadiantLeaderId()));
						}
					} else {
						if(json.getDireTeamId()!=null) {
							team = teamMap.get(json.getDireTeamId());
						} else if(json.getDireLeaderId()!=null) {
							team = teamMap.get(bindTo64Bit(json.getDireLeaderId()));
						}
					}
					if(team==null || scheduleGame==null) {
//						teamDao.findByApiId(apiId)
						continue;
					}
					
					Map<Long, List<GamePicksBans>> picksBansMap = gamePicksBansMap.get(scheduleGame.getApiId());
					
					if(picksBansMap==null) {
						picksBansMap = new HashMap<>();
						gamePicksBansMap.put(scheduleGame.getApiId(), picksBansMap);
					}
					
					
					List<GamePicksBans> banList = picksBansMap.get(team.getId());
					if(banList==null) {
						banList = new ArrayList<>();
						picksBansMap.put(team.getId(), banList);
					}
					
					GamePicksBans pickBan = AppUtils.get(banList, ban);
					Hero hero = heroMap.get(ban.getHeroId());
					if(pickBan == null) {
						pickBan = bindBansToModel(ban, scheduleGame, team, hero);
						banList.add(pickBan);
						list.add(pickBan);
					} else if(!pickBan.equals(ban, hero)) {
						list.add(bindBansToModel(ban, pickBan, scheduleGame, team, hero));
					}
				}
			}
		}
		
		if(list.size() > 0)
			return saveGamePicksBans(list);
		
		return StringUtils.EMPTY;
	}
	
	private String mergeTeamAndTeamMember(Tourament tourament, Map<Long, Team> teamMap, List<Long> teamIds, Map<Long, Map<Long, TeamMember>> teamMemberMap, Map<Long, Player> playerMap) throws Exception {
		List<Team> teams = new ArrayList<>(teamIds.size());
		List<Dota2TeamJsonVo> teamJsonList = new ArrayList<>();
		StringBuilder errorMsg = new StringBuilder();
		for(Long id : teamIds) {
			TeamInfoResultJson resultJson = getTeamForApi(id.toString());
			if(resultJson == null || CollectionUtils.isEmpty(resultJson.getTeams())) {
				continue;
			}
			
			List<Dota2TeamJsonVo> resultList = resultJson.bindToVoList();
			Dota2TeamJsonVo vo = CollectionUtils.isEmpty(resultList) ? null : resultList.get(0);
			if(vo!=null && !vo.getTeamId().equals(id))
				continue;
			Team team = teamMap.get(id);
			if(vo == null && team == null) {
				team = bindNullTeamJsonToModel(id);
				teams.add(team);
				teamMap.put(id, team);
			} else if(team == null) {
				team = bindTeamJsonToModel(vo);
				teams.add(team);
				teamMap.put(id, team);
			} else if(vo!=null && !team.equals(vo)) {
				teams.add(bindTeamJsonToModel(vo, team));
			}
			if(vo != null) {
				teamJsonList.add(vo);
			}
		}
		
		if(teams.size() > 0) {
			errorMsg.append(saveTeam(teams));
		}
		
		List<TeamMember> list = new ArrayList<>();
		for(Dota2TeamJsonVo vo : teamJsonList) {
			Team team = teamMap.get(vo.getTeamId());
			if(team == null || vo.getPlayerIds() == null)
				continue;
			for(Long playerId : vo.getPlayerIds()) {
				Player player = playerMap.get(playerId);
				if(player == null) {
					player = playerService.findByApiIdAndGameType(playerId, getDota2Game().getGameCode());
					if(player == null) {
						player = bindNullPlayerToModel(playerId);
						savePlayer(player);
					}
					playerMap.put(playerId, player);
				}
				
				Map<Long, TeamMember> memberMap = teamMemberMap.get(vo.getTeamId());
				TeamMember member = null;
				if(memberMap != null)
					member = memberMap.get(player.getApiId());
				
				if(member == null) {
					list.add(bindTeamMemberToModel(tourament, team, player));
				} else if(!member.equals(player)) {
					list.add(bindTeamMemberToModel(tourament, member, team, player));
				}
			}
		}
		
		if(list.size() > 0) {
			errorMsg.append(saveTeamMember(list));
		}
		return errorMsg.toString();
	}
	
	private String mergePlayer(Map<Long, Player> playerMap, List<Long> playerIds) throws Exception {
		Set<Player> players = new HashSet<>();
		for(int i = 0; playerIds.size() > i; i+=100) {
			List<Long> tmpPlayerIds = playerIds.subList(i, i + 100 <= playerIds.size() ? i + 100 : playerIds.size());
			PlayerSummariesResultJson resultJson = getPlayerForApi(tmpPlayerIds);
			if(resultJson==null || CollectionUtils.isEmpty(resultJson.getPlayers())) {
				continue;
			}
			List<Long> resultIds = new ArrayList<>();
			for(com.hkesports.matchticker.model.json.dota2.playerSummaries.PlayerJson playerJson : resultJson.getPlayers()) {
				if(playerJson.getSteamid()==null) {
					logger.warn("PlayerJson Steamid is null, tmpPlayerIds:" + tmpPlayerIds);
					continue;
				}
				long steam32 = bindTo32Bit(playerJson.getSteamid());
				Player player = playerMap.get(steam32);
				resultIds.add(steam32);
				if(player == null) {
					player = bindPlayerJsonToModel(playerJson);
					playerMap.put(player.getApiId(), player);
					players.add(player);
				} else if(!player.equals(playerJson)) {
					players.add(bindPlayerJsonToModel(playerJson, player));
				}
			}
			
			for(Long id : tmpPlayerIds) {
				if(!resultIds.contains(id) && playerMap.get(id) == null) {
					Player player = bindNullPlayerToModel(id);
					playerMap.put(player.getApiId(), player);
					players.add(player);
				}
			}
		}
		
		if(players.size() > 0)
			return savePlayer(players);
		
		return StringUtils.EMPTY;
	}

	private <T extends BasicApiInfo> Map<Long, T> bindListModelToMap(List<T> list) {
		Map<Long, T> map = new HashMap<Long, T>(list.size());
		for(T model : list) {
			map.put(model.getApiId(), model);
		}
		return map;
	}
	
	private Map<Long, Map<Long, TeamMember>> bindTeamMemberToMap(List<TeamMember> list) {
		Map<Long, Map<Long, TeamMember>> map = new HashMap<>();
		for(TeamMember teamMember : list) {
			Map<Long, TeamMember> memberMap = map.get(teamMember.getTeam().getApiId());
			if(memberMap == null) {
				memberMap = new HashMap<Long, TeamMember>();
				map.put(teamMember.getTeam().getApiId(), memberMap);
			}
			if(StringUtils.isBlank(teamMember.getMemberName()))
				continue;
			if(teamMember.getPlayer()!=null)
				memberMap.put(teamMember.getPlayer().getApiId(), teamMember);
		}
		return map;
	}
	
	private Map<Long, Map<Long, ScheduleGameDetail>> bindScheduleGameDetailToMap(List<ScheduleGameDetail> list) {
		Map<Long, Map<Long, ScheduleGameDetail>> map = new HashMap<>();
		for(ScheduleGameDetail detail : list) {
			Map<Long, ScheduleGameDetail> detailMap = map.get(detail.getScheduleGame().getApiId());
			if(detailMap == null) {
				detailMap = new HashMap<Long, ScheduleGameDetail>();
				map.put(detail.getScheduleGame().getApiId(), detailMap);
			}
			if(detail.getTeam().getApiId()==null)
				continue;
			detailMap.put(detail.getTeam().getApiId(), detail);
		}
		return map;
	}
	
	private Map<Long, Map<Long, ScheduleGamePlayerDetail>> bindScheduleGamePlayerDetailToMap(List<ScheduleGamePlayerDetail> list) {
		Map<Long, Map<Long, ScheduleGamePlayerDetail>> map = new HashMap<>();
		for(ScheduleGamePlayerDetail detail : list) {
			Map<Long, ScheduleGamePlayerDetail> detailMap = map.get(detail.getScheduleGame().getApiId());
			if(detailMap == null) {
				detailMap = new HashMap<Long, ScheduleGamePlayerDetail>();
				map.put(detail.getScheduleGame().getApiId(), detailMap);
			}
			if(detail.getPlayer().getApiId()==null)
				continue;
			detailMap.put(detail.getPlayer().getApiId(), detail);
		}
		return map;
	}
	
	private Map<Long, Map<Long, List<ScheduleGamePlayerItems>>> bindPlayerItemsToMap(List<ScheduleGamePlayerItems> list) {
		Map<Long, Map<Long, List<ScheduleGamePlayerItems>>> map = new HashMap<>();
		
		for(ScheduleGamePlayerItems detail : list) {
			Map<Long, List<ScheduleGamePlayerItems>> itemsMap = map.get(detail.getScheduleGame().getApiId());
			if(itemsMap == null) {
				itemsMap = new HashMap<Long, List<ScheduleGamePlayerItems>>();
				map.put(detail.getScheduleGame().getApiId(), itemsMap);
			}
			
			List<ScheduleGamePlayerItems> itemsList = itemsMap.get(detail.getPlayer().getApiId());
			if(itemsList == null) {
				itemsList = new ArrayList<ScheduleGamePlayerItems>();
				itemsMap.put(detail.getPlayer().getApiId(), itemsList);
			}
			itemsList.add(detail);
		}
		return map;
	}
	
	private Map<Long, Map<Long, List<AbilityUpgrades>>> bindAbilityUpgradesToMap(List<AbilityUpgrades> list) {
		Map<Long, Map<Long, List<AbilityUpgrades>>> map = new HashMap<>();
		for(AbilityUpgrades au : list) {
			Map<Long, List<AbilityUpgrades>> upgradesMap = map.get(au.getScheduleGame().getApiId());
			if(upgradesMap == null) {
				upgradesMap = new HashMap<Long, List<AbilityUpgrades>>();
				map.put(au.getScheduleGame().getApiId(), upgradesMap);
			}
			
			if(au.getPlayer().getApiId()==null)
				continue;
			List<AbilityUpgrades> upgradesList = upgradesMap.get(au.getPlayer().getApiId());
			if(upgradesList == null) {
				upgradesList = new ArrayList<AbilityUpgrades>();
				upgradesMap.put(au.getPlayer().getApiId(), upgradesList);
			}
			upgradesList.add(au);
		}
		return map;
	}
	
	private Map<Long, Map<Long, List<GamePicksBans>>> bindGamePicksBansToMap(List<GamePicksBans> list) {
		Map<Long, Map<Long, List<GamePicksBans>>> map = new HashMap<>();
		for(GamePicksBans ban : list) {
			Map<Long, List<GamePicksBans>> bansMap = map.get(ban.getScheduleGame().getApiId());
			if(bansMap == null) {
				bansMap = new HashMap<Long, List<GamePicksBans>>();
				map.put(ban.getScheduleGame().getApiId(), bansMap);
			}
			
			if(ban.getTeamId()==null)
				continue;
			List<GamePicksBans> bansList = bansMap.get(ban.getTeamId());
			if(bansList == null) {
				bansList = new ArrayList<GamePicksBans>();
				bansMap.put(ban.getTeamId(), bansList);
			}
			bansList.add(ban);
		}
		return map;
	}
	
	private Player bindPlayerJsonToModel(com.hkesports.matchticker.model.json.dota2.playerSummaries.PlayerJson json) {
		return bindPlayerJsonToModel(json, new Player());
	}
	
	private Player bindPlayerJsonToModel(com.hkesports.matchticker.model.json.dota2.playerSummaries.PlayerJson json, Player player) {
		player.setGameType(GameTypeEnum.DOTA2);
		player.setApiId(bindTo32Bit(json.getSteamid()));
		player.setPlayerName(json.getPersonaName());
		
		if(StringUtils.isBlank(json.getRealName())) {
			player.setPlayerFullName(json.getPersonaName());
		} else {
			player.setPlayerFullName(json.getRealName());
		}
		
		player.setCountry(json.getLocCountryCode());
		player.setPlayerUrl(json.getProfileUrl());
		player.setPhotoUrl(json.getAvatarFull());
		
		return player;
	}
	
	private Player bindNullPlayerToModel(Long id) {
		Player player =new Player();
		player.setGameType(GameTypeEnum.DOTA2);
		player.setApiId(id);
		player.setPlayerName("Dota2_" + id);
		player.setPlayerFullName("Dota2_" + id);
		
		return player;
	}
	
	private Team bindTeamJsonToModel(Dota2TeamJsonVo json) {
		return bindTeamJsonToModel(json, new Team());
	}
	
	private Team bindTeamJsonToModel(Dota2TeamJsonVo json, Team team) {
		team.setGameType(GameTypeEnum.DOTA2);
		team.setApiId(json.getTeamId());
		team.setTeamName(json.getName());
		team.setTeamFullName(json.getName());
		team.setCountry(json.getCountryCode());
		team.setTeamUrl(json.getUrl());
		team.setRating(json.getRating());
		team.setLogoUrl(json.getLogo()!=null?json.getLogo().toString():null);
		return team;
	}
	
	private Team bindTeamJsonToModel(Long id, String teamName) {
		if(teamName == null)
			teamName = "Dota2_" + id;
		
		Team team = new Team();
		team.setGameType(GameTypeEnum.DOTA2);
		team.setApiId(id);
		team.setTeamName(teamName);
		team.setTeamFullName(teamName);
		
		return team;
	}
	
	private Team bindNullTeamJsonToModel(Long id) {
		Team team = new Team();
		team.setGameType(GameTypeEnum.DOTA2);
		team.setApiId(id);
		team.setTeamName("Dota2_" + id);
		team.setTeamFullName("Dota2_" + id);
		
		return team;
	}

	private TeamMember bindTeamMemberToModel(Tourament tourament, Team team, Player player) {
		return bindTeamMemberToModel(tourament, new TeamMember(), team, player);
	}
	
	private TeamMember bindTeamMemberToModel(Tourament tourament, TeamMember member, Team team, Player player) {
		member.setTeam(team);
		member.setPlayer(player);
		member.setMemberName(player.getPlayerName());
		member.setMemberFullName(player.getPlayerFullName());
		member.setMemberCountry(player.getCountry());
		member.setTeamURL(team.getTeamUrl());
		member.setTourament(tourament);
		return member;
	}
	
	private Schedule bindScheduleJsonToModel(Dota2MatchGroupVo vo, String gameName, Tourament tourament, Map<Long, Team> teamMap, Map<Long, Player> playerMap) {
		Schedule schedule = new Schedule();
		schedule.setaSideSupportCount(0L);
		schedule.setbSideSupportCount(0L);
		schedule.setStatus(ScheduleStatus.Ready);
		return bindScheduleJsonToModel(vo, gameName, tourament, teamMap, playerMap, schedule);
	}
	
	private Schedule bindScheduleJsonToModel(Dota2MatchGroupVo vo, String gameName, Tourament tourament, Map<Long, Team> teamMap, Map<Long, Player> playerMap, Schedule schedule) {
		MatchDetailsResultJson json = vo.getFirstGame();
		schedule.setGameType(GameTypeEnum.DOTA2);
		schedule.setApiId(json.getMatchId());
		schedule.setStartTime(vo.getStartDate());
		schedule.setEndTime(vo.getEndDate());
//		schedule.setDateTime(new Date(json.getStartTime() * 1000));
		schedule.setDateTime(vo.getStartDate());
		
		schedule.setGame(getDota2Game());
		if(tourament != null) {
			schedule.setTouramentName(tourament.getTouramentName());
			schedule.setTourament(tourament);
		}
		
		schedule.setWinnerId(vo.getWinId());
		schedule.setMaxGames(Integer.valueOf(vo.getDetails().size()).shortValue());
		if(vo.isTeamGame()) {
			Long radiantTeamId = getRadiantTeamId(vo, json);
			Long direTeamId = getDireTeamId(vo, json);
			if(radiantTeamId!=null && direTeamId!=null) {
				Team radiantTeam = teamMap.get(radiantTeamId);
				Team direTeam = teamMap.get(direTeamId);
				/*
				 * 兩team api id 相比 大的放 team A 小的放team B
				 */
				if(radiantTeamId > direTeamId) {
					if(radiantTeam!=null) {
						schedule.setTeamAId(radiantTeam.getId());
						schedule.setTeamAName(radiantTeam.getTeamFullName());
					} else {
						schedule.setTeamAName(json.getRadiantName());
					}
					if(direTeam!=null) {
						schedule.setTeamBId(direTeam.getId());
						schedule.setTeamBName(direTeam.getTeamFullName());
					} else {
						schedule.setTeamBName(json.getDireName());
					}
				} else {
					if(direTeam!=null) {
						schedule.setTeamAId(direTeam.getId());
						schedule.setTeamAName(direTeam.getTeamFullName());
					} else {
						schedule.setTeamAName(json.getDireName());
					}
					if(radiantTeam!=null) {
						schedule.setTeamBId(radiantTeam.getId());
						schedule.setTeamBName(radiantTeam.getTeamFullName());
					} else {
						schedule.setTeamBName(json.getRadiantName());
					}
				}
			} else {
				schedule.setTeamAName(json.getRadiantName());
				schedule.setTeamBName(json.getDireName());
				logger.warn("tourament leagueId:{}, matchId:{},  team data is incomplete!! , radiantTeamId:{}, direTeamId:{} ", tourament.getApiId(), json.getMatchId(), radiantTeamId, direTeamId);
			}
		} else {
			if(vo.getRadiantLeaderId() != null)
				schedule.setPlayerAName(playerMap.get(vo.getRadiantLeaderId()).getPlayerName());
			if(vo.getDireLeaderId() != null)
				schedule.setPlayerBName(playerMap.get(vo.getDireLeaderId()).getPlayerName());
		}
		return schedule;
	}
	
	private ScheduleGame bindScheduleGameJsonToModel(MatchDetailsResultJson json, Schedule schedule) {
		return bindScheduleGameJsonToModel(json, schedule, new ScheduleGame());
	}
	
	private ScheduleGame bindScheduleGameJsonToModel(MatchDetailsResultJson json, Schedule schedule, ScheduleGame scheduleGame) {
		scheduleGame.setGameType(GameTypeEnum.DOTA2);
		scheduleGame.setApiId(json.getMatchId());
		scheduleGame.setDateTime(new Date(json.getStartTime() * 1000));
		scheduleGame.setGameLength(json.getDuration());
		scheduleGame.setSchedule(schedule);
		scheduleGame.setLobbyType(json.getLobbyType());
		scheduleGame.setHumanPlayers(json.getHumanPlayers());
		scheduleGame.setPositiveVotes(json.getPositiveVotes());
		scheduleGame.setNegativeVote(json.getNegativeVotes());
		scheduleGame.setGameMode(json.getGameMode());
		scheduleGame.setFirstBloodTime(json.getFirstBloodTime());
		
		return scheduleGame;
	}
	
	private ScheduleGameDetail bindScheduleGameDetailJsonToModel(MatchDetailsResultJson json, ScheduleGame scheduleGame, Team team, Dota2TeamType teamType) {
		ScheduleGameDetail detail = new ScheduleGameDetail();
		detail.setGuessCount(0L);
		return bindScheduleGameDetailJsonToModel(json, detail, scheduleGame, team, teamType);
	}
	
	private ScheduleGameDetail bindScheduleGameDetailJsonToModel(MatchDetailsResultJson json, ScheduleGameDetail scheduleGameDetail, ScheduleGame scheduleGame, Team team, Dota2TeamType teamType) {
		try {
			scheduleGameDetail.setScheduleGame(scheduleGame);
			scheduleGameDetail.setTeam(team);
			if(Dota2TeamType.RADIANT == teamType) {
				if(json.getRadiantWin())
					scheduleGameDetail.setWin(GameWinTypeEnum.Win);
				else
					scheduleGameDetail.setWin(GameWinTypeEnum.Fail);
				scheduleGameDetail.setTowerStatus(json.getTowerStatusRadiant());
				scheduleGameDetail.setBarracksStatus(json.getBarracksStatusRadiant());
			} else if(Dota2TeamType.DIRE == teamType) {
				if(json.getRadiantWin())
					scheduleGameDetail.setWin(GameWinTypeEnum.Fail);
				else
					scheduleGameDetail.setWin(GameWinTypeEnum.Win);
				scheduleGameDetail.setTowerStatus(json.getTowerStatusDire());
				scheduleGameDetail.setBarracksStatus(json.getBarracksStatusDire());
			}
			scheduleGameDetail.setPosition(teamType.name());
		} catch(Exception e) {
			logger.error("json :" + json);
			logger.error("scheduleGameDetail : " + scheduleGameDetail);
			logger.error("scheduleGame : " + scheduleGame);
			logger.error("team : " + team);
			logger.error("teamType : " + teamType);
			throw e;
		}
		
		return scheduleGameDetail;
	}
	
	private ScheduleGamePlayerDetail bindPlayerDetailJsonToModel(PlayerJson json, ScheduleGame scheduleGame, ScheduleGameDetail gameDetail, Player player) {
		return bindPlayerDetailJsonToModel(json, new ScheduleGamePlayerDetail(), scheduleGame, gameDetail, player);
	}
	
	private ScheduleGamePlayerDetail bindPlayerDetailJsonToModel(PlayerJson json, ScheduleGamePlayerDetail playerDetail, ScheduleGame scheduleGame, ScheduleGameDetail gameDetail, Player player) {
		playerDetail.setScheduleGame(scheduleGame);
		playerDetail.setScheduleGameDetail(gameDetail);
		playerDetail.setPlayer(player);
		
		playerDetail.setAssists(json.getAssists());
		playerDetail.setDeaths(json.getDeaths());
		playerDetail.setKills(json.getKills());
		
		playerDetail.setEndLevel(json.getLevel());
		playerDetail.setMinionsKilled(json.getDenies());;
		playerDetail.setTotalGold(json.getGold());
		playerDetail.setGoldSpent(json.getGoldSpent());
		playerDetail.setPlayerSlot(json.getPlayerSlot());
		playerDetail.setGoldPerMin(json.getGoldPerMin());
		playerDetail.setXpPerMin(json.getXpPerMin());
		playerDetail.setHeroDamage(json.getHeroDamage());
		playerDetail.setTowerDamage(json.getTowerDamage());
		playerDetail.setHeroHealing(json.getHeroHealing());
		
		return playerDetail;
	}
	
	private ScheduleGamePlayerItems bindPlayerItemJsonToModel(Dota2Item json, ScheduleGame scheduleGame, Player player) {
		return bindPlayerItemJsonToModel(json, new ScheduleGamePlayerItems(), scheduleGame, player);
	}
	
	private ScheduleGamePlayerItems bindPlayerItemJsonToModel(Dota2Item json, ScheduleGamePlayerItems item, ScheduleGame scheduleGame, Player player) {
		item.setScheduleGame(scheduleGame);
		item.setPlayer(player);
		item.setItemType(json.getItemType());
		item.setItemId(json.getItemId());
		item.setSequence(json.getSequence());
		
		return item;
	}
	
	private AbilityUpgrades bindAbilityUpgradesJsonToModel(AbilityUpgradesJson json, ScheduleGame scheduleGame, Player player) {
		return bindAbilityUpgradesJsonToModel(json, new AbilityUpgrades(), scheduleGame, player);
	}
	
	private AbilityUpgrades bindAbilityUpgradesJsonToModel(AbilityUpgradesJson json, AbilityUpgrades abilityUpgrades, ScheduleGame scheduleGame, Player player) {
		abilityUpgrades.setScheduleGame(scheduleGame);
		abilityUpgrades.setPlayer(player);
		abilityUpgrades.setLevel(json.getLevel());
		abilityUpgrades.setTime(json.getTime());
		abilityUpgrades.setApiId(json.getAbility());
		
		return abilityUpgrades;
	}
	
	private GamePicksBans bindBansToModel(PicksBansJson json, ScheduleGame scheduleGame, Team team, Hero hero) {
		return bindBansToModel(json, new GamePicksBans(), scheduleGame, team, hero);
	}
	
	private GamePicksBans bindBansToModel(PicksBansJson json, GamePicksBans ban, ScheduleGame scheduleGame, Team team, Hero hero) {
		ban.setGameType(GameTypeEnum.DOTA2);
		ban.setScheduleGame(scheduleGame);
		if(team != null)
			ban.setTeamId(team.getId());
		ban.setHero(hero);
		ban.setIsPick(json.getIsPick());
		ban.setOrder(json.getOrder());
		return ban;
	}
	
	private String savePlayer(Set<Player> list) {
		try {
			playerService.batchSave(list);
		} catch (Exception e) {
			StringBuilder message = new StringBuilder();
			for(Player player : list) {
				message.append(savePlayer(player));
			}
			return message.toString();
		}
		return StringUtils.EMPTY;
	}
	
	private String savePlayer(Player player) {
		StringBuilder message = new StringBuilder();
		try {
			playerService.save(player);
		} catch(Exception e) {
			MessageUtils.setApiSaveErrorMessage(message, GameTypeEnum.DOTA2, "player", player.getApiId(), e);
			logger.error(MessageUtils.getApiSaveErrorMessage(GameTypeEnum.DOTA2, "player", player.getApiId(), e), e);
		}
		return message.toString();
	}
	
	private String saveTeam(List<Team> list) {
		try {
			teamService.batchSave(list);
		} catch (Exception e) {
			StringBuilder message = new StringBuilder();
			for(Team team : list) {
				message.append(saveTeam(team));
			}
			return message.toString();
		}
		return StringUtils.EMPTY;
	}
	
	private String saveTeam(Team team) {
		StringBuilder message = new StringBuilder();
		try {
			teamService.save(team);
		} catch(Exception e) {
			MessageUtils.setApiSaveErrorMessage(message, GameTypeEnum.DOTA2, "team", team.getApiId(), e);
			logger.error(MessageUtils.getApiSaveErrorMessage(GameTypeEnum.DOTA2, "team", team.getApiId(), e), e);
		}
		return message.toString();
	}
	
	private String saveTeamMember(List<TeamMember> list) {
		try {
			teamMemberService.batchSave(list);
		} catch (Exception e) {
			StringBuilder message = new StringBuilder();
			for(TeamMember teamMember : list) {
				message.append(saveTeamMember(teamMember));
			}
			return message.toString();
		}
		return StringUtils.EMPTY;
	}
	
	private String saveTeamMember(TeamMember teamMember) {
		StringBuilder message = new StringBuilder();
		try {
			teamMemberService.save(teamMember);
		} catch(Exception e) {
			MessageUtils.setApiSaveErrorMessage(message, GameTypeEnum.DOTA2, "TeamMember", teamMember.getTeam().getApiId(), e);
			logger.error(MessageUtils.getApiSaveErrorMessage(GameTypeEnum.DOTA2, "TeamMember", teamMember.getTeam().getApiId(), e), e);
		}
		return message.toString();
	}
	
	/*private String saveGame(List<Game> list) {
		try {
			gameService.batchSave(list);
		} catch (Exception e) {
			StringBuilder message = new StringBuilder();
			for(Game game : list) {
				message.append(saveGame(game));
			}
			return message.toString();
		}
		return StringUtils.EMPTY;
	}
	
	private String saveGame(Game game) {
		StringBuilder message = new StringBuilder();
		try {
			gameService.save(game);
		} catch(Exception e) {
			MessageUtils.setApiSaveErrorMessage(message, GameTypeEnum.DOTA2, "game", game.getApiId(), e);
			logger.error(MessageUtils.getApiSaveErrorMessage(GameTypeEnum.DOTA2, "game", game.getApiId(), e), e);
		}
		return message.toString();
	}*/
	
	private String saveSchedule(List<Schedule> list) {
		StringBuilder message = new StringBuilder();
		try {
			scheduleService.batchSave(list);
		} catch (Exception e) {
			for(Schedule schedule : list) {
				message.append(saveSchedule(schedule));
			}
		}
		return message.toString();
	}
	
	private String saveSchedule(Schedule schedule) {
		StringBuilder message = new StringBuilder();
		try {
			scheduleService.save(schedule);
		} catch(Exception e) {
			MessageUtils.setApiSaveErrorMessage(message, GameTypeEnum.DOTA2, "schedule", schedule.getApiId(), e);
			logger.error(MessageUtils.getApiSaveErrorMessage(GameTypeEnum.DOTA2, "schedule", schedule.getApiId(), e), e);
		}
		return message.toString();
	}
	
	private String saveScheduleGame(List<ScheduleGame> list) {
		StringBuilder message = new StringBuilder();
		try {
			scheduleGameService.batchSave(list);
		} catch (Exception e) {
			for(ScheduleGame scheduleGame : list) {
				message.append(saveScheduleGame(scheduleGame));
			}
		}
		return message.toString();
	}
	
	private String saveScheduleGame(ScheduleGame scheduleGame) {
		StringBuilder message = new StringBuilder();
		try {
			scheduleGameService.save(scheduleGame);
		} catch(Exception e) {
			MessageUtils.setApiSaveErrorMessage(message, GameTypeEnum.DOTA2, "scheduleGame", scheduleGame.getApiId(), e);
			logger.error(MessageUtils.getApiSaveErrorMessage(GameTypeEnum.DOTA2, "scheduleGame", scheduleGame.getApiId(), e), e);
		}
		return message.toString();
	}
	
	private String saveScheduleGameDetail(List<ScheduleGameDetail> list) {
		StringBuilder message = new StringBuilder();
		try {
			scheduleGameDetailService.batchSave(list);
		} catch(Exception e) {
			for(ScheduleGameDetail detail : list) {
				message.append(saveScheduleGameDetail(detail));
			}
		}
		return message.toString();
	}
	
	private String saveScheduleGameDetail(ScheduleGameDetail detail) {
		StringBuilder message = new StringBuilder();
		try {
			scheduleGameDetailService.save(detail);
		} catch(Exception e) {
			MessageUtils.setApiSaveErrorMessage(message, GameTypeEnum.DOTA2, "scheduleGameDetail", detail.getScheduleGame().getApiId(), e);
			logger.error(MessageUtils.getApiSaveErrorMessage(GameTypeEnum.DOTA2, "scheduleGameDetail", detail.getScheduleGame().getApiId(), e), e);
		}
		return message.toString();
	}
	
	private String savePlayerDetail(List<ScheduleGamePlayerDetail> list) {
		StringBuilder message = new StringBuilder();
		try {
			scheduleGamePlayerDetailService.batchSave(list);
		} catch (Exception e) {
			for(ScheduleGamePlayerDetail detail : list) {
				message.append(savePlayerDetail(detail));
			}
		}
		return message.toString();
	}
	
	private String savePlayerDetail(ScheduleGamePlayerDetail detail) {
		StringBuilder message = new StringBuilder();
		try {
			scheduleGamePlayerDetailService.save(detail);
		} catch(Exception e) {
			MessageUtils.setApiSaveErrorMessage(message, GameTypeEnum.DOTA2, "scheduleGamePlayerDetail", detail.getScheduleGame().getApiId(), e);
			logger.error(MessageUtils.getApiSaveErrorMessage(GameTypeEnum.DOTA2, "scheduleGamePlayerDetail", detail.getScheduleGame().getApiId(), e), e);
		}
		return message.toString();
	}
	
	private String savePlayerItem(final List<ScheduleGamePlayerItems> list) {
		StringBuilder message = new StringBuilder();
		try {
			scheduleGamePlayerItemsService.batchSave(list);
		} catch(Exception e) {
			MessageUtils.setApiSaveErrorMessage(message, GameTypeEnum.DOTA2, "ScheduleGamePlayerItems", 0L, e);
			logger.error("batch save ScheduleGamePlayerItems error!!", e);
		}
		return message.toString();
	}
	
	private String saveAbilityUpgrades(final List<AbilityUpgrades> list) {
		StringBuilder message = new StringBuilder();
		try {
			abilityUpgradesService.batchSave(list);
		} catch(Exception e) {
			MessageUtils.setApiSaveErrorMessage(message, GameTypeEnum.DOTA2, "AbilityUpgrades", 0L, e);
			logger.error("batch save AbilityUpgrades error!!", e);
		}
		return message.toString();
	}
	
	private String saveGamePicksBans(final List<GamePicksBans> list) {
		StringBuilder message = new StringBuilder();
		try {
			gamePicksBansService.batchSave(list);
		} catch(Exception e) {
			MessageUtils.setApiSaveErrorMessage(message, GameTypeEnum.DOTA2, "GamePicksBans", 0L, e);
			logger.error("batch save GamePicksBans error!!", e);
		}
		return message.toString();
	}

	public Game getDota2Game() {
		if(gameCache==null) {
			gameCache = gameService.findByGameCode(GameTypeEnum.DOTA2);
		}
		return gameCache;
	}
}
