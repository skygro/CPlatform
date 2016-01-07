package com.system.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.common.base.service.impl.HServiceImpl;
import com.common.util.RandomString;
import com.common.util.exception.DaoException;
import com.system.pojo.PSysOrgUnit;
import com.system.pojo.PSysRoleOrgUnit;
import com.system.service.OrganizationService;

public class OrganizationServiceImpl  extends HServiceImpl implements OrganizationService {
	
	public List organizationTree(Map p) throws Exception {
		String roleId = (String) p.get("roleId");
		String roleTpye = (String) p.get("_roletype");
		String userId = (String) p.get("_userid");
		String sql = null;
		if("adminRole".equals(roleTpye)){
			sql = "select temp.*, ou.id as relateid\n" +
							"  from (select u.id, u.name as text, u.parent_id\n" + 
							"          from p_sys_org_unit u, p_sys_org_unit_user ouu\n" + 
							"         where u.id = ouu.org_id\n" + 
							"           and ouu.user_id = '"+userId+"'\n" + 
							"        union\n" + 
							"        select u.id, u.name as text, u.parent_id\n" + 
							"          from p_sys_org_unit u\n" + 
							"         where u.type = 'center') temp\n" + 
							"  left join p_sys_role_org_unit ou on ou.org_id = temp.id\n" + 
							"                                  and ou.role_id = '"+roleId+"'";
		}else{
			sql ="select u.id, u.name as text, u.parent_id, ou.id as relateid\n" +
					"  from p_sys_org_unit u\n" + 
					"  left join p_sys_role_org_unit ou on ou.org_id = u.id\n" + 
					"                                  and ou.role_id = '"+roleId+"'\n" + 
					" order by u.sortno asc";
		}
		List list = findAddMapBySql(sql, null);
		return list;
	}

	public void saveOrUpdate(PSysOrgUnit pSysOrgUnit) throws DaoException {
		if(pSysOrgUnit.getId() == null || pSysOrgUnit.getId() ==""){
			pSysOrgUnit.setId(RandomString.getTimeSequence(20));
			save(pSysOrgUnit);
		}else{
			update(pSysOrgUnit);
		}
	}

	public PSysOrgUnit getOrganization(String id) throws Exception {
		return (PSysOrgUnit) get(PSysOrgUnit.class, id);
	}

	public void delOrganization(String id) throws Exception {
		
		//删除角色部门信息
		String sqls = "delete from p_sys_role_org_unit o\n" +
								" where o.org_id in (select u.id\n" + 
								"                      from p_sys_org_unit u\n" + 
								"                     start with u.id = '"+id+"'\n" + 
								"                    connect by prior u.id = u.parent_id)";
		deleteBySQL(sqls, null);
		
		//删除部门信息
		String sql = "delete from p_sys_org_unit c\n" +
						" where c.id in (select de.id\n" + 
						"                  from p_sys_org_unit de\n" + 
						"                 start with de.id = '"+id+"'\n" + 
						"                connect by prior de.id = de.parent_id)";
		deleteBySQL(sql, null);
	}

	public Map queryOrgRoleId(String orgId, String roleId) throws Exception {
		String sql = "select * from p_sys_role_org_unit ou where ou.role_id = '"+roleId+"' and ou.org_id = '"+orgId+"'";
		List list = findAddMapBySql(sql, null);
		Map p = new HashMap();
		if(list.size()>0){
			p = (Map) list.get(0);
		}
		return p;
	}

	public void saveMenuToRole(PSysRoleOrgUnit pSysRoleOrgUnit)
			throws Exception {
		if(pSysRoleOrgUnit.getId() == null || "".equals(pSysRoleOrgUnit.getId())){
			pSysRoleOrgUnit.setId(RandomString.getTimeSequence(20));
			save(pSysRoleOrgUnit);
		}
	}

	public void delRoleOrgUnit(String roleId) throws Exception {
		String sql = "delete from p_sys_role_org_unit t where t.role_id = '"+roleId+"'";
		deleteBySQL(sql, null);
	}
}
