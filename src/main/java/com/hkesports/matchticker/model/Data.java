package com.hkesports.matchticker.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.Type;

import com.hkesports.matchticker.model.basic.BasicModel;

/**
 * @author manboyu
 *
 */
@Entity
@Table(name = "data")
public class Data extends BasicModel {

	private static final long serialVersionUID = 1L;

	private String dataName;
	private String dataValue;
	private Integer displayOrder;
	private boolean published = true;
	private String dataDesc;
	private Data parentData;
	private Code code;

	@Column(name = "data_name", length = 128, nullable = false)
	public String getDataName() {
		return dataName;
	}

	public void setDataName(String dataName) {
		this.dataName = dataName;
	}

	@Column(name = "data_value", length = 256, nullable = false)
	public String getDataValue() {
		return dataValue;
	}

	public void setDataValue(String dataValue) {
		this.dataValue = dataValue;
	}

	@Column(name = "display_order", columnDefinition="INT(11)", nullable = true)
	public Integer getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(Integer displayOrder) {
		this.displayOrder = displayOrder;
	}

	@Type(type="yes_no")
	@Column(name = "published", nullable = true)
	public boolean isPublished() {
		return published;
	}

	public void setPublished(boolean published) {
		this.published = published;
	}

	@Column(name = "data_desc", length = 255, nullable = true)
	public String getDataDesc() {
		return dataDesc;
	}

	public void setDataDesc(String dataDesc) {
		this.dataDesc = dataDesc;
	}

	@ManyToOne
	@JoinColumn(name = "parent_data_id")
	public Data getParentData() {
		return parentData;
	}

	public void setParentData(Data parentData) {
		this.parentData = parentData;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "code_id")
	public Code getCode() {
		return code;
	}

	public void setCode(Code code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
		.append("id", getId())
		.append("dataName", getDataName())
		.append("dataValue", getDataValue())
		.append("displayOrder", getDisplayOrder())
		.append("published", isPublished())
		.append("dataDesc", getDataDesc())
		.build();
	}
}
