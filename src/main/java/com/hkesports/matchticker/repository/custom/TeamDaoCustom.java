package com.hkesports.matchticker.repository.custom;

import java.util.List;

import com.hkesports.matchticker.model.batchJob.Team;

public interface TeamDaoCustom {
	
	List<Team> findAllByLogo();

}
