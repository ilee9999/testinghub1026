package com.hkesports.matchticker.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.hkesports.matchticker.common.utils.Const;
import com.hkesports.matchticker.model.Admin;
import com.hkesports.matchticker.model.batchJob.Schedule;
import com.hkesports.matchticker.repository.AdminDao;
import com.hkesports.matchticker.repository.ScheduleDao;
import com.hkesports.matchticker.repository.factory.GenericRepository;
import com.hkesports.matchticker.service.ScheduleService;
import com.hkesports.matchticker.vo.TeamScore;

@Transactional
@Service("scheduleService")
public class ScheduleServiceImpl extends BasicServiceImpl<Schedule> implements ScheduleService {
	
	@Resource(name = "scheduleDao")
	private ScheduleDao scheduleDao;

	@Resource(name = "adminDao")
	private AdminDao adminDao;
	
	@Override
	protected GenericRepository<Schedule, Long> getDao() {
		return scheduleDao;
	}

	@Override
	public void doSaveScheduleResults(Schedule schedule) {
		if(schedule==null || schedule.getId() == null) {
			return;
		}
		List<TeamScore> teamScores = scheduleDao.findTeamScoresBySchedule(schedule.getId());
		if(CollectionUtils.isEmpty(teamScores)) {
			return;
		}
		TeamScore teamAScore = null;
		TeamScore teamBScore = null;
		for(TeamScore teamScore:teamScores) {
			String teamFullName = teamScore.getTeamFullName();
			if(StringUtils.equalsIgnoreCase(teamFullName, schedule.getTeamAName())) {
				teamAScore = teamScore;
			} else if(StringUtils.equalsIgnoreCase(teamFullName, schedule.getTeamBName())) {
				teamBScore = teamScore;
			}
		}
		if(teamBScore==null) {
			teamBScore = teamScores.size()>0?teamScores.get(0):new TeamScore();
		}
		if(teamAScore==null) {
			teamAScore = teamScores.size()>1?teamScores.get(1):new TeamScore();
		}
		if(teamAScore.getScore()==0 && teamBScore.getScore()==0) {
			return;
		}
		String results = new StringBuilder().append(teamAScore.getScore()).append(":").append(teamBScore.getScore()).toString();
		if(!results.equals(schedule.getResults())) {
			schedule.setResults(results);
			scheduleDao.save(schedule);
		}
	}
	
	public void doSaveScheduleStatus() {
		Admin admin = adminDao.findByKey(Const.ADMIN_KEY_SCHEDULE_STATUS_INTERVAL_HOUR);
		int intervalHour = 1;
		if(admin!=null && StringUtils.isNotBlank(admin.getValue())) {
			try {
				intervalHour = Integer.valueOf(admin.getValue());
			} catch (Exception e) {
				logger.error("doSaveScheduleStatus exception: admin key:{}, value:{}, error:{}", Const.ADMIN_KEY_SCHEDULE_STATUS_INTERVAL_HOUR, admin.getValue(), e);
			}
		} else {
			logger.warn("admin key:{}, value is empty or not found", Const.ADMIN_KEY_SCHEDULE_STATUS_INTERVAL_HOUR);
		}
		int count = scheduleDao.doSaveScheduleStatus(intervalHour);
		logger.info("update schedule status count:{}", count);
	}
	
}
