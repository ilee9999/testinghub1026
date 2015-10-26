package com.hkesports.matchticker.service.lol.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
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
import com.hkesports.matchticker.enums.GameTypeEnum;
import com.hkesports.matchticker.enums.GameWinTypeEnum;
import com.hkesports.matchticker.enums.ScheduleStatus;
import com.hkesports.matchticker.model.Game;
import com.hkesports.matchticker.model.batchJob.GamePicksBans;
import com.hkesports.matchticker.model.batchJob.Hero;
import com.hkesports.matchticker.model.batchJob.League;
import com.hkesports.matchticker.model.batchJob.LiveStreams;
import com.hkesports.matchticker.model.batchJob.Player;
import com.hkesports.matchticker.model.batchJob.Schedule;
import com.hkesports.matchticker.model.batchJob.ScheduleGame;
import com.hkesports.matchticker.model.batchJob.ScheduleGameDetail;
import com.hkesports.matchticker.model.batchJob.ScheduleGamePlayerDetail;
import com.hkesports.matchticker.model.batchJob.ScheduleGamePlayerItems;
import com.hkesports.matchticker.model.batchJob.Team;
import com.hkesports.matchticker.model.batchJob.TeamMember;
import com.hkesports.matchticker.model.batchJob.Tourament;
import com.hkesports.matchticker.model.json.factory.CustomDateDeserializer;
import com.hkesports.matchticker.model.json.factory.JsonFactory;
import com.hkesports.matchticker.model.json.lol.game.GameDetailJson;
import com.hkesports.matchticker.model.json.lol.game.GamePlayerJson;
import com.hkesports.matchticker.model.json.lol.game.GameTeamJson;
import com.hkesports.matchticker.model.json.lol.game.GameVodJson;
import com.hkesports.matchticker.model.json.lol.leagues.LeagueJson;
import com.hkesports.matchticker.model.json.lol.leagues.LiveStreamLanguageJson;
import com.hkesports.matchticker.model.json.lol.leagues.LiveStreamsJson;
import com.hkesports.matchticker.model.json.lol.legsUrl.BanJson;
import com.hkesports.matchticker.model.json.lol.legsUrl.LegsUrlJson;
import com.hkesports.matchticker.model.json.lol.legsUrl.ParticipantJson;
import com.hkesports.matchticker.model.json.lol.legsUrl.StatsJson;
import com.hkesports.matchticker.model.json.lol.legsUrl.TeamJson;
import com.hkesports.matchticker.model.json.lol.legsUrl.TimelineJson;
import com.hkesports.matchticker.model.json.lol.player.PlayerJson;
import com.hkesports.matchticker.model.json.lol.schedule.GameJson;
import com.hkesports.matchticker.model.json.lol.schedule.ScheduleJson;
import com.hkesports.matchticker.model.json.lol.team.TeamPlayerJson;
import com.hkesports.matchticker.model.json.lol.tournaments.TournamentJson;
import com.hkesports.matchticker.repository.HeroDao;
import com.hkesports.matchticker.repository.LiveStreamsDao;
import com.hkesports.matchticker.service.GamePicksBansService;
import com.hkesports.matchticker.service.GameService;
import com.hkesports.matchticker.service.LeagueService;
import com.hkesports.matchticker.service.PlayerService;
import com.hkesports.matchticker.service.ScheduleGameDetailService;
import com.hkesports.matchticker.service.ScheduleGamePlayerDetailService;
import com.hkesports.matchticker.service.ScheduleGamePlayerItemsService;
import com.hkesports.matchticker.service.ScheduleGameService;
import com.hkesports.matchticker.service.ScheduleService;
import com.hkesports.matchticker.service.TeamMemberService;
import com.hkesports.matchticker.service.TeamService;
import com.hkesports.matchticker.service.TouramentService;
import com.hkesports.matchticker.service.lol.LoLTouramentService;
import com.hkesports.matchticker.vo.LoLItem;

/**
 * @author manboyu
 *
 */
@Service("loLTouramentService")
public class LoLTouramentServiceImpl implements LoLTouramentService {
	
	private static final Logger logger = LoggerFactory.getLogger(LoLTouramentServiceImpl.class); 
	
//	private static final String API_TMP_GAME_JSON_DATATYPE = "API_GAME_JSON";
//	private static final String API_TMP_TEAM_JSON_DATATYPE = "API_TEAM_JSON";
//	private static final String API_TMP_PLAYER_JSON_DATATYPE = "API_PLAYER_JSON";
//	private static final String API_TMP_ROSTER_JSON_DATATYPE = "API_ROSTER_JSON";
	
	@Resource(name = "propertiesConfig")
	private PropertiesConfig propertiesConfig;
	@Resource(name = "jsoupUtils")
	private JsoupUtils jsoupUtils;
	
//	@Resource
//	private ApiIdTmpService apiIdTmpService;
	
	@Resource
	private TouramentService touramentService;
	
	@Resource
	private ScheduleService scheduleService;
	
	@Resource
	private GameService gameService;
	
	@Resource
	private ScheduleGameService scheduleGameService;
	
	@Resource
	private TeamService teamService;
	
	@Resource
	private TeamMemberService teamMemberService;
	
	@Resource
	private PlayerService playerService;
	
	@Resource
	private ScheduleGameDetailService scheduleGameDetailService;
	
	@Resource
	private ScheduleGamePlayerDetailService scheduleGamePlayerDetailService;
	
	@Resource
	private ScheduleGamePlayerItemsService scheduleGamePlayerItemsService;
	
	@Resource
	private GamePicksBansService gamePicksBansService;
	
	@Resource(name = "liveStreamsDao")
	private LiveStreamsDao liveStreamsDao;
	
	@Resource(name = "heroDao")
	private HeroDao heroDao;
	
	@Resource
	private LeagueService leagueService;
	
	private Map<Long, PlayerJson> playerCache = new HashMap<>();
	private Map<Long, com.hkesports.matchticker.model.json.lol.team.TeamJson> teamCache = new HashMap<>();
	private Game gameCache = null;
	
	private void init() {
		clear(playerCache);
		clear(teamCache);
	}
	
	private void clear(Map<?, ?> map) {
		try {
			if(map!=null && !map.isEmpty()) {
				map.clear();
			}
		} catch (Exception e) {
			logger.error("clear map error!!", e);
		}
	}
	
	public Game getLOLGame() {
		if(gameCache==null) {
			gameCache = gameService.findByGameCode(GameTypeEnum.LOL);
		}
		return gameCache;
	}
	
	@Override
	public List<LeagueJson> getLeagueJson() throws Exception {
		String leaguesUrl = this.propertiesConfig.getProperty("lol.leagues.url");
		Map<String, List<LeagueJson>> mapObj = JsonFactory.fromJson(this.jsoupUtils.getJsonString(leaguesUrl), new TypeToken<Map<String, List<LeagueJson>>>() {}.getType());;
		return mapObj !=null ? mapObj.get("leagues") : null;
	}

	@Override
	public LeagueJson getLeagueJsonById(String leagueId) throws Exception {
		String leagueUrl = StringUtils.replace(this.propertiesConfig.getProperty("lol.league.url"), "{leagueId}", leagueId);
		return JsonFactory.fromJson(this.jsoupUtils.getJsonString(leagueUrl), new TypeToken<LeagueJson>() {}.getType());
	}

	@Override
	public TournamentJson getTournamentJson(String tournamentId) throws Exception {
		String tournamentUrl = StringUtils.replace(this.propertiesConfig.getProperty("lol.tournaments.url"), "{tournamentId}", tournamentId);
		return JsonFactory.fromJson(this.jsoupUtils.getJsonString(tournamentUrl), this.propertiesConfig.getProperty("lol.dateformat"), new TypeToken<TournamentJson>() {}.getType());
	}

