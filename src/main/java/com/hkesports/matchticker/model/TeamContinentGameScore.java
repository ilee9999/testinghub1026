package com.hkesports.matchticker.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "team_continent_game_score")
public class TeamContinentGameScore {

	private Long id;
	private Long continentGameId;
	private Long teamId;
	private Short score;
	private Short winCount;
	private Short drawCount;
	private Short loseCount;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", columnDefinition = "BIGINT(20)", nullable = false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "continent_game_id")
	public Long getContinentGameId() {
		return continentGameId;
	}

	public void setContinentGameId(Long continentGameId) {
		this.continentGameId = continentGameId;
	}

	@Column(name = "team_id")
	public Long getTeamId() {
		return teamId;
	}

	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}

	@Column(name = "score")
	public Short getScore() {
		return score;
	}

	public void setScore(Short score) {
		this.score = score;
	}

	@Column(name = "win_count")
	public Short getWinCount() {
		return winCount;
	}

	public void setWinCount(Short winCount) {
		this.winCount = winCount;
	}

	@Column(name = "draw_count")
	public Short getDrawCount() {
		return drawCount;
	}

	public void setDrawCount(Short drawCount) {
		this.drawCount = drawCount;
	}

	@Column(name = "lose_count")
	public Short getLoseCount() {
		return loseCount;
	}

	public void setLoseCount(Short loseCount) {
		this.loseCount = loseCount;
	}

}
