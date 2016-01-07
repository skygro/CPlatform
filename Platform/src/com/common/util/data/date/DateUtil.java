package com.common.util.data.date;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.common.constants.Constants;
import com.common.util.data.TypeCaseHelper;
import com.common.util.data.ValidateUtil;

/**
 * 日期格式化处理类
 * @author lhw
 *
 */
public class DateUtil implements DateFormatImpl{
	private static SimpleDateFormat getDateParser(String pattern)
	  {
	    return new SimpleDateFormat(pattern);
	  }

	  public static String getDateFormatStr(String formart) {
	    return getDateParser(formart).format(new java.util.Date());
	  }

	  public static java.util.Date formatStringToDate(String str, String formatStr)
	  {
	    java.util.Date date = null;
	    try {
	      SimpleDateFormat formate = getDateParser(formatStr);
	      formate.setLenient(false);
	      date = formate.parse(str);
	    } catch (ParseException e) {
	      date = null;
	    }
	    return date;
	  }

	  public static java.util.Date getCurrentDate()
	  {
	    return new java.util.Date();
	  }

	  public static Integer getCurrentYear() {
	    return Integer.valueOf(dfYyyy.format(getCurrentDate()));
	  }

	  public static Integer getCurrentTopupYear(String topupSeq) {
	    if (("3".equals(topupSeq)) || ("4".equals(topupSeq))) {
	      return Integer.valueOf(getCurrentYear().intValue() - 1);
	    }
	    return getCurrentYear();
	  }

	  public static Integer getCurrentMonth() {
	    return Integer.valueOf(dfMM.format(getCurrentDate()));
	  }

	  public static Integer getCurrentNumDate() {
	    return Integer.valueOf(dfDd.format(getCurrentDate()));
	  }

	  public static java.util.Date getCurrentDateTime()
	  {
	    java.util.Date date = null;
	    try {
	      String datetime = getCurDateTimeStr23WithLink();
	      date = dfyyyyMMddHHMMSSS.parse(datetime);
	    } catch (ParseException e) {
	      e.printStackTrace();
	    }
	    return date;
	  }

	  public static int getCurrentQuarter()
	  {
	    Calendar cal = Calendar.getInstance();
	    int currentMonth = cal.get(2) + 1;
	    int jd = 0;
	    if ((currentMonth >= 1) && (currentMonth <= 3))
	      jd = 1;
	    else if ((currentMonth >= 4) && (currentMonth <= 6))
	      jd = 2;
	    else if ((currentMonth >= 7) && (currentMonth <= 9))
	      jd = 3;
	    else if ((currentMonth >= 10) && (currentMonth <= 12)) {
	      jd = 4;
	    }
	    return jd;
	  }

	  public static String getPreMonth()
	  {
	    Calendar cal = Calendar.getInstance();
	    cal.add(2, -1);
	    return String.valueOf(dfMmmYyyy.format(cal.getTime()));
	  }

	  public static String getFirstDayOfLastMonth()
	  {
	    Calendar cal = Calendar.getInstance();
	    cal.add(2, -1);
	    cal.set(5, 1);
	    return String.valueOf(dfDdMMMYYYY.format(cal.getTime()));
	  }

	  public static String getLastDayOfLastMonth()
	  {
	    Calendar cal = Calendar.getInstance();
	    cal.set(5, 1);
	    cal.add(5, -1);
	    return String.valueOf(dfDdMMMYYYY.format(cal.getTime()));
	  }

	  public static String getLetterDate()
	  {
	    return String.valueOf(dfDdMMMYYYY.format(getCurrentDate()));
	  }

	  public static String getLetterDate(java.util.Date dt)
	  {
	    if (dt != null) {
	      return String.valueOf(dfDdMMMYYYY.format(dt));
	    }
	    return "";
	  }

	  public static String getCurrentDateStr()
	  {
	    java.util.Date now = new java.util.Date();
	    return yyyyMmDd.format(now);
	  }

	  public static String getCurDateTimeStr17()
	  {
	    java.util.Date date = new java.util.Date();
	    return dfyyyyMMddHHMMSSSSS.format(date);
	  }

	  public static String getCurDateTimeStr14()
	  {
	    java.util.Date date = new java.util.Date();
	    return yyyyMMddHHmmss.format(date);
	  }

	  public static BigDecimal getCurrentTimeAsNumber()
	  {
	    String returnStr = getCurDateTimeStr14();
	    return new BigDecimal(returnStr);
	  }

	  public static String getCurDateTimeStr23WithLink()
	  {
	    java.util.Date date = new java.util.Date();
	    return dfyyyyMMddHHMMSSS.format(Long.valueOf(date.getTime()));
	  }

