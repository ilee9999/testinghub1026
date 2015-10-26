package com.hkesports.matchticker.repository.custom;

import java.util.List;

import com.hkesports.matchticker.enums.GameTypeEnum;
import com.hkesports.matchticker.model.batchJob.TournamentBatchjobTmp;

public interface TournamentBatchjobTmpDaoCustom {

	/**
	 * 取得所有建立時間大於 (系統時間 - 傳入參數(小時))的資料
	 * @param gameType
	 * @param limitHour
	 * @return
	 */
	public List<TournamentBatchjobTmp> findAllByCreateDate(GameTypeEnum gameType, int limitHour);
	
}
