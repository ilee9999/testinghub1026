package com.hkesports.matchticker.model.batchJob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;

import com.hkesports.matchticker.model.basic.BasicModel;
import com.hkesports.matchticker.model.json.dota2.matchDetails.PlayerJson;
import com.hkesports.matchticker.model.json.lol.legsUrl.ParticipantJson;
import com.hkesports.matchticker.model.json.lol.legsUrl.StatsJson;
import com.hkesports.matchticker.model.json.lol.legsUrl.TimelineJson;

@Entity
@Table(name = "schedule_game_player_detail_r")
public class ScheduleGamePlayerDetail extends BasicModel {
	
	private static final long serialVersionUID = 1L;

	private Player player;
	private ScheduleGame scheduleGame;
	private ScheduleGameDetail scheduleGameDetail;
	private Double kda;
	private Integer kills;
	private Integer deaths;
	private Integer assists;
	private Integer endLevel;
	private Integer minionsKilled;
	private Integer totalGold;
	private Integer largestKillingSpree;
	private Integer largestMultiKill;
	private Integer killingSprees;
	private Integer longestTimeSpentLiving;
	private Integer doubleKills;
	private Integer tripleKills;
	private Integer quadraKills;
	private Integer pentaKills;
	private Integer unrealKills;
	private Integer totalDamageDealt;
	private Integer magicDamageDealt;
	private Integer physicalDamageDealt;
	private Integer trueDamageDealt;
	private Integer largestCriticalStrike;
	private Integer totalDamageDealtToChampions;
	private Integer magicDamageDealtToChampions;
	private Integer physicalDamageDealtToChampions;
	private Integer trueDamageDealtToChampions;
	private Integer totalHeal;
	private Integer totalUnitsHealed;
	private Integer totalDamageTaken;
	private Integer magicalDamageTaken;
	private Integer physicalDamageTaken;
	private Integer trueDamageTaken;
	private Integer goldSpent;
	private Integer goldEarned;
	private Integer turretKills;
	private Integer inhibitorKills;
	private Integer totalMinionsKilled;
	private Integer neutralMinionsKilled;
	private Integer neutralMinionsKilledTeamJungle;
	private Integer neutralMinionsKilledEnemyJungle;
	private Integer totalTimeCrowdControlDealt;
	private Integer champLevel;
	private Integer visionWardsBoughtInGame;
	private Integer sightWardsBoughtInGame;
	private Integer wardsPlaced;
	private Integer wardsKilled;
	private Boolean firstBloodKill;
	private Boolean firstBloodAssist;
	private Boolean firstTowerKill;
	private Boolean firstTowerAssist;
	private Boolean firstInhibitorKill;
	private Boolean firstInhibitorAssist;
	private Integer combatPlayerScore;
	private Integer objectivePlayerScore;
	private Integer totalPlayerScore;
	private Integer totalScoreRank;
	private String role;
	private String lane;
	private Integer playerSlot;
	private Integer goldPerMin;
	private Integer xpPerMin;
	private Integer heroDamage;
	private Integer towerDamage;
	private Integer heroHealing;

	@OneToOne
	@JoinColumn(name="player_id", columnDefinition="BIGINT(20)", referencedColumnName="id", nullable = false)
	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	@ManyToOne
	@JoinColumn(name="schedule_game_id", columnDefinition="BIGINT(20)", nullable = false)
	public ScheduleGame getScheduleGame() {
		return scheduleGame;
	}

	public void setScheduleGame(ScheduleGame scheduleGame) {
		this.scheduleGame = scheduleGame;
	}
	
	@ManyToOne
	@JoinColumn(name="schedule_game_detail_id", columnDefinition="BIGINT(20)", nullable = true)
	public ScheduleGameDetail getScheduleGameDetail() {
		return scheduleGameDetail;
	}

	public void setScheduleGameDetail(ScheduleGameDetail scheduleGameDetail) {
		this.scheduleGameDetail = scheduleGameDetail;
	}

	@Column(name="kda", columnDefinition="DOUBLE", nullable=true)
	public Double getKda() {
		return kda;
	}

