package com.hkesports.matchticker.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hkesports.matchticker.enums.GameTypeEnum;
import com.hkesports.matchticker.model.batchJob.Tourament;
import com.hkesports.matchticker.model.batchJob.TournamentBatchjobTmp;
import com.hkesports.matchticker.repository.TouramentDao;
import com.hkesports.matchticker.repository.TournamentBatchjobTmpDao;
import com.hkesports.matchticker.repository.factory.GenericRepository;
import com.hkesports.matchticker.service.TouramentService;

@Transactional
@Service("touramentService")
public class TouramentServiceImpl extends BasicServiceImpl<Tourament> implements TouramentService {
	
	@Resource(name = "touramentDao")
	private TouramentDao touramentDao;

	@Resource
	private TournamentBatchjobTmpDao tournamentBatchjobTmpDao;
	
	@Override
	protected GenericRepository<Tourament, Long> getDao() {
		return touramentDao;
	}

	public List<TournamentBatchjobTmp> findAllTmpByCreateDate(GameTypeEnum gameType, int limitHour) {
		return tournamentBatchjobTmpDao.findAllByCreateDate(gameType, limitHour);
	}
}
