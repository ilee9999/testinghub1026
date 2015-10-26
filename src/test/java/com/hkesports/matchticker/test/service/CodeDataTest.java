package com.hkesports.matchticker.test.service;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.hkesports.matchticker.model.Data;
import com.hkesports.matchticker.repository.DataDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("development")
@ContextConfiguration(locations = {"/spring-config.xml" })
@WebAppConfiguration
public class CodeDataTest {
	@Resource(name = "dataDao")
	private DataDao dataDao;

	@Test
	@Ignore
	public void testFindByCodeName() {
		System.out.println("in testFindByCodeName");
		
		List<Data> list = dataDao.findByCodeName("ImageSetting");
		if(list == null || list.size() < 1)
			System.out.println("is null");
		for(Data data : list)
			System.out.println(data.getDataName());
		
		list = dataDao.findByCodeName("ImageSetting", "Player");
		if(list == null || list.size() < 1)
			System.out.println("is null");
		for(Data data : list)
			System.out.println(data.getDataName());
		
		list = dataDao.findByCodeName("ImageSetting", "Player", "Thumbnail");
		if(list == null || list.size() < 1)
			System.out.println("is null");
		for(Data data : list)
			System.out.println(data.getDataName());
		
		System.out.println("end");
	}

	@Test
	@Ignore
	public void testFindByDataName(){
		System.out.println("in testFindByDataName");
		
		System.out.println(dataDao.findByDataName("ImageSetting", "filePath"));
		System.out.println(dataDao.findByDataName("ImageSetting", "Player", "Thumbnail"));
		System.out.println(dataDao.findByDataName("ImageSetting", "Player", "Thumbnail", "test"));
		
		System.out.println("end");
	}
}
