package com.hkesports.matchticker.test.service;

import javax.annotation.Resource;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.hkesports.matchticker.service.GameScoreService;
import com.hkesports.matchticker.service.ScheduleService;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("development")
@ContextConfiguration(locations = {"/spring-config.xml" })
@WebAppConfiguration
public class GameScoreTest {
	
	@Resource
	private GameScoreService gameScoreService;
	
	@Resource
	private ScheduleService scheduleService;
	
	@Test
	@Ignore
	public void testTeamGameScore() throws Exception {
		gameScoreService.insertTeamGameScore();
		gameScoreService.updateTeamGameScore();
	}
	
	@Test
	@Ignore
	public void testTeamGameMonthlyScore() throws Exception {
		gameScoreService.insertTeamGameMonthlyScore();
		gameScoreService.updateTeamGameMonthlyScore();
	}
	
	@Test
	@Ignore
	public void testTouramentGameTeamScore() throws Exception {
		gameScoreService.insertTouramentGameTeamScore();
		gameScoreService.updateTouramentGameTeamScore();
	}
	
	@Test
	@Ignore
	public void testTouramentGameTeamMonthlyScore() throws Exception {
		gameScoreService.insertTouramentGameTeamMonthlyScore();
		gameScoreService.updateTouramentGameTeamMonthlyScore();
	}
	
	@Test
	@Ignore
	public void testTeamContinentGameScore() throws Exception {
		gameScoreService.insertTeamContinentGameScore();
		gameScoreService.updateTeamContinentGameScore();
	}
	
	@Test
	@Ignore
	public void testTeamContinentGameMonthlyScore() throws Exception {
		gameScoreService.insertTeamContinentGameMonthlyScore();
		gameScoreService.updateTeamContinentGameMonthlyScore();
	}
	
	@Test
	@Ignore
	public void testDoSaveScheduleStatus() throws Exception {
		scheduleService.doSaveScheduleStatus();
	}
}
