package com.hkesports.matchticker.service.batch;

public interface BatchForDota2Service {

	public void batchGetDota2Heros() throws Exception;
	
	public void batchGetDota2Items() throws Exception;
	
	public void batchGetDota2Abilities() throws Exception;

	public void batchGetDota2MatchData() throws Exception;
	
	public void batchSaveScheduleLiveLeague() throws Exception;
	
	public void batchUpdateMatchScheduleByBatchjobTmp() throws Exception;
}
