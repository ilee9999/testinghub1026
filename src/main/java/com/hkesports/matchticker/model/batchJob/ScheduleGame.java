package com.hkesports.matchticker.model.batchJob;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.hkesports.matchticker.enums.GameTypeEnum;
import com.hkesports.matchticker.model.basic.BasicApiInfo;
import com.hkesports.matchticker.model.json.dota2.matchDetails.MatchDetailsResultJson;
import com.hkesports.matchticker.model.json.lol.game.GameDetailJson;

/**
 * @author manboyu
 *
 */
@Entity
@Table(name = "schedule_game_r")
public class ScheduleGame extends BasicApiInfo {

	private static final long serialVersionUID = 1L;

	private Date dateTime;
	private Long winnerId;
	private Short maxGames;
	private Short gameNumber;
	private Integer gameLength;
	private String platformId;
	private Long platformGameId;
	private Short noVods;
	private String legsUrl;
	private Short lobbyType;
	private Integer humanPlayers;
	private Integer positiveVotes;
	private Integer negativeVote;
	private Short gameMode;
	private Integer firstBloodTime;
	private Schedule schedule;
	private List<LiveStreams> liveStreams = new ArrayList<>();
	private List<ScheduleGameDetail> scheduleGameDetails = new ArrayList<>();
	private List<ScheduleGamePlayerDetail> scheduleGamePlayerDetails = new ArrayList<>();
	private List<ScheduleGamePlayerItems> scheduleGamePlayerItems = new ArrayList<>();
	private Integer tournamentRound;
	private Long gameCreation;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_time", nullable = true)
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

	@Column(name = "max_games", columnDefinition = "SMALLINT(6)", nullable = true)
	public Short getMaxGames() {
		return maxGames;
	}

	public void setMaxGames(Short maxGames) {
		this.maxGames = maxGames;
	}

	@Column(name = "game_number", columnDefinition = "SMALLINT(6)", nullable = true)
	public Short getGameNumber() {
		return gameNumber;
	}

	public void setGameNumber(Short gameNumber) {
		this.gameNumber = gameNumber;
	}

	@Column(name = "game_length", nullable = true)
	public Integer getGameLength() {
		return gameLength;
	}

	public void setGameLength(Integer gameLength) {
		this.gameLength = gameLength;
	}

	@Column(name = "platform_id", length = 50, nullable = true)
	public String getPlatformId() {
		return platformId;
	}

	public void setPlatformId(String platformId) {
		this.platformId = platformId;
	}

	@Column(name = "platform_game_id", columnDefinition = "BIGINT(20)", nullable = true)
	public Long getPlatformGameId() {
		return platformGameId;
	}

	public void setPlatformGameId(Long platformGameId) {
		this.platformGameId = platformGameId;
	}

	@Column(name = "no_vods", columnDefinition = "SMALLINT(6)", nullable = true)
	public Short getNoVods() {
		return noVods;
	}

	public void setNoVods(Short noVods) {
		this.noVods = noVods;
	}

	@Column(name = "leg_url", length = 255, nullable = true)
	public String getLegsUrl() {
		return legsUrl;
	}

	public void setLegsUrl(String legsUrl) {
		this.legsUrl = legsUrl;
	}

	@Column(name = "lobby_type", columnDefinition = "SMALLINT(6)", nullable = true)
	public Short getLobbyType() {
		return lobbyType;
	}

	public void setLobbyType(Short lobbyType) {
		this.lobbyType = lobbyType;
	}

	@Column(name = "human_players", nullable = true)
	public Integer getHumanPlayers() {
		return humanPlayers;
	}

	public void setHumanPlayers(Integer humanPlayers) {
		this.humanPlayers = humanPlayers;
	}

	@Column(name = "positive_votes", nullable = true)
	public Integer getPositiveVotes() {
		return positiveVotes;
	}

	public void setPositiveVotes(Integer positiveVotes) {
		this.positiveVotes = positiveVotes;
	}

	@Column(name = "negative_vote", nullable = true)
	public Integer getNegativeVote() {
		return negativeVote;
	}

	public void setNegativeVote(Integer negativeVote) {
		this.negativeVote = negativeVote;
	}

	@Column(name = "game_mode", columnDefinition = "SMALLINT(6)", nullable = true)
	public Short getGameMode() {
		return gameMode;
	}

	public void setGameMode(Short gameMode) {
		this.gameMode = gameMode;
	}

	@Column(name = "first_blood_time", nullable = true)
	public Integer getFirstBloodTime() {
		return firstBloodTime;
	}

	public void setFirstBloodTime(Integer firstBloodTime) {
		this.firstBloodTime = firstBloodTime;
	}
	
