package com.hkesports.matchticker.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hkesports.matchticker.enums.GameTypeEnum;
import com.hkesports.matchticker.model.Game;
import com.hkesports.matchticker.repository.GameDao;
import com.hkesports.matchticker.repository.factory.GenericRepository;
import com.hkesports.matchticker.service.GameService;

@Transactional
@Service("gameService")
public class GameServiceImpl extends BasicServiceImpl<Game> implements GameService {

	@Resource(name = "gameDao")
	private GameDao gameDao;
	
	@Override
	protected GenericRepository<Game, Long> getDao() {
		return gameDao;
	}

	public Game findByGameCode(GameTypeEnum gameCode) {
		return gameDao.findByGameCode(gameCode);
	}
	
}
