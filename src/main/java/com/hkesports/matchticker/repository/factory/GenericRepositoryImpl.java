package com.hkesports.matchticker.repository.factory;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import com.hkesports.matchticker.enums.GameTypeEnum;
import com.hkesports.matchticker.enums.SortingOrderEnum;
import com.hkesports.matchticker.model.basic.BasicModel;

/**
 * @author manboyu
 *
 * @param <T>
 * @param <ID>
 */
public class GenericRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements GenericRepository<T, ID> {

	private EntityManager entityManager;
	
	private int batchSize = 500;
	
	public GenericRepositoryImpl(Class<T> domainClass, EntityManager entityManager) {
		super(domainClass, entityManager);
		this.entityManager = entityManager;
	}

	@Override
	public T getEntity(ID id) {
		Class<T> entityClass = getDomainClass();
		Assert.notNull(id, "無法取得 Entity [ " + entityClass.getName() + " ], id不得為空");
		return entityManager.getReference(entityClass, id);
	}

	@Override
	public long getCount(String entityClass, Map<String, Object> properties) {
		StringBuilder jpql = new StringBuilder();
		jpql.append("select count(c.id) from "+ entityClass + " c ");
		if(CollectionUtils.isEmpty(properties)){
			return (Long)entityManager.createQuery(jpql.toString()).getSingleResult();
		}
		for(String key : properties.keySet()) {
			jpql.append("and c." + key + "=:" + key + " ");
		}
		Query queryObj = entityManager.createQuery(jpql.toString().replaceFirst("and", "where"));
		for(Map.Entry<String, Object> entry : properties.entrySet()) {
			queryObj.setParameter(entry.getKey(), entry.getValue());
		}
		return (long)queryObj.getSingleResult();
	}

	@Override
	public List<T> findByGameType(GameTypeEnum gameType) {
		return findByGameType(gameType, SortingOrderEnum.ASC);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findByGameType(GameTypeEnum gameType, SortingOrderEnum sortingOrder, String... sortingFields) {
		Class<T> entityClass = getDomainClass();
		StringBuilder jpql = new StringBuilder();
		jpql.append("select t from ");
		jpql.append(entityClass.getName());
		jpql.append(" t where gameType =:gameType ");
		if(ArrayUtils.isNotEmpty(sortingFields)) {
			jpql.append(" order by ");
			int i = 0;
			for(String field:sortingFields) {
				if(i++>0)
					jpql.append(",");
				jpql.append(field);
			}
			jpql.append(" ").append(sortingOrder);
		}
		Query queryObj = entityManager.createQuery(jpql.toString(), entityClass);
		queryObj.setParameter("gameType", gameType);
		return queryObj.getResultList();
	}
	
	@Override
	public List<T> findByGameId(Long gameId) {
		return findByGameId(gameId, SortingOrderEnum.ASC);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findByGameId(Long gameId, SortingOrderEnum sortingOrder, String... sortingFields) {
		Class<T> entityClass = getDomainClass();
		StringBuilder jpql = new StringBuilder();
		jpql.append("select t from ");
		jpql.append(entityClass.getName());
		jpql.append(" t where t.game.id =:gameId ");
		if(ArrayUtils.isNotEmpty(sortingFields)) {
			jpql.append(" order by ");
			int i = 0;
			for(String field:sortingFields) {
				if(i++>0)
					jpql.append(",");
				jpql.append(field);
			}
			jpql.append(" ").append(sortingOrder);
		}
		Query queryObj = entityManager.createQuery(jpql.toString(), entityClass);
		queryObj.setParameter("gameId", gameId);
		return queryObj.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<T> findAllByGameTypeAndApiIds(GameTypeEnum gameType, Collection<Long> apiIds) {
		Class<T> entityClass = getDomainClass();
		String jpql = "select t from " + entityClass.getName() + " t where t.gameType=:gameType and t.apiId in :apiIds";
		Query queryObj = entityManager.createQuery(jpql, entityClass);
		queryObj.setParameter("gameType", gameType);
		queryObj.setParameter("apiIds", apiIds);
		return queryObj.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public T findByGameIdAndApiId(Long gameId, Long apiId) {
		Class<T> entityClass = getDomainClass();
		String jpql = "select t from " + entityClass.getName() + " t where t.apiId =:apiId and t.game.id=:gameId";
		Query queryObj = entityManager.createQuery(jpql, entityClass);
		queryObj.setParameter("apiId", apiId);
		queryObj.setParameter("gameId", gameId);
		List<T> results = queryObj.getResultList();
		return !CollectionUtils.isEmpty(results) ? results.get(0) : null;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public T findByApiIdAndGameType(Long apiId, GameTypeEnum gameType) {
		Class<T> entityClass = getDomainClass();
		String jpql = "select t from " + entityClass.getName() + " t where t.apiId =:apiId and t.gameType=:gameType";
		Query queryObj = entityManager.createQuery(jpql, entityClass);
		queryObj.setParameter("apiId", apiId);
		queryObj.setParameter("gameType", gameType);
		List<T> results = queryObj.getResultList();
		return !CollectionUtils.isEmpty(results) ? results.get(0) : null;
	}

	@Override
	public <Q extends T> Q insert(Q entity) {
		entityManager.persist(entity);
		return entity;
	}

	@Override
	public <Q extends T> Q update(Q entity) {
		return entityManager.merge(entity);
	}
	
	public void batchSave(Collection<? extends BasicModel> objList) {
		if(CollectionUtils.isEmpty(objList))
			return ;
		int count = 0;
		for(BasicModel b:objList) {
			saveObj(b);
			if ((count!=0 && count % batchSize == 0) || (count == objList.size()-1)) {
				entityManager.flush();
				entityManager.clear();
			}
			count++;
		}
	}
	
	@Transactional
	public <E extends BasicModel> E saveObj(E t) {
		if (t.getId() == null) {
			entityManager.persist(t);
			return t;
		} else {
			return entityManager.merge(t);
		}
	}
}
