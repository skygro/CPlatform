package com.common.framework.init.log;

import java.io.InputStream;

import org.apache.log4j.LogManager;
import org.apache.log4j.xml.DOMConfigurator;
import org.springframework.util.Log4jConfigurer;

public class LoggerConfig {
	
	public static boolean initLogger(){
		boolean b = true;
		try{
			 InputStream is = LoggerConfig.class.getClassLoader() .getResourceAsStream("other/log4j.xml");
			 if (is != null) {
			        DOMConfigurator configurator = new DOMConfigurator();
			        configurator.doConfigure(is, LogManager.getLoggerRepository());
			      } else {
			        b = false;
			      }	
		}catch (Exception e) {
		      e.printStackTrace();
		      b = false;
		 }
	    if (!b) {
	        System.out.println("没找到Log4j路径，不能实例化log4j类");
	      }
	      return b;
	}
	
	  public static void shutDownLog() {
		    Log4jConfigurer.shutdownLogging();
	   }

}
