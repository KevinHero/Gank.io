package com.kevin.gank.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;



public class DateTime {
	private long lNow = System.currentTimeMillis();
	private Calendar cNow = Calendar.getInstance();
	private Date dNow = new Date(lNow);
	private Timestamp tNow = new Timestamp(lNow);
	private java.sql.Date today = new java.sql.Date(lNow);
	private java.sql.Time now = new java.sql.Time(lNow);


	/**
	 * 一天的MilliSecond数
	 */
	private static final long DAY_MILLI = 24 * 60 * 60 * 1000;

	/**
	 * 日期格式常量 Exp:1970-01-01
	 */
	public static final String DATE_FORMAT_LINE = "yyyy-MM-dd";

	/**
	 * 默认构造方法
	 */
	public DateTime() {

	}


	/**
	 * 得到最大天数
	 * @param time
	 * @return
	 */
	public static int getMaxNum(String time){
		return pars2Calender(time).getActualMaximum(Calendar.DAY_OF_MONTH);
	}



	/**
	 * 得到星期
	 * 如三，二
	 * @param c
	 * @return
	 */
	public static String getDay3(Calendar c) {
		if (c != null) {
			int i = c.get(Calendar.DAY_OF_WEEK);
			i = i-1;
			if(i==1){
				return "一";
			}else if(i==2){
				return "二";
			}else if(i==3){
				return "三";
			}else if(i==4){
				return "四";
			}else if(i==5){
				return "五";
			}else if(i==6){
				return "六";
			}else if(i==0){
				return "日";
			}else if(i==7){
				return "日";
			}
		}
		return "";
	}
	/**
	 * 得到星期
	 * 如周三，周二
	 * @param c
	 * @return
	 */
	public static String getDay2(Calendar c) {
		if (c != null) {
			int i = c.get(Calendar.DAY_OF_WEEK);
			i = i-1;
			if(i==1){
				return "周一";
			}else if(i==2){
				return "周二";
			}else if(i==3){
				return "周三";
			}else if(i==4){
				return "周四";
			}else if(i==5){
				return "周五";
			}else if(i==6){
				return "周六";
			}else if(i==0){
				return "周日";
			}else if(i==7){
				return "周日";
			}
		}
		return "";
	}

	/**
	 * 得到星期
	 * 如周三，周二
	 * @return
	 */
	public static String getDayOfWeek(String time) {
		Calendar c = pars2Calender(time);
		if (c != null) {
			int i = c.get(Calendar.DAY_OF_WEEK);
			i = i-1;
			if(i==1){
				return "周一";
			}else if(i==2){
				return "周二";
			}else if(i==3){
				return "周三";
			}else if(i==4){
				return "周四";
			}else if(i==5){
				return "周五";
			}else if(i==6){
				return "周六";
			}else if(i==0){
				return "周日";
			}else if(i==7){
				return "周日";
			}
		}
		return "";
	}



//	/**
//	 * 得到星期 
//	 * 如周三，周二
//	 * @param c
//	 * @return
//	 */
//	public static String getDay3(Context context,String timestamp) {
//		Calendar c = pars2Calender(timestamp);
//		if (c != null) {
//			int i = c.get(Calendar.DAY_OF_WEEK);
//			i = i-1;
//			if(i==1){
//				return context.getResources().getString(R.string.str_Mon );
//			}else if(i==2){
//				return context.getResources().getString(R.string.str_Tues );
//			}else if(i==3){
//				return context.getResources().getString(R.string.str_Wed );
//			}else if(i==4){
//				return context.getResources().getString(R.string.str_Thur );
//			}else if(i==5){
//				return context.getResources().getString(R.string.str_Fri );
//			}else if(i==6){
//				return context.getResources().getString(R.string.str_Sat );
//			}else if(i==0){
//				return context.getResources().getString(R.string.str_Sun );
//			}else if(i==7){
//				return context.getResources().getString(R.string.str_Sun );
//			}
//		} 
//		return "";
//	}

//	/**
//	 * 得到星期 
//	 * 如周三，周二
//	 * @param c
//	 * @return
//	 */
//	public static String getDay3(String timestamp) {
//		Calendar c = pars2Calender(timestamp);
//		if (c != null) {
//			int i = c.get(Calendar.DAY_OF_WEEK);
//			i = i-1;
//			if(i==1){
//				return "周一";
//			}else if(i==2){
//				return "周二";
//			}else if(i==3){
//				return "周三";
//			}else if(i==4){
//				return "周四";
//			}else if(i==5){
//				return "周五";
//			}else if(i==6){
//				return "周六";
//			}else if(i==0){
//				return "周日";
//			}else if(i==7){
//				return "周日";
//			}
//		} 
//		return "";
//	}

	/*
	 * private DateTime(long lNow, Calendar cNow, Date dNow, Timestamp tNow,
	 * java.sql.Date today, Time now) { this.lNow = lNow; this.cNow = cNow;
	 * this.dNow = dNow; this.tNow = tNow; this.today = today; this.now = now; }
	 */

	/**
	 * 根据标准日期将其转为 只有小时与分钟
	 * 如2014-01-08 17:04:49 转化为 17:04
	 * @param timestamp
	 * @return
	 */
	public static String getHourAndMinFromTimestamp(String timestamp){
		if(timestamp==null){
			return "";
		}
		int start = timestamp.indexOf(" ");
		int end  = timestamp.lastIndexOf(":");

		try {
			timestamp = timestamp.substring(start+1, end);
		} catch (Exception e) {
			e.printStackTrace();
			return "出错啦！";
		}
		return timestamp;
	}

	/**
	 * 根据标准日期将其转为 只有月与日
	 * 如2014-01-08 17:04:49 转化为01-08
	 * @param timestamp
	 * @return
	 */
	public static String getMonAndDayFromTimestamp(String timestamp){
		if(timestamp==null){
			return "";
		}
		int start = timestamp.indexOf("-");
		int end = timestamp.indexOf(" ");

		try {
			timestamp = timestamp.substring(start+1, end);
		} catch (Exception e) {
			e.printStackTrace();
			return "出错啦！";
		}
		return timestamp;
	}

