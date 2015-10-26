package com.hkesports.matchticker.service;

import java.util.List;

import com.hkesports.matchticker.model.batchJob.AbilityUpgrades;


public interface AbilityUpgradesService extends BasicService<AbilityUpgrades> {
	
	@Deprecated
	public List<AbilityUpgrades> findDota2TmpAndSubData(List<Long> apiIds, String procId);
	
}
