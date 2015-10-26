package com.hkesports.matchticker.service;

import java.util.List;

import com.hkesports.matchticker.enums.GameTypeEnum;
import com.hkesports.matchticker.model.batchJob.Team;
import com.hkesports.matchticker.model.batchJob.TeamMember;
import com.hkesports.matchticker.model.batchJob.Tourament;

public interface TeamMemberService extends BasicService<TeamMember> {

	public List<TeamMember> findByTournamentAndTeam(Tourament tourament, Team team);
	
	public List<TeamMember> findByTournamentAndGameTypeAndApiIds(Tourament tourament, GameTypeEnum gameType, List<Long> apiIds);
}
