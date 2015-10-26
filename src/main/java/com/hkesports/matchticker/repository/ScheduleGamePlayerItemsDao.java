package com.hkesports.matchticker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hkesports.matchticker.enums.GameTypeEnum;
import com.hkesports.matchticker.model.batchJob.Player;
import com.hkesports.matchticker.model.batchJob.ScheduleGame;
import com.hkesports.matchticker.model.batchJob.ScheduleGamePlayerItems;
import com.hkesports.matchticker.repository.custom.ScheduleGamePlayerItemsDaoCustom;
import com.hkesports.matchticker.repository.factory.GenericRepository;

public interface ScheduleGamePlayerItemsDao extends ScheduleGamePlayerItemsDaoCustom, GenericRepository<ScheduleGamePlayerItems, Long> {

	public List<ScheduleGamePlayerItems> findByScheduleGameAndPlayer(ScheduleGame scheduleGame, Player player);

	@Query(value = "select t from ScheduleGamePlayerItems t where t.scheduleGame.gameType=:gameType and t.scheduleGame.apiId in :apiIds ")
	public List<ScheduleGamePlayerItems> findAllByScheduleGameApiIds(@Param("gameType") GameTypeEnum gameType, @Param("apiIds") List<Long> apiIds);
	
}
