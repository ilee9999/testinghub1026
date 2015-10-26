package com.hkesports.matchticker.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.hkesports.matchticker.model.batchJob.LiveStreams;
import com.hkesports.matchticker.repository.factory.GenericRepository;

public interface LiveStreamsDao extends GenericRepository<LiveStreams, Long> {
	
//	public List<LiveStreams> findByLeague(League league);
	
//	public List<LiveStreams> findByScheduleGame(ScheduleGame scheduleGame);
	
//	public Long countByLeague(League league);

	@Transactional
	@Modifying
	@Query("delete from LiveStreams l where l.league.id=:leagueId ")
	public void deleteByLeague(@Param("leagueId") Long leagueId);
	
	@Transactional
	@Modifying
	@Query("delete from LiveStreams l where l.scheduleGame.id=:scheduleGameId ")
	public void deleteByScheduleGame(@Param("scheduleGameId") Long scheduleGameId);
}
