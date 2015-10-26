package com.hkesports.matchticker.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Dota2TeamJsonVo {

	private Long teamId;
	private String name;
	private String tag;
	private Date timeCreated;
	private String rating;
	private Long logo;
	private Long logoSponsor;
	private String countryCode;
	private String url;
	private String gamesPlayedWithCurrentRoster;
	private List<Long> playerIds;
	private List<Long> leagueIds;

	public Long getTeamId() {
		return teamId;
	}

	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public Date getTimeCreated() {
		return timeCreated;
	}

	public void setTimeCreated(Date timeCreated) {
		this.timeCreated = timeCreated;
	}

	public Long getLogo() {
		return logo;
	}

	public void setLogo(Long logo) {
		this.logo = logo;
	}

	public Long getLogoSponsor() {
		return logoSponsor;
	}

	public void setLogoSponsor(Long logoSponsor) {
		this.logoSponsor = logoSponsor;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getGamesPlayedWithCurrentRoster() {
		return gamesPlayedWithCurrentRoster;
	}

	public void setGamesPlayedWithCurrentRoster(
			String gamesPlayedWithCurrentRoster) {
		this.gamesPlayedWithCurrentRoster = gamesPlayedWithCurrentRoster;
	}

	public List<Long> getPlayerIds() {
		return playerIds;
	}

	public void setPlayerIds(List<Long> playerIds) {
		this.playerIds = playerIds;
	}
	
	public void addPlayerId(Long id){
		if(this.playerIds == null)
			this.playerIds = new ArrayList<>();
		this.playerIds.add(id);
	}

	public List<Long> getLeagueIds() {
		return leagueIds;
	}
	
	public void setLeagueIds(List<Long> leagueIds) {
		this.leagueIds = leagueIds;
	}
	
	public void addLeagueId(Long id){
		if(this.leagueIds == null)
			this.leagueIds = new ArrayList<>();
		this.leagueIds.add(id);
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

}
