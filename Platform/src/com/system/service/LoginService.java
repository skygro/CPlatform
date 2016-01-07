package com.system.service;

import java.util.List;
import java.util.Map;

import com.common.base.service.HService;
import com.common.util.data.map.Dmp;
import com.common.util.page.Pager;

public abstract interface LoginService extends  HService{
	
	public Map getUserInfo(Dmp  dmp);
	
	public List queryAdminRole(Dmp dmp) throws Exception;
	
	public List queryOrgUnit(Dmp dmp) throws Exception;
	
	public List queryBusiRole(Dmp dmp) throws Exception;
	
}
