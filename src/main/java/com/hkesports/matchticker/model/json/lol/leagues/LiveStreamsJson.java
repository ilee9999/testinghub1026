package com.hkesports.matchticker.model.json.lol.leagues;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author manboyu
 *
 */
public class LiveStreamsJson implements Serializable {

	private static final long serialVersionUID = 1L;

	private String title;
	private String url;
	private String randomize;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getRandomize() {
		return randomize;
	}

	public void setRandomize(String randomize) {
		this.randomize = randomize;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
		.append("title", getTitle())
		.append("url", getUrl())
		.append("randomize", getRandomize())
		.build();
	}
}
