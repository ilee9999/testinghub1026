package com.hkesports.matchticker.repository;

import com.hkesports.matchticker.model.TeamGameMonthlyScore;
import com.hkesports.matchticker.repository.custom.TeamGameMonthlyScoreDaoCustom;
import com.hkesports.matchticker.repository.factory.GenericRepository;

public interface TeamGameMonthlyScoreDao extends GenericRepository<TeamGameMonthlyScore, Long>, TeamGameMonthlyScoreDaoCustom {

}
