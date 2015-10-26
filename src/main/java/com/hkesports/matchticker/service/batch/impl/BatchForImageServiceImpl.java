package com.hkesports.matchticker.service.batch.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hkesports.matchticker.service.ImageService;
import com.hkesports.matchticker.service.batch.BatchForImageService;

/**
 * Batch Job Service For IMAGE
 */
@Service("batchForImageService")
public class BatchForImageServiceImpl implements BatchForImageService {
	
	@Resource(name="imageService")
	private ImageService imageService;

	/**
	 * 玩家照片排程
	 */
	@Override
	public void batchPlayerPhoto() throws Exception {
		imageService.batchPlayerPhoto();
	}
	
	/**
	 * 團隊LOGO排程
	 */
	@Override
	public void batchTeamLogo() throws Exception {
		imageService.batchTeamLogo();
	}
	
	/**
	 * 聯賽照片排程
	 */
	@Override
	public void batchLeaguePhoto() throws Exception {
		imageService.batchLeaguePhoto();
	}
}
