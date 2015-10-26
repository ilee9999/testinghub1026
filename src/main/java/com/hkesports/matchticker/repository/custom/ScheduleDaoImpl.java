package com.hkesports.matchticker.repository.custom;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.ResultTransformer;
import org.hibernate.transform.Transformers;
import org.hibernate.type.IntegerType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;

import com.hkesports.matchticker.enums.ScheduleStatus;
import com.hkesports.matchticker.vo.TeamScore;

public class ScheduleDaoImpl implements ScheduleDaoCustom {
	@PersistenceContext
	EntityManager entityManager;
	
	
	private static final String SQL_findTeamScoresBySchedule = 
			"select sg.schedule_id as scheduleId, sgd.team_id as teamId, " +
			"t.team_full_name teamFullName, sum(case when win='Win' then 1 else 0 end) score " +
			"from schedule_game_detail_r sgd " +
			"inner join schedule_game_r sg on sg.id = sgd.schedule_game_id and sg.schedule_id = :scheduleId " +
			"left join team_r t on t.id = sgd.team_id " +
			"group by sg.schedule_id, sgd.team_id " +
			"order by sgd.team_id asc";
	
	private static final String SQL_doSaveScheduleStatus = 
			"update schedule s " + 
			"inner join ( " + 
			" select distinct s2.tourament_id from schedule s2 " + 
			" where s2.start_time <= date_add(now(), interval :intervalHour hour) " + 
			" and s2.status = :status_ready " + 
			") s1 on s1.tourament_id = s.tourament_id " + 
			"set s.status = :status_contestended " + 
			"where s.status = :status_ready ";
	
	@SuppressWarnings("unchecked")
	@Override
	public List<TeamScore> findTeamScoresBySchedule(Long scheduleId) {
		Session session = entityManager.unwrap(Session.class);
		SQLQuery queryObj = session.createSQLQuery(SQL_findTeamScoresBySchedule);
		
		ResultTransformer transformer = Transformers.aliasToBean(TeamScore.class);
		queryObj.addScalar("scheduleId", LongType.INSTANCE);
		queryObj.addScalar("teamId", LongType.INSTANCE);
		queryObj.addScalar("teamFullName", StringType.INSTANCE);
		queryObj.addScalar("score", IntegerType.INSTANCE);
		queryObj.setResultTransformer(transformer);
		queryObj.setParameter("scheduleId", scheduleId);
		
		return queryObj.list();
	}
	
	public int doSaveScheduleStatus(int intervalHour) {
		Session session = entityManager.unwrap(Session.class);
		SQLQuery queryObj = session.createSQLQuery(SQL_doSaveScheduleStatus);
		
		queryObj.setParameter("intervalHour", intervalHour);
		queryObj.setParameter("status_ready", ScheduleStatus.Ready.ordinal());
		queryObj.setParameter("status_contestended", ScheduleStatus.Contestended.ordinal());
		return queryObj.executeUpdate();
	}
}
