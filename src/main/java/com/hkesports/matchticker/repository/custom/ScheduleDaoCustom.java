package com.hkesports.matchticker.repository.custom;

import java.util.List;

import com.hkesports.matchticker.vo.TeamScore;

public interface ScheduleDaoCustom {

	/**
	 * 取得Schedule 各隊伍的輸贏次數
	 * @param scheduleId
	 * @return
	 */
	public List<TeamScore> findTeamScoresBySchedule(Long scheduleId);
	
	/**
	 * 更新Schedule Status
	 * @param intervalHour 指定需更新startTime在系統時間的幾小時(intervalHour)之前的Schedule  
	 * @return 總共更新的筆數
	 */
	public int doSaveScheduleStatus(int intervalHour);
}
