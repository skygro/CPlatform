package com.common.util.data.map;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public abstract interface Dmp extends Map
{
  public abstract Integer getAsInteger(String paramString);

  public abstract Long getAsLong(String paramString);
  
  public abstract Short getAsShort(String paramString);

  public abstract String getAsString(String paramString);

  public abstract BigDecimal getAsBigDecimal(String paramString);

  public abstract Date getAsDate(String paramString);

  public abstract List getAsList(String paramString);

  public abstract Timestamp getAsTimestamp(String paramString);

  public abstract Boolean getAsBoolean(String paramString);

  public abstract void setDefaultAList(List paramList);

  public abstract void setDefaultBList(List paramList);

  public abstract List getDefaultAList();

  public abstract List getDefaultBList();

  public abstract void setDefaultJson(String paramString);

  public abstract String getDefaultJson();

  public abstract String toXml(String paramString);

  public abstract String toXml();

  public abstract String toJson();

  public abstract void println();

  public abstract String toJson(String paramString);

  public abstract void setSuccess(Boolean paramBoolean);

  public abstract Boolean getSuccess();

  public abstract void setMsg(String paramString);

  public abstract String getMsg();
}