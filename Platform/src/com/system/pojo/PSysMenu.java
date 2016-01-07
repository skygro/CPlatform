package com.system.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.SEQUENCE;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * PSysMenu entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "P_SYS_MENU", schema = "CPLATFORM")
public class PSysMenu implements java.io.Serializable {

	// Fields

	private String id;
	private String name;
	private String parentId;
	private String iconcls;
	private String expanded;
	private String url;
	private String leaf;
	private Short sortno;
	private String remark;
	private String icon;
	private String menuType;
	private String status;
	private String linkId;
	private String nodeId;
	private String systemId;
	private String deskicon;
	private String target;

	// Constructors

	/** default constructor */
	public PSysMenu() {
	}

	/** minimal constructor */
	public PSysMenu(String name, String parentId) {
		this.name = name;
		this.parentId = parentId;
	}

	/** full constructor */
	public PSysMenu(String name, String parentId, String iconcls,
			String expanded, String url, String leaf, Short sortno,
			String remark, String icon, String menuType, String status,
			String linkId, String nodeId, String systemId, String deskicon,
			String target) {
		this.name = name;
		this.parentId = parentId;
		this.iconcls = iconcls;
		this.expanded = expanded;
		this.url = url;
		this.leaf = leaf;
		this.sortno = sortno;
		this.remark = remark;
		this.icon = icon;
		this.menuType = menuType;
		this.status = status;
		this.linkId = linkId;
		this.nodeId = nodeId;
		this.systemId = systemId;
		this.deskicon = deskicon;
		this.target = target;
	}

	// Property accessors
	@Id
	@Column(name = "ID", unique = true, nullable = false, length = 50)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "NAME", nullable = false, length = 50)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "PARENT_ID", nullable = false, length = 60)
	public String getParentId() {
		return this.parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	@Column(name = "ICONCLS", length = 50)
	public String getIconcls() {
		return this.iconcls;
	}

	public void setIconcls(String iconcls) {
		this.iconcls = iconcls;
	}

	@Column(name = "EXPANDED", length = 2)
	public String getExpanded() {
		return this.expanded;
	}

	public void setExpanded(String expanded) {
		this.expanded = expanded;
	}

	@Column(name = "URL", length = 100)
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "LEAF", length = 2)
	public String getLeaf() {
		return this.leaf;
	}

	public void setLeaf(String leaf) {
		this.leaf = leaf;
	}

	@Column(name = "SORTNO", precision = 4, scale = 0)
	public Short getSortno() {
		return this.sortno;
	}

	public void setSortno(Short sortno) {
		this.sortno = sortno;
	}

	@Column(name = "REMARK", length = 200)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "ICON", length = 50)
	public String getIcon() {
		return this.icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	@Column(name = "MENU_TYPE", length = 2)
	public String getMenuType() {
		return this.menuType;
	}

	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}

	@Column(name = "STATUS", length = 1)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "LINK_ID", length = 20)
	public String getLinkId() {
		return this.linkId;
	}

	public void setLinkId(String linkId) {
		this.linkId = linkId;
	}

	@Column(name = "NODE_ID", length = 20)
	public String getNodeId() {
		return this.nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	@Column(name = "SYSTEM_ID", length = 2)
	public String getSystemId() {
		return this.systemId;
	}

	public void setSystemId(String systemId) {
		this.systemId = systemId;
	}

	@Column(name = "DESKICON", length = 50)
	public String getDeskicon() {
		return this.deskicon;
	}

	public void setDeskicon(String deskicon) {
		this.deskicon = deskicon;
	}

	@Column(name = "TARGET", length = 20)
	public String getTarget() {
		return this.target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

}