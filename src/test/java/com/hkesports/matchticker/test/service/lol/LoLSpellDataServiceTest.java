package com.hkesports.matchticker.test.service.lol;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import com.hkesports.matchticker.service.lol.LoLSpellDataService;

public class LoLSpellDataServiceTest extends LoLAbstractTest {

	@Resource(name = "loLSpellDataService")
	private LoLSpellDataService loLSpellDataService;
	
	@Test
	@Ignore
	public void testSaveSpellData() throws Exception {
		Assert.assertNotNull(this.loLSpellDataService);
		this.loLSpellDataService.saveSpellData();
	}
}
