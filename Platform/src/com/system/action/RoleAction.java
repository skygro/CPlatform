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
import com.system.pojo.PSysRole;
import com.system.pojo.PSysRoleUser;
import com.system.service.RoleService;
import com.system.service.UserService;

public class RoleAction extends BaseAction{

	private static final long serialVersionUID = 8337140766523613719L;
	
	private RoleService roleService;
	
	private UserService userService;

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public RoleService getRoleService() {
		return roleService;
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}
	
	public String superAdminRole() throws Exception{
		return "superAdminRole";
	}
	
	public String getRoleInfo() throws Exception{
		Dmp dmp = this.getParamsAsDmp();
		Map obj = roleService.getRoleInfo(dmp);
		String role = JSON.toJSONString(obj);
		this.getResponse().setContentType("text/html;charset=UTF-8");   
		this.getResponse().getWriter().write(role);
		return null;
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
	
	public String adminRoleList() throws Exception{
		return "adminRoleList";
	}
	
	public String getSuperRoleTree() throws Exception{
		Map p = this.getParamsAsDmp();
		List list = roleService.getSuperRoleTree();
		List<TreeNodes> newlist=CommonUtil.getTreeList(list, "00",true);
		String treeList = JSON.toJSONString(newlist, true);
		this.getResponse().setContentType("text/html;charset=UTF-8");   
		this.getResponse().getWriter().write(treeList);
		return null;
	}
	
	public String getRoleTree() throws Exception{
		Map p = this.getParamsAsDmp();
		List list = roleService.getRoleTree();
		List<TreeNodes> newlist=CommonUtil.getTreeList(list, "01",true);
		String treeList = JSON.toJSONString(newlist, true);
		this.getResponse().setContentType("text/html;charset=UTF-8");   
		this.getResponse().getWriter().write(treeList);
		return null;
	}
	
	public String busiRoleList() throws Exception{
		return "busiRoleList";
	}
	
	public String queryBusiRole() throws Exception{
		Dmp dmp = this.getParamsAsDmp();
		List list = roleService.queryBusiRole(dmp);
		String newList = JSON.toJSONString(list, true);
		this.getResponse().setContentType("text/html;charset=UTF-8");   
		this.getResponse().getWriter().write(newList);
		return null;
	}
	
	public void saveOrUpdate() throws Exception{
		Dmp dmp = this.getParamsAsDmp();
		String flag = "add";
		PSysRole pSysRole = new PSysRole();
		String id = dmp.getAsString("id");
		pSysRole.setRemark(dmp.getAsString("remark"));
		pSysRole.setName(dmp.getAsString("rolename"));
		String type =dmp.getAsString("type");
		if(type !=null && "admin".equals(type)){
			pSysRole.setType("adminRole");
			pSysRole.setParentId(dmp.getAsString("parentId"));
		}else if( "busi".equals(type)){
			pSysRole.setType("busiRole");
			pSysRole.setParentId("00");
		}
		String entityId = dmp.getAsString("_entityid");
		if(entityId !=null && !"".equals(entityId)){
			pSysRole.setEntityId(entityId);
		}else{
			pSysRole.setEntityId("0");
		}
		pSysRole.setLocked("0");
		roleService.saveOrUpdate(pSysRole);
		if(id!=null && !"".equals(id)){
			flag = "edit";
		}
		this.writeResponse(flag);
	}
	
	public void delRole(){
		Dmp  dmp = this.getParamsAsDmp();
		String type = "success";
		try {
			String flag = roleService.delRole(dmp);
			if("N".equals(flag)){
				 type = "N";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			type = "faile";
		}
		this.writeResponse(type);
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
	
	public void saveUserRole(){
		Dmp dmp = this.getParamsAsDmp();
		String result = "success";
		String ids = dmp.getAsString("ids");
		String [] param=ids.split(","); 
		for(int i=0;i<param.length;i++){
			PSysRoleUser pSysRoleUser = new PSysRoleUser();
			pSysRoleUser.setRoleId(dmp.getAsString("roleId"));
			pSysRoleUser.setUserId(param[i]);
			pSysRoleUser.setRoleType(dmp.getAsString("roleType"));
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
	
}
