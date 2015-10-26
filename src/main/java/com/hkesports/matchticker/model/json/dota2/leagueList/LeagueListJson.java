package com.hkesports.matchticker.model.json.dota2.leagueList;

import java.io.Serializable;

public class LeagueListJson implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private LeagueListResultJson result;

	public LeagueListResultJson getResult() {
		return result;
	}

	public void setResult(LeagueListResultJson result) {
		this.result = result;
	}
}
