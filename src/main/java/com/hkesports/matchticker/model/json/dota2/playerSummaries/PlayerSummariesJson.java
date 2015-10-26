package com.hkesports.matchticker.model.json.dota2.playerSummaries;

import java.io.Serializable;

public class PlayerSummariesJson implements Serializable {
	private static final long serialVersionUID = 1L;

	private PlayerSummariesResultJson response;

	public PlayerSummariesResultJson getResponse() {
		return response;
	}

	public void setResponse(PlayerSummariesResultJson response) {
		this.response = response;
	}
}
