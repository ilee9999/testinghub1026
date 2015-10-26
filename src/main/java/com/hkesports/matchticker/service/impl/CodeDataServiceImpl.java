package com.hkesports.matchticker.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hkesports.matchticker.model.Data;
import com.hkesports.matchticker.repository.DataDao;
import com.hkesports.matchticker.service.CodeDataService;

@Service("codeDataService")
public class CodeDataServiceImpl implements CodeDataService {
	@Resource(name = "dataDao")
	DataDao dataDao;

	@Override
	public List<Data> getCodeData(String code){
		return dataDao.findByCodeName(code);
	}
	
	@Override
	public List<Data> getCodeData(String code, String subCode){
		return dataDao.findByCodeName(code, subCode);
	}
	
	@Override
	public List<Data> getCodeData(String code, String subCode, String data){
		return dataDao.findByCodeName(code, subCode, data);
	}
	
	@Override
	public String getCodeDataValue(String code, String data){
		return dataDao.findByDataName(code, data);
	}
	
	@Override
	public String getCodeDataValue(String code, String subCode, String data){
		return dataDao.findByDataName(code, subCode, data);
	}
	
	@Override
	public String getCodeDataValue(String code, String subCode, String parentDataName, String data){
		return dataDao.findByDataName(code, subCode, parentDataName, data);
	}
}
