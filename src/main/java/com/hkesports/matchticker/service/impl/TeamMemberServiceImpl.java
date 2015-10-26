package com.hkesports.matchticker.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.hkesports.matchticker.enums.GameTypeEnum;
import com.hkesports.matchticker.model.batchJob.Team;
import com.hkesports.matchticker.model.batchJob.TeamMember;
import com.hkesports.matchticker.model.batchJob.Tourament;
import com.hkesports.matchticker.repository.TeamMemberDao;
import com.hkesports.matchticker.repository.factory.GenericRepository;
import com.hkesports.matchticker.service.TeamMemberService;

@Transactional
@Service("teamMemberService")
public class TeamMemberServiceImpl extends BasicServiceImpl<TeamMember> implements TeamMemberService {

	@Resource
	private TeamMemberDao teamMemberDao;
	
	@Override
	protected GenericRepository<TeamMember, Long> getDao() {
		return teamMemberDao;
	}

	@Override
	@Transactional(readOnly=true)
	public List<TeamMember> findAllByGameTypeAndApiIds(GameTypeEnum gameType, List<Long> apiIds) {
		if(CollectionUtils.isEmpty(apiIds)) {
			return new ArrayList<>(0);
		}
		int size = apiIds.size();
		List<TeamMember> resultList = new ArrayList<>(size);
		for(int i=0 ; i < apiIds.size(); i+=MAX_IN_LEN) {
			List<Long> subList = apiIds.subList(i, (i+MAX_IN_LEN) > size ? size : i+MAX_IN_LEN);
			if(!CollectionUtils.isEmpty(subList)) {
				resultList.addAll(teamMemberDao.findAllByTeamApiIds(gameType, subList));
			}
		}
		return resultList;
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<TeamMember> findByTournamentAndGameTypeAndApiIds(Tourament tourament, GameTypeEnum gameType, List<Long> apiIds) {
		if(CollectionUtils.isEmpty(apiIds)) {
			return new ArrayList<>(0);
		}
		int size = apiIds.size();
		List<TeamMember> resultList = new ArrayList<>(size);
		for(int i=0 ; i < apiIds.size(); i+=MAX_IN_LEN) {
			List<Long> subList = apiIds.subList(i, (i+MAX_IN_LEN) > size ? size : i+MAX_IN_LEN);
			if(!CollectionUtils.isEmpty(subList)) {
				resultList.addAll(teamMemberDao.findAllByTournamentTeamApiIds(gameType, tourament.getId(), subList));
			}
		}
		return resultList;
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<TeamMember> findByTournamentAndTeam(Tourament tourament, Team team) {
		return teamMemberDao.findByTouramentAndTeam(tourament, team);
	}
}
