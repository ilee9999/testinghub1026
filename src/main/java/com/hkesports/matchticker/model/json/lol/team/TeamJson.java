package com.hkesports.matchticker.model.json.lol.team;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author manboyu
 *
 */
public class TeamJson implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;
	private String bio;
	private Short noPlayers;
	private String logoUrl;
	private String profileUrl;
	private String teamPhotoUrl;
	private String acronym;
	private Map<String, TeamPlayerJson> roster = new HashMap<>();

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

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public Short getNoPlayers() {
		return noPlayers;
	}

	public void setNoPlayers(Short noPlayers) {
		this.noPlayers = noPlayers;
	}

	public String getLogoUrl() {
		return logoUrl;
	}

	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}

	public String getProfileUrl() {
		return profileUrl;
	}

	public void setProfileUrl(String profileUrl) {
		this.profileUrl = profileUrl;
	}

	public String getTeamPhotoUrl() {
		return teamPhotoUrl;
	}

	public void setTeamPhotoUrl(String teamPhotoUrl) {
		this.teamPhotoUrl = teamPhotoUrl;
	}

	public String getAcronym() {
		return acronym;
	}

	public void setAcronym(String acronym) {
		this.acronym = acronym;
	}

	public Map<String, TeamPlayerJson> getRoster() {
		return roster;
	}

	public void setRoster(Map<String, TeamPlayerJson> roster) {
		this.roster = roster;
	}
	
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
		.append("id", getId())
		.append("name", getName())
		.append("bio", getBio())
		.append("noPlayers", getNoPlayers())
		.append("logoUrl", getLogoUrl())
		.append("profileUrl", getProfileUrl())
		.append("teamPhotoUrl", getTeamPhotoUrl())
		.append("acronym", getAcronym())
		.append("roster", getRoster())
		.build();
	}
}
