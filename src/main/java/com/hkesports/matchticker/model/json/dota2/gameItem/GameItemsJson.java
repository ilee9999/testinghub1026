package com.hkesports.matchticker.model.json.dota2.gameItem;

import java.io.Serializable;

public class GameItemsJson implements Serializable {

	private static final long serialVersionUID = 1L;

	private GameItemsResultJson result;

	public GameItemsResultJson getResult() {
		return result;
	}

	public void setResult(GameItemsResultJson result) {
		this.result = result;
	}
}
