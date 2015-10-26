package com.hkesports.matchticker.repository;

import com.hkesports.matchticker.model.batchJob.Tourament;
import com.hkesports.matchticker.repository.custom.TouramentDaoCustom;
import com.hkesports.matchticker.repository.factory.GenericRepository;

/**
 * @author manboyu
 *
 */
public interface TouramentDao extends TouramentDaoCustom, GenericRepository<Tourament, Long> {
	
}
