package com.hkesports.matchticker.model.batchJob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.hkesports.matchticker.enums.JobStatusEnum;
import com.hkesports.matchticker.model.basic.BasicModel;

/**
 * @author manboyu
 *
 */
@Entity
@Table(name = "job_record")
public class JobRecord extends BasicModel {

	private static final long serialVersionUID = 1L;

	private String name;
	private String startDate;
	private String endDate;
	private String executionTime;
	private String exceptionMessage;
	private JobStatusEnum status;

	@Column(name = "name", length = 100, nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "start_date", length = 20, nullable = false)
	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	@Column(name = "end_date", length = 20, nullable = true)
	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	@Column(name = "execution_time", length = 20, nullable = true)
	public String getExecutionTime() {
		return executionTime;
	}

	public void setExecutionTime(String executionTime) {
		this.executionTime = executionTime;
	}

	@Column(name = "exception_message", columnDefinition = "TEXT", nullable = true)
	public String getExceptionMessage() {
		return exceptionMessage;
	}

	public void setExceptionMessage(String exceptionMessage) {
		this.exceptionMessage = exceptionMessage;
	}
	
	@Enumerated
	@Column(name = "status", columnDefinition = "TINYINT(4)", nullable = true)
	public JobStatusEnum getStatus() {
		return status;
	}

	public void setStatus(JobStatusEnum status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
		.append("id", getId())
		.append("name", getName())
		.append("startDate", getStartDate())
		.append("endDate", getEndDate())
		.append("executionTime", getExecutionTime())
		.append("exceptionMessage", getExceptionMessage())
		.append("status", getStatus())
		.build();
	}
}
