package com.hkesports.matchticker.repository.custom;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.SQLQuery;
import org.hibernate.Session;

public class TouramentGameTeamScoreDaoImpl implements TouramentGameTeamScoreDaoCustom {

	@PersistenceContext
	EntityManager entityManager;
	
	private static final String sql_batchInsertTouramentGameTeamScore = 
					"insert into tourament_game_team_score(tourament_id, team_id, game_id, win_count, draw_count, lose_count, score) " + 
					"select s.tourament_id, sgd.team_id, s.game_id, " + 
					"   sum(case when lower(sgd.win) = 'win' then 1 else 0 end) win, " + 
					"   sum(case when lower(sgd.win) = 'draw' then 1 else 0 end) draw, " + 
					"   sum(case when lower(sgd.win) = 'fail' then 1 else 0 end) lose, " + 
					"	sum( " + 
					"	case " + 
					"	when lower(sgd.win) = 'win' then " + 
					"	case when tg.win_score is not null then tg.win_score when t.win_score is not null then t.win_score when g.win_score is not null then g.win_score else 3 end " + 
					"	when lower(sgd.win) = 'draw' then " + 
					"	case when tg.draw_score is not null then tg.draw_score when t.draw_score is not null then t.draw_score when g.draw_score is not null then g.draw_score else 1 end " + 
					"	else " + 
					"	case when tg.lose_score is not null then tg.lose_score when t.lose_score is not null then t.lose_score when g.lose_score is not null then g.lose_score else 0 end " + 
					"	end) score " + 
					"from schedule s " + 
					"inner join game g on g.id = s.game_id " + 
					"inner join schedule_game sg on sg.schedule_id = s.id " + 
					"inner join schedule_game_detail sgd on sgd.schedule_game_id = sg.id " + 
					"inner join tourament t on t.id = s.tourament_id " + 
					"left join tourament_game tg on tg.tourament_id = t.id " + 
					"	and tg.game_id = g.id " + 
					"where not exists ( " + 
					"	select tgs1.id from tourament_game_team_score tgs1 " + 
					"	where tgs1.tourament_id = s.tourament_id " + 
					"		and tgs1.team_id = sgd.team_id " + 
					"		and tgs1.game_id = s.game_id " + 
					") " + 
					"group by s.tourament_id, s.game_id, sgd.team_id ";
	
	
	private static final String sql_batchUpdateTouramentGameTeamScore = 
					"update tourament_game_team_score as tgs	 " + 
					"inner join ( " + 
					"		select s.tourament_id, sgd.team_id, s.game_id, " + 
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
					"       inner join game g on g.id = s.game_id " + 
					"		inner join schedule_game sg on sg.schedule_id = s.id " + 
					"		inner join schedule_game_detail sgd on sgd.schedule_game_id = sg.id " + 
					"		inner join tourament t on t.id = s.tourament_id " + 
					"		left join tourament_game tg on tg.tourament_id = t.id and tg.game_id = g.id " + 
					"		where exists ( " + 
					"				select tgs1.id from tourament_game_team_score tgs1 " + 
					"				where tgs1.tourament_id = s.tourament_id " + 
					"				and tgs1.team_id = sgd.team_id " + 
					"				and tgs1.game_id = s.game_id " + 
					"               and tgs1.score != score " + 
					"		) " + 
					"		group by s.tourament_id, s.game_id, sgd.team_id " + 
					") all_data on all_data.tourament_id = tgs.tourament_id and all_data.team_id = tgs.team_id and all_data.game_id = tgs.game_id " + 
					"set tgs.score = all_data.score, " + 
					"tgs.win_count = all_data.win, " + 
					"tgs.draw_count = all_data.draw, " + 
					"tgs.lose_count = all_data.lose ";

	@Override
	public int batchInsertTouramentGameTeamScore() {
		Session session = entityManager.unwrap(Session.class);
		SQLQuery queryObj = session.createSQLQuery(sql_batchInsertTouramentGameTeamScore);
		return queryObj.executeUpdate();
	}
	
	@Override
	public int batchUpdateTouramentGameTeamScore() {
		Session session = entityManager.unwrap(Session.class);
		SQLQuery queryObj = session.createSQLQuery(sql_batchUpdateTouramentGameTeamScore);
		return queryObj.executeUpdate();
	}
}
