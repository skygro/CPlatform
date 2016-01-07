package com.system.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.SEQUENCE;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * PSysOrgUnit entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "P_SYS_ORG_UNIT", schema = "CPLATFORM")
public class PSysOrgUnit implements java.io.Serializable {

	// Fields

	private String id;
	private String name;
	private String parentId;
	private Short sortno;
	private String remark;
	private String enabled;
	private String type;
	private String district;
	private String orgCode;
	private String entityId;

	// Constructors

	/** default constructor */
	public PSysOrgUnit() {
	}

	/** minimal constructor */
	public PSysOrgUnit(String name, String parentId, String enabled) {
		this.name = name;
		this.parentId = parentId;
		this.enabled = enabled;
	}

	/** full constructor */
	public PSysOrgUnit(String name, String parentId, Short sortno,
			String remark, String enabled, String type, String district,
			String orgCode, String entityId) {
		this.name = name;
		this.parentId = parentId;
		this.sortno = sortno;
		this.remark = remark;
		this.enabled = enabled;
		this.type = type;
		this.district = district;
		this.orgCode = orgCode;
		this.entityId = entityId;
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

	@Column(name = "NAME", nullable = false, length = 300)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "PARENT_ID", nullable = false, length = 100)
	public String getParentId() {
		return this.parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	@Column(name = "SORTNO", precision = 4, scale = 0)
	public Short getSortno() {
		return this.sortno;
	}

	public void setSortno(Short sortno) {
		this.sortno = sortno;
	}

	@Column(name = "REMARK", length = 100)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "ENABLED", nullable = false, length = 2)
	public String getEnabled() {
		return this.enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

	@Column(name = "TYPE", length = 2)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "DISTRICT", length = 20)
	public String getDistrict() {
		return this.district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	@Column(name = "ORG_CODE", length = 300)
	public String getOrgCode() {
		return this.orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	@Column(name = "ENTITY_ID", length = 100)
	public String getEntityId() {
		return this.entityId;
	}

	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}

}