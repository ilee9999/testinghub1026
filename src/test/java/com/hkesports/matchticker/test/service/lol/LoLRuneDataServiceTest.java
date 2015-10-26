package com.hkesports.matchticker.test.service.lol;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import com.hkesports.matchticker.service.lol.LoLRuneDataService;

/**
 * @author manboyu
 *
 */
public class LoLRuneDataServiceTest extends LoLAbstractTest {
	
	@Resource(name = "loLRuneDataService")
	private LoLRuneDataService loLRuneDataService;
	
	@Test
	@Ignore
	public void testSaveRuneData() throws Exception {
		Assert.assertNotNull(this.loLRuneDataService);
		this.loLRuneDataService.saveRuneData();
	}
}