	@Override
	public List<ScheduleJson> getScheduleJsons(String tournamentId) throws Exception {
		long t1 = System.currentTimeMillis();
		String scheduleUrl = StringUtils.replace(this.propertiesConfig.getProperty("lol.schedules.url"), "{tournamentId}", tournamentId);
		Map<String, ScheduleJson> schedule = JsonFactory.fromJson(this.jsoupUtils.getJsonString(scheduleUrl), new CustomDateDeserializer(this.propertiesConfig.getProperty("lol.dateformat2")), new TypeToken<Map<String, ScheduleJson>>() {}.getType());
		if(CollectionUtils.isEmpty(schedule)) {
			return null;
		}
		List<ScheduleJson> scheduleList = new ArrayList<>(schedule.size());
		for (Map.Entry<String, ScheduleJson> entry : schedule.entrySet()) {
			scheduleList.add(entry.getValue());
		}
		long t2 = System.currentTimeMillis();
		logger.debug("API: ScheduleJsons id:{}, 共耗費:{} ms", tournamentId, (t2 - t1));
		return !CollectionUtils.isEmpty(scheduleList) ? scheduleList : null;
	}

	@Override
	public List<GameJson> getGameJson(ScheduleJson scheduleJson) {
		Map<String, GameJson> games = scheduleJson.getGames();
		List<GameJson> gameList = new ArrayList<>(games.size());
		for (Map.Entry<String, GameJson> entry : games.entrySet()) {
			GameJson gameJson = entry.getValue();
			gameJson.setScheduleJson(scheduleJson);
			gameList.add(gameJson);
		}
		return gameList;
	}

	@Override
	public List<GameDetailJson> getGameDetailJson(List<GameJson> gameList) throws Exception {
		long t1 = System.currentTimeMillis();
		List<GameDetailJson> gameDetailList = new ArrayList<>(gameList.size());
		for(GameJson game : gameList) {
			String gameDetailUrl = StringUtils.replace(this.propertiesConfig.getProperty("lol.gamedetails.url"), "{gameId}", String.valueOf(game.getId()));
			GameDetailJson gamedetail = JsonFactory.fromJson(this.jsoupUtils.getJsonString(gameDetailUrl), this.propertiesConfig.getProperty("lol.dateformat"), new TypeToken<GameDetailJson>() {}.getType());
			if(gamedetail != null) {
				gamedetail.setId(game.getId());
				gamedetail.setScheduleJson(game.getScheduleJson());
				gameDetailList.add(gamedetail);	
			}
		}
		long t2 = System.currentTimeMillis();
		logger.debug("API: GameDetailJson 共耗費:{} ms" , (t2 - t1));
		return gameDetailList;
	}

	@Override
	public LegsUrlJson getLegsUrlJson(String legsUrl) throws Exception {
		long t1 = System.currentTimeMillis();
		try {
			LegsUrlJson jsonObj = JsonFactory.fromJson(this.jsoupUtils.getJsonString(legsUrl), new TypeToken<LegsUrlJson>() {}.getType());
			long t2 = System.currentTimeMillis();
			logger.debug("API: LegsUrlJson 共耗費:{} ms" , (t2 - t1));
			return jsonObj;
		} catch (Exception e) {
			logger.error("API: LegsUrlJson Exception:", e);
		}
		return null;
	}

	@Override
	public com.hkesports.matchticker.model.json.lol.team.TeamJson getTeamJson(Long gameTeamJsonId) throws Exception {
		if(gameTeamJsonId == null) {
			return null;
		}
		com.hkesports.matchticker.model.json.lol.team.TeamJson team = teamCache.get(gameTeamJsonId);
		if(team != null) {
			logger.debug("API: TeamJson id:{} from cache", gameTeamJsonId);
			return team;
		}
		long t1 = System.currentTimeMillis();
		String teamUrl = StringUtils.replace(this.propertiesConfig.getProperty("lol.teams.url"), "{teamId}", String.valueOf(gameTeamJsonId));
		team = JsonFactory.fromJson(this.jsoupUtils.getJsonString(teamUrl), new TypeToken<com.hkesports.matchticker.model.json.lol.team.TeamJson>() {}.getType());
		if(team==null) {
			logger.warn("API: TeamJson id:{}, teamJson is null", gameTeamJsonId);
			return null;
		}
		team.setId(gameTeamJsonId);
		if(team!=null) {
			teamCache.put(gameTeamJsonId, team);
		}
		long t2 = System.currentTimeMillis();
		logger.debug("API: TeamJson id:{}, 共耗費:{} ms", gameTeamJsonId, (t2 - t1));
		return team;
	}
	
	@Override
	public PlayerJson getPlayerJson(Long playerId) throws Exception {
		if(playerId == null) {
			return null;
		}
		PlayerJson playerJson = playerCache.get(playerId);
		if(playerJson!=null) {
			logger.debug("API: PlayerJson id:{} from cache", playerId);
			return playerJson;
		}
		long t1 = System.currentTimeMillis();
		String playerUrl = StringUtils.replace(this.propertiesConfig.getProperty("lol.player.url"), "{playerId}", String.valueOf(playerId));
		playerJson = JsonFactory.fromJson(this.jsoupUtils.getJsonString(playerUrl), new CustomDateDeserializer(this.propertiesConfig.getProperty("lol.dateformat3")), new TypeToken<PlayerJson>() {}.getType());
		if(playerJson!=null) {
			playerJson.setId(playerId);
			playerCache.put(playerId, playerJson);
		}
		long t2 = System.currentTimeMillis();
		logger.debug("API: PlayerJson id:{}, 共耗費:{} ms", playerId, (t2 - t1));
		return playerJson;
	}

	@Override
	public List<Tourament> findTouraments() {
		return this.touramentService.findByGameTypeOrderByApiIdDesc(GameTypeEnum.LOL);
	}

	public List<League> findLeagues() {
		return this.leagueService.findByGameTypeOrderByApiIdDesc(GameTypeEnum.LOL);
	}
	
	@Override
	public List<TeamMember> findTeamMembersByTournamentAndTeam(Tourament tourament, Team team) {
		return this.teamMemberService.findByTournamentAndTeam(tourament, team);
	}

	@Override
	public void saveLeague() throws Exception {
		List<LeagueJson> leaguesJson = getLeagueJson();
		if(leaguesJson == null) {
			throw new Exception("there's no League data from URL : " + this.propertiesConfig.getProperty("lol.leagues.url"));
		}
		StringBuilder message = new StringBuilder();
		Map<Long, League> map = AppUtils.putModelByApiId(findLeagues());
		for(LeagueJson leagueJson : leaguesJson) {
			League league = map.get(leagueJson.getId());
			try {
				if(league != null && league.equals(leagueJson)) {
					logger.debug("league equals leagueJson without change [ league ] : {} ", league);
					continue;
				}
				league = this.doSaveLeague(league, leagueJson);
			} catch (Exception e) {
				logger.error("save Tourament Data - apiId [ " + leagueJson.getId() + " ] occur exception : ", e);
				MessageUtils.setApiSaveErrorMessage(message, GameTypeEnum.LOL, "Tourament", leagueJson.getId(), e);
			}
			if(StringUtils.isNotBlank(message)) {
				System.out.println(leagueJson.getUrl().length());
				throw new Exception(message.toString());
			}
		}
	}