	  public static String getCurDateTimeStr19WithLink()
	  {
	    java.util.Date date = new java.util.Date();
	    return dfyyyyMMddHHMMSS.format(date);
	  }

	  public static String dateToStringFormat(java.util.Date date, String format)
	  {
	    return getDateParser(format).format(date);
	  }

	  public static String putDateToTimeStr19(java.util.Date date)
	  {
	    return dfyyyyMMddHHMMSS.format(date);
	  }

	  public static String putDateToTimeHhMm(java.util.Date date)
	  {
	    return hhmm.format(date);
	  }

	  public static String putDateToTimeStr10(java.util.Date date)
	  {
	    return yyyyMmDd.format(date);
	  }

	  public static String putDateToYmStr(java.util.Date date)
	  {
	    String dateStr = null;
	    if (date != null)
	      dateStr = yyyymm.format(date);
	    return dateStr;
	  }

	  public static String putDateToYmStr()
	  {
	    java.util.Date date = getCurrentDate();
	    return putDateToYmStr(date);
	  }

	  public static String putDateToYmFilePath()
	  {
	    int year = getCurrentYear().intValue();
	    int month = getCurrentMonth().intValue();
	    if (month < 10) {
	      return String.valueOf(year) + Constants.S + "0" + 
	        String.valueOf(month);
	    }
	    return String.valueOf(year) + Constants.S + String.valueOf(month);
	  }

	  public static String putDateToYmdStr(java.util.Date date)
	  {
	    String dateStr = null;
	    if (date != null)
	      dateStr = yyyymmdd.format(date);
	    return dateStr;
	  }

	  public static String dateMmmYyyy(java.util.Date dt)
	  {
	    if (dt != null) {
	      return String.valueOf(dfMmmYyyy.format(dt));
	    }
	    return "";
	  }

	  public static String dateYyyyMmDd(java.util.Date dt)
	  {
	    if (dt != null) {
	      return String.valueOf(yyyyMmDd.format(dt));
	    }
	    return "";
	  }

	  public static String dateYyyyMmDdHhMmSs(java.util.Date dt)
	  {
	    if (dt != null) {
	      return String.valueOf(dfyyyyMMddHHMMSS.format(dt));
	    }
	    return "";
	  }

	  public static String dateYyyyMmDdHhMmSs(long l)
	  {
	    if (l != 0.0D) {
	      return String.valueOf(dfyyyyMMddHHMMSS.format(Long.valueOf(l)));
	    }
	    return "";
	  }

	  public static String dateMmDdYyyy(java.util.Date dt)
	  {
	    if (dt != null) {
	      return String.valueOf(dfMmDdYYYY.format(dt));
	    }
	    return "";
	  }

	  public static java.util.Date putTimeStr23ToDate(String dateTimeStr23)
	  {
	    java.util.Date date = null;
	    try {
	      date = dfyyyyMMddHHMMSSS.parse(dateTimeStr23);
	    } catch (ParseException e) {
	      e.printStackTrace();
	    }
	    return date;
	  }

	  public static java.util.Date putTimeStr19ToDate(String dateTimeStr19)
	  {
	    java.util.Date date = null;
	    try {
	      date = dfyyyyMMddHHMMSS.parse(dateTimeStr19);
	    } catch (ParseException e) {
	      e.printStackTrace();
	    }
	    return date;
	  }

	  public static java.util.Date putTimeStr14ToDate(String dateTimeStr)
	  {
	    java.util.Date date = null;
	    try {
	      date = yyyyMMddHHmmss.parse(dateTimeStr);
	    } catch (ParseException e) {
	      e.printStackTrace();
	    }
	    return date;
	  }

	  public static java.util.Date putTimeStr10ToDate(String dateTimeStr)
	  {
	    java.util.Date date = null;
	    try {
	      date = yyyyMmDd.parse(dateTimeStr);
	    } catch (ParseException e) {
	      e.printStackTrace();
	    }
	    return date;
	  }

	  public static java.util.Date getDateDdMmmYyyy(String ddMmmYyyy)
	  {
	    try
	    {
	      return dfDdMMMYYYY.parse(ddMmmYyyy); } catch (ParseException e) {
	    }
	    return null;
	  }

	  public static java.util.Date stringToDate(String strDate, String srcDateFormat, String dstDateFormat)
	  {
	    java.util.Date rtDate = null;
	    java.util.Date tmpDate = getDateParser(srcDateFormat).parse(strDate, 
	      new ParsePosition(0));
	    String tmpString = null;
	    if (tmpDate != null)
	      tmpString = getDateParser(dstDateFormat).format(tmpDate);
	    if (tmpString != null)
	      rtDate = getDateParser(dstDateFormat).parse(tmpString, 
	        new ParsePosition(0));
	    return rtDate;
	  }

