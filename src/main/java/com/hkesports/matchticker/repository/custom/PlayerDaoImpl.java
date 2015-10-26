package com.hkesports.matchticker.repository.custom;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.hkesports.matchticker.model.batchJob.Player;


public class PlayerDaoImpl implements PlayerDaoCustom {

	@PersistenceContext
	EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Player> findAllByPhoto() {
		String jpql = "select p from Player p where p.photoUrl is not null and p.photoUrl != ''";
		
		Query queryObj = entityManager.createQuery(jpql, Player.class);
		return queryObj.getResultList();
	}
}
