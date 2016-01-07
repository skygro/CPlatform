package com.system.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.SEQUENCE;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * PSysParam entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "P_SYS_PARAM", schema = "CPLATFORM")
public class PSysParam implements java.io.Serializable {

	// Fields

	private String id;
	private String paramkey;
	private String paramvalue;
	private String remark;

	// Constructors

	/** default constructor */
	public PSysParam() {
	}

	/** minimal constructor */
	public PSysParam(String paramkey, String paramvalue) {
		this.paramkey = paramkey;
		this.paramvalue = paramvalue;
	}

	/** full constructor */
	public PSysParam(String paramkey, String paramvalue, String remark) {
		this.paramkey = paramkey;
		this.paramvalue = paramvalue;
		this.remark = remark;
	}

	// Property accessors
	@Id
	@Column(name = "ID", unique = true, nullable = false, length = 8)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "PARAMKEY", nullable = false, length = 50)
	public String getParamkey() {
		return this.paramkey;
	}

	public void setParamkey(String paramkey) {
		this.paramkey = paramkey;
	}

	@Column(name = "PARAMVALUE", nullable = false, length = 1000)
	public String getParamvalue() {
		return this.paramvalue;
	}

	public void setParamvalue(String paramvalue) {
		this.paramvalue = paramvalue;
	}

	@Column(name = "REMARK", length = 200)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}