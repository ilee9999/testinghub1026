package com.hkesports.matchticker.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.hkesports.matchticker.enums.GameTypeEnum;
import com.hkesports.matchticker.model.batchJob.GamePicksBans;
import com.hkesports.matchticker.model.batchJob.ScheduleGame;
import com.hkesports.matchticker.repository.GamePicksBansDao;
import com.hkesports.matchticker.repository.factory.GenericRepository;
import com.hkesports.matchticker.service.GamePicksBansService;

@Transactional
@Service("gamePicksBansService")
public class GamePicksBansServiceImpl extends BasicServiceImpl<GamePicksBans> implements GamePicksBansService {

	@Resource(name = "gamePicksBansDao")
	private GamePicksBansDao gamePicksBansDao;
	
	@Override
	protected GenericRepository<GamePicksBans, Long> getDao() {
		return gamePicksBansDao;
	}

	@Deprecated
	@Override
	@Transactional(readOnly=true)
	public List<GamePicksBans> findDota2TmpAndSubData(List<Long> apiIds, String procId) {
		return gamePicksBansDao.findFromTmpApiId(GameTypeEnum.DOTA2, "match", procId);
	}
	
	@Override
	public List<GamePicksBans> findByScheduleGameAndTeamId(ScheduleGame scheduleGame, Long teamId) {
		return gamePicksBansDao.findByScheduleGameAndTeamId(scheduleGame, teamId);
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<GamePicksBans> findAllByGameTypeAndApiIds(GameTypeEnum gameType, List<Long> apiIds) {
		if(CollectionUtils.isEmpty(apiIds)) {
			return new ArrayList<>(0);
		}
		int size = apiIds.size();
		List<GamePicksBans> resultList = new ArrayList<>(size);
		for(int i=0 ; i < apiIds.size(); i+=MAX_IN_LEN) {
			List<Long> subList = apiIds.subList(i, (i+MAX_IN_LEN) > size ? size : i+MAX_IN_LEN);
			if(!CollectionUtils.isEmpty(subList)) {
				resultList.addAll(gamePicksBansDao.findAllByScheduleGameApiIds(gameType, subList));
			}
		}
		return resultList;
	}
	
}
