package com.hkesports.matchticker.repository;

import com.hkesports.matchticker.model.TeamContinentGameMonthlyScore;
import com.hkesports.matchticker.repository.custom.TeamContinentGameMonthlyScoreDaoCustom;
import com.hkesports.matchticker.repository.factory.GenericRepository;

public interface TeamContinentGameMonthlyScoreDao extends GenericRepository<TeamContinentGameMonthlyScore, Long>, TeamContinentGameMonthlyScoreDaoCustom {

}
