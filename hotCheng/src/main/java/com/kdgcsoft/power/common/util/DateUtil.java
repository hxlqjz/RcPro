package com.kdgcsoft.power.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;




import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 日期处理类
 *
 */
public class DateUtil extends SimpleDateFormat{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger log = LoggerFactory.getLogger(DateUtil.class);

	public static final String[] parsePatterns = { 
		"yyyy/MM/dd",
		"yyyy-MM-dd",
		"yyyy-M-dd",
		"yyyy-MM-dd HH:mm",
		"yyyy-MM-dd HH:mm:ss",
		"yyyy-MM-dd'T'HH:mm",
		"yyyy-MM-dd HH:mm:ss.SSS",
		"yyyy年MM月",
		"EEE MMM dd HH:mm:ss zzz yyyy",
		"yyyy年MM月dd日" };
	
	/**
	 * 把输入参数的日期字符串转换为中文年月日形式：yyyy年MM月dd日。如果无法识别输入日期，返回null;
	 * @param dateStr
	 * @return
	 */
	public static String getDateStrCN(String dateStr){
		Date date = parseDate(dateStr);
		if (date == null) {
			log.error("无法识别的日期格式 {}", dateStr);
			 return null;
		} else {
			return format(date, "yyyy年MM月dd日");
		}
	}
	
    /**
     * 获取当前日期时间字符串，格式为 yyyy-MM-dd HH:mm:ss
     */
    public static String getCurDateTimeStr() {
    	return formatDateTime(new Date());
    }
    
    
    /**
     * 获取当天日期字符串，格式为 yyyy-MM-dd
     * @return
     */
    public static String getTodayStr(){
    	return formatDate(new Date());
    }
    
    /**
     * 获取当天日期
     * @return
     */
    public static Date getToday(){
    	return new Date();
    }
    
     /*********  日期与字符串的转换   ************/
    
    /**
     * 日期转字符串  yyyy-MM-dd
     * @param date
     */
    public static String formatDate(Object date) {
    	return format(date, "yyyy-MM-dd");
    }
    
    /**
     * 日期转字符串  yyyy-MM-dd HH:mm:ss
     * @param date
     */
    public static String formatDateTime(Object date) {
    	return format(date, "yyyy-MM-dd HH:mm:ss");
    }
    
    /**
     * 按给定格式转换日期对象为字符串。
     * @param date 转换日期对象
     * @param pattern 格式字符串，例如 yyyy-MM-dd
     * @return
     */
    public static String format(Object date, String pattern) {
    	if (date == null || pattern == null) {
    		log.error("传入的日期对象或格式字符串为空！");
        	return null;
        }
    	return new SimpleDateFormat(pattern).format(date);
    }
    
    /**
     * 字符串转日期
     * @param dateStr
     * @return 日期对象。如果转换失败，返回null。
     */
    public static Date parseDate(String dateStr) {
		if(StringUtil.isEmpty(dateStr)) {
			log.error("传入的日期字符串为空！");
			return null;
		}
    	try {
			return DateUtils.parseDate(dateStr, parsePatterns);
		} catch (ParseException e) {
			log.error("字符串转日期失败" + dateStr, e);
		}
		return null;
	}
    
    
    /*********  日期与字符串的转换结束   ************/
    
    /**
     * 获取当天年份
     * @return
     */
    public String getYear(){
    	return getTodayStr().substring(0,4);
    }
    
    /**
     * 获取当天月份
     * @return
     */
    public String getMonth(){
    	return getTodayStr().substring(5,7);
    }

    
    /**
     * 得到当前日期是本月第几周
     * @param curday
     */
    public static int getMonthWeek(Date curDate) {
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(curDate);
        return rightNow.get(Calendar.WEEK_OF_MONTH);
    }

    /**
     * 得到当前日期是本年第几周
     * @param curday
     */    
    public static int getYearWeek(Date curDate) {
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(curDate);
        return rightNow.get(Calendar.WEEK_OF_YEAR);
    }
    
    
    /****************  获取日期前后的日期   *******************/
    
    
    /**
     * 天数前后的日期  正数表示天数后  负数表示天数前   
     * 2013-10-10,8  结果:2013-10-18
     * @param date
     * @param days
     * @return
     */
    public static String getPerAfterDay(String dateStr, int days) {
    	Date date = parseDate(dateStr);
    	return formatDate(getPerAfterDay(date,days));
    }
    
    public static Date getPerAfterDay(Date date, int days) {
    	Calendar rightNow = Calendar.getInstance();
    	rightNow.setTime(date);
    	rightNow.add(Calendar.DAY_OF_YEAR, days);
    	return rightNow.getTime();
    }
    
