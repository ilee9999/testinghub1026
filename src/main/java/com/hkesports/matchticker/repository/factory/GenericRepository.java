package com.hkesports.matchticker.repository.factory;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import com.hkesports.matchticker.enums.GameTypeEnum;
import com.hkesports.matchticker.enums.SortingOrderEnum;
import com.hkesports.matchticker.model.basic.BasicModel;

/**
 * @author manboyu
 *
 * @param <T>
 * @param <ID>
 */
@NoRepositoryBean
public interface GenericRepository<T, ID extends Serializable> extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {

	T getEntity(ID id);

	long getCount(String entityClass, Map<String, Object> properties);
	
	List<T> findByGameType(GameTypeEnum gameType);
	
	List<T> findByGameType(GameTypeEnum gameType, SortingOrderEnum sortingOrder, String... sortingFields);
	
	List<T> findByGameId(Long gameId);
	
	List<T> findByGameId(Long gameId, SortingOrderEnum sortingOrder, String... sortingFields);
	
	List<T> findAllByGameTypeAndApiIds(GameTypeEnum gameType, Collection<Long> apiIds);
	
	T findByGameIdAndApiId(Long gameId, Long apiId);
	
	T findByApiIdAndGameType(Long apiId, GameTypeEnum gameType);
	
	<Q extends T> Q insert(Q entity);
	
	<Q extends T> Q update(Q entity);
	
	void batchSave(Collection<? extends BasicModel> objList);
	
	<E extends BasicModel> E saveObj(E t);
}
