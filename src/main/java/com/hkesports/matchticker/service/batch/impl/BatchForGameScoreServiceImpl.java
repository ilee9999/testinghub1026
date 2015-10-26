package com.hkesports.matchticker.service.batch.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hkesports.matchticker.service.GameScoreService;
import com.hkesports.matchticker.service.batch.BatchForGameScoreService;

/**
 * @author manboyu
 *
 */
@Service("batchForGameScoreService")
public class BatchForGameScoreServiceImpl implements BatchForGameScoreService {

	@Resource(name = "gameScoreService")
	private GameScoreService gameScoreService;
	
	@Override
	public void batchInsertTeamGameScore() throws Exception {
		gameScoreService.insertTeamGameScore();
	}

	@Override
	public void batchUpdateTeamGameScore() throws Exception {
		gameScoreService.updateTeamGameScore();
	}

	@Override
	public void batchInsertTeamGameMonthlyScore() throws Exception {
		gameScoreService.insertTeamGameMonthlyScore();
	}

	@Override
	public void batchUpdateTeamGameMonthlyScore() throws Exception {
		gameScoreService.updateTeamGameMonthlyScore();
	}

	@Override
	public void batchInsertTouramentGameTeamScore() throws Exception {
		gameScoreService.insertTouramentGameTeamScore();
	}

	@Override
	public void batchUpdateTouramentGameTeamScore() throws Exception {
		gameScoreService.updateTouramentGameTeamScore();
	}

	@Override
	public void batchInsertTouramentGameTeamMonthlyScore() throws Exception {
		gameScoreService.insertTouramentGameTeamMonthlyScore();
	}

	@Override
	public void batchUpdateTouramentGameTeamMonthlyScore() throws Exception {
		gameScoreService.updateTouramentGameTeamMonthlyScore();
	}

	@Override
	public void batchInsertTeamContinentGameScore() throws Exception {
		gameScoreService.insertTeamContinentGameScore();
	}

	@Override
	public void batchUpdateTeamContinentGameScore() throws Exception {
		gameScoreService.updateTeamContinentGameScore();
	}

	@Override
	public void batchInsertTeamContinentGameMonthlyScore() throws Exception {
		gameScoreService.insertTeamContinentGameMonthlyScore();
	}

	@Override
	public void batchUpdateTeamContinentGameMonthlyScore() throws Exception {
		gameScoreService.updateTeamContinentGameMonthlyScore();
	}
}
