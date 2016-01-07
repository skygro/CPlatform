package com.common.constants;

public final class Constants {
	  public static boolean checkDblink = false;
	  public static final String LOGIN_INFO = "com.zx.cas.client.filter.user";
	  public static final String VALIDATE_CODE_KEY = "validate_code";
	  public static final String S = System.getProperty("file.separator");
	  private static String ROOT_PATH;
	  public static final String Exception_Head = "\n出现错误了， 错误代码如下：\n";
	  public static final String DB_TYPE_PRO = "zxDao.db";
	  public static String[] VALIDATE;
	  public static final String ENTER = "\n";
	  public static final Boolean TRUE = new Boolean(true);

	  public static final Boolean FALSE = new Boolean(false);
	  public static final String SUCCESS = "1";
	  public static final String FAILURE = "0";
	  public static final String ERR_MSG_QUERYFORPAGE_STRING = "您正在使用分页查询,但是你传递的分页参数缺失!如果不需要分页操作,您可以尝试使用普通查询:queryForList()方法";

	  public static void setRootPath(String path)
	  {
	    ROOT_PATH = path;
	  }

	  public static String getRootPath() {
	    return ROOT_PATH;
	  }

	  public static String getClassPath()
	  {
	    return getRootPath() + "/WEB-INF/classes";
	  }

	  public static String[] getVALIDATE()
	  {
//	    if (VALIDATE == null) {
//	      VALIDATE = PropertiesFactory.getPropertiesHelper(
//	        "model").getValue("filter.str").split("&");
//	    }
	    return VALIDATE;
	  }

	  public static void setVALIDATE(String[] vALIDATE)
	  {
	    VALIDATE = vALIDATE;
	  }
}
