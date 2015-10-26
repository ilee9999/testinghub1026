package com.hkesports.matchticker.model.json.dota2.matchDetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;
import com.hkesports.matchticker.enums.GameItemTypeEnum;
import com.hkesports.matchticker.vo.Dota2Item;

public class PlayerJson implements Serializable {
	private static final long serialVersionUID = 1L;

	@SerializedName("account_id")
	private Long accountId;
	
	@SerializedName("hero_id")
	private Long heroId;

	@SerializedName("player_slot")
	private Integer playerSlot;

	@SerializedName("item_0")
	private Long item0;

	@SerializedName("item_1")
	private Long item1;

	@SerializedName("item_2")
	private Long item2;

	@SerializedName("item_3")
	private Long item3;

	@SerializedName("item_4")
	private Long item4;

	@SerializedName("item_5")
	private Long item5;

	private Integer kills;

	private Integer deaths;

	private Integer assists;

	@SerializedName("leaver_status")
	private String leaverStatus;

	private Integer gold;

	@SerializedName("last_hits")
	private Integer last_hits;

	private Integer denies;

	@SerializedName("gold_per_min")
	private Integer goldPerMin;

	@SerializedName("xp_per_min")
	private Integer xpPerMin;

	@SerializedName("gold_spent")
	private Integer goldSpent;

	@SerializedName("hero_damage")
	private Integer heroDamage;

	@SerializedName("tower_damage")
	private Integer towerDamage;

	@SerializedName("hero_healing")
	private Integer heroHealing;

	private Integer level;

	@SerializedName("ability_upgrades")
	List<AbilityUpgradesJson> abilityUpgrades;

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public Integer getPlayerSlot() {
		return playerSlot;
	}

	public void setPlayerSlot(Integer playerSlot) {
		this.playerSlot = playerSlot;
	}

	public Long getHeroId() {
		return heroId;
	}

	public void setHeroId(Long heroId) {
		this.heroId = heroId;
	}

	public Long getItem0() {
		return item0;
	}

	public void setItem0(Long item0) {
		this.item0 = item0;
	}

	public Long getItem1() {
		return item1;
	}

	public void setItem1(Long item1) {
		this.item1 = item1;
	}

	public Long getItem2() {
		return item2;
	}

	public void setItem2(Long item2) {
		this.item2 = item2;
	}

	public Long getItem3() {
		return item3;
	}

	public void setItem3(Long item3) {
		this.item3 = item3;
	}

	public Long getItem4() {
		return item4;
	}

	public void setItem4(Long item4) {
		this.item4 = item4;
	}

	public Long getItem5() {
		return item5;
	}

	public void setItem5(Long item5) {
		this.item5 = item5;
	}

	public Integer getKills() {
		return kills;
	}

	public void setKills(Integer kills) {
		this.kills = kills;
	}

	public Integer getDeaths() {
		return deaths;
	}

	public void setDeaths(Integer deaths) {
		this.deaths = deaths;
	}

	public Integer getAssists() {
		return assists;
	}

	public void setAssists(Integer assists) {
		this.assists = assists;
	}

	public String getLeaverStatus() {
		return leaverStatus;
	}

	public void setLeaverStatus(String leaverStatus) {
		this.leaverStatus = leaverStatus;
	}

	public Integer getGold() {
		return gold;
	}

	public void setGold(Integer gold) {
		this.gold = gold;
	}

	public Integer getLast_hits() {
		return last_hits;
	}

	public void setLast_hits(Integer last_hits) {
		this.last_hits = last_hits;
	}

	public Integer getDenies() {
		return denies;
	}

	public void setDenies(Integer denies) {
		this.denies = denies;
	}

	public Integer getGoldPerMin() {
		return goldPerMin;
	}

	public void setGoldPerMin(Integer goldPerMin) {
		this.goldPerMin = goldPerMin;
	}

	public Integer getXpPerMin() {
		return xpPerMin;
	}

	public void setXpPerMin(Integer xpPerMin) {
		this.xpPerMin = xpPerMin;
	}

	public Integer getGoldSpent() {
		return goldSpent;
	}

	public void setGoldSpent(Integer goldSpent) {
		this.goldSpent = goldSpent;
	}

	public Integer getHeroDamage() {
		return heroDamage;
	}

	public void setHeroDamage(Integer heroDamage) {
		this.heroDamage = heroDamage;
	}

	public Integer getTowerDamage() {
		return towerDamage;
	}

	public void setTowerDamage(Integer towerDamage) {
		this.towerDamage = towerDamage;
	}

	public Integer getHeroHealing() {
		return heroHealing;
	}

	public void setHeroHealing(Integer heroHealing) {
		this.heroHealing = heroHealing;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public List<AbilityUpgradesJson> getAbilityUpgrades() {
		return abilityUpgrades;
	}

	public void setAbilityUpgrades(List<AbilityUpgradesJson> abilityUpgrades) {
		this.abilityUpgrades = abilityUpgrades;
	}

	public boolean isRadiantTeam() {
		return playerSlot < 128 ? true : false;
	}

	public List<Dota2Item> getItemList(){
		List<Dota2Item> list = new ArrayList<>();
		
		list.add(getDota2Item(GameItemTypeEnum.HERO, heroId, (short) 0));
		list.add(getDota2Item(GameItemTypeEnum.EQUIP, item0, (short) 0));
		list.add(getDota2Item(GameItemTypeEnum.EQUIP, item1, (short) 1));
		list.add(getDota2Item(GameItemTypeEnum.EQUIP, item2, (short) 2));
		list.add(getDota2Item(GameItemTypeEnum.EQUIP, item3, (short) 3));
		list.add(getDota2Item(GameItemTypeEnum.EQUIP, item4, (short) 4));
		list.add(getDota2Item(GameItemTypeEnum.EQUIP, item5, (short) 5));

		return list;
	}
	
	private Dota2Item getDota2Item(GameItemTypeEnum type, Long id, short seq){
		Dota2Item item = new Dota2Item();
		item.setItemType(type);
		item.setItemId(id);
		item.setSequence(seq);
		return item;
	}
}