	private League doSaveLeague(League league, LeagueJson leagueJson) {
		logger.debug("doSaveLeague by leagueJson {} : ", leagueJson);
		if(league == null) {
			league = new League();
			league.setApiId(leagueJson.getId());
			league.setGameType(getLOLGame().getGameCode());
		}
		league.setLabel(leagueJson.getLabel());
		league.setShortName(leagueJson.getShortName());
		league.setUrl(leagueJson.getUrl());
		league.setColor(leagueJson.getColor());
		league.setLeagueImage(leagueJson.getLeagueImage());
		league.setDefaultTournamentId(leagueJson.getDefaultTournamentId());
		league.setDefaultSeriesId(leagueJson.getDefaultSeriesId());
		league.setNoVods(leagueJson.getNoVods());
		league.setMenuWeight(leagueJson.getMenuWeight());
		league.setPublished(leagueJson.getPublished());
		league = this.leagueService.save(league);
		
		this.liveStreamsDao.deleteByLeague(league.getId());
		List<LiveStreamLanguageJson> internationalLiveStream = leagueJson.getInternationalLiveStream();
		if(!CollectionUtils.isEmpty(internationalLiveStream)) {
			for(LiveStreamLanguageJson liveStreamLanguageJson : internationalLiveStream) {
				for(LiveStreamsJson stream : liveStreamLanguageJson.getStreams()) {
					LiveStreams liveStream = new LiveStreams();
					liveStream.setLanguage(liveStreamLanguageJson.getLanguage());
					liveStream.setType(stream.getTitle());
					if(stream.getUrl()!=null && stream.getUrl().startsWith("http")) {
						liveStream.setUrl(stream.getUrl());
					}
					liveStream.setEmbedCode(liveStream.getEmbedCode());
					liveStream.setLeague(league);
					this.liveStreamsDao.save(liveStream);
				}
			}
		}
		logger.debug("saveLeague success : {} ", league);
		return league;
	}

	private Tourament doSaveTournament(League league, Tourament tourament, TournamentJson tournamentJson) {
		logger.info("doSaveTournament success league apiId:{}, tournament apiId:{}", league.getApiId(), tournamentJson.getId());
		if(tourament == null) {
			tourament = new Tourament();
			tourament.setApiId(tournamentJson.getId());
			tourament.setGameType(getLOLGame().getGameCode());
		}
		tourament.setLeague(league);
		tourament.setTouramentName(tournamentJson.getNamePublic());
		tourament.setTouramentShortName(tournamentJson.getName());
		
		tourament.setTouramentFromDate(tournamentJson.getDateBegin());
		tourament.setTouramentToDate(tournamentJson.getDateEnd());
		
		tourament.setNoVods(tournamentJson.getNoVods());
		tourament.setSeason(tournamentJson.getSeason());
		tourament.setWinner(StringUtils.isNotBlank(tournamentJson.getWinner()) ? Long.parseLong(tournamentJson.getWinner()) : null);
		tourament.setIsFinished(tournamentJson.isFinished() ? (short)1 : 0);
		tourament.setPublished(tournamentJson.isPublished());
		tourament = touramentService.save(tourament);
		return tourament;
	}
	
	@Override
	public void saveMatchInfos(boolean allFetch) throws Exception {
		long t1 = System.currentTimeMillis();
		List<League> leagues = findLeagues();
		if(CollectionUtils.isEmpty(leagues)) {
			throw new Exception("saveTourments but League is empty, must hava League data first !");
		}
		init();
		StringBuilder message = new StringBuilder();
		int count = 1;
		int total = leagues.size();
		for(League league : leagues) {
			String procId = RandomUtils.getRandomCode(10);//create procId!!
			try {
			logger.info("****** Start leagueId:{} ({}/{}) , procId:{}", league.getApiId(), count, total, procId);
			long t3 = System.currentTimeMillis();
			try {
				saveMatchInfos(league, procId, message, allFetch);
			} catch (Exception e) {
				logger.error("leagueId:{} ({}/{}) , procId:{} ERROR!!!!", league.getApiId(), count, total, procId);
				logger.error("Exception!!!", e);
			}
			long t5 = System.currentTimeMillis();
			logger.info("****** End leagueId:{}  ({}/{}) 共耗費:{} s, procId:{} \n", league.getApiId(), count, total, (t5 - t3)/1000.0, procId);
			count++;
			} finally {
//				logger.info("****** deleteByProcId procId:{}", procId);
//				try {
//					apiIdTmpService.deleteByGameTypeAndProcId(GameTypeEnum.LOL, procId);
//				} catch (Exception e) {
//					logger.error("deleteByProcId error!! procId:{}", procId,e);
//				}
			}
		}
		long t2 = System.currentTimeMillis();
		logger.info("總共耗費:{} s\n", (t2 - t1)/1000.0);
		if(StringUtils.isNotBlank(message)) {
			logger.warn(message.toString());
			throw new Exception(message.toString());
		}
	}

	
	private Integer limitHour = null;
	private Date getBeforeDate() {
		if(limitHour==null) {
			String limitHourStr = propertiesConfig.getProperty("batchjob.limit.hour");
			limitHour = StringUtils.isNotBlank(limitHourStr) ? Integer.valueOf(limitHourStr) : new Integer(-72);
		}
		Calendar c = Calendar.getInstance();
		c.add(Calendar.HOUR, limitHour);
		return c.getTime();
	}
	
	private void saveMatchInfos(League league, String procId, StringBuilder message, boolean allFetch) throws Exception {
		long t3 = System.currentTimeMillis();
		LeagueJson leagueJson = getLeagueJsonById(String.valueOf(league.getApiId()));
		if(leagueJson==null) {
			logger.warn("leagueJson is null, leagueId:{}, procId:{}", league.getApiId(), procId);
			return;
		}
		long t4 = System.currentTimeMillis();
		List<Long> tournamentIds = leagueJson.getLeagueTournaments();
		logger.debug("getLeagueJsonById, set tmptable 共耗費:{} ms", (t4 - t3));
		if(CollectionUtils.isEmpty(tournamentIds)) {
			logger.warn("tournamentIds is empty, leagueId:{}, procId:{}", league.getApiId(), procId);
			return;
		}
		
		for(Long tournamentId : tournamentIds) {
			long t5 = System.currentTimeMillis();
			logger.info("leagueId:{}, tournamentId:{}, procId:{} start!!", league.getApiId(), tournamentId, procId);
			TournamentJson tournamentJson = getTournamentJson(String.valueOf(tournamentId));
			if(tournamentJson==null) {
				logger.warn("getTournamentJson tournamentJson is null, tournament Id:{} ", tournamentId);
				continue;
			}
			tournamentJson.setId(tournamentId);
			Tourament tourament = touramentService.findByApiIdAndGameType(tournamentId, getLOLGame().getGameCode());
			if(tourament==null || tourament.getLeague()==null ||
				!league.getId().equals(tourament.getLeague().getId()) ||
				!tourament.equals(tournamentJson)) {
				tourament = doSaveTournament(league, tourament, tournamentJson);
			}
			Date beforeDate = getBeforeDate();
			if(!allFetch && (tourament != null && tourament.getTouramentToDate() != null 
					&& tourament.getTouramentToDate().after(new Date(0L)) //因為json資料有可能為1970-01-01 00:00:00 若為此時間則不略過
					&& tourament.getTouramentToDate().before(beforeDate))) {//檢查json時間必須大於今天(+limitHour)才跑
				logger.info("leagueId:{}, tourament.id:{}, tourament.apiId:{}, tourament.endTime:{} before {}, procId:{}, continue!!", league.getApiId(), tourament.getId(), tourament.getApiId(), tourament.getTouramentToDate(), beforeDate, procId);
				continue;
			}
			long t6 = System.currentTimeMillis();
			logger.debug("doSaveTournament leagueId:{}, tournamentId:{}, 共耗費:{} ms", league.getApiId(), tournamentId, (t6 - t5));
			List<ScheduleJson> scheduleList = getScheduleJsons(String.valueOf(tournamentId));
			if(CollectionUtils.isEmpty(scheduleList)) {
				logger.warn("leagueId:{}, tournamentId:{}, scheduleList is empty", league.getApiId(), tournamentId);
				continue;
			}
			long t7 = System.currentTimeMillis();
			logger.info("prepare scheduleList leagueId:{}, tournamentId:{}, 共耗費:{} ms", league.getApiId(), tournamentId, (t7 - t6));
			for(ScheduleJson scheduleJson : scheduleList) {
				try {
					long t9 = System.currentTimeMillis();
					Schedule schedule = this.scheduleService.findByApiIdAndGameType(scheduleJson.getMatchId(), getLOLGame().getGameCode());
					if(schedule==null || schedule.getTourament()==null || 
						!tourament.getId().equals(schedule.getTourament().getId()) ||
						!schedule.equals(scheduleJson)) {
						schedule = this.doSaveSchedules(tourament, schedule, tournamentJson, scheduleJson);
					}
					this.doSaveScheduleGame(message, procId, scheduleJson, schedule);
					long t10 = System.currentTimeMillis();
					logger.info("do save schedule & scheduleGame leagueId:{}, tournamentId:{}, 共耗費:{} s\n", league.getApiId(), tournamentId, (t10 - t9)/1000.0);
				} catch (Exception e) {
					logger.error("save Schedules - leagueId:{}, tournamentId:{}, scheduleId:{} , occur exception:{}", league.getApiId(), tournamentId, scheduleJson.getMatchId(), e);
					MessageUtils.setApiSaveErrorMessage(message, GameTypeEnum.LOL, "Schedule", scheduleJson.getMatchId(), e);
				}
			}
		}
		
	}
	
