package com.hkesports.matchticker.model.batchJob;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.hkesports.matchticker.enums.GameTypeEnum;
import com.hkesports.matchticker.model.basic.BasicApiInfo;
import com.hkesports.matchticker.model.json.dota2.livegames.LiveGameJson;

/**
 * @author manboyu
 *
 */
@Entity
@Table(name = "tourament_r")
public class Tourament extends BasicApiInfo {

	private static final long serialVersionUID = 1L;

	private String touramentName;
	private String touramentShortName;
	private String touramentIconSmall;
	private String touramentIconLarge;
	private String touramentIconHuge;
	private Date touramentFromDate;
	private Date touramentToDate;
	private String touramentDescription;
	private String touramentChannelURL;
	private String touramentSiteUrl;
	private Short winScore = 3;
	private Short drawScore = 1;
	private Short loseScore = 0;
	private Short noVods;
	private Boolean published;
	private Short isFinished;
	private String season;
	private Long winner;
	private League league;
	private String tournamentCompetitionSystem;
	
	@Column(name = "tourament_Name", length = 255, nullable = false)
	public String getTouramentName() {
		return touramentName;
	}

	public void setTouramentName(String touramentName) {
		this.touramentName = touramentName;
	}

	@Column(name = "tourament_short_name", length = 100, nullable = false)
	public String getTouramentShortName() {
		return touramentShortName;
	}

	public void setTouramentShortName(String touramentShortName) {
		this.touramentShortName = touramentShortName;
	}

	@Column(name = "tourament_icon_small", length = 10, nullable = true)
	public String getTouramentIconSmall() {
		return touramentIconSmall;
	}

	public void setTouramentIconSmall(String touramentIconSmall) {
		this.touramentIconSmall = touramentIconSmall;
	}

	@Column(name = "tourament_icon_large", length = 10, nullable = true)
	public String getTouramentIconLarge() {
		return touramentIconLarge;
	}

	public void setTouramentIconLarge(String touramentIconLarge) {
		this.touramentIconLarge = touramentIconLarge;
	}
	
	@Column(name = "tourament_icon_Huge", length = 10, nullable = true)
	public String getTouramentIconHuge() {
		return touramentIconHuge;
	}

	public void setTouramentIconHuge(String touramentIconHuge) {
		this.touramentIconHuge = touramentIconHuge;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "tourament_from_date", nullable = true)
	public Date getTouramentFromDate() {
		return touramentFromDate;
	}

	public void setTouramentFromDate(Date touramentFromDate) {
		this.touramentFromDate = touramentFromDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "tourament_to_date", nullable = true)
	public Date getTouramentToDate() {
		return touramentToDate;
	}

	public void setTouramentToDate(Date touramentToDate) {
		this.touramentToDate = touramentToDate;
	}

	@Column(name = "tourament_description", columnDefinition = "TEXT", nullable = true)
	public String getTouramentDescription() {
		return touramentDescription;
	}

	public void setTouramentDescription(String touramentDescription) {
		this.touramentDescription = touramentDescription;
	}

	@Column(name = "tourament_channel_url", length = 255, nullable = true)
	public String getTouramentChannelURL() {
		return touramentChannelURL;
	}

	public void setTouramentChannelURL(String touramentChannelURL) {
		this.touramentChannelURL = touramentChannelURL;
	}

	@Column(name = "tourament_site_url", length = 255, nullable = true)
	public String getTouramentSiteUrl() {
		return touramentSiteUrl;
	}

	public void setTouramentSiteUrl(String touramentSiteUrl) {
		this.touramentSiteUrl = touramentSiteUrl;
	}

	@Column(name = "win_score", columnDefinition = "SMALLINT(6)", nullable = false)
	public Short getWinScore() {
		return winScore;
	}

	public void setWinScore(Short winScore) {
		this.winScore = winScore;
	}

	@Column(name = "draw_score", columnDefinition = "SMALLINT(6)", nullable = false)
	public Short getDrawScore() {
		return drawScore;
	}

	public void setDrawScore(Short drawScore) {
		this.drawScore = drawScore;
	}

	@Column(name = "lose_score", columnDefinition = "SMALLINT(6)", nullable = false)
	public Short getLoseScore() {
		return loseScore;
	}

	public void setLoseScore(Short loseScore) {
		this.loseScore = loseScore;
	}

	@Column(name = "no_vods", columnDefinition = "SMALLINT(6)", nullable = true)
	public Short getNoVods() {
		return noVods;
	}

	public void setNoVods(Short noVods) {
		this.noVods = noVods;
	}
	
