package com.hkesports.matchticker.model.json.lol.tournaments;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author manboyu
 *
 */
public class TournamentJson implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;
	private String namePublic;
	private boolean isFinished;
	private Date dateBegin;
	private Date dateEnd;
	private Short noVods;
	private String season;
	private boolean published;
	private String winner;
	private Map<String, ContestantJson> contestants = new HashMap<>();
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNamePublic() {
		return namePublic;
	}

	public void setNamePublic(String namePublic) {
		this.namePublic = namePublic;
	}

	public boolean isFinished() {
		return isFinished;
	}

	public void setFinished(boolean isFinished) {
		this.isFinished = isFinished;
	}

	public Date getDateBegin() {
		return dateBegin;
	}

	public void setDateBegin(Date dateBegin) {
		this.dateBegin = dateBegin;
	}

	public Date getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}

	public Short getNoVods() {
		return noVods;
	}

	public void setNoVods(Short noVods) {
		this.noVods = noVods;
	}

	public String getSeason() {
		return season;
	}

	public void setSeason(String season) {
		this.season = season;
	}

	public boolean isPublished() {
		return published;
	}

	public void setPublished(boolean published) {
		this.published = published;
	}

	public String getWinner() {
		return winner;
	}

	public void setWinner(String winner) {
		this.winner = winner;
	}
	
	public Map<String, ContestantJson> getContestants() {
		return contestants;
	}

	public void setContestants(Map<String, ContestantJson> contestants) {
		this.contestants = contestants;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
		.append("id", getId())
		.append("tournamentName", getName())
		.append("namePublic", getNamePublic())
		.append("isFinished", isFinished())
		.append("dateBegin", getDateBegin())
		.append("dateEnd", getDateEnd())
		.append("noVods", getNoVods())
		.append("season", getSeason())
		.append("published", isPublished())
		.append("noVods", getNoVods())
		.append("published", isPublished())
		.append("winner", getWinner())
		.append("contestants", getContestants())
		.build();
	}
}
