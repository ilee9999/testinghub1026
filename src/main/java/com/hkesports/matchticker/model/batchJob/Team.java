package com.hkesports.matchticker.model.batchJob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;

import com.hkesports.matchticker.enums.GameTypeEnum;
import com.hkesports.matchticker.model.basic.BasicApiInfo;
import com.hkesports.matchticker.model.json.lol.team.TeamJson;
import com.hkesports.matchticker.vo.Dota2TeamJsonVo;

@Entity
@Table(name = "team_r")
public class Team extends BasicApiInfo {
	private static final long serialVersionUID = 1L;

	private String teamName;
	private String teamFullName;
	private String country;
	private String teamIconSmall;
	private String teamIconLarge;
	private String teamUrl;
	private String bio;
	private String teamPhotoUrl;
	private String logoUrl;
	private Short noPlayers;
	private String rating;
	
	@Column(name="team_name", length=128)
	public String getTeamName() {
		return teamName;
	}
	
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	
	@Column(name="team_full_name", length=128)
	public String getTeamFullName() {
		return teamFullName;
	}
	
	public void setTeamFullName(String teamFullName) {
		this.teamFullName = teamFullName;
	}
	
	@Column(name="country", length=2, nullable=true)
	public String getCountry() {
		return country;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}
	
	@Column(name="team_icon_small", length = 10, nullable = true)
	public String getTeamIconSmall() {
		return teamIconSmall;
	}
	
	public void setTeamIconSmall(String teamIconSmall) {
		this.teamIconSmall = teamIconSmall;
	}
	
	@Column(name="team_icon_large", length = 10, nullable = true)
	public String getTeamIconLarge() {
		return teamIconLarge;
	}
	
	public void setTeamIconLarge(String teamIconLarge) {
		this.teamIconLarge = teamIconLarge;
	}
	
	@Column(name="team_url", length=255, nullable = true)
	public String getTeamUrl() {
		return teamUrl;
	}
	
	public void setTeamUrl(String teamUrl) {
		this.teamUrl = teamUrl;
	}
	
	@Column(name="bio", columnDefinition="TEXT", nullable=true)
	public String getBio() {
		return bio;
	}
	
	public void setBio(String bio) {
		this.bio = bio;
	}
	
	@Column(name="team_photo_url", length=255, nullable=true)
	public String getTeamPhotoUrl() {
		return teamPhotoUrl;
	}
	public void setTeamPhotoUrl(String teamPhotoUrl) {
		this.teamPhotoUrl = teamPhotoUrl;
	}
	
	@Column(name="logo_url", length=255, nullable=true)
	public String getLogoUrl() {
		return logoUrl;
	}
	
	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}
	
	@Column(name="no_players", columnDefinition="SMALLINT(6)", nullable=true)
	public Short getNoPlayers() {
		return noPlayers;
	}
	
	public void setNoPlayers(Short noPlayers) {
		this.noPlayers = noPlayers;
	}
	
	@Column(name="rating", length=255, nullable=true)
	public String getRating() {
		return rating;
	}
	
	public void setRating(String rating) {
		this.rating = rating;
	}
	
	public boolean equals(Object obj){
		if(obj instanceof Dota2TeamJsonVo){
			Dota2TeamJsonVo vo = (Dota2TeamJsonVo) obj;
			EqualsBuilder eb = new EqualsBuilder();
			eb.append(getGameType(), GameTypeEnum.DOTA2);
			eb.append(getApiId(), vo.getTeamId());
			eb.append(getTeamName(), vo.getName());
			eb.append(getTeamFullName(), vo.getName());
			eb.append(getCountry(), vo.getCountryCode());
			eb.append(getTeamUrl(), vo.getUrl());
			eb.append(getRating(), vo.getRating());
			eb.append(getLogoUrl(), vo.getLogo()!=null?vo.getLogo().toString():null);
			return eb.isEquals();
		} else if(obj instanceof TeamJson){
			TeamJson teamJson = (TeamJson)obj;
			return new EqualsBuilder()
				.append(getApiId(), teamJson.getId())
				.append(getGameType(), GameTypeEnum.LOL)
				.append(getTeamName(), StringUtils.isBlank(teamJson.getAcronym()) ? GameTypeEnum.LOL + "_" + teamJson.getId() : teamJson.getAcronym())
				.append(getTeamFullName(), teamJson.getName())
				.append(getNoPlayers(), teamJson.getNoPlayers())
				.append(getTeamUrl(), teamJson.getProfileUrl())
				.append(getTeamPhotoUrl(), teamJson.getTeamPhotoUrl())
				.append(getLogoUrl(), teamJson.getLogoUrl())
				.isEquals();
		}
		return super.equals(obj);
	}
}
