package com.hkesports.matchticker.model.json.lol.game;

import java.io.Serializable;

public class GameTournamentJson implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String name;
	private Integer round;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getRound() {
		return round;
	}

	public void setRound(Integer round) {
		this.round = round;
	}
}
