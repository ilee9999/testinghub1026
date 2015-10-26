package com.hkesports.matchticker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hkesports.matchticker.enums.GameTypeEnum;
import com.hkesports.matchticker.model.batchJob.Player;
import com.hkesports.matchticker.model.batchJob.ScheduleGamePlayerDetail;
import com.hkesports.matchticker.repository.custom.ScheduleGamePlayerDetailDaoCustom;
import com.hkesports.matchticker.repository.factory.GenericRepository;

public interface ScheduleGamePlayerDetailDao extends ScheduleGamePlayerDetailDaoCustom, GenericRepository<ScheduleGamePlayerDetail, Long> {

	public ScheduleGamePlayerDetail findByPlayer(Player player);
	
	@Query(value = "select t from ScheduleGamePlayerDetail t where t.scheduleGame.gameType=:gameType and t.scheduleGame.apiId in :apiIds ")
	public List<ScheduleGamePlayerDetail> findAllByScheduleGameApiIds(@Param("gameType") GameTypeEnum gameType, @Param("apiIds") List<Long> apiIds);
}