	/**
	 * 根据标准日期将其转为月日星期
	 * 如2014-01-08 17:04:49 转化为01月08日 周三
	 * @param timestamp
	 * @return
	 */
	public static String getMonDayWeekFromTimestamp(String timestamp){
		if(timestamp==null){
			return "";
		}

		Calendar c = pars2Calender(timestamp);
//		int w =getDay(c);

		int start = timestamp.indexOf("-");
		int end = timestamp.indexOf(" ");

		try {
			timestamp = timestamp.substring(start+1, end);
			timestamp = timestamp.replace("-","月");
			timestamp = timestamp+"日"+"　"+getDay2(c);
		} catch (Exception e) {
			e.printStackTrace();
			return "出错啦！";
		}
		return timestamp;
	}



	/**
	 * 根据标准日期将其转为月日星期
	 * 如2014-01-08 17:04:49 转化为2014年01月08日 周三
	 * @param timestamp
	 * @return
	 */
	public static String getMonDayWeekFromTimestamp2(String timestamp){
		if(timestamp==null){
			return "";
		}

		Calendar c = pars2Calender(timestamp);
//		int w =getDay(c);


		int start = timestamp.indexOf("-");
		int end = timestamp.indexOf(" ");

		String year = timestamp.substring(0, start);
		try {
			timestamp = timestamp.substring(start+1, end);
			timestamp = timestamp.replace("-","月");
			timestamp = timestamp+"日"+"　"+getDay2(c);
			timestamp = year+"年"+timestamp;
		} catch (Exception e) {
			e.printStackTrace();
			return "出错啦！";
		}
		return timestamp;
	}

	/**
	 * 根据标准日期将其转为年月日
	 * 如2014-01-08 17:04:49 转化为2014年01月08日
	 * @param timestamp
	 * @return
	 *
	 * Ts timestamp
	 * YMD year month day
	 */
	public static String convertTsToYMD(String timestamp){
		if(timestamp==null){
			return "";
		}

		int start = timestamp.indexOf("-");
		int end = timestamp.indexOf(" ");

		String year = timestamp.substring(0, start);
		try {
			timestamp = timestamp.substring(start+1, end);
			timestamp = timestamp.replace("-","月");
			timestamp = timestamp+"日";
			timestamp = year+"年"+timestamp;
		} catch (Exception e) {
			e.printStackTrace();
			return "出错啦！";
		}
		return timestamp;
	}



	/**
	 * 根据标准日期将其转为年月日
	 * 如2014-01-08 17:04:49 转化为2014-01-08
	 * @param timestamp
	 * @return
	 */
	public static String getDateFromTimestamp(String timestamp){
		if(timestamp==null){
			return "";
		}
//		int start = timestamp.indexOf("-");
		int end = timestamp.indexOf(" ");

		try {
			timestamp = timestamp.substring(0, end);
		} catch (Exception e) {
			e.printStackTrace();
			return "出错啦！";
		}
		return timestamp;
	}



	/**
	 * 得到年
	 *
	 * @param c
	 * @return
	 */
	public static int getYear(Calendar c) {
		if (c != null) {
			return c.get(Calendar.YEAR);
		} else {
			return Calendar.getInstance().get(Calendar.YEAR);
		}
	}

	/**
	 * 得到月份 注意，这里的月份依然是从0开始的
	 *
	 * @param c
	 * @return
	 */
	public static int getMonth(Calendar c) {
		if (c != null) {
			return c.get(Calendar.MONTH);
		} else {
			return Calendar.getInstance().get(Calendar.MONTH);
		}
	}
	/**
	 * 得到月份如 1月，2月
	 *
	 * @param c
	 * @return
	 */
	public static String getMonth2(Calendar c) {
		String str ="";
		if (c != null) {
			str = getMonthString(c.get(Calendar.MONTH)+1);
		} else {
			str = getMonthString(Calendar.getInstance().get(Calendar.MONTH)+1);
		}
		return str;
	}

	/**
	 *
	 * @param month
	 * @return
	 */
	public static String getMonthString(int month){
		String monthStr = "";
		if(month==1){
			monthStr = "1月";
		}else if(month==2){
			monthStr = "2月";
		}else if(month==3){
			monthStr = "3月";
		}else if(month==4){
			monthStr = "4月";
		}else if(month==5){
			monthStr = "5月";
		}else if(month==6){
			monthStr = "6月";
		}else if(month==7){
			monthStr = "7月";
		}else if(month==8){
			monthStr = "8月";
		}else if(month==9){
			monthStr = "9月";
		}else if(month==10){
			monthStr = "10月";
		}else if(month==11){
			monthStr = "11月";
		}else if(month==12){
			monthStr = "12月";
		}
		return monthStr;
	}
	/**
	 * 得到日期
	 *
	 * @param c
	 * @return
	 */
	public static int getDate(Calendar c) {
		if (c != null) {
			return c.get(Calendar.DATE);
		} else {
			return Calendar.getInstance().get(Calendar.DATE);
		}
	}

	/**
	 * 得到星期
	 *
	 * @param c
	 * @return
	 */
	public static int getDay(Calendar c) {
		if (c != null) {
			return c.get(Calendar.DAY_OF_WEEK);
		} else {
			return Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
		}
	}

	/**
	 * 得到小时
	 *
	 * @param c
	 * @return
	 */
	public static int getHour(int tatalSecond) {
		return tatalSecond /3600;
	}
	/**
	 * 得到小时
	 *
	 * @param c
	 * @return
	 */
	public static int getHour(Calendar c) {
		if (c != null) {
			return c.get(Calendar.HOUR_OF_DAY);
		} else {
			return Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
		}
	}

	/**
	 * 得到分钟
	 *
	 * @param c
	 * @return
	 */
	public static int getMinute(Calendar c) {
		if (c != null) {
			return c.get(Calendar.MINUTE);
		} else {
			return Calendar.getInstance().get(Calendar.MINUTE);
		}
	}

	/**
	 * 得到秒
	 *
	 * @param c
	 * @return
	 */
	public static int getSecond(Calendar c) {
		if (c != null) {
			return c.get(Calendar.SECOND);
		} else {
			return Calendar.getInstance().get(Calendar.SECOND);
		}
	}

