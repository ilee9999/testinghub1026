package com.hkesports.matchticker.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.hkesports.matchticker.enums.GameTypeEnum;
import com.hkesports.matchticker.model.basic.BasicModel;

@Entity
@Table(name = "game")
public class Game extends BasicModel {
	
	private static final long serialVersionUID = 1L;

	private String enGameName;
	private String twGameName;
	private String gameIconSmall;
	private String gameIconLarge;
	private String gameUrl;
	private Boolean team = true;
	private String teamADefaultIconSmall;
	private String teamBDefaultIconSmall;
	private String teamADefaultIconLarge;
	private String teamBDefaultIconLarge;
	private Short winScore = 3;
	private Short drawScore = 1;
	private Short loseScore = 0;
	private GameTypeEnum gameCode;
	
	@Column(name = "en_game_name", length = 100, nullable = false)
	public String getEnGameName() {
		return enGameName;
	}
	
	public void setEnGameName(String enGameName) {
		this.enGameName = enGameName;
	}
	
	@Column(name = "tw_game_name", length = 100, nullable = false)
	public String getTwGameName() {
		return twGameName;
	}
	
	public void setTwGameName(String twGameName) {
		this.twGameName = twGameName;
	}
	
	@Column(name = "game_icon_small", length = 10, nullable = true)
	public String getGameIconSmall() {
		return gameIconSmall;
	}
	
	public void setGameIconSmall(String gameIconSmall) {
		this.gameIconSmall = gameIconSmall;
	}
	
	@Column(name = "game_icon_large", length = 10, nullable = true)
	public String getGameIconLarge() {
		return gameIconLarge;
	}
	
	public void setGameIconLarge(String gameIconLarge) {
		this.gameIconLarge = gameIconLarge;
	}
	
	@Column(name = "game_url", length = 255, nullable = true)
	public String getGameUrl() {
		return gameUrl;
	}
	
	public void setGameUrl(String gameUrl) {
		this.gameUrl = gameUrl;
	}
	
	@Column(name = "team_a_default_icon_small", length = 10, nullable = true)
	public String getTeamADefaultIconSmall() {
		return teamADefaultIconSmall;
	}
	
	public void setTeamADefaultIconSmall(String teamADefaultIconSmall) {
		this.teamADefaultIconSmall = teamADefaultIconSmall;
	}
	
	@Column(name = "team_b_default_icon_small", length = 10, nullable = true)
	public String getTeamBDefaultIconSmall() {
		return teamBDefaultIconSmall;
	}
	
	public void setTeamBDefaultIconSmall(String teamBDefaultIconSmall) {
		this.teamBDefaultIconSmall = teamBDefaultIconSmall;
	}
	
	@Column(name = "team_a_default_icon_large", length = 10, nullable = true)
	public String getTeamADefaultIconLarge() {
		return teamADefaultIconLarge;
	}
	
	public void setTeamADefaultIconLarge(String teamADefaultIconLarge) {
		this.teamADefaultIconLarge = teamADefaultIconLarge;
	}
	
	@Column(name = "team_b_default_icon_large", length = 10, nullable = true)
	public String getTeamBDefaultIconLarge() {
		return teamBDefaultIconLarge;
	}
	
	public void setTeamBDefaultIconLarge(String teamBDefaultIconLarge) {
		this.teamBDefaultIconLarge = teamBDefaultIconLarge;
	}
	
	@Column(name = "win_score", columnDefinition = "SMALLINT(6)", nullable = false)
	public Short getWinScore() {
		return winScore;
	}
	
	public void setWinScore(Short winScore) {
		this.winScore = winScore;
	}
	
	@Column(name = "draw_score", columnDefinition = "SMALLINT(6)", nullable=false)
	public Short getDrawScore() {
		return drawScore;
	}
	
	public void setDrawScore(Short drawScore) {
		this.drawScore = drawScore;
	}
	
	@Column(name = "lose_score", columnDefinition = "SMALLINT(6)", nullable = false)
	public Short getLoseScore() {
		return loseScore;
	}
	
	public void setLoseScore(Short loseScore) {
		this.loseScore = loseScore;
	}

	@Column(name = "team", columnDefinition = "TINYINT(4)", nullable=false)
	public Boolean getTeam() {
		return team;
	}
	
	public void setTeam(Boolean team) {
		this.team = team;
	}
	
	@Enumerated(EnumType.STRING)
	@Column(name = "game_code", length = 10, nullable = true)
	public GameTypeEnum getGameCode() {
		return gameCode;
	}

	public void setGameCode(GameTypeEnum gameCode) {
		this.gameCode = gameCode;
	}
	
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
		.append("id", getId())
		.append("enGameName", getEnGameName())
		.append("twGameName", getTwGameName())
		.append("gameIconSmall", getGameIconSmall())
		.append("gameIconLarge", getGameIconLarge())
		.append("gameUrl", getGameUrl())
		.append("team", getTeam())
		.append("teamADefaultIconSmall", getTeamADefaultIconSmall())
		.append("teamBDefaultIconSmall", getTeamBDefaultIconSmall())
		.append("teamADefaultIconLarge", getTeamADefaultIconLarge())
		.append("teamBDefaultIconLarge", getTeamBDefaultIconLarge())
		.append("winScore", getWinScore())
		.append("drawScore", getDrawScore())
		.append("loseScore", getLoseScore())
		.build();
	}
}
