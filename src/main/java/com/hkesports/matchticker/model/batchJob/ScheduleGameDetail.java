package com.hkesports.matchticker.model.batchJob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;

import com.hkesports.matchticker.enums.Dota2TeamType;
import com.hkesports.matchticker.enums.GameWinTypeEnum;
import com.hkesports.matchticker.model.basic.BasicModel;
import com.hkesports.matchticker.model.json.dota2.matchDetails.MatchDetailsResultJson;
import com.hkesports.matchticker.model.json.lol.legsUrl.TeamJson;

@Entity
@Table(name = "schedule_game_detail_r")
public class ScheduleGameDetail extends BasicModel {
	
	private static final long serialVersionUID = 1L;

	private Team team;
	private ScheduleGame scheduleGame;
	private GameWinTypeEnum win;
	private Boolean firstBlood;
	private Boolean firstTower;
	private Boolean firstInhibitor;
	private Boolean firstBaron;
	private Boolean firstDragon;
	private Integer towerKills;
	private Integer inhibitorKills;
	private Integer baronKills;
	private Integer dragonKills;
	private Integer vilemawKills;
	private Integer towerStatus;
	private Integer barracksStatus;
	private String position;
	private Integer dominionVictoryScore;
	private Long guessCount;
	
	@OneToOne
	@JoinColumn(name="team_id", columnDefinition="BIGINT(20)", referencedColumnName="id", nullable=true)
	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	@ManyToOne
	@JoinColumn(name="schedule_game_id", columnDefinition="BIGINT(20)", nullable = true)
	public ScheduleGame getScheduleGame() {
		return scheduleGame;
	}

	public void setScheduleGame(ScheduleGame scheduleGame) {
		this.scheduleGame = scheduleGame;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "win", length = 5, nullable=true)
	public GameWinTypeEnum getWin() {
		return win;
	}

	public void setWin(GameWinTypeEnum win) {
		this.win = win;
	}

	@Column(name="first_blood", columnDefinition="TINYINT", nullable=true)
	public Boolean getFirstBlood() {
		return firstBlood;
	}

	public void setFirstBlood(Boolean firstBlood) {
		this.firstBlood = firstBlood;
	}

	@Column(name="first_tower", columnDefinition="TINYINT", nullable=true)
	public Boolean getFirstTower() {
		return firstTower;
	}

	public void setFirstTower(Boolean firstTower) {
		this.firstTower = firstTower;
	}

	@Column(name="first_inhibitor", columnDefinition="TINYINT", nullable=true)
	public Boolean getFirstInhibitor() {
		return firstInhibitor;
	}

	public void setFirstInhibitor(Boolean firstInhibitor) {
		this.firstInhibitor = firstInhibitor;
	}

	@Column(name="first_baron", columnDefinition="TINYINT", nullable=true)
	public Boolean getFirstBaron() {
		return firstBaron;
	}

	public void setFirstBaron(Boolean firstBaron) {
		this.firstBaron = firstBaron;
	}

	@Column(name="first_dragon", columnDefinition="TINYINT", nullable=true)
	public Boolean getFirstDragon() {
		return firstDragon;
	}

	public void setFirstDragon(Boolean firstDragon) {
		this.firstDragon = firstDragon;
	}

	@Column(name="tower_kills", columnDefinition="INT(11)", nullable=true)
	public Integer getTowerKills() {
		return towerKills;
	}

	public void setTowerKills(Integer towerKills) {
		this.towerKills = towerKills;
	}

	@Column(name="inhibitor_kills", columnDefinition="INT(11)", nullable=true)
	public Integer getInhibitorKills() {
		return inhibitorKills;
	}

	public void setInhibitorKills(Integer inhibitorKills) {
		this.inhibitorKills = inhibitorKills;
	}

	@Column(name="baron_kills", columnDefinition="INT(11)", nullable=true)
	public Integer getBaronKills() {
		return baronKills;
	}

	public void setBaronKills(Integer baronKills) {
		this.baronKills = baronKills;
	}

	@Column(name="dragon_kills", columnDefinition="INT(11)", nullable=true)
	public Integer getDragonKills() {
		return dragonKills;
	}

