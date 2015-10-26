package com.hkesports.matchticker.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Entity
@Table(name = "admin")
public class Admin implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Long id;
	private String key;
	private String value;
	
	public Admin(){}
	
	public Admin(Long id, String key, String value) {
		super();
		this.id = id;
		this.key = key;
		this.value = value;
	}

	@Id
	@Column(name = "id", columnDefinition = "BIGINT(20)", nullable = false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "key", length = 128, nullable = false)
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	@Column(name = "value", length = 255, nullable = false)
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
		.append("id", getId())
		.append("key", getKey())
		.append("value", getValue())
		.build();
	}
	
}
