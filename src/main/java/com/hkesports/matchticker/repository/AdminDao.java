package com.hkesports.matchticker.repository;

import com.hkesports.matchticker.model.Admin;
import com.hkesports.matchticker.repository.factory.GenericRepository;

public interface AdminDao extends GenericRepository<Admin, Long> {

	public Admin findByKey(String key);
	
}
