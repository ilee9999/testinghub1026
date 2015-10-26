package com.hkesports.matchticker.repository.custom;

import java.util.List;

import com.hkesports.matchticker.enums.GameTypeEnum;
import com.hkesports.matchticker.model.batchJob.GamePicksBans;

public interface GamePicksBansDaoCustom {

	@Deprecated
	List<GamePicksBans> findFromTmpApiId(GameTypeEnum gameType, String dataType, String procId);

}
