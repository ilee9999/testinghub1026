package com.hkesports.matchticker.service.batch;

/**
 * @author manboyu
 *
 */
public interface BatchForGameScoreService {

	public void batchInsertTeamGameScore() throws Exception;
	
	public void batchUpdateTeamGameScore() throws Exception;
	
	public void batchInsertTeamGameMonthlyScore() throws Exception;
	
	public void batchUpdateTeamGameMonthlyScore() throws Exception;
	
	public void batchInsertTouramentGameTeamScore() throws Exception;
	
	public void batchUpdateTouramentGameTeamScore() throws Exception;
	
	public void batchInsertTouramentGameTeamMonthlyScore() throws Exception;
	
	public void batchUpdateTouramentGameTeamMonthlyScore() throws Exception;
	
	public void batchInsertTeamContinentGameScore() throws Exception;
	
	public void batchUpdateTeamContinentGameScore() throws Exception;
	
	public void batchInsertTeamContinentGameMonthlyScore() throws Exception;
	
	public void batchUpdateTeamContinentGameMonthlyScore() throws Exception;
}
