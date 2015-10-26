package com.hkesports.matchticker.model.json.lol.legsUrl;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class BanJson implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer championId;
	private Integer pickTurn;

	public Integer getChampionId() {
		return championId;
	}

	public void setChampionId(Integer championId) {
		this.championId = championId;
	}

	public Integer getPickTurn() {
		return pickTurn;
	}

	public void setPickTurn(Integer pickTurn) {
		this.pickTurn = pickTurn;
	}
	
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
		.append("championId", getChampionId())
		.append("pickTurn", getPickTurn())
		.build();
	}
}
