package com.hkesports.matchticker.model.json.lol.legsUrl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.hkesports.matchticker.enums.GameItemTypeEnum;
import com.hkesports.matchticker.vo.LoLItem;

public class ParticipantJson implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long playerApiId;
	private Double kda;
	private Integer participantId;
	private Integer teamId;
	private Integer championId;
	private Integer spell1Id;
	private Integer spell2Id;
	private String highestAchievedSeasonTier;
	private StatsJson stats;
	private TimelineJson timeline;

	public Long getPlayerApiId() {
		return playerApiId;
	}

	public void setPlayerApiId(Long playerApiId) {
		this.playerApiId = playerApiId;
	}

	public Double getKda() {
		return kda;
	}

	public void setKda(Double kda) {
		this.kda = kda;
	}
	
	public Integer getParticipantId() {
		return participantId;
	}

	public void setParticipantId(Integer participantId) {
		this.participantId = participantId;
	}

	public Integer getTeamId() {
		return teamId;
	}

	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}

	public Integer getChampionId() {
		return championId;
	}

	public void setChampionId(Integer championId) {
		this.championId = championId;
	}

	public Integer getSpell1Id() {
		return spell1Id;
	}

	public void setSpell1Id(Integer spell1Id) {
		this.spell1Id = spell1Id;
	}

	public Integer getSpell2Id() {
		return spell2Id;
	}

	public void setSpell2Id(Integer spell2Id) {
		this.spell2Id = spell2Id;
	}

	public String getHighestAchievedSeasonTier() {
		return highestAchievedSeasonTier;
	}

	public void setHighestAchievedSeasonTier(String highestAchievedSeasonTier) {
		this.highestAchievedSeasonTier = highestAchievedSeasonTier;
	}

	public StatsJson getStats() {
		return stats;
	}

	public void setStats(StatsJson stats) {
		this.stats = stats;
	}

	public TimelineJson getTimeline() {
		return timeline;
	}

	public void setTimeline(TimelineJson timeline) {
		this.timeline = timeline;
	}
	
	public List<LoLItem> getItemList(){
		List<LoLItem> list = new ArrayList<>(10);
		list.add(new LoLItem(GameItemTypeEnum.HERO, championId.longValue(), (short) 0));
		list.add(new LoLItem(GameItemTypeEnum.SPELL, spell1Id.longValue(), (short) 0));
		list.add(new LoLItem(GameItemTypeEnum.SPELL, spell2Id.longValue(), (short) 1));
		if(stats != null){
			list.add(new LoLItem(GameItemTypeEnum.EQUIP, stats.getItem0(), (short) 0));
			list.add(new LoLItem(GameItemTypeEnum.EQUIP, stats.getItem1(), (short) 1));
			list.add(new LoLItem(GameItemTypeEnum.EQUIP, stats.getItem2(), (short) 2));
			list.add(new LoLItem(GameItemTypeEnum.EQUIP, stats.getItem3(), (short) 3));
			list.add(new LoLItem(GameItemTypeEnum.EQUIP, stats.getItem4(), (short) 4));
			list.add(new LoLItem(GameItemTypeEnum.EQUIP, stats.getItem5(), (short) 5));
			list.add(new LoLItem(GameItemTypeEnum.EQUIP, stats.getItem6(), (short) 6));
		}
		return list;
	}
	
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
		.append("playerApiId", getPlayerApiId())
		.append("kda", getKda())
		.append("participantId", getParticipantId())
		.append("teamId", getTeamId())
		.append("championId", getChampionId())
		.append("spell1Id", getSpell1Id())
		.append("spell2Id", getSpell2Id())
		.append("highestAchievedSeasonTier", getHighestAchievedSeasonTier())
		.append("stats", getStats())
		.append("timeline", getTimeline())
		.build();
	}
}
