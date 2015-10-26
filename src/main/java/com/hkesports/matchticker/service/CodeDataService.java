package com.hkesports.matchticker.service;

import java.util.List;

import com.hkesports.matchticker.model.Data;

public interface CodeDataService {

	List<Data> getCodeData(String code);

	List<Data> getCodeData(String code, String subCode);

	List<Data> getCodeData(String code, String subCode, String data);

	String getCodeDataValue(String code, String data);

	String getCodeDataValue(String code, String subCode, String data);

	String getCodeDataValue(String code, String subCode, String parentDataName, String data);
}
