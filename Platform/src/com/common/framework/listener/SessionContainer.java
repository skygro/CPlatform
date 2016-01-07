package com.common.framework.listener;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

import com.common.base.pojo.UserInfo;

public class SessionContainer
implements HttpSessionBindingListener
{
private UserInfo userInfo;

public void valueBound(HttpSessionBindingEvent arg0){}

public void valueUnbound(HttpSessionBindingEvent arg0){}

public UserInfo getUserInfo() {
  return this.userInfo;
}

public void setUserInfo(UserInfo userInfo) {
  this.userInfo = userInfo;
}

}