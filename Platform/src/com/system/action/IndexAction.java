package com.system.action;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.common.base.action.BaseAction;
import com.common.base.pojo.UserInfo;
import com.common.framework.listener.SessionContainer;
import com.common.util.data.map.Dmp;
import com.system.service.IndexService;

public class IndexAction extends BaseAction{

	private static final long serialVersionUID = 8010058289453770304L;
	
	private IndexService indexService;

	public IndexService getIndexService() {
		return indexService;
	}

	public void setIndexService(IndexService indexService) {
		this.indexService = indexService;
	}
	
	public String indexInit() throws Exception{
		Dmp dmp = getParamsAsDmp();
		//获取角色名称与部门名称
		Map p = indexService.getRoleAndOrg(dmp);
		SessionContainer sessionContainer = getSessionContainer();
		UserInfo userinfo = sessionContainer.getUserInfo();
		dmp.put("account", userinfo.getAccount());
		dmp.put("username", userinfo.getUsername());
		dmp.put("rolename", p.get("rolename"));
		dmp.put("orgname", p.get("orgname"));
		this.setAttributeFromDmp(dmp);
		return "indexView";
	}
	
	public String menuList() throws Exception{
		Dmp dmp = getParamsAsDmp();
		List list = indexService.listMenu(dmp);
		String menuList = JSON.toJSONString(list, true);
		this.setAttributeFromDmp(dmp);
		this.getResponse().setContentType("text/html;charset=UTF-8"); 
		this.getResponse().getWriter().write(menuList);
		return null;
	}
	
	public String navList() throws Exception{
		Dmp dmp = getParamsAsDmp();
		List list = indexService.listNav(dmp);
		String navList = JSON.toJSONString(list, true);
		this.setAttributeFromDmp(dmp);
		this.getResponse().setContentType("text/html;charset=UTF-8"); 
		this.getResponse().getWriter().write(navList);
		return null;
	}
	
	public String getMenu() throws Exception{
		Dmp dmp = getParamsAsDmp();
		Map p = indexService.getMenu(dmp);
		String menu = JSON.toJSONString(p, true);
		this.setAttributeFromDmp(dmp);
		this.getResponse().setContentType("text/html;charset=UTF-8"); 
		this.getResponse().getWriter().write(menu);
		return null;
	}
	
}
