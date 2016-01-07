package com.system.action;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.common.base.action.BaseAction;
import com.common.util.CommonUtil;
import com.common.util.TreeNodes;
import com.common.util.data.map.Dmp;
import com.system.pojo.PSysMenu;
import com.system.pojo.PSysRoleMenu;
import com.system.service.MenuService;
import com.system.service.RoleService;

public class MenuAction extends BaseAction{

	private static final long serialVersionUID = -8781675909204411392L;
	
	private MenuService menuService;
	
	private RoleService roleService;

	public RoleService getRoleService() {
		return roleService;
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	public MenuService getMenuService() {
		return menuService;
	}

	public void setMenuService(MenuService menuService) {
		this.menuService = menuService;
	}
	
	public String menuList() throws Exception{
		Dmp dmp = this.getParamsAsDmp();
		this.setAttributeFromDmp(dmp);
		return "menuList";
	}
	
	public String menuTree() throws Exception{
		Map p = this.getParamsAsDmp();
		List list = menuService.menuTree(p);
		List<TreeNodes> newlist=CommonUtil.getTreeList(list, "0",false);
		String treeList = JSON.toJSONString(newlist, true);
		this.getResponse().setContentType("text/html;charset=UTF-8");   
		this.getResponse().getWriter().write(treeList);
		return null;
	}
	
	public String getMenuInfo() throws Exception{
		Dmp  dmp = this.getParamsAsDmp();
		PSysMenu pSysMenu =menuService.getMenuInfo(dmp.getAsString("id"));
		this.setAttributeFromDmp(dmp);
		String obj = JSON.toJSONString(pSysMenu, true);
		this.getResponse().setContentType("text/html;charset=UTF-8"); 
		this.getResponse().getWriter().write(obj);
		return null;
	}
	
	public void saveOrUpdate() throws Exception{
		Dmp  dmp = this.getParamsAsDmp();
		String id = dmp.getAsString("id");
		String type = "add";
		PSysMenu pSysMenu = new PSysMenu();
		pSysMenu.setId(id);
		pSysMenu.setName(dmp.getAsString("name"));
		pSysMenu.setParentId(dmp.getAsString("parentid"));
		pSysMenu.setSortno(dmp.getAsShort("sortno"));
		pSysMenu.setStatus(dmp.getAsString("status"));
		pSysMenu.setRemark(dmp.getAsString("remark"));
		pSysMenu.setUrl(dmp.getAsString("url"));
		pSysMenu.setLeaf(dmp.getAsString("leaf"));
		menuService.saveOrUpdate(pSysMenu);
		if(id!=null&&!"".equals(id)){
			type = "edit";
		}else{
			type = "add";
		}
		this.setAttributeFromDmp(dmp);
		this.writeResponse(type);
	}
	
	public void delMenu() {
		Dmp  dmp = this.getParamsAsDmp();
		String type = "success";
		try {
			menuService.delMenu(dmp.getAsString("id"));
			type = "success";
		} catch (Exception e) {
			type="faile";
		}
		this.setAttributeFromDmp(dmp);
		this.writeResponse(type);
	}
	
	public String menuToRoleList() throws Exception{
		Dmp  dmp = this.getParamsAsDmp();
		List list = roleService.queryBusiRole(dmp);
		dmp.put("list", list);
		this.setAttributeFromDmp(dmp);
		return "menuToRoleList";
	}
	
	public void saveMenuToRole(){
		Dmp  dmp = this.getParamsAsDmp();
		String type = "success";
		try {
			String roleId = dmp.getAsString("roleId");
			String menuIds = dmp.getAsString("menuIds");
			String [] param =new String[]{}; 
			if(menuIds !=null && !"".equals(menuIds)){
				param=menuIds.split(","); 
				menuService.delRoleMenu(roleId);
			}
			for(int i=0;i<param.length;i++){
				String menuId = param[i];
				Map p = menuService.queryMenuRoleId(menuId, roleId);
				PSysRoleMenu pSysRoleMenu = new PSysRoleMenu();
				pSysRoleMenu.setId((String) p.get("id"));
				pSysRoleMenu.setMenuId(menuId);
				pSysRoleMenu.setRoleId(roleId);
				menuService.saveMenuToRole(pSysRoleMenu);
			}
			
			type = "success";
		} catch (Exception e) {
			type="faile";
		}
		this.setAttributeFromDmp(dmp);
		this.writeResponse(type);
	}

}
