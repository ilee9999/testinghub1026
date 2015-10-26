package com.hkesports.matchticker.repository.custom;

import java.util.List;

import com.hkesports.matchticker.model.batchJob.Player;

public interface PlayerDaoCustom {

	List<Player> findAllByPhoto();

}
