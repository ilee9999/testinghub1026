package com.hkesports.matchticker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hkesports.matchticker.enums.GameTypeEnum;
import com.hkesports.matchticker.model.batchJob.Team;
import com.hkesports.matchticker.model.batchJob.TeamMember;
import com.hkesports.matchticker.model.batchJob.Tourament;
import com.hkesports.matchticker.repository.custom.TeamMemberDaoCustom;
import com.hkesports.matchticker.repository.factory.GenericRepository;

public interface TeamMemberDao extends TeamMemberDaoCustom, GenericRepository<TeamMember, Long> {

	public List<TeamMember> findByTouramentAndTeam(Tourament tourament, Team team);
	
	@Query(value = "select t from TeamMember t where t.team.gameType=:gameType and t.team.apiId in :apiIds ")
	public List<TeamMember> findAllByTeamApiIds(@Param("gameType") GameTypeEnum gameType, @Param("apiIds") List<Long> apiIds);
	
	@Query(value = "select t from TeamMember t where t.team.gameType=:gameType and t.tourament.id=:touramentId and t.team.apiId in :apiIds ")
	public List<TeamMember> findAllByTournamentTeamApiIds(@Param("gameType") GameTypeEnum gameType, @Param("touramentId") Long touramentId, @Param("apiIds") List<Long> apiIds);
}
