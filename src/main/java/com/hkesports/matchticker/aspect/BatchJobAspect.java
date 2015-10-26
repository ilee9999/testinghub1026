package com.hkesports.matchticker.aspect;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.hkesports.matchticker.common.utils.DateUtils;
import com.hkesports.matchticker.enums.JobStatusEnum;
import com.hkesports.matchticker.model.batchJob.JobRecord;
import com.hkesports.matchticker.repository.JobRecordDao;
import com.hkesports.matchticker.service.batch.BatchForDota2Service;
import com.hkesports.matchticker.service.batch.BatchForLolService;

/**
 * @author manboyu
 *
 */
@Component
@Aspect
public class BatchJobAspect {
	
	private static final Log logger = LogFactory.getLog(BatchJobAspect.class);
	
	private static final String DATE_FORMAT = "yyyy/MM/dd HH:mm:ss";
	
	private static final String LOL_FETCH_ALL_TOURNAMENT = "batchLolAllTouraments";
	private static final String LOL_FETCH_TOURNAMENT = "batchLolTouraments";
	private static final String DOTA2_FETCH_ALL_TOURNAMENT = "batchGetDota2MatchData";
	private static final String DOTA2_ALL_LIVE_TOURNAMENT = "batchGetDota2LiveMatchData";
	private static final String DOTA2_ALL_SCHEDULE_TOURNAMENT = "batchGetDota2ScheduleMatchData";
	
	@Resource(name = "jobRecordDao")
	private JobRecordDao jobRecordDao;

	@Pointcut("execution(* com.hkesports.matchticker.service.batch.*.*(..))")
	public void batchJobPointcut() { }
	
	/**
	 * batch job record by spring aop
	 * 當job開始執行時, 先記錄一筆記錄, 狀態為PROCESSING
	 * 當結束時狀態為SUCCESS, 有exception則為FAILED
	 * 倘若LOL or DOTA2 正在進行每月一次的tournament的fullfectch時,
	 * 其他的tournament job狀態會為SUSPEND, 用以確保fullfectch結束時才執行
	 * 
	 * @param pjp
	 * @return
	 * @throws Throwable
	 */
	@Around("batchJobPointcut()")
	public Object batchJobAround(ProceedingJoinPoint pjp) throws Throwable {
		String methodName = pjp.getSignature().getName();
		List<JobRecord> jobRecords = null;
		if(pjp.getTarget() instanceof BatchForLolService && StringUtils.equals(methodName, LOL_FETCH_TOURNAMENT)) {
			jobRecords = jobRecordDao.findByStatusAndName(JobStatusEnum.PROCESSING, LOL_FETCH_ALL_TOURNAMENT);
		} else if(pjp.getTarget() instanceof BatchForDota2Service && 
				(StringUtils.equals(methodName, DOTA2_ALL_LIVE_TOURNAMENT) || StringUtils.equals(methodName, DOTA2_ALL_SCHEDULE_TOURNAMENT))) {
			jobRecords = jobRecordDao.findByStatusAndName(JobStatusEnum.PROCESSING, DOTA2_FETCH_ALL_TOURNAMENT);
		}
		
		Object result = null;
		StopWatch sw = new StopWatch();
		sw.start();
		long start = sw.getTime();
		
		JobRecord jobRecord = doSaveJobRecord(null, methodName);
		try {
			if(CollectionUtils.isEmpty(jobRecords)) {
				result = pjp.proceed();
				jobRecord.setStatus(JobStatusEnum.SUCCESS);
			} else {
				jobRecord.setStatus(JobStatusEnum.SUSPEND);
			}
		} catch (Exception e) {
			jobRecord.setExceptionMessage(e.getMessage());
			jobRecord.setStatus(JobStatusEnum.FAILED);
		} finally {
			sw.stop();
			jobRecord.setEndDate(DateUtils.parserDateToString(new Date(), DATE_FORMAT));
			long elapsedTime = sw.getTime() - start;
			String times = DateUtils.millisToShortDHMS(elapsedTime);
			logger.info(pjp.getSignature().getName() + " 執行時間 : " + times);
			jobRecord.setExecutionTime(times);
			jobRecord = doSaveJobRecord(jobRecord, methodName);
		}
		
		return result;
	}
	
	private JobRecord doSaveJobRecord(JobRecord jobRecord, String methodName) {
		if(jobRecord == null) {
			jobRecord = new JobRecord();
			jobRecord.setStatus(JobStatusEnum.PROCESSING);
			jobRecord.setStartDate(DateUtils.parserDateToString(new Date(), DATE_FORMAT));
			jobRecord.setName(methodName);
		}
		jobRecordDao.save(jobRecord);
		return jobRecord;
	}
}
