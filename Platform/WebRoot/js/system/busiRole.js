$(function() {
	$('#dg').datagrid({  
		 loadMsg: "数据加载中，请稍后...",
		 url:'${ctx}/role/system_queryBusiRole.pt', 
		 loadFilter : pagerFilter
	});
	
	$('#p').panel({    
		 tools: [{    
		   text:'新增', 
		   iconCls:'c_addIcon',    
		   handler:function(){open();}    
		 },{ 
		   text:'删除',
		   iconCls:'c_delIcon',    
		   handler:function(){delRole();}    
		 }]    
	});  
});

function open(){
	 $('#win').window({    
		    width:600,    
		    height:300,    
		    modal:false,
		    maximizable:false,
		    minimizable:false,
		    collapsible:false,
		    draggable:false,
		    left:($(window).width()-600)*0.5,
			top:($(window).height()-400)*0.5
	 });
	 reset();
	 $('#win').window('open');
}

function saveOrUpdate(){
	var rolename = $('#rolename').val();
	if(rolename !=null && rolename != ""){
		$('#f_form').form('submit', {    
		    url:'${ctx}/role/system_saveOrUpdate.pt?type=busi', 
		    success:function(type){
	    	    if(type=="add"){
	    	    	$.messager.alert('提示信息','添加业务角色成功！','info');
	    	    	$('#win').window('close');
	    	    	$("#tt").tree("reload"); 
	    	    }else if(type=="edit"){
	    	    	$.messager.alert('提示信息','修改业务角色成功！','info');
	    	    }else{
	    	    	$.messager.alert('提示信息','添加业务角色失败！','error');
	    	    }
	    	    $("#dg").datagrid("reload"); 
		    }    
		});  
	}else{
		$.messager.alert('提示信息','名称不能为空！','warning');
	}
}

function delRole(){
	var row = $('#dg').datagrid('getSelected');
	if(row == null || row == ""){
		$.messager.alert('提示信息','请选择至少一条数据！','warning');
	}else{
		$.post("${ctx}/role/system_delRole.pt",{id:row.id},function(data){
			 if(data=="success"){
				 $.messager.alert('提示信息','删除业务角色成功！','info');
			 }else if(data=="N"){
				 $.messager.alert('提示信息','该用户权限不够！','info');
			 }else{
				 $.messager.alert('提示信息','删除业务角色失败！','error');
			 }
			 $("#dg").datagrid("reload"); 
		});
	}
}


