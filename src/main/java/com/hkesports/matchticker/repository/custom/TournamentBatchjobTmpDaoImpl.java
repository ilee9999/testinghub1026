package com.hkesports.matchticker.repository.custom;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.DateType;
import org.hibernate.type.LongType;

import com.hkesports.matchticker.enums.GameTypeEnum;
import com.hkesports.matchticker.model.batchJob.TournamentBatchjobTmp;

public class TournamentBatchjobTmpDaoImpl implements TournamentBatchjobTmpDaoCustom {

	@PersistenceContext
	EntityManager entityManager;

	
	@SuppressWarnings("unchecked")
	@Override
	public List<TournamentBatchjobTmp> findAllByCreateDate(GameTypeEnum gameType, int limitHour) {
		StringBuilder sql = new StringBuilder();
		sql.append("select t.id, t.game_type as gameType, t.api_id as apiId, t.create_date as createDate, t.update_date as updateDate ");
		sql.append("from tournament_batchjob_tmp t ");
		sql.append("where t.game_type=:gameType ");
		if(limitHour==0) {
			sql.append("and t.create_date >= current_date() ");
		} else if(limitHour > 0) {
			sql.append("and t.create_date >= date_add(current_date(), interval :limitHour hour) ");
		} else {
			sql.append("and t.create_date >= date_sub(current_date(), interval :limitHour hour) ");
		}
		Session session = entityManager.unwrap(Session.class);
		SQLQuery queryObj = session.createSQLQuery(sql.toString());
		
//		Properties params = new Properties();
//		params.put("enumClass", "com.hkesports.matchticker.enums.GameTypeEnum");
//		params.put("type", "12");
//		Type enumType = new TypeLocatorImpl(new TypeResolver()).custom(GameTypeEnum.class, params);
//		queryObj.addScalar("gameType", enumType);
		
		queryObj.addScalar("id", LongType.INSTANCE);
		queryObj.addScalar("apiId", LongType.INSTANCE);
		queryObj.addScalar("createDate", DateType.INSTANCE);
		queryObj.addScalar("updateDate", DateType.INSTANCE);
		queryObj.setResultTransformer(Transformers.aliasToBean(TournamentBatchjobTmp.class));
		queryObj.setParameter("gameType", gameType.name());
		queryObj.setParameter("limitHour", Math.abs(limitHour));
		return queryObj.list();
	}

}
