package com.system.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.SEQUENCE;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * PSysRole entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "P_SYS_ROLE", schema = "CPLATFORM")
public class PSysRole implements java.io.Serializable {

	// Fields

	private String id;
	private String name;
	private String parentId;
	private String type;
	private String remark;
	private String locked;
	private String entityId;

	// Constructors

	/** default constructor */
	public PSysRole() {
	}

	/** minimal constructor */
	public PSysRole(String name, String type) {
		this.name = name;
		this.type = type;
	}

	/** full constructor */
	public PSysRole(String name, String parentId, String type, String remark,String entityId,
			String locked) {
		this.name = name;
		this.parentId = parentId;
		this.type = type;
		this.remark = remark;
		this.entityId = entityId;
		this.locked = locked;
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

	@Column(name = "NAME", nullable = false, length = 50)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "PARENT_ID", length = 100)
	public String getParentId() {
		return this.parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	@Column(name = "TYPE", nullable = false, length = 2)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "REMARK", length = 200)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "LOCKED", length = 2)
	public String getLocked() {
		return this.locked;
	}

	public void setLocked(String locked) {
		this.locked = locked;
	}

	public String getEntityId() {
		return entityId;
	}

	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}

}