<%@page contentType="text/html;charset=UTF-8"%>
<%@ include file="/include/jsp/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>云服务平台</title>
	<script type="text/javascript" src="${ctx}/js/system/menu.js"></script>
</head>
<body class="easyui-layout">
		<div id="cc" class="easyui-layout" style="width:100%;height:100%;">   
		    <div data-options="region:'west',title:'系统菜单',split:true" style="width:20%;">
		    	<ul id="tt"></ul>
		    </div>   
		    <div data-options="region:'center',title:'菜单信息'" style="padding:1px;">
		        <form  id="f_xm" name="f_xm"  method="post">
		        <input id="menuId" type="hidden" name="menuId" value=""/>
		        <input id="leaf" type="hidden" name="leaf" value=""/>
		        		<table width="100%" align="center" cellpadding="2" cellspacing="1" class="table">
							   <tr class="alternate_line2">
							        <td width="20%" class="td_title">菜单名称：</td>
							         <td width="30%" class="td_content">
							        	<input id="name" type="text" name="name" class="c_textbox"/>
							        </td>
							        <td width="20%" class="td_title">上级菜单：</td>
							        <td width="30%" class="td_content">
							        	<input id="parentname" type="text" name="parentname" class="c_textbox" disabled="true"/>
							        	<input id="parentid" type="hidden" name="parentid" value=""/>
							        </td>
							  </tr>
							  
							  <tr class="alternate_line2">	
							        <td width="20%" class="td_title">请求地址：</td>
							        <td align="80%" class="td_content" colspan="3">
							        	<input id="url" type="text" name="url" class="c_textbox"/>
							        </td>
							  </tr>
							  
							  <tr class="alternate_line2">
							      	<td width="20%" class="td_title">状态：</td>
							        <td width="30%" class="td_content">
							        	<select id="status" class="easyui-combobox" name="status" style="width: 98%;">
							        	    <option value="1">有效 </option>
								            <option value="2">无效 </option>
									    </select>
							        </td>
							        <td width="20%" class="td_title">排序号：</td>
							        <td width="30%" class="td_content">
							        	<input class="c_textbox" id="sortno" name="sortno" type="text"/>
							        </td>
						       </tr>
						       
						        <tr class="alternate_line2">
							        <td width="20%" class="td_title">备注：</td>
							        <td width="80%" align="left" class="td_content" colspan='3'>
							        	<input id="remark"  name="remark" type='text' class="c_textbox" data-options="multiline:true" style="height: 50px;"/>
							        </td>
							  </tr>
				    </table>
		        	
		        </form>
	    		<div data-options="region:'south',border:false" style="text-align:right;padding:5px 0 0;">
					<a class="easyui-linkbutton" data-options="iconCls:'icon-ok'" href="javascript:void(0)" onclick="saveOrUpdate();" style="width:80px">保存</a>
					<a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" href="javascript:void(0)" onclick="closes();" style="width:80px">重置</a>
				</div>
		    </div>
		    <div id="treePanel" class="easyui-menu" style="width:50px;">
   				<div id="addMenu" onclick="addMenu()" style="width:100%;">新增</div>
				<div id="delMenu" onclick="delMenu()" style="width:100%;">删除</div>
			</div>
		</div> 
</body>
</html>