package com.system.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.SEQUENCE;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * PSysRoleMenu entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "P_SYS_ROLE_MENU", schema = "CPLATFORM")
public class PSysRoleMenu implements java.io.Serializable {

	// Fields

	private String id;
	private String roleId;
	private String menuId;

	// Constructors

	/** default constructor */
	public PSysRoleMenu() {
	}

	/** full constructor */
	public PSysRoleMenu(String roleId, String menuId) {
		this.roleId = roleId;
		this.menuId = menuId;
	}

	// Property accessors
	@Id
	@Column(name = "ID", unique = true, nullable = false, length = 20)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "ROLE_ID", nullable = false, length = 20)
	public String getRoleId() {
		return this.roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	@Column(name = "MENU_ID", nullable = false, length = 20)
	public String getMenuId() {
		return this.menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

}