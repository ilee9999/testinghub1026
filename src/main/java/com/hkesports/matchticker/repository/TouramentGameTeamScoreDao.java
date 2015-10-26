package com.hkesports.matchticker.repository;

import com.hkesports.matchticker.model.TouramentGameTeamScore;
import com.hkesports.matchticker.repository.custom.TouramentGameTeamScoreDaoCustom;
import com.hkesports.matchticker.repository.factory.GenericRepository;

public interface TouramentGameTeamScoreDao extends GenericRepository<TouramentGameTeamScore, Long>, TouramentGameTeamScoreDaoCustom {

}
