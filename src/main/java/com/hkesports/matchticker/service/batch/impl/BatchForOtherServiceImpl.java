package com.hkesports.matchticker.service.batch.impl;

import org.springframework.stereotype.Service;

import com.hkesports.matchticker.service.ScheduleService;
import com.hkesports.matchticker.service.batch.BatchForOtherService;

@Service("batchForOtherService")
public class BatchForOtherServiceImpl implements BatchForOtherService {

	private ScheduleService scheduleService;
	
	@Override
	public void batchSaveScheduleStatus() throws Exception {
		scheduleService.doSaveScheduleStatus();
	}
	
}
