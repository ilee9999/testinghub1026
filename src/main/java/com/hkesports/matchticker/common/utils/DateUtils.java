package com.hkesports.matchticker.common.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 常用日期方法
 * 
 * @author manboyu
 * 
 */
public class DateUtils {

	private final static Log log = LogFactory.getLog(DateUtils.class);

	private static final String DEFAULT_FORMAT = "yyyy/MM/dd";
	private static ThreadLocal<DateFormat> THREADLOCAL = new ThreadLocal<DateFormat>();

	/**
	 * 初始DateFormat
	 * 
	 * @param format
	 * @return
	 */
	public static DateFormat getDateFormat() {
		DateFormat df = THREADLOCAL.get();
		if (df == null) {
			df = new SimpleDateFormat();
			THREADLOCAL.set(df);
		}
		return df;
	}

	/**
	 * 比較src是否在target之前
	 * 
	 * @param src
	 * @param target(String)
	 * @return
	 */
	public static boolean before(String src, String target) {
		return before(src, target, DEFAULT_FORMAT);
	}
	
	/**
	 * 比較src是否在target之前
	 * 
	 * @param src
	 * @param target(String)
	 * @param format
	 * @return
	 */
	public static boolean before(String src, String target, String format) {
		try {
			return before(src, parserStringToDate(target, format), format);
		} catch (Exception e) {
			log.error("DateUtils.before - " + src + " 日期parse失敗 : ", e);
		}
		return false;
	}

	/**
	 * 比較src是否在target之前
	 * 
	 * @param src
	 * @param target(Date)
	 * @return
	 * @throws ParseException
	 */
	public static boolean before(String src, Date target) throws ParseException {
		return before(src, target, DEFAULT_FORMAT);
	}
	
	/**
	 * 比較src是否在target之前
	 * 
	 * @param src
	 * @param target(Date)
	 * @param format
	 * @return
	 * @throws ParseException
	 */
	public static boolean before(String src, Date target, String format) throws ParseException {
		Date srcDate = parserStringToDate(src, format);
		Calendar cal1 = Calendar.getInstance();
		clearTime(cal1, target);
		Calendar cal2 = Calendar.getInstance();
		clearTime(cal2, srcDate);
		return srcDate.getTime() < target.getTime();
	}

	/**
	 * 兩日期相減所得結果天數
	 * 
	 * @param src
	 * @param target
	 * @return
	 */
	public static int getTwoDateSubtract(Date src, Date target) {
		Calendar calendar = Calendar.getInstance();
		clearTime(calendar, target);
		long targetTime = calendar.getTimeInMillis();
		clearTime(calendar, src);
		long srcTime = calendar.getTimeInMillis();
		return (int) ((srcTime - targetTime) / (1000 * 60 * 60 * 24));
	}

	private static void clearTime(Calendar cal, Date date) {
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
	}
	
	/**
	 * 字串轉日期
	 * 
	 * @param dateString
	 * @return
	 */
	public static Date parserStringToDate(String dateString) {
		try {
			return parserStringToDate(dateString, DEFAULT_FORMAT);
		} catch (Exception e) {
			log.error("[DateUtil.parserStringToDate] => 發生錯誤!", e);
			return null;
		}
	}

	/**
	 * 字串轉日期
	 * 
	 * @param dateString
	 * @param format
	 * @return
	 * @throws ParseException 
	 */
	public static Date parserStringToDate(String dateString, String format) throws ParseException {
		SimpleDateFormat sdf = (SimpleDateFormat)getDateFormat();
		sdf.applyPattern(format);
		return sdf.parse(dateString);
	}
	
	/**
	 * 日期轉字串
	 * 
	 * @param strDate
	 * @param format
	 * @return
	 */
	public static String parserDateToString(Date date) {
		if (date != null) {
			return parserDateToString(date, DEFAULT_FORMAT);
		} else {  
			return StringUtils.EMPTY;
		}
	}
	
	/**
	 * 日期轉字串
	 * 
	 * @param strDate
	 * @param format
	 * @return
	 */
	public static String parserDateToString(Date date, String format) {
		if (date != null) {
			SimpleDateFormat sdf = (SimpleDateFormat)getDateFormat();
			sdf.applyPattern(format);
			return sdf.format(date);
		} else {
			return StringUtils.EMPTY;
		}
	}
	
	/**
	 * 毫秒轉時分秒格式
	 * 
	 * @param duration
	 * @return
	 */
	public static String millisToShortDHMS(long duration) {
		long hours = TimeUnit.MILLISECONDS.toHours(duration) - TimeUnit.DAYS.toHours(TimeUnit.MILLISECONDS.toDays(duration));
		long minutes = TimeUnit.MILLISECONDS.toMinutes(duration) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(duration));
		long seconds = TimeUnit.MILLISECONDS.toSeconds(duration) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(duration));
		return String.format("%02d時:%02d分:%02d秒", hours, minutes, seconds);
	}
}
