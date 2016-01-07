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
		<form  id="f_form" name="f_form"  method="post">
					 <input id="userId" name="userId" type="hidden" value="${obj.id }"/>
						 <fieldset style="width:100%;">
				            <legend>用户信息</legend>
						     <table align="center" border="0" width="100%">
								  <tr>	
								        <td align="right">账号：</td>
								        <td width="15%" align="left">
								            <input id="form_account" name="account" type="text" class="easyui-textbox" value="${obj.account }"/>
								        </td>
								        <td align="right">密码：</td>
								        <td width="15%" align="left">
								        	<input id="form_password" type="password" name="password" class="easyui-textbox" value="${obj.password }"/>
								        </td>
								        <td align="right">姓名：</td>
								        <td width="15%" align="left">
								        	<input id="form_username" type="text" name="username" class="easyui-textbox" value="${obj.username }"/>
								        </td>
								        <td align="right">性别：</td>
								        <td width="15%" align="left">
									        <select id="sex" class="easyui-combobox" name="sex" >
								        	    <option value="1" <c:if test='${obj.sex=="1" }'>selected="selected"</c:if>>男 </option>
									            <option value="2" <c:if test='${obj.sex=="2" }'>selected="selected"</c:if>>女 </option>
									            <option value="0" <c:if test='${obj.sex=="0" }'>selected="selected"</c:if>>未知 </option>
										    </select>
								        </td>
								  </tr>
								  
								  <tr>	
								        <td align="right">手机号码：</td>
								        <td width="15%" align="left">
								        	<input id="phone" type="text" name="phone" class="easyui-textbox"value="${obj.phone }" />
								        </td>
								        <td align="right">邮箱地址：</td>
								        <td width="15%" align="left">
								        	<input id="email" type="text" name="email" class="easyui-textbox" value="${obj.email }"/>
								        </td>
									    <td align="right">身份证号码：</td>
								        <td width="15%" align="left">
								        	<input id="caid" type="text" name="caid" class="easyui-textbox" value="${obj.caid }"/>
								        </td>
								        </td>
								        <td align="right">启动状态：</td>
								        <td width="15%" align="left">
		    							   <select id="enabled" class="easyui-combobox" name="enabled" panelHeight="80"  style="width:80%;">
								        	    <option value="1" <c:if test='${obj.enabled=="1" }'>selected="selected"</c:if>>启用</option>
									            <option value="0" <c:if test='${obj.enabled=="0" }'>selected="selected"</c:if>>停用</option>
										    </select>
								        </td>
								  </tr>
		  						   <tr>
								        <td align="right">备注：</td>
								        <td colspan="5">
								        	<textarea id="remark" rows="3"  name="remark" class="textarea easyui-validatebox" style="width:100%;overflow:hidden;" value="${obj.remark }"></textarea>
								        </td>
								  </tr>
						    </table>
					    </fieldset>
			    </form>
   	    		<div style="text-align:right;margin:5px 30px 10px 0;">
					<a class="easyui-linkbutton" data-options="iconCls:'icon-ok'" href="javascript:void(0)" onclick="saveOrUpdate();" style="width:80px">保存</a>
					<a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" href="javascript:void(0)" onclick="reset();" style="width:80px">重置</a>
				</div>
</body>
</html>