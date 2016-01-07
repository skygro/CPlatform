package com.system.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.SEQUENCE;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * PSysOrgUnitUser entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "P_SYS_ORG_UNIT_USER", schema = "CPLATFORM")
public class PSysOrgUnitUser implements java.io.Serializable {

	// Fields

	private String id;
	private String orgId;
	private String userId;

	// Constructors

	/** default constructor */
	public PSysOrgUnitUser() {
	}

	/** full constructor */
	public PSysOrgUnitUser(String orgId, String userId) {
		this.orgId = orgId;
		this.userId = userId;
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

	@Column(name = "ORG_ID", nullable = false, length = 20)
	public String getOrgId() {
		return this.orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	@Column(name = "USER_ID", nullable = false, length = 20)
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}