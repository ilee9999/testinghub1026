package com.hkesports.matchticker.service.lol;

import java.io.IOException;
import java.util.List;

import com.hkesports.matchticker.model.batchJob.League;
import com.hkesports.matchticker.model.batchJob.Team;
import com.hkesports.matchticker.model.batchJob.TeamMember;
import com.hkesports.matchticker.model.batchJob.Tourament;
import com.hkesports.matchticker.model.json.lol.game.GameDetailJson;
import com.hkesports.matchticker.model.json.lol.leagues.LeagueJson;
import com.hkesports.matchticker.model.json.lol.legsUrl.LegsUrlJson;
import com.hkesports.matchticker.model.json.lol.player.PlayerJson;
import com.hkesports.matchticker.model.json.lol.schedule.GameJson;
import com.hkesports.matchticker.model.json.lol.schedule.ScheduleJson;
import com.hkesports.matchticker.model.json.lol.team.TeamJson;
import com.hkesports.matchticker.model.json.lol.tournaments.TournamentJson;

/**
 * @author manboyu
 *
 */
public interface LoLTouramentService {

	public List<LeagueJson> getLeagueJson() throws Exception;
	
	public LeagueJson getLeagueJsonById(String leagueId) throws Exception;
	
	/**
	 * getTournament By League.getLeagueTournaments()
	 * 
	 * @param tournamentId
	 * @return
	 * @throws IOException
	 */
	public TournamentJson getTournamentJson(String tournamentId) throws Exception;
	
	/**
	 * getSchedule By tournamentId
	 * 
	 * @param tournamentId
	 * @return
	 * @throws IOException
	 */
	public List<ScheduleJson> getScheduleJsons(String tournamentId) throws Exception;
	
	/**
	 * getGameInfo By ScheduleJson
	 * 
	 * @param scheduleJson
	 * @return
	 */
	public List<GameJson> getGameJson(ScheduleJson scheduleJson);
	
	/**
	 * getGameDetail By gameInfo
	 * 
	 * @param gameList
	 * @return
	 * @throws IOException
	 */
	public List<GameDetailJson> getGameDetailJson(List<GameJson> gameList) throws Exception;
	
	/**
	 * getLegsUrlJson By ScheduleGame.getLegsUrl
	 * 
	 * @param legsUrl
	 * @return
	 * @throws IOException
	 */
	public LegsUrlJson getLegsUrlJson(String legsUrl) throws Exception;
	
	/**
	 * getTeamJson By gameTeamJson.getId
	 * 
	 * @param gameTeamJsonId
	 * @return
	 */
	public TeamJson getTeamJson(Long gameTeamJsonId) throws Exception;
	
	/**
	 * getTeamJson By playerId
	 * 
	 * @param playerId
	 * @return
	 * @throws IOException
	 */
	public PlayerJson getPlayerJson(Long playerId) throws Exception;
	
	public List<Tourament> findTouraments();
	
	public List<League> findLeagues();
	
	public List<TeamMember> findTeamMembersByTournamentAndTeam(Tourament tourament,Team team);
	
	public void saveLeague() throws Exception;
	
	public void saveMatchInfos(boolean allFetch) throws Exception;
}
