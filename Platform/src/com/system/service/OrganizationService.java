package com.system.service;

import java.util.List;
import java.util.Map;

import com.common.util.exception.DaoException;
import com.system.pojo.PSysOrgUnit;
import com.system.pojo.PSysRoleOrgUnit;

public interface OrganizationService {
	
	public List organizationTree(Map p) throws Exception;
	
	public void saveOrUpdate(PSysOrgUnit pSysOrgUnit) throws DaoException;
	
	public PSysOrgUnit getOrganization(String id) throws Exception;
	
	public void delOrganization(String id) throws Exception;
	
	public Map queryOrgRoleId(String orgId,String roleId) throws Exception; 
	
	public void saveMenuToRole(PSysRoleOrgUnit pSysRoleOrgUnit) throws Exception;
	
	public void delRoleOrgUnit(String roleId) throws Exception;

}
