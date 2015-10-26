package com.hkesports.matchticker.repository.custom;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.hkesports.matchticker.enums.GameTypeEnum;
import com.hkesports.matchticker.model.batchJob.GamePicksBans;

public class GamePicksBansDaoImpl implements GamePicksBansDaoCustom {
	@PersistenceContext
	EntityManager entityManager;
	
	@Deprecated
	@SuppressWarnings("unchecked")
	@Override
	public List<GamePicksBans> findFromTmpApiId(GameTypeEnum gameType, String dataType, String procId) {
		String jpql = "select t from GamePicksBans t join fetch t.scheduleGame join fetch t.hero, ApiIdTmp a ";
		jpql += " where t.scheduleGame.apiId = a.apiId and t.scheduleGame.gameType = a.gameType and a.gameType=:gameType and a.dataType=:dataType and a.procId=:procId";
		
		Query queryObj = entityManager.createQuery(jpql, GamePicksBans.class);
		queryObj.setParameter("gameType", gameType);
		queryObj.setParameter("dataType", dataType);
		queryObj.setParameter("procId", procId);
		return queryObj.getResultList();
	}
}
