package com.hkesports.matchticker.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.hkesports.matchticker.enums.GameTypeEnum;
import com.hkesports.matchticker.model.batchJob.ScheduleGame;
import com.hkesports.matchticker.model.batchJob.ScheduleGameDetail;
import com.hkesports.matchticker.model.batchJob.Team;
import com.hkesports.matchticker.repository.ScheduleGameDetailDao;
import com.hkesports.matchticker.repository.factory.GenericRepository;
import com.hkesports.matchticker.service.ScheduleGameDetailService;

@Transactional
@Service("scheduleGameDetailService")
public class ScheduleGameDetailServiceImpl extends BasicServiceImpl<ScheduleGameDetail> implements ScheduleGameDetailService {
	@Resource
	private ScheduleGameDetailDao scheduleGameDetailDao;

	@Override
	protected GenericRepository<ScheduleGameDetail, Long> getDao() {
		return scheduleGameDetailDao;
	}

	@Deprecated
	@Override
	@Transactional(readOnly=true)
	public List<ScheduleGameDetail> findDota2TmpAndSubData(List<Long> apiIds, String procId) {
		return scheduleGameDetailDao.findFromTmpApiId(GameTypeEnum.DOTA2, "match", procId);
	}
	
	@Override
	public ScheduleGameDetail findByScheduleGameAndTeam(ScheduleGame scheduleGame, Team team) {
		return scheduleGameDetailDao.findByScheduleGameAndTeam(scheduleGame, team);
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<ScheduleGameDetail> findAllByGameTypeAndApiIds(GameTypeEnum gameType, List<Long> apiIds) {
		if(CollectionUtils.isEmpty(apiIds)) {
			return new ArrayList<>(0);
		}
		int size = apiIds.size();
		List<ScheduleGameDetail> resultList = new ArrayList<>(size);
		for(int i=0 ; i < apiIds.size(); i+=MAX_IN_LEN) {
			List<Long> subList = apiIds.subList(i, (i+MAX_IN_LEN) > size ? size : i+MAX_IN_LEN);
			if(!CollectionUtils.isEmpty(subList)) {
				resultList.addAll(scheduleGameDetailDao.findAllByScheduleGameApiIds(gameType, subList));
			}
		}
		return resultList;
	}
}
