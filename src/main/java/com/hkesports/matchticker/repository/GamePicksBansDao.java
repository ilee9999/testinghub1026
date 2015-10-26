package com.hkesports.matchticker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hkesports.matchticker.enums.GameTypeEnum;
import com.hkesports.matchticker.model.batchJob.GamePicksBans;
import com.hkesports.matchticker.model.batchJob.ScheduleGame;
import com.hkesports.matchticker.repository.custom.GamePicksBansDaoCustom;
import com.hkesports.matchticker.repository.factory.GenericRepository;

public interface GamePicksBansDao extends GamePicksBansDaoCustom, GenericRepository<GamePicksBans, Long> {

	public List<GamePicksBans> findByScheduleGame(ScheduleGame scheduleGame);
	
	public List<GamePicksBans> findByScheduleGameAndTeamId(ScheduleGame scheduleGame, Long teamId);
	
	@Query(value = "select t from GamePicksBans t where t.scheduleGame.gameType=:gameType and t.scheduleGame.apiId in :apiIds ")
	public List<GamePicksBans> findAllByScheduleGameApiIds(@Param("gameType") GameTypeEnum gameType, @Param("apiIds") List<Long> apiIds);
}
