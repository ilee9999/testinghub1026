package com.hkesports.matchticker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.hkesports.matchticker.model.batchJob.League;
import com.hkesports.matchticker.repository.factory.GenericRepository;

public interface LeagueDao extends GenericRepository<League, Long> {
	
	@Query(value="select t from League t where t.leagueImage is not null and t.leagueImage <> ''")
	public List<League> findAllByLeagueImage();
	
}