	/**
	 * 得到指定或者当前时间前n天的Calendar
	 *
	 * @param c
	 * @param n
	 * @return
	 */
	public static Calendar beforeNDays(Calendar c, int n) {
		// 偏移量，给定n天的毫秒数
		long offset = n * 24 * 60 * 60 * 1000;
		Calendar calendar = null;
		if (c != null) {
			calendar = c;
		} else {
			calendar = Calendar.getInstance();
		}
		calendar.setTimeInMillis(calendar.getTimeInMillis() - offset);
		return calendar;
	}
	/**
	 * 得到指定或者当前时间前n天的日期
	 * @param c
	 * @param n
	 * @return
	 */
	public static String beforeNDays_v2(String date, int n) {
		if(date.indexOf(" ")<=0){
			date = date +" 00:00:00";
		}
		// 偏移量，给定n天的毫秒数
		Calendar calendar = pars2Calender(date);
		calendar.add(Calendar.DAY_OF_MONTH, n);
		return formatDate(calendar);
	}

	/**
	 * 得到当前时间前n天的Calendar
	 * @param c
	 * @param n
	 * @return
	 */
	public static Calendar beforeNDays(int n) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, n);
		return calendar;
	}

	/**
	 * 得到当前时间前或后n天的String 如2014-03-06
	 * @param n 前为负，如-2表前两天
	 * @return
	 */
	public static String beforeNDays2Str(int n) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, n);
		String str = formatDate(calendar);
		return str;
	}

	/**
	 * 得到当前时间前几秒的时间，或后几秒的时间
	 * @param n 前为负，如-60*60表60分钟前的时间
	 * @return
	 */
	public static String beforeSecond(int n) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.SECOND, n);
		String str = formatTime(calendar);
		return str;
	}
	/**
	 * 得到time前几秒的时间，或后几秒的时间
	 * @param n 前为负，如-60*60表60分钟前的时间
	 * @return
	 */
	public static String beforeSecond(String time,int n) {
//		Calendar calendar = Calendar.getInstance();
		Calendar calendar = pars2Calender(time);
		calendar.add(Calendar.SECOND, n);
		String str = formatTime(calendar);
		return str;
	}




	/**
	 * 得到指定或者当前时间后n天的Calendar
	 *
	 * @param c
	 * @param n
	 * @return
	 */
	public static Calendar afterNDays(Calendar c, int n) {
		// 偏移量，给定n天的毫秒数
		long offset = n * 24 * 60 * 60 * 1000;
		Calendar calendar = null;
		if (c != null) {
			calendar = c;
		} else {
			calendar = Calendar.getInstance();
		}

		calendar.setTimeInMillis(calendar.getTimeInMillis() + offset);
		return calendar;
	}

	/**
	 * 将2014-01-07 14:33:43 转成14：33
	 * @param TimeString
	 * @return
	 */
	public static String TimeStringToHourMIn(String TimeString){
		int space = TimeString.indexOf(" ");
		int last = TimeString.lastIndexOf(":");
		String time = "";
		try {
			time = TimeString.substring(space+1, last);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return time;
	}

	/**
	 * 昨天
	 *
	 * @param c
	 * @return
	 */
	public static Calendar yesterday(Calendar c) {
		long offset = 1 * 24 * 60 * 60 * 1000;
		Calendar calendar = null;
		if (c != null) {
			calendar = c;
		} else {
			calendar = Calendar.getInstance();
		}

		calendar.setTimeInMillis(calendar.getTimeInMillis() - offset);
		return calendar;
	}

	/**
	 * 明天
	 *
	 * @param c
	 * @return
	 */
	public static Calendar tomorrow(Calendar c) {
		long offset = 1 * 24 * 60 * 60 * 1000;
		Calendar calendar = null;
		if (c != null) {
			calendar = c;
		} else {
			calendar = Calendar.getInstance();
		}

		calendar.setTimeInMillis(calendar.getTimeInMillis() + offset);
		return calendar;
	}

	/**
	 * 得到指定或者当前时间前offset毫秒的Calendar
	 *
	 * @param c
	 * @param offset
	 * @return
	 */
	public static Calendar before(Calendar c, long offset) {
		Calendar calendar = null;
		if (c != null) {
			calendar = c;
		} else {
			calendar = Calendar.getInstance();
		}

		calendar.setTimeInMillis(calendar.getTimeInMillis() - offset);
		return calendar;
	}

	/**
	 * 得到指定或者当前时间前offset毫秒的Calendar
	 *
	 * @param c
	 * @param offset
	 * @return
	 */
	public static Calendar after(Calendar c, long offset) {
		Calendar calendar = null;
		if (c != null) {
			calendar = c;
		} else {
			calendar = Calendar.getInstance();
		}

		calendar.setTimeInMillis(calendar.getTimeInMillis() - offset);
		return calendar;
	}

	/**
	 * 日期格式化
	 *
	 * @param c
	 * @param pattern
	 * @return
	 */
	public static String format(Calendar c, String pattern) {
		Calendar calendar = null;
		if (c != null) {
			calendar = c;
		} else {
			calendar = Calendar.getInstance();
		}
		if (pattern == null || pattern.equals("")) {
			pattern = "yyyy年MM月dd日 HH:mm:ss";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);

		return sdf.format(calendar.getTime());
	}
	/**
	 * 日期格式化成2014-03-06 16:21:22
	 * @param c
	 * @return
	 */
	public static String formatTime(Calendar c) {
		Calendar calendar = null;
		if (c != null) {
			calendar = c;
		} else {
			calendar = Calendar.getInstance();
		}
		String pattern = "yyyy-MM-dd HH:mm:ss";
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(calendar.getTime());
	}

	/**
	 * 日期格式化成2014-03-06
	 * @param c
	 * @return
	 */
	public static String formatDate(Calendar c) {
		Calendar calendar = null;
		if (c != null) {
			calendar = c;
		} else {
			calendar = Calendar.getInstance();
		}
		String pattern = "yyyy-MM-dd";
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(calendar.getTime());
	}
	/**
	 * 日期格式化成2014年03月
	 * @param c
	 * @return
	 */
	public static String formatMonth(Calendar c) {
		Calendar calendar = null;
		if (c != null) {
			calendar = c;
		} else {
			calendar = Calendar.getInstance();
		}
		String pattern = "yyyy年MM月";
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(calendar.getTime());
	}

	/**
	 * 计算两个时期的秒数
	 * @return
	 */
	public static long differToSecond(Date date1,Date date2){

		return 1;
	}

	/**
	 * Date类型转换到Calendar类型
	 *
	 * @param d
	 * @return
	 */
	public static Calendar Date2Calendar(Date d) {
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		return c;
	}

	/**
	 * Calendar类型转换到Date类型
	 *
	 * @param c
	 * @return
	 */
	public static Date Calendar2Date(Calendar c) {
		return c.getTime();
	}

	/**
	 * Date类型转换到Timestamp类型
	 *
	 * @param d
	 * @return
	 */
	public static Timestamp Date2Timestamp(Date d) {
		return new Timestamp(d.getTime());
	}

	/**
	 * Calendar类型转换到Timestamp类型
	 *
	 * @param c
	 * @return
	 */
	public static Timestamp Calendar2Timestamp(Calendar c) {
		return new Timestamp(c.getTimeInMillis());
	}

	/**
	 * Timestamp类型转换到Calendar类型
	 *
	 * @param ts
	 * @return
	 */
	public static Calendar Timestamp2Calendar(Timestamp ts) {
		Calendar c = Calendar.getInstance();
		c.setTime(ts);
		return c;
	}

	/**
	 * 得到当前时间的字符串表示 格式2010-02-02 12:12:12
	 * @return
	 */
	public static String getTimeString() {
		return format(Calendar.getInstance(), "yyyy-MM-dd HH:mm:ss");
	}


	/**
	 * 得到当前时间的字符串表示 格式20140502_121212
	 * @return
	 */
	public static String getTimeString2() {
		String time  = format(Calendar.getInstance(), "yyyyMMddHHmmss");
		return time;
	}
	/**
	 * 当前时间 格式12:29
	 * @return
	 */
	public static String getTimeStr1229() {
		String time  = format(Calendar.getInstance(), "HH:mm");
		return time;
	}
	/**
	 * 当前时间 格式12:29
	 * @return
	 */
	public static String getTimeStr1229(String Time) {
		Calendar c = pars2Calender(Time);
		String time  = format(c, "HH:mm");
		return time;
	}
	/**
	 * 当前时间 格式12:29:00
	 * @return
	 */
	public static String getTimeStr122901(String Time) {
		Calendar c = pars2Calender(Time);
		String time  = format(c, "HH:mm:ss");
		return time;
	}

	/**
	 * 当前时间 格式12时29分
	 * @return
	 */
	public static String getTimeStr12shi29fen() {
		String time  = format(Calendar.getInstance(), "HH时mm分");
		return time;
	}

	/**
	 * 得到下个钟时间
	 */
	public static Calendar getNextHour(){
		Calendar c= Calendar.getInstance();
		c.add(Calendar.HOUR_OF_DAY, 1);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		return c;
	}
	/**
	 * 得到下个1/2刻
	 */
	public static Calendar getNextHalf(){
		Calendar c= Calendar.getInstance();
		int hour = c.get(Calendar.HOUR_OF_DAY);
		int min = c.get(Calendar.MINUTE);
		int sec = c.get(Calendar.SECOND);
		int day = c.get(Calendar.DAY_OF_MONTH);
		int mon = c.get(Calendar.MONTH)+1;
		if(min<30){
			min = 30;
		}else if(min>=30){
			min = 0;
			c.add(Calendar.HOUR_OF_DAY, 1);
		}
		c.set(Calendar.MINUTE, min);
		c.set(Calendar.SECOND, 0);
		return c;
	}
	/**
	 * 得到下个1/4刻
	 */
	public static Calendar getNextquarter(){
		Calendar c= Calendar.getInstance();
		int hour = c.get(Calendar.HOUR_OF_DAY);
		int min = c.get(Calendar.MINUTE);
		int sec = c.get(Calendar.SECOND);
		int day = c.get(Calendar.DAY_OF_MONTH);
		int mon = c.get(Calendar.MONTH)+1;
		if(min<15){
			min = 15;
		}else if(min>=15&&min<30){
			min = 30;
		}else if(min>=30&&min<45){
			min = 45;
		}else if(min>=45){
			min = 0;
			c.add(Calendar.HOUR_OF_DAY, 1);
		}
		c.set(Calendar.MINUTE, min);
		c.set(Calendar.SECOND, 0);
		return c;
	}
	/**
	 * 得到某一天的周起始天，周结束天
	 * @param date
	 * @param startDayOfWeek 用户定义的每周开始天，1表星期天，2表星期一
	 * @return 开始：arr[0],结束：arr[1]yyyy-MM-dd
	 */
	public static String[] getDateOfWeekStartAndEnd(String date,int startDayOfWeek) {
		String[] arr= new String[2];
		if(date.indexOf(" ")<0){
			date = date+" 00:00:00";
		}
		Calendar c = DateTime.pars2Calender(date);
//		c.get(Calendar.d)
		int d  = c.get(Calendar.DAY_OF_WEEK);//周日是开始
		System.out.println(d );
		int dif = 0;
		if(startDayOfWeek>d){
			dif = d+(7-startDayOfWeek);
		}else{
			dif = d-startDayOfWeek;
		}

		System.out.println(dif);
		c.add(Calendar.DATE,-dif);


		String time  = DateTime.format(c, "yyyy-MM-dd");
		arr[0] = time;
		c.add(Calendar.DATE,6);
		time  = DateTime.format(c, "yyyy-MM-dd");
		arr[1] = time;
		return arr;
	}
	/**
	 * 得到某一天,上一周的周起始天，周结束天
	 * @param date
	 * @param startDayOfWeek 用户定义的每周开始天，1表星期天，2表星期一
	 * @return 开始：arr[0],结束：arr[1]yyyy-MM-dd
	 */
	public static String[] getDateOfWeekStartAndEndForLastWeek(String date,int startDayOfWeek) {
		String[] arr= new String[2];
		if(date.indexOf(" ")<0){
			date = date+" 00:00:00";
		}
		Calendar c = DateTime.pars2Calender(date);
//		c.get(Calendar.d)
		int d  = c.get(Calendar.DAY_OF_WEEK);//周日是开始
		System.out.println(d );
		int dif = 0;
		if(startDayOfWeek>d){
			dif = d+(7-startDayOfWeek);
		}else{
			dif = d-startDayOfWeek;
		}

		System.out.println(dif);
		c.add(Calendar.DATE,-dif);

		c.add(Calendar.DATE, -7);

		String time  = DateTime.format(c, "yyyy-MM-dd");
		arr[0] = time;
		c.add(Calendar.DATE,6);
		time  = DateTime.format(c, "yyyy-MM-dd");
		arr[1] = time;
		return arr;
	}

	/**
	 * 得到某一天,下一周的周起始天，周结束天
	 * @param date
	 * @param startDayOfWeek 用户定义的每周开始天，1表星期天，2表星期一
	 * @return 开始：arr[0],结束：arr[1]yyyy-MM-dd
	 */
	public static String[] getDateOfWeekStartAndEndForNextWeek(String date,int startDayOfWeek) {
		String[] arr= new String[2];
		if(date.indexOf(" ")<0){
			date = date+" 00:00:00";
		}
		Calendar c = DateTime.pars2Calender(date);
//		c.get(Calendar.d)
		int d  = c.get(Calendar.DAY_OF_WEEK);//周日是开始
		System.out.println(d );
		int dif = 0;
		if(startDayOfWeek>d){
			dif = d+(7-startDayOfWeek);
		}else{
			dif = d-startDayOfWeek;
		}

		System.out.println(dif);
		c.add(Calendar.DATE,-dif);

		c.add(Calendar.DATE, 7);

		String time  = DateTime.format(c, "yyyy-MM-dd");
		arr[0] = time;
		c.add(Calendar.DATE,6);
		time  = DateTime.format(c, "yyyy-MM-dd");
		arr[1] = time;
		return arr;
	}
	/**
	 * 得天每周的开始日期 如2014--7-14
	 * @param date
	 * @param startDayOfWeek 用户定义的每周开始天，1表星期天，2表星期一
	 */
	public static String getDateOfWeekStart(int startDayOfWeek) {
		Calendar c = Calendar.getInstance();
		int d  = c.get(Calendar.DAY_OF_WEEK);//周日是开始
//		System.out.println(d );
		int dif = 0;
		if(startDayOfWeek>d){
			dif = d+(7-startDayOfWeek);
		}else{
			dif = d-startDayOfWeek;
		}
		c.add(Calendar.DATE,-dif);

		return formatDate(c);
	}
	/**
	 * 得到某一天,该天的第一天与最后一天的日期
	 * @param date
	 * @return 开始：arr[0],结束：arr[1],yyyy-MM-dd
	 */
	public static String[] getDateOfMonthStartAndEnd(String date) {
		String[] arr= new String[3];
		if(date.indexOf(" ")<0){
			date = date+" 00:00:00";
		}
		Calendar c = DateTime.pars2Calender(date);
		int day = c.get(Calendar.DAY_OF_MONTH);
		int min = c.getActualMinimum(Calendar.DAY_OF_MONTH);
		int max = c.getActualMaximum(Calendar.DAY_OF_MONTH);
		c.add(Calendar.DATE,-(day-min));
		String time  = DateTime.format(c, "yyyy-MM-dd");
		arr[0] = time;
		c.add(Calendar.DATE,(max-min));
		time  = DateTime.format(c, "yyyy-MM-dd");
		arr[1] = time;
		arr[2] = max+"";
		return arr;
	}

	/**
	 * 得到某一天,该天上个月的第一天与最后一天的日期
	 * @param date
	 * @return 开始：arr[0],结束：arr[1],yyyy-MM-dd
	 */
	public static String[] getDateOfMonthStartAndEndLastMonth(String date) {
		String[] arr= new String[3];
		if(date.indexOf(" ")<0){
			date = date+" 00:00:00";
		}
		Calendar c = DateTime.pars2Calender(date);
		c.add(Calendar.MONTH, -1);
		int day = c.get(Calendar.DAY_OF_MONTH);
		int min = c.getActualMinimum(Calendar.DAY_OF_MONTH);
		int max = c.getActualMaximum(Calendar.DAY_OF_MONTH);
		c.add(Calendar.DATE,-(day-min));
		String time  = DateTime.format(c, "yyyy-MM-dd");
		arr[0] = time;
		c.add(Calendar.DATE,(max-min));
		time  = DateTime.format(c, "yyyy-MM-dd");
		arr[1] = time;
		arr[2] = max+"";
		return arr;
	}
	/**
	 * 得到某一天,该天下个月的第一天与最后一天的日期
	 * @param date
	 * @return 开始：arr[0],结束：arr[1],yyyy-MM-dd
	 */
	public static String[] getDateOfMonthStartAndEndNextMonth(String date) {
		String[] arr= new String[3];
		if(date.indexOf(" ")<0){
			date = date+" 00:00:00";
		}
		Calendar c = DateTime.pars2Calender(date);
		c.add(Calendar.MONTH, 1);
		int day = c.get(Calendar.DAY_OF_MONTH);
		int min = c.getActualMinimum(Calendar.DAY_OF_MONTH);
		int max = c.getActualMaximum(Calendar.DAY_OF_MONTH);
		c.add(Calendar.DATE,-(day-min));
		String time  = DateTime.format(c, "yyyy-MM-dd");
		arr[0] = time;
		c.add(Calendar.DATE,(max-min));
		time  = DateTime.format(c, "yyyy-MM-dd");
		arr[1] = time;
		arr[2] = max+"";
		return arr;
	}


	/**
	 * 得到1分钟后的时间,格式：2014-04-02 12:12:12
	 * @return 2014-04-02 12:12:12
	 */
	public static String getTime1MinuteLater(){
		Calendar c = Calendar.getInstance();
		c.set(Calendar.MINUTE, c.get(Calendar.MINUTE)+60);
		String time = DateTime.formatTime(c);
//		String time = date.substring(date.indexOf(" ")+1, date.length());
//		log("测试提醒：提醒时间为："+time);
		return time;
	}

	/**
	 * 得到1分钟后的时间,格式：12:12:12
	 * @return 12:12:12
	 */
	public static String getTime1MinuteLater2(){
		Calendar c = Calendar.getInstance();
		c.set(Calendar.MINUTE, c.get(Calendar.MINUTE)+60);
		String date = DateTime.formatTime(c);
		String time = date.substring(date.indexOf(" ")+1, date.length());
//		log("测试提醒：提醒时间为："+time);
		return time;
	}
	/**
	 * 得到n秒后的时间,格式：12:12:12
	 * @return 12:12:12
	 */
	public static String getTime_nSecondLater(int n){
		Calendar c = Calendar.getInstance();
		c.set(Calendar.SECOND, c.get(Calendar.SECOND)+20);
		String date = DateTime.formatTime(c);
		String time = date.substring(date.indexOf(" ")+1, date.length());
		return time;
	}

	/**
	 * 得到当前时间的字符串表示 格式2010-02-02
	 *
	 * @return
	 */
	public static String getDateString() {
		return format(Calendar.getInstance(), "yyyy/MM/dd");
	}

	/**
	 * 标准日期格式字符串解析成Calendar对象
	 *
	 * @param s
	 * @return
	 */
	public static Calendar pars2Calender(String s) {
		Timestamp ts = Timestamp.valueOf(s);
		return Timestamp2Calendar(ts);
	}
	/**
	 * 标准日期格式字符串解析成Calendar对象
	 * @param s
	 * @return
	 */
	public static Calendar pars2CalenderCanNull(String s) {
		Calendar cal;
		try {
			Timestamp ts = Timestamp.valueOf(s);
			cal = Timestamp2Calendar(ts);
		} catch (IllegalArgumentException e) {
			cal = Calendar.getInstance();
		}
		return cal;
	}
	/**
	 * 标准日期字符串解析成Calendar对象，时间为00:00:00
	 * @param date
	 * @return
	 */
	public static Calendar pars2Calender2(String date) {
		Timestamp ts = Timestamp.valueOf(date+" 00:00:00");
		return Timestamp2Calendar(ts);
	}

	/**
	 * 将秒数转为00小时00分00秒 格式
	 * @param totalSecond
	 * @return
	 */
	public static String calculateTime(long totalSecond) {
		long hours = totalSecond / 3600;
		long minute = (totalSecond % 3600) / 60;
		long second = (totalSecond % 3600) % 60;

		String retValue = "";
		if(hours > 0)
			retValue += hours + "小时";

		if(minute > 0)
			retValue += minute + "分";

		retValue += second + "秒";

		return retValue;
	}
	/**
	 * 将秒数转为00小时00分 格式 (不带秒)
	 * @param totalSecond
	 * @return
	 */
	public static String calculateTime3(long totalSecond) {
		long days = totalSecond / 86400;
		long hours = (totalSecond%86400) / 3600;
		long minute = ((totalSecond%86400) % 3600) / 60;
//		long second = ((totalSecond%86400) % 3600) % 60;

		String retValue = "";

		if(days>0){
			retValue += days + "天";
		}
		if(hours > 0){
			retValue += hours + "小时";
		}

		if(minute > 0)
			retValue += minute + "分";

		if(retValue.equals("")){

			retValue += "<1分";
		}

		return retValue;
	}
//	/**
//	 * 将秒数转为00时00分 格式 (不带秒)
//	 * @param totalSecond
//	 * @return
//	 */
//	public static String calculateTime5(Context context,long totalSecond) {
//		long days = totalSecond / 86400;
//		long hours = (totalSecond%86400) / 3600;
//		long minute = ((totalSecond%86400) % 3600) / 60;
////		long second = ((totalSecond%86400) % 3600) % 60;
//		
//		String retValue = "";
//		
//		if(days>0){
//			retValue += days + context.getResources().getString(R.string.str_Day);
//		}
//		if(hours > 0){
//			retValue += hours + context.getResources().getString(R.string.str_hour_short);
//		}
//		
//		if(minute > 0)
//			retValue += minute + context.getResources().getString(R.string.str_minute_short);
//		
//		if(retValue.equals("")){
//			
//			retValue += "<1"+context.getResources().getString(R.string.str_minute_short);
//		}
//		
//		return retValue;
//	}
//	/**
//	 * 将秒数转为00时00分 格式 (不带秒)
//	 * @param totalSecond
//	 * @return
//	 */
//	public static String calculateTime5(long totalSecond) {
//		long days = totalSecond / 86400;
//		long hours = (totalSecond%86400) / 3600;
//		long minute = ((totalSecond%86400) % 3600) / 60;
////		long second = ((totalSecond%86400) % 3600) % 60;
//		
//		String retValue = "";
//		
//		if(days>0){
//			retValue += days + "天";
//		}
//		if(hours > 0){
//			retValue += hours + "时";
//		}
//		
//		if(minute > 0)
//			retValue += minute + "分";
//		
//		if(retValue.equals("")){
//			
//			retValue += "<1分";
//		}
//		
//		return retValue;
//	}

	/**
	 * 将秒数转为00:00分 格式 (不带秒)
	 * @param totalSecond
	 * @return
	 */
	public static String calculateTime4(long totalSecond) {
		long hours = (totalSecond%86400) / 3600;
		long minute = ((totalSecond%86400) % 3600) / 60;
//		long second = ((totalSecond%86400) % 3600) % 60;
		String retValue = "";
		if(hours > 0){
			retValue += hours + ":";
		}

		if(minute<10){
			retValue += "0"+minute;
		}else{
			retValue += minute;
		}
		return retValue;
	}

	/**
	 * 将秒数转为00:00:00格式
	 * @param totalSecond
	 * @return
	 */
	public static String calculateTime2(long totalSecond) {
		long hours = totalSecond / 3600;
		long minute = (totalSecond % 3600) / 60;
		long second = (totalSecond % 3600) % 60;

		String retValue = "";
		if(hours > 0){
//			retValue += hours + "：";
			if(hours>9){
				retValue += hours + ":";
			}else{
				retValue += "0"+hours + ":";
			}
		}else{
			retValue += "00:";
		}

		if(minute > 0){
			if(minute>9){
				retValue += minute + ":";
			}else{
				retValue += "0"+minute + ":";
			}
		}else{
			retValue += "00:";
		}

		if(second>9){
			retValue += second;
		}else{
			retValue += "0"+second;
		}

		return retValue;
	}
	/**
	 * 将秒数转为00时00分00秒格式
	 * @param totalSecond
	 * @return
	 */
	public static String calculateTime6(long totalSecond) {
		long hours = totalSecond / 3600;
		long minute = (totalSecond % 3600) / 60;
		long second = (totalSecond % 3600) % 60;

		String retValue = "";
		if(hours > 0){
//			retValue += hours + "：";
//			if(hours>9){
				retValue += hours + "时";
//			}else{
//				retValue += hours + ":";
//			}
		}

		if(minute > 0){
//			if(minute>9){
				retValue += minute + "分";
//			}else{
//				retValue += "0"+minute + ":";
//			}
		}else{
			if(hours > 0){
				retValue += "0分";
			}
		}

		if(second>0){
			retValue += second+"秒";
		}
		if(hours==0&&minute==0&&second==0){
			retValue = "0秒";
		}


		return retValue;
	}
	/**
	 * 将秒数转为00:00秒格式(不带时)
	 * @param totalSecond
	 * @return
	 */
	public static String calculateTime7(long totalSecond) {
		long hours = totalSecond / 3600;
		long minute = (totalSecond % 3600) / 60;
		long second = (totalSecond % 3600) % 60;

		String retValue = "";

		if(minute > 0){
//			if(minute>9){
				retValue += minute + ":";
//			}else{
//				retValue += "0"+minute + ":";
//			}
		}else{
				retValue += "0:";
		}

		if(second > 0){
			if(second>9){
				retValue += second;
			}else{
				retValue += "0"+second;
			}
		}else{
			retValue += "00";
		}
		return retValue;
	}
	/**
	 * 比较两个日期的大小,（大）即日期靠后
	 * @param DATE1
	 * @param DATE2
	 * @return 1时date1比date2更后
	 */
	public static int compare_date(String DATE1, String DATE2) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");//日期格式控制
        try {
            Date dt1 = df.parse(DATE1);
            Date dt2 = df.parse(DATE2);
            if (dt1.getTime() > dt2.getTime()) {
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }
	/**
	 * 日期与现在比较，
	 * @param DATE1
	 * @return 1表time大于现在，0相等，-1小于现在
	 */
	public static int compareNow(String time) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");//日期格式控制
		try {
			Date dt1 = df.parse(time);
			Date dt2 = Calendar.getInstance().getTime();
			if (dt1.getTime() > dt2.getTime()) {
				return 1;
			} else if (dt1.getTime() < dt2.getTime()) {
				return -1;
			} else {
				return 0;
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return 0;
	}

	/**
	 *	将字符串转换成日期类型
	 * @param dateStr	日期字符串
	 * @param styleStr	样式字符串,如:yyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static Date toDate(String dateStr, String styleStr) {
		Date date = null;
		SimpleDateFormat sdf = new SimpleDateFormat(styleStr);
		try {
			date = sdf.parse(dateStr);
		} catch (ParseException pe) {
			pe.printStackTrace();
			date = null;
		}
		return date;
	}

	/**
	 *	求两个日期间隔的天数
	 * @param startDateStr
	 * @param endDateStr
	 * @return
	 * 求两个日期间隔的天数
	 */
	public static int getBetweenDayNumber(Date startDate, Date endDate) {
		long dayNumber = 0;
		dayNumber = (endDate.getTime() - startDate.getTime()) / DateTime.DAY_MILLI;

		return (int)dayNumber;
	}

	/**
	 * 求两个日期间隔的天数
	 * @param startDateStr
	 * @param endDateStr
	 * @return
	 */
	public static int getBetweenDayNumber(String startDateStr, Date endDate) {
		long dayNumber = 0;
		SimpleDateFormat sdf = new SimpleDateFormat(DateTime.DATE_FORMAT_LINE);
		try {
			Date startDate = sdf.parse(startDateStr);
			dayNumber = (endDate.getTime() - startDate.getTime()) / DateTime.DAY_MILLI;
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return (int)dayNumber;
	}
	/**
	 *	求两个日期间隔的小时
	 * @param startDateStr
	 * @param endDateStr
	 * @return
	 */
	public static int getBetweenDayNumberByTime(Date startDate, Date endDate) {
		long dayNumber = 0;
		dayNumber = (endDate.getTime() - startDate.getTime()) / (60 * 1000);
		return (int)dayNumber;
	}


	/**
	 * 求两个日期间隔的天数
	 * @param startDateStr
	 * @param endDateStr
	 * @return
	 * 求两个日期间隔的天数
	 */
	public static int cal_daysBetween(Date startDate, Date endDate) {
		long dayNumber = 0;
		dayNumber = (endDate.getTime() - startDate.getTime()) / DateTime.DAY_MILLI;
		return (int)dayNumber;
	}

	/**
	 * 求两个日期间隔的天数
	 * @param startDateStr
	 * @param endDateStr
	 * @return
	 */
	public static int cal_daysBetween(String startDateStr, Date endDate) {
		long dayNumber = 0;
		SimpleDateFormat sdf = new SimpleDateFormat(DateTime.DATE_FORMAT_LINE);
		try {
			Date startDate = sdf.parse(startDateStr);
			dayNumber = (endDate.getTime() - startDate.getTime()) / DateTime.DAY_MILLI;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return (int)dayNumber;
	}

	/**
	 * 求两个日期间隔的天数
	 * @param startDateStr
	 * @param endDateStr 2014-03-14
	 * @return
	 */
	public static int cal_daysBetween(String startDateStr, String endDateStr) {
		long dayNumber = 0;
		SimpleDateFormat sdf = new SimpleDateFormat(DateTime.DATE_FORMAT_LINE);
		try {
			Date startDate = sdf.parse(startDateStr);
			Date endDate = sdf.parse(endDateStr);
			dayNumber = (endDate.getTime() - startDate.getTime()) / DateTime.DAY_MILLI;
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return (int)dayNumber;
	}

	/**
	 * 两个日期间的天数，格式任意，位置任意
	 * @param start 可以是2014-03-06 也可以2014-03-06 00:12:00格式
	 * @param end
	 * @return {2014-03-12,2014-03-13}
	 */
	public static int cal_daysBetween_v2(String start, String end) {
		if(start==null||end==null){
			return 0;
		}
		start = start.replace("T"," ");
		end = end.replace("T"," ");
		int startInt = start.indexOf(" ");
		int endInt = end.indexOf(" ");
		if(startInt>0){
			start = start.substring(0, startInt);
		}
		if(endInt>0){
			end = end.substring(0, endInt);
		}
		int diff = compare_date(start+" 00:00:00", end+" 00:00:00");

		return diff;
	}

	/**
	 * 得到指定日期间的日期集合
	 * @param startDateStr 可以是2014-03-06 也可以2014-03-06 00:12:00格式
	 * @param endDateStr
	 * @return {2014-03-12,2014-03-13}
	 */
	public static HashMap<String, Integer> cal_daysMapBetween(String start, String end) {
		if(start==null&&end==null){
			return null;
		}
		int startInt = start.indexOf(" ");
		int endInt = end.indexOf(" ");
		if(startInt>0){
			start = start.substring(0, startInt);
		}
		if(endInt>0){
			end = end.substring(0, endInt);
		}
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		int flag = compare_date(start+" 00:00:00", end+" 00:00:00");
		if(flag>0){
			String temp = end;
			end = start;
			start = temp;
		}else if(flag==0){
			map.put(start, 0);
			return map;
		}

		int diff = DateTime.cal_daysBetween(start,end);
		Calendar cal = DateTime.pars2Calender(start+" 00:00:00");
		map.put(start, 0);
		for(int i=0;i<diff;i++){
			cal.add(Calendar.DATE, 1);
			map.put(DateTime.formatDate(cal), 0);
		}

		return map;
	}

	/**
	 *	求两个日期间隔的小时
	 * @param startDateStr
	 * @param endDateStr
	 * @return
	 */
	public static int cal_hoursBetween(Date startDate, Date endDate) {
		long dayNumber = 0;
		dayNumber = (endDate.getTime() - startDate.getTime()) / (60 * 1000);
		return (int)dayNumber;
	}


	/**
	 * 得到两个日期间的秒数
	 * @param startDateStr
	 * @param endDateStr
	 * @return
	 */
	public static int cal_secBetween(Date startDate, Date endDate) {
		long dayNumber = 0;
		try {
			dayNumber = (endDate.getTime() - startDate.getTime()) / (1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (int)dayNumber;
	}
	/**
	 * 得到两个日期间的秒数
	 * @param startDateStr
	 * @param endDateStr
	 * @return
	 */
	public static int cal_secBetween(String startDate, String endDate) {
		long dayNumber = 0;
		try {
			dayNumber = (pars2Calender(endDate).getTime().getTime() -pars2Calender(startDate).getTime().getTime()) / (1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (int)dayNumber;
	}



	/**
	 * TODO 得到天数数组（没测试）
	 * @param startTime
	 * @param deadline
	 * @return new int[]{tatal(起始),use(到目前已过天数)}
	 */
	public static int[] getDayBetween(String startTime,String deadline){
		if(startTime==null||deadline==null){
			return null;
		}
		Calendar cal = Calendar.getInstance();
		Date today = cal.getTime();
		Date start = DateTime.pars2Calender(startTime).getTime();
		Date dead = DateTime.pars2Calender(deadline).getTime();

		int use = DateTime.getBetweenDayNumber(start, today);
		int tatal = DateTime.getBetweenDayNumber(start, dead);
		return new int[]{tatal,use};
	}

//	/**
//	 * 得到给定时间到n天前的所有日期
//	 * @param startTime
//	 * @param day 5天前
//	 * @return new int[]{tatal(起始),use(到目前已过天数)}
//	 */
//	public static Object[] beforeNDaysArr(String startTime,int day){
//		if(startTime==null){
//			return null;
//		}
//		Calendar cal = pars2Calender(startTime);
//		cal.add(Calendar.DATE, -day);
//		Set<String>  daySet = new TreeSet<String>();
//		Map<String,DayInfo> dayMap = new HashMap<String, DayInfo>();
//		for(int i=0;i<=day;i++){
//			String tempDate = DateTime.formatDate(cal);
//			dayMap.put(tempDate, new DayInfo(i+1, 0, "",0, DateTime.format(cal, "dd"), DateTime.getDay3(cal)));
//			daySet.add(tempDate);
//			cal.add(Calendar.DATE, 1);
//		}
//		Object[] arr = new Object[2];
//		arr[0] = daySet;
//		arr[1] = dayMap;
//		return arr;
//	}


//	/**
//	 * 得到给定时间到n天后的所有日期
//	 * @param startTime
//	 * @param day 5天后
//	 * @return new int[]{tatal(起始),use(到目前已过天数)}
//	 */
//	public static Object[] afterNDaysArr(String startTime,int day){
//		if(startTime==null){
//			return null;
//		}
//		Calendar cal = pars2Calender(startTime);
////		cal.add(Calendar.DATE, -day);
//		Set<String>  daySet = new TreeSet<String>();
//		Map<String,DayInfo> dayMap = new HashMap<String, DayInfo>();
//		for(int i=0;i<=day;i++){
//			String tempDate = DateTime.formatDate(cal);
//			dayMap.put(tempDate, new DayInfo(i+1, 0, "",0, DateTime.format(cal, "dd"), DateTime.getDay3(cal)));
//			daySet.add(tempDate);
//			cal.add(Calendar.DATE, 1);
//		}
//		Object[] arr = new Object[2];
//		arr[0] = daySet;
//		arr[1] = dayMap;
//		return arr;
//	}





	// ================以下是get和set方法=========================//

	public long getLNow() {
		return lNow;
	}

	public void setLNow(long now) {
		lNow = now;
	}

	public Calendar getCNow() {
		return cNow;
	}

	public void setCNow(Calendar now) {
		cNow = now;
	}

	public Date getDNow() {
		return dNow;
	}

	public void setDNow(Date now) {
		dNow = now;
	}

	public Timestamp getTNow() {
		return tNow;
	}

	public void setTNow(Timestamp now) {
		tNow = now;
	}

	public java.sql.Date getToday() {
		return today;
	}

	public void setToday(java.sql.Date today) {
		this.today = today;
	}

	public java.sql.Time getNow() {
		return now;
	}

	public void setNow(java.sql.Time now) {
		this.now = now;
	}

	/**
	 * @param
	 */
	public static String todayWeek() {
		String str[]={"星期日","星期一","星期二","星期三","星期四","星期五","星期六"};//字符串数组
		Calendar rightNow=Calendar.getInstance();
		int day=rightNow.get(rightNow.DAY_OF_WEEK);//获取时间
//		System.out.println("今天是"+str[day-1]);//通过数组把周几输出
		return str[day - 1];
	}





}
