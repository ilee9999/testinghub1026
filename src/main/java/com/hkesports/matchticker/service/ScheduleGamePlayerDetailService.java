package com.hkesports.matchticker.service;

import java.util.List;

import com.hkesports.matchticker.model.batchJob.ScheduleGamePlayerDetail;

public interface ScheduleGamePlayerDetailService extends BasicService<ScheduleGamePlayerDetail> {
	
	@Deprecated
	public List<ScheduleGamePlayerDetail> findDota2TmpAndSubData(List<Long> apiIds, String procId);
	
	@Deprecated
	public List<ScheduleGamePlayerDetail> findLOLTmpAndSubData(List<Long> apiIds, String dataType, String procId);
}