	public void setKda(Double kda) {
		this.kda = kda;
	}

	@Column(name="kills", columnDefinition="INT(11)", nullable=true)
	public Integer getKills() {
		return kills;
	}

	public void setKills(Integer kills) {
		this.kills = kills;
	}

	@Column(name="deaths", columnDefinition="INT(11)", nullable=true)
	public Integer getDeaths() {
		return deaths;
	}

	public void setDeaths(Integer deaths) {
		this.deaths = deaths;
	}

	@Column(name="assists", columnDefinition="INT(11)", nullable=true)
	public Integer getAssists() {
		return assists;
	}

	public void setAssists(Integer assists) {
		this.assists = assists;
	}

	@Column(name="end_level", columnDefinition="INT(11)", nullable=true)
	public Integer getEndLevel() {
		return endLevel;
	}

	public void setEndLevel(Integer endLevel) {
		this.endLevel = endLevel;
	}

	@Column(name="minions_killed", columnDefinition="INT(11)", nullable=true)
	public Integer getMinionsKilled() {
		return minionsKilled;
	}

	public void setMinionsKilled(Integer minionsKilled) {
		this.minionsKilled = minionsKilled;
	}

	@Column(name="total_gold", columnDefinition="INT(11)", nullable=true)
	public Integer getTotalGold() {
		return totalGold;
	}

	public void setTotalGold(Integer totalGold) {
		this.totalGold = totalGold;
	}

	@Column(name="largest_killing_spree", columnDefinition="INT(11)", nullable=true)
	public Integer getLargestKillingSpree() {
		return largestKillingSpree;
	}

	public void setLargestKillingSpree(Integer largestKillingSpree) {
		this.largestKillingSpree = largestKillingSpree;
	}

	@Column(name="largest_multi_kill", columnDefinition="INT(11)", nullable=true)
	public Integer getLargestMultiKill() {
		return largestMultiKill;
	}

	public void setLargestMultiKill(Integer largestMultiKill) {
		this.largestMultiKill = largestMultiKill;
	}

	@Column(name="killing_sprees", columnDefinition="INT(11)", nullable=true)
	public Integer getKillingSprees() {
		return killingSprees;
	}

	public void setKillingSprees(Integer killingSprees) {
		this.killingSprees = killingSprees;
	}

	@Column(name="longest_time_spent_living", columnDefinition="INT(11)", nullable=true)
	public Integer getLongestTimeSpentLiving() {
		return longestTimeSpentLiving;
	}

	public void setLongestTimeSpentLiving(Integer longestTimeSpentLiving) {
		this.longestTimeSpentLiving = longestTimeSpentLiving;
	}

	@Column(name="double_kills", columnDefinition="INT(11)", nullable=true)
	public Integer getDoubleKills() {
		return doubleKills;
	}

	public void setDoubleKills(Integer doubleKills) {
		this.doubleKills = doubleKills;
	}

	@Column(name="triple_kills", columnDefinition="INT(11)", nullable=true)
	public Integer getTripleKills() {
		return tripleKills;
	}

	public void setTripleKills(Integer tripleKills) {
		this.tripleKills = tripleKills;
	}

	@Column(name="quadra_kills", columnDefinition="INT(11)", nullable=true)
	public Integer getQuadraKills() {
		return quadraKills;
	}

	public void setQuadraKills(Integer quadraKills) {
		this.quadraKills = quadraKills;
	}

	@Column(name="penta_kills", columnDefinition="INT(11)", nullable=true)
	public Integer getPentaKills() {
		return pentaKills;
	}

	public void setPentaKills(Integer pentaKills) {
		this.pentaKills = pentaKills;
	}

	@Column(name="unreal_kills", columnDefinition="INT(11)", nullable=true)
	public Integer getUnrealKills() {
		return unrealKills;
	}

	public void setUnrealKills(Integer unrealKills) {
		this.unrealKills = unrealKills;
	}

	@Column(name="total_damage_dealt", columnDefinition="INT(11)", nullable=true)
	public Integer getTotalDamageDealt() {
		return totalDamageDealt;
	}

