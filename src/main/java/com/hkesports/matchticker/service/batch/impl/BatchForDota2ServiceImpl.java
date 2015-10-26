package com.hkesports.matchticker.service.batch.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hkesports.matchticker.service.batch.BatchForDota2Service;
import com.hkesports.matchticker.service.dota2.Dota2AbilityService;
import com.hkesports.matchticker.service.dota2.Dota2HeroService;
import com.hkesports.matchticker.service.dota2.Dota2ItemService;
import com.hkesports.matchticker.service.dota2.Dota2LeagueTournamentService;
import com.hkesports.matchticker.service.dota2.Dota2MatchService;

@Service("batchForDota2Service")
public class BatchForDota2ServiceImpl implements BatchForDota2Service {
	
	@Resource(name = "dota2HeroService")
	private Dota2HeroService dota2HeroService;
	
	@Resource(name = "dota2ItemService")
	private Dota2ItemService dota2ItemService;
	
	@Resource(name = "dota2AbilityService")
	private Dota2AbilityService dota2AbilityService;
	
	@Resource(name = "dota2LeagueTournamentService")
	private Dota2LeagueTournamentService dota2LeagueTournamentService;
	
	@Resource(name = "dota2MatchService")
	private Dota2MatchService dota2MatchService;

	@Override
	public void batchGetDota2Heros() throws Exception {
		dota2HeroService.updateHerosForApi();
	}

	@Override
	public void batchGetDota2Items() throws Exception {
		dota2ItemService.updateItemForApi();
	}

	@Override
	public void batchGetDota2Abilities() throws Exception {
		dota2AbilityService.updateAbilityForApi();
	}

	@Override
	public void batchGetDota2MatchData() throws Exception {
		dota2MatchService.updateMatchScheduleForApi();
	}

	@Override
	public void batchSaveScheduleLiveLeague() throws Exception {
		dota2LeagueTournamentService.saveScheduleLiveLeague();
	}

	@Override
	public void batchUpdateMatchScheduleByBatchjobTmp() throws Exception {
		dota2MatchService.updateMatchScheduleByBatchjobTmp();
	}
}
