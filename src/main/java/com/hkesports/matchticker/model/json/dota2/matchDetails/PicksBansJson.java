package com.hkesports.matchticker.model.json.dota2.matchDetails;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;
import com.hkesports.matchticker.model.batchJob.GamePicksBans;

public class PicksBansJson implements Serializable {
	private static final long serialVersionUID = 1L;

	private String team;
	
	@SerializedName("hero_id")
	private Long heroId;
	
	@SerializedName("is_pick")
	private Boolean isPick;
	
	private Integer order;

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	public Long getHeroId() {
		return heroId;
	}

	public void setHeroId(Long heroId) {
		this.heroId = heroId;
	}

	public Boolean getIsPick() {
		return isPick;
	}

	public void setIsPick(Boolean isPick) {
		this.isPick = isPick;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof GamePicksBans) {
			return obj.equals(this);
		} else {
			return super.equals(obj);
		}
	}
}