	private Schedule doSaveSchedules(Tourament tourament, Schedule schedule, TournamentJson tournamentJson, ScheduleJson scheduleJson) {
		logger.debug("doSaveSchedules by tournamentJson:{}, scheduleJson : {} ", tournamentJson, scheduleJson);
		if(schedule == null) {
			schedule = new Schedule();
			schedule.setApiId(scheduleJson.getMatchId());
			schedule.setGameType(GameTypeEnum.LOL);
			schedule.setaSideSupportCount(0L);
			schedule.setbSideSupportCount(0L);
			schedule.setStatus(ScheduleStatus.Ready);
		}
		schedule.setTouramentName(tourament.getTouramentName());
		schedule.setTourament(tourament);
		
		schedule.setStartTime(tournamentJson.getDateBegin());
		schedule.setEndTime(tournamentJson.getDateEnd());
		
		schedule.setDateTime(scheduleJson.getDateTime());
		schedule.setWinnerId(StringUtils.isNotBlank(scheduleJson.getWinnerId()) ? Long.parseLong(scheduleJson.getWinnerId()) : null);
		schedule.setMaxGames(StringUtils.isNotBlank(scheduleJson.getMaxGames()) ? Short.parseShort(scheduleJson.getMaxGames()) : null);
		schedule.setIsLive(scheduleJson.isLive());
		schedule.setIsFinished(scheduleJson.getIsFinished());
		schedule.setPolldaddyId(scheduleJson.getPolldaddyId());
		
		schedule.setGame(getLOLGame());
		schedule = this.scheduleService.save(schedule);
		logger.debug("saveSchedule success : {} ", schedule);
		return schedule;
	}
	
