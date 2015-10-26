package com.hkesports.matchticker.repository.custom;

import java.util.List;

import com.hkesports.matchticker.enums.GameTypeEnum;
import com.hkesports.matchticker.model.batchJob.ScheduleGamePlayerDetail;

public interface ScheduleGamePlayerDetailDaoCustom {

	@Deprecated
	List<ScheduleGamePlayerDetail> findFromTmpApiId(GameTypeEnum gameType, String dataType, String procId);

}
