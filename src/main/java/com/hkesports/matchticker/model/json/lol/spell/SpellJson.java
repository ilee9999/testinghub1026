package com.hkesports.matchticker.model.json.lol.spell;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class SpellJson implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;
	private String description;
	private String key;
	private Integer summonerLevel;
	private SpellImageJson image;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Integer getSummonerLevel() {
		return summonerLevel;
	}

	public void setSummonerLevel(Integer summonerLevel) {
		this.summonerLevel = summonerLevel;
	}

	public SpellImageJson getImage() {
		return image;
	}

	public void setImage(SpellImageJson image) {
		this.image = image;
	}
	
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
		.append("id", getId())
		.append("name", getName())
		.append("description", getDescription())
		.append("key", getKey())
		.append("summonerLevel", getSummonerLevel())
		.append("image", getImage())
		.build();
	}
}
