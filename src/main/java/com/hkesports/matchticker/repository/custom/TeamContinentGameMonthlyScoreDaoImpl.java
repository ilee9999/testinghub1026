package com.hkesports.matchticker.repository.custom;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.SQLQuery;
import org.hibernate.Session;

public class TeamContinentGameMonthlyScoreDaoImpl implements TeamContinentGameMonthlyScoreDaoCustom {
	
	private static final String sql_batchInsertTeamContinentGameMonthlyScore = 
					"insert into team_continent_game_monthly_score(team_continent_game_id, year, month, win_count, draw_count, lose_count, score, rank) " + 
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
					"from match_ticker.team_continent_game_score tgs " + 
					"inner join continent_game cg on cg.id = tgs.continent_game_id " + 
					"inner join schedule_game_detail sgd on sgd.team_id = tgs.team_id " + 
					"inner join schedule_game sg on sg.id = sgd.schedule_game_id " + 
					"inner join schedule s on s.id = sg.schedule_id and s.game_id = cg.game_id " + 
					"inner join game g on g.id = cg.game_id " + 
					"inner join tourament t on t.id = s.tourament_id " + 
					"left join tourament_game tg on tg.tourament_id = t.id and tg.game_id = g.id " + 
					"where not exists ( " + 
					"	select tgms.id from team_continent_game_monthly_score tgms " + 
					"	where tgms.team_continent_game_id = tgs.id " + 
					") " + 
					"group by tgs.id, year, month ";
	
	private static final String sql_batchUpdateTeamContinentGameMonthlyScore = 
					"update team_continent_game_monthly_score as tgms " + 
					"inner join ( " + 
					"	select " + 
					"		tgs.id, " + 
					"		year(ifnull(s.start_time, s.date_time)) year, month(ifnull(s.start_time, s.date_time)) month, " + 
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
					"		end) score, " + 
					"		0 as rank " + 
					"	from match_ticker.team_continent_game_score tgs " + 
					"	inner join continent_game cg on cg.id = tgs.continent_game_id " + 
					"	inner join schedule_game_detail sgd on sgd.team_id = tgs.team_id " + 
					"	inner join schedule_game sg on sg.id = sgd.schedule_game_id " + 
					"	inner join schedule s on s.id = sg.schedule_id and s.game_id = cg.game_id " + 
					"	inner join game g on g.id = cg.game_id " + 
					"	inner join tourament t on t.id = s.tourament_id " + 
					"	left join tourament_game tg on tg.tourament_id = t.id and tg.game_id = g.id " + 
					"	where exists ( " + 
					"		select tgms.id from team_continent_game_monthly_score tgms " + 
					"		where tgms.team_continent_game_id = tgs.id and tgms.score <> score " + 
					"	) " + 
					"	group by tgs.id, year, month " + 
					") all_data on all_data.id = tgms.team_continent_game_id " + 
					"	and all_data.year = tgms.year " + 
					"	and all_data.month = tgms.month " + 
					"set tgms.win_count = all_data.win_count, " + 
					"	tgms.draw_count = all_data.draw_count, " + 
					"	tgms.lose_count = all_data.lose_count, " + 
					"	tgms.score = all_data.score ";
	
	private static final String sql_batchUpdateTeamContinentGameMonthlyRank = 
					"update team_continent_game_monthly_score as tgms " + 
					"inner join ( " + 
					"	select s.*, " + 
					"	@prev\\:=@curr, @curr\\:=s.score, " + 
					"	@rank\\:=(case when @continent_game=s.continent_game_id and @gdate=concat(s.year, lpad(s.month, 2, 0)) then if(@prev=@curr, @rank, @rank + @rcount) else 1 end) as rank, " + 
					"	@rcount\\:=(case when @continent_game=s.continent_game_id and @gdate=concat(s.year, lpad(s.month, 2, 0)) then if(@prev=@curr, @rcount+1, 1) else 1 end) as rcount, " + 
					"	@continent_game\\:=s.continent_game_id, @gdate\\:=concat(s.year, lpad(s.month, 2, 0)) as gdate " + 
					"	from ( " + 
					"	select tgs.continent_game_id, tgs.team_id, tgms.id, tgms.year, tgms.month, tgms.score " + 
					"	from team_continent_game_monthly_score tgms, team_continent_game_score tgs " + 
					"	where tgs.id = tgms.team_continent_game_id " + 
					"	order by tgs.continent_game_id, tgms.year, tgms.month, tgms.score desc " + 
					"	) s, (select @curr\\:=null, @prev\\:=null, @rank\\:= 0, @continent_game\\:=null, @gdate\\:=null, @rcount\\:=1) t2 " + 
					"	order by s.continent_game_id, s.year, s.month, s.score desc " + 
					") all_data on all_data.id = tgms.id " + 
					"set tgms.rank = all_data.rank " + 
					"where tgms.rank <> all_data.rank ";
	
	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	public int batchInsertTeamContinentGameMonthlyScore() {
		Session session = entityManager.unwrap(Session.class);
		SQLQuery queryObj = session.createSQLQuery(sql_batchInsertTeamContinentGameMonthlyScore);
		return queryObj.executeUpdate();
	}

	@Override
	public int batchUpdateTeamContinentGameMonthlyScore() {
		Session session = entityManager.unwrap(Session.class);
		SQLQuery queryObj = session.createSQLQuery(sql_batchUpdateTeamContinentGameMonthlyScore);
		return queryObj.executeUpdate();
	}

	@Override
	public int batchUpdateTeamContinentGameMonthlyRank() {
		Session session = entityManager.unwrap(Session.class);
		SQLQuery queryObj = session.createSQLQuery(sql_batchUpdateTeamContinentGameMonthlyRank);
		return queryObj.executeUpdate();
	}
}
