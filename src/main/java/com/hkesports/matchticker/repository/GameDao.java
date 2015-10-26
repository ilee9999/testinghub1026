package com.hkesports.matchticker.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hkesports.matchticker.enums.GameTypeEnum;
import com.hkesports.matchticker.model.Game;
import com.hkesports.matchticker.repository.factory.GenericRepository;

public interface GameDao extends GenericRepository<Game, Long> {
	
	@Query("select g from Game g where g.gameCode=:gameCode")
	public Game findByGameCode(@Param("gameCode") GameTypeEnum gameCode);
	
}
