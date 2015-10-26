package com.hkesports.matchticker.model.json.dota2.livegames;

import java.util.List;

public class LiveGamesResultJson {
	private List<LiveGameJson> games;

	public List<LiveGameJson> getGames() {
		return games;
	}

	public void setGames(List<LiveGameJson> games) {
		this.games = games;
	}
}
