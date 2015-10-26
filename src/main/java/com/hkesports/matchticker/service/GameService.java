package com.hkesports.matchticker.service;

import com.hkesports.matchticker.enums.GameTypeEnum;
import com.hkesports.matchticker.model.Game;

public interface GameService extends BasicService<Game> {

	public Game findByGameCode(GameTypeEnum type);
	
}
