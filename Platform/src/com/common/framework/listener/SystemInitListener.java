package com.common.framework.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.common.constants.Constants;
import com.common.framework.init.SystemStartup;
import com.common.framework.init.log.LoggerConfig;
import com.common.util.resource.PropertiesFactory;
import com.common.util.resource.PropertiesFile;
import com.common.util.resource.PropertiesHelper;

public class SystemInitListener implements ServletContextListener {
	
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void contextInitialized(ServletContextEvent arg0) {
	    String contextPath = arg0.getServletContext().getRealPath("/");
	    Constants.setRootPath(contextPath);
	    System.out.println(" 集成服务综合开发云平台开始启动...");
	    if (LoggerConfig.initLogger()) {
	    	SystemStartup.getInstance().startUp(arg0.getServletContext());
	    }
	}
	
}
