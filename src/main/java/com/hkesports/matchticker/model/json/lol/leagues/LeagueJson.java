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
public class LeagueJson implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String color;
	private String leagueImage;
	private Long defaultTournamentId;
	private String defaultSeriesId;
	private List<LiveStreamLanguageJson> internationalLiveStream = new ArrayList<>();
	private String shortName;
	private List<Long> leagueTournaments = new ArrayList<>();
	private List<Integer> leagueSeries = new ArrayList<>();
	private Short noVods;
	private Integer menuWeight;
	private Boolean published;
	private String url;
	private String label;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getLeagueImage() {
		return leagueImage;
	}

	public void setLeagueImage(String leagueImage) {
		this.leagueImage = leagueImage;
	}

	public Long getDefaultTournamentId() {
		return defaultTournamentId;
	}

	public void setDefaultTournamentId(Long defaultTournamentId) {
		this.defaultTournamentId = defaultTournamentId;
	}

	public String getDefaultSeriesId() {
		return defaultSeriesId;
	}

	public void setDefaultSeriesId(String defaultSeriesId) {
		this.defaultSeriesId = defaultSeriesId;
	}

	public List<LiveStreamLanguageJson> getInternationalLiveStream() {
		return internationalLiveStream;
	}

	public void setInternationalLiveStream(
			List<LiveStreamLanguageJson> internationalLiveStream) {
		this.internationalLiveStream = internationalLiveStream;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public List<Long> getLeagueTournaments() {
		return leagueTournaments;
	}

	public void setLeagueTournaments(List<Long> leagueTournaments) {
		this.leagueTournaments = leagueTournaments;
	}

	public List<Integer> getLeagueSeries() {
		return leagueSeries;
	}

	public void setLeagueSeries(List<Integer> leagueSeries) {
		this.leagueSeries = leagueSeries;
	}

	public Short getNoVods() {
		return noVods;
	}

	public void setNoVods(Short noVods) {
		this.noVods = noVods;
	}

	public Integer getMenuWeight() {
		return menuWeight;
	}

	public void setMenuWeight(Integer menuWeight) {
		this.menuWeight = menuWeight;
	}

	public Boolean getPublished() {
		return published;
	}

	public void setPublished(Boolean published) {
		this.published = published;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
		.append("id", getId())
		.append("color", getColor())
		.append("leagueImage", getLeagueImage())
		.append("defaultTournamentId", getDefaultTournamentId())
		.append("defaultSeriesId", getDefaultSeriesId())
		.append("internationalLiveStream", getInternationalLiveStream())
		.append("shortName", getShortName())
		.append("leagueTournaments", getLeagueTournaments())
		.append("leagueSeries", getLeagueSeries())
		.append("noVods", getNoVods())
		.append("menuWeight", getMenuWeight())
		.append("published", getPublished())
		.append("url", getUrl())
		.append("label", getLabel())
		.build();
	}
}