	public void setTotalDamageDealt(Integer totalDamageDealt) {
		this.totalDamageDealt = totalDamageDealt;
	}

	@Column(name="magic_damage_dealt", columnDefinition="INT(11)", nullable=true)
	public Integer getMagicDamageDealt() {
		return magicDamageDealt;
	}

	public void setMagicDamageDealt(Integer magicDamageDealt) {
		this.magicDamageDealt = magicDamageDealt;
	}

	@Column(name="physical_damage_dealt", columnDefinition="INT(11)", nullable=true)
	public Integer getPhysicalDamageDealt() {
		return physicalDamageDealt;
	}

	public void setPhysicalDamageDealt(Integer physicalDamageDealt) {
		this.physicalDamageDealt = physicalDamageDealt;
	}

	@Column(name="true_damage_dealt", columnDefinition="INT(11)", nullable=true)
	public Integer getTrueDamageDealt() {
		return trueDamageDealt;
	}

	public void setTrueDamageDealt(Integer trueDamageDealt) {
		this.trueDamageDealt = trueDamageDealt;
	}

	@Column(name="largest_critical_strike", columnDefinition="INT(11)", nullable=true)
	public Integer getLargestCriticalStrike() {
		return largestCriticalStrike;
	}

	public void setLargestCriticalStrike(Integer largestCriticalStrike) {
		this.largestCriticalStrike = largestCriticalStrike;
	}

	@Column(name="total_damage_dealt_to_champions", columnDefinition="INT(11)", nullable=true)
	public Integer getTotalDamageDealtToChampions() {
		return totalDamageDealtToChampions;
	}

	public void setTotalDamageDealtToChampions(
			Integer totalDamageDealtToChampions) {
		this.totalDamageDealtToChampions = totalDamageDealtToChampions;
	}

	@Column(name="magic_damage_dealt_to_champions", columnDefinition="INT(11)", nullable=true)
	public Integer getMagicDamageDealtToChampions() {
		return magicDamageDealtToChampions;
	}

	public void setMagicDamageDealtToChampions(
			Integer magicDamageDealtToChampions) {
		this.magicDamageDealtToChampions = magicDamageDealtToChampions;
	}

	@Column(name="physical_damage_dealt_to_champions", columnDefinition="INT(11)", nullable=true)
	public Integer getPhysicalDamageDealtToChampions() {
		return physicalDamageDealtToChampions;
	}

	public void setPhysicalDamageDealtToChampions(
			Integer physicalDamageDealtToChampions) {
		this.physicalDamageDealtToChampions = physicalDamageDealtToChampions;
	}

	@Column(name="true_damage_dealt_to_champions", columnDefinition="INT(11)", nullable=true)
	public Integer getTrueDamageDealtToChampions() {
		return trueDamageDealtToChampions;
	}

	public void setTrueDamageDealtToChampions(Integer trueDamageDealtToChampions) {
		this.trueDamageDealtToChampions = trueDamageDealtToChampions;
	}

	@Column(name="total_heal", columnDefinition="INT(11)", nullable=true)
	public Integer getTotalHeal() {
		return totalHeal;
	}

	public void setTotalHeal(Integer totalHeal) {
		this.totalHeal = totalHeal;
	}

	@Column(name="total_units_healed", columnDefinition="INT(11)", nullable=true)
	public Integer getTotalUnitsHealed() {
		return totalUnitsHealed;
	}

	public void setTotalUnitsHealed(Integer totalUnitsHealed) {
		this.totalUnitsHealed = totalUnitsHealed;
	}

	@Column(name="total_damage_taken", columnDefinition="INT(11)", nullable=true)
	public Integer getTotalDamageTaken() {
		return totalDamageTaken;
	}

	public void setTotalDamageTaken(Integer totalDamageTaken) {
		this.totalDamageTaken = totalDamageTaken;
	}

	@Column(name="magical_damage_taken", columnDefinition="INT(11)", nullable=true)
	public Integer getMagicalDamageTaken() {
		return magicalDamageTaken;
	}

	public void setMagicalDamageTaken(Integer magicalDamageTaken) {
		this.magicalDamageTaken = magicalDamageTaken;
	}

