package com.hkesports.matchticker.model.batchJob;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.hkesports.matchticker.enums.GameTypeEnum;

@Deprecated
@Entity
@Table(name = "api_id_tmp")
public class ApiIdTmp implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Long apiId;
	private String dataType;
	private GameTypeEnum gameType;
	private String procId;
	
	public ApiIdTmp(){}
	
	public ApiIdTmp(Long apiId, String dataType, GameTypeEnum gameType, String procId) {
		super();
		this.apiId = apiId;
		this.dataType = dataType;
		this.gameType = gameType;
		this.procId = procId;
	}

	@Id
	@Column(name = "api_id", columnDefinition = "BIGINT(20)", nullable = false)
	public Long getApiId() {
		return apiId;
	}

	public void setApiId(Long apiId) {
		this.apiId = apiId;
	}

	@Column(name = "data_type", length = 45, nullable = false)
	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "game_type", length = 10, nullable = false)
	public GameTypeEnum getGameType() {
		return gameType;
	}

	public void setGameType(GameTypeEnum gameType) {
		this.gameType = gameType;
	}
	
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
		.append("apiId", getApiId())
		.append("dataType", getDataType())
		.append("gameType", getGameType())
		.build();
	}

	@Column(name = "proc_id", length = 36, nullable = false)
	public String getProcId() {
		return procId;
	}

	public void setProcId(String procId) {
		this.procId = procId;
	}
}
