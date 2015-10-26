package com.hkesports.matchticker.model.json.lol.schedule;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author manboyu
 *
 */
public class GameJson implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String winnerId;
	private Integer noVods;
	private Integer hasVod;
	private ScheduleJson scheduleJson;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getWinnerId() {
		return winnerId;
	}

	public void setWinnerId(String winnerId) {
		this.winnerId = winnerId;
	}

	public Integer getNoVods() {
		return noVods;
	}

	public void setNoVods(Integer noVods) {
		this.noVods = noVods;
	}

	public Integer getHasVod() {
		return hasVod;
	}

	public void setHasVods(Integer hasVod) {
		this.hasVod = hasVod;
	}

	public ScheduleJson getScheduleJson() {
		return scheduleJson;
	}

	public void setScheduleJson(ScheduleJson scheduleJson) {
		this.scheduleJson = scheduleJson;
	}
	
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
		.append("id", getId())
		.append("winnerId", getWinnerId())
		.append("noVods", getNoVods())
		.append("hasVods", getHasVod())
		.build();
	}
	
}
