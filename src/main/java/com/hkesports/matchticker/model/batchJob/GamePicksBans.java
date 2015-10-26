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

import com.hkesports.matchticker.enums.GameTypeEnum;
import com.hkesports.matchticker.model.basic.BasicModel;
import com.hkesports.matchticker.model.json.dota2.matchDetails.PicksBansJson;
import com.hkesports.matchticker.model.json.lol.legsUrl.BanJson;

@Entity
@Table(name = "game_picks_bans_r")
public class GamePicksBans extends BasicModel {
	private static final long serialVersionUID = 1L;

	private GameTypeEnum gameType;
	private ScheduleGame scheduleGame;
	private Long teamId;
	private Hero hero;
	private Boolean isPick;
	private Integer order;

	@Enumerated(EnumType.STRING)
	@Column(name = "game_type", length = 10)
	public GameTypeEnum getGameType() {
		return gameType;
	}

	public void setGameType(GameTypeEnum gameType) {
		this.gameType = gameType;
	}

	@ManyToOne
	@JoinColumn(name="schedule_game_id", columnDefinition="BIGINT(20)", referencedColumnName="id", nullable=true)
	public ScheduleGame getScheduleGame() {
		return scheduleGame;
	}

	public void setScheduleGame(ScheduleGame scheduleGame) {
		this.scheduleGame = scheduleGame;
	}
	
	@Column(name="team_id", columnDefinition="BIGINT(20)", nullable=true)
	public Long getTeamId() {
		return teamId;
	}

	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}

	@OneToOne
	@JoinColumn(name="hero_id", columnDefinition="BIGINT(20)", referencedColumnName="id")
	public Hero getHero() {
		return hero;
	}

	public void setHero(Hero hero) {
		this.hero = hero;
	}

	@Column(name="is_pick", columnDefinition="TINYINT")
	public Boolean getIsPick() {
		return isPick;
	}

	public void setIsPick(Boolean isPick) {
		this.isPick = isPick;
	}

	@Column(name="item_order", columnDefinition="INT(11)", nullable=true)
	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}
	
	@Override
	public boolean equals(Object obj){
		if(obj instanceof PicksBansJson){
			PicksBansJson json = (PicksBansJson) obj;
			if(this.order.equals(json.getOrder()))
				return true;
			else
				return false;
		}else
			return super.equals(obj);
	}
	
	public boolean equals(Object obj, Hero hero){
		if(obj instanceof PicksBansJson){
			PicksBansJson json = (PicksBansJson) obj;
			EqualsBuilder eb = new EqualsBuilder();
			eb.append(this.hero != null ? this.hero.getId() : null, hero != null ? hero.getId() : null);
			eb.append(this.order, json.getOrder());
			eb.append(this.isPick, json.getIsPick());
			return eb.isEquals();
		}else
			return super.equals(obj);
	}
	
	public boolean equals(BanJson banJson, ScheduleGame scheduleGame, Hero hero){
		EqualsBuilder eq = new EqualsBuilder();
		eq.append(getScheduleGame().getApiId(), scheduleGame.getApiId());
		eq.append(getGameType(), GameTypeEnum.LOL);
		eq.append(getHero() != null ? this.getHero().getId() : null, hero != null ? hero.getId() : null);
		eq.append(getOrder(), banJson.getPickTurn());
		return eq.isEquals();
	}
}
