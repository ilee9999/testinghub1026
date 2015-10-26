package com.hkesports.matchticker.model.json.lol.item;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class ItemImageJson implements Serializable {

	private static final long serialVersionUID = 1L;

	private String full;
	private String sprite;
	private String group;
	private Integer x;
	private Integer y;
	private Integer w;
	private Integer h;

	public String getFull() {
		return full;
	}

	public void setFull(String full) {
		this.full = full;
	}

	public String getSprite() {
		return sprite;
	}

	public void setSprite(String sprite) {
		this.sprite = sprite;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public Integer getX() {
		return x;
	}

	public void setX(Integer x) {
		this.x = x;
	}

	public Integer getY() {
		return y;
	}

	public void setY(Integer y) {
		this.y = y;
	}

	public Integer getW() {
		return w;
	}

	public void setW(Integer w) {
		this.w = w;
	}

	public Integer getH() {
		return h;
	}

	public void setH(Integer h) {
		this.h = h;
	}
	
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
		.append("full", getFull())
		.append("sprite", getSprite())
		.append("group", getGroup())
		.append("x", getX())
		.append("y", getY())
		.append("w", getW())
		.append("h", getH())
		.build();
	}
}
