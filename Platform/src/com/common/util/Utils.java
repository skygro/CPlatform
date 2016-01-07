package com.common.util;

import java.beans.IntrospectionException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import ognl.OgnlException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.util.WebUtils;

import com.common.util.data.map.BaseDmp;
import com.common.util.data.map.Dmp;
import com.opensymphony.xwork2.ognl.OgnlUtil;

public class Utils {
	
	  private static final Log logger = LogFactory.getLog(Utils.class);
	  
	  public static Dmp getParamAsDmp(HttpServletRequest request){
			    Dmp dmp =  new  BaseDmp();
			    Map map = request.getParameterMap();
			    Iterator keyIterator = map.keySet().iterator();
			    while (keyIterator.hasNext()) {
				      String key = (String)keyIterator.next();
				      String value = ((String[])map.get(key))[0];
				      dmp.put(key, value);
			    }
			    return dmp;
	  }

	  public static void setAttributeFromDmp(Object obj, HttpServletRequest request) {
			    Map p = new HashMap();
			    
			    if ((obj instanceof Map))
				      p = (Map)obj;
			    else {
				      try {
				    	  OgnlUtil ognlUtil = new OgnlUtil();
				        p = ognlUtil.getBeanMap(obj);
				      }
				      catch (IntrospectionException e) {logger.error(e, e);}
				      catch (OgnlException e) { logger.error(e, e); }
			    }
			    
			    WebUtils.exposeRequestAttributes(request, p);
	  }

}
