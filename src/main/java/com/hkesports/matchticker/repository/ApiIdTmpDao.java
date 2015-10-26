package com.hkesports.matchticker.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hkesports.matchticker.enums.GameTypeEnum;
import com.hkesports.matchticker.model.batchJob.ApiIdTmp;
import com.hkesports.matchticker.repository.factory.GenericRepository;

public interface ApiIdTmpDao extends GenericRepository<ApiIdTmp, Long> {

	@Modifying
	@Query("delete from ApiIdTmp where gameType=:gameType and dataType=:dataType and procId=:procId")
	public void deleteByGameTypeAndDataTypeAndProcId(@Param("gameType") GameTypeEnum gameType, @Param("dataType") String dataType, @Param("procId") String procId);
	
	@Modifying
	@Query("delete from ApiIdTmp a where a.gameType=:gameType and a.procId=:procId")
	public void deleteByGameTypeAndProcId(@Param("gameType") GameTypeEnum gameType, @Param("procId") String procId);
}
