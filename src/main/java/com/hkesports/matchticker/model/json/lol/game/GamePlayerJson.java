package com.hkesports.matchticker.model.json.lol.game;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author manboyu
 *
 */
public class GamePlayerJson implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String teamId;
	private String name;
	private String photoURL;
	private Double kda;
	private Integer kills;
	private Integer deaths;
	private Integer assists;
	private Integer endLevel;
	private Integer minionsKilled;
	private Integer totalGold;
	private String spell0;
	private String spell1;
	private String items0;
	private String items1;
	private String items2;
	private String items3;
	private String items4;
	private String items5;
	private String championId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTeamId() {
		return teamId;
	}

	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhotoURL() {
		return photoURL;
	}

	public void setPhotoURL(String photoURL) {
		this.photoURL = photoURL;
	}

	public Double getKda() {
		return kda;
	}

	public void setKda(Double kda) {
		this.kda = kda;
	}

	public Integer getKills() {
		return kills;
	}

	public void setKills(Integer kills) {
		this.kills = kills;
	}

	public Integer getDeaths() {
		return deaths;
	}

	public void setDeaths(Integer deaths) {
		this.deaths = deaths;
	}

	public Integer getAssists() {
		return assists;
	}

	public void setAssists(Integer assists) {
		this.assists = assists;
	}

	public Integer getEndLevel() {
		return endLevel;
	}

	public void setEndLevel(Integer endLevel) {
		this.endLevel = endLevel;
	}

	public Integer getMinionsKilled() {
		return minionsKilled;
	}

	public void setMinionsKilled(Integer minionsKilled) {
		this.minionsKilled = minionsKilled;
	}

	public Integer getTotalGold() {
		return totalGold;
	}

	public void setTotalGold(Integer totalGold) {
		this.totalGold = totalGold;
	}

	public String getSpell0() {
		return spell0;
	}

	public void setSpell0(String spell0) {
		this.spell0 = spell0;
	}

	public String getSpell1() {
		return spell1;
	}

	public void setSpell1(String spell1) {
		this.spell1 = spell1;
	}

	public String getItems0() {
		return items0;
	}

	public void setItems0(String items0) {
		this.items0 = items0;
	}

	public String getItems1() {
		return items1;
	}

	public void setItems1(String items1) {
		this.items1 = items1;
	}

	public String getItems2() {
		return items2;
	}

	public void setItems2(String items2) {
		this.items2 = items2;
	}

	public String getItems3() {
		return items3;
	}

	public void setItems3(String items3) {
		this.items3 = items3;
	}

	public String getItems4() {
		return items4;
	}

	public void setItems4(String items4) {
		this.items4 = items4;
	}

	public String getItems5() {
		return items5;
	}

	public void setItems5(String items5) {
		this.items5 = items5;
	}

	public String getChampionId() {
		return championId;
	}

	public void setChampionId(String championId) {
		this.championId = championId;
	}
	
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
		.append("id", getId())
		.append("teamId", getTeamId())
		.append("name", getName())
		.append("photoURL", getPhotoURL())
		.append("kda", getKda())
		.append("kills", getKills())
		.append("deaths", getDeaths())
		.append("assists", getAssists())
		.append("endLevel", getEndLevel())
		.append("minionsKilled", getMinionsKilled())
		.append("totalGold", getTotalGold())
		.append("spell0", getSpell0())
		.append("spell1", getSpell1())
		.append("items0", getItems0())
		.append("items1", getItems1())
		.append("items2", getItems2())
		.append("items3", getItems3())
		.append("items4", getItems4())
		.append("items5", getItems5())
		.append("championId", getChampionId())
		.build();
	}
}
