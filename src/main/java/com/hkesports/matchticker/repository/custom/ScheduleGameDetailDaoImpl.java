package com.hkesports.matchticker.repository.custom;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.hkesports.matchticker.enums.GameTypeEnum;
import com.hkesports.matchticker.model.batchJob.ScheduleGameDetail;

public class ScheduleGameDetailDaoImpl implements ScheduleGameDetailDaoCustom {

	@PersistenceContext
	EntityManager entityManager;
	
	@Deprecated
	@SuppressWarnings("unchecked")
	@Override
	public List<ScheduleGameDetail> findFromTmpApiId(GameTypeEnum gameType, String dataType, String procId) {
		String jpql = "select t from ScheduleGameDetail t join fetch t.team, ApiIdTmp a ";
		jpql += " where t.scheduleGame.apiId = a.apiId and t.scheduleGame.gameType = a.gameType and a.gameType=:gameType and a.dataType=:dataType and a.procId=:procId";
		
		Query queryObj = entityManager.createQuery(jpql, ScheduleGameDetail.class);
		queryObj.setParameter("gameType", gameType);
		queryObj.setParameter("dataType", dataType);
		queryObj.setParameter("procId", procId);
		return queryObj.getResultList();
	}
}
