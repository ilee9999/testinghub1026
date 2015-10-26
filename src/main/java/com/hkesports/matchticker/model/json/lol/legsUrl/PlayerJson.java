package com.hkesports.matchticker.model.json.lol.legsUrl;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class PlayerJson implements Serializable {

	private static final long serialVersionUID = 1L;

	private String summonerName;
	private String profileIcon;

	public String getSummonerName() {
		return summonerName;
	}

	public void setSummonerName(String summonerName) {
		this.summonerName = summonerName;
	}

	public String getProfileIcon() {
		return profileIcon;
	}

	public void setProfileIcon(String profileIcon) {
		this.profileIcon = profileIcon;
	}
	
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
		.append("summonerName", getSummonerName())
		.append("profileIcon", getProfileIcon())
		.build();
	}
}
