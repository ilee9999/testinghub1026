package com.hkesports.matchticker.model.json.dota2.teamInfo;

import java.io.Serializable;

public class TeamInfoJson implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private TeamInfoResultJson result;

	public TeamInfoResultJson getResult() {
		return result;
	}

	public void setResult(TeamInfoResultJson result) {
		this.result = result;
	}
}
