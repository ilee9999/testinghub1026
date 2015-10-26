package com.hkesports.matchticker.vo;

public class TeamScore {

	private Long scheduleId;
	private Long teamId;
	private String teamFullName;
	private Integer score = 0;
	
	
	public Long getScheduleId() {
		return scheduleId;
	}
	public void setScheduleId(Long scheduleId) {
		this.scheduleId = scheduleId;
	}
	public Long getTeamId() {
		return teamId;
	}
	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}
	public String getTeamFullName() {
		return teamFullName;
	}
	public void setTeamFullName(String teamFullName) {
		this.teamFullName = teamFullName;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}

}
