package com.hkesports.matchticker.model.json.lol.champion;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class ChampionListJson implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String type;
	private String version;
	private Map<String, ChampionJson> data = new HashMap<>();

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public Map<String, ChampionJson> getData() {
		return data;
	}

	public void setData(Map<String, ChampionJson> data) {
		this.data = data;
	}
	
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
		.append("type", getType())
		.append("version", getVersion())
		.build();
	}
}
