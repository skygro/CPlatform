package com.system.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.SEQUENCE;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * PSysException entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "P_SYS_EXCEPTION", schema = "CPLATFORM")
public class PSysException implements java.io.Serializable {

	// Fields

	private String id;
	private String clazz;
	private String methodname;
	private Long activetime;
	private String exception;

	// Constructors

	/** default constructor */
	public PSysException() {
	}

	/** full constructor */
	public PSysException(String clazz, String methodname, Long activetime,
			String exception) {
		this.clazz = clazz;
		this.methodname = methodname;
		this.activetime = activetime;
		this.exception = exception;
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

	@Column(name = "CLAZZ", length = 200)
	public String getClazz() {
		return this.clazz;
	}

	public void setClazz(String clazz) {
		this.clazz = clazz;
	}

	@Column(name = "METHODNAME", length = 50)
	public String getMethodname() {
		return this.methodname;
	}

	public void setMethodname(String methodname) {
		this.methodname = methodname;
	}

	@Column(name = "ACTIVETIME", precision = 14, scale = 0)
	public Long getActivetime() {
		return this.activetime;
	}

	public void setActivetime(Long activetime) {
		this.activetime = activetime;
	}

	@Column(name = "EXCEPTION")
	public String getException() {
		return this.exception;
	}

	public void setException(String exception) {
		this.exception = exception;
	}

}