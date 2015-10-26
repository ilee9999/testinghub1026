package com.hkesports.matchticker.model.json.lol.game;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.hkesports.matchticker.model.json.lol.schedule.ScheduleJson;

/**
 * @author manboyu
 *
 */
public class GameDetailJson implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private Date dateTime;
	private String winnerId;
	private String maxGames;
	private String gameNumber;
	private Integer gameLength;
	private String matchId;
	private String platformId;
	private Integer platformGameId;
	private Integer noVods;
	private String legsUrl;
	private Map<String, GameVodJson> vods = new HashMap<>();
	private Map<String, GameTeamJson> contestants = new HashMap<>();
	private Map<String, GamePlayerJson> players = new HashMap<>();
	private ScheduleJson scheduleJson;
	private GameTournamentJson tournament; 
	
	private transient Long gameCreation;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	public String getWinnerId() {
		return winnerId;
	}

	public void setWinnerId(String winnerId) {
		this.winnerId = winnerId;
	}

	public String getMaxGames() {
		return maxGames;
	}

	public void setMaxGames(String maxGames) {
		this.maxGames = maxGames;
	}

	public String getGameNumber() {
		return gameNumber;
	}

	public void setGameNumber(String gameNumber) {
		this.gameNumber = gameNumber;
	}

	public Integer getGameLength() {
		return gameLength;
	}

	public void setGameLength(Integer gameLength) {
		this.gameLength = gameLength;
	}

	public String getMatchId() {
		return matchId;
	}

	public void setMatchId(String matchId) {
		this.matchId = matchId;
	}

	public String getPlatformId() {
		return platformId;
	}

	public void setPlatformId(String platformId) {
		this.platformId = platformId;
	}

	public Integer getPlatformGameId() {
		return platformGameId;
	}

	public void setPlatformGameId(Integer platformGameId) {
		this.platformGameId = platformGameId;
	}

	public Integer getNoVods() {
		return noVods;
	}

	public void setNoVods(Integer noVods) {
		this.noVods = noVods;
	}

	public String getLegsUrl() {
		return legsUrl;
	}

	public void setLegsUrl(String legsUrl) {
		this.legsUrl = legsUrl;
	}

	public Map<String, GameVodJson> getVods() {
		return vods;
	}

	public void setVods(Map<String, GameVodJson> vods) {
		this.vods = vods;
	}

	public Map<String, GameTeamJson> getContestants() {
		return contestants;
	}

	public void setContestants(Map<String, GameTeamJson> contestants) {
		this.contestants = contestants;
	}

	public Map<String, GamePlayerJson> getPlayers() {
		return players;
	}

	public void setPlayers(Map<String, GamePlayerJson> players) {
		this.players = players;
	}
	
	public ScheduleJson getScheduleJson() {
		return scheduleJson;
	}

	public void setScheduleJson(ScheduleJson scheduleJson) {
		this.scheduleJson = scheduleJson;
	}
	
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
		.append("id", getId())
		.append("dateTime", getDateTime())
		.append("winnerId", getWinnerId())
		.append("maxGames", getMaxGames())
		.append("gameNumber", getGameNumber())
		.append("gameLength", getGameLength())
		.append("matchId", getMatchId())
		.append("platformId", getPlatformId())
		.append("platformGameId", getPlatformGameId())
		.append("noVods", getNoVods())
		.append("legsUrl", getLegsUrl())
		.append("vods", getVods())
		.append("contestants", getContestants())
		.append("players", getPlayers())
		.build();
	}

	public GameTournamentJson getTournament() {
		return tournament;
	}

	public void setTournament(GameTournamentJson tournament) {
		this.tournament = tournament;
	}

	public Long getGameCreation() {
		return gameCreation;
	}

	public void setGameCreation(Long gameCreation) {
		this.gameCreation = gameCreation;
	}
}
