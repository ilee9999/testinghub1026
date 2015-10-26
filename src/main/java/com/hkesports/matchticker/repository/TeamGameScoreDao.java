package com.hkesports.matchticker.repository;

import com.hkesports.matchticker.model.TeamGameScore;
import com.hkesports.matchticker.repository.custom.TeamGameScoreDaoCustom;
import com.hkesports.matchticker.repository.factory.GenericRepository;

public interface TeamGameScoreDao extends GenericRepository<TeamGameScore, Long>, TeamGameScoreDaoCustom {

}
