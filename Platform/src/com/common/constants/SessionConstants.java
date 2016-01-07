package com.common.constants;

import com.common.util.CodeUtil;

public class SessionConstants {
	
	public static String SESSION_CONTAINER = "SessionContainer" + getInitNumber();
	
	private static String INITNUMBER = null;
	
	public static final String SESSION_SYSTEMID = "session_systemid" +   getInitNumber();

	private static String getInitNumber() {
		    if (INITNUMBER == null) {
			      INITNUMBER = CodeUtil.getRandom(3);
		    }
		    return INITNUMBER;
	  }
}
