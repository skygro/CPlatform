<%@page contentType="text/html;charset=UTF-8"%>
<%@ include file="/include/jsp/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>云平台研发中心</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta HTTP-EQUIV="Pragma" CONTENT="no-cache" />
		<meta HTTP-EQUIV="Cache-Control" CONTENT="no-cache" />
		<meta HTTP-EQUIV="Expires" CONTENT="0" />
		<script type="text/javascript" src="<%=path%>/include/js/switch.js"></script>
		<script type="text/javascript" src="<%=path%>/include/js/common.js"></script>
		<link href="<%=path%>/include/css/login.css" rel="stylesheet" type="text/css" />
		 
	</head>
	<body class="unieap body_bg" style="margin-top: 0px; overflow: hidden">
		<div class="login_header">
			<div class="login_main">
			
		    </div>
		</div> 
		<div id="win" class="easyui-window" title="请选择角色"    
	        data-options="minimizable:false,closable:false,collapsible:false"> 
	        <form id="s_form" method="post">  
			    <div id="tt" class="easyui-tabs">
				    <c:if test='${obj.superrole=="superRole" || obj.adminrole == "adminRole"}'>
					    <div title="管理角色">
						        <table width="570" border="0">
						            <tr><td style="margin-top:20%;">&nbsp;</td></tr>
						        	<tr>
						        	 <c:if test='${obj.superrole=="superRole"}'>
    						        	<td align="right" width="15%">管理角色：</td>
						        		<td width="35%">
							        		<select id="adminRole" class="easyui-combobox" style="width:80%;"></select>
						        		</td>
						        		<td align="right" width="15%"></td>
						        		<td width="35%"></td>
							        </c:if>
							         <c:if test='${obj.adminrole=="adminRole"}'>
      							        <td align="right" width="15%">组织单元：</td>
						        		<td width="35%">
							        		<select id="orgId" name="orgId" class="easyui-combobox" style="width:80%;" panelWidth="230"></select>
						        		</td>
    						        	<td align="right" width="15%">管理角色：</td>
						        		<td width="35%">
							        		<select id="adminRole" class="easyui-combobox" style="width:80%;"></select>
						        		</td>
						            </c:if>
						        	</tr>
	  						        <tr><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr>
						        	<tr><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr>
						        	<tr><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr>
						        	<tr><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td align="center">
						        	    <c:if test='${not empty obj.superrole}'>
						        	    	<input type="button" value="确定" class="subBtn" id="subBtn" onclick="login('superrole');" onmouseover="this.className='subBtnOut';" onmouseout="this.className='subBtn';" />
						        	    </c:if>
						        		<c:if test='${not empty obj.adminrole}'>
						        	    	<input type="button" value="确定" class="subBtn" id="subBtn" onclick="login('adminrole');" onmouseover="this.className='subBtnOut';" onmouseout="this.className='subBtn';" />
						        	    </c:if>
						        		<input type="button" value="登出" class="subBtn" style="margin-left:8px;" onclick="exit();" onmouseover="this.className='subBtnOut';" onmouseout="this.className='subBtn';" />
						        	</td></tr>
						        </table>
					    </div>   
				    </c:if>
				    <c:if test='${obj.busirole=="busiRole"}'>
					    <div title="业务角色" data-options="selected:true">
					        <table width="570" border="0">	
					            <tr><td style="margin-top:20%;">&nbsp;</td></tr>
					        	<tr>
					        		<td align="right" width="15%">组织单元：</td>
					        		<td width="35%">
						        		<select id="orgId" name="orgId" class="easyui-combobox" style="width:80%;" panelWidth="230"></select>
					        		</td>
					        		<td  align="right" width="15%">业务角色：</td>
					        		<td width="35%">
					        			<select id="busiRole" class="easyui-combobox" style="width:80%;"></select>
					        		</td>
					        	</tr>
						        <tr><td colspan="4" align="center" height="30"><div id="msg" class="msg" style="color:red;font-size:13px;">请联系管理员分配角色权限！</div></td></tr>
					        	<tr><td colspan="4" >&nbsp;</td></tr>
					        	<tr>
					        		<td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td align="center">
										<input type="button" value="确定" class="subBtn" id="subBtn" onclick="login('busiRole');" onmouseover="this.className='subBtnOut';" onmouseout="this.className='subBtn';" />
						        		<input type="button" value="登出" class="subBtn" style="margin-left:8px;" onclick="exit();" onmouseover="this.className='subBtnOut';" onmouseout="this.className='subBtn';" />					        	</td>
					        	</tr>
					        </table>       
					    </div> 
				    </c:if> 
				</div>    
			 </form> 
		</div>  

		
</html>