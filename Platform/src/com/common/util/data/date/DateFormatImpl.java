package com.common.util.data.date;

import java.text.SimpleDateFormat;

/**
 * 日期格式化接口
 * @author lhw
 *
 */
public abstract interface DateFormatImpl {
	
	public static final String FORMAT_DateTime = "yyyy-MM-dd HH:mm:ss";
	  public static final String FORMAT_DateTime_12 = "yyyy-MM-dd hh:mm:ss";
	  public static final String FORMAT_Date = "yyyy-MM-dd";
	  public static final String FORMAT_Time = "HH:mm:ss";
	  public static final String FORMAT_Time_12 = "hh:mm:ss";
	  public static final String DF_YYYY = "yyyy";
	  public static final String DF_MM = "MM";
	  public static final String DF_DD = "dd";
	  public static final String DF_MMMYYYY = "MMM yyyy";
	  public static final String DF_DDMMMYYYY = "dd MMM yyyy";
	  public static final String DF_MMDDYYYY = "MM/dd/yyyy";
	  public static final String DF_YYYY_MM_DD_HHMMSS = "yyyy-MM-dd HH:mm:ss";
	  public static final String DF_YYYYMMDDHHMMSSS = "yyyy-MM-dd HH:mm:ss:SSS";
	  public static final String DF_YYYY_MM_DD = "yyyy-MM-dd";
	  public static final String DF_HHMM = "HH:mm";
	  public static final String DF_YYYYMMDD = "yyyyMMdd";
	  public static final String DF_YYYYMM = "yyyyMM";
	  public static final String DF_YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
	  public static final String DF_YYYYMMDDHHMMSSSSS = "yyyyMMddHHmmssSSS";
	  public static final SimpleDateFormat dfYyyy = new SimpleDateFormat("yyyy");

	  public static final SimpleDateFormat dfMM = new SimpleDateFormat("MM");

	  public static final SimpleDateFormat dfDd = new SimpleDateFormat("dd");

	  public static final SimpleDateFormat dfMmmYyyy = new SimpleDateFormat(
	    "MMM yyyy");

	  public static final SimpleDateFormat dfDdMMMYYYY = new SimpleDateFormat(
	    "dd MMM yyyy");

	  public static final SimpleDateFormat dfMmDdYYYY = new SimpleDateFormat(
	    "MM/dd/yyyy");

	  public static final SimpleDateFormat dfyyyyMMddHHMMSS = new SimpleDateFormat(
	    "yyyy-MM-dd HH:mm:ss");

	  public static final SimpleDateFormat dfyyyyMMddHHMMSSS = new SimpleDateFormat(
	    "yyyy-MM-dd HH:mm:ss:SSS");

	  public static final SimpleDateFormat yyyyMmDd = new SimpleDateFormat(
	    "yyyy-MM-dd");

	  public static final SimpleDateFormat hhmm = new SimpleDateFormat("HH:mm");

	  public static final SimpleDateFormat yyyymmdd = new SimpleDateFormat(
	    "yyyyMMdd");

	  public static final SimpleDateFormat yyyymm = new SimpleDateFormat(
	    "yyyyMM");

	  public static final SimpleDateFormat yyyyMMddHHmmss = new SimpleDateFormat(
	    "yyyyMMddHHmmss");

	  public static final SimpleDateFormat dfyyyyMMddHHMMSSSSS = new SimpleDateFormat(
	    "yyyyMMddHHmmssSSS");
	  public static final String twentyFourHourRegExp = "^(([0-1][0-9])|(2[0-3])):([0-5][0-9])$";

}
