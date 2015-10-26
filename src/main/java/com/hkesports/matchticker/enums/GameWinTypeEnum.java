package com.hkesports.matchticker.enums;

import org.apache.commons.lang3.StringUtils;

public enum GameWinTypeEnum {
	Win, 
	Fail, 
	Draw;
	
	public static GameWinTypeEnum transform(String value) {
		for (GameWinTypeEnum obj : GameWinTypeEnum.values()) {
			if (StringUtils.equalsIgnoreCase(obj.name(), value)) {
				return obj;
			}
		}
		return null;
	}
}
