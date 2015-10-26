package com.hkesports.matchticker.repository.custom;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.SQLQuery;
import org.hibernate.Session;

public class TeamContinentGameScoreDaoImpl implements TeamContinentGameScoreDaoCustom {

	private static final String sql_batchInsertTeamContinentGameScore = 
					"insert into team_continent_game_score (continent_game_id, team_id, win_count, draw_count, lose_count, score) " + 
					"select " + 
					"	cg.id, sgd.team_id, " + 
					"    sum(case when lower(sgd.win) = 'win' then 1 else 0 end) win, " + 
					"    sum(case when lower(sgd.win) = 'draw' then 1 else 0 end) draw, " + 
					"    sum(case when lower(sgd.win) = 'fail' then 1 else 0 end) lose, " + 
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
					"inner join team te on te.id = sgd.team_id " + 
					"inner join continent_game cg on cg.game_id = s.game_id and te.country = cg.country " + 
					"inner join game g on g.id = s.game_id " + 
					"inner join tourament t on t.id = s.tourament_id " + 
					"left join tourament_game tg on tg.tourament_id = t.id and tg.game_id = g.id " + 
					"where not exists ( " + 
					"	select tgs1.id from team_continent_game_score tgs1 " + 
					"	where tgs1.continent_game_id = cg.id and tgs1.team_id = sgd.team_id " + 
					") " + 
					"group by cg.id, sgd.team_id ";
	
	private static final String sql_batchUpdateTeamContinentGameScore = 
					"update team_continent_game_score tcgs " + 
					"inner join ( " + 
					"	select " + 
					"		cg.id, sgd.team_id, " + 
					"		sum(case when lower(sgd.win) = 'win' then 1 else 0 end) win, " + 
					"		sum(case when lower(sgd.win) = 'draw' then 1 else 0 end) draw, " + 
					"		sum(case when lower(sgd.win) = 'fail' then 1 else 0 end) lose, " + 
					"		sum( " + 
					"		case " + 
					"		when sgd.win = 'win' then " + 
					"		case when tg.win_score is not null then tg.win_score when t.win_score is not null then t.win_score when g.win_score is not null then g.win_score else 3 end " + 
					"		when sgd.win = 'draw' then " + 
					"		case when tg.draw_score is not null then tg.draw_score when t.draw_score is not null then t.draw_score when g.draw_score is not null then g.draw_score else 1 end " + 
					"		else " + 
					"		case when tg.lose_score is not null then tg.lose_score when t.lose_score is not null then t.lose_score when g.lose_score is not null then g.lose_score else 0 end " + 
					"		end) score " + 
					"	from schedule s " + 
					"	inner join schedule_game sg on sg.schedule_id = s.id " + 
					"	inner join schedule_game_detail sgd on sgd.schedule_game_id = sg.id " + 
					"	inner join team te on te.id = sgd.team_id " + 
					"	inner join continent_game cg on cg.game_id = s.game_id and te.country = cg.country " + 
					"	inner join game g on g.id = s.game_id " + 
					"	inner join tourament t on t.id = s.tourament_id " + 
					"	left join tourament_game tg on tg.tourament_id = t.id and tg.game_id = g.id " + 
					"	where exists ( " + 
					"		select tgs1.id from team_continent_game_score tgs1 " + 
					"		where tgs1.continent_game_id = cg.id and tgs1.team_id = sgd.team_id and tgs1.score <> score " + 
					"	) " + 
					"	group by cg.id, sgd.team_id " + 
					") all_data on tcgs.continent_game_id = all_data.id and tcgs.team_id = all_data.team_id " + 
					"set tcgs.score = all_data.score, " + 
					"tcgs.win_count = all_data.win, " + 
					"tcgs.draw_count = all_data.draw, " + 
					"tcgs.lose_count = all_data.lose ";
	
	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	public int batchInsertTeamContinentGameScore() {
		Session session = entityManager.unwrap(Session.class);
		SQLQuery queryObj = session.createSQLQuery(sql_batchInsertTeamContinentGameScore);
		return queryObj.executeUpdate();
	}

	@Override
	public int batchUpdateTeamContinentGameScore() {
		Session session = entityManager.unwrap(Session.class);
		SQLQuery queryObj = session.createSQLQuery(sql_batchUpdateTeamContinentGameScore);
		return queryObj.executeUpdate();
	}

}
