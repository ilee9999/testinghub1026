package com.hkesports.matchticker.model.json.lol.game;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author manboyu
 *
 */
public class GameVodJson implements Serializable {

	private static final long serialVersionUID = 1L;

	private String type;
	private String URL;
	private String embedCode;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getURL() {
		return URL;
	}

	public void setURL(String uRL) {
		URL = uRL;
	}

	public String getEmbedCode() {
		return embedCode;
	}

	public void setEmbedCode(String embedCode) {
		this.embedCode = embedCode;
	}
	
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
		.append("type", getType())
		.append("URL", getURL())
		.append("embedCode", getEmbedCode())
		.build();
	}
}
