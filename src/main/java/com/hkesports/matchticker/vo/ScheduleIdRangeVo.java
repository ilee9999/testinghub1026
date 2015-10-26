package com.hkesports.matchticker.vo;

public class ScheduleIdRangeVo {

	private Long minId;
	
	private Long maxId;

	public ScheduleIdRangeVo() {}
	
	public ScheduleIdRangeVo(Long minId, Long maxId) {
		this.minId = minId;
		this.maxId = maxId;
	}

	public Long getMinId() {
		return minId;
	}

	public void setMinId(Long minId) {
		this.minId = minId;
	}

	public Long getMaxId() {
		return maxId;
	}

	public void setMaxId(Long maxId) {
		this.maxId = maxId;
	}
}
