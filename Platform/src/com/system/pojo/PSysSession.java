package com.system.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.SEQUENCE;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * PSysSession entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "P_SYS_SESSION", schema = "CPLATFORM")
public class PSysSession implements java.io.Serializable {

	// Fields

	private String id;
	private String username;
	private String account;
	private String createtime;
	private String loginIp;
	private String userId;
	private String explorer;

	// Constructors

	/** default constructor */
	public PSysSession() {
	}

	/** full constructor */
	public PSysSession(String username, String account, String createtime,
			String loginIp, String userId, String explorer) {
		this.username = username;
		this.account = account;
		this.createtime = createtime;
		this.loginIp = loginIp;
		this.userId = userId;
		this.explorer = explorer;
	}

	// Property accessors
	@Id
	@Column(name = "ID", unique = true, nullable = false, length = 100)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "USERNAME", length = 20)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "ACCOUNT", length = 30)
	public String getAccount() {
		return this.account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	@Column(name = "CREATETIME", length = 20)
	public String getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	@Column(name = "LOGIN_IP", length = 15)
	public String getLoginIp() {
		return this.loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	@Column(name = "USER_ID", length = 20)
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "EXPLORER", length = 15)
	public String getExplorer() {
		return this.explorer;
	}

	public void setExplorer(String explorer) {
		this.explorer = explorer;
	}

}