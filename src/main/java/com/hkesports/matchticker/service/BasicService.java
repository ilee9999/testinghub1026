package com.hkesports.matchticker.service;

import java.util.Collection;
import java.util.List;

import com.hkesports.matchticker.enums.GameTypeEnum;


public interface BasicService<T> {
	
	T findByApiIdAndGameType(Long apiId, GameTypeEnum gameType);
	
	List<T> findByGameType(GameTypeEnum gameType);
	
	List<T> findByGameTypeOrderByApiIdDesc(GameTypeEnum gameType);
	
	List<T> findByGameId(Long gameId);
	
	List<T> findByGameIdOrderByApiIdDesc(Long gameId);
	
	List<T> findAllByGameTypeAndApiIds(GameTypeEnum gameType, List<Long> apiIds);
	
	T save(T entity);

	void batchSave(Collection<T> t);

//	List<T> findByDota2(List<Long> apiIds, String dataType, String procId);
	
//	List<T> findByLOL(List<Long> apiIds, String dataType, String procId);
}
