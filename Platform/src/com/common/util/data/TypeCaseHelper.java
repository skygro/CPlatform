package com.common.util.data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.math.BigDecimal;
import java.sql.Clob;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import com.common.util.exception.TypeCastException;

public class TypeCaseHelper {
	public static Object convert(Object obj, String type, String format)
		    throws TypeCastException
		  {
		    Locale locale = new Locale("zh", "CN", "");
		    if (obj == null)
		      return null;
		    if (obj.getClass().getName().equals(type))
		      return obj;
		    if (("Object".equals(type)) || ("java.lang.Object".equals(type)))
		      return obj;
		    String fromType = null;
		    if ((obj instanceof String)) {
		      fromType = "String";
		      String str = (String)obj;
		      if (("String".equals(type)) || ("java.lang.String".equals(type)))
		        return obj;
		      if (str.length() == 0)
		        return null;
		      if (("Boolean".equals(type)) || ("java.lang.Boolean".equals(type))) {
		        Boolean value = null;
		        if (str.equalsIgnoreCase("TRUE"))
		          value = new Boolean(true);
		        else
		          value = new Boolean(false);
		        return value;
		      }
		      if (("Double".equals(type)) || ("java.lang.Double".equals(type)))
		        try {
		          Number tempNum = getNf(locale).parse(str);
		          return new Double(tempNum.doubleValue());
		        } catch (ParseException e) {
		          throw new TypeCastException("Could not convert " + str + 
		            " to " + type + ": ", e);
		        }
		      if (("BigDecimal".equals(type)) || 
		        ("java.math.BigDecimal".equals(type)))
		        try {
		          BigDecimal retBig = new BigDecimal(str);
		          int iscale = str.indexOf(".");
		          int keylen = str.length();
		          if (iscale > -1) {
		            iscale = keylen - (iscale + 1);
		            return retBig.setScale(iscale, 5);
		          }
		          return retBig.setScale(0, 5);
		        }
		        catch (Exception e) {
		          throw new TypeCastException("Could not convert " + str + 
		            " to " + type + ": ", e);
		        }
		      if (("Float".equals(type)) || ("java.lang.Float".equals(type)))
		        try {
		          Number tempNum = getNf(locale).parse(str);
		          return new Float(tempNum.floatValue());
		        } catch (ParseException e) {
		          throw new TypeCastException("Could not convert " + str + 
		            " to " + type + ": ", e);
		        }
		      if (("Long".equals(type)) || ("java.lang.Long".equals(type)))
		        try {
		          NumberFormat nf = getNf(locale);
		          nf.setMaximumFractionDigits(0);
		          Number tempNum = nf.parse(str);
		          return new Long(tempNum.longValue());
		        } catch (ParseException e) {
		          throw new TypeCastException("Could not convert " + str + 
		            " to " + type + ": ", e);
		        }
		      if (("Short".equals(type)) || ("java.lang.Short".equals(type)))
			        try {
			          NumberFormat nf = getNf(locale);
			          nf.setMaximumFractionDigits(0);
			          Number tempNum = nf.parse(str);
			          return new Short(tempNum.shortValue());
			        } catch (ParseException e) {
			          throw new TypeCastException("Could not convert " + str + 
			            " to " + type + ": ", e);
			        }
		      if (("Integer".equals(type)) || ("java.lang.Integer".equals(type)))
		        try {
		          NumberFormat nf = getNf(locale);
		          nf.setMaximumFractionDigits(0);
		          Number tempNum = nf.parse(str);
		          return new Integer(tempNum.intValue());
		        } catch (ParseException e) {
		          throw new TypeCastException("Could not convert " + str + 
		            " to " + type + ": ", e);
		        }
		      if (("Date".equals(type)) || ("java.sql.Date".equals(type))) {
		        if ((format == null) || (format.length() == 0))
		          try {
		            return java.sql.Date.valueOf(str);
		          } catch (Exception e) {
		            try {
		              DateFormat df = null;
		              if (locale != null)
		                df = DateFormat.getDateInstance(3, locale);
		              else
		                df = DateFormat.getDateInstance(3);
		              java.util.Date fieldDate = df.parse(str);
		              return new java.sql.Date(fieldDate.getTime());
		            } catch (ParseException e1) {
		              throw new TypeCastException("Could not convert " + 
		                str + " to " + type + ": ", e);
		            }
		          }
		        try {
		          SimpleDateFormat sdf = new SimpleDateFormat(format);
		          java.util.Date fieldDate = sdf.parse(str);
		          return new java.sql.Date(fieldDate.getTime());
		        } catch (ParseException e) {
		          throw new TypeCastException("Could not convert " + str + 
		            " to " + type + ": ", e);
		        }
		      }
		      if (("Timestamp".equals(type)) || ("java.sql.Timestamp".equals(type))) {
		        if (str.length() == 10)
		          str = str + " 00:00:00";
		        if ((format == null) || (format.length() == 0))
		          try {
		            return Timestamp.valueOf(str);
		          } catch (Exception e) {
		            try {
		              DateFormat df = null;
		              if (locale != null)
		                df = DateFormat.getDateTimeInstance(3, 3, 
		                  locale);
		              else
		                df = DateFormat.getDateTimeInstance(3, 3);
		              java.util.Date fieldDate = df.parse(str);
		              return new Timestamp(fieldDate.getTime());
		            } catch (ParseException e1) {
		              throw new TypeCastException("Could not convert " + 
		                str + " to " + type + ": ", e);
		            }
		          }
		        try {
		          SimpleDateFormat sdf = new SimpleDateFormat(format);
		          java.util.Date fieldDate = sdf.parse(str);
		          return new Timestamp(fieldDate.getTime());
		        } catch (ParseException e) {
		          throw new TypeCastException("Could not convert " + str + 
		            " to " + type + ": ", e);
		        }
		      }
		      throw new TypeCastException("Conversion from " + fromType + 
		        " to " + type + " not currently supported");
		    }
		    if ((obj instanceof BigDecimal)) {
		      fromType = "BigDecimal";
		      BigDecimal bigD = (BigDecimal)obj;
		      if ("String".equals(type))
		        return getNf(locale).format(bigD.doubleValue());
		      if (("BigDecimal".equals(type)) || 
		        ("java.math.BigDecimal".equals(type)))
		        return obj;
		      if ("Double".equals(type))
		        return new Double(bigD.doubleValue());
		      if ("Float".equals(type))
		        return new Float(bigD.floatValue());
		      if ("Long".equals(type))
		        return new Long(Math.round(bigD.doubleValue()));
		      if ("Integer".equals(type)) {
		        return new Integer((int)Math.round(bigD.doubleValue()));
		      }
		      throw new TypeCastException("Conversion from " + fromType + 
		        " to " + type + " not currently supported");
		    }if ((obj instanceof Double)) {
		      fromType = "Double";
		      Double dbl = (Double)obj;
		      if (("String".equals(type)) || ("java.lang.String".equals(type)))
		        return getNf(locale).format(dbl.doubleValue());
		      if (("Double".equals(type)) || ("java.lang.Double".equals(type)))
		        return obj;
		      if (("Float".equals(type)) || ("java.lang.Float".equals(type)))
		        return new Float(dbl.floatValue());
		      if (("Long".equals(type)) || ("java.lang.Long".equals(type)))
		        return new Long(Math.round(dbl.doubleValue()));
		      if (("Integer".equals(type)) || ("java.lang.Integer".equals(type)))
		        return new Integer((int)Math.round(dbl.doubleValue()));
		      if (("BigDecimal".equals(type)) || 
		        ("java.math.BigDecimal".equals(type))) {
		        return new BigDecimal(dbl.toString());
		      }
		      throw new TypeCastException("Conversion from " + fromType + 
		        " to " + type + " not currently supported");
		    }if ((obj instanceof Float)) {
		      fromType = "Float";
		      Float flt = (Float)obj;
		      if ("String".equals(type))
		        return getNf(locale).format(flt.doubleValue());
		      if (("BigDecimal".equals(type)) || 
		        ("java.math.BigDecimal".equals(type)))
		        return new BigDecimal(flt.doubleValue());
		      if ("Double".equals(type))
		        return new Double(flt.doubleValue());
		      if ("Float".equals(type))
		        return obj;
		      if ("Long".equals(type))
		        return new Long(Math.round(flt.doubleValue()));
		      if ("Integer".equals(type)) {
		        return new Integer((int)Math.round(flt.doubleValue()));
		      }
		      throw new TypeCastException("Conversion from " + fromType + 
		        " to " + type + " not currently supported");
		    }if ((obj instanceof Long)) {
		      fromType = "Long";
		      Long lng = (Long)obj;
		      if (("String".equals(type)) || ("java.lang.String".equals(type)))
		        return getNf(locale).format(lng.longValue());
		      if (("Double".equals(type)) || ("java.lang.Double".equals(type)))
		        return new Double(lng.doubleValue());
		      if (("Float".equals(type)) || ("java.lang.Float".equals(type)))
		        return new Float(lng.floatValue());
		      if (("BigDecimal".equals(type)) || 
		        ("java.math.BigDecimal".equals(type)))
		        return new BigDecimal(lng.toString());
		      if (("Long".equals(type)) || ("java.lang.Long".equals(type)))
		        return obj;
		      if (("Integer".equals(type)) || ("java.lang.Integer".equals(type))) {
		        return new Integer(lng.intValue());
		      }
		      throw new TypeCastException("Conversion from " + fromType + 
		        " to " + type + " not currently supported");
		    }if ((obj instanceof Integer)) {
		      fromType = "Integer";
		      Integer intgr = (Integer)obj;
		      if (("String".equals(type)) || ("java.lang.String".equals(type)))
		        return getNf(locale).format(intgr.longValue());
		      if (("Double".equals(type)) || ("java.lang.Double".equals(type)))
		        return new Double(intgr.doubleValue());
		      if (("Float".equals(type)) || ("java.lang.Float".equals(type)))
		        return new Float(intgr.floatValue());
		      if (("BigDecimal".equals(type)) || 
		        ("java.math.BigDecimal".equals(type))) {
		        String str = intgr.toString();
		        BigDecimal retBig = new BigDecimal(intgr.doubleValue());
		        int iscale = str.indexOf(".");
		        int keylen = str.length();
		        if (iscale > -1) {
		          iscale = keylen - (iscale + 1);
		          return retBig.setScale(iscale, 5);
		        }
		        return retBig.setScale(0, 5);
		      }

		      if (("Long".equals(type)) || ("java.lang.Long".equals(type)))
		        return new Long(intgr.longValue());
		      if (("Integer".equals(type)) || ("java.lang.Integer".equals(type))) {
		        return obj;
		      }
		      throw new TypeCastException("Conversion from " + fromType + 
		        " to " + type + " not currently supported");
		    }if ((obj instanceof java.sql.Date)) {
		      fromType = "Date";
		      java.sql.Date dte = (java.sql.Date)obj;
		      if (("String".equals(type)) || ("java.lang.String".equals(type))) {
		        if ((format == null) || (format.length() == 0)) {
		          return dte.toString();
		        }
		        SimpleDateFormat sdf = new SimpleDateFormat(format);
		        return sdf.format(new java.util.Date(dte.getTime()));
		      }
		      if (("Date".equals(type)) || ("java.sql.Date".equals(type)))
		        return obj;
		      if (("Time".equals(type)) || ("java.sql.Time".equals(type)))
		        throw new TypeCastException("Conversion from " + fromType + 
		          " to " + type + " not currently supported");
		      if (("Timestamp".equals(type)) || ("java.sql.Timestamp".equals(type))) {
		        return new Timestamp(dte.getTime());
		      }
		      throw new TypeCastException("Conversion from " + fromType + 
		        " to " + type + " not currently supported");
		    }if ((obj instanceof Timestamp)) {
		      fromType = "Timestamp";
		      Timestamp tme = (Timestamp)obj;
		      if (("String".equals(type)) || ("java.lang.String".equals(type))) {
		        if ((format == null) || (format.length() == 0)) {
		          return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
		            .format(tme).toString();
		        }
		        SimpleDateFormat sdf = new SimpleDateFormat(format);
		        return sdf.format(new java.util.Date(tme.getTime()));
		      }
		      if (("Date".equals(type)) || ("java.sql.Date".equals(type)))
		        return new java.sql.Date(tme.getTime());
		      if (("Time".equals(type)) || ("java.sql.Time".equals(type)))
		        return new Time(tme.getTime());
		      if (("Timestamp".equals(type)) || ("java.sql.Timestamp".equals(type))) {
		        return obj;
		      }
		      throw new TypeCastException("Conversion from " + fromType + 
		        " to " + type + " not currently supported");
		    }if ((obj instanceof Boolean)) {
		      fromType = "Boolean";
		      Boolean bol = (Boolean)obj;
		      if (("Boolean".equals(type)) || ("java.lang.Boolean".equals(type)))
		        return bol;
		      if (("String".equals(type)) || ("java.lang.String".equals(type)))
		        return bol.toString();
		      if (("Integer".equals(type)) || ("java.lang.Integer".equals(type))) {
		        if (bol.booleanValue()) {
		          return new Integer(1);
		        }
		        return new Integer(0);
		      }
		      throw new TypeCastException("Conversion from " + fromType + 
		        " to " + type + " not currently supported");
		    }
		    if ((obj instanceof Clob)) {
		      if ("String".equals(type))
		        try {
		          Reader reader = ((Clob)obj).getCharacterStream();
		          BufferedReader br = new BufferedReader(reader);
		          StringBuilder sb = new StringBuilder();
		          String str;
		          while ((str = br.readLine()) != null)
		          {
		            sb.append(str);
		          }
		          return sb.toString();
		        } catch (SQLException e) {
		          e.printStackTrace();
		        } catch (IOException e) {
		          e.printStackTrace();
		        }
		      else {
		        throw new TypeCastException("Conversion from Clob to " + type + 
		          " not currently supported");
		      }
		    }
		    if (("String".equals(type)) || ("java.lang.String".equals(type))) {
		      return obj.toString();
		    }
		    throw new TypeCastException("Conversion from " + 
		      obj.getClass().getName() + " to " + type + 
		      " not currently supported");
		  }

