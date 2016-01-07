package com.system.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.common.base.service.impl.HServiceImpl;
import com.common.util.RandomString;
import com.common.util.exception.DaoException;
import com.system.pojo.PSysMenu;
import com.system.pojo.PSysRoleMenu;
import com.system.service.MenuService;

public class MenuServiceImpl extends HServiceImpl implements MenuService{

	public List menuTree(Map p) throws Exception {
		String roleId = (String) p.get("roleId");
		String roleType = (String) p.get("_roletype");
		String sql = null;
		if("adminRole".equals(roleType)){
			sql = "select temp.*, rf.id as relateid\n" +
							"  from (select f.id, f.name as text, f.parent_id, f.sortno\n" + 
							"          from p_sys_menu f\n" + 
							"         where 1 = 1\n" + 
							"           and f.menu_type <> '1') temp\n" + 
							"  left join p_sys_role_menu rf on rf.menu_id = temp.id\n" + 
							"                              and rf.role_id ='"+roleId+"'\n" + 
							" order by temp.sortno asc";
		}else{
			sql =  "select f.id, f.name as text, f.parent_id, rf.id as relateid\n" +
					"  from p_sys_menu f\n" + 
					"  left join p_sys_role_menu rf on rf.menu_id = f.id\n" + 
					"                             and rf.role_id = '"+roleId+"'\n" + 
					" order by f.sortno asc";
		}
		List list = findAddMapBySql(sql, null);
		return list;
	}

	public void saveOrUpdate(PSysMenu pSysMenu) throws DaoException {
		if(pSysMenu.getId() == null || "".equals(pSysMenu.getId())){
			pSysMenu.setId(RandomString.getTimeSequence(20));
			save(pSysMenu);
		}else{
			update(pSysMenu);
		}
	}

	public PSysMenu getMenuInfo(String id) throws Exception {
		return (PSysMenu) get(PSysMenu.class, id);

	}

	public void delMenu(String id) throws Exception {
		
		String sqls = "delete from p_sys_role_menu rm\n" +
								" where rm.id in (select m.id\n" + 
								"                   from p_sys_menu m\n" + 
								"                  start with m.id = '"+id+"'\n" + 
								"                 connect by prior m.id = m.parent_id)";

		deleteBySQL(sqls, null);
		
		String sql =  "delete from p_sys_menu f\n" +
								" where f.id in (select fu.id\n" + 
								"                  from p_sys_menu fu\n" + 
								"                 start with fu.id = '"+id+"'\n" + 
								"                connect by prior fu.id = fu.parent_id)";
		deleteBySQL(sql, null);
	}

	public Map queryMenuRoleId(String menuId, String roleId) throws Exception {
		String sql = "select * from p_sys_role_menu rf where rf.role_id = '"+roleId+"' and rf.menu_id = '"+menuId+"'";
		List list = findAddMapBySql(sql, null);
		Map p = new HashMap();
		if(list.size()>0){
			p = (Map) list.get(0);
		}
		return p;
	}

	public void saveMenuToRole(PSysRoleMenu pSysRoleMenu) throws Exception {
		if(pSysRoleMenu.getId() == null || "".equals(pSysRoleMenu.getId())){
			pSysRoleMenu.setId(RandomString.getTimeSequence(20));
			save(pSysRoleMenu);
		}else{
			update(pSysRoleMenu);
		}
	}

	public void delRoleMenu(String roleId) throws Exception {
		String sql = "delete from p_sys_role_menu t where t.role_id = '"+roleId+"'";
		deleteBySQL(sql, null);
	}

}
