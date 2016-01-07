package com.common.util;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

import com.common.util.data.date.DateUtil;

public class CodeUtil {
	  private static Long lastVersionTime;
	  public static String[] CODE = { "a", "b", "c", "d", "e", "f", "g", "h", 
	    "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", 
	    "v", "w", "x", "y", "z" };

	  public static String getId(String id, int length)
	  {
	    String lengthTemp = "";
	    for (int i = 0; i < length - 1; i++) {
	      lengthTemp = lengthTemp + "0";
	    }

	    String str = "";
	    if ((id == null) || (id.equals(""))) {
	      str = lengthTemp + "1";
	    } else {
	      str = id;
	      int tempId = Integer.parseInt(str);
	      tempId++;
	      str = Integer.toString(tempId);

	      str = lengthTemp.substring(0, length - str.length()) + str;
	    }
	    return str;
	  }

	  public static final String getRandom(int pLength)
	  {
	    String random = "";
	    for (int i = 0; i < pLength; i++) {
	      int t = (int)(Math.random() * 10.0D);
	      random = random + String.valueOf(t);
	    }
	    return random;
	  }

	  public static final String getTimeSequence(int pLength)
	  {
	    int tmpLength = pLength;

	    if (tmpLength < 17) {
	      tmpLength = 17;
	    }
	    if (tmpLength > 20) {
	      tmpLength = 20;
	    }

	    String Sequence = "";

	    Date temp = new Date();
	    Sequence = DateUtil.dfyyyyMMddHHMMSSSSS.format(temp);
	    Sequence = Sequence + getRandom(tmpLength - 17);

	    return Sequence;
	  }

	  public static final synchronized String getTimeSequence()
	  {
	    return getTimeSequence(20);
	  }

	  public static synchronized String getTimeVersion()
	  {
	    if (lastVersionTime == null) {
	      lastVersionTime = Long.valueOf(Long.parseLong(DateUtil.dfyyyyMMddHHMMSSSSS
	        .format(new Date())));
	    }
	    Long nowTime = Long.valueOf(Long.parseLong(DateUtil.dfyyyyMMddHHMMSSSSS
	      .format(new Date())));

	    if (nowTime.longValue() <= lastVersionTime.longValue()) {
	      nowTime = Long.valueOf(lastVersionTime.longValue() + 1L);
	    }
	    String version = nowTime + getRandom(3);
	    lastVersionTime = nowTime;
	    return version;
	  }

	  public static BigDecimal getRandom(int start, int end)
	  {
	    return new BigDecimal(start + Math.random() * end);
	  }

	  public static String getRandomCode(int length)
	  {
	    StringBuffer random = new StringBuffer("");
	    Random r = new Random();
	    int len = CODE.length;
	    for (int i = 0; i < length; i++) {
	      int num = r.nextInt(len);
	      if (num == len)
	        num = len - 1;
	      else if (num < 0) {
	        num = 0;
	      }
	      random.append(CODE[num]);
	    }
	    return random.toString();
	  }

	  public static String getGUID()
	  {
	    UUID guid = UUID.randomUUID();
	    return guid.toString();
	  }

	  public static void main(String[] args) {
	    System.out.println(getTimeSequence());
	  }
	}