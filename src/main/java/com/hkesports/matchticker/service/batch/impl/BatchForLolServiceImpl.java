package com.hkesports.matchticker.service.batch.impl;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.hkesports.matchticker.service.batch.BatchForLolService;
import com.hkesports.matchticker.service.lol.LoLHeroService;
import com.hkesports.matchticker.service.lol.LoLItemDataService;
import com.hkesports.matchticker.service.lol.LoLRuneDataService;
import com.hkesports.matchticker.service.lol.LoLSpellDataService;
import com.hkesports.matchticker.service.lol.LoLTouramentService;

/**
 * Batch Job Service For LOL
 * 
 * @author manboyu
 *
 */
@Service("batchForLolService")
public class BatchForLolServiceImpl implements BatchForLolService {

	@Resource(name = "loLTouramentService")
	private LoLTouramentService loLTouramentService;
	@Resource(name = "loLItemDataService")
	private LoLItemDataService loLItemDataService;
	@Resource(name = "loLRuneDataService")
	private LoLRuneDataService loLRuneDataService;
	@Resource(name = "loLHeroService")
	private LoLHeroService loLHeroService;
	@Resource(name = "loLSpellDataService")
	private LoLSpellDataService loLSpellDataService;
	
	@PostConstruct
	private void init() throws Exception {
		if(CollectionUtils.isEmpty(this.loLTouramentService.findLeagues())) {
			this.loLTouramentService.saveLeague();
		}
	}
	
	@Override
	public void batchLolLeagues() throws Exception {
		this.loLTouramentService.saveLeague();
	}
	
	@Override
	public void batchLolTouraments() throws Exception {
		this.loLTouramentService.saveMatchInfos(false);
	}
	
	@Override
	public void batchLolAllTouraments() throws Exception {
		this.loLTouramentService.saveMatchInfos(true);
	}

	@Override
	public void batchLolChampions() throws Exception {
		this.loLHeroService.saveHero();
	}

	@Override
	public void batchLolItems() throws Exception {
		this.loLItemDataService.saveItemData();
	}

	@Override
	public void batchLolSpells() throws Exception {
		this.loLSpellDataService.saveSpellData();
	}

	@Override
	public void batchLolRunes() throws Exception {
		this.loLRuneDataService.saveRuneData();
	}
}
