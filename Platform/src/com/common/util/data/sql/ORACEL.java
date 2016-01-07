package com.common.util.data.sql;

public class ORACEL extends DBType{
	
	  private static ORACEL instance;

	  public static ORACEL getInstance(){
	    if (instance == null) {
	      instance = new ORACEL();
	    }
	    return instance;
	  }

	@Override
	  public String getDriver(){
	    return "oracle.jdbc.driver.OracleDriver";
	  }

	@Override
	  public String getText() {
	    return "ORACLE";
	  }

	@Override
	public String getDburl(String serverIp, String port, String dbName) {
		return "jdbc:oracle:thin:@" + serverIp + ":" + port + ":" + dbName;
	}

}
