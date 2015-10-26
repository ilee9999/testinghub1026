package com.hkesports.matchticker.model.batchJob;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.hkesports.matchticker.enums.GameTypeEnum;
import com.hkesports.matchticker.enums.ScheduleStatus;
import com.hkesports.matchticker.model.Game;
import com.hkesports.matchticker.model.basic.BasicApiInfo;
import com.hkesports.matchticker.model.json.dota2.matchDetails.MatchDetailsResultJson;
import com.hkesports.matchticker.model.json.lol.schedule.ScheduleJson;
import com.hkesports.matchticker.vo.Dota2MatchGroupVo;

@Entity
@Table(name = "schedule_r")
public class Schedule extends BasicApiInfo {
	
	private static final long serialVersionUID = 1L;

	private Boolean highlight = false;
	private String teamAName;
	private Long teamAId;
	private String playerAName;
	private Long aSideSupportCount;
	private String teamBName;
	private Long teamBId;
	private String playerBName;
	private Long bSideSupportCount;
	private Date startTime;
	private Date endTime;
	private Tourament tourament;
	private String touramentName;
	private Game game;
	private String matchLiveUrl;
	private String results;
	private String matchArchiveUrl;
	private Boolean display;

	private Date dateTime;
	private Long winnerId;
	private Short maxGames;
	private Boolean isLive;
	private String isFinished;
	private String polldaddyId;
	private ScheduleStatus status;
	private List<ScheduleGame> scheduleGames = new ArrayList<>();
	
	@Column(name = "highlight", columnDefinition = "TINYINT", nullable = false)
	public Boolean getHighlight() {
		return highlight;
	}
	
	public void setHighlight(Boolean highlight) {
		this.highlight = highlight;
	}
	
	@Column(name="team_a_name", length=50, nullable = true)
	public String getTeamAName() {
		return teamAName;
	}
	
	public void setTeamAName(String teamAName) {
		this.teamAName = teamAName;
	}
	
	@Column(name="team_a_id")
	public Long getTeamAId() {
		return teamAId;
	}

	public void setTeamAId(Long teamAId) {
		this.teamAId = teamAId;
	}

	@Column(name="player_a_name", length=64, nullable = true)
	public String getPlayerAName() {
		return playerAName;
	}
	
	public void setPlayerAName(String playerAName) {
		this.playerAName = playerAName;
	}
	
	@Column(name="a_side_support_count", columnDefinition="BIGINT(20)", nullable = true)
	public Long getaSideSupportCount() {
		return aSideSupportCount;
	}
	
	public void setaSideSupportCount(Long aSideSupportCount) {
		this.aSideSupportCount = aSideSupportCount;
	}
	
	@Column(name="team_b_name", length=50, nullable = true)
	public String getTeamBName() {
		return teamBName;
	}
	
	public void setTeamBName(String teamBName) {
		this.teamBName = teamBName;
	}
	
	@Column(name="team_b_id")
	public Long getTeamBId() {
		return teamBId;
	}

	public void setTeamBId(Long teamBId) {
		this.teamBId = teamBId;
	}
	
	@Column(name="player_b_name", length=64, nullable = true)
	public String getPlayerBName() {
		return playerBName;
	}
	
	public void setPlayerBName(String playerBName) {
		this.playerBName = playerBName;
	}
	
	@Column(name="b_side_support_count", columnDefinition="BIGINT(20)", nullable = true)
	public Long getbSideSupportCount() {
		return bSideSupportCount;
	}
	
