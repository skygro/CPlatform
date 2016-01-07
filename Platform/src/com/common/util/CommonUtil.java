package com.common.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class CommonUtil {
	
	public static List<TreeNodes> getTreeList(List<Map> deptList, String parentid,boolean openStatus) {
		List<TreeNodes> list = new ArrayList<TreeNodes>();
		for (Map p : deptList) {
			if (parentid.equals(p.get("parent_id"))) {
				TreeNodes node = new TreeNodes();
				node.setText((String)p.get("text"));
				node.setId((String)p.get("id"));
				List<TreeNodes> childlist = getTreeList(deptList, (String)p.get("id"),openStatus);
				if (childlist.size() > 0) {
					node.setChildren(childlist);
					node.setLeaf(false);
					if(openStatus){
						node.setState("open");
					}else{
						node.setState("closed");
					}
				} else {
					node.setLeaf(true);
				}
				String relateId = (String) p.get("relateid");
				if(relateId!=null && !"".equals(relateId)){
					node.setChecked(true);
				}
				node.setCascadeCheck(false);
				list.add(node);
			}
		}
		return list;
	}

}
