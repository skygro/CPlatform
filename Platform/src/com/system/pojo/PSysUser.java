package com.system.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.SEQUENCE;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * PSysUser entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "P_SYS_USER", schema = "CPLATFORM")
public class PSysUser implements java.io.Serializable {

	// Fields

	private String id;
	private String username;
	private String account;
	private String password;
	private String sex;
	private String locked;
	private String remark;
	private String usertype;
	private String enabled;
	private String theme;
	private String panel;
	private String rutype;
	private String mainurl;
	private String email;
	private String phone;
	private String caid;
	private String entityId;

	// Constructors

	/** default constructor */
	public PSysUser() {
	}

	/** minimal constructor */
	public PSysUser(String username, String account, String password,
			String sex, String locked, String enabled) {
		this.username = username;
		this.account = account;
		this.password = password;
		this.sex = sex;
		this.locked = locked;
		this.enabled = enabled;
	}

	/** full constructor 
	 * @param entityId */
	public PSysUser(String username, String account, String password,
			String sex, String locked, String remark, String usertype,
			String enabled, String theme, String panel, String rutype,
			String mainurl, String email, String phone, String caid,String eneityId, String entityId) {
		this.username = username;
		this.account = account;
		this.password = password;
		this.sex = sex;
		this.locked = locked;
		this.remark = remark;
		this.usertype = usertype;
		this.enabled = enabled;
		this.theme = theme;
		this.panel = panel;
		this.rutype = rutype;
		this.mainurl = mainurl;
		this.email = email;
		this.phone = phone;
		this.caid = caid;
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

	@Column(name = "USERNAME", nullable = false, length = 100)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "ACCOUNT", nullable = false, length = 30)
	public String getAccount() {
		return this.account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	@Column(name = "PASSWORD", nullable = false, length = 50)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "SEX", nullable = false, length = 2)
	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Column(name = "LOCKED", nullable = false, length = 2)
	public String getLocked() {
		return this.locked;
	}

	public void setLocked(String locked) {
		this.locked = locked;
	}

	@Column(name = "REMARK", length = 50)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "USERTYPE", length = 2)
	public String getUsertype() {
		return this.usertype;
	}

	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}

	@Column(name = "ENABLED", nullable = false, length = 2)
	public String getEnabled() {
		return this.enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

	@Column(name = "THEME", length = 50)
	public String getTheme() {
		return this.theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	@Column(name = "PANEL", length = 50)
	public String getPanel() {
		return this.panel;
	}

	public void setPanel(String panel) {
		this.panel = panel;
	}

	@Column(name = "RUTYPE", length = 2)
	public String getRutype() {
		return this.rutype;
	}

	public void setRutype(String rutype) {
		this.rutype = rutype;
	}

	@Column(name = "MAINURL", length = 100)
	public String getMainurl() {
		return this.mainurl;
	}

	public void setMainurl(String mainurl) {
		this.mainurl = mainurl;
	}

	@Column(name = "EMAIL", length = 30)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "PHONE", length = 20)
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "CAID", length = 20)
	public String getCaid() {
		return this.caid;
	}

	public void setCaid(String caid) {
		this.caid = caid;
	}
	@Column(name = "ENTITYID", length = 20)
	public String getEntityId() {
		return entityId;
	}

	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}

}