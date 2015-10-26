package com.hkesports.matchticker.model.json.dota2.gameItem;

import java.io.Serializable;
import java.util.List;

public class GameItemsResultJson implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer status;
	
	private List<GameItemJson> items;

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public List<GameItemJson> getItems() {
		return items;
	}

	public void setItems(List<GameItemJson> items) {
		this.items = items;
	}
}
