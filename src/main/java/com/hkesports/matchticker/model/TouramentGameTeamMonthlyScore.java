package com.hkesports.matchticker.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tourament_game_team_monthly_score")
public class TouramentGameTeamMonthlyScore implements Serializable {
private static final long serialVersionUID = 1L;
	
	private Long id;
	private Long touramentGameTeamId;//tourament_game_team_id
	private Short score;
	private Short year;
	private Short month;
	private Short winCount;
	private Short drawCount;
	private Short loseCount;
	private Short rank;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", columnDefinition = "BIGINT(20)", nullable = false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "tourament_game_team_id")
	public Long getTouramentGameTeamId() {
		return touramentGameTeamId;
	}

	public void setTouramentGameTeamId(Long touramentGameTeamId) {
		this.touramentGameTeamId = touramentGameTeamId;
	}
	
	@Column(name = "year")
	public Short getYear() {
		return year;
	}

	public void setYear(Short year) {
		this.year = year;
	}

	@Column(name = "month")
	public Short getMonth() {
		return month;
	}

	public void setMonth(Short month) {
		this.month = month;
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

	@Column(name = "rank")
	public Short getRank() {
		return rank;
	}

	public void setRank(Short rank) {
		this.rank = rank;
	}

}
