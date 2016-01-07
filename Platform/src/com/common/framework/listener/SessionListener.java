package com.common.framework.listener;

import java.util.Hashtable;
import java.util.Iterator;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.common.base.pojo.UserInfo;
import com.common.constants.SessionConstants;
import com.common.util.data.date.DateUtil;

public class SessionListener implements HttpSessionListener{

	  private static Logger log = LoggerFactory.getLogger(SessionListener.class);

	  private static Hashtable ht = new Hashtable();

	  public void sessionCreated(HttpSessionEvent event) { }

	  public void sessionDestroyed(HttpSessionEvent event)
	  {
	    try
	    {
	      HttpSession session = event.getSession();
	        ht.remove(session.getId());
	        log.info("销毁了一个Session连接:" + session.getId() + " " + 
	          DateUtil.getCurDateTimeStr19WithLink());
	    } catch (Exception e) {
	      log.error("销毁Session连接出错\n" + e.getMessage());
	      e.printStackTrace();
	    }
	  }

	  public static void addSession(HttpSession session,SessionContainer sessionContainer)
	  {
	    ht.put(session.getId(), session);
	    session.setAttribute(SessionConstants.SESSION_CONTAINER, sessionContainer);
	  }

	  public static Iterator getSessions()
	  {
	    return ht.values().iterator();
	  }

	  public static HttpSession getSessionByID(String sessionId)
	  {
	    return (HttpSession)ht.get(sessionId);
	  }
}
