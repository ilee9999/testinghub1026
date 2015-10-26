package com.hkesports.matchticker.model.json.dota2.matchHistory;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class MatchJson implements Serializable {

	private static final long serialVersionUID = 1L;

	@SerializedName("match_id")
	private Long matchId;
	
	@SerializedName("match_seq_num")
	private String matchSeqNum;
	
	@SerializedName("start_time")
	private Long startTime;
	
	@SerializedName("lobby_type")
	private Short lobbyType;
	
	@SerializedName("radiant_team_id")
	private String radiantTeamId;
	
	@SerializedName("dire_team_id")
	private String direTeamId;
	
	private List<PlayerJson> players;

	public Long getMatchId() {
		return matchId;
	}

	public void setMatchId(Long matchId) {
		this.matchId = matchId;
	}

	public String getMatchSeqNum() {
		return matchSeqNum;
	}

	public void setMatchSeqNum(String matchSeqNum) {
		this.matchSeqNum = matchSeqNum;
	}

	public Long getStartTime() {
		return startTime;
	}

	public void setStartTime(Long startTime) {
		this.startTime = startTime;
	}

	public Short getLobbyType() {
		return lobbyType;
	}

	public void setLobbyType(Short lobbyType) {
		this.lobbyType = lobbyType;
	}

	public String getRadiantTeamId() {
		return radiantTeamId;
	}

	public void setRadiantTeamId(String radiantTeamId) {
		this.radiantTeamId = radiantTeamId;
	}

	public String getDireTeamId() {
		return direTeamId;
	}

	public void setDireTeamId(String direTeamId) {
		this.direTeamId = direTeamId;
	}

	public List<PlayerJson> getPlayers() {
		return players;
	}

	public void setPlayers(List<PlayerJson> players) {
		this.players = players;
	}
}
