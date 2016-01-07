package com.common.base.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.common.constants.SessionConstants;
import com.common.framework.listener.SessionContainer;

public class WebUtils {
	
	public static SessionContainer getSessionContainer(HttpServletRequest request) throws Exception{
		    try{
		          SessionContainer sessionContainer = (SessionContainer)request.getSession().getAttribute(
		          SessionConstants.SESSION_CONTAINER);
			      if (sessionContainer == null) {
			          sessionContainer = new SessionContainer();
			          HttpSession session = request.getSession(false);
			          session.setAttribute(SessionConstants.SESSION_CONTAINER, 
			          sessionContainer);
			      }
			      return sessionContainer;
		    } catch (Exception e) {throw e; }
	}
}