	private void doSaveScheduleGame(StringBuilder message, String procId, ScheduleJson scheduleJson, Schedule schedule) throws Exception {
		long t1 = System.currentTimeMillis();
		List<GameDetailJson> gameDetailList = getGameDetailJson(getGameJson(scheduleJson));
		if(CollectionUtils.isEmpty(gameDetailList)) {
			logger.warn("match id:{}, gameDetailList is empty", schedule.getApiId());
			return;
		}
		List<Long> scheduleGameIds = this.getJsonIds(gameDetailList);
//		this.apiIdTmpService.clearApiIdTmpByLOL(scheduleGameIds, API_TMP_GAME_JSON_DATATYPE, procId);
		List<ScheduleGame> scheduleGames = this.scheduleGameService.findAllByGameTypeAndApiIds(getLOLGame().getGameCode(), scheduleGameIds);
		List<ScheduleGamePlayerDetail> scheduleGamePlayerDetails = this.scheduleGamePlayerDetailService.findAllByGameTypeAndApiIds(getLOLGame().getGameCode(), scheduleGameIds);
		
		Map<Long, Map<Long, ScheduleGamePlayerDetail>> scheduleGamePlayerDetailMap = this.getScheduleGamePlayerDetailMap(scheduleGamePlayerDetails);
		Map<Long, ScheduleGame> scheduleGameMap = AppUtils.putModelByApiId(scheduleGames);
		long t2 = System.currentTimeMillis();
		logger.debug("doSaveScheduleGame getJsonIds Schedule ApiId:{}, 共耗費:{} ms", schedule.getApiId(), (t2 - t1));
		Map<Long, Team> scheduleTeamMap = new HashMap<>(2);
		for(GameDetailJson gameDetailJson : gameDetailList) {
			logger.info("procId:{}, schedule id:{}, schedule ApiId:{}, scheduleGame ApiId:{}, legsUrl:{}", procId, schedule.getId(), schedule.getApiId() , gameDetailJson.getId(), gameDetailJson.getLegsUrl());
			long t3 = System.currentTimeMillis();
			ScheduleGame scheduleGame = scheduleGameMap.get(gameDetailJson.getId());
			try {
				LegsUrlJson legsUrlJson = null;
				if(StringUtils.isNotBlank(gameDetailJson.getLegsUrl())) {
					legsUrlJson = getLegsUrlJson(gameDetailJson.getLegsUrl());
				}
				long t4 = System.currentTimeMillis();
				logger.debug("doSaveScheduleGame getLegsUrlJson ScheduleGame ApiId:{}, 共耗費:{} ms", gameDetailJson.getId(), (t4 - t3));
				gameDetailJson.setGameCreation(legsUrlJson !=null ? legsUrlJson.getGameCreation() : null);
				if(scheduleGame == null || !scheduleGame.equals(gameDetailJson)) {
					scheduleGame = this.doSaveScheduleGame(schedule, scheduleGame, gameDetailJson);
				} 
				Map<String, GameTeamJson> contestants = gameDetailJson.getContestants();
				Map<String, ScheduleGameDetail> gameDetailMap = new HashMap<>();
				if(contestants!=null) {
					List<Long> contestantIds = this.getJsonIds(contestants);
//					this.apiIdTmpService.clearApiIdTmpByLOL(contestantIds, API_TMP_TEAM_JSON_DATATYPE, procId);
					Map<Long, Team> teamMap = AppUtils.putModelByApiId(this.teamService.findAllByGameTypeAndApiIds(getLOLGame().getGameCode(), contestantIds));
					for (Map.Entry<String, GameTeamJson> entry : contestants.entrySet()) {
						GameTeamJson gameTeamJson = entry.getValue();
						if(gameTeamJson == null) {
							continue;
						}
						com.hkesports.matchticker.model.json.lol.team.TeamJson teamJson = this.getTeamJson(gameTeamJson.getId());
						if(teamJson == null) {
							continue;
						}
						Team team = teamMap.get(gameTeamJson.getId());
						if(team == null || !team.equals(teamJson)) {
							team = this.doSaveTeam(schedule.getTourament(), team, teamJson);
						}
						scheduleTeamMap.put(team.getApiId(), team);
						if(legsUrlJson != null) {
							this.doSaveScheduleGameDetail(scheduleGame, team, legsUrlJson, gameDetailJson, entry.getKey(), gameDetailMap);
						} else {
							ScheduleGameDetail scheduleGameDetail = this.scheduleGameDetailService.findByScheduleGameAndTeam(scheduleGame, team);
							if(scheduleGameDetail == null || !scheduleGameDetail.equals(null, team, scheduleGame)) {
								scheduleGameDetail = this.doSaveScheduleGameDetail(scheduleGame, team, scheduleGameDetail, null, entry.getKey());
							}
							gameDetailMap.put(gameTeamJson.getId().toString(), scheduleGameDetail);
						}
					}
				}
				long t5 = System.currentTimeMillis();
				logger.debug("doSaveScheduleGame doSaveScheduleGameDetail ScheduleGame ApiId:{}, 共耗費:{} ms", scheduleGame.getApiId(), (t5 - t4));
				Map<String, GamePlayerJson> playerJsons = gameDetailJson.getPlayers();
				List<Long> playerIds = this.getJsonIds(playerJsons);
//				this.apiIdTmpService.clearApiIdTmpByLOL(playerIds, API_TMP_PLAYER_JSON_DATATYPE, procId);
				Map<Long, Player> playerMap = AppUtils.putModelByApiId(this.playerService.findAllByGameTypeAndApiIds(getLOLGame().getGameCode(), playerIds));
				Map<Long, ScheduleGamePlayerDetail> playerDetailMap = scheduleGamePlayerDetailMap.get(scheduleGame.getApiId());
				for (Map.Entry<String, GamePlayerJson> entry : playerJsons.entrySet()) {
					GamePlayerJson gamePlayerJson = entry.getValue();
					Player player = playerMap.get(gamePlayerJson.getId());
					PlayerJson playerJson = getPlayerJson(gamePlayerJson.getId());
					if(playerJson != null) {
						if (player == null || !player.equals(playerJson)) {
							player = this.doSavePlayer(player, playerJson);
						}
					} else {
						logger.warn("Schedule ApiId:{}, scheduleGame ApiId:{}, gamePlayerJson.getId():{} - getPlyayerJosn is null. ", schedule.getApiId(), gameDetailJson.getId(), gamePlayerJson.getId());
					}
					ScheduleGamePlayerDetail scheduleGamePlayerDetail = null;
					if(playerDetailMap != null) {
						scheduleGamePlayerDetail = playerDetailMap.get(gamePlayerJson.getId());
					}
					ParticipantJson participant = compareLegPlayerJson(gamePlayerJson, legsUrlJson);
					ScheduleGameDetail gameDetail = gameDetailMap.get(gamePlayerJson.getTeamId());
					if(scheduleGamePlayerDetail == null || !scheduleGamePlayerDetail.equals(participant, scheduleGame, gameDetail, player)) {
						this.doSaveScheduleGamePlayerDetail(scheduleGamePlayerDetail, scheduleGame, gameDetail, player, participant);
					}
					if(participant == null) {
						continue;
					}
					this.doSaveScheduleGamePlayerItems(scheduleGame, player, participant);
				}
				long t6 = System.currentTimeMillis();
				logger.debug("doSaveScheduleGame doSaveScheduleGamePlayerItems ScheduleGame ApiId:{}, 共耗費:{} ms", scheduleGame.getApiId(), (t6 - t5));
			} catch (Exception e) {
				logger.error("save ScheduleGame - apiId [ " + gameDetailJson.getId() + " ] occur exception : ", e);
				MessageUtils.setApiSaveErrorMessage(message, GameTypeEnum.LOL, "ScheduleGame", gameDetailJson.getId(), e);
			}
		}
		
		if(scheduleTeamMap.size()!=2) {
			logger.warn("schedule id:{}, schedule apiId:{}, team count != 2, map:{}", schedule.getId(), schedule.getApiId(), scheduleTeamMap);
		}
		Team aTeam = null;
		Team bTeam = null;
		for(Entry<Long, Team> key:scheduleTeamMap.entrySet()) {
			Team team = key.getValue();
			if(aTeam==null) {
				aTeam = team;
			} else {
				if(aTeam.getApiId() > team.getApiId()) {
					bTeam = team;
				} else {
					bTeam = aTeam;
					aTeam = team;
				}
				break;
			}
		}
		
		if(!schedule.equals(aTeam, bTeam)) {
			if(aTeam!=null) {
				schedule.setTeamAName(aTeam.getTeamFullName());
				schedule.setTeamAId(aTeam.getId());
			} else {
				schedule.setTeamAName(null);
				schedule.setTeamAId(null);
			}
			if(bTeam!=null) {
				schedule.setTeamBName(bTeam.getTeamFullName());
				schedule.setTeamBId(bTeam.getId());
			} else {
				schedule.setTeamBName(null);
				schedule.setTeamBId(null);
			}
			this.scheduleService.save(schedule);
		}
		this.scheduleService.doSaveScheduleResults(schedule);
	}
	
	private ScheduleGame doSaveScheduleGame(Schedule schedule, ScheduleGame scheduleGame, GameDetailJson gameDetailJson) {
		if(logger.isDebugEnabled()) logger.debug("save ScheduleGame by gameDetailJson : {} ", gameDetailJson);
		if(scheduleGame == null) {
			scheduleGame = new ScheduleGame();
			scheduleGame.setApiId(gameDetailJson.getId());
			scheduleGame.setGameType(GameTypeEnum.LOL);
		}
		scheduleGame.setDateTime(gameDetailJson.getDateTime());
		scheduleGame.setWinnerId(StringUtils.isNotBlank(gameDetailJson.getWinnerId()) ? Long.parseLong(gameDetailJson.getWinnerId()) : null);
		scheduleGame.setMaxGames(StringUtils.isNotBlank(gameDetailJson.getMaxGames()) ? Short.parseShort(gameDetailJson.getMaxGames()) : null);
		scheduleGame.setGameNumber(StringUtils.isNotBlank(gameDetailJson.getGameNumber()) ? Short.parseShort(gameDetailJson.getGameNumber()) : null);
		scheduleGame.setGameLength(gameDetailJson.getGameLength());
		scheduleGame.setSchedule(schedule);
		scheduleGame.setPlatformId(gameDetailJson.getPlatformId());
		scheduleGame.setPlatformGameId(gameDetailJson.getPlatformGameId() != null ? gameDetailJson.getPlatformGameId().longValue() : null);
		scheduleGame.setNoVods(gameDetailJson.getNoVods() != null ? gameDetailJson.getNoVods().shortValue() : null);
		scheduleGame.setLegsUrl(gameDetailJson.getLegsUrl());
		scheduleGame.setTournamentRound(gameDetailJson.getTournament() !=null ? gameDetailJson.getTournament().getRound() : null);
		scheduleGame.setGameCreation(gameDetailJson !=null ? gameDetailJson.getGameCreation() : null);
		
		scheduleGame = this.scheduleGameService.save(scheduleGame);
		this.liveStreamsDao.deleteByScheduleGame(scheduleGame.getId());
		Map<String, GameVodJson> vods = gameDetailJson.getVods();
		if(!CollectionUtils.isEmpty(vods)) {
			for (Map.Entry<String, GameVodJson> entry : vods.entrySet()) {
				GameVodJson gameVodJson = entry.getValue();
				LiveStreams liveStream = new LiveStreams();
				liveStream.setType(gameVodJson.getType());
				liveStream.setUrl(gameVodJson.getURL());
				liveStream.setEmbedCode(gameVodJson.getEmbedCode());
				liveStream.setScheduleGame(scheduleGame);
				this.liveStreamsDao.save(liveStream);
			}
		}
		
		logger.debug("saveScheduleGame success : {} ", scheduleGame);
		return scheduleGame;
	}
	
