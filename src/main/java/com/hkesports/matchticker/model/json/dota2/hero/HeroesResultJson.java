package com.hkesports.matchticker.model.json.dota2.hero;

import java.io.Serializable;
import java.util.List;

public class HeroesResultJson implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer status;
	
	private Integer count;
	
	private List<HeroJson> heroes;

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public List<HeroJson> getHeroes() {
		return heroes;
	}

	public void setHeros(List<HeroJson> heroes) {
		this.heroes = heroes;
	}
}
