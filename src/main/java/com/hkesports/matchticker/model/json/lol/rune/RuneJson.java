package com.hkesports.matchticker.model.json.lol.rune;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class RuneJson implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String name;
	private String description;
	private RuneImageJson image;
	private RuneDetailJson rune;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public RuneImageJson getImage() {
		return image;
	}

	public void setImage(RuneImageJson image) {
		this.image = image;
	}

	public RuneDetailJson getRune() {
		return rune;
	}

	public void setRune(RuneDetailJson rune) {
		this.rune = rune;
	}
	
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
		.append("id", getId())
		.append("name", getName())
		.append("description", getDescription())
		.append("image", getImage())
		.append("rune", getRune())
		.build();
	}
}
