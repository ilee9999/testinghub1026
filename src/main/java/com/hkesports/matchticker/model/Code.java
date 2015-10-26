package com.hkesports.matchticker.model;

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
@Table(name = "code")
public class Code extends BasicModel {

	private static final long serialVersionUID = 1L;

	private String codeName;
	private Code parentCode;
	private boolean published = true;
	private String codeDesc;

	@Column(name = "code_name", length = 128, nullable = false)
	public String getCodeName() {
		return codeName;
	}

	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}

	@ManyToOne
	@JoinColumn(name = "parent_code_id")
	public Code getParentCode() {
		return parentCode;
	}

	public void setParentCode(Code parentCode) {
		this.parentCode = parentCode;
	}

	@Type(type="yes_no")
	@Column(name = "published", nullable = true)
	public boolean isPublished() {
		return published;
	}

	public void setPublished(boolean published) {
		this.published = published;
	}

	@Column(name = "code_desc", length = 255, nullable = true)
	public String getCodeDesc() {
		return codeDesc;
	}

	public void setCodeDesc(String codeDesc) {
		this.codeDesc = codeDesc;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
		.append("id", getId())
		.append("codeName", getCodeName())
		.append("published", isPublished())
		.append("codeDesc", getCodeDesc())
		.build();
	}
}
