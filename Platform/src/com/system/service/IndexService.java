package com.system.service;

import java.util.List;
import java.util.Map;

import com.common.base.pojo.MenuVo;
import com.common.util.data.map.Dmp;

public interface IndexService {
	
	/**
	 * 获取一级菜单
	 * @param dmp
	 * @return
	 */
	public List listMenu(Dmp dmp) throws Exception;
	
	/**
	 * 根据父类ID获取菜单
	 * @param dmp
	 * @return
	 * @throws Exception
	 */
	public List<MenuVo> listNav(Dmp dmp) throws Exception;
	
	/**
	 * 获取菜单
	 * @param dmp
	 * @return
	 * @throws Exception
	 */
	public Map getMenu(Dmp dmp) throws Exception;
	
	/**
	 * 获取角色名称与组织单元名称
	 * @param dmp
	 * @return
	 * @throws Exception
	 */
	public Map getRoleAndOrg(Dmp dmp) throws Exception;

}
