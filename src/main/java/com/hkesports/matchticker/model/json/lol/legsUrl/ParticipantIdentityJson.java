package com.hkesports.matchticker.model.json.lol.legsUrl;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class ParticipantIdentityJson implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer participantId;
	private PlayerJson player;

	public Integer getParticipantId() {
		return participantId;
	}

	public void setParticipantId(Integer participantId) {
		this.participantId = participantId;
	}

	public PlayerJson getPlayer() {
		return player;
	}

	public void setPlayer(PlayerJson player) {
		this.player = player;
	}
	
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
		.append("participantId", getParticipantId())
		.append("player", getPlayer())
		.build();
	}
}