	private Team doSaveTeam(Tourament tourament, Team team, com.hkesports.matchticker.model.json.lol.team.TeamJson teamJson) throws Exception {
		logger.debug("save Team by teamJson {} : ", teamJson);
		if(team == null) {
			team = new Team();
			team.setApiId(teamJson.getId());
			team.setGameType(GameTypeEnum.LOL);
		}
		team.setTeamName(StringUtils.isBlank(teamJson.getAcronym()) ? GameTypeEnum.LOL + "_" + teamJson.getId() : teamJson.getAcronym());
		team.setTeamFullName(teamJson.getName());
		team.setNoPlayers(teamJson.getNoPlayers());
		team.setTeamUrl(teamJson.getProfileUrl());
		team.setTeamPhotoUrl(teamJson.getTeamPhotoUrl());
		team.setLogoUrl(teamJson.getLogoUrl());
		this.teamService.save(team);
		logger.debug("saveTeam success : {} ", team);
		
		Map<String, TeamPlayerJson> roster = teamJson.getRoster();
		List<TeamMember> teamMembers = findTeamMembersByTournamentAndTeam(tourament, team);
		List<Long> rosterIds = this.getJsonIds(roster);
//		this.apiIdTmpService.clearApiIdTmpByLOL(rosterIds, API_TMP_ROSTER_JSON_DATATYPE, procId);
		List<Player> players = this.playerService.findAllByGameTypeAndApiIds(getLOLGame().getGameCode(), rosterIds);
		Map<Long, Player> playerMap = AppUtils.putModelByApiId(players);
		for (Map.Entry<String, TeamPlayerJson> entry : roster.entrySet()) {
			TeamPlayerJson teamPlayerJson = entry.getValue();
			PlayerJson playerJson = getPlayerJson(teamPlayerJson.getPlayerId());
			if(playerJson==null) {
				logger.warn("playerJson is null, teamPlayerJson.playerId:{} ", teamPlayerJson.getPlayerId());
				continue;
			}
			// use TeamPlayerJson to get Play data from API
			Player player = playerMap.get(teamPlayerJson.getPlayerId());
			if(player == null || !player.equals(playerJson)) {
				player = this.doSavePlayer(player, playerJson);
			}
			TeamMember teamMember = this.getTeamMemberByPlayerId(teamMembers, player.getId());
			if(teamMember == null || !teamMember.equals(teamPlayerJson)) {
				this.doSaveTeamMember(tourament, team, teamMember, teamPlayerJson, player);
			}
		}
		return team;
	}
	
	private void doSaveTeamMember(Tourament tourament, Team team, TeamMember teamMember, TeamPlayerJson teamPlayerJson, Player player) {
		logger.debug("save TeamMember by teamPlayerJson : {} ", teamPlayerJson);
		if(teamMember == null) {
			teamMember = new TeamMember();
		}
		teamMember.setPlayer(player);
		teamMember.setMemberName(teamPlayerJson.getName());
		teamMember.setMemberFullName(teamPlayerJson.getName());
		teamMember.setIsStarter(teamPlayerJson.getIsStarter().shortValue());
		teamMember.setRole(teamPlayerJson.getRole());
		teamMember.setTeam(team);
		teamMember.setTourament(tourament);
		teamMember = this.teamMemberService.save(teamMember);
		logger.debug("saveTeamMember success : {} ", teamMember);
	}
	
