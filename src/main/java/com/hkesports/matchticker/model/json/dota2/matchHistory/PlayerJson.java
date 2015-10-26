package com.hkesports.matchticker.model.json.dota2.matchHistory;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

public class PlayerJson implements Serializable {

	private static final long serialVersionUID = 1L;

	@SerializedName("account_id")
	private String accountId;
	
	@SerializedName("player_slot")
	private Integer playerSlot;
	
	@SerializedName("hero_id")
	private Integer heroId;

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public Integer getPlayerSlot() {
		return playerSlot;
	}

	public void setPlayerSlot(Integer playerSlot) {
		this.playerSlot = playerSlot;
	}

	public Integer getHeroId() {
		return heroId;
	}

	public void setHeroId(Integer heroId) {
		this.heroId = heroId;
	}
}
