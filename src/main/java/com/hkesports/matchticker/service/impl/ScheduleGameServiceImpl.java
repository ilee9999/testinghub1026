package com.hkesports.matchticker.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hkesports.matchticker.model.batchJob.ScheduleGame;
import com.hkesports.matchticker.repository.ScheduleGameDao;
import com.hkesports.matchticker.repository.factory.GenericRepository;
import com.hkesports.matchticker.service.ScheduleGameService;

@Service("scheduleGameService")
public class ScheduleGameServiceImpl extends BasicServiceImpl<ScheduleGame> implements ScheduleGameService {

	@Resource
	private ScheduleGameDao scheduleGameDao;
	
	@Override
	protected GenericRepository<ScheduleGame, Long> getDao() {
		return scheduleGameDao;
	}

}
