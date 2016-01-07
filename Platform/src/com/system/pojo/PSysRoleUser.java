package com.system.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.SEQUENCE;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * PSysRoleUser entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "P_SYS_ROLE_USER", schema = "CPLATFORM")
public class PSysRoleUser implements java.io.Serializable {

	// Fields

	private String id;
	private String userId;
	private String roleId;
	private String roleType;

	// Constructors

	/** default constructor */
	public PSysRoleUser() {
	}

	/** minimal constructor */
	public PSysRoleUser(String userId, String roleId) {
		this.userId = userId;
		this.roleId = roleId;
	}

	/** full constructor */
	public PSysRoleUser(String userId, String roleId, String roleType) {
		this.userId = userId;
		this.roleId = roleId;
		this.roleType = roleType;
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

	@Column(name = "USER_ID", nullable = false, length = 20)
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "ROLE_ID", nullable = false, length = 20)
	public String getRoleId() {
		return this.roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	@Column(name = "ROLE_TYPE", length = 20)
	public String getRoleType() {
		return this.roleType;
	}

	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}

}