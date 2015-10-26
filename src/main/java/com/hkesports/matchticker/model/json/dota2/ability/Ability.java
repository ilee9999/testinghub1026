package com.hkesports.matchticker.model.json.dota2.ability;

import java.io.Serializable;

import com.hkesports.matchticker.model.batchJob.SpellData;

public class Ability implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private Long heroId;
	private String name;
	private String localizedName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getHeroId() {
		return heroId;
	}

	public void setHeroId(Long heroId) {
		this.heroId = heroId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocalizedName() {
		return localizedName;
	}

	public void setLocalizedName(String localizedName) {
		this.localizedName = localizedName;
	}
	
	@Override
	public boolean equals(Object obj){
		if(obj instanceof SpellData){
			SpellData spell = (SpellData) obj;
			if(spell.getApiId().equals(getId()) && spell.getEnName().equals(getName()) && spell.getName().equals(getLocalizedName()))
				return true;
			else
				return false;
		}else
			return super.equals(obj);
	}
}