    /**
     * 返回月数前后的日期字符串，格式为yyyy-MM-dd
     * 2013-10-10,8  结果:2014-6-18
     * @param date 基准日期
     * @param months  偏移月数。正数表示月数后，负数表示月数前
     * @return
     */
    public static String getPerAfterMonth(String dateStr,int months) {
    	Date date = parseDate(dateStr);
    	return formatDate(getPerAfterMonth(date,months));
    }
    
    /**
     * 返回月数前后的日期对象
     * 2013-10-10,8  结果:2014-6-18
     * @param date 基准日期
     * @param months  偏移月数。正数表示月数后，负数表示月数前
     * @return
     */
    public static Date getPerAfterMonth(Date date,int months){
    	Calendar rightNow = Calendar.getInstance();
    	rightNow.setTime(date);
    	rightNow.add(Calendar.MONTH, months);
    	return rightNow.getTime();
    }
    
    /**
     * 年数前后的日期字符串。返回日期格式为yyyy-MM-dd
     * 例如： 参数为2013-10-10, 1  结果:2014-10-10
     * @param date 基准日期。
     * @param years 年数。正数表示年数后，负数表示年数前。
     * @return
     */
    public static String getPerAfterYear(String dateStr, int years){
    	Date date = parseDate(dateStr);
    	return formatDate(getPerAfterYear(date, years));
    }
    
    /**
     * 年数前后的日期。
     * 例如： 参数为2013-10-10, 1  结果:2014-10-10
     * @param date 基准日期。
     * @param years 年数。正数表示年数后，负数表示年数前。
     * @return
     */
    public static Date getPerAfterYear(Date date,int years){
    	Calendar rightNow = Calendar.getInstance();
    	rightNow.setTime(date);
    	rightNow.add(Calendar.YEAR, years);
    	return rightNow.getTime();
    }
    
    /**
     * 返回周数前后的日期字符串。返回的日期格式为yyyy-MM-dd。
     * 例如： 参数为2013-10-10, 2  结果:2013-10-24
     * @param date 基准日期。
     * @param weeks 周数。正数表示周数后，负数表示周数前。
     * @return
     */
    public static String getPerAfterWeek(String dateStr, int weeks){
    	Date date = parseDate(dateStr);
    	return formatDate(getPerAfterWeek(date, weeks));
    }
    
    /**
     * 返回加减指定周数后的日期。正数表示周数后， 负数表示周数前
     * 例如： 参数为2013-10-10, 2  结果:2013-10-24
     * @param date 基准日期。
     * @param weeks 周数。正数表示周数后，负数表示周数前。
     * @return
     */
    public static Date getPerAfterWeek(Date date, int weeks){
    	Calendar rightNow = Calendar.getInstance();
    	rightNow.setTime(date);
    	rightNow.add(Calendar.WEEK_OF_YEAR, weeks);
    	return rightNow.getTime();
    }
    
    /**
     * 返回周几(1-7)。1表示周日，7表示周六
     * @param date
     * @return
     */
    public static int getWeekDay(Date date) {
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(date);
        return rightNow.get(Calendar.DAY_OF_WEEK);
    }
 
    /**
    * 返回中文的"星期几"。 
    * @param date
    * @return
    * @throws Exception 
    */
    public static String getWeekCN(Date date) {
        int i = getWeekDay(date);
        String s = "";
        switch (i) {
            case 1:
                s = "星期天";
                break;
            case 2:
                s = "星期一";
                break;
            case 3:
                s = "星期二";
                break;
            case 4:
                s = "星期三";
                break;
            case 5:
                s = "星期四";
                break;
            case 6:
                s = "星期五";
                break;
            case 7:
                s = "星期六";
                break;
        }
        return s;
    }

    
   /**
    * 返回日期所在月最后一天的日期
    * @param date
    * @return
    */
    public static Date getMonthLastDay(Date date) {
    	if (date == null) {
    		log.error("传入的日期为null!");
    		return null;
    	}
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(date);
        rightNow.set(Calendar.DAY_OF_MONTH, 1);
        rightNow.roll(Calendar.DAY_OF_MONTH, -1);
        return rightNow.getTime();
    }
    
    /**
     * 返回日期所在月最后一天的日期字符串，格式为yyyy-MM-dd
     * @param dateStr 日期
     * @return
     */
    public static String getMonthLastDay(String dateStr) {
    	Date date = parseDate(dateStr);
    	return formatDate(getMonthLastDay(date));
    }
    
    /************ 两个日期之间的月数、天数  *******************/
    
   /**
    * 两个日期间隔的月数，endDate - startDate  
    * startDate 2013-01-01 endDate 2013-07-02  结果为6
    * @param startDate
    * @param endDate
    * @return
 * @throws Exception 如果任何参数为null，抛出异常
    */
    public static int getDiffMonths(Date startDate, Date endDate) throws Exception {
    	if (startDate == null || endDate == null) {
    		log.error("日期为空，无法计算。");
    		throw new Exception("因日期为空，无法计算日期间隔!");    		
    	}
    	Calendar start = Calendar.getInstance();
    	Calendar end = Calendar.getInstance();
		start.setTime(startDate);
		end.setTime(endDate);
    	int diff = (end.get(Calendar.YEAR) - start.get(Calendar.YEAR)) * 12 + end.get(Calendar.MONTH) - start.get(Calendar.MONTH);
    	return diff;
    }
    
