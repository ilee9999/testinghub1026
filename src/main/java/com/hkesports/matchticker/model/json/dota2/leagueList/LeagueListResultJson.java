package com.hkesports.matchticker.model.json.dota2.leagueList;

import java.io.Serializable;
import java.util.List;

public class LeagueListResultJson implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<LeagueJson> leagues;

	public List<LeagueJson> getLeagues() {
		return leagues;
	}

	public void setLeagues(List<LeagueJson> leagues) {
		this.leagues = leagues;
	}	
}
