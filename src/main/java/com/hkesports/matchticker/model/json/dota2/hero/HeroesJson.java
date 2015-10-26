package com.hkesports.matchticker.model.json.dota2.hero;

import java.io.Serializable;

public class HeroesJson implements Serializable {

	private static final long serialVersionUID = 1L;

	private HeroesResultJson result;

	public HeroesResultJson getResult() {
		return result;
	}

	public void setResult(HeroesResultJson result) {
		this.result = result;
	}
}
