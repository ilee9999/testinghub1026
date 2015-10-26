package com.hkesports.matchticker.service.lol;

import java.util.List;

import com.hkesports.matchticker.model.batchJob.SpellData;
import com.hkesports.matchticker.model.json.lol.spell.SpellListJson;

/**
 * @author manboyu
 *
 */
public interface LoLSpellDataService {
	
	public SpellListJson getSpellListJson() throws Exception;
	
	public List<SpellData> findSpellDatas();

	public void saveSpellData() throws Exception;
}
