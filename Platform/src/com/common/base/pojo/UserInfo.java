package com.common.base.pojo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserInfo {

	  private static final long serialVersionUID = 6264644362076741414L;
	  private String userId;
	  private String username;
	  private String account;
	  private String password;
	  private String sex;
	  private String entityId;
	  private String lock;
	  private String sessionID;
	  private String sessionCreatedTime;
	  private String loginIP;
	  private String explorer;
	  private String roleTpye;
	  private String roleId;
	  private String orgId;

	  public UserInfo(){ }

	  public String getUsername() {
	    return this.username;
	  }

	  public void setUsername(String username) {
	    this.username = username;
	  }

	  public String getAccount() {
	    return this.account;
	  }

	  public void setAccount(String account) {
	    this.account = account;
	  }

	  public String getPassword() {
	    return this.password;
	  }

	  public void setPassword(String password) {
	    this.password = password;
	  }

	  public String getSex() {
	    return this.sex;
	  }

	  public void setSex(String sex) {
	    this.sex = sex;
	  }

	  public String getLock() {
	    return this.lock;
	  }

	  public void setLock(String lock) {
	    this.lock = lock;
	  }

	  public String getSessionID() {
	    return this.sessionID;
	  }

	  public void setSessionID(String sessionID) {
	    this.sessionID = sessionID;
	  }

	  public String getSessionCreatedTime() {
	    return this.sessionCreatedTime;
	  }

	  public void setSessionCreatedTime(String sessionCreatedTime) {
	    this.sessionCreatedTime = sessionCreatedTime;
	  }

	  public String getLoginIP() {
	    return this.loginIP;
	  }

	  public void setLoginIP(String loginIP) {
	    this.loginIP = loginIP;
	  }

	  public String getExplorer() {
	    return this.explorer;
	  }

	  public void setExplorer(String explorer) {
	    this.explorer = explorer;
	  }

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getEntityId() {
		return entityId;
	}

	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}

	public String getRoleTpye() {
		return roleTpye;
	}

	public void setRoleTpye(String roleTpye) {
		this.roleTpye = roleTpye;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
}