	public void setbSideSupportCount(Long bSideSupportCount) {
		this.bSideSupportCount = bSideSupportCount;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="start_time", nullable=false)
	public Date getStartTime() {
		return startTime;
	}
	
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "end_time", nullable = true)
	public Date getEndTime() {
		return endTime;
	}
	
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	@Column(name = "tourament_name", length = 255, nullable = true)
	public String getTouramentName() {
		return touramentName;
	}
	
	public void setTouramentName(String touramentName) {
		this.touramentName = touramentName;
	}
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "game_id", nullable = true, columnDefinition="BIGINT(20)", referencedColumnName = "id")
	public Game getGame() {
		return game;
	}
	
	public void setGame(Game game) {
		this.game = game;
	}
	
	@Column(name="match_live_url", length=2048, nullable = true)
	public String getMatchLiveUrl() {
		return matchLiveUrl;
	}
	
	public void setMatchLiveUrl(String matchLiveUrl) {
		this.matchLiveUrl = matchLiveUrl;
	}
	
	@Column(name="results", length=32, nullable = true)
	public String getResults() {
		return results;
	}
	
	public void setResults(String results) {
		this.results = results;
	}
	
	@Column(name="match_archive_url", length=2048, nullable = true)
	public String getMatchArchiveUrl() {
		return matchArchiveUrl;
	}
	
	public void setMatchArchiveUrl(String matchArchiveUrl) {
		this.matchArchiveUrl = matchArchiveUrl;
	}
	
	@Column(name="display", columnDefinition="TINYINT", nullable = true)
	public Boolean getDisplay() {
		return display;
	}
	
	public void setDisplay(Boolean display) {
		this.display = display;
	}
	
	@OneToMany(mappedBy = "schedule", fetch = FetchType.LAZY)
	public List<ScheduleGame> getScheduleGames() {
		return scheduleGames;
	}

	public void setScheduleGames(List<ScheduleGame> scheduleGames) {
		this.scheduleGames = scheduleGames;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "tourament_id", nullable = true, columnDefinition="BIGINT(20)", referencedColumnName = "id")
	public Tourament getTourament() {
		return tourament;
	}

	public void setTourament(Tourament tourament) {
		this.tourament = tourament;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name =" date_time", nullable = true)
	public Date getDateTime() {
		return dateTime;
	}
	
	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}
	
	@Column(name = "winner_id", columnDefinition = "BIGINT(20)", nullable = true)
	public Long getWinnerId() {
		return winnerId;
	}
	
	public void setWinnerId(Long winnerId) {
		this.winnerId = winnerId;
	}
	
	@Column(name="max_games", columnDefinition="SMALLINT(6)", nullable=true)
	public Short getMaxGames() {
		return maxGames;
	}
	
	public void setMaxGames(Short maxGames) {
		this.maxGames = maxGames;
	}
	
	@Column(name = "is_live", columnDefinition = "TINYINT(4)", nullable = true)
	public Boolean getIsLive() {
		return isLive;
	}
	
	public void setIsLive(Boolean isLive) {
		this.isLive = isLive;
	}
	
	@Column(name = "is_finished", length = 10, nullable = true)
	public String getIsFinished() {
		return isFinished;
	}
	
	public void setIsFinished(String isFinished) {
		this.isFinished = isFinished;
	}
	
	@Column(name = "polldaddy_id", length = 255, nullable = true)
	public String getPolldaddyId() {
		return polldaddyId;
	}
	
	public void setPolldaddyId(String polldaddyId) {
		this.polldaddyId = polldaddyId;
	}
	
	@Enumerated
	@Column(name = "status", columnDefinition = "TINYINT(4)", nullable = true)
	public ScheduleStatus getStatus() {
		return status;
	}

	public void setStatus(ScheduleStatus status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
		.append("id", getId())
		.append("apiId", getApiId())
		.append("gameType", getGameType())
		.append("highlight", getHighlight())
		.append("teamAName", getTeamAName())
		.append("playerAName", getPlayerAName())
		.append("aSideSupportCount", getaSideSupportCount())
		.append("teamBName", getTeamBName())
		.append("playerBName", getPlayerBName())
		.append("bSideSupportCount", getbSideSupportCount())
		.append("startTime", getStartTime())
		.append("endTime", getEndTime())
		.append("touramentName", getTouramentName())
		.append("game", getGame())
		.append("matchLiveUrl", getMatchLiveUrl())
		.append("results", getResults())
		.append("matchArchiveUrl", getMatchArchiveUrl())
		.append("display", getDisplay())
		.append("dateTime", getDateTime())
		.append("winnerId", getWinnerId())
		.append("maxGames", getMaxGames())
		.append("isLive", getIsLive())
		.append("isFinished", getIsFinished())
		.append("polldaddyId", getPolldaddyId())
		.build();
	}
	
	public boolean equals(Team aTeam, Team bTeam) {
		EqualsBuilder eb = new EqualsBuilder();
		if(aTeam!=null) {
			eb.append(getTeamAId(), aTeam.getId());
			eb.append(getTeamAName(), aTeam.getTeamFullName());
		} else {
			if(getTeamAId()!=null || getTeamAName()!=null) {
				return false;
			}
		}
		if(bTeam!=null) {
			eb.append(getTeamBId(), bTeam.getId());
			eb.append(getTeamBName(), bTeam.getTeamFullName());
		} else {
			if(getTeamBId()!=null || getTeamBName()!=null) {
				return false;
			}
		}
		return eb.isEquals();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Dota2MatchGroupVo){
			Dota2MatchGroupVo vo = (Dota2MatchGroupVo) obj;
			MatchDetailsResultJson json = vo.getFirstGame();
			EqualsBuilder eb = new EqualsBuilder();
			eb.append(getGameType(), GameTypeEnum.DOTA2);
			eb.append(getApiId(), json.getMatchId());
//			long thisTime = 0L;
//			long jsonTime = 0L;
//			if(getStartTime() != null)
//				thisTime = getStartTime().getTime();
//			if(json.getStartTime() != null && !json.getStartTime().equals("null") && json.getStartTime() != 0L)
//				jsonTime = json.getStartTime() * 1000;
			eb.append(getStartTime(), vo.getStartDate());
			eb.append(getEndTime(), vo.getEndDate());
			eb.append(getDateTime(), vo.getStartDate());
			eb.append(getWinnerId(), vo.getWinId());
			
			eb.append(Integer.valueOf(getMaxGames()), Integer.valueOf(vo.getDetails().size()));
			
			return eb.isEquals();
		} else if(obj instanceof ScheduleJson) {
			ScheduleJson scheduleJson = (ScheduleJson)obj;
			EqualsBuilder eb = new EqualsBuilder()
			.append(getApiId(), scheduleJson.getMatchId())
			.append(getGameType(), GameTypeEnum.LOL)
			.append(getDateTime() != null ? getDateTime().getTime() : null, scheduleJson.getDateTime() != null ? scheduleJson.getDateTime().getTime() : null)
			.append(getWinnerId(), StringUtils.isNotBlank(scheduleJson.getWinnerId()) ? Long.parseLong(scheduleJson.getWinnerId()) : null)
			.append(getMaxGames(), StringUtils.isNotBlank(scheduleJson.getMaxGames()) ? Short.parseShort(scheduleJson.getMaxGames()) : null)
			.append(getIsLive().booleanValue(), scheduleJson.isLive())
			.append(getIsFinished(), scheduleJson.getIsFinished())
			.append(getPolldaddyId(), scheduleJson.getPolldaddyId());
			return eb.isEquals();
		}
			
		return super.equals(obj);
	}
}
