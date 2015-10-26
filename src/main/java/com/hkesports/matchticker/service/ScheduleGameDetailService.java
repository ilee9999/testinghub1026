package com.hkesports.matchticker.service;

import java.util.List;

import com.hkesports.matchticker.model.batchJob.ScheduleGame;
import com.hkesports.matchticker.model.batchJob.ScheduleGameDetail;
import com.hkesports.matchticker.model.batchJob.Team;

public interface ScheduleGameDetailService extends BasicService<ScheduleGameDetail> {
	
	@Deprecated
	public List<ScheduleGameDetail> findDota2TmpAndSubData(List<Long> apiIds, String procId);
	
	public ScheduleGameDetail findByScheduleGameAndTeam(ScheduleGame scheduleGame, Team team);
}
