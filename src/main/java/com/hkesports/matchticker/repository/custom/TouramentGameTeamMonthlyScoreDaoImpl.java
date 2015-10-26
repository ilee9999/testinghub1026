package com.hkesports.matchticker.repository.custom;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.SQLQuery;
import org.hibernate.Session;

public class TouramentGameTeamMonthlyScoreDaoImpl implements TouramentGameTeamMonthlyScoreDaoCustom {
	
	@PersistenceContext
	EntityManager entityManager;
	
	private static final String sql_batchInsertTouramentGameTeamMonthlyScore = 
				"insert into tourament_game_team_monthly_score(tourament_game_team_id, year, month, win_count, draw_count, lose_count, score, rank) " + 
				"select " + 
				"	tgs.id, " + 
				"	year(ifnull(s.start_time, s.date_time)) year, month(ifnull(s.start_time, s.date_time)) month, " + 
				"	sum(case when lower(sgd.win) = 'win' then 1 else 0 end) win_count, " + 
				"	sum(case when lower(sgd.win) = 'draw' then 1 else 0 end) draw_count, " + 
				"	sum(case when lower(sgd.win) = 'fail' then 1 else 0 end) lose_count, " + 
				"	sum( " + 
				"	case " + 
				"	when lower(sgd.win) = 'win' then " + 
				"	case when tg.win_score is not null then tg.win_score when t.win_score is not null then t.win_score when g.win_score is not null then g.win_score else 3 end " + 
				"	when lower(sgd.win) = 'draw' then " + 
				"	case when tg.draw_score is not null then tg.draw_score when t.draw_score is not null then t.draw_score when g.draw_score is not null then g.draw_score else 1 end " + 
				"	else " + 
				"	case when tg.lose_score is not null then tg.lose_score when t.lose_score is not null then t.lose_score when g.lose_score is not null then g.lose_score else 0 end " + 
				"	end) score, " + 
				"	0 as rank " + 
				"from match_ticker.tourament_game_team_score tgs " + 
				"inner join tourament t on t.id = tgs.tourament_id " + 
				"inner join schedule s on s.tourament_id = t.id " + 
				"inner join schedule_game sg on sg.schedule_id = s.id " + 
				"inner join schedule_game_detail sgd on sgd.schedule_game_id = sg.id and sgd.team_id = tgs.team_id " + 
				"inner join game g on g.id = tgs.game_id " + 
				"left join tourament_game tg on tg.tourament_id = tgs.tourament_id and tg.game_id = tgs.game_id " + 
				"where not exists ( " + 
				"	select tgms.id from tourament_game_team_monthly_score tgms " + 
				"	where tgms.tourament_game_team_id = tgs.id " + 
				") " + 
				"group by tgs.id, year, month ";
	
