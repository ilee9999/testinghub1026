package com.hkesports.matchticker.repository.custom;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.hkesports.matchticker.model.Data;

public class DataDaoImpl implements DataDaoCustom {

	@PersistenceContext
	EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Data> findByCodeName(String codeName) {
		String jpql = "select d from Data d join fetch d.code where d.code.codeName=:codeName";
		
		Query queryObj = entityManager.createQuery(jpql, Data.class);
		queryObj.setParameter("codeName", codeName);
		return queryObj.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Data> findByCodeName(String codeName, String subCodeName) {
		String jpql = "select d from Data d join fetch d.code join fetch d.code.parentCode where d.code.codeName=:subCodeName and d.code.parentCode.codeName=:codeName and d.parentData is null";
		
		Query queryObj = entityManager.createQuery(jpql, Data.class);
		queryObj.setParameter("codeName", codeName);
		queryObj.setParameter("subCodeName", subCodeName);
		return queryObj.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Data> findByCodeName(String codeName, String subCodeName, String parentDataName) {
		String jpql = "select d from Data d join fetch d.code join fetch d.code.parentCode join fetch d.parentData where d.code.codeName=:subCodeName and d.code.parentCode.codeName=:codeName and d.parentData.dataName = :parentDataName";
		
		Query queryObj = entityManager.createQuery(jpql, Data.class);
		queryObj.setParameter("codeName", codeName);
		queryObj.setParameter("subCodeName", subCodeName);
		queryObj.setParameter("parentDataName", parentDataName);
		return queryObj.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public String findByDataName(String codeName, String dataName) {
		String jpql = "select d from Data d join fetch d.code where d.code.codeName=:codeName and d.dataName = :dataName";
		
		Query queryObj = entityManager.createQuery(jpql, Data.class);
		queryObj.setParameter("codeName", codeName);
		queryObj.setParameter("dataName", dataName);
		return getDataValue(queryObj.getResultList());
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public String findByDataName(String codeName, String subCodeName, String dataName) {
		String jpql = "select d from Data d join fetch d.code join fetch d.code.parentCode where d.code.codeName=:subCodeName and d.code.parentCode.codeName=:codeName and d.dataName = :dataName";
		
		Query queryObj = entityManager.createQuery(jpql, Data.class);
		queryObj.setParameter("codeName", codeName);
		queryObj.setParameter("subCodeName", subCodeName);
		queryObj.setParameter("dataName", dataName);
		return getDataValue(queryObj.getResultList());
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public String findByDataName(String codeName, String subCodeName, String parentDataName, String dataName) {
		String jpql = "select d from Data d join fetch d.code join fetch d.code.parentCode join fetch d.parentData where d.code.codeName=:subCodeName and d.code.parentCode.codeName=:codeName and d.parentData.dataName = :parentDataName and d.dataName = :dataName";
		
		Query queryObj = entityManager.createQuery(jpql, Data.class);
		queryObj.setParameter("codeName", codeName);
		queryObj.setParameter("subCodeName", subCodeName);
		queryObj.setParameter("parentDataName", parentDataName);
		queryObj.setParameter("dataName", dataName);
		return getDataValue(queryObj.getResultList());
	}
	
	private String getDataValue(List<Data> list){
		if(list == null || list.size() < 1)
			return null;
		else
			return list.get(0).getDataValue();
	}
}
