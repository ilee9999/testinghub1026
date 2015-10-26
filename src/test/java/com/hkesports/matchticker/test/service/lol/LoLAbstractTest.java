package com.hkesports.matchticker.test.service.lol;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.hkesports.matchticker.common.utils.JsoupUtils;
import com.hkesports.matchticker.config.PropertiesConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("development")
@ContextConfiguration(locations = {"/spring-config.xml" })
@WebAppConfiguration
public abstract class LoLAbstractTest {
	
	protected final static Log logger = LogFactory.getLog(LoLAbstractTest.class);

	@Resource(name = "propertiesConfig")
	PropertiesConfig propertiesConfig;
	@Resource(name = "jsoupUtils")
	JsoupUtils jsoupUtils;
	
	String leaguesUrl;
	String itemUrl;
	String runeUrl;
	String championUrl;
	String spellUrl;
	
	@Before
	public void setUp(){
		leaguesUrl = this.propertiesConfig.getProperty("lol.leagues.url");
		itemUrl = this.propertiesConfig.getProperty("lol.item.url");
		runeUrl = this.propertiesConfig.getProperty("lol.rune.url");
		championUrl = this.propertiesConfig.getProperty("lol.champion.url");
		spellUrl = this.propertiesConfig.getProperty("lol.summonerSpell.url");
	}
}
