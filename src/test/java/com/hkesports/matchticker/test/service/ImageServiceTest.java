package com.hkesports.matchticker.test.service;

import javax.annotation.Resource;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.hkesports.matchticker.service.ImageService;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("development")
@ContextConfiguration(locations = {"/spring-config.xml" })
@WebAppConfiguration
public class ImageServiceTest {

	@Resource
	private ImageService imageService;
	
	@Test
	@Ignore
	public void test() throws Exception{
		imageService.batchPlayerPhoto();
		imageService.batchTeamLogo();
		imageService.batchLeaguePhoto();
	}
}
