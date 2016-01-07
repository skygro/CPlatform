package com.common.util.security;

import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

/**
 * 读取配置文件
 * @author lhw
 *
 */
public class JdbcEncryptFromPropertiesFile extends PropertyPlaceholderConfigurer{
	
	 protected void processProperties(ConfigurableListableBeanFactory beanFactory, Properties props) throws BeansException {  
	        String password = props.getProperty("datasource.password");  
	  
	        if (password != null) {//**解密过程**  
	        	DesUtil des = new DesUtil(); // 实例化一个对像  
	            String strDes = des.decryptBasedDes(password);// 解密datasource.password属性值，重新明文赋值   
	            props.setProperty("datasource.password", strDes);//赋值  
	        }  
	        super.processProperties(beanFactory, props);//调用父方法  
	  
	    }  
}
