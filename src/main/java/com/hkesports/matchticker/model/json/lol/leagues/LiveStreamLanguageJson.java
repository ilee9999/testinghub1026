package com.hkesports.matchticker.model.json.lol.leagues;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author manboyu
 *
 */
public class LiveStreamLanguageJson implements Serializable {

	private static final long serialVersionUID = 1L;

	private LeagueJson leagueJson;
	private String language;
	private String display_language;
	private List<LiveStreamsJson> streams = new ArrayList<>();

	public LeagueJson getLeagueJson() {
		return leagueJson;
	}

	public void setLeagueJson(LeagueJson leagueJson) {
		this.leagueJson = leagueJson;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getDisplay_language() {
		return display_language;
	}

	public void setDisplay_language(String display_language) {
		this.display_language = display_language;
	}

	public List<LiveStreamsJson> getStreams() {
		return streams;
	}

	public void setStreams(List<LiveStreamsJson> streams) {
		this.streams = streams;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
		.append("language", getLanguage())
		.append("display_language", getDisplay_language())
		.append("streams", getStreams())
		.build();
	}
}
