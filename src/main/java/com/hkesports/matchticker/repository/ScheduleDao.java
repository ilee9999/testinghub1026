package com.hkesports.matchticker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.hkesports.matchticker.model.batchJob.Schedule;
import com.hkesports.matchticker.repository.custom.ScheduleDaoCustom;
import com.hkesports.matchticker.repository.factory.GenericRepository;
import com.hkesports.matchticker.vo.ScheduleIdRangeVo;

public interface ScheduleDao extends GenericRepository<Schedule, Long>, ScheduleDaoCustom {

	@Query(value = "select * from schedule where game_type = 'DOTA2' ", nativeQuery = true)
	public List<Schedule> getDota2Data();

	@Query(value = "select new com.hkesports.matchticker.vo.ScheduleIdRangeVo(min(id), max(id)) from Schedule")
	public ScheduleIdRangeVo findScheduleIdRange();
}