	  public static Timestamp getCurrentTimestamp()
	  {
	    Object obj = TypeCaseHelper.convert(getCurDateTimeStr19WithLink(), 
	      "Timestamp", "yyyy-MM-dd HH:mm:ss");
	    if (obj != null) {
	      return (Timestamp)obj;
	    }
	    return null;
	  }

	  public static Timestamp getTimestampFromString(String time)
	  {
	    Object obj = TypeCaseHelper.convert(time, "Timestamp", 
	      "yyyy-MM-dd HH:mm:ss");
	    if (obj != null) {
	      return (Timestamp)obj;
	    }
	    return null;
	  }

	  public static int age(java.util.Date dob)
	  {
	    Calendar from = Calendar.getInstance();
	    Calendar to = Calendar.getInstance();
	    from.setTime(dob);
	    to.setTime(new java.util.Date());
	    int birthYYYY = from.get(1);
	    int curYYYY = to.get(1);
	    int ageInYears = curYYYY - birthYYYY;
	    if (ageInYears < 0)
	      ageInYears = 0;
	    return ageInYears;
	  }

	  public static int dayBetweenTwoDates(java.util.Date beginDate, java.util.Date endDate)
	  {
	    int pnMark = 1;
	    int days;
	    if ((endDate != null) && (beginDate != null)) {
	      Calendar bCalendar = Calendar.getInstance();
	      Calendar eCalendar = Calendar.getInstance();
	      if (beginDate.after(endDate)) {
	        pnMark = -1;
	        bCalendar.setTime(endDate);
	        eCalendar.setTime(beginDate);
	      } else {
	        bCalendar.setTime(beginDate);
	        eCalendar.setTime(endDate);
	      }
	      int dayBegin = bCalendar.get(6);
	      int dayEnd = eCalendar.get(6);
	      days = dayEnd - dayBegin;
	      int endYear = eCalendar.get(1);
	      if (bCalendar.get(1) != endYear) {
	        bCalendar = (Calendar)bCalendar.clone();
	      }
	      while (bCalendar.get(1) != endYear) {
	        days += bCalendar.getActualMaximum(6);
	        bCalendar.add(1, 1);
	      }
	    } else {
	      days = 0;
	    }
	    return days * pnMark;
	  }

	  public static int getIntervalDays(java.util.Date startDay, java.util.Date endDay)
	  {
	    if (startDay.after(endDay)) {
	      java.util.Date cal = startDay;
	      startDay = endDay;
	      endDay = cal;
	    }

	    long startl = startDay.getTime();
	    long endl = endDay.getTime();
	    long ei = endl - startl;
	    return (int)(ei / 86400000L);
	  }

	  public static java.util.Date dateAfterNDays(java.util.Date dt, int n)
	  {
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(dt);
	    cal.add(5, n);
	    return cal.getTime();
	  }

	  public static java.util.Date dateAfterNMonths(java.util.Date dt, int n)
	  {
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(dt);
	    cal.add(2, n);
	    return cal.getTime();
	  }

	  public static String getBookInfoDate(int days)
	  {
	    java.util.Date now = new java.util.Date();
	    return yyyyMmDd.format(new java.util.Date(now.getTime() - days * 24L * 60L * 
	      60L * 1000L));
	  }

