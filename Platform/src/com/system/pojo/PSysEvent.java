package com.system.pojo;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.SEQUENCE;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * PSysEvent entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "P_SYS_EVENT", schema = "CPLATFORM")
public class PSysEvent implements java.io.Serializable {

	// Fields

	private String id;
	private String userId;
	private String account;
	private String username;
	private String description;
	private Long activetime;
	private String requestpath;
	private String methodname;
	private BigDecimal costtime;

	// Constructors

	/** default constructor */
	public PSysEvent() {
	}

	/** full constructor */
	public PSysEvent(String userId, String account, String username,
			String description, Long activetime, String requestpath,
			String methodname, BigDecimal costtime) {
		this.userId = userId;
		this.account = account;
		this.username = username;
		this.description = description;
		this.activetime = activetime;
		this.requestpath = requestpath;
		this.methodname = methodname;
		this.costtime = costtime;
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

	@Column(name = "USER_ID", length = 20)
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "ACCOUNT", length = 30)
	public String getAccount() {
		return this.account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	@Column(name = "USERNAME", length = 20)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "DESCRIPTION", length = 100)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "ACTIVETIME", precision = 14, scale = 0)
	public Long getActivetime() {
		return this.activetime;
	}

	public void setActivetime(Long activetime) {
		this.activetime = activetime;
	}

	@Column(name = "REQUESTPATH", length = 200)
	public String getRequestpath() {
		return this.requestpath;
	}

	public void setRequestpath(String requestpath) {
		this.requestpath = requestpath;
	}

	@Column(name = "METHODNAME", length = 50)
	public String getMethodname() {
		return this.methodname;
	}

	public void setMethodname(String methodname) {
		this.methodname = methodname;
	}

	@Column(name = "COSTTIME", precision = 22, scale = 0)
	public BigDecimal getCosttime() {
		return this.costtime;
	}

	public void setCosttime(BigDecimal costtime) {
		this.costtime = costtime;
	}

}