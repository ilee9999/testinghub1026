package com.hkesports.matchticker.service;

import com.hkesports.matchticker.model.batchJob.Schedule;

public interface ScheduleService extends BasicService<Schedule> {
	
	public void doSaveScheduleResults(Schedule schedule);
	
	/**
	 * 批次更新 schedule status 預設ready=0, contestended=1, cancel=2
	 * 找尋比賽開始前?個小時(admin 設定)的schedule, 並將其他相同tournament之schedule status一併更改為contestended
	 */
	public void doSaveScheduleStatus();
}
