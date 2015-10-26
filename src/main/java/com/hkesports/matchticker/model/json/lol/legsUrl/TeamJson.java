package com.hkesports.matchticker.model.json.lol.legsUrl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class TeamJson implements Serializable {

	private static final long serialVersionUID = 1L;

	private String teamApiId;
	private Integer teamId;
	private String win;
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
	private Integer dominionVictoryScore;
	private List<BanJson> bans = new ArrayList<>();

	public String getTeamApiId() {
		return teamApiId;
	}

	public void setTeamApiId(String teamApiId) {
		this.teamApiId = teamApiId;
	}

	public Integer getTeamId() {
		return teamId;
	}

	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}

	public String getWin() {
		return win;
	}

	public void setWin(String win) {
		this.win = win;
	}

	public Boolean getFirstBlood() {
		return firstBlood;
	}

	public void setFirstBlood(Boolean firstBlood) {
		this.firstBlood = firstBlood;
	}

	public Boolean getFirstTower() {
		return firstTower;
	}

	public void setFirstTower(Boolean firstTower) {
		this.firstTower = firstTower;
	}

	public Boolean getFirstInhibitor() {
		return firstInhibitor;
	}

	public void setFirstInhibitor(Boolean firstInhibitor) {
		this.firstInhibitor = firstInhibitor;
	}

	public Boolean getFirstBaron() {
		return firstBaron;
	}

	public void setFirstBaron(Boolean firstBaron) {
		this.firstBaron = firstBaron;
	}

	public Boolean getFirstDragon() {
		return firstDragon;
	}

	public void setFirstDragon(Boolean firstDragon) {
		this.firstDragon = firstDragon;
	}

	public Integer getTowerKills() {
		return towerKills;
	}

	public void setTowerKills(Integer towerKills) {
		this.towerKills = towerKills;
	}

	public Integer getInhibitorKills() {
		return inhibitorKills;
	}

	public void setInhibitorKills(Integer inhibitorKills) {
		this.inhibitorKills = inhibitorKills;
	}

	public Integer getBaronKills() {
		return baronKills;
	}

	public void setBaronKills(Integer baronKills) {
		this.baronKills = baronKills;
	}

	public Integer getDragonKills() {
		return dragonKills;
	}

	public void setDragonKills(Integer dragonKills) {
		this.dragonKills = dragonKills;
	}

	public Integer getVilemawKills() {
		return vilemawKills;
	}

	public void setVilemawKills(Integer vilemawKills) {
		this.vilemawKills = vilemawKills;
	}

	public Integer getDominionVictoryScore() {
		return dominionVictoryScore;
	}

	public void setDominionVictoryScore(Integer dominionVictoryScore) {
		this.dominionVictoryScore = dominionVictoryScore;
	}

	public List<BanJson> getBans() {
		return bans;
	}

	public void setBans(List<BanJson> bans) {
		this.bans = bans;
	}
	
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
		.append("teamApiId", getTeamApiId())
		.append("teamId", getTeamId())
		.append("win", getWin())
		.append("firstBlood", getFirstBlood())
		.append("firstTower", getFirstTower())
		.append("firstInhibitor", getFirstInhibitor())
		.append("firstBaron", getFirstBaron())
		.append("firstDragon", getFirstDragon())
		.append("towerKills", getTowerKills())
		.append("inhibitorKills", getInhibitorKills())
		.append("baronKills", getBaronKills())
		.append("dragonKills", getDragonKills())
		.append("vilemawKills", getVilemawKills())
		.append("dominionVictoryScore", getDominionVictoryScore())
		.append("bans", getBans())
		.build();
	}
}
