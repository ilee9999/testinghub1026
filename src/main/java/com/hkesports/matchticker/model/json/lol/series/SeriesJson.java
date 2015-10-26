package com.hkesports.matchticker.model.json.lol.series;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author manboyu
 *
 */
public class SeriesJson implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;
	private String labelPublic;
	private String season;
	private String label;
	private String url;
	private List<String> tournaments = new ArrayList<>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLabelPublic() {
		return labelPublic;
	}

	public void setLabelPublic(String labelPublic) {
		this.labelPublic = labelPublic;
	}

	public String getSeason() {
		return season;
	}

	public void setSeason(String season) {
		this.season = season;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<String> getTournaments() {
		return tournaments;
	}

	public void setTournaments(List<String> tournaments) {
		this.tournaments = tournaments;
	}
	
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
		.append("id", getId())
		.append("labelPublic", getLabelPublic())
		.append("season", getSeason())
		.append("label", getLabel())
		.append("url", getUrl())
		.append("tournaments", getTournaments())
		.build();
	}
}
