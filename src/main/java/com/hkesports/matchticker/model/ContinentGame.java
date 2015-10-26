package com.hkesports.matchticker.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "continent_game")
public class ContinentGame implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Long gameId;
	private Long continentId;
	private String country;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", columnDefinition = "BIGINT(20)", nullable = false)
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name = "game_id")
	public Long getGameId() {
		return gameId;
	}
	
	public void setGameId(Long gameId) {
		this.gameId = gameId;
	}
	
	@Column(name = "continent_id")
	public Long getContinentId() {
		return continentId;
	}
	
	public void setContinentId(Long continentId) {
		this.continentId = continentId;
	}
	
	@Column(name = "country", length = 2, nullable = false)
	public String getCountry() {
		return country;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}

}
