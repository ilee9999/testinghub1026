package com.hkesports.matchticker.model.json.lol.legsUrl;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class TimelineJson implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer participantId;
	private DeltasJson creepsPerMinDeltas;
	private DeltasJson xpPerMinDeltas;
	private DeltasJson goldPerMinDeltas;
	private DeltasJson damageTakenPerMinDeltas;
	private String role;
	private String lane;

	public Integer getParticipantId() {
		return participantId;
	}

	public void setParticipantId(Integer participantId) {
		this.participantId = participantId;
	}

	public DeltasJson getCreepsPerMinDeltas() {
		return creepsPerMinDeltas;
	}

	public void setCreepsPerMinDeltas(DeltasJson creepsPerMinDeltas) {
		this.creepsPerMinDeltas = creepsPerMinDeltas;
	}

	public DeltasJson getXpPerMinDeltas() {
		return xpPerMinDeltas;
	}

	public void setXpPerMinDeltas(DeltasJson xpPerMinDeltas) {
		this.xpPerMinDeltas = xpPerMinDeltas;
	}

	public DeltasJson getGoldPerMinDeltas() {
		return goldPerMinDeltas;
	}

	public void setGoldPerMinDeltas(DeltasJson goldPerMinDeltas) {
		this.goldPerMinDeltas = goldPerMinDeltas;
	}

	public DeltasJson getDamageTakenPerMinDeltas() {
		return damageTakenPerMinDeltas;
	}

	public void setDamageTakenPerMinDeltas(DeltasJson damageTakenPerMinDeltas) {
		this.damageTakenPerMinDeltas = damageTakenPerMinDeltas;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getLane() {
		return lane;
	}

	public void setLane(String lane) {
		this.lane = lane;
	}
	
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
		.append("participantId", getParticipantId())
		.append("creepsPerMinDeltas", getCreepsPerMinDeltas())
		.append("xpPerMinDeltas", getXpPerMinDeltas())
		.append("goldPerMinDeltas", getGoldPerMinDeltas())
		.append("damageTakenPerMinDeltas", getDamageTakenPerMinDeltas())
		.append("role", getRole())
		.append("lane", getLane())
		.build();
	}
}
