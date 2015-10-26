package com.hkesports.matchticker.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hkesports.matchticker.model.batchJob.Player;
import com.hkesports.matchticker.repository.PlayerDao;
import com.hkesports.matchticker.repository.factory.GenericRepository;
import com.hkesports.matchticker.service.PlayerService;

@Service("playerService")
public class PlayerServiceImpl extends BasicServiceImpl<Player> implements PlayerService {

	@Resource
	private PlayerDao playerDao;

	@Override
	protected GenericRepository<Player, Long> getDao() {
		return playerDao;
	}
	
	
}
