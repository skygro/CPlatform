package com.common.util.io.json;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import net.sf.json.JsonConfig;
import net.sf.json.xml.XMLSerializer;

import org.apache.struts2.json.JSONException;
import org.apache.struts2.json.JSONUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.common.util.data.ValidateUtil;
import com.common.util.data.map.BaseDmp;
import com.common.util.data.map.Dmp;
import com.common.util.page.Pager;

public class DealJSON {
	private static Logger log = LoggerFactory.getLogger(DealJSON.class);

	  private static JsonConfig config = new JsonConfig();

	  static {
	    config.registerJsonValueProcessor(java.sql.Date.class, 
	      new JsonValueDateProcessor());
	    config.registerJsonValueProcessor(Timestamp.class, 
	      new JsonValueDateProcessor());
	    config.registerJsonValueProcessor(java.util.Date.class, 
	      new JsonValueDateProcessor());
	    config.registerJsonValueProcessor(java.sql.Date.class, 
	      new JsonValueDateProcessor());
	  }

	  public static String encodeList2JObj(List list)
	  {
	    Map p = new HashMap();
	    p.put("success", Boolean.valueOf(true));
	    if ((list == null) || (list.size() <= 0))
	      p.put("datalist", "");
	    else {
	      p.put("datalist", list);
	    }
	    return encodeObject2Json(p);
	  }

	  public static String encodeMap2JObj(Map map)
	  {
	    Map jsonMap = new HashMap();
	    jsonMap.put("success", Boolean.valueOf(true));
	    if ((map == null) || (map.isEmpty()))
	      jsonMap.put("datamap", "");
	    else {
	      jsonMap.put("datamap", map);
	    }
	    return encodeObject2Json(jsonMap);
	  }

	  public static String encodeTj2JObj(List list, Map info)
	  {
	    Map p = new HashMap();
	    p.put("success", Boolean.valueOf(true));
	    if ((list == null) || (list.size() <= 0))
	      p.put("datalist", "");
	    else {
	      p.put("datalist", list);
	    }
	    if ((info == null) || (info.isEmpty()))
	      p.put("dataSum", "");
	    else {
	      p.put("dataSum", info);
	    }
	    return encodeObject2Json(p);
	  }

	  public static String encodePager2JObj(Pager page)
	  {
	    String subJsonString = encodeObject2Json(page.getList());
	    String jsonString = "{totalRows:" + page.getTotalRows() + ", datalist:" + 
	      subJsonString + "}";
	    return jsonString;
	  }

	  public static String encodeJson2Xml(String json)
	  {
	    try
	    {
	      XMLSerializer serializer = new XMLSerializer();
	      JSON jsonObject = JSONSerializer.toJSON(json);
	      return serializer.write(jsonObject);
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	    return "";
	  }

	  public static String encodeXml2Json(String xml)
	  {
	    XMLSerializer xmlSerializer = new XMLSerializer();
	    return xmlSerializer.read(xml).toString();
	  }

	  public static Object encodeJson2Object(String str)
	    throws JSONException
	  {
	    return JSONUtil.deserialize(str);
	  }

	  public static final String encodeObject2Json(Object pObject)
	  {
	    String jsonString = "[]";
	    if (ValidateUtil.isEmpty(pObject)) {
	      log.warn("传入的对象为空,不能将其序列化为Json格式.请检查!");
	    }
	    else if ((pObject instanceof ArrayList)) {
	      JSONArray jsonArray = JSONArray.fromObject(pObject, config);
	      jsonString = jsonArray.toString();
	    } else {
	      JSONObject jsonObject = JSONObject.fromObject(pObject, config);
	      jsonString = jsonObject.toString();
	    }

	    return jsonString;
	  }

	  public static final String encodeObject2Json(Object pObject, String dateFormat)
	  {
	    String jsonString = "[]";
	    if (ValidateUtil.isEmpty(pObject)) {
	      log.warn("传入的Java对象为空,不能将其序列化为Json资料格式.请检查!");
	    } else {
	      JsonConfig cfg = new JsonConfig();
	      cfg.registerJsonValueProcessor(Timestamp.class, 
	        new JsonValueDateProcessor(dateFormat));
	      cfg.registerJsonValueProcessor(java.util.Date.class, 
	        new JsonValueDateProcessor(dateFormat));
	      cfg.registerJsonValueProcessor(java.sql.Date.class, 
	        new JsonValueDateProcessor(dateFormat));
	      if ((pObject instanceof ArrayList)) {
	        JSONArray jsonArray = JSONArray.fromObject(pObject, cfg);
	        jsonString = jsonArray.toString();
	      } else {
	        JSONObject jsonObject = JSONObject.fromObject(pObject, cfg);
	        jsonString = jsonObject.toString();
	      }
	    }
	    return jsonString;
	  }

	  public static String encodeDmp2FormJson(Dmp pDmp, String pFormatString)
	  {
	    String jsonString = "";
	    Map p = new HashMap();
	    p.put("success", 
	      ValidateUtil.isEmpty(pDmp.getAsString("success")) ? "true" : 
	      pDmp.getAsString("success"));
	    p.put("data", pDmp);
	    if (ValidateUtil.isEmpty(pFormatString))
	      jsonString = encodeObject2Json(p);
	    else {
	      jsonString = encodeObject2Json(p, pFormatString);
	    }
	    if (log.isInfoEnabled()) {
	      log.info("序列化后的JSON资料输出:\n" + jsonString);
	    }
	    return jsonString;
	  }

	  public static Dmp parseSingleJson2Dmp(String jsonString)
	  {
	    Dmp dmp = new BaseDmp();
	    if (ValidateUtil.isEmpty(jsonString)) {
	      return dmp;
	    }
	    JSONObject jb = JSONObject.fromObject(jsonString);
	    dmp = (BaseDmp)JSONObject.toBean(jb, BaseDmp.class);
	    return dmp;
	  }

	  public static List parseJson2List(String jsonString)
	  {
	    List list = new ArrayList();
	    JSONObject jbJsonObject = JSONObject.fromObject(jsonString);
	    Iterator iterator = jbJsonObject.keySet().iterator();
	    while (iterator.hasNext()) {
	      Dmp dmp = parseSingleJson2Dmp(jbJsonObject.getString(iterator
	        .next().toString()));
	      list.add(dmp);
	    }
	    return list;
	  }

	  public static void main(String[] args)
	  {
	    String str = "{\"a\":\"b\", \"c\":\"d\"}";
	    str = "{\"D\":[" + 
	      str + 
	      "],\"A\":[\"a\",\"b\"],\"B\":[\"a\",\"b\"],\"C\":[\"a\",\"b\"]}";
	    try {
	      Object obj = encodeJson2Object(str);
	      if ((obj instanceof Map)) {
	        Iterator ita = ((Map)obj).entrySet().iterator();
	        while (ita.hasNext()) {
	          Map.Entry entry = (Map.Entry)ita.next();
	          Object o = entry.getValue();
	          System.out.println(entry.getKey() + "=" + entry.getValue());
	        }
	      }
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	  }
}
