<%@page contentType="text/html;charset=UTF-8"%>
<%@ include file="/include/jsp/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>云服务平台</title>
	<script type="text/javascript" src="${ctx}/js/system/organization.js"></script>
</head>
<body class="easyui-layout"> 
		<div id="cc" class="easyui-layout" style="width:100%;height:100%;">
		    <input type="hidden" id="roleType" name="roleType" value="${_roletype }"/>
		    <div data-options="region:'west',title:'组织机构',split:true" style="width:20%;">
		    	<ul id="tt"></ul>
		    </div>   
		    <div data-options="region:'center',title:'机构信息'" style="padding:1px;">
		        <form id="f_xm" name="f_xm"  method="post">
		        		<input id="organizationId" type="hidden" name="organizationId" value=""/>
		        		<input id="type" type="hidden" name="type" value=""/>
		        		<table width="100%" align="center" cellpadding="2" cellspacing="1" class="table">
							   <tr class="alternate_line2">
							        <td width="20%" class="td_title">部门名称：</td>
							         <td width="30%" class="td_content">
							        	<input id="name" type="text" name="name" class="c_textbox" value="${obj.deptname }"/>
							        </td>
							        <td width="20%" class="td_title">上级部门：</td>
							        <td width="30%" class="td_content">
							        	<input id="parentname" type="text" name="parentname" disabled="true" class="c_textbox" value="${obj.parentname }"/>
							        	<input id="parentid" type="hidden" name="parentid" value=""/>
							        </td>
							  </tr>
							  
							  <tr class="alternate_line2">
							      	<td width="20%" class="td_title">状态：</td>
							        <td width="30%" class="td_content">
							        	<select id="enabled" name="enabled" style="width: 100%;">
							        	    <option value="1">启用 </option>
								            <option value="0">停用 </option>
									    </select>
							        </td>
							        <td width="20%" class="td_title">排序号：</td>
							        <td width="30%" class="td_content">
							        	<input class="c_textbox" id="sortno" name="sortno" type="text" value="${obj.sortno }"/>
							        </td>
						       </tr>
						       
						        <tr class="alternate_line2">
							        <td width="20%" class="td_title">备注：</td>
							        <td width="80%" align="left" class="td_content" colspan='3'>
							        	<input id="remark"  name="remark" type='text' class="c_textbox" data-options="multiline:true" style="height: 50px;" value="${obj.remark}" />
							        </td>
							  </tr>
				    </table>
		        	
		        </form>
	    		<div data-options="region:'south',border:false" style="text-align:right;padding:5px 0 0;">
					<a class="easyui-linkbutton" data-options="iconCls:'icon-ok'" href="javascript:void(0)" onclick="saveOrUpdate();" style="width:80px">保存</a>
					<a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" href="javascript:void(0)" onclick="reset();" style="width:80px">重置</a>
				</div>
		    </div>
		    <div id="treePanel" class="easyui-menu" style="width:50px;">
   				<div onclick="add()" style="width:100%;">新增</div>
				<div onclick="del()" style="width:100%;">删除</div>
			</div>
		</div> 
</body>
</html>