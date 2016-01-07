package com.system.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.SEQUENCE;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * PSysMonitor entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "P_SYS_MONITOR", schema = "CPLATFORM")
public class PSysMonitor implements java.io.Serializable {

	// Fields

	private String id;
	private Long starttime;
	private Integer costtime;
	private Integer effectrows;
	private String type;
	private String sqltext;

	// Constructors

	/** default constructor */
	public PSysMonitor() {
	}

	/** full constructor */
	public PSysMonitor(Long starttime, Integer costtime, Integer effectrows,
			String type, String sqltext) {
		this.starttime = starttime;
		this.costtime = costtime;
		this.effectrows = effectrows;
		this.type = type;
		this.sqltext = sqltext;
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

	@Column(name = "STARTTIME", precision = 14, scale = 0)
	public Long getStarttime() {
		return this.starttime;
	}

	public void setStarttime(Long starttime) {
		this.starttime = starttime;
	}

	@Column(name = "COSTTIME", precision = 8, scale = 0)
	public Integer getCosttime() {
		return this.costtime;
	}

	public void setCosttime(Integer costtime) {
		this.costtime = costtime;
	}

	@Column(name = "EFFECTROWS", precision = 8, scale = 0)
	public Integer getEffectrows() {
		return this.effectrows;
	}

	public void setEffectrows(Integer effectrows) {
		this.effectrows = effectrows;
	}

	@Column(name = "TYPE", length = 1)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "SQLTEXT")
	public String getSqltext() {
		return this.sqltext;
	}

	public void setSqltext(String sqltext) {
		this.sqltext = sqltext;
	}

}