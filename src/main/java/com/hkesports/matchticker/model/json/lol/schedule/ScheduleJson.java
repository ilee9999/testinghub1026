package com.hkesports.matchticker.model.json.lol.schedule;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author manboyu
 *
 */
public class ScheduleJson implements Serializable {

	private static final long serialVersionUID = 1L;

	private Date dateTime;
	private String winnerId;
	private Long matchId;
	private String url;
	private String maxGames;
	private boolean isLive;
	private String isFinished;
	private String polldaddyId;
	private Map<String, GameJson> games = new HashMap<>();
	private String name;

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	public String getWinnerId() {
		return winnerId;
	}

	public void setWinnerId(String winnerId) {
		this.winnerId = winnerId;
	}

	public Long getMatchId() {
		return matchId;
	}

	public void setMatchId(Long matchId) {
		this.matchId = matchId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMaxGames() {
		return maxGames;
	}

	public void setMaxGames(String maxGames) {
		this.maxGames = maxGames;
	}

	public boolean isLive() {
		return isLive;
	}

	public void setLive(boolean isLive) {
		this.isLive = isLive;
	}

	public String getIsFinished() {
		return isFinished;
	}

	public void setIsFinished(String isFinished) {
		this.isFinished = isFinished;
	}

	public String getPolldaddyId() {
		return polldaddyId;
	}

	public void setPolldaddyId(String polldaddyId) {
		this.polldaddyId = polldaddyId;
	}

	public Map<String, GameJson> getGames() {
		return games;
	}

	public void setGames(Map<String, GameJson> games) {
		this.games = games;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
		.append("dateTime", getDateTime())
		.append("winnerId", getWinnerId())
		.append("matchId", getMatchId())
		.append("url", getUrl())
		.append("maxGames", getMaxGames())
		.append("isLive", isLive())
		.append("isFinished", getIsFinished())
		.append("polldaddyId", getPolldaddyId())
		.append("games", getGames())
		.append("name", getName())
		.build();
	}
}
