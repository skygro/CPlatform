package com.system.action;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.common.base.action.BaseAction;
import com.common.base.pojo.LockUser;
import com.common.base.pojo.UserInfo;
import com.common.framework.listener.SessionContainer;
import com.common.framework.listener.SessionListener;
import com.common.util.data.ValidateUtil;
import com.common.util.data.date.DateUtil;
import com.common.util.data.map.BaseDmp;
import com.common.util.data.map.Dmp;
import com.common.util.security.DesUtil;
import com.system.pojo.PSysUser;
import com.system.service.LoginService;
import com.system.service.RoleService;
import com.system.service.UserService;

public class LoginAction extends BaseAction{

	private static final long serialVersionUID = -8321745690164298156L;
	
	private static Logger log = LoggerFactory.getLogger(LoginAction.class);
	
	private LoginService loginService;
	
	private UserService userService;
	
	private RoleService roleService;
	
	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public LoginService getLoginService() {
		return loginService;
	}

	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}
	
	/**
	 * 跳转至初始化配置页面
	 * @return
	 */
	public String Init(){
		return "install";
	}
	
	/**
	 * 退出系统
	 * @return
	 */
	public String exit(){
		//删除所有session
		if (getRequest().getSession() != null) {
		      getRequest().getSession().invalidate();
		 }
		return "exit";
	}
	
	public String switchRole() throws Exception{
		Dmp  dmp = this.getParamsAsDmp();
		if (getRequest().getSession() != null) {
		      getRequest().getSession().invalidate();
		 }
		dmp.put("id", dmp.getAsString("_userid"));
		PSysUser pSysUser = userService.getUserInfo(dmp);
		dmp.put("account", pSysUser.getAccount());
		dmp.put("password", DesUtil.decryptBasedDes(pSysUser.getPassword()));
		
		Map  userDmp =  this.loginService.getUserInfo(dmp);
		//设置session
	     saveSession(userDmp);
	     dmp.put("obj", userDmp);
		this.setAttributeFromDmp(dmp);
		return "switchLogin";
	}
	
	/**
	 * 跳转至登录页面
	 * @return
	 */
	public String loginInit(){
		return "login";
	}
	
	public String switchLogin() throws Exception{
		    Dmp  dmp = this.getParamsAsDmp();
			Map p = roleService.getOrgAndRole(dmp);
			SessionContainer sessionContainer = getSessionContainer();
			UserInfo userinfo = sessionContainer.getUserInfo();
			userinfo.setRoleId(dmp.getAsString("roleId"));
			userinfo.setRoleTpye((String) p.get("type"));
			userinfo.setEntityId((String) p.get("entity_id"));
			userinfo.setOrgId(dmp.getAsString("orgId"));
			 this.setAttributeFromDmp(dmp);
			 return "index";
	}
	
	/**
	 * 平台登录验证
	 * @return
	 */
	public String login(){
		Dmp  dmp = this.getParamsAsDmp();
		
	    if (ValidateUtil.isNotEmpty(getRequest().getSession())) {
	        getRequest().getSession().invalidate();
	    }
	    
		Dmp jsonDmp = new BaseDmp();
		 
		 String account = (String)dmp.get("account");
		 String password = (String)dmp.get("password");
		 
		if (ValidateUtil.isEmpty(account) ){
		      jsonDmp.put("success", Boolean.valueOf(false));
		      jsonDmp.put("msg", "登录失败，登录帐号为空!");
		      jsonDmp.put("errorType", "2");
		      log.warn("登录系统失败(失败原因：登录帐号不能为空!)");
		      this.setAttributeFromDmp(jsonDmp);
		      return "login";
		 }
		
		if (ValidateUtil.isEmpty(password) ){
		      jsonDmp.put("success", Boolean.valueOf(false));
		      jsonDmp.put("msg", "登录失败，登录密码不能为空!");
		      jsonDmp.put("errorType", "2");
		      log.warn("登录系统失败(失败原因：登录密码为空!)");
		      this.setAttributeFromDmp(jsonDmp);
		      return "login";
		 }
		
		log.info("帐户[" + account + "]正尝试登陆系统...");
		
		Map  userDmp =  this.loginService.getUserInfo(dmp);
		String enabled = (String) userDmp.get("enabled");
		if("0".equals(enabled)){
	        jsonDmp.put("success", Boolean.valueOf(false));
	        jsonDmp.put("msg", "登录失败，用户已停用，请联系系统管理员!");
	        jsonDmp.put("errorType", "2");
	        log.warn("[" + account + "]" + "登录系统失败(失败原因：用户已停用)");
	        this.setAttributeFromDmp(jsonDmp);
	        return "login";
		}
		
	    if (checkLockUser(userDmp, account)) {
	        jsonDmp.put("success", Boolean.valueOf(false));
	        jsonDmp.put("msg", "登录失败，用户已被锁住，请3分钟后再试!");
	        jsonDmp.put("errorType", "2");
	        log.warn("[" + account + "]" + "登录系统失败(失败原因：用户被锁住)");
	        this.setAttributeFromDmp(jsonDmp);
	        return "login";
	      }
	    
	   
	    if (! DesUtil.encryptBasedDes(password).equals(userDmp.get("password"))) {
	        jsonDmp.put("success", Boolean.valueOf(false));
	        jsonDmp.put("msg", "登录失败，密码错误!");
	          log.warn(userDmp.get("username") + "[" + 
	        		  userDmp.get("account") + "]" + 
	            "登录系统失败(失败原因：密码输入错误)");
	        this.setAttributeFromDmp(jsonDmp);
	        return "login";
	      }
	    //设置session
	     saveSession(userDmp);
	     //成功登陆，插入操作事件
	     dmp.put("obj", userDmp);
	     this.setAttributeFromDmp(dmp);
	    return "switchLogin";
	}
	
	private void saveSession(Map p){
		    UserInfo userInfo = new UserInfo();
		    userInfo.setAccount((String) p.get("account"));
		    userInfo.setUsername((String) p.get("username"));
		    userInfo.setUserId((String) p.get("id"));
		    getRequest().getSession().setAttribute("userInfo", userInfo);
		    SessionContainer sessionContainer = getSessionContainer();
		    sessionContainer.setUserInfo(userInfo);
		    log.info(userInfo.getUsername() + "[" + userInfo.getAccount() + "]" + 
		    	      "成功登录系统!创建了一个有效Session连接,会话ID:[" + 
		    	      getRequest().getSession().getId() + "]" + 
		    	      DateUtil.getCurDateTimeStr19WithLink());
		     SessionListener.addSession(getRequest().getSession(),sessionContainer);
	}
	
	  private boolean checkLockUser(Map<String, LockUser> usermap, String usercode){
		    boolean check = false;
		    if (usermap.containsKey(usercode)) {
			      LockUser user = (LockUser)usermap.get(usercode);
			      if (user.getErrorNum() == 3) {
			        user.setStartTime(System.currentTimeMillis());
			        check = true;
			      }
		    }
		    return check;
	  }
	  
	  public void queryAdminRole() throws Exception{
		  Dmp  dmp = this.getParamsAsDmp();
		  List list = loginService.queryAdminRole(dmp);
		  String obj = JSON.toJSONString(list, true);
		  this.getResponse().setContentType("text/html;charset=UTF-8"); 
		  this.getResponse().getWriter().write(obj);
	  }
	  
	  public void queryOrgUnit() throws Exception{
		  Dmp  dmp = this.getParamsAsDmp();
		  List list = loginService.queryOrgUnit(dmp);
		  String obj = JSON.toJSONString(list, true);
		  this.getResponse().setContentType("text/html;charset=UTF-8"); 
		  this.getResponse().getWriter().write(obj);
	  }
	  
		
	  public void queryBusiRole() throws Exception{
		  Dmp  dmp = this.getParamsAsDmp();
		  List list = loginService.queryBusiRole(dmp);
		  String obj = JSON.toJSONString(list, true);
		  this.getResponse().setContentType("text/html;charset=UTF-8"); 
		  this.getResponse().getWriter().write(obj);
	  }

	public RoleService getRoleService() {
		return roleService;
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}
	  
}
