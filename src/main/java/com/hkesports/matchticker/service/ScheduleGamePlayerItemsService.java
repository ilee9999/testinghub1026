package com.hkesports.matchticker.service;

import java.util.List;

import com.hkesports.matchticker.model.batchJob.Player;
import com.hkesports.matchticker.model.batchJob.ScheduleGame;
import com.hkesports.matchticker.model.batchJob.ScheduleGamePlayerItems;

public interface ScheduleGamePlayerItemsService extends BasicService<ScheduleGamePlayerItems> {
	
	public List<ScheduleGamePlayerItems> findDota2TmpAndSubData(List<Long> apiIds, String procId);
	
	public List<ScheduleGamePlayerItems> findByScheduleGameAndPlayer(ScheduleGame scheduleGame, Player player);
	
}
