package com.hkesports.matchticker.repository.custom;

import java.util.List;

import com.hkesports.matchticker.enums.GameTypeEnum;
import com.hkesports.matchticker.model.batchJob.ScheduleGameDetail;

public interface ScheduleGameDetailDaoCustom {

	@Deprecated
	List<ScheduleGameDetail> findFromTmpApiId(GameTypeEnum gameType, String dataType, String procId);

}
