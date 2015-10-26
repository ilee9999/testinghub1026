package com.hkesports.matchticker.model.json.lol.item;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class ItemJson implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;
	private String group;
	private String description;
	private String plaintext;
	private ItemImageJson image;

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

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPlaintext() {
		return plaintext;
	}

	public void setPlaintext(String plaintext) {
		this.plaintext = plaintext;
	}

	public ItemImageJson getImage() {
		return image;
	}

	public void setImage(ItemImageJson image) {
		this.image = image;
	}
	
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
		.append("id", getId())
		.append("name", getName())
		.append("group", getGroup())
		.append("description", getDescription())
		.append("plaintext", getPlaintext())
		.append("image", getImage())
		.build();
	}
}
