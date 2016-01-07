package com.common.util.io.json;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import com.common.util.data.ValidateUtil;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

public class JsonValueDateProcessor implements JsonValueProcessor{ private String format = "yyyy-MM-dd HH:mm:ss";

public JsonValueDateProcessor() {
}

public JsonValueDateProcessor(String format) {
  this.format = format;
}

public Object processArrayValue(Object value, JsonConfig jsonConfig)
{
  String[] obj = new String[0];
  if ((value instanceof java.util.Date[])) {
    SimpleDateFormat sf = new SimpleDateFormat(this.format);
    java.util.Date[] dates = (java.util.Date[])value;
    obj = new String[dates.length];
    for (int i = 0; i < dates.length; i++) {
      obj[i] = sf.format(dates[i]);
    }
  }
  if ((value instanceof Timestamp[])) {
    SimpleDateFormat sf = new SimpleDateFormat(this.format);
    Timestamp[] dates = (Timestamp[])value;
    obj = new String[dates.length];
    for (int i = 0; i < dates.length; i++) {
      obj[i] = sf.format(dates[i]);
    }
  }
  if ((value instanceof java.sql.Date[])) {
    SimpleDateFormat sf = new SimpleDateFormat(this.format);
    java.sql.Date[] dates = (java.sql.Date[])value;
    obj = new String[dates.length];
    for (int i = 0; i < dates.length; i++) {
      obj[i] = sf.format(dates[i]);
    }
  }
  return obj;
}

public Object processObjectValue(String key, Object value, JsonConfig jsonConfig)
{
  if (ValidateUtil.isEmpty(value))
    return "";
  if ((value instanceof Timestamp)) {
    String str = new SimpleDateFormat(this.format).format((Timestamp)value);
    return str;
  }if ((value instanceof java.util.Date)) {
    String str = new SimpleDateFormat(this.format)
      .format((java.util.Date)value);
    return str;
  }if ((value instanceof java.sql.Date)) {
    String str = new SimpleDateFormat(this.format)
      .format((java.sql.Date)value);
    return str;
  }
  return value.toString();
}

public String getFormat() {
  return this.format;
}

public void setFormat(String format) {
  this.format = format;
}

}
