package com.hkesports.matchticker.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tourament_game_team_score")
public class TouramentGameTeamScore implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Long touramentId;
	private Long gameID;
	private Long teamID;
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

	@Column(name = "tourament_id")
	public Long getTouramentId() {
		return touramentId;
	}

	public void setTouramentId(Long touramentId) {
		this.touramentId = touramentId;
	}
	
	@Column(name = "team_id")
	public Long getTeamID() {
		return teamID;
	}

	public void setTeamID(Long teamID) {
		this.teamID = teamID;
	}

	@Column(name = "game_id")
	public Long getGameID() {
		return gameID;
	}

	public void setGameID(Long gameID) {
		this.gameID = gameID;
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