	private Player doSavePlayer(Player player, PlayerJson playerJson) {
		try {
			logger.debug("save player by playerJson : " + playerJson);
			if(player == null) {
				player = new Player();
				player.setApiId(playerJson.getId());
				player.setGameType(GameTypeEnum.LOL);
			}
			player.setBio(playerJson.getBio());
			player.setPlayerName(StringUtils.isNotBlank(playerJson.getName()) ? playerJson.getName() : GameTypeEnum.LOL + "_" + playerJson.getId() );
			player.setPlayerFullName(playerJson.getFirstname() + playerJson.getLastName());
			player.setPlayerUrl(playerJson.getProfileUrl());
			player.setPhotoUrl(playerJson.getPhotoUrl());
			player.setHometown(playerJson.getHometown());
			player.setFacebookUrl(playerJson.getFacebookUrl());
			player.setTwitterUrl(playerJson.getTwitterUrl());
			player.setIsStarter(playerJson.getIsStarter());
			player.setResidency(playerJson.getResidency());
			player.setContractExpiration(playerJson.getContractExpiration());
			player = this.playerService.save(player);
			logger.debug("savePlayer success : {} ", player);
			return player;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	private void doSaveScheduleGameDetail(ScheduleGame scheduleGame, Team team, LegsUrlJson legsUrlJson, GameDetailJson gameDetailJson, String position, Map<String, ScheduleGameDetail> gameDetailMap) {
		if(legsUrlJson == null) {
			return;
		}
		List<TeamJson> teamJsons = legsUrlJson.getTeams();
		if(CollectionUtils.isEmpty(teamJsons)) {
			return;
		}
		Map<Integer, GamePicksBans> gamePicksBansMap = getGamePickBansMap(this.gamePicksBansService.findByScheduleGameAndTeamId(scheduleGame, team.getId()));
		for(TeamJson teamJson : teamJsons) {
			compareLegTeamsJson(gameDetailJson.getPlayers(), legsUrlJson.getParticipants(), teamJson);
			if(team.getApiId().toString().equals(teamJson.getTeamApiId())) {
				ScheduleGameDetail scheduleGameDetail = this.scheduleGameDetailService.findByScheduleGameAndTeam(scheduleGame, team);
				if(scheduleGameDetail == null || !scheduleGameDetail.equals(teamJson, team, scheduleGame)) {
					scheduleGameDetail = this.doSaveScheduleGameDetail(scheduleGame, team, scheduleGameDetail, teamJson, position);
				}
				gameDetailMap.put(teamJson.getTeamApiId(), scheduleGameDetail);
				List<BanJson> banJsons = teamJson.getBans();
				if(!CollectionUtils.isEmpty(banJsons)) {
					for(BanJson banJson : banJsons) {
						GamePicksBans oGamePicksBans = gamePicksBansMap.get(banJson.getPickTurn());
//						Hero hero = this.heroDao.findByApiIdAndGameType(banJson.getChampionId().longValue(), GameTypeEnum.LOL);
						Hero hero = this.heroDao.findByGameIdAndApiId(getLOLGame().getId(), banJson.getChampionId().longValue());
						if(oGamePicksBans == null || !oGamePicksBans.equals(banJson, scheduleGame, hero)) {
							this.doSaveGamePicksBans(scheduleGame, oGamePicksBans, banJson, hero, team.getId());
						}
					}
				}
			}
		}
	}
	
	private void doSaveGamePicksBans(ScheduleGame scheduleGame, GamePicksBans gamePicksBans, BanJson banJson, Hero hero, Long teamId) {
		try {
			logger.debug("save GamePicksBans by banJson : {} ", banJson);
			if(gamePicksBans == null) {
				gamePicksBans = new GamePicksBans();
				gamePicksBans.setGameType(GameTypeEnum.LOL);
			}
			gamePicksBans.setHero(hero);
			gamePicksBans.setScheduleGame(scheduleGame);
			gamePicksBans.setTeamId(teamId);
			gamePicksBans.setOrder(banJson.getPickTurn());
			gamePicksBans.setIsPick(Boolean.FALSE);
			gamePicksBans = this.gamePicksBansService.save(gamePicksBans);
			logger.debug("saveGamePicksBans success : {} ", gamePicksBans);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	private ScheduleGameDetail doSaveScheduleGameDetail(ScheduleGame scheduleGame, Team team, ScheduleGameDetail scheduleGameDetail, TeamJson teamJson, String position) {
		try {
			if(scheduleGameDetail == null) {
				scheduleGameDetail = new ScheduleGameDetail();
				scheduleGameDetail.setGuessCount(0L);
			}
			scheduleGameDetail.setTeam(team);
			scheduleGameDetail.setScheduleGame(scheduleGame);
			scheduleGameDetail.setPosition(position);
			if(teamJson!=null) {
				logger.debug("save ScheduleGameDetail by teamJson : {} ", teamJson);
				scheduleGameDetail.setWin(GameWinTypeEnum.transform(teamJson.getWin()));
				scheduleGameDetail.setFirstBlood(teamJson.getFirstBlood());
				scheduleGameDetail.setFirstTower(teamJson.getFirstTower());
				scheduleGameDetail.setFirstInhibitor(teamJson.getFirstInhibitor());
				scheduleGameDetail.setFirstBaron(teamJson.getFirstBaron());
				scheduleGameDetail.setFirstDragon(teamJson.getFirstDragon());
				scheduleGameDetail.setTowerKills(teamJson.getTowerKills());
				scheduleGameDetail.setInhibitorKills(teamJson.getInhibitorKills());
				scheduleGameDetail.setBaronKills(teamJson.getBaronKills());
				scheduleGameDetail.setDragonKills(teamJson.getDragonKills());
				scheduleGameDetail.setVilemawKills(teamJson.getVilemawKills());
				scheduleGameDetail.setDominionVictoryScore(teamJson.getDominionVictoryScore());
			} else {
				logger.debug("save ScheduleGameDetail by teamJson is null");
			}
			scheduleGameDetail = this.scheduleGameDetailService.save(scheduleGameDetail);
			return scheduleGameDetail;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	private void doSaveScheduleGamePlayerDetail(ScheduleGamePlayerDetail scheduleGamePlayerDetail, ScheduleGame scheduleGame, ScheduleGameDetail gameDetail, Player player, ParticipantJson participantJson) {
		try {
			logger.debug("save ScheduleGamePlayerDetail by participantJson : {} ", participantJson);
			if(scheduleGamePlayerDetail == null) {
				scheduleGamePlayerDetail = new ScheduleGamePlayerDetail();
			}
			scheduleGamePlayerDetail.setScheduleGame(scheduleGame);
			scheduleGamePlayerDetail.setScheduleGameDetail(gameDetail);
			scheduleGamePlayerDetail.setPlayer(player);
			if(participantJson != null) {
				scheduleGamePlayerDetail.setKda(participantJson.getKda());
				StatsJson statsJson = participantJson.getStats();
				if(statsJson != null) {
					scheduleGamePlayerDetail.setKills(statsJson.getKills());
					scheduleGamePlayerDetail.setDeaths(statsJson.getDeaths());
					scheduleGamePlayerDetail.setAssists(statsJson.getAssists());
					scheduleGamePlayerDetail.setLargestKillingSpree(statsJson.getLargestKillingSpree());
					scheduleGamePlayerDetail.setLargestMultiKill(statsJson.getLargestMultiKill());
					scheduleGamePlayerDetail.setKillingSprees(statsJson.getKillingSprees());
					scheduleGamePlayerDetail.setLongestTimeSpentLiving(statsJson.getLongestTimeSpentLiving());
					scheduleGamePlayerDetail.setDoubleKills(statsJson.getDoubleKills());
					scheduleGamePlayerDetail.setTripleKills(statsJson.getTripleKills());
					scheduleGamePlayerDetail.setQuadraKills(statsJson.getQuadraKills());
					scheduleGamePlayerDetail.setPentaKills(statsJson.getPentaKills());
					scheduleGamePlayerDetail.setUnrealKills(statsJson.getUnrealKills());
					scheduleGamePlayerDetail.setTotalDamageDealt(statsJson.getTotalDamageDealt());
					scheduleGamePlayerDetail.setMagicDamageDealt(statsJson.getMagicDamageDealt());
					scheduleGamePlayerDetail.setPhysicalDamageDealt(statsJson.getPhysicalDamageDealt());
					scheduleGamePlayerDetail.setTrueDamageDealt(statsJson.getTrueDamageDealt());
					scheduleGamePlayerDetail.setLargestCriticalStrike(statsJson.getLargestCriticalStrike());
					scheduleGamePlayerDetail.setTotalDamageDealtToChampions(statsJson.getTotalDamageDealtToChampions());
					scheduleGamePlayerDetail.setMagicDamageDealtToChampions(statsJson.getMagicDamageDealtToChampions());
					scheduleGamePlayerDetail.setPhysicalDamageDealtToChampions(statsJson.getPhysicalDamageDealtToChampions());
					scheduleGamePlayerDetail.setTrueDamageDealtToChampions(statsJson.getTrueDamageDealtToChampions());
					scheduleGamePlayerDetail.setTotalHeal(statsJson.getTotalHeal());
					scheduleGamePlayerDetail.setTotalUnitsHealed(statsJson.getTotalUnitsHealed());
					scheduleGamePlayerDetail.setTotalDamageTaken(statsJson.getTotalDamageTaken());
					scheduleGamePlayerDetail.setMagicalDamageTaken(statsJson.getMagicalDamageTaken());
					scheduleGamePlayerDetail.setPhysicalDamageTaken(statsJson.getPhysicalDamageTaken());
					scheduleGamePlayerDetail.setTrueDamageTaken(statsJson.getTrueDamageTaken());
					scheduleGamePlayerDetail.setGoldSpent(statsJson.getGoldSpent());
					scheduleGamePlayerDetail.setGoldEarned(statsJson.getGoldEarned());
					scheduleGamePlayerDetail.setTurretKills(statsJson.getTurretKills());
					scheduleGamePlayerDetail.setInhibitorKills(statsJson.getInhibitorKills());
					scheduleGamePlayerDetail.setTotalMinionsKilled(statsJson.getTotalMinionsKilled());
					scheduleGamePlayerDetail.setNeutralMinionsKilled(statsJson.getNeutralMinionsKilled());
					scheduleGamePlayerDetail.setNeutralMinionsKilledTeamJungle(statsJson.getNeutralMinionsKilledTeamJungle());
					scheduleGamePlayerDetail.setNeutralMinionsKilledEnemyJungle(statsJson.getNeutralMinionsKilledEnemyJungle());
					scheduleGamePlayerDetail.setTotalTimeCrowdControlDealt(statsJson.getTotalTimeCrowdControlDealt());
					scheduleGamePlayerDetail.setChampLevel(statsJson.getChampLevel());
					scheduleGamePlayerDetail.setVisionWardsBoughtInGame(statsJson.getVisionWardsBoughtInGame());
					scheduleGamePlayerDetail.setSightWardsBoughtInGame(statsJson.getSightWardsBoughtInGame());
					scheduleGamePlayerDetail.setWardsPlaced(statsJson.getWardsPlaced());
					scheduleGamePlayerDetail.setWardsKilled(statsJson.getWardsKilled());
					scheduleGamePlayerDetail.setFirstBloodKill(statsJson.getFirstBloodKill());
					scheduleGamePlayerDetail.setFirstBloodAssist(statsJson.getFirstBloodAssist());
					scheduleGamePlayerDetail.setFirstTowerKill(statsJson.getFirstTowerKill());
					scheduleGamePlayerDetail.setFirstTowerAssist(statsJson.getFirstTowerAssist());
					scheduleGamePlayerDetail.setFirstInhibitorKill(statsJson.getFirstInhibitorKill());
					scheduleGamePlayerDetail.setFirstInhibitorAssist(statsJson.getFirstInhibitorAssist());
					scheduleGamePlayerDetail.setCombatPlayerScore(statsJson.getCombatPlayerScore());
					scheduleGamePlayerDetail.setObjectivePlayerScore(statsJson.getObjectivePlayerScore());
					scheduleGamePlayerDetail.setTotalPlayerScore(statsJson.getTotalPlayerScore());
					scheduleGamePlayerDetail.setTotalScoreRank(statsJson.getTotalScoreRank());
					TimelineJson timelineJson = participantJson.getTimeline();
					if(timelineJson != null) {
						scheduleGamePlayerDetail.setRole(timelineJson.getRole());
						scheduleGamePlayerDetail.setLane(timelineJson.getLane());
					}
				}
			}
			scheduleGamePlayerDetail = this.scheduleGamePlayerDetailService.save(scheduleGamePlayerDetail);
			logger.debug("saveScheduleGamePlayerDetail success {} : ", scheduleGamePlayerDetail);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	private void doSaveScheduleGamePlayerItems(ScheduleGame scheduleGame, Player player, ParticipantJson participantJson) {
		List<ScheduleGamePlayerItems> scheduleGamePlayerItems = this.scheduleGamePlayerItemsService.findByScheduleGameAndPlayer(scheduleGame, player);
		List<LoLItem> list = participantJson.getItemList();
		for(LoLItem item : list) {
			ScheduleGamePlayerItems oScheduleGamePlayerItems = null;
			for(ScheduleGamePlayerItems playerItem:scheduleGamePlayerItems) {
				if(item.equals(playerItem)) {
					oScheduleGamePlayerItems = playerItem;
					break;
				}
			}
			
			if(oScheduleGamePlayerItems == null || !oScheduleGamePlayerItems.equals(item, scheduleGame.getApiId(), player.getApiId())) {
				this.doSaveScheduleGamePlayerItems(scheduleGame, player, item, oScheduleGamePlayerItems);		
			}
		}
	}
	
	private void doSaveScheduleGamePlayerItems(ScheduleGame scheduleGame, Player player, LoLItem item, ScheduleGamePlayerItems scheduleGamePlayerItems) {
		try {
			if(scheduleGamePlayerItems == null) {
				scheduleGamePlayerItems = new ScheduleGamePlayerItems();
			}
			scheduleGamePlayerItems.setItemId(item.getItemId());
			scheduleGamePlayerItems.setItemType(item.getItemType());
			scheduleGamePlayerItems.setScheduleGame(scheduleGame);
			scheduleGamePlayerItems.setPlayer(player);
			scheduleGamePlayerItems.setSequence(item.getSequence());
			scheduleGamePlayerItems = this.scheduleGamePlayerItemsService.save(scheduleGamePlayerItems);
			logger.debug("saveScheduleGamePlayerItems success : {} ", scheduleGamePlayerItems);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	/**
	 * 根據傳入的json物件取得該json的id集合
	 * 
	 * @param jsonObjs
	 * @return
	 */
	private <T> List<Long> getJsonIds(List<T> jsonObjs) {
		List<Long> ids = new ArrayList<>(jsonObjs.size());
		for(T jsonObj : jsonObjs) {
			if(jsonObj instanceof ScheduleJson) {
				ScheduleJson json = (ScheduleJson)jsonObj;
				ids.add(json.getMatchId());
			} else if(jsonObj instanceof GameDetailJson) {
				GameDetailJson json = (GameDetailJson)jsonObj;
				ids.add(json.getId());
			}
		}
		return ids;
	}
	
	/**
	 * 根據傳入的json物件取得該json的id集合
	 * 
	 * @param jsonObjs
	 * @return
	 */
	private <T> List<Long> getJsonIds(Map<String, T> jsonObjs) {
		List<Long> ids = new ArrayList<>(jsonObjs.size());
		for (Map.Entry<String, T> entry : jsonObjs.entrySet()) {
			if(entry.getValue() instanceof GameTeamJson) {
				GameTeamJson gameTeamJson = (GameTeamJson)entry.getValue();
				if(gameTeamJson.getId() != null) {
					ids.add(gameTeamJson.getId());
				}
			} else if(entry.getValue() instanceof TeamPlayerJson) {
				TeamPlayerJson teamPlayerJson = (TeamPlayerJson)entry.getValue();
				ids.add(teamPlayerJson.getPlayerId());
			} else if(entry.getValue() instanceof GamePlayerJson) {
				GamePlayerJson gamePlayerJson = (GamePlayerJson)entry.getValue();
				ids.add(gamePlayerJson.getId());
			}
		}
		return ids;
	}
	
	/**
	 * 利用playerId取得TeamMember
	 * 
	 * @param teammembers
	 * @param name
	 * @return
	 */
	private TeamMember getTeamMemberByPlayerId(List<TeamMember> teammembers, Long playerId) {
		if(playerId!=null && !CollectionUtils.isEmpty(teammembers)) {
			for(TeamMember teamMember : teammembers) {
				if(teamMember.getPlayer()!=null && teamMember.getPlayer().getId().equals(playerId)) {
					return teamMember;
				}
			}
		}
		return null;
	}
	
	/**
	 * 因為從legurl取出的team資料中的teamid並非team的apiid 因此利用底下的play進行比對
	 * 最後再將team的apiid塞進legurl的teamjson之中
	 * 
	 * @param players
	 * @param legsUrlJson
	 * @return
	 */
	private void compareLegTeamsJson(Map<String, GamePlayerJson> players, List<ParticipantJson> participants, TeamJson teamJson) {
		if(teamJson == null || CollectionUtils.isEmpty(players) || CollectionUtils.isEmpty(participants)) {
			return;
		}
		for(ParticipantJson participantJson : participants) {
			if(teamJson.getTeamId().equals(participantJson.getTeamId())) {
				for (Map.Entry<String, GamePlayerJson> entry : players.entrySet()) {
					GamePlayerJson gamePlayerJson = entry.getValue();
					if(StringUtils.equals(String.valueOf(participantJson.getChampionId()), gamePlayerJson.getChampionId())) {
						teamJson.setTeamApiId(gamePlayerJson.getTeamId());
						return;
					}
				}
			}
		}
	}
	
	/**
	 * 因為從legurl取出的player資料中的participantId並非player的apiid 因此利用底下的play進行比對
	 * 最後再將player的apiid塞進legurl的playerjson之中
	 * 
	 * @param players
	 * @param legsUrlJson
	 * @return
	 */
	private ParticipantJson compareLegPlayerJson(GamePlayerJson gamePlayerJson, LegsUrlJson legsUrlJson) {
		if(legsUrlJson != null) {
			List<ParticipantJson> participants = legsUrlJson.getParticipants();
			for(ParticipantJson participantJson : participants) {
				if(StringUtils.equals(String.valueOf(participantJson.getChampionId()), gamePlayerJson.getChampionId())) {
					participantJson.setPlayerApiId(gamePlayerJson.getId());
					participantJson.setKda(gamePlayerJson.getKda());
					return participantJson;
				}
			}
		}
		return null;
	}
	
	/**
	 * 用GamePicksBans的order作為map的key
	 * 
	 * @param gamePicksBans
	 * @return
	 */
	private Map<Integer, GamePicksBans> getGamePickBansMap(List<GamePicksBans> gamePicksBans) {
		Map<Integer, GamePicksBans> map = new HashMap<>();
		for(GamePicksBans g : gamePicksBans) {
			MapUtils.safeAddToMap(map, g.getOrder(), g);
		}
		return map;
	}
	
	/**
	 * 
	 * @param list
	 * @return
	 */
	private Map<Long, Map<Long, ScheduleGamePlayerDetail>> getScheduleGamePlayerDetailMap(List<ScheduleGamePlayerDetail> list) {
		Map<Long, Map<Long, ScheduleGamePlayerDetail>> map = new HashMap<>();
		for(ScheduleGamePlayerDetail detail : list) {
			Map<Long, ScheduleGamePlayerDetail> detailMap = map.get(detail.getScheduleGame().getApiId()); 
			if(detailMap == null) {
				detailMap = new HashMap<Long, ScheduleGamePlayerDetail>();
				map.put(detail.getScheduleGame().getApiId(), detailMap);
			}
			detailMap.put(detail.getPlayer().getApiId(), detail);
		}
		return map;
	}
}
