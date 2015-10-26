package com.hkesports.matchticker.model.json.dota2.matchDetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;
import com.hkesports.matchticker.enums.Dota2TeamType;
import com.hkesports.matchticker.vo.Dota2MatchGroupVo;

public class MatchDetailsResultJson implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private List<PlayerJson> players;
	
	@SerializedName("radiant_win")
	private Boolean radiantWin;
	
	private Integer duration;
	
	@SerializedName("start_time")
	private Long startTime;
	
	@SerializedName("match_id")
	private Long matchId;
	
	@SerializedName("match_seq_num")
	private String matchSeqNum;
	
	@SerializedName("tower_status_radiant")
	private Integer towerStatusRadiant;
	
	@SerializedName("tower_status_dire")
	private Integer towerStatusDire;
	
	@SerializedName("barracks_status_radiant")
	private Integer barracksStatusRadiant;
	
	@SerializedName("barracks_status_dire")
	private Integer barracksStatusDire;
	
	private Integer cluster;
	
	@SerializedName("first_blood_time")
	private Integer firstBloodTime;
	
	@SerializedName("lobby_type")
	private Short lobbyType;
	
	@SerializedName("human_players")
	private Integer humanPlayers;
	
	@SerializedName("leagueid")
	private String leagueId;
	
	@SerializedName("positive_votes")
	private Integer positiveVotes;
	
	@SerializedName("negative_votes")
	private Integer negativeVotes;
	
	@SerializedName("game_mode")
	private Short gameMode;
	
	private Integer engine;
	
	@SerializedName("radiant_team_id")
	private Long radiantTeamId;
	
	@SerializedName("radiant_name")
	private String radiantName;
	
	@SerializedName("radiant_logo")
	private String radiantLogo;
	
	@SerializedName("radiant_team_complete")
	private Integer radiantTeamComplete;
	
	@SerializedName("radiant_captain")
	private Long radiantCaptain;
	
	@SerializedName("dire_team_id")
	private Long direTeamId;
	
	@SerializedName("dire_name")
	private String direName;
	
	@SerializedName("dire_logo")
	private String direLogo;
	
	@SerializedName("dire_team_complete")
	private Integer direTeamComplete;
	
	@SerializedName("dire_captain")
	private Long direCaptain;
	
	@SerializedName("picks_bans")
	private List<PicksBansJson> picksBans;

	public List<PlayerJson> getPlayers() {
		return players;
	}

	public void setPlayers(List<PlayerJson> players) {
		this.players = players;
	}

	public Boolean getRadiantWin() {
		return radiantWin;
	}

	public void setRadiantWin(Boolean radiantWin) {
		this.radiantWin = radiantWin;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public Long getStartTime() {
		return startTime;
	}

	public void setStartTime(Long startTime) {
		this.startTime = startTime;
	}

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

	public Integer getTowerStatusRadiant() {
		return towerStatusRadiant;
	}

	public void setTowerStatusRadiant(Integer towerStatusRadiant) {
		this.towerStatusRadiant = towerStatusRadiant;
	}

	public Integer getTowerStatusDire() {
		return towerStatusDire;
	}

	public void setTowerStatusDire(Integer towerStatusDire) {
		this.towerStatusDire = towerStatusDire;
	}

	public Integer getBarracksStatusRadiant() {
		return barracksStatusRadiant;
	}

	public void setBarracksStatusRadiant(Integer barracksStatusRadiant) {
		this.barracksStatusRadiant = barracksStatusRadiant;
	}

	public Integer getBarracksStatusDire() {
		return barracksStatusDire;
	}

	public void setBarracksStatusDire(Integer barracksStatusDire) {
		this.barracksStatusDire = barracksStatusDire;
	}

	public Integer getCluster() {
		return cluster;
	}

	public void setCluster(Integer cluster) {
		this.cluster = cluster;
	}

	public Integer getFirstBloodTime() {
		return firstBloodTime;
	}

	public void setFirstBloodTime(Integer firstBloodTime) {
		this.firstBloodTime = firstBloodTime;
	}

	public Short getLobbyType() {
		return lobbyType;
	}

	public void setLobbyType(Short lobbyType) {
		this.lobbyType = lobbyType;
	}

	public Integer getHumanPlayers() {
		return humanPlayers;
	}

	public void setHumanPlayers(Integer humanPlayers) {
		this.humanPlayers = humanPlayers;
	}

	public String getLeagueId() {
		return leagueId;
	}

	public void setLeagueId(String leagueId) {
		this.leagueId = leagueId;
	}

	public Integer getPositiveVotes() {
		return positiveVotes;
	}

	public void setPositiveVotes(Integer positiveVotes) {
		this.positiveVotes = positiveVotes;
	}

	public Integer getNegativeVotes() {
		return negativeVotes;
	}

	public void setNegativeVotes(Integer negativeVotes) {
		this.negativeVotes = negativeVotes;
	}

	public Short getGameMode() {
		return gameMode;
	}

	public void setGameMode(Short gameMode) {
		this.gameMode = gameMode;
	}

	public Integer getEngine() {
		return engine;
	}

	public void setEngine(Integer engine) {
		this.engine = engine;
	}

	public Long getRadiantTeamId() {
		return radiantTeamId;
	}

	public void setRadiantTeamId(Long radiantTeamId) {
		this.radiantTeamId = radiantTeamId;
	}

	public String getRadiantName() {
		return radiantName;
	}

	public void setRadiantName(String radiantName) {
		this.radiantName = radiantName;
	}

	public String getRadiantLogo() {
		return radiantLogo;
	}

	public void setRadiantLogo(String radiantLogo) {
		this.radiantLogo = radiantLogo;
	}

	public Integer getRadiantTeamComplete() {
		return radiantTeamComplete;
	}

	public void setRadiantTeamComplete(Integer radiantTeamComplete) {
		this.radiantTeamComplete = radiantTeamComplete;
	}

	public Long getDireTeamId() {
		return direTeamId;
	}

	public void setDireTeamId(Long direTeamId) {
		this.direTeamId = direTeamId;
	}

	public String getDireName() {
		return direName;
	}

	public void setDireName(String direName) {
		this.direName = direName;
	}

	public String getDireLogo() {
		return direLogo;
	}

	public void setDireLogo(String direLogo) {
		this.direLogo = direLogo;
	}

	public Integer getDireTeamComplete() {
		return direTeamComplete;
	}

	public void setDireTeamComplete(Integer direTeamComplete) {
		this.direTeamComplete = direTeamComplete;
	}
	
	public Long getRadiantCaptain() {
		return radiantCaptain;
	}

	public void setRadiantCaptain(Long radiantCaptain) {
		this.radiantCaptain = radiantCaptain;
	}

	public Long getDireCaptain() {
		return direCaptain;
	}

	public void setDireCaptain(Long direCaptain) {
		this.direCaptain = direCaptain;
	}

	public List<PicksBansJson> getPicksBans() {
		return picksBans;
	}

	public void setPicksBans(List<PicksBansJson> picksBans) {
		this.picksBans = picksBans;
	}
	
	//match id 916328589 資料不完整 缺主第一隊的團隊id
	public boolean isTeamGame(){
		boolean isTeam = false;
		if((radiantTeamId != null && radiantTeamId > 0) && (direTeamId != null && direTeamId > 0))
			isTeam = true;
		
		if(getRadiantPlayerIds().size() > 1 || getDirePlayerIds().size() > 1)
			isTeam = true;
		
		return isTeam;
	}
	
	public Long getRadiantLeaderId(){
		if(radiantCaptain != null && getRadiantPlayerIds().contains(radiantCaptain)) 
			return radiantCaptain;
		else
			return getTeamLeaderId(Dota2TeamType.RADIANT);
	}
	
	public Long getDireLeaderId(){
		if(direCaptain != null && getDirePlayerIds().contains(direCaptain))
			return direCaptain;
		else
			return getTeamLeaderId(Dota2TeamType.DIRE);
	}
	
	private Long getTeamLeaderId(Dota2TeamType teamType){
		Long leaderId = null;
		int slot = Integer.MAX_VALUE;
		for(Long id : getTeamPlayerIds(teamType)){
			for(PlayerJson player : players){
				if(player.getAccountId() == id && slot >= player.getPlayerSlot()){
					leaderId = player.getAccountId();
					slot = player.getPlayerSlot();
				}
			}
		}
		return leaderId;
	}
	
	public List<Long> getRadiantPlayerIds(){
		return getTeamPlayerIds(Dota2TeamType.RADIANT);
	}
	
	public List<Long> getDirePlayerIds(){
		return getTeamPlayerIds(Dota2TeamType.DIRE);
	}
	
	private List<Long> getTeamPlayerIds(Dota2TeamType teamType){
		List<Long> list = new ArrayList<>();
		
		for(PlayerJson player : players){
			if(((teamType == Dota2TeamType.RADIANT && player.getPlayerSlot() < 128) || (teamType == Dota2TeamType.DIRE && player.getPlayerSlot() > 127)) 
					&& player.getAccountId() != null)
				list.add(player.getAccountId());
		}
		
		return list;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Dota2MatchGroupVo) {
			return obj.equals(this);
		} else {
			return super.equals(obj);
		}
	}
	
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("radiantWin: ").append(this.radiantWin).append(", ");
		sb.append("duration: ").append(this.duration).append(", ");
		sb.append("startTime: ").append(this.startTime).append(", ");
		sb.append("matchId: ").append(this.matchId).append(", ");
		sb.append("matchSeqNum: ").append(this.matchSeqNum).append(", ");
		sb.append("towerStatusRadiant: ").append(this.towerStatusRadiant).append(", ");
		sb.append("towerStatusDire: ").append(this.towerStatusDire).append(", ");
		sb.append("barracksStatusRadiant: ").append(this.barracksStatusRadiant).append(", ");
		sb.append("barracksStatusDire: ").append(this.barracksStatusDire).append(", ");
		sb.append("cluster: ").append(this.cluster).append(", ");
		sb.append("firstBloodTime: ").append(this.firstBloodTime).append(", ");
		sb.append("lobbyType: ").append(this.lobbyType).append(", ");
		sb.append("humanPlayers: ").append(this.humanPlayers).append(", ");
		sb.append("leagueId: ").append(this.leagueId).append(", ");
		sb.append("positiveVotes: ").append(this.positiveVotes).append(", ");
		sb.append("negativeVotes: ").append(this.negativeVotes).append(", ");
		sb.append("gameMode: ").append(this.gameMode).append(", ");
		sb.append("engine: ").append(this.engine).append(", ");
		sb.append("radiantTeamId: ").append(this.radiantTeamId).append(", ");
		sb.append("radiantName: ").append(this.radiantName).append(", ");
		sb.append("radiantLogo: ").append(this.radiantLogo).append(", ");
		sb.append("radiantTeamComplete: ").append(this.radiantTeamComplete).append(", ");
		sb.append("radiantCaptain: ").append(this.radiantCaptain).append(", ");
		sb.append("direTeamId: ").append(this.direTeamId).append(", ");
		sb.append("direName: ").append(this.direName).append(", ");
		sb.append("direLogo: ").append(this.direLogo).append(", ");
		sb.append("direTeamComplete: ").append(this.direTeamComplete).append(", ");
		sb.append("direCaptain: ").append(this.direCaptain);

		return sb.toString();
	}
}
