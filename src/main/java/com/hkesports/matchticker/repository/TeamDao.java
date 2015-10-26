package com.hkesports.matchticker.repository;

import com.hkesports.matchticker.enums.GameTypeEnum;
import com.hkesports.matchticker.model.batchJob.Team;
import com.hkesports.matchticker.repository.custom.TeamDaoCustom;
import com.hkesports.matchticker.repository.factory.GenericRepository;

public interface TeamDao extends TeamDaoCustom, GenericRepository<Team, Long> {
	
	public <Q> Q findByApiIdAndGameTypeAndTeamName(Long apiId, GameTypeEnum gameType, String teamName);
}
