package com.hkesports.matchticker.repository.custom;

import java.util.List;

import com.hkesports.matchticker.enums.GameTypeEnum;
import com.hkesports.matchticker.model.batchJob.AbilityUpgrades;

public interface AbilityUpgradesDaoCustom {

	@Deprecated
	List<AbilityUpgrades> findFromTmpApiId(GameTypeEnum gameType, String dataType, String procId);

}