	private static final String sql_batchUpdateTouramentGameTeamMonthlyScore = 
				"update team_game_monthly_score as tgms " + 
				"inner join ( " + 
				"	select " + 
				"		tgs.id, " + 
				"		year(ifnull(sg.date_time, s.date_time)) year, " + 
				"		month(ifnull(sg.date_time, s.date_time)) month, " + 
				"		sum(case when lower(sgd.win) = 'win' then 1 else 0 end) win_count, " + 
				"		sum(case when lower(sgd.win) = 'draw' then 1 else 0 end) draw_count, " + 
				"		sum(case when lower(sgd.win) = 'fail' then 1 else 0 end) lose_count, " + 
				"		sum( " + 
				"		case " + 
				"		when lower(sgd.win) = 'win' then " + 
				"		case when tg.win_score is not null then tg.win_score when t.win_score is not null then t.win_score when g.win_score is not null then g.win_score else 3 end " + 
				"		when lower(sgd.win) = 'draw' then " + 
				"		case when tg.draw_score is not null then tg.draw_score when t.draw_score is not null then t.draw_score when g.draw_score is not null then g.draw_score else 1 end " + 
				"		else " + 
				"		case when tg.lose_score is not null then tg.lose_score when t.lose_score is not null then t.lose_score when g.lose_score is not null then g.lose_score else 0 end " + 
				"		end) score " + 
				"	from match_ticker.team_game_score tgs " + 
				"	inner join schedule s on s.game_id = tgs.game_id " + 
				"	inner join game g on g.id = s.game_id " + 
				"	inner join schedule_game sg on sg.schedule_id = s.id " + 
				"	inner join schedule_game_detail sgd on sgd.schedule_game_id = sg.id and sgd.team_id = tgs.team_id " + 
				"	inner join tourament t on t.id = s.tourament_id " + 
				"	left join tourament_game tg on tg.tourament_id = t.id and tg.game_id = g.id " + 
				"	where exists ( " + 
				"		select tgms.id from team_game_monthly_score tgms " + 
				"		where tgms.team_game_id = tgs.id and tgms.score <> score " + 
				"	) " + 
				"	group by tgs.id, year, month	 " + 
				") all_data on all_data.id = tgms.team_game_id " + 
				"	and all_data.year = tgms.year " + 
				"	and all_data.month = tgms.month " + 
				"set tgms.win_count = all_data.win_count, " + 
				"	tgms.draw_count = all_data.draw_count, " + 
				"	tgms.lose_count = all_data.lose_count, " + 
				"	tgms.score = all_data.score ";
	
	private static final String sql_batchUpdateTouramentGameTeamMonthlyRank = 
				"update tourament_game_team_monthly_score as tgms " + 
				"inner join ( " + 
				"	select s.*, " + 
				"	@prev\\:=@curr, @curr\\:=s.score, " + 
				"	@rank\\:=(case when @tourament=s.tourament_id and @gdate=concat(s.year, lpad(s.month, 2, 0)) then if(@prev=@curr, @rank, @rank + @rcount) else 1 end) as rank, " + 
				"	@rcount\\:=(case when @tourament=s.tourament_id and @gdate=concat(s.year, lpad(s.month, 2, 0)) then if(@prev=@curr, @rcount+1, 1) else 1 end), " + 
				"	@tourament\\:=s.tourament_id, @gdate\\:=concat(s.year, lpad(s.month, 2, 0)) " + 
				"	from ( " + 
				"	select tgs.tourament_id, tgs.team_id, tgms.id, tgms.year, tgms.month, tgms.score " + 
				"	from tourament_game_team_monthly_score tgms, tourament_game_team_score tgs " + 
				"	where tgs.id = tgms.tourament_game_team_id " + 
				"	order by tgs.tourament_id, tgms.year, tgms.month, tgms.score desc " + 
				"	) s, (select @curr\\:=null, @prev\\:=null, @rank\\:= 0, @tourament\\:=null, @gdate\\:=null, @rcount\\:=1) t2 " + 
				") all_data on all_data.id = tgms.id " + 
				"set tgms.rank = all_data.rank " + 
				"where tgms.rank <> all_data.rank ";
	
	
	@Override
	public int batchInsertTouramentGameTeamMonthlyScore() {
		Session session = entityManager.unwrap(Session.class);
		SQLQuery queryObj = session.createSQLQuery(sql_batchInsertTouramentGameTeamMonthlyScore);
		return queryObj.executeUpdate();
	}

	@Override
	public int batchUpdateTouramentGameTeamMonthlyScore() {
		Session session = entityManager.unwrap(Session.class);
		SQLQuery queryObj = session.createSQLQuery(sql_batchUpdateTouramentGameTeamMonthlyScore);
		return queryObj.executeUpdate();
	}

	@Override
	public int batchUpdateTouramentGameTeamMonthlyRank() {
		Session session = entityManager.unwrap(Session.class);
		SQLQuery queryObj = session.createSQLQuery(sql_batchUpdateTouramentGameTeamMonthlyRank);
		return queryObj.executeUpdate();
	}
}
