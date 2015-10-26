package com.hkesports.matchticker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hkesports.matchticker.enums.GameTypeEnum;
import com.hkesports.matchticker.model.batchJob.AbilityUpgrades;
import com.hkesports.matchticker.repository.custom.AbilityUpgradesDaoCustom;
import com.hkesports.matchticker.repository.factory.GenericRepository;

public interface AbilityUpgradesDao extends AbilityUpgradesDaoCustom, GenericRepository<AbilityUpgrades, Long> {

	@Query(value = "select t from AbilityUpgrades t where t.scheduleGame.gameType=:gameType and t.scheduleGame.apiId in :apiIds ")
	public List<AbilityUpgrades> findAllByScheduleGameApiIds(@Param("gameType") GameTypeEnum gameType, @Param("apiIds") List<Long> apiIds);
	
}
