package com.hkesports.matchticker.service.lol;

import java.util.List;

import com.hkesports.matchticker.model.batchJob.RuneData;
import com.hkesports.matchticker.model.json.lol.rune.RuneListJson;

/**
 * @author manboyu
 *
 */
public interface LoLRuneDataService {
	
	public RuneListJson getRuneListJosn() throws Exception;
	
	public List<RuneData> findRuneDatas();
	
	public void saveRuneData() throws Exception;
}
