package com.hkesports.matchticker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hkesports.matchticker.enums.GameTypeEnum;
import com.hkesports.matchticker.model.batchJob.ScheduleGame;
import com.hkesports.matchticker.model.batchJob.ScheduleGameDetail;
import com.hkesports.matchticker.model.batchJob.Team;
import com.hkesports.matchticker.repository.custom.ScheduleGameDetailDaoCustom;
import com.hkesports.matchticker.repository.factory.GenericRepository;

public interface ScheduleGameDetailDao extends ScheduleGameDetailDaoCustom, GenericRepository<ScheduleGameDetail, Long> {

	public ScheduleGameDetail findByScheduleGameAndTeam(ScheduleGame scheduleGame, Team team);
	
	@Query(value = "select t from ScheduleGameDetail t where t.scheduleGame.gameType=:gameType and t.scheduleGame.apiId in :apiIds ")
	public List<ScheduleGameDetail> findAllByScheduleGameApiIds(@Param("gameType") GameTypeEnum gameType, @Param("apiIds") List<Long> apiIds);
}
