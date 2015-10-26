package com.hkesports.matchticker.repository.custom;

import java.util.List;

import com.hkesports.matchticker.model.batchJob.Tourament;

public interface TouramentDaoCustom {

	@Deprecated
	List<Tourament> findAllByLeagueImage();

}
