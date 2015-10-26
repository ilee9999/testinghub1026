package com.hkesports.matchticker.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.hkesports.matchticker.enums.GameTypeEnum;
import com.hkesports.matchticker.enums.SortingOrderEnum;
import com.hkesports.matchticker.model.basic.BasicModel;
import com.hkesports.matchticker.repository.factory.GenericRepository;
import com.hkesports.matchticker.service.BasicService;

@Transactional
public abstract class BasicServiceImpl<T extends BasicModel> implements BasicService<T> {

	protected abstract GenericRepository<T, Long> getDao();
	
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	protected static final int MAX_IN_LEN = 100;
	

	@Transactional(readOnly=true)
	public T findByApiIdAndGameType(Long apiId, GameTypeEnum gameType) {
		return getDao().findByApiIdAndGameType(apiId, gameType);
	}
	
	@Transactional(readOnly=true)
	public List<T> findByGameType(GameTypeEnum gameType) {
		return getDao().findByGameType(gameType);
	}
	
	@Transactional(readOnly=true)
	public List<T> findByGameTypeOrderByApiIdDesc(GameTypeEnum gameType) {
		return getDao().findByGameType(gameType, SortingOrderEnum.DESC, "apiId");
	}
	
	@Transactional(readOnly=true)
	public List<T> findByGameId(Long gameId) {
		return getDao().findByGameId(gameId);
	}
	
	@Transactional(readOnly=true)
	public List<T> findByGameIdOrderByApiIdDesc(Long gameId) {
		return getDao().findByGameId(gameId, SortingOrderEnum.DESC, "apiId");
	}
	
	@Transactional(readOnly=true)
	public List<T> findAllByGameTypeAndApiIds(GameTypeEnum gameType, List<Long> apiIds) {
		if(CollectionUtils.isEmpty(apiIds)) {
			return new ArrayList<>(0);
		}
		int size = apiIds.size();
		List<T> resultList = new ArrayList<>(size);
		for(int i=0 ; i < apiIds.size(); i+=MAX_IN_LEN) {
			List<Long> subList = apiIds.subList(i, (i+MAX_IN_LEN) > size ? size : i+MAX_IN_LEN);
			if(!CollectionUtils.isEmpty(subList)) {
				resultList.addAll(getDao().findAllByGameTypeAndApiIds(gameType, subList));
			}
		}
		return resultList;
	}
	
	public T save(T entity) {
		return getDao().save(entity);
	}
	
	public void batchSave(Collection<T> objs) {
		long t1 = System.nanoTime();
		if(CollectionUtils.isEmpty(objs)) {
			return;
		}
		getDao().batchSave(objs);
		long t2 = System.nanoTime();
		logger.debug("batchSave objs.size:{}, cost time:{} ms", objs.size(), ((t2-t1)/1000000.0));

	}

}
