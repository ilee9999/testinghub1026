package com.hkesports.matchticker.test.service.lol;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import com.hkesports.matchticker.common.utils.MessageUtils;
import com.hkesports.matchticker.service.MailService;
import com.hkesports.matchticker.service.lol.LoLHeroService;

public class LoLHeroServiceTest extends LoLAbstractTest {

	@Resource(name = "loLHeroService")
	private LoLHeroService loLHeroService;
	@Resource(name = "mailService")
	private MailService mailService;
	
	@Test
	@Ignore
	public void testSaveHero() throws Exception {
		Assert.assertNotNull(this.loLHeroService);
		this.loLHeroService.saveHero();
	}
	
	@Test
	@Ignore
	public void testSendMail(){
		mailService.sendMail(MessageUtils.getMailTemplate("job_exception.html"));
	}
	
	@Test
	@Ignore
	public void testClasspathResource() {
		System.out.println(MessageUtils.getMailTemplate("job_exception.html"));
	}
}
