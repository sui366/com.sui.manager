package com.sui.manager.common.util.date;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

/**
 * 日期时间的工具类.
 */
public class TimeUtil extends DateUtils {
	/** 一天 */
	public static final long ONE_DAY = 1000 * 60 * 60 * 24;

	/**
	 * 获取当前时间.
	 * 
	 * @return 返回当前时间
	 */
	public static Date getCurretTime() {
		return new Date(System.currentTimeMillis());
	}

	/**
	 * 获取输入格式的当前时间文本格式
	 * 
	 * @param pattern时间格式
	 * @return
	 */
	public static String getCurrentTime(String pattern) {
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		return format.format(getCurretTime());
	}

	/**
	 * 获取当前是第几周
	 * 
	 * @return
	 */
	public static String getWeekTime() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-ww");
		return format.format(getCurretTime());
	}

	/**
	 * 获取时间
	 * 
	 * @param millis
	 * @return
	 */
	public static Date getTime(long millis) {
		if (millis < 0) {
			millis = System.currentTimeMillis();
		}
		return new Date(millis);
	}

	/**
	 * 获取时间
	 * 
	 * @return
	 */
	public static Timestamp getNowDateTime() {
		return new Timestamp(System.currentTimeMillis());
	}

	/**
	 * 格式化日期
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 * @author zengdq 2012-09-14
	 */
	public static String format(Date date) {
		return format(date, null);
	}

	/**
	 * 格式化日期
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 * @author zengdq 2012-09-14
	 */
	public static String format(Date date, String pattern) {
		if (StringUtils.isBlank(pattern)) {
			pattern = "yyyy-MM-dd HH:mm:ss";
		}
		if (date == null) {
			date = getCurretTime();
		}
		return new SimpleDateFormat(pattern).format(date);
	}

	/**
	 * 格式化日期
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 * @author zengdq 2012-09-14
	 */
	public static String format(long timeInMills, String pattern) {
		if (StringUtils.isBlank(pattern)) {
			pattern = "yyyy-MM-dd HH:mm:ss";
		}
		if (timeInMills < 0) {
			timeInMills = System.currentTimeMillis();
		}
		return new SimpleDateFormat(pattern).format(getTime(timeInMills));
	}

	/**
	 * 根据格式解析时间
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 * @throws ParseException
	 */
	public static Date parseDate(String date, String pattern) throws ParseException {
		return new SimpleDateFormat(pattern).parse(date);
	}

	/**
	 * 获取当前月加上或减去第n个月第一天
	 * 
	 * @return
	 */
	public static Date getFirstDay(Date date, int month) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		if (0 != month) {
			cal.add(Calendar.MONTH, month); // 月份的增加或减少
		}
		cal.set(Calendar.DAY_OF_MONTH, 1); // 第一天
		// 时分秒 设置为00：00:00
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		return cal.getTime();
	}

	/**
	 * 获取当前月加上或减去n个月
	 * 
	 * @return
	 */
	public static Date getAddMonth(Date date, int month) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		if (0 != month) {
			cal.add(Calendar.MONTH, month); // 月份的增加或减少
		}
		return cal.getTime();
	}

	/**
	 * 获取日期时间字符串，格式pattern
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String getDateString(Date date, String pattern) {
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		return format.format(date);
	}

	/**
	 * 日期时间加上一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date addOneDay(Date date) {
		if (date != null) {
			Calendar calendar = new GregorianCalendar();
			calendar.setTime(date);
			calendar.add(Calendar.DATE, 1);// 把日期往后增加一天.整数往后推,负数往前移动
			date = calendar.getTime(); // 这个时间就是日期往后推一天的结果
		}
		return date;
	}

}