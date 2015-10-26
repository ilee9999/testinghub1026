package com.hkesports.matchticker.model.batchJob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.hkesports.matchticker.model.basic.BasicModel;

/**
 * @author manboyu
 *
 */
@Entity
@Table(name = "live_streams_r")
public class LiveStreams extends BasicModel {

	private static final long serialVersionUID = 1L;

	private String type;
	private String url;
	private String embedCode;
	private String language;
	private League league;
	private ScheduleGame scheduleGame;
	
	@Column(name = "type", length = 100, nullable = true)
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "url", length = 255, nullable = true)
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "embed_code", columnDefinition = "TEXT", nullable = true)
	public String getEmbedCode() {
		return embedCode;
	}

	public void setEmbedCode(String embedCode) {
		this.embedCode = embedCode;
	}

	@Column(name = "language", length = 100, nullable = true)
	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "league_id", columnDefinition = "BIGINT(20)", nullable = true)
	public League getLeague() {
		return league;
	}

	public void setLeague(League league) {
		this.league = league;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "schedule_game_id", columnDefinition = "BIGINT(20)", nullable = true)
	public ScheduleGame getScheduleGame() {
		return scheduleGame;
	}

	public void setScheduleGame(ScheduleGame scheduleGame) {
		this.scheduleGame = scheduleGame;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
		.append("id", getId())
		.append("type", getType())
		.append("url", getUrl())
		.append("embedCode", getEmbedCode())
		.append("language", getLanguage())
		.append("league", getLeague())
		.append("scheduleGame", getScheduleGame())
		.build();
	}
}
