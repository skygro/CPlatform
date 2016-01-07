package com.common.util.bean;

import java.lang.reflect.Field;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

import com.common.util.data.map.Dmp;

/**
 * 操作JavaBean的工具类
 * @author lhw
 *
 */
public class BeansUtil extends BeanUtils{
	private static Logger log = Logger.getLogger(BeansUtil.class);

	  public static Field getDeclaredField(Object object, String propertyName)
	    throws NoSuchFieldException
	  {
	    return getDeclaredField(object.getClass(), propertyName);
	  }

	  public static Field getDeclaredField(Class clazz, String propertyName)
	    throws NoSuchFieldException
	  {
	    for (Class superClass = clazz; superClass != Object.class; ) {
	      try
	      {
	        return superClass.getDeclaredField(propertyName);
	      }
	      catch (NoSuchFieldException localNoSuchFieldException)
	      {	
	        superClass = superClass
	          .getSuperclass();
	      }

	    }

	    throw new NoSuchFieldException("No such field: " + clazz.getName() + 
	      '.' + propertyName);
	  }

	  public static Object forceGetProperty(Object object, String propertyName)
	    throws NoSuchFieldException
	  {
	    Field field = getDeclaredField(object, propertyName);
	    boolean accessible = field.isAccessible();
	    field.setAccessible(true);

	    Object result = null;
	    try {
	      result = field.get(object);
	    } catch (IllegalAccessException e) {
	      e.printStackTrace();
	    }
	    field.setAccessible(accessible);
	    return result;
	  }

	  public static void forceSetProperty(Object object, String propertyName, Object value)
	    throws NoSuchFieldException
	  {
	    try
	    {
	      Field field = getDeclaredField(object, propertyName);
	      boolean accessible = field.isAccessible();
	      field.setAccessible(true);
	      field.set(object, value);
	      field.setAccessible(accessible);
	    } catch (Exception e) {
	      throw new RuntimeException(e);
	    }
	  }

	  public static void copyPropBetweenBeans(Object destObj, Object origObj)
	  {
	    try
	    {
	      copyProperties(destObj, origObj);
	    } catch (Exception e) {
	      log.error("==开发人员请注意:==\n JavaBean之间的属性值拷贝发生错误啦!\n详细错误信息如下:");
	      e.printStackTrace();
	    }
	  }

	  public static void copyPropFromBean2LMap(Object pFromObj, Dmp pToDmp)
	  {
	    if (pToDmp != null)
	      try {
	        pToDmp.putAll(describe(pFromObj));
	        pToDmp.remove("class");
	      } catch (Exception e) {
	        log.error("==开发人员请注意:==\n 将JavaBean属性值拷贝到Dmp对象发生错误啦!\n详细错误信息如下:");
	        e.printStackTrace();
	      }
	  }
}
