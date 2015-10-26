package com.hkesports.matchticker.service.impl;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.hkesports.matchticker.repository.TeamContinentGameMonthlyScoreDao;
import com.hkesports.matchticker.repository.TeamContinentGameScoreDao;
import com.hkesports.matchticker.repository.TeamGameMonthlyScoreDao;
import com.hkesports.matchticker.repository.TeamGameScoreDao;
import com.hkesports.matchticker.repository.TouramentGameTeamMonthlyScoreDao;
import com.hkesports.matchticker.repository.TouramentGameTeamScoreDao;
import com.hkesports.matchticker.service.GameScoreService;

@Service("gameScoreService")
@Transactional
public class GameScoreServiceImpl implements GameScoreService {
	
	@Resource
	private TeamGameScoreDao teamGameScoreDao; 
	
	@Resource
	private TeamGameMonthlyScoreDao teamGameMonthlyScoreDao;
	
	@Resource
	private TouramentGameTeamScoreDao touramentGameTeamScoreDao;
	
	@Resource
	private TouramentGameTeamMonthlyScoreDao touramentGameTeamMonthlyScoreDao;
	
	@Resource
	private TeamContinentGameScoreDao teamContinentGameScoreDao;
	
	@Resource
	private TeamContinentGameMonthlyScoreDao teamContinentGameMonthlyScoreDaoCustom;
	
	private static final Logger logger = LoggerFactory.getLogger(GameScoreServiceImpl.class); 
	
	@Override
	public void insertTeamGameScore() throws Exception {
		try {
			int count = teamGameScoreDao.batchInsertTeamGameScore();
			logger.info("insert TeamGameScore, count:{}", count);
		} catch (Exception e) {
			logger.error("batchInsertTeamGameScore error!!", e);
			throw e;
		}
	}
	
	@Override
	public void updateTeamGameScore() throws Exception {
		try {
			int count = teamGameScoreDao.batchUpdateTeamGameScore();
			logger.info("update TeamGameScore, count:{}", count);
		} catch (Exception e) {
			logger.error("batchUpdateTeamGameScore error!!", e);
			throw e;
		}
	}
	
	@Override
	public void insertTeamGameMonthlyScore() throws Exception {
		try {
			int count = teamGameMonthlyScoreDao.batchInsertTeamGameMonthlyScore();
			logger.info("insert TeamGameMonthlyScore, count:{}", count);
			
			int count2 = teamGameMonthlyScoreDao.batchUpdateTeamGameMonthlyRank();
			logger.info("insert TeamGameMonthlyScore rank, count:{}", count2);
		} catch (Exception e) {
			logger.error("batchInsertTeamGameMonthlyScore error!!", e);
			throw e;
		}
	}
	
	@Override
	public void updateTeamGameMonthlyScore() throws Exception {
		try {
			int count = teamGameMonthlyScoreDao.batchUpdateTeamGameMonthlyScore();
			logger.info("update TeamGameMonthlyScore, count:{}", count);
			
			int count2 = teamGameMonthlyScoreDao.batchUpdateTeamGameMonthlyRank();
			logger.info("update TeamGameMonthlyScore rank, count:{}", count2);
		} catch (Exception e) {
			logger.error("batchUpdateTeamGameMonthlyScore error!!", e);
			throw e;
		}
	}
	
	@Override
	public void insertTouramentGameTeamScore() throws Exception {
		try {
			int count = touramentGameTeamScoreDao.batchInsertTouramentGameTeamScore();
			logger.info("insert TouramentGameTeamScore, count:{}", count);
		} catch (Exception e) {
			logger.error("insertTouramentGameTeamScore error!!", e);
			throw e;
		}
	}
	
	@Override
	public void updateTouramentGameTeamScore() throws Exception {
		try {
			int count = touramentGameTeamScoreDao.batchUpdateTouramentGameTeamScore();
			logger.info("update TouramentGameTeamScore, count:{}", count);
		} catch (Exception e) {
			logger.error("updateTouramentGameTeamScore error!!", e);
			throw e;
		}
	}
	
	@Override
	public void insertTouramentGameTeamMonthlyScore() throws Exception {
		try {
			int count = touramentGameTeamMonthlyScoreDao.batchInsertTouramentGameTeamMonthlyScore();
			logger.info("insert TouramentGameTeamMonthlyScore, count:{}", count);
			
			int count2 = touramentGameTeamMonthlyScoreDao.batchUpdateTouramentGameTeamMonthlyRank();
			logger.info("insert TouramentGameTeamMonthlyScore rank, count:{}", count2);
		} catch (Exception e) {
			logger.error("batchInsertTouramentGameTeamMonthlyScore error!!", e);
			throw e;
		}
	}
	
	@Override
	public void updateTouramentGameTeamMonthlyScore() throws Exception {
		try {
			int count = touramentGameTeamMonthlyScoreDao.batchUpdateTouramentGameTeamMonthlyScore();
			logger.info("update TouramentGameTeamMonthlyScore, count:{}", count);
			
			int count2 = touramentGameTeamMonthlyScoreDao.batchUpdateTouramentGameTeamMonthlyRank();
			logger.info("update TouramentGameTeamMonthlyScore rank, count:{}", count2);
		} catch (Exception e) {
			logger.error("batchUpdateTouramentGameTeamMonthlyScore error!!", e);
			throw e;
		}
	}

	
	@Override
	public void insertTeamContinentGameScore() throws Exception {
		try {
			int count = teamContinentGameScoreDao.batchInsertTeamContinentGameScore();
			logger.info("insert TeamContinentGameScore, count:{}", count);
		} catch (Exception e) {
			logger.error("insertTeamContinentGameScore error!!", e);
			throw e;
		}
	}
	
	@Override
	public void updateTeamContinentGameScore() throws Exception {
		try {
			int count = teamContinentGameScoreDao.batchUpdateTeamContinentGameScore();
			logger.info("update TeamContinentGameScore, count:{}", count);
		} catch (Exception e) {
			logger.error("updateTeamContinentGameScore error!!", e);
			throw e;
		}
	}
	
	@Override
	public void insertTeamContinentGameMonthlyScore() throws Exception {
		try {
			int count = teamContinentGameMonthlyScoreDaoCustom.batchInsertTeamContinentGameMonthlyScore();
			logger.info("insert TeamContinentGameMonthlyScore, count:{}", count);
			
			int count2 = teamContinentGameMonthlyScoreDaoCustom.batchUpdateTeamContinentGameMonthlyRank();
			logger.info("update TeamContinentGameMonthlyScore rank, count:{}", count2);
		} catch (Exception e) {
			logger.error("batchInsertTeamContinentGameMonthlyScore error!!", e);
			throw e;
		}
	}
	
	@Override
	public void updateTeamContinentGameMonthlyScore() throws Exception {
		try {
			int count = teamContinentGameMonthlyScoreDaoCustom.batchUpdateTeamContinentGameMonthlyScore();
			logger.info("update TeamContinentGameMonthlyScore, count:{}", count);
			
			int count2 = teamContinentGameMonthlyScoreDaoCustom.batchUpdateTeamContinentGameMonthlyRank();
			logger.info("update TeamContinentGameMonthlyScore rank, count:{}", count2);
		} catch (Exception e) {
			logger.error("batchUpdateTeamContinentGameMonthlyScore error!!", e);
			throw e;
		}
	}
}
