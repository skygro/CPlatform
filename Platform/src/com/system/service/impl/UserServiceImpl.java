package com.system.service.impl;

import java.util.List;

import com.common.base.service.impl.HServiceImpl;
import com.common.util.RandomString;
import com.common.util.data.map.Dmp;
import com.common.util.page.Pager;
import com.system.pojo.PSysOrgUnitUser;
import com.system.pojo.PSysRoleUser;
import com.system.pojo.PSysUser;
import com.system.service.UserService;

public class UserServiceImpl  extends HServiceImpl implements UserService {

	public Pager queryUser(Dmp dmp) throws Exception {
		String account = dmp.getAsString("account");
		String username = dmp.getAsString("username");
		String roleType = dmp.getAsString("_roletype");
		
		String sql = null;
		if("adminRole".equals(roleType)){
			sql = "select * from p_sys_user t where 1 = 1 and (t.entityId ='"+dmp.getAsString("_entityid")+"' or t.entityId='0') ";

		}else{
			sql = "select * from p_sys_user t where 1 = 1 ";
		}
		if(account  != null && !"".equals(account)){
			sql += " and t.account ='"+account+"'";
		}
		
		if(username!=null && !"".equals(username)){
			sql += " and t.username like '%"+username+"%'";
		}
		sql += " order by t.id desc";
		Pager pager = findPagerBySqlForMap(sql, null, dmp);
		return pager;
	}

	public void saveOrUpdate(PSysUser pSysUser) throws Exception {
		String id = pSysUser.getId();
		if(id != null && !"".equals(id)){
			update(pSysUser);
		}else{
			pSysUser.setId(RandomString.getTimeSequence(20));
			save(pSysUser);
		}
		
	}

	public PSysUser getUserInfo(Dmp dmp) throws Exception {
		String id = dmp.getAsString("id");
		PSysUser user = null;
		if(id != null && !"".equals(id)){
			user = (PSysUser) get(PSysUser.class, id);
		}
		return user;
	}

	public void delUser(Dmp dmp) throws Exception {
		String ids = (String) dmp.getAsString("ids");
		if(ids!=null&&!"".equals(ids)){
			String sql = "delete from p_sys_user u where u.id in ("+ids.substring(0, ids.length()-1)+")";
			deleteBySQL(sql, null);
		}
	}

	public Pager queryUserByRole(Dmp dmp) throws Exception {
		String roleId = dmp.getAsString("roleId");
		String sql = "select u.*,r.id as rid from p_sys_user u,p_sys_role_user r where u.id = r.user_id and r.role_id = '"+roleId+"' order by u.id desc";
		Pager pager = findPagerBySqlForMap(sql, null, dmp);
		return pager;
	}

	public void saveUserRole(PSysRoleUser pSysRoleUser) throws Exception {
		if("".equals(pSysRoleUser.getId()) || pSysRoleUser.getId() == null){
			pSysRoleUser.setId(RandomString.getTimeSequence(20));
			save(pSysRoleUser);
		}
	}

	public Pager queryOtherUser(Dmp dmp) throws Exception {
		String account = dmp.getAsString("account");
		String roleId = dmp.getAsString("roleId");
		String roleType = dmp.getAsString("_roletype");
		String sql = null;
		if("adminRole".equals(roleType)){
			sql= "select t.*\n" +
							"  from p_sys_user t\n" + 
							" where 1 = 1\n" + 
							"   and (t.entityid = '"+dmp.getAsString("_entityid")+"' or t.entityid = '0')\n" + 
							"   and t.id not in (select u.id\n" + 
							"                      from p_sys_user u, p_sys_role_user ur\n" + 
							"                     where ur.user_id = u.id\n" + 
							"                       and ur.role_id = '"+roleId+"')";
		}else{
			sql =   "select t.*\n" +
						"  from p_sys_user t\n" + 
						" where t.id not in (select u.id\n" + 
						"                      from p_sys_user u, p_sys_role_user ur\n" + 
						"                     where ur.user_id = u.id\n" + 
						"                       and ur.role_id = '"+roleId+"')";
		}
		
		if(account  != null && !"".equals(account)){
			sql += " and t.account ='"+account+"'";
		}
		
		sql += " order by t.id asc";
		Pager pager = findPagerBySqlForMap(sql, null, dmp);
		return pager;
	}

	public void delUserRole(Dmp dmp) throws Exception {
		String ids = (String) dmp.getAsString("ids");
		if(ids!=null&&!"".equals(ids)){
			String [] param=ids.split(","); 
			String sql = "delete from p_sys_role_user ur where ur.id in ("+ids.substring(0, ids.length()-1)+")";
			deleteBySQL(sql, null);
		}
	}

	public List queryOrgUnit(Dmp dmp) throws Exception {
		String userId = dmp.getAsString("_userid");
		String sql = "";
		List list = findAddMapBySql(sql, null);
		return list;
	}

	public Pager queryUserByOrgUnit(Dmp dmp) throws Exception {
		String orgId = dmp.getAsString("orgId");
		String sql = "select u.*, ou.id as rid\n" +
							"  from p_sys_user u, p_sys_org_unit_user ou\n" + 
							" where u.id = ou.user_id\n" + 
							"   and ou.org_id = '"+orgId+"'\n" + 
							" order by u.id desc";
		Pager pager = findPagerBySqlForMap(sql, null, dmp);
		return pager;
	}

	public void saveOrgUnitUser(PSysOrgUnitUser pSysOrgUnitUser)
			throws Exception {
		if(pSysOrgUnitUser.getId() == null || "".equals(pSysOrgUnitUser.getId())){
			pSysOrgUnitUser.setId(RandomString.getTimeSequence(20));
			save(pSysOrgUnitUser);
		}
	}

	public void delOrgUnitUser(Dmp dmp) throws Exception {
		String ids = (String) dmp.getAsString("ids");
		if(ids!=null&&!"".equals(ids)){
			String sql = "delete from p_sys_org_unit_user u where u.id in ("+ids.substring(0, ids.length()-1)+")";
			deleteBySQL(sql, null);
		}
	}

	public Pager queryOtherOrgUser(Dmp dmp) throws Exception {
		String account = dmp.getAsString("account");
		String orgId = dmp.getAsString("orgId");
		String roleType = dmp.getAsString("_roletype");
		String sql = null;
		if("adminRole".equals(roleType)){
			sql ="select t.*\n" +
							"  from p_sys_user t\n" + 
							" where 1 = 1\n" + 
							"   and (t.entityid = '"+dmp.getAsString("_entityid")+"' or t.entityid = '0')\n" + 
							"   and t.id not in (select u.id\n" + 
							"                      from p_sys_user u, p_sys_org_unit_user ou\n" + 
							"                     where ou.user_id = u.id\n" + 
							"                       and ou.org_id = '"+orgId+"')\n";
		}else{
			sql = "select t.*\n" +
					"  from p_sys_user t\n" + 
					" where t.id not in (select u.id\n" + 
					"                      from p_sys_user u, p_sys_org_unit_user ou\n" + 
					"                     where ou.user_id = u.id\n" + 
					"                       and ou.org_id = '"+orgId+"')";
		}
	
		if(account  != null && !"".equals(account)){
			sql += " and t.account ='"+account+"'";
		}
		
		sql += " order by t.id asc";
		Pager pager = findPagerBySqlForMap(sql, null, dmp);
		return pager;
	}

}
