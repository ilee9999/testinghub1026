package com.hkesports.matchticker.model.json.lol.rune;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class RuneDetailJson implements Serializable {

	private static final long serialVersionUID = 1L;

	private Boolean isRune;
	private Integer tier;
	private String type;

	public Boolean getIsRune() {
		return isRune;
	}

	public void setIsRune(Boolean isRune) {
		this.isRune = isRune;
	}

	public Integer getTier() {
		return tier;
	}

	public void setTier(Integer tier) {
		this.tier = tier;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
		.append("isRune", getIsRune())
		.append("tier", getTier())
		.append("type", getType())
		.build();
	}
}
