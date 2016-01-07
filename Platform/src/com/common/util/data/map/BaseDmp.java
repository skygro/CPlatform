package com.common.util.data.map;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.common.util.data.TypeCaseHelper;
import com.common.util.io.json.DealJSON;
import com.common.util.io.xml.DealXML;

public class BaseDmp extends HashMap implements Dmp{

	private static final long serialVersionUID = 5518507075289834L;
	
	public static BaseDmp toTjHashMap(Map p)
	  {
	    BaseDmp m = new BaseDmp();
	    Iterator it = p.keySet().iterator();
	    while (it.hasNext()) {
	      Object key = it.next();
	      m.put(key, p.get(key));
	    }
	    return m;
	  }

	  public BaseDmp() {
	  }

	  public BaseDmp(String key, Object value) {
	    put(key, value);
	  }

	  public BaseDmp(Boolean success) {
	    setSuccess(success);
	  }

	  public BaseDmp(Boolean success, String msg) {
	    setSuccess(success);
	    setMsg(msg);
	  }

	  public BigDecimal getAsBigDecimal(String key)
	  {
	    Object obj = TypeCaseHelper.convert(get(key), "BigDecimal", null);
	    if (obj != null) {
	      return (BigDecimal)obj;
	    }
	    return null;
	  }

	  public Date getAsDate(String key)
	  {
	    Object obj = TypeCaseHelper.convert(get(key), "Date", "yyyy-MM-dd");
	    if (obj != null) {
	      return (Date)obj;
	    }
	    return null;
	  }

	  public Integer getAsInteger(String key)
	  {
	    Object obj = TypeCaseHelper.convert(get(key), "Integer", null);
	    if (obj != null) {
	      return (Integer)obj;
	    }
	    return null;
	  }

	  public Long getAsLong(String key)
	  {
	    Object obj = TypeCaseHelper.convert(get(key), "Long", null);
	    if (obj != null) {
	      return (Long)obj;
	    }
	    return null;
	  }

	  public String getAsString(String key)
	  {
	    Object obj = TypeCaseHelper.convert(get(key), "String", null);
	    if (obj != null) {
	      return (String)obj;
	    }
	    return "";
	  }

	  public List getAsList(String key)
	  {
	    return (List)get(key);
	  }

	  public Timestamp getAsTimestamp(String key)
	  {
	    Object obj = TypeCaseHelper.convert(get(key), "Timestamp", 
	      "yyyy-MM-dd HH:mm:ss");
	    if (obj != null) {
	      return (Timestamp)obj;
	    }
	    return null;
	  }

	  public Boolean getAsBoolean(String key)
	  {
	    Object obj = TypeCaseHelper.convert(get(key), "Boolean", null);
	    if (obj != null) {
	      return (Boolean)obj;
	    }
	    return null;
	  }

	  public void setDefaultAList(List pList)
	  {
	    put("defaultAList", pList);
	  }

	  public void setDefaultBList(List pList)
	  {
	    put("defaultBList", pList);
	  }

	  public List getDefaultAList()
	  {
	    return (List)get("defaultAList");
	  }

	  public List getDefaultBList()
	  {
	    return (List)get("defaultBList");
	  }

	  public void setDefaultJson(String jsonString)
	  {
	    put("defaultJsonString", jsonString);
	  }

	  public String getDefaultJson()
	  {
	    return getAsString("defaultJsonString");
	  }

	  public String toXml(String pStyle)
	  {
	    String strXml = null;
	    if (pStyle.equals("0"))
	    {
	      strXml = DealXML.parseLMap2Xml(this, "root", "row");
	    } else if (pStyle.equals("1"))
	    {
	      strXml = DealXML.parseLMap2Xml(this, "root");
	    }return strXml;
	  }

	  public String toXml()
	  {
	    String strXml = null;

	    strXml = DealXML.parseLMap2Xml(this, "root");
	    return strXml;
	  }

	  public String toJson()
	  {
	    String strJson = null;
	    strJson = DealJSON.encodeObject2Json(this);
	    return strJson;
	  }

	  public String toJson(String pFormat)
	  {
	    String strJson = null;
	    strJson = DealJSON.encodeObject2Json(this, pFormat);
	    return strJson;
	  }

	  public void setSuccess(Boolean pSuccess)
	  {
	    put("success", pSuccess);
	    pSuccess.booleanValue();
	  }

	  public Boolean getSuccess()
	  {
	    return getAsBoolean("success");
	  }

	  public void setMsg(String pMsg)
	  {
	    put("msg", pMsg);
	  }

	  public String getMsg()
	  {
	    return getAsString("msg");
	  }

	  public void println()
	  {
	    System.out.println(this);
	  }

	  public Short getAsShort(String key)
	  {
	    Object obj = TypeCaseHelper.convert(get(key), "Short", null);
	    if (obj != null) {
	      return (Short)obj;
	    }
	    return null;
	  }
	}