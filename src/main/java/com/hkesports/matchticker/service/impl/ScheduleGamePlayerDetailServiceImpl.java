package com.hkesports.matchticker.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.hkesports.matchticker.enums.GameTypeEnum;
import com.hkesports.matchticker.model.batchJob.ScheduleGamePlayerDetail;
import com.hkesports.matchticker.repository.ScheduleGamePlayerDetailDao;
import com.hkesports.matchticker.repository.factory.GenericRepository;
import com.hkesports.matchticker.service.ScheduleGamePlayerDetailService;

@Transactional
@Service("scheduleGamePlayerDetailService")
public class ScheduleGamePlayerDetailServiceImpl extends BasicServiceImpl<ScheduleGamePlayerDetail> implements ScheduleGamePlayerDetailService {

	@Resource
	private ScheduleGamePlayerDetailDao scheduleGamePlayerDetailDao;
	
	@Override
	protected GenericRepository<ScheduleGamePlayerDetail, Long> getDao() {
		return scheduleGamePlayerDetailDao;
	}

	@Deprecated
	@Override
	@Transactional(readOnly=true)
	public List<ScheduleGamePlayerDetail> findDota2TmpAndSubData(List<Long> apiIds, String procId) {
		return scheduleGamePlayerDetailDao.findFromTmpApiId(GameTypeEnum.DOTA2, "match", procId);
	}

	@Deprecated
	@Override
	@Transactional(readOnly=true)
	public List<ScheduleGamePlayerDetail> findLOLTmpAndSubData(List<Long> apiIds, String dataType, String procId) {
		return scheduleGamePlayerDetailDao.findFromTmpApiId(GameTypeEnum.LOL, dataType, procId);
	}

	@Override
	@Transactional(readOnly=true)
	public List<ScheduleGamePlayerDetail> findAllByGameTypeAndApiIds(GameTypeEnum gameType, List<Long> apiIds) {
		if(CollectionUtils.isEmpty(apiIds)) {
			return new ArrayList<>(0);
		}
		int size = apiIds.size();
		List<ScheduleGamePlayerDetail> resultList = new ArrayList<>(size);
		for(int i=0 ; i < apiIds.size(); i+=MAX_IN_LEN) {
			List<Long> subList = apiIds.subList(i, (i+MAX_IN_LEN) > size ? size : i+MAX_IN_LEN);
			if(!CollectionUtils.isEmpty(subList)) {
				resultList.addAll(scheduleGamePlayerDetailDao.findAllByScheduleGameApiIds(gameType, subList));
			}
		}
		return resultList;
	}
}
