package com.hkesports.matchticker.model.json.lol.legsUrl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * http://na.lolesports.com:80/api/game/{gameId}.json get legsURl link legsURl
 * get data setting here
 * 
 * Example: https://acs.leagueoflegends.com/v1/stats/game/TRTW/290521?gameHash=
 * f3ca88d2f540cc96
 * 
 * @author Administrator
 *
 */
public class LegsUrlJson implements Serializable {

	private static final long serialVersionUID = 1L;

	private String gameId;
	private String platformId;
	private Long gameCreation;
	private Integer gameDuration;
	private Integer queueId;
	private Integer mapId;
	private Integer seasonId;
	private String gameVersion;
	private String gameMode;
	private String gameType;
	private List<TeamJson> teams = new ArrayList<>();
	private List<ParticipantJson> participants = new ArrayList<>();
	private List<ParticipantIdentityJson> participantIdentities = new ArrayList<>();

	public String getGameId() {
		return gameId;
	}

	public void setGameId(String gameId) {
		this.gameId = gameId;
	}

	public String getPlatformId() {
		return platformId;
	}

	public void setPlatformId(String platformId) {
		this.platformId = platformId;
	}

	public Long getGameCreation() {
		return gameCreation;
	}

	public void setGameCreation(Long gameCreation) {
		this.gameCreation = gameCreation;
	}

	public Integer getGameDuration() {
		return gameDuration;
	}

	public void setGameDuration(Integer gameDuration) {
		this.gameDuration = gameDuration;
	}

	public Integer getQueueId() {
		return queueId;
	}

	public void setQueueId(Integer queueId) {
		this.queueId = queueId;
	}

	public Integer getMapId() {
		return mapId;
	}

	public void setMapId(Integer mapId) {
		this.mapId = mapId;
	}

	public Integer getSeasonId() {
		return seasonId;
	}

	public void setSeasonId(Integer seasonId) {
		this.seasonId = seasonId;
	}

	public String getGameVersion() {
		return gameVersion;
	}

	public void setGameVersion(String gameVersion) {
		this.gameVersion = gameVersion;
	}

	public String getGameMode() {
		return gameMode;
	}

	public void setGameMode(String gameMode) {
		this.gameMode = gameMode;
	}

	public String getGameType() {
		return gameType;
	}

	public void setGameType(String gameType) {
		this.gameType = gameType;
	}

	public List<TeamJson> getTeams() {
		return teams;
	}

	public void setTeams(List<TeamJson> teams) {
		this.teams = teams;
	}

	public List<ParticipantJson> getParticipants() {
		return participants;
	}

	public void setParticipants(List<ParticipantJson> participants) {
		this.participants = participants;
	}

	public List<ParticipantIdentityJson> getParticipantIdentities() {
		return participantIdentities;
	}

	public void setParticipantIdentities(List<ParticipantIdentityJson> participantIdentities) {
		this.participantIdentities = participantIdentities;
	}
	
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
		.append("gameId", getGameId())
		.append("platformId", getPlatformId())
		.append("gameCreation", getGameCreation())
		.append("gameDuration", getGameDuration())
		.append("queueId", getQueueId())
		.append("mapId", getMapId())
		.append("seasonId", getSeasonId())
		.append("gameVersion", getGameVersion())
		.append("gameMode", getGameMode())
		.append("gameType", getGameType())
		.append("teams", getTeams())
		.append("participants", getParticipants())
		.append("ParticipantIdentities", getParticipantIdentities())
		.append("participantIdentities", getParticipantIdentities())
		.build();
	}
}
