package com.common.util;

import java.util.Date;
import java.util.UUID;

public class RandomString {

	private static Long lastVersionTime;

	private static java.text.SimpleDateFormat formater = new java.text.SimpleDateFormat(
			"yyyyMMddHHmmssSSS");

	/**
	 * 按给定长度自动生成主键ID
	 */
	public static String getId(String id, int length) {
		String lengthTemp = "";
		for (int i = 0; i < length - 1; i++) {
			lengthTemp += "0";
		}

		String str = "";
		if (id == null || id.equals("")) {
			str = lengthTemp + "1";
		} else {
			str = id;
			int tempId = Integer.parseInt(str);
			tempId++;
			str = Integer.toString(tempId);

			str = lengthTemp.substring(0, (length - str.length())) + str;
		}
		return str;
	}

	/**
	 * 根据参数产生定长的随机数
	 * 
	 * @param pLength
	 * @return
	 */
	public static final String getRandom(int pLength) {
		String random = "";
		for (int i = 0; i < pLength; i++) {
			int t = (int) (java.lang.Math.random() * 10);
			random += String.valueOf(t);
		}
		return random;
	}

	/**
	 * 产生定长的时间随机数 精确到毫秒+变长随机数
	 * 
	 * @param pLength
	 *            随机数的长度 17到20之间
	 * @return
	 */
	public static final String getTimeSequence(int pLength) {
		int tmpLength = pLength;

		if (tmpLength < 17) {
			tmpLength = 17;
		}
		if (tmpLength > 20) {
			tmpLength = 20;
		}

		String Sequence = "";

		java.util.Date temp = new java.util.Date();
		Sequence = formater.format(temp);
		Sequence += getRandom(tmpLength - 17);

		return Sequence;

	}

	/**
	 * 取得默认的时间随机数，默认20位长度
	 */
	public synchronized static final String getTimeSequence() {
		return getTimeSequence(20);
	}

	/**
	 * 取得递增的版本号 默认20位长度
	 * 
	 * @return 版本号
	 */
	public synchronized static String getTimeVersion() {
		// 首次进行
		if (lastVersionTime == null) {
			lastVersionTime = Long.parseLong(formater.format(new Date()));
		}

		Long nowTime = Long.parseLong(formater.format(new Date()));

		// 如果发生并发或者是首次
		if (nowTime <= lastVersionTime) {
			nowTime = lastVersionTime + 1;
		}

		String version = nowTime + getRandom(3);
		lastVersionTime = nowTime;
		return version;
	}

	/**
	 * 获取GUID
	 * 
	 * @return
	 */
	public static String getGUID() {
		UUID guid = UUID.randomUUID();
		return guid.toString();
	}

	public static void main(String[] args) {
		System.out.println(getTimeSequence());
		// System.out.println(getId("111", 4));
		// for (int i = 0; i < 10; i++) {
		// System.out.println(getTimeVersion());
		// }
	}
}
