package com.hkesports.matchticker.model.json.lol.champion;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class ChampionJson implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String key;
	private String name;
	private String title;
	private ChampionImageJson image;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public ChampionImageJson getImage() {
		return image;
	}

	public void setImage(ChampionImageJson image) {
		this.image = image;
	}
	
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
		.append("id", getId())
		.append("key", getKey())
		.append("name", getName())
		.append("title", getTitle())
		.append("image", getImage())
		.build();
	}
}
