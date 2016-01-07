package com.system.service.impl;

import java.util.List;
import java.util.Map;

import com.common.base.pojo.MenuVo;
import com.common.base.pojo.UserInfo;
import com.common.base.service.impl.HServiceImpl;
import com.common.util.data.map.Dmp;
import com.system.service.IndexService;

public class IndexServiceImpl extends HServiceImpl implements IndexService {

	public List listMenu(Dmp dmp) throws Exception {
		String roleType = dmp.getAsString("_roletype");
		String roleId = dmp.getAsString("_roleid");
		String sql =  "select distinct m.sortno, m.id, m.id as menu_id, m.name, m.parent_id, m.url, m.leaf\n" +
								"  from p_sys_menu m,p_sys_role_menu r\n" + 
								" where m.id = r.menu_id\n"; 
		           
		           if("busiRole".equals(roleType)){
		        	   sql+="  and m.parent_id = '02' and r.role_id = '"+roleId+"'";
		           }else if("adminRole".equals(roleType)){
		        	   sql+=" and m.parent_id = '01'  and r.role_id = '"+roleId+"'";
		           }else{
		        	   sql+=" and m.parent_id = '01' ";
		           }
					sql += " order by m.sortno asc";

		List list = findAddMapBySql(sql, null);
		return list;
	}

	public List<MenuVo> listNav(Dmp dmp) throws Exception {
		String menuId = dmp.getAsString("id");
		String roleType = dmp.getAsString("_roletype");
		String roleId = dmp.getAsString("_roleid");
		String sql =   ""; 
						        if("superRole".equals(roleType)){
						     	   sql = "select m.sortno, m.id, m.id as menu_id, m.name, m.parent_id, m.url, m.leaf\n" +
						     					 "  from p_sys_menu m\n" + 
						     					 " where m.parent_id = '"+menuId+"'\n" + 
						     					 " order by m.sortno asc";
						        }else{
						        	sql ="select m.sortno, m.id, m.id as menu_id, m.name, m.parent_id, m.url, m.leaf\n" +
											"  from p_sys_menu m, p_sys_role_menu rm\n" + 
											" where m.id = rm.menu_id\n" + 
											"   and m.parent_id = '"+menuId+"'\n" +
											" and rm.role_id = '"+roleId+"' order by m.sortno asc";
						        }

		List list = findAddMapBySql(sql, null);
		return list;
	}

	public Map getMenu(Dmp dmp) throws Exception {
		String menuId = dmp.getAsString("id");
		String sql = "select m.* from p_sys_menu m where m.id = '"+menuId+"'";
		List list = findAddMapBySql(sql, null);
		return (Map) list.get(0);
	}

	public Map getRoleAndOrg(Dmp dmp) throws Exception {
		String sql= "select temp1.name as rolename, temp2.name as orgname\n" +
							"  from (select r.id, r.name\n" + 
							"          from p_sys_role r\n" + 
							"         where r.id = '"+dmp.getAsString("_roleid")+"') temp1\n" + 
							"  left join (select ou.id, ou.name, rou.role_id\n" + 
							"               from p_sys_org_unit ou, p_sys_role_org_unit rou\n" + 
							"              where ou.id = rou.org_id\n" + 
							"                and ou.id = '"+dmp.getAsString("_orgid")+"') temp2 on temp1.id =\n" + 
							"                                                             temp2.role_id";

		List list = findAddMapBySql(sql, null);
		return  (Map) list.get(0);
	}

}
