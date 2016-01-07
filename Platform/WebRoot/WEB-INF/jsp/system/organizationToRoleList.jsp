<%@page contentType="text/html;charset=UTF-8"%>
<%@ include file="/include/jsp/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>云服务平台</title>
	<script type="text/javascript" src="${ctx}/js/system/organizationrole.js"></script>
	<script type="text/javascript" src="${ctx}/js/system/common.js"></script>
</head>
<body  class="easyui-layout">
		    <div data-options="region:'west',title:'业务角色',split:true" style="width:20%;padding:2px;">
		       <table width="100%" border="0">
                   <c:forEach var="obj" items="${list}">
	                   <tr>
		                   <td width="5%"><input name="roleId" type="radio" value="${obj.id }" onclick="changeRoleOrg();"/></td>
		                   <td>${obj.name }</td>
	                   </tr>
                   </c:forEach>
		       </table>
		    </div>   
		    <div data-options="region:'center'">
			     <div id="org_panel" class="easyui-panel" data-options="collapsible:false,title:'组织单元'" style="height:100%;border:0px;padding:3px;">
			     	<ul id="tt" data-options="checkbox:true"></ul>
			     </div>
		    </div>
</body>
</html>