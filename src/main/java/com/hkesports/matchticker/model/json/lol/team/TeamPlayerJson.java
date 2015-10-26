package com.hkesports.matchticker.model.json.lol.team;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author manboyu
 *
 */
public class TeamPlayerJson implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long playerId;
	private String name;
	private String role;
	private Integer isStarter;

	public Long getPlayerId() {
		return playerId;
	}

	public void setPlayerId(Long playerId) {
		this.playerId = playerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Integer getIsStarter() {
		return isStarter;
	}

	public void setIsStarter(Integer isStarter) {
		this.isStarter = isStarter;
	}
	
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
		.append("playerId", getPlayerId())
		.append("name", getName())
		.append("role", getRole())
		.append("isStarter", getIsStarter())
		.build();
	}
}
