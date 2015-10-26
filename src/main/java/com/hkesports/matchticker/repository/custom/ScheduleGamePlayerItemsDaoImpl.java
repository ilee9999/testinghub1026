package com.hkesports.matchticker.repository.custom;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.hkesports.matchticker.enums.GameTypeEnum;
import com.hkesports.matchticker.model.batchJob.ScheduleGamePlayerItems;

public class ScheduleGamePlayerItemsDaoImpl implements ScheduleGamePlayerItemsDaoCustom {

	@PersistenceContext
	EntityManager entityManager;
	
	@Deprecated
	@SuppressWarnings("unchecked")
	@Override
	public List<ScheduleGamePlayerItems> findFromTmpApiId(GameTypeEnum gameType, String dataType, String procId) {
		String jpql = "select t from ScheduleGamePlayerItems t join fetch t.scheduleGame join fetch t.player, ApiIdTmp a ";
		jpql += " where t.scheduleGame.apiId = a.apiId and t.scheduleGame.gameType = a.gameType and a.gameType=:gameType and a.dataType=:dataType and a.procId=:procId";
		
		Query queryObj = entityManager.createQuery(jpql, ScheduleGamePlayerItems.class);
		queryObj.setParameter("gameType", gameType);
		queryObj.setParameter("dataType", dataType);
		queryObj.setParameter("procId", procId);
		return queryObj.getResultList();
	}
}
