package com.hkesports.matchticker.model.basic;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;

import com.hkesports.matchticker.enums.GameTypeEnum;
import com.hkesports.matchticker.vo.ApiIF;

/**
 * @author manboyu
 *
 */
@MappedSuperclass
public class BasicApiInfo extends BasicModel implements ApiIF {

	private static final long serialVersionUID = 1L;

	private Long apiId;
	private GameTypeEnum gameType;
	
	@Column(name = "api_id", columnDefinition = "BIGINT(20)", nullable = true)
	public Long getApiId() {
		return apiId;
	}

	public void setApiId(Long apiId) {
		this.apiId = apiId;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "game_type", length = 10, nullable = true)
	public GameTypeEnum getGameType() {
		return gameType;
	}

	public void setGameType(GameTypeEnum gameType) {
		this.gameType = gameType;
	}

}
