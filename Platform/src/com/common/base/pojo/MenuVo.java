package com.common.base.pojo;

import java.util.ArrayList;
import java.util.List;

import com.common.util.data.ValidateUtil;

public class MenuVo extends BaseVo{
	 private String menuid;
	  private String menuname;
	  private String iconcls;
	  private String parentid;
	  private String request;
	  private String leaf;
	  private String isNotLast;
	  private String isRoot;
	  private String expanded;
	  private String menupath;
	  private String icon;
	  private String checked;
	  private String target;
	  private String systemid;
	  private String sortno;
	  private List<MenuVo> children;

	  public String toString()
	  {
	    StringBuffer str = new StringBuffer("{");
	    str.append("id:'").append(this.menuid).append("',");
	    str.append("text:'").append(this.menuname).append("'");
	    if (ValidateUtil.isNotEmpty(this.iconcls)) {
	      str.append(",").append("iconCls:'").append(this.iconcls).append("'");
	    }
	    if ((ValidateUtil.isNotEmpty(this.children)) && (this.children.size() > 0)) {
	      if ((ValidateUtil.isNotEmpty(this.leaf)) && ("0".equals(this.leaf)))
	        str.append(",").append("state:'").append(
	          ("true".equals(this.expanded)) || ("1".equals(this.expanded)) ? "open" : "closed")
	          .append("'");
	      else if (ValidateUtil.isNotEmpty(this.expanded)) {
	        str.append(",").append("state:'").append(
	          this.expanded.equals("1") ? "open" : "closed").append("'");
	      }
	      str.append(",").append("children:[");
	      for (int i = 0; i < this.children.size(); i++) {
	        if (i != 0) {
	          str.append(",");
	        }
	        str.append(((MenuVo)this.children.get(i)).toString());
	      }
	      str.append("]");
	    } else if (ValidateUtil.isNotEmpty(this.checked)) {
	      str.append(",").append("checked:").append(this.checked);
	    }
	    str.append(",attributes:{");
	    str.append("url:'").append(this.request).append("',");
	    str.append("pathCl:'").append(this.menupath).append("',");
	    str.append("target:'").append(this.target).append("'");
	    str.append("}");
	    str.append("}");
	    return str.toString();
	  }

	  public String toChildString() {
	    StringBuffer str = new StringBuffer("[");
	    if ((ValidateUtil.isNotEmpty(this.children)) && (this.children.size() > 0)) {
	      for (int i = 0; i < this.children.size(); i++) {
	        if (i != 0) {
	          str.append(",");
	        }
	        str.append(((MenuVo)this.children.get(i)).toString());
	      }
	    }
	    str.append("]");
	    return str.toString();
	  }

	  public String getSystemid() {
	    return this.systemid;
	  }

	  public void setSystemid(String systemid) {
	    this.systemid = systemid;
	  }

	  public String getTarget() {
	    return this.target;
	  }

	  public void setTarget(String target) {
	    this.target = target;
	  }

	  public String getMenuid()
	  {
	    if (this.menuid == null) {
	      this.menuid = "";
	    }
	    return this.menuid;
	  }

	  public void setMenuid(String menuid) {
	    this.menuid = menuid;
	  }

	  public String getMenuname() {
	    if (this.menuname == null) {
	      this.menuname = "";
	    }
	    return this.menuname;
	  }

	  public void setMenuname(String menuname) {
	    this.menuname = menuname;
	  }

	  public String getIsNotLast() {
	    return this.isNotLast;
	  }

	  public void setIsNotLast(String isNotLast) {
	    this.isNotLast = isNotLast;
	  }

	  public void setParentid(String parentid) {
	    this.parentid = parentid;
	  }

	  public void setRequest(String request) {
	    this.request = request;
	  }

	  public void setLeaf(String leaf) {
	    this.leaf = leaf;
	  }

	  public String getParentid() {
	    return this.parentid;
	  }

	  public String getRequest() {
	    return this.request;
	  }

	  public String getIsRoot() {
	    return this.isRoot;
	  }

	  public void setIsRoot(String isRoot) {
	    this.isRoot = isRoot;
	  }

	  public String getIconcls() {
	    return this.iconcls;
	  }

	  public void setIconcls(String iconcls) {
	    this.iconcls = iconcls;
	  }

	  public String getLeaf() {
	    return this.leaf;
	  }

	  public String getExpanded() {
	    return this.expanded;
	  }

	  public void setExpanded(String expanded) {
	    this.expanded = expanded;
	  }

	  public String getMenupath() {
	    return this.menupath;
	  }

	  public void setMenupath(String menupath) {
	    this.menupath = menupath;
	  }

	  public String getIcon() {
	    return this.icon;
	  }

	  public void setIcon(String icon) {
	    this.icon = icon;
	  }

	  public String getChecked() {
	    return this.checked;
	  }

	  public void setChecked(String checked) {
	    this.checked = checked;
	  }

	  public List<MenuVo> getChildren() {
	    if (this.children == null) {
	      this.children = new ArrayList();
	    }
	    return this.children;
	  }

	  public void setChildren(List<MenuVo> children) {
	    this.children = children;
	  }

	  public String getSortno() {
	    return this.sortno;
	  }

	  public void setSortno(String sortno) {
	    this.sortno = sortno;
	  }
}
