package com.hkesports.matchticker.common.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;

import com.hkesports.matchticker.vo.ApiIF;

/**
 * @author manboyu
 *
 */
public class AppUtils {

	
	/**
	 * 根據apiId將資料放入map中, 型態為該物件
	 * 
	 * @param apiId
	 * @param t
	 * @return
	 */
	public static <T extends ApiIF> Map<Long, T> putModelByApiId(List<T> list){
		Map<Long, T> map = new HashMap<>();
		for(T t : list) {
			MapUtils.safeAddToMap(map, t.getApiId(), t);
		}
		return map;
	}
	
	public static <T> T get(List<T> list, Object o) {
		if(list==null || list.isEmpty())
			return null;
		
		int index = list.indexOf(o);
		return index>-1?list.get(index):null;
	}
	
	public static Long bindTo32Bit(Long id) {
		return id - 76561197960265728L;
	}
	
	public static Long bindTo64Bit(Long id) {
		return id + 76561197960265728L;
	}
}
