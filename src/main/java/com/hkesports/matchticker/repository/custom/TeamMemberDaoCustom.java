package com.hkesports.matchticker.repository.custom;

import java.util.List;

import com.hkesports.matchticker.enums.GameTypeEnum;
import com.hkesports.matchticker.model.batchJob.TeamMember;

public interface TeamMemberDaoCustom {

	@Deprecated
	List<TeamMember> findFromTmpApiId(GameTypeEnum gameType, String dataType, String procId);

}
