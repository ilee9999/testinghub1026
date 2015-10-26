package com.hkesports.matchticker.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.hkesports.matchticker.common.utils.MessageUtils;
import com.hkesports.matchticker.model.json.dota2.matchDetails.MatchDetailsResultJson;
import com.hkesports.matchticker.model.json.dota2.matchDetails.PlayerJson;

public class Dota2MatchGroupVo {

	private List<Long> teams;
	private List<Long> teamLeaders;
	private List<MatchDetailsResultJson> details;
	private List<Long> players;
	private Long firstGameId;
	private Long completeGameId;
	private Date startDate;
	private Date endDate;
	
	
	public Dota2MatchGroupVo() {
		teams = new ArrayList<>();
		teamLeaders = new ArrayList<>();
		details = new ArrayList<>();
		players = new ArrayList<>();
	}

	public void addDetail(MatchDetailsResultJson detail){
		if(detail.isTeamGame()){
			if(detail.getDireTeamId() != null)
				teams.add(detail.getDireTeamId());
			else if(detail.getDireCaptain() != null)
				teamLeaders.add(detail.getDireCaptain());
			
			if(detail.getRadiantTeamId() != null)
				teams.add(detail.getRadiantTeamId());
			else if(detail.getRadiantCaptain() != null)
				teamLeaders.add(detail.getRadiantCaptain());
		}else{
			for(PlayerJson playerJson : detail.getPlayers()){
				if(playerJson.getAccountId() != null)
					players.add(playerJson.getAccountId());
			}
		}
		details.add(detail);
		
		if(firstGameId == null || detail.getMatchId() < firstGameId)
			firstGameId = detail.getMatchId();
		
		if(detail.getDireTeamId() != null && detail.getDireCaptain() != null && detail.getRadiantTeamId() != null && detail.getRadiantCaptain() != null)
			completeGameId = detail.getMatchId();
		
		if(detail.getStartTime()!=null) {
			long startTime = detail.getStartTime() * 1000;
			if(startDate==null || startDate.getTime() > startTime) {
				startDate = new Date(startTime);
			}
			if(detail.getDuration()!=null) {
				long endTime = startTime + (detail.getDuration()*1000);
				if(endDate == null || endDate.getTime() < endTime) {
					endDate = new Date(endTime);
				}
			}
		}
	}
	
	public MatchDetailsResultJson getFirstGame(){
		if(firstGameId == null)
			return null;
		for(MatchDetailsResultJson json : details){
			if(json.getMatchId() == firstGameId)
				return json;
		}
		return null;
	}
	
	public MatchDetailsResultJson getCompleteGame(){
		if(completeGameId == null)
			return null;
		
		for(MatchDetailsResultJson json : details){
			if(json.getMatchId() == completeGameId)
				return json;
		}
		return null;
	}
	
	public Long getTeamId(Long leaderId) {
		if(leaderId == null)
			return null;
		for(MatchDetailsResultJson json : details){
			if(json.getRadiantTeamId()!=null && json.getRadiantLeaderId().longValue() == leaderId.longValue())
				return json.getRadiantTeamId();
			else if(json.getDireTeamId()!=null && json.getDireLeaderId().longValue() == leaderId.longValue())
				return json.getDireTeamId();
		}
		return null;
	}
	
	public List<Long> getTeams() {
		return teams;
	}

	public void setTeams(List<Long> teams) {
		this.teams = teams;
	}

	public List<MatchDetailsResultJson> getDetails() {
		return details;
	}

	public void setDetails(List<MatchDetailsResultJson> details) {
		this.details = details;
	}

	public List<Long> getPlayers() {
		return players;
	}

	public void setPlayers(List<Long> players) {
		this.players = players;
	}

	public boolean isTeamGame(){
		MatchDetailsResultJson firstGame = getFirstGame();
		return firstGame != null ? firstGame.isTeamGame() : false;
	}
	
	public Long getWinId(){
		MatchDetailsResultJson firstMatch = getFirstGame();
		if(firstMatch == null)
			return null;
		
		Long matchWin = null;
		int radiantWins = 0;
		int direWins = 0;
		
		long radiantId = 0;
		if(firstMatch.isTeamGame() && firstMatch.getRadiantTeamId() != null)
			radiantId = firstMatch.getRadiantTeamId();
		else{
			Long tmpRadiantId = firstMatch.getRadiantLeaderId();
			if(tmpRadiantId != null)
				radiantId = tmpRadiantId;
		}
		
		long direId = 0;
		if(firstMatch.isTeamGame() && firstMatch.getDireTeamId() != null){
			direId = firstMatch.getDireTeamId();
		}else{
			Long tmpDireId = firstMatch.getDireLeaderId();
			if(tmpDireId != null)
				direId = tmpDireId;
		}
		
		for(MatchDetailsResultJson detail : getDetails()){
			if(detail.getDuration() != null){
				long detailRadiantId = 0;
				if(detail.getRadiantTeamId() != null)
					detailRadiantId = detail.getRadiantTeamId();
				else if(detail.getRadiantLeaderId() != null)
					detailRadiantId = detail.getRadiantLeaderId();
				
				if(detail.getRadiantWin() != null && detail.getRadiantWin() == true)
					if(radiantId == detailRadiantId)
						radiantWins++;
					else
						direWins++;
				else
					if(radiantId == detailRadiantId)
						direWins++;
					else
						radiantWins++;
			}
		}
		
		if(radiantWins > direWins)
			matchWin = radiantId;
		else if(radiantWins < direWins)
			matchWin = direId;
		
		return matchWin;
	}
	
	public Long getRadiantLeaderId(){
		return getFirstGame().getRadiantLeaderId();
	}
	
	public Long getDireLeaderId(){
		return getFirstGame().getDireLeaderId();
	}
	
	public boolean equals(Object obj){
		if(obj instanceof MatchDetailsResultJson){
			MatchDetailsResultJson detail = (MatchDetailsResultJson) obj;
			return equalsMatchDetailsResultJson(detail);
		}else if(obj instanceof Dota2MatchGroupVo){
			Dota2MatchGroupVo group = (Dota2MatchGroupVo) obj;
			return equalsMatchDetailsResultJson(group.getFirstGame());
		}else
			return super.equals(obj);
	}
	
	private boolean equalsMatchDetailsResultJson(MatchDetailsResultJson detail){
		boolean isEquals = false;
		
		if(detail == null)
			return isEquals;
		
		if(isTeamGame() && detail.isTeamGame()){
			if((checkSameTeam(teams, detail.getRadiantTeamId()) || checkSameTeam(teamLeaders, detail.getRadiantCaptain()))
					&& (checkSameTeam(teams, detail.getDireTeamId()) || checkSameTeam(teamLeaders, detail.getDireCaptain())))
				isEquals = true;
		} else if(!isTeamGame() && !detail.isTeamGame()) {
			isEquals = true;
			for(PlayerJson playerJson : detail.getPlayers()){
				if(playerJson.getAccountId() != null && !players.contains(playerJson.getAccountId().longValue()))
					isEquals = false;
			}
		}
		
		return isEquals;
	}
	
	private boolean checkSameTeam(List<Long> list, Long id){
		if(id != null && list.contains(id.longValue()))
			return true;
		else
			return false;
	}
	
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
		.append("teams", MessageUtils.joinListToString(teams))
		.append("players", MessageUtils.joinListToString(players))
		.append("teamLeaders", MessageUtils.joinListToString(teamLeaders))
		.append("firstGameId", firstGameId)
		.append("details", getDetails())
		.build();
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
}
