package com.common.framework.init;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletContext;

import org.aspectj.weaver.patterns.ThisOrTargetAnnotationPointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.common.constants.Constants;
import com.common.util.resource.PropertiesFactory;
import com.common.util.resource.PropertiesFile;
import com.common.util.resource.PropertiesHelper;

public class SystemStartup {
	
	  private static Logger log;
	  private static SystemStartup instance;
	  private static final long UNLOCKTIME = 180000L;

	  public static SystemStartup getInstance(){
		    if (instance == null) {
		      instance = new SystemStartup();
		      log = LoggerFactory.getLogger(SystemStartup.class);
		    }
		    return instance;
	  }
	  
	  public void startUp(final ServletContext event){
		    log.info("*******************************************************");
		    log.info("工程路径：" + Constants.getRootPath());
		    initDbType();
		    long start = System.currentTimeMillis();
		    boolean success = true;
		    
		    new systemThread().start();
		    
		    long timeSec = (System.currentTimeMillis() - start) / 1000L;
		    String time = "";
		    if (timeSec / 60L > 0L) {
		      time = timeSec / 60L + "分 ";
		    }
		    time = time + timeSec % 60L + "秒 ";
		    if (success)
		      log.info(PropertiesFactory.getMsgValue("webapp.title") + 
		        " 启动成功");
		    else {
		      log.error(PropertiesFactory.getMsgValue("webapp.title") + 
		        " 启动失败");
		    }
		    log.info("启动总耗时: " + time);
		    log.info("*******************************************************");
	    
	  }
	  
	  //初始化数据库连接
	  private void initDbType(){
			  InputStream in =getClass().getResourceAsStream("/other/init.properties");
			  Properties   props   =  new  Properties();
			  try {
				props.load(in);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			  if(props.isEmpty()){
				  System.out.println("空");
			  }else{
				  String URL=props.get("css.styleUrl").toString();
			  }
	  }
	  
	  //系统线程创建
	  class systemThread extends Thread {
			  public void run() {
		            while (true) {
			              try {
				                sleep(30000L);
				                log.info("***************线程在跑动*********************");
			              } catch (InterruptedException e) {
				                e.printStackTrace();
			              }
		            }
			  }
	  }

}
