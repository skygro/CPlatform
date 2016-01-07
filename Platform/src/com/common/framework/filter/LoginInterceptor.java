package com.common.framework.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.StrutsStatics;

import com.common.constants.SessionConstants;
import com.common.framework.listener.SessionContainer;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class LoginInterceptor extends AbstractInterceptor{

	private static final long serialVersionUID = -1882312204838489028L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		ActionContext actionContext = invocation.getInvocationContext();
	    HttpServletRequest request = (HttpServletRequest)actionContext .get("com.opensymphony.xwork2.dispatcher.HttpServletRequest");
	    
//	    String serPath = request.getServletPath();
//	    if ((serPath.lastIndexOf(".html") <= -1) && (serPath.indexOf("/login/system_") <= -1) && 
//	    	      (serPath.indexOf("/system/") <= -1) &&  (serPath .indexOf("/login/") <= -1)) {
	    	 HttpSession session = request.getSession();
	    	 SessionContainer sessionContainer = (SessionContainer) session.getAttribute(SessionConstants.SESSION_CONTAINER);
	    	 if(sessionContainer == null){
	    		 session.invalidate();
	    	     return "loginIn";
	    	 }else{
	    		 return invocation.invoke();
	    	 }
//	    }
//				return null;
	}

}
