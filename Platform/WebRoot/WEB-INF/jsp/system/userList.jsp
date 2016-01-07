<%@page contentType="text/html;charset=UTF-8"%>
<%@ include file="/include/jsp/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>云服务平台</title>
	<script type="text/javascript" src="${ctx}/js/system/user.js"></script>
	<script type="text/javascript" src="${ctx}/js/system/common.js"></script>
</head>
<body class="easyui-layout">
		<div id="f_panel"  class="easyui-panel" data-options="collapsible:false,title:'用户查询'" style="width:100%;height:14%">
		     <form  id="f_query" name="f_query"  method="post">
			     <table align="center" border="0" width="50%">
					  <tr>	
					        <td align="right">账号:</td>
					        <td width="30%" align="left">
					        	<input id="account" type="text" name="account" class="c_textbox" />
					        </td>
					        <td align="right">姓名:</td>
					        <td width="30%" align="left">
					        	<input id="username" type="text" name="username" class="c_textbox" />
					        </td>
					  </tr>
				  </table>
		      </form>
	     </div>   
	     <div id="g_panel" class="easyui-panel" data-options="title:'用户列表'" style="width:100%;height:86%;padding:2px;">
	      	<table id="dg" height="100%" width="100%" 
					data-options="rownumbers:true,
					singleSelect:false,
					autoRowHeight:false,
					pagination:true,
					pageSize:10">
				 <thead>
					<tr>
						<th data-options="field:'id',checkbox:true" ></th>
						<th field="account" width="15%" align="center">
							账号
						</th>
						<th field="username" width="15%" align="center">
							姓名
						</th>
						<th field="sex" width="10%" align="center" formatter="Common.SexFormatter">
							性别
						</th>
						<th field="enabled" width="15%" align="center" formatter="Common.Enabled">
							启用状态
						</th>
						<th field="locked" width="10%" align="center" formatter="Common.Locked">
							锁定状态
						</th>
						<th field="caid" width="20%" align="center">
							身份证号码
						</th>
						<th field="phone" width="15%" align="center">
							手机号码
						</th>
						<th field="email" width="15%" align="center">
							邮箱地址
						</th>
					</tr>
				</thead>
			</table>
	     </div> 
	     
   		    <div id="win" class="easyui-window" title="新增用户" style="padding:8px;"   
		        data-options="iconCls:'icon-save',closed:true,minimizable:false,collapsible:false">
			</div>  
	     
</body>
</html>