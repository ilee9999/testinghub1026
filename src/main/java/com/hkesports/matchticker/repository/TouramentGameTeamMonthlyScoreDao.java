package com.hkesports.matchticker.repository;

import com.hkesports.matchticker.model.TouramentGameTeamMonthlyScore;
import com.hkesports.matchticker.repository.custom.TouramentGameTeamMonthlyScoreDaoCustom;
import com.hkesports.matchticker.repository.factory.GenericRepository;

public interface TouramentGameTeamMonthlyScoreDao extends GenericRepository<TouramentGameTeamMonthlyScore, Long>, TouramentGameTeamMonthlyScoreDaoCustom {

}
