package com.hkesports.matchticker.vo;

import com.hkesports.matchticker.enums.GameItemTypeEnum;
import com.hkesports.matchticker.model.batchJob.ScheduleGamePlayerItems;

public class Dota2Item {
	private GameItemTypeEnum itemType;
	private Long itemId;
	private Short sequence = 0;

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
	public boolean equals(Object obj){
		if(obj instanceof ScheduleGamePlayerItems) {
			return obj.equals(this);
		} else {
			return super.equals(obj);
		}
	}
}
