package com.system.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.SEQUENCE;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * PSysIcon entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "P_SYS_ICON", schema = "CPLATFORM")
public class PSysIcon implements java.io.Serializable {

	// Fields

	private String id;
	private String filename;
	private String cssname;

	// Constructors

	/** default constructor */
	public PSysIcon() {
	}

	/** full constructor */
	public PSysIcon(String filename, String cssname) {
		this.filename = filename;
		this.cssname = cssname;
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

	@Column(name = "FILENAME", nullable = false, length = 50)
	public String getFilename() {
		return this.filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	@Column(name = "CSSNAME", nullable = false, length = 50)
	public String getCssname() {
		return this.cssname;
	}

	public void setCssname(String cssname) {
		this.cssname = cssname;
	}

}