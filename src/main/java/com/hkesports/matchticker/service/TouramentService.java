package com.hkesports.matchticker.service;

import java.util.List;

import com.hkesports.matchticker.enums.GameTypeEnum;
import com.hkesports.matchticker.model.batchJob.Tourament;
import com.hkesports.matchticker.model.batchJob.TournamentBatchjobTmp;

public interface TouramentService extends BasicService<Tourament>  {
	
	public List<TournamentBatchjobTmp> findAllTmpByCreateDate(GameTypeEnum gameType, int limitHour);

}
