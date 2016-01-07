package com.system.action;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.common.base.action.BaseAction;
import com.common.util.CommonUtil;
import com.common.util.TreeNodes;
import com.common.util.data.map.Dmp;
import com.system.pojo.PSysOrgUnit;
import com.system.pojo.PSysRoleOrgUnit;
import com.system.service.OrganizationService;
import com.system.service.RoleService;

public class OrganizationAction extends BaseAction{
	
	private static final long serialVersionUID = -8811893712144979975L;
	
	private OrganizationService organizationService;
	
	private RoleService roleService;

	public RoleService getRoleService() {
		return roleService;
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}
	
	public OrganizationService getOrganizationService() {
		return organizationService;
	}

	public void setOrganizationService(OrganizationService organizationService) {
		this.organizationService = organizationService;
	}
	
	public String organizationList(){
		Dmp dmp = this.getParamsAsDmp();
		this.setAttributeFromDmp(dmp);
		return "organizationList";
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
	
	public void saveOrUpdate() throws Exception{
		Dmp  dmp = this.getParamsAsDmp();
		String id = dmp.getAsString("id");
		String type = "add";
		PSysOrgUnit pSysOrgUnit = new PSysOrgUnit();
		pSysOrgUnit.setId(dmp.getAsString("id"));
		pSysOrgUnit.setParentId(dmp.getAsString("parentid"));
		pSysOrgUnit.setSortno(dmp.getAsShort("sortno"));
		pSysOrgUnit.setName(dmp.getAsString("name"));
		pSysOrgUnit.setType(dmp.getAsString("type"));
		pSysOrgUnit.setRemark(dmp.getAsString("remark"));
		pSysOrgUnit.setEnabled(dmp.getAsString("enabled"));
		organizationService.saveOrUpdate(pSysOrgUnit);
		if(id!=null&&!"".equals(id)){
			type = "edit";
		}else{
			type = "add";
		}
		this.setAttributeFromDmp(dmp);
		this.writeResponse(type);
	}
	
	public String getOrganization() throws Exception{
		Dmp  dmp = this.getParamsAsDmp();
		PSysOrgUnit pSysOrgUnit = organizationService.getOrganization(dmp.getAsString("id"));
		this.setAttributeFromDmp(dmp);
		String obj = JSON.toJSONString(pSysOrgUnit, true);
		this.getResponse().setContentType("text/html;charset=UTF-8"); 
		this.getResponse().getWriter().write(obj);
		return null;
	}
	
	public void delOrganization() {
		Dmp  dmp = this.getParamsAsDmp();
		String type = "";
		try {
			organizationService.delOrganization(dmp.getAsString("id"));
			type = "success";
		} catch (Exception e) {
			type="faile";
		}
		this.setAttributeFromDmp(dmp);
		this.writeResponse(type);
	}
	
	public String organizationToRoleList() throws Exception{
		Dmp  dmp = this.getParamsAsDmp();
		List list = roleService.queryBusiRole(dmp);
		dmp.put("list", list);
		this.setAttributeFromDmp(dmp);
		return "organizationToRoleList";
	}
	
	public void saveOrgToRole(){
		Dmp  dmp = this.getParamsAsDmp();
		String type = "success";
		try {
			String roleId = dmp.getAsString("roleId");
			String orgIds = dmp.getAsString("orgIds");
			String [] param =new String[]{}; 
			if(orgIds !=null && !"".equals(orgIds)){
				param=orgIds.split(","); 
				organizationService.delRoleOrgUnit(roleId);
			}
			
			for(int i=0;i<param.length;i++){
				String orgId = param[i];
				Map p = organizationService.queryOrgRoleId(orgId, roleId);
				String id = (String) p.get("id");
				if(id==null || id ==""){
					PSysRoleOrgUnit pSysRoleOrgUnit = new PSysRoleOrgUnit();
					pSysRoleOrgUnit.setOrgId(orgId);
					pSysRoleOrgUnit.setRoleId(roleId);
					organizationService.saveMenuToRole(pSysRoleOrgUnit);
				}
			}
			
			type = "success";
		} catch (Exception e) {
			type="faile";
		}
		this.setAttributeFromDmp(dmp);
		this.writeResponse(type);
	}

}
