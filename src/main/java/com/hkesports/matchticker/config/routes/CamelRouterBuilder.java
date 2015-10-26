package com.hkesports.matchticker.config.routes;

import javax.annotation.Resource;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import com.hkesports.matchticker.config.PropertiesConfig;

/**
 * @author manboyu
 *
 */
@Component("camelRouterBuilder")
public class CamelRouterBuilder extends RouteBuilder {
	
	@Resource(name = "propertiesConfig")
	private PropertiesConfig propertiesConfig;

	@Override
	public void configure() throws Exception {
		/* ======================================== LOL JOB START ======================================== */
		from("quartz2://LOL/batchLolItems?job.name=batchLolItems&cron=" + propertiesConfig.getProperty("job.batchLolItems.cron"))
			.routeId("batchLolItems")
			.beanRef("batchForLolService", "batchLolItems");
		
		from("quartz2://LOL/batchLolChampions?job.name=batchLolChampions&cron=" + propertiesConfig.getProperty("job.batchLolChampions.cron"))
			.routeId("batchLolChampions")
			.beanRef("batchForLolService", "batchLolChampions");
		
		from("quartz2://LOL/batchLolSpells?job.name=batchLolSpells&cron=" + propertiesConfig.getProperty("job.batchLolSpells.cron"))
			.routeId("batchLolSpells")
			.beanRef("batchForLolService", "batchLolSpells");
		
		from("quartz2://LOL/batchLolRunes?job.name=batchLolRunes&cron=" + propertiesConfig.getProperty("job.batchLolRunes.cron"))
			.routeId("batchLolRunes")
			.beanRef("batchForLolService", "batchLolRunes");
		
		from("quartz2://LOL/batchLolLeagues?job.name=batchLolLeagues&cron=" + propertiesConfig.getProperty("job.batchLolLeagues.cron"))
			.routeId("batchLolLeagues")
			.beanRef("batchForLolService", "batchLolLeagues");
		
		from("quartz2://LOL/batchLolTouraments?job.name=batchLolTouraments&cron=" + propertiesConfig.getProperty("job.batchLolTouraments.cron"))
			.routeId("batchLolTouraments")
			.beanRef("batchForLolService", "batchLolTouraments");
		
		from("quartz2://LOL/batchLolAllTouraments?job.name=batchLolAllTouraments&cron=" + propertiesConfig.getProperty("job.batchLolAllTouraments.cron"))
			.routeId("batchLolAllTouraments")
			.beanRef("batchForLolService", "batchLolAllTouraments");
		/* ======================================== LOL JOB END ======================================== */
		
		/* ======================================== DOTA2 JOB START ======================================== */
		from("quartz2://DOTA2/batchGetDota2Heros?job.name=batchGetDota2Heros&cron=" + propertiesConfig.getProperty("job.dota2.hero.cron"))
			.routeId("batchGetDota2Heros")
			.beanRef("batchForDota2Service", "batchGetDota2Heros");
		
		from("quartz2://DOTA2/batchGetDota2Items?job.name=batchGetDota2Items&cron=" + propertiesConfig.getProperty("job.dota2.item.cron"))
			.routeId("batchGetDota2Items")
			.beanRef("batchForDota2Service", "batchGetDota2Items");
		
		from("quartz2://DOTA2/batchGetDota2Abilities?job.name=batchGetDota2Abilities&cron=" + propertiesConfig.getProperty("job.dota2.ability.cron"))
			.routeId("batchGetDota2Abilities")
			.beanRef("batchForDota2Service", "batchGetDota2Abilities");
		
		from("quartz2://DOTA2/batchGetDota2MatchData?job.name=batchGetDota2MatchData&cron=" + propertiesConfig.getProperty("job.dota2.match.cron"))
			.routeId("batchGetDota2MatchData")
			.beanRef("batchForDota2Service", "batchGetDota2MatchData");
		
		from("quartz2://DOTA2/batchSaveScheduleLiveLeague?job.name=batchSaveScheduleLiveLeague&cron=" + propertiesConfig.getProperty("job.dota2.batchSaveScheduleLiveLeague.cron"))
			.routeId("batchSaveScheduleLiveLeague")
			.beanRef("batchForDota2Service", "batchSaveScheduleLiveLeague");
		
		from("quartz2://DOTA2/batchUpdateMatchScheduleByBatchjobTmp?job.name=batchUpdateMatchScheduleByBatchjobTmp&cron=" + propertiesConfig.getProperty("job.dota2.batchUpdateMatchScheduleByBatchjobTmp.cron"))
			.routeId("batchUpdateMatchScheduleByBatchjobTmp")
			.beanRef("batchForDota2Service", "batchUpdateMatchScheduleByBatchjobTmp");
		/* ======================================== DOTA2 JOB END ======================================== */
		
		/* ======================================== GAME SCORE JOB START ======================================== */
		from("quartz2://GAMESCORE/batchInsertTeamGameScore?job.name=batchInsertTeamGameScore&cron=" + propertiesConfig.getProperty("job.batchInsertTeamGameScore.cron"))
			.routeId("batchInsertTeamGameScore")
			.beanRef("batchForGameScoreService", "batchInsertTeamGameScore");
		
		from("quartz2://GAMESCORE/batchUpdateTeamGameScore?job.name=batchUpdateTeamGameScore&cron=" + propertiesConfig.getProperty("job.batchUpdateTeamGameScore.cron"))
			.routeId("batchUpdateTeamGameScore")
			.beanRef("batchForGameScoreService", "batchUpdateTeamGameScore");
		
		from("quartz2://GAMESCORE/batchInsertTeamGameMonthlyScore?job.name=batchInsertTeamGameMonthlyScore&cron=" + propertiesConfig.getProperty("job.batchInsertTeamGameMonthlyScore.cron"))
			.routeId("batchInsertTeamGameMonthlyScore")
			.beanRef("batchForGameScoreService", "batchInsertTeamGameMonthlyScore");
		
		from("quartz2://GAMESCORE/batchUpdateTeamGameMonthlyScore?job.name=batchUpdateTeamGameMonthlyScore&cron=" + propertiesConfig.getProperty("job.batchUpdateTeamGameMonthlyScore.cron"))
			.routeId("batchUpdateTeamGameMonthlyScore")
			.beanRef("batchForGameScoreService", "batchUpdateTeamGameMonthlyScore");
		
		from("quartz2://GAMESCORE/batchInsertTouramentGameTeamScore?job.name=batchInsertTouramentGameTeamScore&cron=" + propertiesConfig.getProperty("job.batchInsertTouramentGameTeamScore.cron"))
			.routeId("batchInsertTouramentGameTeamScore")
			.beanRef("batchForGameScoreService", "batchInsertTouramentGameTeamScore");
		
		from("quartz2://GAMESCORE/batchUpdateTouramentGameTeamScore?job.name=batchUpdateTouramentGameTeamScore&cron=" + propertiesConfig.getProperty("job.batchUpdateTouramentGameTeamScore.cron"))
			.routeId("batchUpdateTouramentGameTeamScore")
			.beanRef("batchForGameScoreService", "batchUpdateTouramentGameTeamScore");
		
		from("quartz2://GAMESCORE/batchInsertTouramentGameTeamMonthlyScore?job.name=batchInsertTouramentGameTeamMonthlyScore&cron=" + propertiesConfig.getProperty("job.batchInsertTouramentGameTeamMonthlyScore.cron"))
			.routeId("batchInsertTouramentGameTeamMonthlyScore")
			.beanRef("batchForGameScoreService", "batchInsertTouramentGameTeamMonthlyScore");
		
		from("quartz2://GAMESCORE/batchUpdateTouramentGameTeamMonthlyScore?job.name=batchUpdateTouramentGameTeamMonthlyScore&cron=" + propertiesConfig.getProperty("job.batchUpdateTouramentGameTeamMonthlyScore.cron"))
			.routeId("batchUpdateTouramentGameTeamMonthlyScore")
			.beanRef("batchForGameScoreService", "batchUpdateTouramentGameTeamMonthlyScore");
		
		from("quartz2://GAMESCORE/batchInsertTeamContinentGameScore?job.name=batchInsertTeamContinentGameScore&cron=" + propertiesConfig.getProperty("job.batchInsertTeamContinentGameScore.cron"))
			.routeId("batchInsertTeamContinentGameScore")
			.beanRef("batchForGameScoreService", "batchInsertTeamContinentGameScore");
		
		from("quartz2://GAMESCORE/batchUpdateTeamContinentGameScore?job.name=batchUpdateTeamContinentGameScore&cron=" + propertiesConfig.getProperty("job.batchUpdateTeamContinentGameScore.cron"))
			.routeId("batchUpdateTeamContinentGameScore")
			.beanRef("batchForGameScoreService", "batchUpdateTeamContinentGameScore");
		
		from("quartz2://GAMESCORE/batchInsertTeamContinentGameMonthlyScore?job.name=batchInsertTeamContinentGameMonthlyScore&cron=" + propertiesConfig.getProperty("job.batchInsertTeamContinentGameMonthlyScore.cron"))
			.routeId("batchInsertTeamContinentGameMonthlyScore")
			.beanRef("batchForGameScoreService", "batchInsertTeamContinentGameMonthlyScore");
		
		from("quartz2://GAMESCORE/batchUpdateTeamContinentGameMonthlyScore?job.name=batchUpdateTeamContinentGameMonthlyScore&cron=" + propertiesConfig.getProperty("job.batchUpdateTeamContinentGameMonthlyScore.cron"))
			.routeId("batchUpdateTeamContinentGameMonthlyScore")
			.beanRef("batchForGameScoreService", "batchUpdateTeamContinentGameMonthlyScore");
		/* ======================================== GAME SCORE JOB END ======================================== */
		
		/* ======================================== IMAGE JOB START ==================================== */
		from("quartz2://IMAGE/batchPlayerPhoto?job.name=batchPlayerPhoto&cron=" + propertiesConfig.getProperty("job.image.player.cron"))
			.routeId("batchPlayerPhoto")
			.beanRef("batchForImageService", "batchPlayerPhoto");
		
		from("quartz2://IMAGE/batchTeamLogo?job.name=batchTeamLogo&cron=" + propertiesConfig.getProperty("job.image.team.cron"))
			.routeId("batchTeamLogo")
			.beanRef("batchForImageService", "batchTeamLogo");
		
		from("quartz2://IMAGE/batchLeaguePhoto?job.name=batchLeaguePhoto&cron=" + propertiesConfig.getProperty("job.image.league.cron"))
			.routeId("batchLeaguePhoto")
			.beanRef("batchForImageService", "batchLeaguePhoto");
		/* ======================================== IMAGE JOB END ====================================== */
	}
}
