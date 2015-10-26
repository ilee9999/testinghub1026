package com.hkesports.matchticker.model.json.dota2.playerSummaries;

import java.io.Serializable;
import java.util.List;

public class PlayerSummariesResultJson implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<PlayerJson> players;

	public List<PlayerJson> getPlayers() {
		return players;
	}

	public void setPlayers(List<PlayerJson> players) {
		this.players = players;
	}
}
