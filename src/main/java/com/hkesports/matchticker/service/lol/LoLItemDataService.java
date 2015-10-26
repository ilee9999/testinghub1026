package com.hkesports.matchticker.service.lol;

import java.util.List;

import com.hkesports.matchticker.model.batchJob.ItemData;
import com.hkesports.matchticker.model.json.lol.item.ItemListJson;

/**
 * @author manboyu
 *
 */
public interface LoLItemDataService {
	
	public ItemListJson getItemListJson() throws Exception;
	
	public List<ItemData> findItemDatas();

	public void saveItemData() throws Exception;
}
