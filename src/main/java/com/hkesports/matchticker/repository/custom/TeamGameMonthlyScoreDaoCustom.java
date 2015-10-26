package com.hkesports.matchticker.repository.custom;

public interface TeamGameMonthlyScoreDaoCustom {
	
	public int batchInsertTeamGameMonthlyScore();
	
	public int batchUpdateTeamGameMonthlyScore();
	
	public int batchUpdateTeamGameMonthlyRank();
}
