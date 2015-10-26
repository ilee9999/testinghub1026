package com.hkesports.matchticker.model.batchJob;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;

import com.hkesports.matchticker.enums.GameTypeEnum;
import com.hkesports.matchticker.model.basic.BasicApiInfo;
import com.hkesports.matchticker.model.json.lol.leagues.LeagueJson;

@Entity
@Table(name = "league_r")
public class League extends BasicApiInfo {

	private static final long serialVersionUID = 1L;

	private String color;
	private String leagueImage;
	private Long defaultTournamentId;
	private String defaultSeriesId;
	private String shortName;
	private Short noVods;
	private Integer menuWeight;
	private Boolean published;
	private String url;
	private String label;

	private List<LiveStreams> liveStreams = new ArrayList<>();

	@Column(name = "color", length = 7, nullable = true)
	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	@Column(name = "league_image", length = 255, nullable = true)
	public String getLeagueImage() {
		return leagueImage;
	}

	public void setLeagueImage(String leagueImage) {
		this.leagueImage = leagueImage;
	}

	@Column(name = "default_tournament_id", columnDefinition = "BIGINT(20)", nullable = true)
	public Long getDefaultTournamentId() {
		return defaultTournamentId;
	}

	public void setDefaultTournamentId(Long defaultTournamentId) {
		this.defaultTournamentId = defaultTournamentId;
	}

	@Column(name = "no_vods", columnDefinition = "SMALLINT(6)", nullable = true)
	public Short getNoVods() {
		return noVods;
	}

	public void setNoVods(Short noVods) {
		this.noVods = noVods;
	}

	@Column(name = "menu_weight", columnDefinition = "INT(11)", nullable = true)
	public Integer getMenuWeight() {
		return menuWeight;
	}

	public void setMenuWeight(Integer menuWeight) {
		this.menuWeight = menuWeight;
	}

	@Column(name = "published", columnDefinition = "SMALLINT(6)", nullable = true)
	public Boolean getPublished() {
		return published;
	}

	public void setPublished(Boolean published) {
		this.published = published;
	}

	@OneToMany(mappedBy = "league", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	public List<LiveStreams> getLiveStreams() {
		return liveStreams;
	}

	public void setLiveStreams(List<LiveStreams> liveStreams) {
		this.liveStreams = liveStreams;
	}

	@Column(name = "default_series_id", length = 50, nullable = true)
	public String getDefaultSeriesId() {
		return defaultSeriesId;
	}

	public void setDefaultSeriesId(String defaultSeriesId) {
		this.defaultSeriesId = defaultSeriesId;
	}

	@Column(name = "short_name", length = 50, nullable = true)
	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	@Column(name = "url", length = 255, nullable = true)
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "label", length = 50, nullable = true)
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof LeagueJson) {
			LeagueJson leagueJson = (LeagueJson) obj;
			return new EqualsBuilder()
					.append(getGameType(), GameTypeEnum.LOL)
					.append(getApiId(), leagueJson.getId())
				 	.append(getLabel(), leagueJson.getLabel())
				 	.append(getShortName(), leagueJson.getShortName())
				 	.append(getUrl(), leagueJson.getUrl())
				 	.append(getColor(), leagueJson.getColor())
				 	.append(getLeagueImage(), leagueJson.getLeagueImage())
				 	.append(getDefaultTournamentId(), leagueJson.getDefaultTournamentId())
				 	.append(getDefaultSeriesId(), leagueJson.getDefaultSeriesId())
				 	.append(getNoVods(), leagueJson.getNoVods())
				 	.append(getMenuWeight(), leagueJson.getMenuWeight())
				 	.append(getPublished(), leagueJson.getPublished())
				 	.isEquals();
		}
		return super.equals(obj);
	}

	
}
