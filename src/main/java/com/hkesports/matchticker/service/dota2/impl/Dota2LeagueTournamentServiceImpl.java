package com.hkesports.matchticker.service.dota2.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.google.gson.reflect.TypeToken;
import com.hkesports.matchticker.common.utils.JsoupUtils;
import com.hkesports.matchticker.common.utils.MessageUtils;
import com.hkesports.matchticker.config.PropertiesConfig;
import com.hkesports.matchticker.enums.GameTypeEnum;
import com.hkesports.matchticker.model.Game;
import com.hkesports.matchticker.model.batchJob.Tourament;
import com.hkesports.matchticker.model.batchJob.TournamentBatchjobTmp;
import com.hkesports.matchticker.model.json.dota2.leagueList.LeagueJson;
import com.hkesports.matchticker.model.json.dota2.leagueList.LeagueListJson;
import com.hkesports.matchticker.model.json.dota2.livegames.LiveGameJson;
import com.hkesports.matchticker.model.json.dota2.livegames.LiveGamesListJson;
import com.hkesports.matchticker.model.json.factory.JsonFactory;
import com.hkesports.matchticker.repository.TouramentDao;
import com.hkesports.matchticker.repository.TournamentBatchjobTmpDao;
import com.hkesports.matchticker.service.GameService;
import com.hkesports.matchticker.service.dota2.Dota2LeagueTournamentService;

@Service("dota2LeagueTournamentService")
public class Dota2LeagueTournamentServiceImpl implements Dota2LeagueTournamentService {
	private static final Log logger = LogFactory.getLog(Dota2LeagueTournamentServiceImpl.class);
	
	@Resource(name = "jsoupUtils")
	private JsoupUtils jsoupUtils;
	
	@Resource(name = "propertiesConfig")
	private PropertiesConfig propertiesConfig;
	
	@Resource(name = "touramentDao")
	private TouramentDao touramentDao;

	@Resource(name = "tournamentBatchjobTmpDao")
	private TournamentBatchjobTmpDao tournamentBatchjobTmpDao;
	
	@Resource
	private GameService gameService;
	
	@Override
	public void updateLeagueTournamentForApi() throws Exception {
		List<Tourament> touramentList = new ArrayList<>();
		mergeTouramentData(touramentList, getApiLeagueList());
		saveTourament(touramentList);
	}
	
	@Override
	public List<LeagueJson> getApiLeagueList() throws Exception {
		String url = propertiesConfig.getProperty("dota2.leagueList.url");
		LeagueListJson league = JsonFactory.fromJson(jsoupUtils.getJsonString(url), new TypeToken<LeagueListJson>() {}.getType());
		return league.getResult().getLeagues();
	}

	/**
	 * 執行 GetScheduledLeagueGames Api(coming up) & GetLiveLeagueGames Api 來產生資料
	 * @throws Exception
	 */
	public void saveScheduleLiveLeague() throws Exception {
		Set<Long> leagueSet = new HashSet<>();
		String url = propertiesConfig.getProperty("dota2.scheduleleagueList.url");
		LiveGamesListJson league = JsonFactory.fromJson(jsoupUtils.getJsonString(url), new TypeToken<LiveGamesListJson>() {}.getType());
		if(league!=null && league.getResult()!=null && !CollectionUtils.isEmpty(league.getResult().getGames())) {
			List<LiveGameJson> games = league.getResult().getGames();
			for(LiveGameJson game:games) {
				leagueSet.add(game.getLeague_id());
			}
		}
		
		String url2 = propertiesConfig.getProperty("dota2.liveleagueList.url");
		LiveGamesListJson league2 = JsonFactory.fromJson(jsoupUtils.getJsonString(url2), new TypeToken<LiveGamesListJson>() {}.getType());
		if(league2!=null && league2.getResult()!=null && !CollectionUtils.isEmpty(league2.getResult().getGames())) {
			List<LiveGameJson> games = league2.getResult().getGames();
			for(LiveGameJson game:games) {
				leagueSet.add(game.getLeague_id());
			}
		}
		
		if(!leagueSet.isEmpty()) {
			updateLeagueTournamentForApi();
			saveTournamentBatchjobTmp(leagueSet);
		}
	}
	
	@Transactional
	public void saveTournamentBatchjobTmp(Collection<Long> leagueIds) {
		if(CollectionUtils.isEmpty(leagueIds)) {
			return;
		}
		List<TournamentBatchjobTmp> leagueList = tournamentBatchjobTmpDao.findAllByGameTypeAndApiIds(GameTypeEnum.DOTA2, leagueIds);
		for(Long leagueId:leagueIds) {
			boolean flag = true;
			for(TournamentBatchjobTmp tmp:leagueList) {
				if(tmp.getApiId().equals(leagueId)) {
					flag = false;
					break;
				}
			}
			if(flag) {
				TournamentBatchjobTmp tmp = new TournamentBatchjobTmp();
				tmp.setApiId(leagueId);
				tmp.setGameType(GameTypeEnum.DOTA2);
				tournamentBatchjobTmpDao.save(tmp);
			}
		}
	}
	
	@Override
	public List<Tourament> findAllTourament() {
		return touramentDao.findByGameType(GameTypeEnum.DOTA2);
	}
	
	@Override
	public Map<Long, Tourament> findTouramentDataToMap() {
		List<Tourament> list = findAllTourament();
		Map<Long, Tourament> map = new HashMap<>();
		for(Tourament tourament : list)
			map.put(tourament.getApiId(), tourament);
		return map;
	}
	
	private void mergeTouramentData(List<Tourament> touramentList, List<LeagueJson> LeagueList) {
		Map<Long, Tourament> oldTouramentMap = findTouramentDataToMap();
		for(LeagueJson json : LeagueList) {
			Tourament oldTourament = oldTouramentMap.get(json.getLeagueid());
			if(oldTourament == null) {
				touramentList.add(createTouramentForJson(json));
			} else if(!oldTourament.equals(json))
				touramentList.add(updateTouramentForJson(oldTourament, json));
		}
	}
	
	private Tourament createTouramentForJson(LeagueJson json) {
		return updateTouramentForJson(new Tourament(), json);
	}
	
	private Tourament updateTouramentForJson(Tourament tourament, LeagueJson json) {
		tourament.setGameType(GameTypeEnum.DOTA2);
		tourament.setApiId(json.getLeagueid());
		tourament.setTouramentName(json.getName());
		tourament.setTouramentShortName(json.getName());
		tourament.setTouramentDescription(json.getDescription());
		tourament.setTouramentSiteUrl(json.getTournamentUrl());
		
		return tourament;
	}
	
	@Transactional
	private String saveTourament(List<Tourament> touramentList) {
		StringBuilder message = new StringBuilder("");
		
		for(Tourament tourament : touramentList) {
			try {
				touramentDao.save(tourament);
			} catch(DataAccessException e) {
				logger.error(e);
				MessageUtils.setApiSaveErrorMessage(message, GameTypeEnum.DOTA2, "League", tourament.getApiId(), e);
			}
		}
		
		return message.toString();
	}
}
