package com.common.util.resource;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class PropertiesHelper {
	
	  private static Log log = LogFactory.getLog(PropertiesHelper.class);
	  
	  private Properties properties;
	  
	  public PropertiesHelper(Properties pro) {
		    this.properties = pro;
	  }
	  
	  public PropertiesHelper(InputStream is) throws Exception {
			    try{
			      this.properties = new Properties();
			      //加载输入流
			      this.properties.load(is);
			    } catch (FileNotFoundException e) {
			      log.error("\n出现错误了， 错误代码如下：\n未找到属性资源文件!");
			      e.printStackTrace();
			      throw e;
			    } catch (Exception e) {
			      log.error("\n出现错误了， 错误代码如下：\n读取属性资源文件发生未知错误!");
			      e.printStackTrace();
			      throw e;
			    } finally {
			      is.close();
			    }
		 }
	  
	  public void store(String pFileName) {
		    FileOutputStream outStream = null;
		    try {
		      File file = new File(pFileName + ".properties");
		      outStream = new FileOutputStream(file);
		      this.properties.store(outStream, "#属性文集");
		    } catch (Exception e) {
		      log.error("\n出现错误了， 错误代码如下：\n保存属性文件出错.");
		      e.printStackTrace();
		    }finally{
		        try {
					outStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		    }
	  }
	  
	  //获取键值
	  public String getValue(String key) {
	    return this.properties.getProperty(key);
	  }
	  
	  public String getValue(String key, String defaultValue){
	    return this.properties.getProperty(key, defaultValue);
	  }
	  
	  //设置键值
	  public void setProperty(String key, String value) {
	    this.properties.setProperty(key, value);
	  }
	  
	  public void removeProperty(String key){
	    this.properties.remove(key);
	  }
	  
	  //获取当前文件对象
	  public Properties getObjProperties() {
		    return this.properties;
	 }

}
