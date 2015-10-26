package com.hkesports.matchticker.model.batchJob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;

import com.hkesports.matchticker.model.basic.BasicModel;
import com.hkesports.matchticker.model.json.dota2.matchDetails.AbilityUpgradesJson;

@Deprecated
@Entity
@Table(name = "ability_upgrades_r")
public class AbilityUpgrades extends BasicModel {
	private static final long serialVersionUID = 1L;

	private Player player;
	private ScheduleGame scheduleGame;
	private Integer time;
	private Integer level;
	private Integer apiId;

	@OneToOne
	@JoinColumn(name="player_id", columnDefinition="BIGINT(20)", referencedColumnName="id")
	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	@OneToOne
	@JoinColumn(name="schedule_game_id", columnDefinition="BIGINT(20)", referencedColumnName="id")
	public ScheduleGame getScheduleGame() {
		return scheduleGame;
	}

	public void setScheduleGame(ScheduleGame scheduleGame) {
		this.scheduleGame = scheduleGame;
	}

	@Column(name="time", columnDefinition="INT(11)", nullable=true)
	public Integer getTime() {
		return time;
	}

	public void setTime(Integer time) {
		this.time = time;
	}

	@Column(name="level", columnDefinition="INT(11)")
	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	@Column(name="api_id", columnDefinition="INT(11)", nullable=true)
	public Integer getApiId() {
		return apiId;
	}

	public void setApiId(Integer apiId) {
		this.apiId = apiId;
	}
	
	@Override
	public boolean equals(Object obj){
		if(obj instanceof AbilityUpgradesJson){
			AbilityUpgradesJson json = (AbilityUpgradesJson) obj;
			if(level.equals(json.getLevel()))
				return true;
			else
				return false;
		}else
			return super.equals(obj);
	}
	
	public boolean equals(Object obj, ScheduleGame scheduleGame, Player player){
		if(obj instanceof AbilityUpgradesJson){
			AbilityUpgradesJson json = (AbilityUpgradesJson) obj;
			EqualsBuilder eb = new EqualsBuilder();
			eb.append(this.scheduleGame.getId(), scheduleGame.getId());
			eb.append(this.player.getId(), player.getId());
			eb.append(this.time, json.getTime());
			eb.append(this.level, json.getLevel());
			eb.append(this.apiId, json.getAbility());
			return eb.isEquals();
		}else
			return false;
	}
}
