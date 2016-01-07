package com.common.base.action;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.dom4j.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.common.base.pojo.UserInfo;
import com.common.constants.Constants;
import com.common.framework.listener.SessionContainer;
import com.common.util.Utils;
import com.common.util.data.map.BaseDmp;
import com.common.util.data.map.Dmp;
import com.common.util.io.json.DealJSON;
import com.common.util.io.xml.DealXML;
import com.common.util.page.Pager;
import com.opensymphony.xwork2.ActionSupport;

public class BaseAction  extends ActionSupport{
	
	private static Logger logger = LoggerFactory.getLogger(BaseAction.class);
	
	private static final long serialVersionUID = -8096452575295587708L;
	
	  public HttpServletRequest getRequest() {
	    return ServletActionContext.getRequest();
	  }

	  public HttpServletResponse getResponse() {
	    return ServletActionContext.getResponse();
	  }
	
	  public Dmp getParamsAsDmp() {
	    Dmp dmp = Utils.getParamAsDmp(getRequest());
	    UserInfo info = getSessionContainer().getUserInfo();
	    if (info != null) {
	      dmp.put("_userid", info.getUserId());
	      dmp.put("_roleid", info.getRoleId());
	      dmp.put("_entityid", info.getEntityId());
	      dmp.put("_orgid", info.getOrgId());
	      String roleType = info.getRoleTpye();
	      if(roleType!=null && !"".equals(roleType)){
	    	  dmp.put("_roletype",roleType);
	      }
	      
	      String entityId = info.getEntityId();
	      if(entityId!=null && !"".equals(entityId)){
	    	  dmp.put("_entityid",entityId);
	      }
	    }
	    return dmp;
	  }
	  
	  public void setAttributeFromDmp(Dmp dmp){
	    logger.info("写出数据为：" + dmp.toString());
	    Utils.setAttributeFromDmp(dmp, getRequest());
	  }
	  
	  public void writeResponse(Object obj)
	  {
	    try
	    {
	      logger.info("序列化输出为：" + obj.toString());
	      getResponse().setContentType("text/plain");
	      getResponse().getWriter().write(obj.toString());
	      getResponse().getWriter().flush();
	      getResponse().getWriter().close();
	    } catch (IOException e) {
	      e.printStackTrace();
	    }
	  }

	  public void writeResponse(Object obj, String contentType)
	  {
	    try
	    {
	      logger.info("序列化输出为：" + obj.toString());
	      getResponse().setContentType(contentType);
	      getResponse().getWriter().write(obj.toString());
	      getResponse().getWriter().flush();
	      getResponse().getWriter().close();
	    } catch (IOException e) {
	      e.printStackTrace();
	    }
	  }
	  
	  public void writeXmlAsPager(Pager page)
	  {
	    Document doc = DealXML.createDoc(page);
	    writeResponse(doc.asXML());
	  }

	  public void writeXmlAsList(List list)
	  {
	    Document doc = DealXML.createDoc(list);
	    writeResponse(doc.asXML());
	  }

	  public void writeAttributeXmlAsList(List list)
	  {
	    Document doc = DealXML.createDocAsAttribute(list);
	    writeResponse(doc.asXML());
	  }

	  public void writeJsonAsList(List list)
	  {
	    String jsonStr = DealJSON.encodeList2JObj(list);
	    writeResponse(jsonStr);
	  }

	  public void writeJsonAsTj(List list, Map info)
	  {
	    String jsonStr = DealJSON.encodeTj2JObj(list, info);
	    writeResponse(jsonStr);
	  }

	  public void writeJsonAsPager(Pager page)
	  {
	    String jsonStr = DealJSON.encodePager2JObj(page);
	    writeResponse(jsonStr);
	  }

	  public void writeJsonAsMap(Map map)
	  {
	    String jsonStr = DealJSON.encodeMap2JObj(map);
	    writeResponse(jsonStr);
	  }

	  public void writeJsonAsMsg(String msg)
	  {
	    Dmp dmp = new BaseDmp(Constants.TRUE, msg);
	    writeResponse(dmp.toJson());
	  }

	  public void writeJsonAsObject(Object obj)
	  {
	    writeResponse(DealJSON.encodeObject2Json(obj));
	  }
	  
	  protected SessionContainer getSessionContainer()
	  {
	    try
	    {
	      return WebUtils.getSessionContainer(getRequest());
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	    return null;
	  }
	
}
