package com.hkesports.matchticker.repository.custom;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.SQLQuery;
import org.hibernate.Session;

public class TeamGameScoreDaoImpl implements TeamGameScoreDaoCustom {
	
	@PersistenceContext
	EntityManager entityManager;
	
	private static final String sql_batchInsertTeamGameScore = 
					"insert into team_game_score(team_id, game_id, win_count, draw_count, lose_count, score) " + 
					"select sgd.team_id, s.game_id, " + 
					"   sum(case when lower(sgd.win) = 'win' then 1 else 0 end) win, " + 
					"   sum(case when lower(sgd.win) = 'draw' then 1 else 0 end) draw, " + 
					"   sum(case when lower(sgd.win) = 'fail' then 1 else 0 end) lose, " + 
					"	sum( " + 
					"	case " + 
					"	when sgd.win = 'win' then " + 
					"	case when tg.win_score is not null then tg.win_score when t.win_score is not null then t.win_score when g.win_score is not null then g.win_score else 3 end " + 
					"	when sgd.win = 'draw' then " + 
					"	case when tg.draw_score is not null then tg.draw_score when t.draw_score is not null then t.draw_score when g.draw_score is not null then g.draw_score else 1 end " + 
					"	else " + 
					"	case when tg.lose_score is not null then tg.lose_score when t.lose_score is not null then t.lose_score when g.lose_score is not null then g.lose_score else 0 end " + 
					"	end) score " + 
					"from schedule s " + 
					"inner join schedule_game sg on sg.schedule_id = s.id " + 
					"inner join schedule_game_detail sgd on sgd.schedule_game_id = sg.id " + 
					"inner join game g on g.id = s.game_id " + 
					"inner join tourament t on t.id = s.tourament_id " + 
					"left join tourament_game tg on tg.tourament_id = t.id and tg.game_id = g.id " + 
					"where not exists ( " + 
					"	select tgs1.id from team_game_score tgs1 " + 
					"	where tgs1.team_id = sgd.team_id and tgs1.game_id = s.game_id " + 
					") " + 
					"group by s.game_id, sgd.team_id ";

	
	private static final String sql_batchUpdateTeamGameScore = 
					"update team_game_score as tgs " + 
					"inner join ( " + 
					"		select sgd.team_id, s.game_id, " + 
					"			sum(case when lower(sgd.win) = 'win' then 1 else 0 end) win, " + 
					"			sum(case when lower(sgd.win) = 'draw' then 1 else 0 end) draw, " + 
					"			sum(case when lower(sgd.win) = 'fail' then 1 else 0 end) lose, " + 
					"           sum( " + 
					"           case " + 
					"			when lower(sgd.win) = 'win' then " + 
					"           case when tg.win_score is not null then tg.win_score when t.win_score is not null then t.win_score when g.win_score is not null then g.win_score else 3 end " + 
					"           when lower(sgd.win) = 'draw' then " + 
					"           case when tg.draw_score is not null then tg.draw_score when t.draw_score is not null then t.draw_score when g.draw_score is not null then g.draw_score else 1 end " + 
					"           else " + 
					"           case when tg.lose_score is not null then tg.lose_score when t.lose_score is not null then t.lose_score when g.lose_score is not null then g.lose_score else 0 end " + 
					"           end) score " + 
					"		from schedule s " + 
					"		inner join schedule_game sg on sg.schedule_id = s.id " + 
					"		inner join schedule_game_detail sgd on sgd.schedule_game_id = sg.id " + 
					"		inner join game g on g.id = s.game_id " + 
					"		inner join tourament t on t.id = s.tourament_id " + 
					"		left join tourament_game tg on tg.tourament_id = t.id and tg.game_id = g.id " + 
					"		where exists ( " + 
					"			select tgs1.id from team_game_score tgs1 " + 
					"			where tgs1.team_id = sgd.team_id and tgs1.game_id = s.game_id and tgs1.score <> score " + 
					"		) " + 
					"		group by s.game_id, sgd.team_id " + 
					"	) all_data on all_data.team_id = tgs.team_id and all_data.game_id = tgs.game_id " + 
					"set tgs.score = all_data.score, " + 
					"tgs.win_count = all_data.win, " + 
					"tgs.draw_count = all_data.draw, " + 
					"tgs.lose_count = all_data.lose ";
	
	public int batchInsertTeamGameScore() {
		Session session = entityManager.unwrap(Session.class);
		SQLQuery queryObj = session.createSQLQuery(sql_batchInsertTeamGameScore);
		return queryObj.executeUpdate();
	}
	
	public int batchUpdateTeamGameScore() {
		Session session = entityManager.unwrap(Session.class);
		SQLQuery queryObj = session.createSQLQuery(sql_batchUpdateTeamGameScore);
		return queryObj.executeUpdate();
	}
}
