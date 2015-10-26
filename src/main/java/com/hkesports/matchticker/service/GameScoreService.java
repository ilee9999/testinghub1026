package com.hkesports.matchticker.service;

public interface GameScoreService {

	//TeamGameScore
	public void insertTeamGameScore() throws Exception;
	
	public void updateTeamGameScore() throws Exception;
	
	//TeamGameMonthlyScore
	public void insertTeamGameMonthlyScore() throws Exception;
	
	public void updateTeamGameMonthlyScore() throws Exception;
	
	//TouramentGameTeamScore
	public void insertTouramentGameTeamScore() throws Exception;
	
	public void updateTouramentGameTeamScore() throws Exception;
	
	//TouramentGameTeamMonthlyScore
	public void insertTouramentGameTeamMonthlyScore() throws Exception;
	
	public void updateTouramentGameTeamMonthlyScore() throws Exception;
	
	//TeamContinentGameScore
	public void insertTeamContinentGameScore() throws Exception;
	
	public void updateTeamContinentGameScore() throws Exception;
	
	//TeamContinentGameMonthlyScore
	public void insertTeamContinentGameMonthlyScore() throws Exception;
	
	public void updateTeamContinentGameMonthlyScore() throws Exception;
}
