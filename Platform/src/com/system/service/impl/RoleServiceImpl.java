package com.system.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.common.base.service.impl.HServiceImpl;
import com.common.util.RandomString;
import com.common.util.data.map.Dmp;
import com.system.pojo.PSysRole;
import com.system.service.RoleService;

public class RoleServiceImpl extends HServiceImpl implements RoleService{
	
	public Map getSuperRole() throws Exception {
		String sql = "select r.id,r.rolename,r.roletype,r.remark from p_sys_role r where r.roletype = 'superRole' and r.locked = '0'";
		List list = findAddMapBySql(sql, null);
		Map obj = null;
		if(list.size()>0){
			 obj = (Map) list.get(0);
		}
		return obj;
	}

	public Map getRoleInfo(Map p) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("select r.id,r.name,r.type,r.remark from p_sys_role r where  r.locked = '0'");
		String roleId = (String) p.get("roleId");
		if(roleId != null && !"".equals(roleId)){
			sql.append(" and r.id ='").append(roleId).append("'");
		}
		List list = findAddMapBySql(sql.toString(), null);
		Map obj = null;
		if(list.size()>0){
			 obj = (Map) list.get(0);
		}
		return obj;
	}

	public List getRoleTree() throws Exception {
		String sql =  "select temp.* 　from (select r.id, r.name as text, r.parent_id\n" +
								"                       from p_sys_role r\n" + 
								"                      where 1 = 1\n" + 
								"                        and r.type = 'adminRole'\n" + 
								"                     union all\n" + 
								"                     select t.id, t.name as text, t.parent_id\n" + 
								"                       from p_sys_role t\n" + 
								"                      where 1 = 1\n" + 
								"                        and t.id = (select distinct ro.parent_id\n" + 
								"                                      from p_sys_role ro\n" + 
								"                                     where ro.type = 'adminRole')) temp\n" + 
								" order by temp.id asc";

		List list = findAddMapBySql(sql, null);
		return list;
	}

	public List queryBusiRole(Dmp dmp) throws Exception {
		String roleType = dmp.getAsString("_roletype");
		String sql = null;
		if("adminRole".equals(roleType)){
		 sql =  "select r.id, r.name, r.remark\n" +
					"  from p_sys_role r\n" + 
					" where r.type = 'busiRole' and (r.entityid ='"+dmp.getAsString("_entityid")+"' or r.entityid = '0')\n" + 
					" order by r.id desc";
		}else{
			 sql =  "select r.id, r.name, r.remark\n" +
						"  from p_sys_role r\n" + 
						" where r.type in ('busiRole','adminRole')\n" + 
						" order by r.id desc";
		}

		List list = findAddMapBySql(sql, null);
		return list;
	}

	public void saveOrUpdate(PSysRole pSysRole) throws Exception {
		String id = pSysRole.getId();
		if(id ==  null || "".equals(id)){
			pSysRole.setId(RandomString.getTimeSequence(20));
			save(pSysRole);
		}else{
			update(pSysRole);
		}
	}

	public String delRole(Dmp dmp) throws Exception {
		String roleId = dmp.getAsString("id");
		String userId = dmp.getAsString("_userid");
		String flag = "Y";
		String sql = "select * from p_sys_role_org_unit rou where 1 = 1 and rou.role_id = '"+roleId+"'";
		List list = findAddMapBySql(sql, null);
		if(list.size()==0){
			delete(PSysRole.class, roleId);
		}else{
			String sqls = "select *\n" +
							"  from p_sys_role_org_unit rou\n" + 
							" where 1 = 1\n" + 
							"   and rou.role_id = '"+roleId+"'\n" + 
							"   and rou.org_id in\n" + 
							"       (select ouu.org_id\n" + 
							"          from p_sys_org_unit_user ouu\n" + 
							"         where ouu.user_id = '"+userId+"')";
             List lists =  findAddMapBySql(sql, null);
             if(lists.size() == 0){
            	 delete(PSysRole.class, roleId);
             }else{
            	 flag = "N";
             }
		}
		return flag;
	}

	public Map getOrgAndRole(Dmp dmp) throws Exception {
		String sql = 
				"select temp1.*, temp2.entity_id\n" +
						"  from (select r.*, ru.user_id\n" + 
						"          from p_sys_role r, p_sys_role_user ru\n" + 
						"         where r.id = ru.role_id\n" + 
						"           and ru.user_id = '"+dmp.getAsString("_userid")+"'\n" + 
						"           and ru.role_id = '"+dmp.getAsString("roleId")+"') temp1\n" + 
						"  left join (select u.entity_id, uu.user_id, u.id as org_id\n" + 
						"               from p_sys_org_unit u, p_sys_org_unit_user uu\n" + 
						"              where u.id = uu.org_id\n" + 
						"                and u.id = '"+dmp.getAsString("orgId")+"') temp2 on temp2.user_id = temp1.user_id";

		List list = findAddMapBySql(sql, null);
		Map p = new HashMap();
		if(list.size()>0){
			p = (Map) list.get(0);
		}
		return p;
	}

	public List getSuperRoleTree() throws Exception {
		String sql = "select r.id,r.name as text,r.parent_id from p_sys_role r where 1 = 1 and r.type = 'superRole' order by r.id asc";
		List list = findAddMapBySql(sql, null);
		return list;
	}

}
