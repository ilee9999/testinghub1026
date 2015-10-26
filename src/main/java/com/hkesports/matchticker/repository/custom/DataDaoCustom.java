package com.hkesports.matchticker.repository.custom;

import java.util.List;

import com.hkesports.matchticker.model.Data;

public interface DataDaoCustom {

	List<Data> findByCodeName(String codeName);

	List<Data> findByCodeName(String codeName, String subCodeName);

	List<Data> findByCodeName(String codeName, String subCodeName, String parentDataName);

	String findByDataName(String codeName, String dataName);
	
	String findByDataName(String codeName, String subCodeName, String dataName);

	String findByDataName(String codeName, String subCodeName, String parentDataName, String dataName);
}
