<%@page contentType="text/html;charset=UTF-8"%>
<%@ include file="/include/jsp/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>云服务平台</title>
	<script type="text/javascript" src="${ctx}/js/system/orgUnitUser.js"></script>
	<script type="text/javascript" src="${ctx}/js/system/common.js"></script>
</head>
<body  class="easyui-layout">
		    <div data-options="region:'west',title:'组织单元',split:true" style="width:20%;">
		    	<ul id="tt"></ul>
		    </div>   
		    <div data-options="region:'center'" style="height:100%;padding:1px;">
				     <div id="c_form" class="easyui-panel" data-options="collapsible:false,title:'组织信息'" style="height:25%;margin-bottom:1px;">
					     <form  id="f_xm" name="f_xm"  method="post">
						     <input type="hidden" id="orgId" name="orgId" value=""/>
						     <table align="center">
								  <tr>	
								        <td>名称</td>
								        <td>
								        	<input id="name" type="text" name="name" class="c_textbox" style="width:400px;"/>
								        </td>
								  </tr>
							        <tr>
								        <td>描述</td>
								        <td>
								        	<input id="remark"  name="remark" type='text' class="c_textbox" data-options="multiline:true" style="height:42px;"/>
								        </td>
								  </tr>
						    </table>
					    </form>
				     </div>   
			         <div id="c_grid" class="easyui-panel" data-options="title:'关联用户'" style="height:74%;padding:1px;">
				         	<table id="dg" height="100%"
								data-options="rownumbers:true,
											singleSelect:false,
											autoRowHeight:false,
											pagination:true,
											pageSize:10">
								 <thead>
									<tr>
										<th data-options="field:'id',checkbox:true" ></th>
										<th field="account" width="20%" align="center">
											账号
										</th>
										<th field="username" width="20%" align="center">
											姓名
										</th>
										<th field="sex" width="16%" align="center" formatter="Common.SexFormatter">
											性别
										</th>
										<th field="phone" width="20%" align="center">
											手机号码
										</th>
										<th field="email" width="20%" align="center">
											邮箱地址
										</th>
									</tr>
								</thead>
							</table>
			         </div>   
				</div> 
		    </div>
		    
		    <div id="win" class="easyui-window" title="账号查询" style="width:600px;height:400px"   
		        data-options="iconCls:'icon-save',modal:true,closed:true">   
			    <div class="easyui-layout" data-options="fit:true">
        			
        			<div data-options="region:'north'" style="height:15%;padding:0 8px;">
						<fieldset style="width:100%;">
				            <legend>账号查询</legend>
				                    <table cellspacing="0" cellpadding="0" style="width:100%;" border="0">
				                        <tr>
				                            <td width="8%" align="right">账号：</td>
				                            <td>
				                                <input type="text" id="account" name="account" class="easyui-textbox"/>
				                            </td>
				                            <td colspan="2" align="right" style="padding:5px;">
                    					          <a href="#" id="btnSearch" class="combtn" onclick="queryUser();">查询</a>
                    					          <a href="#" id="btnSearch" class="combtn" onclick="selectUser();">确定</a>
				                            </td>
				                          </tr>
				                    </table>
				        </fieldset>
        			</div>   
        			
        			<div data-options="region:'center'" style="border:0px;">
        				<table id="win_grid" height="100%" 
							data-options="rownumbers:true,
										singleSelect:false,
										autoRowHeight:false,
										pagination:true,
										pageSize:10">
							 <thead>
								<tr>
									<th data-options="field:'rid',checkbox:true" ></th>
									<th field="account" width="20%" align="center">
										账号
									</th>
									<th field="username" width="20%" align="center">
										姓名
									</th>
									<th field="sex" width="14%" align="center" formatter="Common.SexFormatter">
										性别
									</th>
									<th field="phone" width="20%" align="center">
										手机号码
									</th>
									<th field="email" width="20%" align="center">
										邮箱地址
									</th>
								</tr>
							</thead>
						</table>
        			</div> 
        		
        		</div>
			</div>  
			
		    <div id="treeMenu" class="easyui-menu" style="width:50px;">
   				<div onclick="addRole()" style="width:100%;">新增</div>
				<div onclick="delRole()" style="width:100%;">删除</div>
			</div>
</body>
</html>