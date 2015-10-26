package com.hkesports.matchticker.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.hkesports.matchticker.enums.GameTypeEnum;
import com.hkesports.matchticker.model.batchJob.Player;
import com.hkesports.matchticker.model.batchJob.ScheduleGame;
import com.hkesports.matchticker.model.batchJob.ScheduleGamePlayerItems;
import com.hkesports.matchticker.repository.ScheduleGamePlayerItemsDao;
import com.hkesports.matchticker.repository.factory.GenericRepository;
import com.hkesports.matchticker.service.ScheduleGamePlayerItemsService;

@Transactional
@Service("scheduleGamePlayerItemsService")
public class ScheduleGamePlayerItemsServiceImpl extends BasicServiceImpl<ScheduleGamePlayerItems> implements ScheduleGamePlayerItemsService {

	@Resource
	private ScheduleGamePlayerItemsDao scheduleGamePlayerItemsDao;
	
	@Override
	protected GenericRepository<ScheduleGamePlayerItems, Long> getDao() {
		return scheduleGamePlayerItemsDao;
	}

	@Deprecated
	@Override
	@Transactional(readOnly=true)
	public List<ScheduleGamePlayerItems> findDota2TmpAndSubData(List<Long> apiIds, String procId) {
		return scheduleGamePlayerItemsDao.findFromTmpApiId(GameTypeEnum.DOTA2, "match", procId);
	}
	
	@Override
	public List<ScheduleGamePlayerItems> findByScheduleGameAndPlayer(ScheduleGame scheduleGame, Player player) {
		return scheduleGamePlayerItemsDao.findByScheduleGameAndPlayer(scheduleGame, player);
	}

	@Override
	@Transactional(readOnly=true)
	public List<ScheduleGamePlayerItems> findAllByGameTypeAndApiIds(GameTypeEnum gameType, List<Long> apiIds) {
		if(CollectionUtils.isEmpty(apiIds)) {
			return new ArrayList<>(0);
		}
		int size = apiIds.size();
		List<ScheduleGamePlayerItems> resultList = new ArrayList<>(size);
		for(int i=0 ; i < apiIds.size(); i+=MAX_IN_LEN) {
			List<Long> subList = apiIds.subList(i, (i+MAX_IN_LEN) > size ? size : i+MAX_IN_LEN);
			if(!CollectionUtils.isEmpty(subList)) {
				resultList.addAll(scheduleGamePlayerItemsDao.findAllByScheduleGameApiIds(gameType, subList));
			}
		}
		return resultList;
	}
}
