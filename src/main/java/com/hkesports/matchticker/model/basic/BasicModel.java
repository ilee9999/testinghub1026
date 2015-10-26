package com.hkesports.matchticker.model.basic;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Model superclass 
 * 
 * @author manboyu
 *
 */
@MappedSuperclass
public class BasicModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private Date createDate;
	private Date updateDate;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", columnDefinition = "BIGINT(20)", nullable = false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_date", nullable = true)
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "update_date", nullable = true)
	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	
	@PrePersist
    public void prePersist() {
        this.setCreateDate(new Date());
    }
	
	@PreUpdate
    public void preUpdate() {
		this.setUpdateDate(new Date());
    }
}
