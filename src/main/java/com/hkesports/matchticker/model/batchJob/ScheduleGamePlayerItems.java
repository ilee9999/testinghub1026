package com.hkesports.matchticker.model.batchJob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;

import com.hkesports.matchticker.enums.GameItemTypeEnum;
import com.hkesports.matchticker.model.basic.BasicModel;
import com.hkesports.matchticker.vo.Dota2Item;
import com.hkesports.matchticker.vo.LoLItem;

@Entity
@Table(name = "schedule_game_player_items_r")
public class ScheduleGamePlayerItems extends BasicModel {
	
	private static final long serialVersionUID = 1L;

	private ScheduleGame scheduleGame;
	private Player player;
	private GameItemTypeEnum itemType;
	private Long itemId;
	private Short sequence = 0;
	
	@ManyToOne
	@JoinColumn(name="schedule_game_id", columnDefinition="BIGINT(20)", nullable=true)
	public ScheduleGame getScheduleGame() {
		return scheduleGame;
	}
	
	public void setScheduleGame(ScheduleGame scheduleGame) {
		this.scheduleGame = scheduleGame;
	}
	
	@ManyToOne
	@JoinColumn(name="player_id", columnDefinition="BIGINT(20)", referencedColumnName="id")
	public Player getPlayer() {
		return player;
	}
	
	public void setPlayer(Player player) {
		this.player = player;
	}
		
	@Enumerated(EnumType.STRING)
	@Column(name = "item_type", length = 10)
	public GameItemTypeEnum getItemType() {
		return itemType;
	}
	
	public void setItemType(GameItemTypeEnum itemType) {
		this.itemType = itemType;
	}
	
	@Column(name="item_id", columnDefinition = "BIGINT(20)")
	public Long getItemId() {
		return itemId;
	}
	
	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}
	
	@Column(name="sequence", columnDefinition="SMALLINT(6)")
	public Short getSequence() {
		return sequence;
	}
	
	public void setSequence(Short sequence) {
		this.sequence = sequence;
	}
	
	@Override
	public boolean equals(Object obj){
		if(obj instanceof Dota2Item){
			Dota2Item item = (Dota2Item) obj;
			if(itemType.equals(item.getItemType()) && sequence.equals(item.getSequence()))
				return true;
			else
				return false;
		}
		return super.equals(obj);
	}
	
	public boolean equals(Object obj, Long scheduleGameId, Long playerId){
		if(obj instanceof Dota2Item) {
			Dota2Item item = (Dota2Item) obj;
			EqualsBuilder eb = new EqualsBuilder();
			eb.append(scheduleGame.getId(), scheduleGameId);
			eb.append(player.getId(), playerId);
			eb.append(this.itemType, item.getItemType());
			eb.append(this.itemId, item.getItemId());
			eb.append(this.sequence, item.getSequence());
			return eb.isEquals();
		} else  if(obj instanceof LoLItem) {
			LoLItem item = (LoLItem)obj;
			EqualsBuilder eq = new EqualsBuilder();
			eq.append(getScheduleGame().getApiId(), scheduleGameId);
			eq.append(getPlayer().getApiId(), playerId);
			eq.append(getItemId(), item.getItemId());
			eq.append(getItemType(), item.getItemType());
			return false;
		}
		
		return super.equals(obj);
	}
}
