package com.hkesports.matchticker.vo;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.hkesports.matchticker.enums.GameItemTypeEnum;
import com.hkesports.matchticker.model.batchJob.ScheduleGamePlayerItems;

/**
 * @author manboyu
 *
 */
public class LoLItem implements Serializable {

	private static final long serialVersionUID = 1L;

	private GameItemTypeEnum itemType;
	private Long itemId;
	private Short sequence = 0;
	
	public LoLItem(){
		
	}
	
	public LoLItem(GameItemTypeEnum itemType, Long itemId) {
		this.itemType = itemType;
		this.itemId = itemId;
	}
	
	public LoLItem(GameItemTypeEnum itemType, Long itemId, Short sequence) {
		this.itemType = itemType;
		this.itemId = itemId;
		this.sequence = sequence;
	}

	public GameItemTypeEnum getItemType() {
		return itemType;
	}

	public void setItemType(GameItemTypeEnum itemType) {
		this.itemType = itemType;
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public Short getSequence() {
		return sequence;
	}

	public void setSequence(Short sequence) {
		this.sequence = sequence;
	}
	
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
		.append("itemType", getItemType())
		.append("itemId", getItemId())
		.append("sequence", getSequence())
		.build();
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof ScheduleGamePlayerItems) {
			ScheduleGamePlayerItems item = (ScheduleGamePlayerItems) obj;
			if(itemType.equals(item.getItemType()) && sequence.equals(item.getSequence()))
				return true;
			else
				return false;
		}
		return super.equals(obj);
	}
}
