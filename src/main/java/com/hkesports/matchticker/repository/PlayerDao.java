package com.hkesports.matchticker.repository;

import com.hkesports.matchticker.model.batchJob.Player;
import com.hkesports.matchticker.repository.custom.PlayerDaoCustom;
import com.hkesports.matchticker.repository.factory.GenericRepository;

public interface PlayerDao extends PlayerDaoCustom, GenericRepository<Player, Long> {

}
