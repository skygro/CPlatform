package com.system.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.common.base.service.impl.HServiceImpl;
import com.common.util.data.map.BaseDmp;
import com.common.util.data.map.Dmp;
import com.common.util.exception.DaoException;
import com.common.util.page.Pager;
import com.common.util.security.DesUtil;
import com.system.service.LoginService;

public class LoginServiceImpl extends HServiceImpl implements LoginService{

	public Map getUserInfo(Dmp dmp){
		String account =dmp.getAsString("account");
		String password = dmp.getAsString("password");
		DesUtil des = new DesUtil();
		String sql =   "select u.*,\n" +
								"       (select distinct ru.role_type\n" + 
								"          from p_sys_role_user ru\n" + 
								"         where ru.user_id = u.id\n" + 
								"           and ru.role_type = 'superRole') as superRole,\n" + 
								"       (select distinct ru.role_type\n" + 
								"          from p_sys_role_user ru\n" + 
								"         where ru.user_id = u.id\n" + 
								"           and ru.role_type = 'adminRole') as adminRole,\n" + 
								"       (select distinct ru.role_type\n" + 
								"          from p_sys_role_user ru\n" + 
								"         where ru.user_id = u.id\n" + 
								"           and ru.role_type = 'busiRole') as busiRole\n" + 
								"  from p_sys_user u\n" + 
								" where 1 = 1\n" + 
								"   and u.account = '"+account+"'\n" + 
								"   and u.password = '"+DesUtil.encryptBasedDes(password)+"'";

		 List<Dmp> list = new ArrayList<Dmp>();
		try {
			list = findAddMapBySql(sql, null);
		} catch (DaoException e) {
			e.printStackTrace();
		}
		 Map  userInfo = new HashMap();
		 if(list.size()>0){
			 userInfo = (Map) list.get(0);
		 }
		return userInfo;
	}

	public List queryAdminRole(Dmp dmp)  throws Exception{
		String userId = dmp.getAsString("_userid");
		String sql =  "select r.id, r.name\n" +
								"  from p_sys_role r, p_sys_role_user u\n" + 
								" where r.id = u.role_id\n" + 
								"   and u.user_id = '"+userId+"'\n" + 
								"   and r.type <> 'busiRole'";

        List list = findAddMapBySql(sql, null);
		return list;
	}

	public List queryOrgUnit(Dmp dmp) throws Exception {
		String userId = dmp.getAsString("_userid");
		String sql ="select ou.id,\n" +
							"       (select org.name from p_sys_org_unit org where org.type = 'center') || '->' ||\n" + 
							"       ou.name as name\n" + 
							"  from p_sys_user u, p_sys_org_unit ou, p_sys_org_unit_user ouu\n" + 
							" where ouu.org_id = ou.id\n" + 
							"   and ouu.user_id = u.id\n" + 
							"   and u.id = '"+userId+"'\n" + 
							"   and ou.type = 'unit' order by ouu.id asc";

		List list = findAddMapBySql(sql, null);
		return list;
	}
	
	public List queryBusiRole(Dmp dmp) throws Exception {
		String orgId = dmp.getAsString("orgId");
		String sql ="select r.id, r.name\n" +
							"  from p_sys_org_unit      ou,\n" + 
							"       p_sys_role_org_unit rou,\n" + 
							"       p_sys_role          r,\n" + 
							"       p_sys_role_user     ru\n" + 
							" where r.id = rou.role_id\n" + 
							"   and r.id = ru.role_id\n" + 
							"   and ru.user_id = '"+dmp.getAsString("_userid")+"'\n" + 
							"   and ou.id = rou.org_id\n" + 
							"   and ou.id = '"+orgId+"'";

		List list = findAddMapBySql(sql, null);
		return list;
	}

}
