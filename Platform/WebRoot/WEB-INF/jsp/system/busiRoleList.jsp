<%@page contentType="text/html;charset=UTF-8"%>
<%@ include file="/include/jsp/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>云服务平台</title>
	<script type="text/javascript" src="${ctx}/js/system/busiRole.js"></script>
	<script type="text/javascript" src="${ctx}/js/system/common.js"></script>
</head>
<body class="easyui-layout">
    <div id="p" class="easyui-panel"  data-options="title:'业务角色信息'" style="width:100%;height:100%;padding:2px;">
        <table id="dg" height="100%"
				data-options="rownumbers:true,
							singleSelect:false,
							autoRowHeight:false,
							pagination:true,
							pageSize:10">
				 <thead>
					<tr>
						<th field="id" formatter="Common.RadioFormatter"></th>
						<th field="name" width="30%" align="center">
							角色名称
						</th>
						<th field="remark" width="66%" align="center">
							描述信息
						</th>
					</tr>
				</thead>
			</table>
    </div>
    <div id="win" class="easyui-window" title="角色信息" closed="true">  
	     <form id="f_form" name="f_form" method="post">
		     <table align="center">
				  <tr>	
				        <td>名称</td>
				        <td>
				        	<input id="rolename" type="text" name="rolename" class="c_textbox" style="width:400px;"/>
				        </td>
				  </tr>
			      <tr>
				        <td>描述</td>
				        <td>
				        	<input id="remark"  name="remark" type='text' class="c_textbox" data-options="multiline:true" style="height:60px;"/>
				        </td>
				   </tr>
				   <tr>
				   		<td></td>
				   		<td align="right">
	   				    	<div style="margin-top:30px;text-align:right;padding:5px 0 0;">
								<a class="easyui-linkbutton" data-options="iconCls:'icon-ok'" href="javascript:void(0)" onclick="saveOrUpdate();" style="width:80px">保存</a>
								<a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" href="javascript:void(0)" onclick="reset();" style="width:80px">重置</a>
							</div>
				   		</td
				   </tr>
		    </table>
	     </form>  
    </div> 
</body>
</html>