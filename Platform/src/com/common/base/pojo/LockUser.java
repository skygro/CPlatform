package com.common.base.pojo;

import java.io.Serializable;

public class LockUser implements Serializable{

	private static final long serialVersionUID = -5066372815060036801L;

	  private String userCode;
	  private int errorNum = 0;

	  private long startTime = 0L;

	  public String getUserCode() {
	    return this.userCode;
	  }

	  public void setUserCode(String userCode) {
	    this.userCode = userCode;
	  }

	  public int getErrorNum() {
	    return this.errorNum;
	  }

	  public void setErrorNum(int errorNum) {
	    this.errorNum = errorNum;
	  }

	  public long getStartTime() {
	    return this.startTime;
	  }

	  public void setStartTime(long startTime) {
	    this.startTime = startTime;
	  }

}