	@Column(name="physical_damage_taken", columnDefinition="INT(11)", nullable=true)
	public Integer getPhysicalDamageTaken() {
		return physicalDamageTaken;
	}

	public void setPhysicalDamageTaken(Integer physicalDamageTaken) {
		this.physicalDamageTaken = physicalDamageTaken;
	}

	@Column(name="true_damage_taken", columnDefinition="INT(11)", nullable=true)
	public Integer getTrueDamageTaken() {
		return trueDamageTaken;
	}

	public void setTrueDamageTaken(Integer trueDamageTaken) {
		this.trueDamageTaken = trueDamageTaken;
	}

	@Column(name="gold_spent", columnDefinition="INT(11)", nullable=true)
	public Integer getGoldSpent() {
		return goldSpent;
	}

	public void setGoldSpent(Integer goldSpent) {
		this.goldSpent = goldSpent;
	}

	@Column(name="turret_kills", columnDefinition="INT(11)", nullable=true)
	public Integer getTurretKills() {
		return turretKills;
	}

	public void setTurretKills(Integer turretKills) {
		this.turretKills = turretKills;
	}

	@Column(name="inhibitor_kills", columnDefinition="INT(11)", nullable=true)
	public Integer getInhibitorKills() {
		return inhibitorKills;
	}

	public void setInhibitorKills(Integer inhibitorKills) {
		this.inhibitorKills = inhibitorKills;
	}

	@Column(name="neutral_minions_killed", columnDefinition="INT(11)", nullable=true)
	public Integer getNeutralMinionsKilled() {
		return neutralMinionsKilled;
	}

	public void setNeutralMinionsKilled(Integer neutralMinionsKilled) {
		this.neutralMinionsKilled = neutralMinionsKilled;
	}

	@Column(name="neutral_minions_killed_team_jungle", columnDefinition="INT(11)", nullable=true)
	public Integer getNeutralMinionsKilledTeamJungle() {
		return neutralMinionsKilledTeamJungle;
	}

	public void setNeutralMinionsKilledTeamJungle(
			Integer neutralMinionsKilledTeamJungle) {
		this.neutralMinionsKilledTeamJungle = neutralMinionsKilledTeamJungle;
	}

	@Column(name="neutral_minions_killed_enemy_jungle", columnDefinition="INT(11)", nullable=true)
	public Integer getNeutralMinionsKilledEnemyJungle() {
		return neutralMinionsKilledEnemyJungle;
	}

	public void setNeutralMinionsKilledEnemyJungle(
			Integer neutralMinionsKilledEnemyJungle) {
		this.neutralMinionsKilledEnemyJungle = neutralMinionsKilledEnemyJungle;
	}

	@Column(name="total_time_crowd_control_dealt", columnDefinition="INT(11)", nullable=true)
	public Integer getTotalTimeCrowdControlDealt() {
		return totalTimeCrowdControlDealt;
	}

	public void setTotalTimeCrowdControlDealt(Integer totalTimeCrowdControlDealt) {
		this.totalTimeCrowdControlDealt = totalTimeCrowdControlDealt;
	}

	@Column(name="vision_wards_bought_in_game", columnDefinition="INT(11)", nullable=true)
	public Integer getVisionWardsBoughtInGame() {
		return visionWardsBoughtInGame;
	}

	public void setVisionWardsBoughtInGame(Integer visionWardsBoughtInGame) {
		this.visionWardsBoughtInGame = visionWardsBoughtInGame;
	}

	@Column(name="sight_wards_bought_in_game", columnDefinition="INT(11)", nullable=true)
	public Integer getSightWardsBoughtInGame() {
		return sightWardsBoughtInGame;
	}

	public void setSightWardsBoughtInGame(Integer sightWardsBoughtInGame) {
		this.sightWardsBoughtInGame = sightWardsBoughtInGame;
	}

	@Column(name="wards_placed", columnDefinition="INT(11)", nullable=true)
	public Integer getWardsPlaced() {
		return wardsPlaced;
	}

	public void setWardsPlaced(Integer wardsPlaced) {
		this.wardsPlaced = wardsPlaced;
	}

