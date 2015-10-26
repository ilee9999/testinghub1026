package com.hkesports.matchticker.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hkesports.matchticker.model.batchJob.Team;
import com.hkesports.matchticker.repository.TeamDao;
import com.hkesports.matchticker.repository.factory.GenericRepository;
import com.hkesports.matchticker.service.TeamService;

@Transactional
@Service("teamService")
public class TeamServiceImpl extends BasicServiceImpl<Team> implements TeamService {

	@Resource
	private TeamDao teamDao;
	
	@Override
	protected GenericRepository<Team, Long> getDao() {
		return teamDao;
	}

}
