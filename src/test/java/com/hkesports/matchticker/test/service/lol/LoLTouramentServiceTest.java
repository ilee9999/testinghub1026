/**
 * 
 */
package com.hkesports.matchticker.test.service.lol;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import com.hkesports.matchticker.repository.ScheduleDao;
import com.hkesports.matchticker.service.lol.LoLTouramentService;

/**
 * @author manboyu
 *
 */
public class LoLTouramentServiceTest extends LoLAbstractTest {

	@Resource(name = "scheduleDao")
	private ScheduleDao scheduleDao;
	
	@Resource(name = "loLTouramentService")
	private LoLTouramentService loLTouramentService;
	
	@Test
	@Ignore
	public void testSaveLeague() throws Exception {
		Assert.assertNotNull(this.loLTouramentService);
		this.loLTouramentService.saveLeague();
	}
	
	@Test
	@Ignore
	public void testSaveMathInfos() throws Exception {
		Assert.assertNotNull(this.loLTouramentService);
		this.loLTouramentService.saveMatchInfos(true);
	}
	
	@Test
	@Ignore
	public void testGetJsonObj() throws Exception {
		logger.info(this.loLTouramentService.getScheduleJsons("116"));
	}
}