	@Column(name="wards_killed", columnDefinition="INT(11)", nullable=true)
	public Integer getWardsKilled() {
		return wardsKilled;
	}

	public void setWardsKilled(Integer wardsKilled) {
		this.wardsKilled = wardsKilled;
	}

	@Column(name="first_blood_kill", columnDefinition="TINYINT", nullable=true)
	public Boolean getFirstBloodKill() {
		return firstBloodKill;
	}

	public void setFirstBloodKill(Boolean firstBloodKill) {
		this.firstBloodKill = firstBloodKill;
	}

	@Column(name="first_blood_assist", columnDefinition="TINYINT", nullable=true)
	public Boolean getFirstBloodAssist() {
		return firstBloodAssist;
	}

	public void setFirstBloodAssist(Boolean firstBloodAssist) {
		this.firstBloodAssist = firstBloodAssist;
	}

	@Column(name="first_tower_kill", columnDefinition="TINYINT", nullable=true)
	public Boolean getFirstTowerKill() {
		return firstTowerKill;
	}

	public void setFirstTowerKill(Boolean firstTowerKill) {
		this.firstTowerKill = firstTowerKill;
	}

	@Column(name="first_tower_assist", columnDefinition="TINYINT", nullable=true)
	public Boolean getFirstTowerAssist() {
		return firstTowerAssist;
	}

	public void setFirstTowerAssist(Boolean firstTowerAssist) {
		this.firstTowerAssist = firstTowerAssist;
	}

	@Column(name="first_inhibitor_kill", columnDefinition="TINYINT", nullable=true)
	public Boolean getFirstInhibitorKill() {
		return firstInhibitorKill;
	}

	public void setFirstInhibitorKill(Boolean firstInhibitorKill) {
		this.firstInhibitorKill = firstInhibitorKill;
	}

	@Column(name="first_inhibitor_assist", columnDefinition="TINYINT", nullable=true)
	public Boolean getFirstInhibitorAssist() {
		return firstInhibitorAssist;
	}

	public void setFirstInhibitorAssist(Boolean firstInhibitorAssist) {
		this.firstInhibitorAssist = firstInhibitorAssist;
	}

	@Column(name="combat_player_score", columnDefinition="INT(11)", nullable=true)
	public Integer getCombatPlayerScore() {
		return combatPlayerScore;
	}

	public void setCombatPlayerScore(Integer combatPlayerScore) {
		this.combatPlayerScore = combatPlayerScore;
	}

	@Column(name="objective_player_score", columnDefinition="INT(11)", nullable=true)
	public Integer getObjectivePlayerScore() {
		return objectivePlayerScore;
	}

	public void setObjectivePlayerScore(Integer objectivePlayerScore) {
		this.objectivePlayerScore = objectivePlayerScore;
	}

	@Column(name="total_player_score", columnDefinition="INT(11)", nullable=true)
	public Integer getTotalPlayerScore() {
		return totalPlayerScore;
	}

	public void setTotalPlayerScore(Integer totalPlayerScore) {
		this.totalPlayerScore = totalPlayerScore;
	}

	@Column(name="total_score_rank", columnDefinition="INT(11)", nullable=true)
	public Integer getTotalScoreRank() {
		return totalScoreRank;
	}

	public void setTotalScoreRank(Integer totalScoreRank) {
		this.totalScoreRank = totalScoreRank;
	}

	@Column(name="role", nullable=true)
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Column(name="lane", nullable=true)
	public String getLane() {
		return lane;
	}

	public void setLane(String lane) {
		this.lane = lane;
	}

	@Column(name="player_slot", columnDefinition="INT(11)", nullable=true)
	public Integer getPlayerSlot() {
		return playerSlot;
	}

	public void setPlayerSlot(Integer playerSlot) {
		this.playerSlot = playerSlot;
	}

	@Column(name="gold_per_min", columnDefinition="INT(11)", nullable=true)
	public Integer getGoldPerMin() {
		return goldPerMin;
	}

	public void setGoldPerMin(Integer goldPerMin) {
		this.goldPerMin = goldPerMin;
	}

