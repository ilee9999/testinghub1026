package com.hkesports.matchticker.model.json.lol.player;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author manboyu
 *
 */
public class PlayerJson implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;
	private String bio;
	private String firstname;
	private String lastName;
	private String hometown;
	private String facebookUrl;
	private String twitterUrl;
	private Integer teamId;
	private String profileUrl;
	private String role;
	private String roleId;
	private String photoUrl;
	private Short isStarter;
	private String residency;
	private Date contractExpiration;

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

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getHometown() {
		return hometown;
	}

	public void setHometown(String hometown) {
		this.hometown = hometown;
	}

	public String getFacebookUrl() {
		return facebookUrl;
	}

	public void setFacebookUrl(String facebookUrl) {
		this.facebookUrl = facebookUrl;
	}

	public String getTwitterUrl() {
		return twitterUrl;
	}

	public void setTwitterUrl(String twitterUrl) {
		this.twitterUrl = twitterUrl;
	}

	public Integer getTeamId() {
		return teamId;
	}

	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}

	public String getProfileUrl() {
		return profileUrl;
	}

	public void setProfileUrl(String profileUrl) {
		this.profileUrl = profileUrl;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getPhotoUrl() {
		return photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}

	public Short getIsStarter() {
		return isStarter;
	}

	public void setIsStarter(Short isStarter) {
		this.isStarter = isStarter;
	}

	public String getResidency() {
		return residency;
	}

	public void setResidency(String residency) {
		this.residency = residency;
	}

	public Date getContractExpiration() {
		return contractExpiration;
	}

	public void setContractExpiration(Date contractExpiration) {
		this.contractExpiration = contractExpiration;
	}
	
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
		.append("id", getId())
		.append("name", getName())
		.append("bio", getBio())
		.append("firstname", getFirstname())
		.append("lastName", getLastName())
		.append("hometown", getHometown())
		.append("facebookUrl", getFacebookUrl())
		.append("twitterUrl", getTwitterUrl())
		.append("teamId", getTeamId())
		.append("profileUrl", getProfileUrl())
		.append("role", getRole())
		.append("roleId", getRoleId())
		.append("photoUrl", getPhotoUrl())
		.append("isStarter", getIsStarter())
		.append("residency", getResidency())
		.append("contractExpiration", getContractExpiration())
		.build();
	}
}
