package com.hkesports.matchticker.service.dota2;

import java.util.List;

import com.hkesports.matchticker.model.json.dota2.gameItem.GameItemJson;

public interface Dota2ItemService {
	public void updateItemForApi() throws Exception;

	public List<GameItemJson> getApiItemList() throws Exception;
}
