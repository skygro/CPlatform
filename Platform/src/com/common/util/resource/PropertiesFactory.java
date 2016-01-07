package com.common.util.resource;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PropertiesFactory {
	
	  private static Logger log = LoggerFactory.getLogger(PropertiesFactory.class);
	  
	  private static Map<String, PropertiesHelper> container = new HashMap();
	  
	  //类被加载时便执行静态代码块
	  static {
		  
		  //线程上下文类型加载器
		  ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		  
		  //当前上下文环境下的类加载器
		  if(classLoader == null){
				  classLoader = PropertiesFactory.class.getClassLoader();
		  }
		  
		  try{
			      InputStream is = classLoader .getResourceAsStream("other/init.properties");
			      PropertiesHelper ph = new PropertiesHelper(is);
			      container.put("init", ph);
		    } catch (Exception e1) {
			      log.error("\n出现错误了， 错误代码如下：\n加载属性文件model.properties出错!");
		    }
		  try
		    {
		      InputStream is = classLoader .getResourceAsStream("other/jdbc.properties");
		      PropertiesHelper ph = new PropertiesHelper(is);
		      container.put("jdbc", ph);
		    } catch (Exception e1) {
		      log.error("\n出现错误了， 错误代码如下：\n加载属性文件jdbc.properties出错!");
		    }
		  try {
				      InputStream is = classLoader .getResourceAsStream("other/message.properties");
				      PropertiesHelper ph = new PropertiesHelper(is);
				      container.put("message", ph);
			    } catch (Exception e1) {
				      log.error("\n出现错误了， 错误代码如下：\n加载属性文件message.properties出错!");
			    }
		  createPATHFile(PropertiesFile.INIT_PATH);
	  }
	  
	  //创建数据库初始化配置文件
	private static void createPATHFile(String initPath) {
		    try{
			      File file = new File(initPath);
			      if (!file.exists()){
				        File dfile = new File(PropertiesFile.CLASS_PATH + "/resource");
				        dfile.mkdirs();
				        file.createNewFile();
				        PropertiesHelper ph = new PropertiesHelper(new Properties());
				        container.put(initPath, ph);
			      }else{
			    	  InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("resource/init");
			    	  PropertiesHelper ph = new PropertiesHelper(is);
			    	  container.put(initPath, ph);
			      }
		    } catch (Exception e1) {
			      log.error("\n出现错误了， 错误代码如下：\n加载属性文件" + initPath + "出错!");
		    }
	  }
	  
	  public static void setIntPropertiesHelper(Properties pro, String type) {
		    try {
		      File file = new File(type);
		      if (!file.exists()) {
		        file.createNewFile();
		      }
		      FileOutputStream os = new FileOutputStream(file);
		      pro.store(os, type);
		      os.close();
		    } catch (Exception e) {
		      e.printStackTrace();
		    }
		    PropertiesHelper ph = new PropertiesHelper(pro);
		    container.put(type, ph);
		  }
	  
	  public static PropertiesHelper getPropertiesHelper(String pFile) {
	    PropertiesHelper ph = (PropertiesHelper)container.get(pFile);
	    return ph;
	  }
	  
	  public static String getMsgValue(String key) {
		    return getPropertiesHelper("message").getValue(key);
		  }

}
