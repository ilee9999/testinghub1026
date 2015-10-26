package com.hkesports.matchticker.test.service.dota2;

import javax.annotation.Resource;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.hkesports.matchticker.common.utils.JsoupUtils;
import com.hkesports.matchticker.config.PropertiesConfig;
import com.hkesports.matchticker.repository.ApiIdTmpDao;
import com.hkesports.matchticker.repository.DataDao;
import com.hkesports.matchticker.repository.GameDao;
import com.hkesports.matchticker.repository.ScheduleDao;
import com.hkesports.matchticker.repository.ScheduleGameDetailDao;
import com.hkesports.matchticker.service.ApiIdTmpService;
import com.hkesports.matchticker.service.dota2.Dota2AbilityService;
import com.hkesports.matchticker.service.dota2.Dota2HeroService;
import com.hkesports.matchticker.service.dota2.Dota2ItemService;
import com.hkesports.matchticker.service.dota2.Dota2JsonFileService;
import com.hkesports.matchticker.service.dota2.Dota2LeagueTournamentService;
import com.hkesports.matchticker.service.dota2.Dota2MatchService;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("development")
@ContextConfiguration(locations = {"/spring-config.xml" })
@WebAppConfiguration
public class Dota2Test {

	@Resource(name = "propertiesConfig")
	PropertiesConfig propertiesConfig;
	@Resource(name = "jsoupUtils")
	JsoupUtils jsoupUtils;
	
	@Resource(name = "dota2HeroService")
	Dota2HeroService dota2HeroService;
	@Resource(name = "dota2AbilityService")
	Dota2AbilityService dota2AbilityService;
	@Resource(name = "dota2ItemService")
	Dota2ItemService dota2ItemService;
	@Resource(name = "dota2LeagueTournamentService")
	Dota2LeagueTournamentService dota2LeagueTournamentService;
	@Resource(name = "dota2MatchService")
	Dota2MatchService dota2MatchService;
	@Resource(name = "apiIdTmpDao")
	ApiIdTmpDao apiIdTmpDao;
	@Resource(name = "gameDao")
	private GameDao gameDao;
	@Resource(name = "scheduleDao")
	private ScheduleDao scheduleDao;
	@Resource(name = "scheduleGameDetailDao")
	private ScheduleGameDetailDao scheduleGameDetailDao;
	@Resource(name = "apiIdTmpService")
	private ApiIdTmpService apiIdTmpService;
	@Resource(name = "dota2JsonFileService")
	private Dota2JsonFileService dota2JsonFileService;
	@Resource
	DataDao dataDao;
	
	@Test
	@Ignore
	public void testMothed() throws Exception{
		long t1 = System.currentTimeMillis();
		dota2HeroService.updateHerosForApi();
		dota2AbilityService.updateAbilityForApi();
		dota2ItemService.updateItemForApi();
		dota2LeagueTournamentService.saveScheduleLiveLeague();
		dota2MatchService.updateMatchScheduleByBatchjobTmp();
//		dota2MatchService.updateMatchScheduleForApi();

		dota2MatchService.updateMatchScheduleForApi("3798");
//		dota2MatchService.updateMatchScheduleForApi("1640");
//		dota2MatchService.updateMatchScheduleForApi("65007");
//		apiIdTmpDao.deleteByGameTypeAndDataType(GameTypeEnum.DOTA2, "a");
//		List<Long> matchIdList = new ArrayList<>();
//		matchIdList.add(100L);
//		matchIdList.add(200L);
//		apiIdTmpService.findByTmpDataTableAndJoinSubData(scheduleGameDetailDao, matchIdList, GameTypeEnum.DOTA2, "match");
//		List<Schedule> schedule = scheduleDao.findAll();
//		System.out.println(100915719+76561197960265728L);
//		System.out.println(new Date(Long.valueOf(1399509803+"000")));
//		scheduleGameDetailDao.findDataFromTmpApiId(GameTypeEnum.DOTA2, "match");
		
//		dota2JsonFileService.doFetchAll();
//		dota2JsonFileService.fetchAllTeamFromTeamApi();
		long t2 = System.currentTimeMillis();
		System.out.println("共耗費:" + (t2-t1)/1000.0 + "秒");
	}
	
	@Test
	@Ignore
	public void testMothed2() throws Exception{
//		dataDao.findByDataName("RADIANT");
	}
}
