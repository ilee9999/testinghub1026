package com.hkesports.matchticker.model.json.dota2.matchDetails;

import java.io.Serializable;

import com.hkesports.matchticker.model.batchJob.AbilityUpgrades;

public class AbilityUpgradesJson implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer ability;
	
	private Integer time;
	
	private Integer level;

	public Integer getAbility() {
		return ability;
	}

	public void setAbility(Integer ability) {
		this.ability = ability;
	}

	public Integer getTime() {
		return time;
	}

	public void setTime(Integer time) {
		this.time = time;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof AbilityUpgrades) {
			return obj.equals(this);
		} else {
			return super.equals(obj);
		}
	}
}