	@Column(name = "published", columnDefinition = "SMALLINT(6)", nullable = true)
	public Boolean getPublished() {
		return published;
	}

	public void setPublished(Boolean published) {
		this.published = published;
	}
	
	@Column(name="is_finished", columnDefinition="SMALLINT(6)", nullable = true)
	public Short getIsFinished() {
		return isFinished;
	}
	
	public void setIsFinished(Short isFinished) {
		this.isFinished = isFinished;
	}
	
	@Column(name="season", length=50, nullable = true)
	public String getSeason() {
		return season;
	}
	
	public void setSeason(String season) {
		this.season = season;
	}
	
	@Column(name="winner", columnDefinition="BIGINT(20)", nullable = true)
	public Long getWinner() {
		return winner;
	}
	
	public void setWinner(Long winner) {
		this.winner = winner;
	}
	
	@Column(name = "tournament_competition_system", length = 10, nullable = true)
	public String getTournamentCompetitionSystem() {
		return tournamentCompetitionSystem;
	}

	public void setTournamentCompetitionSystem(String tournamentCompetitionSystem) {
		this.tournamentCompetitionSystem = tournamentCompetitionSystem;
	}

	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "league_id", nullable = true, columnDefinition="BIGINT(20)", referencedColumnName = "id")
	public League getLeague() {
		return league;
	}

	public void setLeague(League league) {
		this.league = league;
	}
	
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
		.append("id", getId())
		.append("touramentName", getTouramentName())
		.append("touramentShortName", getTouramentShortName())
		.append("touramentIconSmall", getTouramentIconSmall())
		.append("touramentIconLarge", getTouramentIconLarge())
		.append("touramentIconHuge", getTouramentIconHuge())
		.append("touramentFromDate", getTouramentFromDate())
		.append("touramentToDate", getTouramentToDate())
		.append("touramentDescription", getTouramentDescription())
		.append("touramentChannelURL", getTouramentChannelURL())
		.append("touramentSiteUrl", getTouramentSiteUrl())
		.append("winScore", getWinScore())
		.append("drawScore", getDrawScore())
		.append("loseScore", getLoseScore())
		.append("gameType", getGameType())
		.append("apiId", getApiId())
		.append("isFinished", getIsFinished())
		.append("season", getSeason())
		.append("winner", getWinner())
		.append("tournamentCompetitionSystem", getTournamentCompetitionSystem())
		.build();
	}
	
	public boolean equals(Object obj){
		if(obj instanceof com.hkesports.matchticker.model.json.dota2.leagueList.LeagueJson) {
			com.hkesports.matchticker.model.json.dota2.leagueList.LeagueJson json = (com.hkesports.matchticker.model.json.dota2.leagueList.LeagueJson) obj;
			EqualsBuilder eb = new EqualsBuilder();
			eb.append(getGameType(), GameTypeEnum.DOTA2);
			eb.append(getApiId(), json.getLeagueid());
			eb.append(this.touramentName, json.getName());
			eb.append(this.touramentSiteUrl, json.getTournamentUrl());
			eb.append(this.touramentDescription, json.getDescription());
			return eb.isEquals();
		} else if(obj instanceof com.hkesports.matchticker.model.json.lol.leagues.LeagueJson) {
			com.hkesports.matchticker.model.json.lol.tournaments.TournamentJson tournamentJson = (com.hkesports.matchticker.model.json.lol.tournaments.TournamentJson) obj;
			return new EqualsBuilder()
				.append(getGameType(), GameTypeEnum.LOL)
				.append(getApiId(), tournamentJson.getId())
			 	.append(getTouramentName(), tournamentJson.getNamePublic())
			 	.append(getTouramentShortName(), tournamentJson.getName())
			 	.append(getTouramentFromDate(), tournamentJson.getDateBegin())
			 	.append(getTouramentToDate(), tournamentJson.getDateEnd())
				.append(getNoVods(), tournamentJson.getNoVods())
				.append(getSeason(), tournamentJson.getSeason())
				.append(getWinner(), StringUtils.isNotBlank(tournamentJson.getWinner()) ? Long.parseLong(tournamentJson.getWinner()) : null)
				.append(getIsFinished().shortValue(), tournamentJson.isFinished() ? (short)1 : 0)
				.append(getPublished(), Boolean.valueOf(tournamentJson.isPublished()))
			 	.isEquals();
		} else if(obj instanceof LiveGameJson) {
			return this.getApiId().equals(((LiveGameJson) obj).getLeague_id());
		}
		return super.equals(obj);
	}
}