	@OneToMany(mappedBy = "scheduleGame", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	public List<LiveStreams> getLiveStreams() {
		return liveStreams;
	}

	public void setLiveStreams(List<LiveStreams> liveStreams) {
		this.liveStreams = liveStreams;
	}

	@OneToMany(mappedBy = "scheduleGame", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	public List<ScheduleGameDetail> getScheduleGameDetails() {
		return scheduleGameDetails;
	}

	public void setScheduleGameDetails(List<ScheduleGameDetail> scheduleGameDetails) {
		this.scheduleGameDetails = scheduleGameDetails;
	}

	@OneToMany(mappedBy = "scheduleGame", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	public List<ScheduleGamePlayerDetail> getScheduleGamePlayerDetails() {
		return scheduleGamePlayerDetails;
	}

	public void setScheduleGamePlayerDetails(List<ScheduleGamePlayerDetail> scheduleGamePlayerDetails) {
		this.scheduleGamePlayerDetails = scheduleGamePlayerDetails;
	}

	@OneToMany(mappedBy = "scheduleGame", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	public List<ScheduleGamePlayerItems> getScheduleGamePlayerItems() {
		return scheduleGamePlayerItems;
	}

	public void setScheduleGamePlayerItems(List<ScheduleGamePlayerItems> scheduleGamePlayerItems) {
		this.scheduleGamePlayerItems = scheduleGamePlayerItems;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "schedule_id", columnDefinition = "BIGINT(20)", nullable = true)
	public Schedule getSchedule() {
		return schedule;
	}

	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
		.append("id", getId())
		.append("apiId", getApiId())
		.append("gameType", getGameType())
		.append("dateTime", getDateTime())
		.append("winnerId", getWinnerId())
		.append("maxGames", getMaxGames())
		.append("gameNumber", getGameNumber())
		.append("gameLength", getGameLength())
		.append("platformId", getPlatformId())
		.append("platformGameId", getPlatformGameId())
		.append("noVods", getNoVods())
		.append("legsUrl", getLegsUrl())
		.append("lobbyType", getLobbyType())
		.append("humanPlayers", getHumanPlayers())
		.append("positiveVotes", getPositiveVotes())
		.append("negativeVote", getNegativeVote())
		.append("gameMode", getGameMode())
		.append("firstBloodTime", getFirstBloodTime())
		.build();
	}
	
	public boolean equals(Object obj){
		if(obj instanceof MatchDetailsResultJson){
			MatchDetailsResultJson json = (MatchDetailsResultJson) obj;
			EqualsBuilder eb = new EqualsBuilder();
			eb.append(getGameType(), GameTypeEnum.DOTA2);
			eb.append(getApiId(), json.getMatchId());
			long thisTime = 0L;
			long jsonTime = 0L;
			if(getDateTime() != null)
				thisTime = getDateTime().getTime();
			if(json.getStartTime() != null && !json.getStartTime().equals("null") && json.getStartTime() != 0L)
				jsonTime = json.getStartTime() * 1000;
			eb.append(thisTime, jsonTime);
			eb.append(getGameLength(), json.getDuration());
			eb.append(getLobbyType(), json.getLobbyType());
			eb.append(getHumanPlayers(), json.getHumanPlayers());
			eb.append(getPositiveVotes(), json.getPositiveVotes());
			eb.append(getNegativeVote(), json.getNegativeVotes());
			eb.append(getGameMode(), json.getGameMode());
			eb.append(getFirstBloodTime(), json.getFirstBloodTime());
			return eb.isEquals();
		} else if(obj instanceof GameDetailJson) {
			GameDetailJson gameDetailJson = (GameDetailJson)obj;
			return new EqualsBuilder()
			.append(getApiId(), gameDetailJson.getId())
			.append(getGameType(), GameTypeEnum.LOL)
			.append(getDateTime() != null ? getDateTime().getTime() : null, gameDetailJson.getDateTime() != null ? gameDetailJson.getDateTime().getTime() : null)
			.append(getWinnerId(), StringUtils.isNotBlank(gameDetailJson.getWinnerId()) ? Long.parseLong(gameDetailJson.getWinnerId()) : null)
			.append(getMaxGames(), StringUtils.isNotBlank(gameDetailJson.getMaxGames()) ? Short.parseShort(gameDetailJson.getMaxGames()) : null)
			.append(getGameNumber(), StringUtils.isNotBlank(gameDetailJson.getGameNumber()) ? Short.parseShort(gameDetailJson.getGameNumber()) : null)
			.append(getGameLength(), gameDetailJson.getGameLength())
			.append(getPlatformId(), gameDetailJson.getPlatformId())
			.append(getPlatformGameId(), gameDetailJson.getPlatformGameId() != null ? gameDetailJson.getPlatformGameId().longValue() : null)
			.append(getNoVods(), gameDetailJson.getNoVods() != null ? gameDetailJson.getNoVods().shortValue() : null)
			.append(getLegsUrl(), gameDetailJson.getLegsUrl())
			.append(getTournamentRound(), gameDetailJson.getTournament() !=null ? gameDetailJson.getTournament().getRound() : null)
			.append(getGameCreation(), gameDetailJson.getGameCreation())
			.isEquals();
		}  
		return super.equals(obj);
	}
	
	@Column(name = "tournament_round", columnDefinition = "INT(11)", nullable = true)
	public Integer getTournamentRound() {
		return tournamentRound;
	}

	public void setTournamentRound(Integer tournamentRound) {
		this.tournamentRound = tournamentRound;
	}

	@Column(name = "game_creation", columnDefinition = "BIGINT(20)", nullable = true)
	public Long getGameCreation() {
		return gameCreation;
	}

	public void setGameCreation(Long gameCreation) {
		this.gameCreation = gameCreation;
	}
}
