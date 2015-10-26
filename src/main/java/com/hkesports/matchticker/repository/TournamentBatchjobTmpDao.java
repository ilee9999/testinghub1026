package com.hkesports.matchticker.repository;

import com.hkesports.matchticker.model.batchJob.TournamentBatchjobTmp;
import com.hkesports.matchticker.repository.custom.TournamentBatchjobTmpDaoCustom;
import com.hkesports.matchticker.repository.factory.GenericRepository;

public interface TournamentBatchjobTmpDao extends GenericRepository<TournamentBatchjobTmp, Long>, TournamentBatchjobTmpDaoCustom {
	
}