    /**
     * 两个日期字符串间隔的月数，endDate - startDate  
     * startDate 2013-01-01 endDate 2013-07-02  结果为6
     * @param startDate 起始日期，会尝试自动识别格式
     * @param endDate 结束日期，会尝试自动识别格式
     * @return
     * @throws Exception 
     */
    public static int getDiffMonths(String startDateStr, String endDateStr) throws Exception {
    	return getDiffMonths(parseDate(startDateStr), parseDate(endDateStr));
    }

    /**
     * 两个日期之间间隔的的天数  endDate - startDate  
     * startDate 2013-01-01 endDate 2013-01-02  结果为1
     * @param startDate
     * @param endDate
     * @return 间隔天数
     * @throws Exception 如果传入的日期对象为空，抛出异常
     */
    public static int getDiffDays(Date startDate, Date endDate) throws Exception {
    	if (startDate == null || endDate == null) {
    		throw new Exception("传入的日期为空!");
    	}
        Long str = (endDate.getTime() - startDate.getTime()) / (3600 * 24 * 1000);
        return str.intValue();
    }
    
    /**
     * 两个日期字符串之间间隔的天数，endDate - startDate  
     * startDate 2013-01-01 endDate 2013-01-02  结果为1
     * @param startDate 起始日期，会尝试自动识别格式
     * @param endDate 结束日期，会尝试自动识别格式
     * @return 天数
     * @throws Exception 如果日期无法识别，抛出异常
     */
    public static int getDiffDays(String startDateStr, String endDateStr) throws Exception {
    	if (StringUtil.isEmpty(startDateStr) || StringUtil.isEmpty(endDateStr)) {
    		throw new Exception("传入的日期字符串为空!");
    	}
    	Date startDate = parseDate(startDateStr);
    	Date endDate = parseDate(endDateStr);
    	if (startDate == null || endDate == null) {
    		throw new Exception("无法识别的日期格式！!");
    	}
    	return getDiffDays(startDate, endDate);
    }
	
    /**
	 * 获得与某日期相隔几天的日期
	 * 
	 * @param date
	 *            指定的日期
	 * @param addCount
	 *            相隔的天数
	 * @return 返回的日期
	 */
	public static Date addDay(Date date, int addCount) throws ParseException {
		// Calendar cal = new GregorianCalendar();
		// 最好用Calendar.getInstance();
		// 因为有的地方，不是使用GregorianCalendar的。
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(parseDate(dateToStr(date)));
		calendar.add(Calendar.DATE, addCount);
		return calendar.getTime();
	}
	/**
	 * 转换日期为字符串，格式"yyyy-MM-dd"
	 * 
	 * @param date
	 *            日期
	 * @return 返回格式化的日期字符串
	 */
	public static String dateToStr(Date date) {
		if (date == null)
			return null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		return formatter.format(date);
	}
    
	/**
	 * 判断某年是平年还是闰年
	 * @param year
	 * @return
	 */
	public static boolean isLeapYear(String year){
		boolean isLeapYear = false;
		int yearInt = Integer.parseInt(year);
		if(yearInt / 400 == 0 || (yearInt % 4 == 0 && yearInt / 100 != 0)){
			isLeapYear = true;
		}
		return isLeapYear;
	}

	 /**
	  * 将其他格式转为yyyy-MM-dd
	  * @param str
	  * @return
	  */
	 public static String toDateStr(String str){
		 String dateStr = null;
		 if(str != null && str.length() == 10){
			 dateStr = str.substring(0, 4) + "-" + str.substring(5, 7) + "-" + str.substring(8, 10);
		 }
		 return dateStr;
	 }
	 
	 /**
     * 两个日期之间毫秒数  endDate - startDate  
     * startDate 2013-01-01 endDate 2013-01-02  结果为1
     * @param startDate
     * @param endDate
     * @return 间隔天数
     * @throws Exception 如果传入的日期对象为空，抛出异常
     */
    public static Long getDiffMilliseconds (Date startDate, Date endDate) throws Exception {
    	if (startDate == null || endDate == null) {
    		throw new Exception("传入的日期为空!");
    	}
        Long diff = endDate.getTime() - startDate.getTime();
        return diff;
    }
    
    /**
     * 重写SimpleDateFormat中parse中方法将字符串日志转化成对应的Date类型
     * @param source 日期型字符串
     */
    public Date parse(String source) {
		return DateUtil.parseDate(source);
	}
	 
}
