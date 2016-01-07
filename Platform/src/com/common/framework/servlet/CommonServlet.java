package com.common.framework.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.common.util.data.sql.DataBaseUtil;
import com.common.util.resource.PropertiesFactory;
import com.common.util.resource.PropertiesFile;
import com.common.util.resource.PropertiesHelper;

public class CommonServlet  extends HttpServlet{

	private static final long serialVersionUID = 7078402570287755482L;
	
	public void destroy() {
		super.destroy();
	}
	
	 public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
			    excute(request, response);
	 }
	 
	  public void doPost(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException{
			    excute(request, response);
	 }
	  
	  public void excute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
			  String path = request.getParameter("requestCode");
			  response.sendRedirect("login/system_loginInit.pt");
//			  if ((path != null) && (!"".equals(path))){
//				  if("login".equals(path)){
//					  if(checkInstallSystem(request,response,"1")){
//						  response.sendRedirect("index/install_loginInit.pt");
//					  }else{
//						  response.sendRedirect("index/install_Init.pt");
//					  }
//				  }else{
//					  if(checkInstallSystem(request,response,"2")){
//						  initParam(request,response);
//						  response.sendRedirect("index/install_loginInit.pt");
//					  }else{
//						  request.setAttribute("msg", "数据库连接配置失败，请检查");
//					  }
//				  }
//			  }
	  }
	  
	  /**
	   * 验证配置文件及连接数据库
	   * @param request
	   * @param response
	   * @param type
	   * @return
	   */
	  private boolean checkInstallSystem(HttpServletRequest request, HttpServletResponse response,String type){
		    boolean check = false;
		    if("1".equals(type)){//根据初始化配置文件判断
		    	 PropertiesHelper pro =  PropertiesFactory.getPropertiesHelper(PropertiesFile.INIT_PATH);
				   if(pro!=null && !"".equals(pro)){
					    if ((pro.getValue("dbType") != null) && (pro.getValue("dbUser") != null) && (pro.getValue("dbPass") != null) && (pro.getValue("dbUrl") != null)) {
							  Connection conn =  DataBaseUtil.getConnection(pro.getValue("dbType"), pro.getValue("dbUrl"),pro.getValue("dbUser"), pro.getValue("dbPass"));
							  if(conn != null && !"".equals(conn)){
								  check = true;
							  }
					     }
				   }
		    }else{//根据当前获取参数判断
				    if (( request.getParameter("dbType") != null) && ( request.getParameter("dbUser") != null) && ( request.getParameter("dbPass") != null) &&  (request.getParameter("dbUrl") != null)) {
						  Connection conn =  DataBaseUtil.getConnection(request.getParameter("dbType"), request.getParameter("dbUrl"), request.getParameter("dbUser"), request.getParameter("dbPass"));
						  if(conn != null && !"".equals(conn)){
							  check = true;
						  }
				     }
		    }
		    return check;
	  }
	  
	  /**
	   * 设置数据库初始化配置
	   * @param request
	   * @param response
	   */
	  private void  initParam(HttpServletRequest request, HttpServletResponse response){
//		  PropertiesHelper pro =  PropertiesFactory.getPropertiesHelper(PropertiesFile.INIT_PATH);
		  Properties pro = PropertiesFactory.getPropertiesHelper( PropertiesFile.INIT_PATH).getObjProperties();
		  if(pro!=null&&!"".equals(pro)){
			  pro.setProperty("dbType", request.getParameter("dbType"));
			  pro.setProperty("dbUrl", request.getParameter("dbUrl"));
			  pro.setProperty("dbName", request.getParameter("dbName"));
			  pro.setProperty("port", request.getParameter("port"));
			  pro.setProperty("dbUser", request.getParameter("dbUser"));
			  pro.setProperty("dbPass", request.getParameter("dbPass"));
			  pro.setProperty("initialPoolSize", request.getParameter("initialPoolSize"));
			  pro.setProperty("minPoolSize", request.getParameter("minPoolSize"));
			  pro.setProperty("maxPoolSize", request.getParameter("maxPoolSize"));
			  pro.setProperty("maxStatements", request.getParameter("maxStatements"));
			  PropertiesFactory.setIntPropertiesHelper(pro,  PropertiesFile.INIT_PATH);
//			  pro.store(PropertiesFile.INIT_PATH);
		  }
	  }
	 
}
