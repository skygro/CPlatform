package com.system.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.SEQUENCE;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * PSysRoleOrgUnit entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "P_SYS_ROLE_ORG_UNIT", schema = "CPLATFORM")
public class PSysRoleOrgUnit implements java.io.Serializable {

	// Fields

	private String id;
	private String roleId;
	private String orgId;

	// Constructors

	/** default constructor */
	public PSysRoleOrgUnit() {
	}

	/** full constructor */
	public PSysRoleOrgUnit(String roleId, String orgId) {
		this.roleId = roleId;
		this.orgId = orgId;
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

	@Column(name = "ORG_ID", nullable = false, length = 20)
	public String getOrgId() {
		return this.orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

}