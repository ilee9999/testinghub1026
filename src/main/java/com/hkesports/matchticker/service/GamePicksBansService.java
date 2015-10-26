package com.hkesports.matchticker.service;

import java.util.List;

import com.hkesports.matchticker.model.batchJob.GamePicksBans;
import com.hkesports.matchticker.model.batchJob.ScheduleGame;

public interface GamePicksBansService extends BasicService<GamePicksBans> {

	@Deprecated
	public List<GamePicksBans> findDota2TmpAndSubData(List<Long> apiIds, String procId);
	
	public List<GamePicksBans> findByScheduleGameAndTeamId(ScheduleGame scheduleGame, Long teamId);
}
