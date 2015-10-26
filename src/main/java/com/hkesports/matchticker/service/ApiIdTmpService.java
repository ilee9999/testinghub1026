package com.hkesports.matchticker.service;

import java.util.List;

import com.hkesports.matchticker.enums.GameTypeEnum;

@Deprecated
public interface ApiIdTmpService {
	
	public void deleteByGameTypeAndProcId(GameTypeEnum gameType, String procId);
	
	public void clearApiIdTmpByLOL(List<Long> apiIds, String dataType, String procId);
	
	public void clearApiIdTmpByDOTA2(List<Long> apiIds, String dataType, String procId);
	
	public void clearAndInsertApiIdTmp(List<Long> apiIds, GameTypeEnum gameTypeEnum, String dataType, String procId);

}
