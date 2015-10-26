package com.hkesports.matchticker.repository;

import java.util.List;

import com.hkesports.matchticker.enums.JobStatusEnum;
import com.hkesports.matchticker.model.batchJob.JobRecord;
import com.hkesports.matchticker.repository.factory.GenericRepository;

/**
 * @author manboyu
 *
 */
public interface JobRecordDao extends GenericRepository<JobRecord, Long> {

	public List<JobRecord> findByStatusAndName(JobStatusEnum status, String name);
}
