package com.hkesports.matchticker.repository;

import com.hkesports.matchticker.model.TeamContinentGameScore;
import com.hkesports.matchticker.repository.custom.TeamContinentGameScoreDaoCustom;
import com.hkesports.matchticker.repository.factory.GenericRepository;

public interface TeamContinentGameScoreDao extends GenericRepository<TeamContinentGameScore, Long>, TeamContinentGameScoreDaoCustom {

}