	  public static int getDaysInMonth(int year, int month)
	  {
	    if ((month == 1) || (month == 3) || (month == 5) || (month == 7) || 
	      (month == 8) || (month == 10) || (month == 12))
	      return 31;
	    if ((month == 4) || (month == 6) || (month == 9) || 
	      (month == 11)) {
	      return 30;
	    }
	    if (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0)) {
	      return 29;
	    }
	    return 28;
	  }

	  public static String getWeekDayByDate(String strdate)
	  {
	    String[] dayNames = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", 
	      "星期六" };
	    SimpleDateFormat sdfInput = new SimpleDateFormat("yyyy-MM-dd");
	    Calendar calendar = Calendar.getInstance();
	    java.util.Date date = new java.util.Date();
	    try {
	      date = sdfInput.parse(strdate);
	    } catch (ParseException e) {
	      e.printStackTrace();
	    }
	    calendar.setTime(date);
	    int dayOfWeek = calendar.get(7) - 1;
	    if (dayOfWeek < 0)
	      dayOfWeek = 0;
	    return dayNames[dayOfWeek];
	  }

	  public static int getIntervalDays(java.sql.Date startDate, java.sql.Date endDate)
	  {
	    long startdate = startDate.getTime();
	    long enddate = endDate.getTime();
	    long interval = enddate - startdate;
	    int intervalday = (int)(interval / 86400000L);
	    return intervalday;
	  }

	  public static int getIntervalMonths(java.sql.Date startDate, java.sql.Date endDate)
	  {
	    Calendar startCal = Calendar.getInstance();
	    startCal.setTime(startDate);
	    Calendar endCal = Calendar.getInstance();
	    endCal.setTime(endDate);
	    int startDateM = 2;
	    int startDateY = 1;
	    int enddatem = 2;
	    int enddatey = 1;
	    int interval = enddatey * 12 + enddatem - (
	      startDateY * 12 + startDateM);
	    return interval;
	  }

	  public static boolean isValidTimeHHMM(String timeStr)
	  {
	    if (ValidateUtil.isNotEmpty(timeStr)) {
	      Pattern p = Pattern.compile("^(([0-1][0-9])|(2[0-3])):([0-5][0-9])$");
	      Matcher m = p.matcher(timeStr);
	      return m.matches();
	    }
	    return false;
	  }

	  public static String change(String str, String formatStr)
	  {
	    return change(formatStringToDate(str, formatStr));
	  }

	  public static String change(java.util.Date srcDate)
	  {
	    DateFormat f = new SimpleDateFormat("yyyy-MM-dd");
	    String s;
	    try
	    {
	    	s = f.format(srcDate);
	    }
	    catch (NullPointerException e)
	    {
	      return "";
	    }
	    String[] dFirst = s.split("-");
	    StringBuffer sMonth = new StringBuffer();
	    StringBuffer sDate = new StringBuffer();
	    StringBuffer dChinese = new StringBuffer();
	    String year = dFirst[0];
	    String month = dFirst[1];
	    String date = dFirst[2];
	    String fmonth = "month";
	    String fdate = "date";

	    for (int j = 0; j < year.length(); j++) {
	      dChinese.append(changeCount(year.charAt(j)));
	    }

	    sMonth = formatDate(month, fmonth);
	    sDate = formatDate(date, fdate);

	    dChinese.append("年").append(sMonth).append("月").append(sDate).append(
	      "日");

	    return dChinese.toString();
	  }

	  public static StringBuffer formatDate(String s, String flag)
	  {
	    StringBuffer sDate = new StringBuffer();
	    int date = Integer.parseInt(s);
	    if ((date < 10) && (date > 0)) {
	      if (s.length() == 2)
	        sDate.append(changeCount(s.charAt(1)));
	      else
	        sDate.append(changeCount(s.charAt(0)));
	    }
	    else if (date == 10) {
	      sDate.append("十");
	    } else if ((date > 10) && (date < 20)) {
	      sDate.append("十");
	      sDate.append(changeCount(s.charAt(1)));
	    }
	    if (flag.equals("date")) {
	      if (date == 20) {
	        sDate.append("二十");
	      } else if ((date > 20) && (date < 30)) {
	        sDate.append("二十");
	        sDate.append(changeCount(s.charAt(1)));
	      } else if (date == 30) {
	        sDate.append("三十");
	      } else if (date == 31) {
	        sDate.append("三十一");
	      }
	    }
	    return sDate;
	  }

	  public static String getFixedMonth(String month)
	  {
	    String ret = "";
	    if ((month != null) && (!"".equals(month))) {
	      if (Integer.parseInt(month) < 10)
	        ret = "0" + month;
	      else {
	        ret = month;
	      }
	    }
	    return ret;
	  }

	  public static String getFixedDay(String day)
	  {
	    String ret = "";
	    if ((day != null) && (!"".equals(day))) {
	      if (Integer.parseInt(day) < 10)
	        ret = "0" + day;
	      else {
	        ret = day;
	      }
	    }
	    return ret;
	  }

	  public static char changeCount(char c)
	  {
	    if (c == '0')
	      c = '〇';
	    else if (c == '1')
	      c = '一';
	    else if (c == '2')
	      c = '二';
	    else if (c == '3')
	      c = '三';
	    else if (c == '4')
	      c = '四';
	    else if (c == '5')
	      c = '五';
	    else if (c == '6')
	      c = '六';
	    else if (c == '7')
	      c = '七';
	    else if (c == '8')
	      c = '八';
	    else if (c == '9') {
	      c = '九';
	    }
	    return c;
	  }

	  public static void main(String[] args) {
	    System.getProperties().list(System.out);
	  }
}
