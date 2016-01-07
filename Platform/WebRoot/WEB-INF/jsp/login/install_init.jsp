<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/include/jsp/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>云平台研发中心</title>
	<link href="<%=path%>/include/css/dbconfig.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript"></script>
</head>
<body class="unieap body_bg" style="margin-top: 0px; overflow: hidden">
		<form action="${ctx}/control?requestCode=install_check" method="post" onsubmit="return subform();">
		<div id="login2">
			<div class="login_logo"></div>
			<div class="login_main"><div class="left"><img src="<%=path%>/include/image/login/loginimg.jpg" /></div>
			<div class="right">
				  <table width="96%" border="0" align="center" class="table">
				    <tr>
				      <td  height="30" align="right" class="tabletext">类型：</td>
				      <td width="290" height="30">
					      <span class="td_content">
					       <select name="dbType" class="input1">
					       		<option>ORACLE</option>
					       	    <option>MYSQL</option>
					       	    <option>SQL SERVER</option>
						  </select>
					      </span>
					  </td>
				    </tr>
				    <tr>
				      <td height="30" align="right" class="tabletext">地址：</td>
				      <td height="30">
					      <span class="td_content">
						      <input type="text" name="dbName" id="dbName" class="input1" value="${address}" />
					      </span>
				      </td>
				    </tr>
				    <tr>
				      <td align="right" class="tabletext">名称：</td>
				      <td height="30">
					      <span class="td_content">
						      <input type="text" name="dbUrl" id="dbUrl" class="input1" value="${dbname}" />
					      </span>
				      </td>
				    </tr>
				    <tr>
				      <td align="right" class="tabletext">端口：</td>
				      <td height="30">
					      <span class="td_content">
						      <input type="text" name="port" id="port" class="input1" value="${port}" />
					      </span>
				      </td>
				    </tr>
				    <tr>
				      <td height="30" align="right" class="tabletext">用户：</td>
				      <td height="30">
					      <span class="td_content">
						      <input type="text" name="dbUser" id="dbUser" class="input1"	value="${user}" />
					      </span>
				      </td>
				    </tr>
				    <tr>
				      <td height="30" align="right" class="tabletext">密码：</td>
				      <td height="30">
					      <span class="td_content">
						      <input type="password" name="dbPass" id="dbPass" class="input1" value="${psw}" />
					      </span>
				      </td>
				    </tr>
				    
				    <tr>
				      <td height="30" align="right" nowrap="nowrap" class="tabletext">初始化连接池：</td>
				      <td height="30">
					      <span class="td_content">
						      <input type="text" name="initialPoolSize" id="initialPoolSize" class="input1" value="${initialPoolSize}" />
					      </span>
				      </td>
				    </tr>
				    <tr>
				      <td height="30" align="right" class="tabletext">最小连接池：</td>
				      <td height="30">
					      <span class="td_content">
						      <input type="text" name="minPoolSize" id="minPoolSize" class="input1" value="${minPoolSize}" />
					      </span>
				      </td>
				    </tr>
				    <tr>
				      <td height="30" align="right" class="tabletext">最大连接池：</td>
				      <td height="30">
					      <span class="td_content">
						      <input type="text" name="maxPoolSize" id="maxPoolSize" class="input1" value="${maxPoolSize}" />
					      </span>
				      </td>
				    </tr>
				    <tr>
				      <td height="30" align="right" class="tabletext">最大执行数：</td>
				      <td height="30">
					      <span class="td_content">
						      <input type="text" name="maxStatements" id="maxStatements" class="input1" value="${maxStatements}" />
					      </span>
				      </td>
				    </tr>
				    <tr>
				    	<td height="10" colspan="2" nowrap="nowrap" align="center"><span style="font-size:13px;color:red;">
				    	${msg} <%request.getAttribute("msg"); %></span></td>
				    </tr>
				    <tr>
				    	<td height="50" colspan="2"><input type="submit" class="submit_btn" value="提交" /></td>
				    </tr>
				  </table>
			</div>
			</div>
			<div class="login_bottom"></div>
			</div>
		<div align="center"></div>
		</form>
</body>
</html>