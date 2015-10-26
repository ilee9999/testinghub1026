package com.hkesports.matchticker.model.json.dota2.playerSummaries;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

public class PlayerJson implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long steamid;
	
	@SerializedName("communityvisibilitystate")
	private String communityVisibilityState;
	
	@SerializedName("profilestate")
	private String profileState;
	
	@SerializedName("personaname")
	private String personaName;
	
	@SerializedName("lastlogoff")
	private Long lastLogoff;
	
	@SerializedName("profileurl")
	private String profileUrl;
	
	private String avatar;
	
	@SerializedName("avatarmedium")
	private String avatarMedium;
	
	@SerializedName("avatarfull")
	private String avatarFull;
	
	@SerializedName("personastate")
	private Integer personaState;
	
	@SerializedName("realname")
	private String realName;
	
	@SerializedName("primaryclanid")
	private String primaryClanId;
	
	@SerializedName("timecreated")
	private Long timeCreated;
	
	@SerializedName("personastateflags")
	private String personaStateFlags;
	
	@SerializedName("loccountrycode")
	private String locCountryCode;
	
	@SerializedName("locstatecode")
	private String locStateCode;
	
	@SerializedName("loccityid")
	private String locCityId;

	public Long getSteamid() {
		return steamid;
	}

	public void setSteamid(Long steamid) {
		this.steamid = steamid;
	}

	public String getCommunityVisibilityState() {
		return communityVisibilityState;
	}

	public void setCommunityVisibilityState(String communityVisibilityState) {
		this.communityVisibilityState = communityVisibilityState;
	}

	public String getProfileState() {
		return profileState;
	}

	public void setProfileState(String profileState) {
		this.profileState = profileState;
	}

	public String getPersonaName() {
		return personaName;
	}

	public void setPersonaName(String personaName) {
		this.personaName = personaName;
	}

	public Long getLastLogoff() {
		return lastLogoff;
	}

	public void setLastLogoff(Long lastLogoff) {
		this.lastLogoff = lastLogoff;
	}

	public String getProfileUrl() {
		return profileUrl;
	}

	public void setProfileUrl(String profileUrl) {
		this.profileUrl = profileUrl;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getAvatarMedium() {
		return avatarMedium;
	}

	public void setAvatarMedium(String avatarMedium) {
		this.avatarMedium = avatarMedium;
	}

	public String getAvatarFull() {
		return avatarFull;
	}

	public void setAvatarFull(String avatarFull) {
		this.avatarFull = avatarFull;
	}

	public Integer getPersonaState() {
		return personaState;
	}

	public void setPersonaState(Integer personaState) {
		this.personaState = personaState;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getPrimaryClanId() {
		return primaryClanId;
	}

	public void setPrimaryClanId(String primaryClanId) {
		this.primaryClanId = primaryClanId;
	}

	public Long getTimeCreated() {
		return timeCreated;
	}

	public void setTimeCreated(Long timeCreated) {
		this.timeCreated = timeCreated;
	}

	public String getPersonaStateFlags() {
		return personaStateFlags;
	}

	public void setPersonaStateFlags(String personaStateFlags) {
		this.personaStateFlags = personaStateFlags;
	}

	public String getLocCountryCode() {
		return locCountryCode;
	}

	public void setLocCountryCode(String locCountryCode) {
		this.locCountryCode = locCountryCode;
	}

	public String getLocStateCode() {
		return locStateCode;
	}

	public void setLocStateCode(String locStateCode) {
		this.locStateCode = locStateCode;
	}

	public String getLocCityId() {
		return locCityId;
	}

	public void setLocCityId(String locCityId) {
		this.locCityId = locCityId;
	}
}
