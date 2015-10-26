package com.hkesports.matchticker.repository.custom;

import java.util.List;

import com.hkesports.matchticker.enums.GameTypeEnum;
import com.hkesports.matchticker.model.batchJob.ScheduleGamePlayerItems;

public interface ScheduleGamePlayerItemsDaoCustom {

	@Deprecated
	List<ScheduleGamePlayerItems> findFromTmpApiId(GameTypeEnum gameType, String dataType, String procId);

}
