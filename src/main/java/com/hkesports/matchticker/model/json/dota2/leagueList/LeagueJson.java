package com.hkesports.matchticker.model.json.dota2.leagueList;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

public class LeagueJson implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name;
	
	private Long leagueid;
	
	private String description;
	
	@SerializedName("tournament_url")
	private String tournamentUrl;
	
	private String itemdef;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getLeagueid() {
		return leagueid;
	}

	public void setLeagueid(Long leagueid) {
		this.leagueid = leagueid;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTournamentUrl() {
		return tournamentUrl;
	}

	public void setTournamentUrl(String tournamentUrl) {
		this.tournamentUrl = tournamentUrl;
	}

	public String getItemdef() {
		return itemdef;
	}

	public void setItemdef(String itemdef) {
		this.itemdef = itemdef;
	}
}
