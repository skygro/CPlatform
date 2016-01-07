package com.system.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.common.base.action.BaseAction;
import com.common.util.CommonUtil;
import com.common.util.TreeNodes;
import com.common.util.data.map.Dmp;
import com.common.util.page.Pager;
import com.common.util.security.DesUtil;
import com.system.pojo.PSysOrgUnit;
import com.system.pojo.PSysOrgUnitUser;
import com.system.pojo.PSysRoleUser;
import com.system.pojo.PSysUser;
import com.system.service.OrganizationService;
import com.system.service.RoleService;
import com.system.service.UserService;

public class UserAction extends BaseAction{

	private static final long serialVersionUID = -210001639179001824L;
	
	private UserService userService;
	
    private RoleService roleService;  
    
    private OrganizationService organizationService;

	public OrganizationService getOrganizationService() {
		return organizationService;
	}

	public void setOrganizationService(OrganizationService organizationService) {
		this.organizationService = organizationService;
	}

	public RoleService getRoleService() {
		return roleService;
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	public String userList() throws Exception{
		return "userList";
	}
	
	public String queryUser() throws Exception{
		Dmp dmp = this.getParamsAsDmp();
		Pager pager = userService.queryUser(dmp);
		String result = null;
		if(pager!=null&&!"".equals(pager)){
			Map<String, Object> jsonMap = new HashMap<String, Object>();
			jsonMap.put("total",pager.getTotalRows());
			jsonMap.put("rows", pager.getList());
		    result = JSON.toJSONString(jsonMap, true);
		}
		this.getResponse().setContentType("text/html;charset=UTF-8");   
		this.getResponse().getWriter().write(result);
		this.setAttributeFromDmp(dmp);
		return null;
	}
	
	public String statusUserList() throws Exception{
		return "statusUserList";
	}
	
	public String activeUserList() throws Exception{
		Dmp dmp = this.getParamsAsDmp();
		String id = dmp.getAsString("_userid");
		dmp.put("id", id);
		PSysUser pSysUser = userService.getUserInfo(dmp);
		dmp.put("obj", pSysUser);
		this.setAttributeFromDmp(dmp);
		return "activeUserList";
	}
	
	public String roleToUserList() throws Exception{
		Dmp dmp = this.getParamsAsDmp();
		List list = roleService.queryBusiRole(dmp);
		dmp.put("list", list);
		this.setAttributeFromDmp(dmp);
		return "roleToUserList";
	}
	
	public String orgUnitToUserList() throws Exception{
		return "orgUnitToUserList";
	}
	
	public void saveOrUpdate() throws Exception{
		Dmp dmp = this.getParamsAsDmp();
		String id = dmp.getAsString("id");
		String type = "add";
		
		PSysUser pSysUser = new PSysUser();
		pSysUser.setId(dmp.getAsString("id"));
		pSysUser.setAccount(dmp.getAsString("account"));
		pSysUser.setPassword(DesUtil.encryptBasedDes(dmp.getAsString("password")));
		pSysUser.setUsername(dmp.getAsString("username"));
		pSysUser.setSex(dmp.getAsString("sex"));
		pSysUser.setCaid(dmp.getAsString("caid"));
		pSysUser.setEnabled(dmp.getAsString("enabled"));
		pSysUser.setRemark(dmp.getAsString("remark"));
		pSysUser.setPhone(dmp.getAsString("phone"));
		pSysUser.setEmail(dmp.getAsString("email"));
		pSysUser.setLocked("0");
		String entityId = dmp.getAsString("_entityid");
		if(entityId != null && !"".equals(entityId)){
			pSysUser.setEntityId(dmp.getAsString("_entityid"));
		}else{
			pSysUser.setEntityId("0");
		}
		
		userService.saveOrUpdate(pSysUser);
		
		if(id!=null&&!"".equals(id)){
			type = "edit";
		}else{
			type = "add";
		}
		this.setAttributeFromDmp(dmp);
		this.writeResponse(type);
	}
	
	public String addOrUpdate() throws Exception{
		Dmp dmp = this.getParamsAsDmp();
		PSysUser pSysUser = userService.getUserInfo(dmp);
		if(pSysUser !=null){
			pSysUser.setPassword(DesUtil.decryptBasedDes(pSysUser.getPassword()));
		}
		dmp.put("obj", pSysUser);
		this.setAttributeFromDmp(dmp);
		return "addOrUpdate";
	}
	
	public void delUser(){
		Dmp dmp = this.getParamsAsDmp();
		String result = "success";
		try {
			userService.delUser(dmp);
		} catch (Exception e) {
			result = "faile";
			e.printStackTrace();
		}
		this.setAttributeFromDmp(dmp);
		this.writeResponse(result);
	}
	
	public void updateStatus() {
		Dmp dmp = this.getParamsAsDmp();
		String result = "success";
		try {
			PSysUser pSysUser = userService.getUserInfo(dmp);
			String enabled = dmp.getAsString("enabled");
			if(enabled!=null && "1".equals(enabled)){
				pSysUser.setEnabled("1");
			}else{
				pSysUser.setEnabled("0");
			}
			userService.saveOrUpdate(pSysUser);
		} catch (Exception e) {
			result = "faile";
			e.printStackTrace();
		}
		this.setAttributeFromDmp(dmp);
		this.writeResponse(result);
	}
	
	public void updateLock() {
		Dmp dmp = this.getParamsAsDmp();
		String result = "success";
		try {
			PSysUser pSysUser = userService.getUserInfo(dmp);
			String locked = dmp.getAsString("locked");
			if(locked != null && "1".equals(locked)){
				pSysUser.setLocked("1");
			}else{
				pSysUser.setLocked("0");
			}
			userService.saveOrUpdate(pSysUser);
		} catch (Exception e) {
			result = "faile";
			e.printStackTrace();
		}
		this.setAttributeFromDmp(dmp);
		this.writeResponse(result);
	}
	
	public String queryUserByRole() throws Exception{
		Dmp dmp = this.getParamsAsDmp();
		Pager pager = userService.queryUserByRole(dmp);
		String result = null;
		if(pager!=null&&!"".equals(pager)){
			Map<String, Object> jsonMap = new HashMap<String, Object>();
			jsonMap.put("total",pager.getTotalRows());
			jsonMap.put("rows", pager.getList());
		    result = JSON.toJSONString(jsonMap, true);
		}
		this.getResponse().setContentType("text/html;charset=UTF-8");   
		this.getResponse().getWriter().write(result);
		this.setAttributeFromDmp(dmp);
		return null;
	}
	
	public String getRoleInfo() throws Exception{
		Dmp dmp = this.getParamsAsDmp();
		Map obj = roleService.getRoleInfo(dmp);
		String role = JSON.toJSONString(obj);
		this.getResponse().setContentType("text/html;charset=UTF-8");   
		this.getResponse().getWriter().write(role);
		return null;
	}
	
	public void saveUserRole(){
		Dmp dmp = this.getParamsAsDmp();
		String result = "success";
		String ids = dmp.getAsString("ids");
		String [] param=ids.split(","); 
		for(int i=0;i<param.length;i++){
			PSysRoleUser pSysRoleUser = new PSysRoleUser();
			pSysRoleUser.setRoleId(dmp.getAsString("roleId"));
			pSysRoleUser.setRoleType(dmp.getAsString("roleType"));
			pSysRoleUser.setUserId(param[i]);
			try {
				userService.saveUserRole(pSysRoleUser);
			} catch (Exception e) {
				result = "faile";
				e.printStackTrace();
			}
		}
		this.setAttributeFromDmp(dmp);
		this.writeResponse(result);
	}
	
	public String queryOtherUser() throws Exception{
		Dmp dmp = this.getParamsAsDmp();
		Pager pager = userService.queryOtherUser(dmp);
		String result = null;
		if(pager!=null&&!"".equals(pager)){
			Map<String, Object> jsonMap = new HashMap<String, Object>();
			jsonMap.put("total",pager.getTotalRows());
			jsonMap.put("rows", pager.getList());
		    result = JSON.toJSONString(jsonMap, true);
		}
		this.getResponse().setContentType("text/html;charset=UTF-8");   
		this.getResponse().getWriter().write(result);
		this.setAttributeFromDmp(dmp);
		return null;
	}
	
	public void delUserRole(){
		Dmp dmp = this.getParamsAsDmp();
		String result = "success";
		try {
			userService.delUserRole(dmp);
		} catch (Exception e) {
			result = "faile";
			e.printStackTrace();
		}
		this.setAttributeFromDmp(dmp);
		this.writeResponse(result);
	}
	
	public String organizationTree() throws Exception{
		Map p = this.getParamsAsDmp();
		List list =organizationService.organizationTree(p);
		List<TreeNodes> newlist=CommonUtil.getTreeList(list, "01",false);
		String treeList = JSON.toJSONString(newlist, true);
		this.getResponse().setContentType("text/html;charset=UTF-8");   
		this.getResponse().getWriter().write(treeList);
		return null;
	}
	
	public String getOrgUnitInfo() throws Exception{
		Dmp  dmp = this.getParamsAsDmp();
		PSysOrgUnit pSysOrgUnit = organizationService.getOrganization(dmp.getAsString("id"));
		this.setAttributeFromDmp(dmp);
		String obj = JSON.toJSONString(pSysOrgUnit, true);
		this.getResponse().setContentType("text/html;charset=UTF-8"); 
		this.getResponse().getWriter().write(obj);
		return null;
	}
	
	public String queryUserByOrgUnit() throws Exception{
		Dmp dmp = this.getParamsAsDmp();
		Pager pager = userService.queryUserByOrgUnit(dmp);
		String result = null;
		if(pager!=null&&!"".equals(pager)){
			Map<String, Object> jsonMap = new HashMap<String, Object>();
			jsonMap.put("total",pager.getTotalRows());
			jsonMap.put("rows", pager.getList());
		    result = JSON.toJSONString(jsonMap, true);
		}
		this.getResponse().setContentType("text/html;charset=UTF-8");   
		this.getResponse().getWriter().write(result);
		this.setAttributeFromDmp(dmp);
		return null;
	}
	
	public void saveOrgUnitUser(){
		Dmp dmp = this.getParamsAsDmp();
		String result = "success";
		String ids = dmp.getAsString("ids");
		String [] param=ids.split(","); 
		for(int i=0;i<param.length;i++){
			PSysOrgUnitUser pSysOrgUnitUser = new PSysOrgUnitUser();
			pSysOrgUnitUser.setOrgId(dmp.getAsString("orgId"));
			pSysOrgUnitUser.setUserId(param[i]);
			try {
				userService.saveOrgUnitUser(pSysOrgUnitUser);
			} catch (Exception e) {
				result = "faile";
				e.printStackTrace();
			}
		}
		this.setAttributeFromDmp(dmp);
		this.writeResponse(result);
	}
	
	public void delOrgUnitUser(){
		Dmp dmp = this.getParamsAsDmp();
		String result = "success";
		try {
			userService.delOrgUnitUser(dmp);
		} catch (Exception e) {
			result = "faile";
			e.printStackTrace();
		}
		this.setAttributeFromDmp(dmp);
		this.writeResponse(result);
	}
	
	public String queryOtherOrgUser() throws Exception{
		Dmp dmp = this.getParamsAsDmp();
		Pager pager = userService.queryOtherOrgUser(dmp);
		String result = null;
		if(pager!=null&&!"".equals(pager)){
			Map<String, Object> jsonMap = new HashMap<String, Object>();
			jsonMap.put("total",pager.getTotalRows());
			jsonMap.put("rows", pager.getList());
		    result = JSON.toJSONString(jsonMap, true);
		}
		this.getResponse().setContentType("text/html;charset=UTF-8");   
		this.getResponse().getWriter().write(result);
		this.setAttributeFromDmp(dmp);
		return null;
	}
}
