package com.hkesports.matchticker.test.service.lol;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import com.hkesports.matchticker.service.lol.LoLItemDataService;

/**
 * @author manboyu
 *
 */
public class LoLItemDataServiceTest extends LoLAbstractTest {

	@Resource(name = "loLItemDataService")
	private LoLItemDataService loLItemDataService;
	
	@Test
	@Ignore
	public void testSaveItemData() throws Exception {
		Assert.assertNotNull(this.loLItemDataService);
		this.loLItemDataService.saveItemData();
	}
}
