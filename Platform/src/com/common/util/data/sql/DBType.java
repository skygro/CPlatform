package com.common.util.data.sql;

public abstract class DBType {
	
	public abstract String getText();
	
	public abstract String getDriver();
	
	public abstract String getDburl(String serverIp, String port, String dbName);
	
	  public static DBType getSysDBType(String systype) {
		    if (systype.equalsIgnoreCase(ORACEL.getInstance().getText())){
		      return ORACEL.getInstance();
		    }
		    return null;
		  }

}
