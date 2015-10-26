package com.hkesports.matchticker.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.hkesports.matchticker.enums.GameTypeEnum;
import com.hkesports.matchticker.model.batchJob.AbilityUpgrades;
import com.hkesports.matchticker.repository.AbilityUpgradesDao;
import com.hkesports.matchticker.repository.factory.GenericRepository;
import com.hkesports.matchticker.service.AbilityUpgradesService;

@Transactional
@Service("abilityUpgradesService")
public class AbilityUpgradesServiceImpl extends BasicServiceImpl<AbilityUpgrades> implements AbilityUpgradesService {
	
	@Resource(name = "abilityUpgradesDao")
	private AbilityUpgradesDao abilityUpgradesDao;

	@Override
	protected GenericRepository<AbilityUpgrades, Long> getDao() {
		return abilityUpgradesDao;
	}
	
	@Deprecated
	@Override
	@Transactional(readOnly=true)
	public List<AbilityUpgrades> findDota2TmpAndSubData(List<Long> apiIds, String procId) {
		return abilityUpgradesDao.findFromTmpApiId(GameTypeEnum.DOTA2, "match", procId);
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<AbilityUpgrades> findAllByGameTypeAndApiIds(GameTypeEnum gameType, List<Long> apiIds) {
		if(CollectionUtils.isEmpty(apiIds)) {
			return new ArrayList<>(0);
		}
		int size = apiIds.size();
		List<AbilityUpgrades> resultList = new ArrayList<>(size);
		for(int i=0 ; i < apiIds.size(); i+=MAX_IN_LEN) {
			List<Long> subList = apiIds.subList(i, (i+MAX_IN_LEN) > size ? size : i+MAX_IN_LEN);
			if(!CollectionUtils.isEmpty(subList)) {
				resultList.addAll(abilityUpgradesDao.findAllByScheduleGameApiIds(gameType, subList));
			}
		}
		return resultList;
	}
}
