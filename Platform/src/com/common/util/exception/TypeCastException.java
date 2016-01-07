package com.common.util.exception;

import java.io.PrintStream;
import java.io.PrintWriter;

/**
 * 时间处理异常类
 * @author lhw
 *
 */
public class TypeCastException extends RuntimeException{
	Throwable nested;

	  public TypeCastException()
	  {
	    this.nested = null;
	  }

	  public TypeCastException(String msg) {
	    super(msg);
	    this.nested = null;
	  }

	  public TypeCastException(String msg, Throwable nested) {
	    super(msg);
	    this.nested = null;
	    this.nested = nested;
	  }

	  public TypeCastException(Throwable nested)
	  {
	    this.nested = null;
	    this.nested = nested;
	  }

	  public String getMessage() {
	    if (this.nested != null) {
	      return super.getMessage() + " (" + this.nested.getMessage() + ")";
	    }
	    return super.getMessage();
	  }

	  public String getNonNestedMessage() {
	    return super.getMessage();
	  }

	  public Throwable getNested() {
	    if (this.nested == null) {
	      return this;
	    }
	    return this.nested;
	  }

	  public void printStackTrace() {
	    super.printStackTrace();
	    if (this.nested != null)
	      this.nested.printStackTrace();
	  }

	  public void printStackTrace(PrintStream ps) {
	    super.printStackTrace(ps);
	    if (this.nested != null)
	      this.nested.printStackTrace(ps);
	  }

	  public void printStackTrace(PrintWriter pw) {
	    super.printStackTrace(pw);
	    if (this.nested != null)
	      this.nested.printStackTrace(pw);
	  }
}
