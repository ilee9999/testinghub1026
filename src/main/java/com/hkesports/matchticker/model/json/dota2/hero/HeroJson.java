package com.hkesports.matchticker.model.json.dota2.hero;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

public class HeroJson implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String name;
	
	@SerializedName("localized_name")
	private String localizedName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
}
