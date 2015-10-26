package com.hkesports.matchticker.model.json.lol.legsUrl;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.google.gson.annotations.SerializedName;

public class DeltasJson implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@SerializedName("0-10")
	private Double deltas0_10;
	@SerializedName("10-20")
	private Double deltas10_20;
	@SerializedName("20-30")
	private Double deltas20_30;
	@SerializedName("30-end")
	private Double deltas30_end;

	public Double getDeltas0_10() {
		return deltas0_10;
	}

	public void setDeltas0_10(Double deltas0_10) {
		this.deltas0_10 = deltas0_10;
	}

	public Double getDeltas10_20() {
		return deltas10_20;
	}

	public void setDeltas10_20(Double deltas10_20) {
		this.deltas10_20 = deltas10_20;
	}

	public Double getDeltas20_30() {
		return deltas20_30;
	}

	public void setDeltas20_30(Double deltas20_30) {
		this.deltas20_30 = deltas20_30;
	}

	public Double getDeltas30_end() {
		return deltas30_end;
	}

	public void setDeltas30_end(Double deltas30_end) {
		this.deltas30_end = deltas30_end;
	}
	
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
		.append("deltas0_10", getDeltas0_10())
		.append("deltas10_20", getDeltas10_20())
		.append("deltas20_30", getDeltas20_30())
		.append("deltas30_end", getDeltas30_end())
		.build();
	}
}