	public void setDragonKills(Integer dragonKills) {
		this.dragonKills = dragonKills;
	}

	@Column(name="vilemaw_kills", columnDefinition="INT(11)", nullable=true)
	public Integer getVilemawKills() {
		return vilemawKills;
	}

	public void setVilemawKills(Integer vilemawKills) {
		this.vilemawKills = vilemawKills;
	}

	@Column(name="tower_status", columnDefinition="INT(11)", nullable=true)
	public Integer getTowerStatus() {
		return towerStatus;
	}

	public void setTowerStatus(Integer towerStatus) {
		this.towerStatus = towerStatus;
	}

	@Column(name="barracks_status", columnDefinition="INT(11)", nullable = true)
	public Integer getBarracksStatus() {
		return barracksStatus;
	}

	public void setBarracksStatus(Integer barracksStatus) {
		this.barracksStatus = barracksStatus;
	}

	@Column(name="guess_count", columnDefinition="BIGINT(20)", nullable = true)
	public Long getGuessCount() {
		return guessCount;
	}

	public void setGuessCount(Long guessCount) {
		this.guessCount = guessCount;
	}

	public boolean equals(MatchDetailsResultJson json, ScheduleGame scheduleGame, Team team, Dota2TeamType teamType){
		boolean isEquals = true;
		
		if(this.scheduleGame == null || !scheduleGame.getApiId().equals(this.scheduleGame.getApiId()))
			isEquals = false;
		else if(this.team == null || team.getApiId() == null || !team.getApiId().equals(this.team.getApiId()))
			isEquals = false;
		else if(teamType.equals(Dota2TeamType.RADIANT)){
			if(!(GameWinTypeEnum.Win.equals(this.win) == json.getRadiantWin()))
				isEquals = false;
			if((this.towerStatus != null && !this.towerStatus.equals(json.getTowerStatusRadiant())) 
					|| (this.barracksStatus != null && !this.barracksStatus.equals(json.getBarracksStatusRadiant())))
				isEquals = false;
		}else if(teamType.equals(Dota2TeamType.DIRE)){
			if(!(GameWinTypeEnum.Win.equals(this.win) != json.getRadiantWin()))
				isEquals = false;
			if((this.towerStatus != null && !this.towerStatus.equals(json.getTowerStatusDire())) 
					|| (this.barracksStatus != null && !this.barracksStatus.equals(json.getBarracksStatusDire())))
				isEquals = false;
		}
		
		return isEquals;
	}
	
	public boolean equals(TeamJson teamJson, Team team, ScheduleGame scheduleGame){
		EqualsBuilder eq = new EqualsBuilder();
		eq.append(getTeam().getApiId(), team.getApiId());
		eq.append(getScheduleGame().getApiId(), scheduleGame.getApiId());
		if(teamJson!=null) {
			eq.append(getWin(), GameWinTypeEnum.transform(teamJson.getWin()));
			eq.append(getFirstBlood(), teamJson.getFirstBlood());
			eq.append(getFirstTower(), teamJson.getFirstTower());
			eq.append(getFirstInhibitor(), teamJson.getFirstInhibitor());
			eq.append(getFirstBaron(), teamJson.getFirstBaron());
			eq.append(getFirstDragon(), teamJson.getFirstDragon());
			eq.append(getTowerKills(), teamJson.getTowerKills());
			eq.append(getInhibitorKills(), teamJson.getInhibitorKills());
			eq.append(getBaronKills(), teamJson.getBaronKills());
			eq.append(getDragonKills(), teamJson.getDragonKills());
			eq.append(getVilemawKills(), teamJson.getVilemawKills());
		}
		return eq.isEquals();
	}

	@Column(name="position", length=20, nullable=true)
	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	@Column(name="dominion_victory_score", columnDefinition="INT(11)", nullable=true)
	public Integer getDominionVictoryScore() {
		return dominionVictoryScore;
	}

	public void setDominionVictoryScore(Integer dominionVictoryScore) {
		this.dominionVictoryScore = dominionVictoryScore;
	}
}
