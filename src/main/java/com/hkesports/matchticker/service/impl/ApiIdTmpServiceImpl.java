package com.hkesports.matchticker.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.hkesports.matchticker.enums.GameTypeEnum;
import com.hkesports.matchticker.model.batchJob.ApiIdTmp;
import com.hkesports.matchticker.repository.ApiIdTmpDao;
import com.hkesports.matchticker.service.ApiIdTmpService;

@Deprecated
@Transactional
@Service("apiIdTmpService")
public class ApiIdTmpServiceImpl implements ApiIdTmpService {
	
	private static final Logger logger = LoggerFactory.getLogger(ApiIdTmpServiceImpl.class);
	
	@Resource(name = "apiIdTmpDao")
	private ApiIdTmpDao apiIdTmpDao;

	public void deleteByGameTypeAndProcId(GameTypeEnum gameType, String procId) {
		apiIdTmpDao.deleteByGameTypeAndProcId(gameType, procId);
	}
	
	public void clearAndInsertApiIdTmp(List<Long> apiIds, GameTypeEnum gameTypeEnum, String dataType, String procId) {
		long t1 = System.nanoTime();
		apiIdTmpDao.deleteByGameTypeAndDataTypeAndProcId(gameTypeEnum, dataType, procId);
		if(CollectionUtils.isEmpty(apiIds)) {
			long t2 = System.nanoTime();
			logger.debug("deleteByGameTypeAndDataTypeAndProcId gameType:{}, dataType:{}, procId:{}, cost time:{} ms", gameTypeEnum, dataType, procId, ((t2-t1)/1000000.0));
			return;
		}
		for(Long id : apiIds) {
			apiIdTmpDao.insert(new ApiIdTmp(id, dataType, gameTypeEnum, procId));
		}
		long t2 = System.nanoTime();
		logger.debug("clearAndInsertApiIdTmp gameType:{}, dataType:{}, procId:{}, apiIds.size:{}, cost time:{} ms", gameTypeEnum, dataType, procId, apiIds.size(), ((t2-t1)/1000000.0));
	}
	
	public void clearApiIdTmpByLOL(List<Long> apiIds, String dataType, String procId) {
		clearAndInsertApiIdTmp(apiIds, GameTypeEnum.LOL, dataType, procId);
	}
	
	public void clearApiIdTmpByDOTA2(List<Long> apiIds, String dataType, String procId) {
		clearAndInsertApiIdTmp(apiIds, GameTypeEnum.DOTA2, dataType, procId);
	}
}
