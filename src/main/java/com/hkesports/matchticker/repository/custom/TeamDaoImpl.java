package com.hkesports.matchticker.repository.custom;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.hkesports.matchticker.model.batchJob.Team;

public class TeamDaoImpl implements TeamDaoCustom {
	@PersistenceContext
	EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Team> findAllByLogo() {
		String jpql = "select t from Team t where t.logoUrl is not null and t.logoUrl != ''";
		
		Query queryObj = entityManager.createQuery(jpql, Team.class);
		return queryObj.getResultList();
	}
}
