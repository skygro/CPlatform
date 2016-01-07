package com.common.util.data.sql;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;

public class DataBaseUtil {
	
	  public static Connection getConnection(String dbtype, String url, String user, String psw){
	    try {
	    	  DBType temp = DBType.getSysDBType(dbtype);
	          Driver driver =  (Driver)Class.forName(temp.getDriver()).newInstance();
	          DriverManager.registerDriver(driver);
	          Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", user, psw);
	          if (conn != null) return conn;
	    }catch (Exception e) {
	    	e.printStackTrace();
	    }
	    return null;
	  }

}