		  private static NumberFormat getNf(Locale locale) {
		    NumberFormat nf = null;
		    if (locale == null)
		      nf = NumberFormat.getNumberInstance();
		    else
		      nf = NumberFormat.getNumberInstance(locale);
		    nf.setGroupingUsed(false);
		    return nf;
		  }

		  public static Boolean convert2SBoolean(Object obj) throws TypeCastException {
		    return (Boolean)convert(obj, "Boolean", null);
		  }

		  public static Integer convert2Integer(Object obj) throws TypeCastException {
		    return (Integer)convert(obj, "Integer", null);
		  }

		  public static String convert2String(Object obj) throws TypeCastException {
		    return (String)convert(obj, "String", null);
		  }

		  public static String convert2String(Object obj, String defaultValue) throws TypeCastException
		  {
		    Object s = convert(obj, "String", null);
		    if (s != null) {
		      return (String)s;
		    }
		    return "";
		  }

		  public static Long convert2Long(Object obj) throws TypeCastException {
		    return (Long)convert(obj, "Long", null);
		  }

		  public static Double convert2Double(Object obj) throws TypeCastException {
		    return (Double)convert(obj, "Double", null);
		  }

		  public static BigDecimal convert2BigDecimal(Object obj, int scale) throws TypeCastException
		  {
		    return ((BigDecimal)convert(obj, "BigDecimal", null)).setScale(scale, 
		      5);
		  }

		  public static java.sql.Date convert2SqlDate(Object obj, String format) throws TypeCastException
		  {
		    return (java.sql.Date)convert(obj, "Date", format);
		  }

		  public static Timestamp convert2Timestamp(Object obj, String format) throws TypeCastException
		  {
		    return (Timestamp)convert(obj, "Timestamp", format);
		  }

}
