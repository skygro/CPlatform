package com.common.util.exception;

public class DaoException extends Exception{
	
	 private String appCode;
	  private String errorMsg;
	  private String prcName;

	  public DaoException(){}

	  public DaoException(String message) {}

	  public DaoException(Throwable throwable) {
	    super(throwable);
	  }

	  public DaoException(String message, Throwable throwable) {
	    super(message, throwable);
	  }

	  public DaoException(String prcName, String errorMsg)
	  {
	    super("\n出现错误了， 错误代码如下：\n调用存储过程[" + prcName + "]发生错误,错误原因：[" + 
	      errorMsg + "]");
	    setErrorMsg(errorMsg);
	  }

	  public DaoException(String prcName, String appCode, String errorMsg)
	  {
	    super("\n出现错误了， 错误代码如下：\n调用存储过程[" + prcName + "]发生错误,错误编码为：[" + 
	      appCode + "] 错误原因：[" + errorMsg + "]");
	    setAppCode(appCode);
	    setPrcName(prcName);
	    setErrorMsg(errorMsg);
	  }

	  public String getAppCode() {
	    return this.appCode;
	  }

	  public void setAppCode(String appCode) {
	    this.appCode = appCode;
	  }

	  public String getErrorMsg() {
	    return this.errorMsg;
	  }

	  public void setErrorMsg(String errorMsg) {
	    this.errorMsg = errorMsg;
	  }

	  public String getPrcName() {
	    return this.prcName;
	  }

	  public void setPrcName(String prcName) {
	    this.prcName = prcName;
	  }

}
