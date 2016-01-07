package com.common.util.data;

import java.util.Collection;
import java.util.Map;

/**
 * 数据验证
 * @author lhw
 *
 */
public class ValidateUtil {
	     private static String[] HanDigiStr = { "零", "壹", "贰", "叁", 
		    "肆", "伍", "陆", "柒", "捌", "玖" };

		  private static String[] HanDiviStr = { "", "拾", "佰", "仟", "万", 
		    "拾", "佰", "仟", "亿", "拾", "佰", "仟", "万", "拾", "佰", "仟", "亿", "拾", 
		    "佰", "仟", "万", "拾", "佰", "仟" };

		  public static boolean isEmpty(String someStr)
		  {
		    return (someStr == null) || (someStr.trim().length() == 0) || 
		      (someStr.trim().equalsIgnoreCase("null"));
		  }

		  public static boolean isEmpty(Long someLong)
		  {
		    return (someLong == null) || (someLong.intValue() == 0);
		  }
		  
		  public static boolean isEmpty(Object pObj)
		  {
		    if (pObj == null)
		      return true;
		    if (pObj == "")
		      return true;
		    if ((pObj instanceof String)) {
		      if (((String)pObj).length() == 0)
		        return true;
		    }
		    else if ((pObj instanceof Collection)) {
		      if (((Collection)pObj).size() == 0)
		        return true;
		    }
		    else if (((pObj instanceof Map)) && 
		      (((Map)pObj).size() == 0)) {
		      return true;
		    }

		    return false;
		  }
		  
		  public static boolean isNotEmpty(Object pObj)
		  {
		    if (pObj == null)
		      return false;
		    if (pObj == "")
		      return false;
		    if ((pObj instanceof String)) {
		      if (((String)pObj).length() == 0)
		        return false;
		    }
		    else if ((pObj instanceof Collection)) {
		      if (((Collection)pObj).size() == 0)
		        return false;
		    }
		    else if (((pObj instanceof Map)) && 
		      (((Map)pObj).size() == 0)) {
		      return false;
		    }

		    return true;
		  }
}
