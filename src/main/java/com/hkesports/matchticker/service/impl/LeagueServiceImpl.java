package com.hkesports.matchticker.service.impl;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.hkesports.matchticker.model.batchJob.League;
import com.hkesports.matchticker.repository.LeagueDao;
import com.hkesports.matchticker.repository.factory.GenericRepository;
import com.hkesports.matchticker.service.LeagueService;

@Service("leagueService")
@Transactional
public class LeagueServiceImpl extends BasicServiceImpl<League> implements LeagueService {

	@Resource
	private LeagueDao leagueDao;
	
	@Override
	protected GenericRepository<League, Long> getDao() {
		return leagueDao;
	}

}
