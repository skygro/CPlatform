package com.system.service;

import java.util.List;

import com.common.util.data.map.Dmp;
import com.common.util.page.Pager;
import com.system.pojo.PSysOrgUnitUser;
import com.system.pojo.PSysRoleUser;
import com.system.pojo.PSysUser;

public interface UserService {
	
	public Pager queryUser(Dmp dmp) throws Exception;
	
	public void saveOrUpdate(PSysUser pSysUser) throws Exception;
	
	public PSysUser getUserInfo(Dmp dmp) throws Exception;
	
	public void delUser(Dmp dmp) throws Exception;
	
	public Pager queryUserByRole(Dmp dmp) throws Exception;
	
	public void saveUserRole(PSysRoleUser pSysUser) throws Exception;
	
	public Pager queryOtherUser(Dmp dmp)  throws Exception;
	
	public void delUserRole(Dmp dmp) throws Exception;
	
	public List queryOrgUnit(Dmp dmp) throws Exception;
	
	public Pager queryUserByOrgUnit(Dmp dmp) throws Exception;
	
	public void saveOrgUnitUser(PSysOrgUnitUser pSysOrgUnitUser) throws Exception;
	
	public void delOrgUnitUser(Dmp dmp) throws Exception;
	
	public Pager queryOtherOrgUser(Dmp dmp)  throws Exception;
	
}
