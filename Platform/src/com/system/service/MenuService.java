package com.system.service; 

import java.util.List;
import java.util.Map;

import com.common.util.exception.DaoException;
import com.system.pojo.PSysMenu;
import com.system.pojo.PSysRoleMenu;

public interface MenuService {
	
	public List menuTree(Map p) throws Exception;
	
	public void saveOrUpdate(PSysMenu cpSysMenu) throws DaoException;
	
	public PSysMenu getMenuInfo(String id) throws Exception;
	
	public void delMenu(String id) throws Exception;
	
	public Map queryMenuRoleId(String menuId,String roleId) throws Exception;
	
	public void saveMenuToRole(PSysRoleMenu pSysRoleMenu) throws Exception;
	
	public void delRoleMenu(String roleId) throws Exception;
	
}
