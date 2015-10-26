package com.hkesports.matchticker.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "continent")
public class Continent implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Long id;
	private String enContinentName;
	private String twContinentName;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", columnDefinition = "BIGINT(20)", nullable = false)
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name = "en_continent_name", length = 64, nullable = false)
	public String getEnContinentName() {
		return enContinentName;
	}
	
	public void setEnContinentName(String enContinentName) {
		this.enContinentName = enContinentName;
	}
	
	@Column(name = "tw_continent_name", length = 64, nullable = false)
	public String getTwContinentName() {
		return twContinentName;
	}
	
	public void setTwContinentName(String twContinentName) {
		this.twContinentName = twContinentName;
	}

	
}