	@Column(name="xp_per_min", columnDefinition="INT(11)", nullable=true)
	public Integer getXpPerMin() {
		return xpPerMin;
	}

	public void setXpPerMin(Integer xpPerMin) {
		this.xpPerMin = xpPerMin;
	}

	@Column(name="hero_damage", columnDefinition="INT(11)", nullable=true)
	public Integer getHeroDamage() {
		return heroDamage;
	}

	public void setHeroDamage(Integer heroDamage) {
		this.heroDamage = heroDamage;
	}

	@Column(name="tower_damage", columnDefinition="INT(11)", nullable=true)
	public Integer getTowerDamage() {
		return towerDamage;
	}

	public void setTowerDamage(Integer towerDamage) {
		this.towerDamage = towerDamage;
	}

	@Column(name="hero_healing", columnDefinition="INT(11)", nullable=true)
	public Integer getHeroHealing() {
		return heroHealing;
	}

	public void setHeroHealing(Integer heroHealing) {
		this.heroHealing = heroHealing;
	}

	public boolean equals(Object obj, ScheduleGame scheduleGame, ScheduleGameDetail gameDetail, Player player) {
		if(!getScheduleGame().getApiId().equals(scheduleGame.getApiId())) {
			return false;
		}
		if(!getPlayer().getApiId().equals(player.getApiId())) {
			return false;
		}
		if(gameDetail!=null && getScheduleGameDetail()==null) {
			return false;
		}
		if(gameDetail!=null && 
				(!getScheduleGameDetail().getScheduleGame().getApiId().equals(gameDetail.getScheduleGame().getApiId()) ||
				!getScheduleGameDetail().getTeam().getApiId().equals(gameDetail.getTeam().getApiId()))) {
			return false;
		}
		if(obj==null) {
			return true;
		}
		if(obj instanceof PlayerJson) {
			PlayerJson json = (PlayerJson) obj;			
			EqualsBuilder eb = new EqualsBuilder();
			eb.append(this.endLevel, json.getLevel());
			eb.append(this.minionsKilled, json.getDenies());
			eb.append(this.totalGold, json.getGold());
			eb.append(this.goldSpent, json.getGoldSpent());
			eb.append(this.playerSlot, json.getPlayerSlot());
			eb.append(this.goldPerMin, json.getGoldPerMin());
			eb.append(this.xpPerMin, json.getXpPerMin());
			eb.append(this.heroDamage, json.getHeroDamage());
			eb.append(this.towerDamage, json.getTowerDamage());
			eb.append(this.heroHealing, json.getHeroHealing());
			return eb.isEquals();
		} else if(obj instanceof ParticipantJson) {
			ParticipantJson participantJson = (ParticipantJson)obj;
			EqualsBuilder eq = new EqualsBuilder();
			eq.append(getKda(), participantJson.getKda());
			StatsJson statsJson = participantJson.getStats();
			if(statsJson != null){
				eq.append(getKills(), statsJson.getKills());
				eq.append(getDeaths(), statsJson.getDeaths());
				eq.append(getAssists(), statsJson.getAssists());
				eq.append(getLargestKillingSpree(), statsJson.getLargestKillingSpree());
				eq.append(getLargestMultiKill(), statsJson.getLargestMultiKill());
				eq.append(getKillingSprees(), statsJson.getKillingSprees());
				eq.append(getLongestTimeSpentLiving(), statsJson.getLongestTimeSpentLiving());
				eq.append(getDoubleKills(), statsJson.getDoubleKills());
				eq.append(getTripleKills(), statsJson.getTripleKills());
				eq.append(getQuadraKills(), statsJson.getQuadraKills());
				eq.append(getPentaKills(), statsJson.getPentaKills());
				eq.append(getUnrealKills(), statsJson.getUnrealKills());
				eq.append(getTotalDamageDealt(), statsJson.getTotalDamageDealt());
				eq.append(getMagicDamageDealt(), statsJson.getMagicDamageDealt());
				eq.append(getPhysicalDamageDealt(), statsJson.getPhysicalDamageDealt());
				eq.append(getTrueDamageDealt(), statsJson.getTrueDamageDealt());
				eq.append(getLargestCriticalStrike(), statsJson.getLargestCriticalStrike());
				eq.append(getTotalDamageDealtToChampions(), statsJson.getTotalDamageDealtToChampions());
				eq.append(getMagicDamageDealtToChampions(), statsJson.getMagicDamageDealtToChampions());
				eq.append(getPhysicalDamageDealtToChampions(), statsJson.getPhysicalDamageDealtToChampions());
				eq.append(getTrueDamageDealtToChampions(), statsJson.getTrueDamageDealtToChampions());
				eq.append(getTotalHeal(), statsJson.getTotalHeal());
				eq.append(getTotalUnitsHealed(), statsJson.getTotalUnitsHealed());
				eq.append(getTotalDamageTaken(), statsJson.getTotalDamageTaken());
				eq.append(getMagicalDamageTaken(), statsJson.getMagicalDamageTaken());
				eq.append(getTrueDamageTaken(), statsJson.getTrueDamageTaken());
				eq.append(getGoldSpent(), statsJson.getGoldSpent());
				eq.append(getTurretKills(), statsJson.getTurretKills());
				eq.append(getInhibitorKills(), statsJson.getInhibitorKills());
				eq.append(getNeutralMinionsKilled(), statsJson.getNeutralMinionsKilled());
				eq.append(getNeutralMinionsKilledTeamJungle(), statsJson.getNeutralMinionsKilledTeamJungle());
				eq.append(getNeutralMinionsKilledEnemyJungle(), statsJson.getNeutralMinionsKilledEnemyJungle());
				eq.append(getTotalTimeCrowdControlDealt(), statsJson.getTotalTimeCrowdControlDealt());
				eq.append(getVisionWardsBoughtInGame(), statsJson.getVisionWardsBoughtInGame());
				eq.append(getSightWardsBoughtInGame(), statsJson.getSightWardsBoughtInGame());
				eq.append(getWardsPlaced(), statsJson.getWardsPlaced());
				eq.append(getWardsKilled(), statsJson.getWardsKilled());
				eq.append(getFirstBloodKill(), statsJson.getFirstBloodKill());
				eq.append(getFirstBloodAssist(), statsJson.getFirstBloodAssist());
				eq.append(getFirstTowerKill(), statsJson.getFirstTowerKill());
				eq.append(getFirstTowerAssist(), statsJson.getFirstTowerAssist());
				eq.append(getFirstInhibitorKill(), statsJson.getFirstInhibitorKill());
				eq.append(getFirstInhibitorAssist(), statsJson.getFirstInhibitorAssist());
				eq.append(getCombatPlayerScore(), statsJson.getCombatPlayerScore());
				eq.append(getObjectivePlayerScore(), statsJson.getObjectivePlayerScore());
				eq.append(getTotalPlayerScore(), statsJson.getTotalPlayerScore());
				eq.append(getTotalScoreRank(), statsJson.getTotalScoreRank());
				TimelineJson timelineJson = participantJson.getTimeline();
				if(timelineJson != null){
					eq.append(getRole(), timelineJson.getRole());
					eq.append(getLane(), timelineJson.getLane());
				}
			}
			return eq.isEquals();
		}
			
		return super.equals(obj);
	}

	@Column(name="gold_earned", columnDefinition="INT(11)", nullable=true)
	public Integer getGoldEarned() {
		return goldEarned;
	}

	public void setGoldEarned(Integer goldEarned) {
		this.goldEarned = goldEarned;
	}

	@Column(name="total_minions_killed", columnDefinition="INT(11)", nullable=true)
	public Integer getTotalMinionsKilled() {
		return totalMinionsKilled;
	}

	public void setTotalMinionsKilled(Integer totalMinionsKilled) {
		this.totalMinionsKilled = totalMinionsKilled;
	}

	@Column(name="champ_level", columnDefinition="INT(11)", nullable=true)
	public Integer getChampLevel() {
		return champLevel;
	}

	public void setChampLevel(Integer champLevel) {
		this.champLevel = champLevel;
	}
}
