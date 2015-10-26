package com.hkesports.matchticker.service.dota2;

import java.util.List;

import com.hkesports.matchticker.model.json.dota2.ability.Ability;

public interface Dota2AbilityService {
	public void updateAbilityForApi() throws Exception;

	List<Ability> getAbilityListForApi() throws Exception;
}
