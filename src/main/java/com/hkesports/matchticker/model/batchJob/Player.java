package com.hkesports.matchticker.model.batchJob;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.hkesports.matchticker.enums.GameTypeEnum;
import com.hkesports.matchticker.model.basic.BasicApiInfo;

@Entity
@Table(name = "player_r")
public class Player extends BasicApiInfo {
	
	private static final long serialVersionUID = 1L;

	private String playerName;
	private String playerFullName;
	private String country;
	private String playerIconSmall;
	private String playerIconLarge;
	private String playerUrl;
	private String photoUrl;
	private String bio;
	private String hometown;
	private String facebookUrl;
	private String twitterUrl;
	private Short isStarter;
	private String residency;
	private Date contractExpiration;
	
	@Column(name="player_name", length=128, nullable = false)
	public String getPlayerName() {
		return playerName;
	}
	
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	
	@Column(name="player_full_name", length=128, nullable = false)
	public String getPlayerFullName() {
		return playerFullName;
	}
	
	public void setPlayerFullName(String playerFullName) {
		this.playerFullName = playerFullName;
	}
	
	@Column(name="country", length = 2, nullable = true)
	public String getCountry() {
		return country;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}
	
	@Column(name="player_icon_small", length = 10, nullable = true)
	public String getPlayerIconSmall() {
		return playerIconSmall;
	}
	
	public void setPlayerIconSmall(String playerIconSmall) {
		this.playerIconSmall = playerIconSmall;
	}
	
	@Column(name="player_icon_large", length = 10, nullable = true)
	public String getPlayerIconLarge() {
		return playerIconLarge;
	}
	
	public void setPlayerIconLarge(String playerIconLarge) {
		this.playerIconLarge = playerIconLarge;
	}
	
	@Column(name="player_url", length = 255, nullable = true)
	public String getPlayerUrl() {
		return playerUrl;
	}
	
	public void setPlayerUrl(String playerUrl) {
		this.playerUrl = playerUrl;
	}
	
	@Column(name="photo_url", length = 255, nullable = true)
	public String getPhotoUrl() {
		return photoUrl;
	}
	
	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}
	
	@Column(name="bio", columnDefinition = "TEXT", nullable = true)
	public String getBio() {
		return bio;
	}
	
	public void setBio(String bio) {
		this.bio = bio;
	}
	
	@Column(name="hometown", length = 100, nullable = true)
	public String getHometown() {
		return hometown;
	}
	
	public void setHometown(String hometown) {
		this.hometown = hometown;
	}
	
	@Column(name="facebook_url", length = 255, nullable = true)
	public String getFacebookUrl() {
		return facebookUrl;
	}
	
	public void setFacebookUrl(String facebookUrl) {
		this.facebookUrl = facebookUrl;
	}
	
	@Column(name="twitter_url", length = 255, nullable = true)
	public String getTwitterUrl() {
		return twitterUrl;
	}
	
	public void setTwitterUrl(String twitterUrl) {
		this.twitterUrl = twitterUrl;
	}
	
	@Column(name="is_starter", columnDefinition = "SMALLINT(6)", nullable = true)
	public Short getIsStarter() {
		return isStarter;
	}
	
	public void setIsStarter(Short isStarter) {
		this.isStarter = isStarter;
	}
	
	@Column(name="residency", length=255, nullable = true)
	public String getResidency() {
		return residency;
	}
	
	public void setResidency(String residency) {
		this.residency = residency;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="contract_expiration", nullable = true)
	public Date getContractExpiration() {
		return contractExpiration;
	}
	
	public void setContractExpiration(Date contractExpiration) {
		this.contractExpiration = contractExpiration;
	}
	
	@Override
	public boolean equals(Object obj){
		if(obj instanceof com.hkesports.matchticker.model.json.dota2.playerSummaries.PlayerJson){
			com.hkesports.matchticker.model.json.dota2.playerSummaries.PlayerJson json = (com.hkesports.matchticker.model.json.dota2.playerSummaries.PlayerJson) obj;
			EqualsBuilder eb = new EqualsBuilder();
			eb.append(getGameType(), GameTypeEnum.DOTA2);
			eb.append(getApiId(), bindTo32Bit(json.getSteamid()));
			eb.append(getPlayerName(), json.getPersonaName());
			
			if(StringUtils.isBlank(json.getRealName())) {
				eb.append(getPlayerFullName(), json.getPersonaName());
			} else {
				eb.append(getPlayerFullName(), json.getRealName());
			}
			
			eb.append(getCountry(), json.getLocCountryCode());
			eb.append(getPlayerUrl(), json.getProfileUrl());
			eb.append(getPhotoUrl(), json.getAvatarFull());
			return eb.isEquals();
		} else if(obj instanceof com.hkesports.matchticker.model.json.lol.player.PlayerJson) {
			com.hkesports.matchticker.model.json.lol.player.PlayerJson playerJson = (com.hkesports.matchticker.model.json.lol.player.PlayerJson)obj;
			return new EqualsBuilder()
			.append(getApiId(), playerJson.getId())
			.append(getGameType(), GameTypeEnum.LOL)
			.append(getBio(), playerJson.getBio())
			.append(getPlayerName(), StringUtils.isNotBlank(playerJson.getName()) ? playerJson.getName() : GameTypeEnum.LOL + "_" + playerJson.getId() )
			.append(getPlayerFullName(), playerJson.getFirstname() + playerJson.getLastName())
			.append(getPlayerUrl(), playerJson.getProfileUrl())
			.append(getPhotoUrl(), playerJson.getPhotoUrl())
			.append(getHometown(), playerJson.getHometown())
			.append(getFacebookUrl(), playerJson.getFacebookUrl())
			.append(getTwitterUrl(), playerJson.getTwitterUrl())
			.append(getIsStarter(), playerJson.getIsStarter())
			.append(getResidency(), playerJson.getResidency())
			.append(getContractExpiration() != null ? getContractExpiration().getTime() : null, playerJson.getContractExpiration() != null ? playerJson.getContractExpiration().getTime() : null)
			.isEquals();
		}
			
		return super.equals(obj);
	}
	
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
		.append("id", getId())
		.append("apiId", getApiId())
		.append("gameType", getGameType())
		.append("playerName", getPlayerName())
		.append("playerFullName", getPlayerFullName())
		.append("country", getCountry())
		.append("playerIconSmall", getPlayerIconSmall())
		.append("playerIconLarge", getPlayerIconLarge())
		.append("playerUrl", getPlayerUrl())
		.append("photoUrl", getPhotoUrl())
		.append("bio", getBio())
		.append("hometown", getHometown())
		.append("facebookUrl", getFacebookUrl())
		.append("twitterUrl", getTwitterUrl())
		.append("isStarter", getIsStarter())
		.append("residency", getResidency())
		.append("contractExpiration", getContractExpiration())
		.build();
	}
	
	private Long bindTo32Bit(Long id){
		return id - 76561197960265728L;
	}
}
