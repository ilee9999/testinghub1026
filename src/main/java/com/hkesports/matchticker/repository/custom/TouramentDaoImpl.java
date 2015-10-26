package com.hkesports.matchticker.repository.custom;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.hkesports.matchticker.model.batchJob.Tourament;

public class TouramentDaoImpl implements TouramentDaoCustom {
	@PersistenceContext
	EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	@Override
	@Deprecated
	public List<Tourament> findAllByLeagueImage() {
		String jpql = "select t from Tourament t where t.leagueImage is not null and t.leagueImage != ''";
		
		Query queryObj = entityManager.createQuery(jpql, Tourament.class);
		return queryObj.getResultList();
	}
}
