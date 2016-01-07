$(function() {
	changeRoleInfo();
	$('#userrole_grid').panel({    
		  tools: [{   
		    text:'关联用户', 
		    iconCls:'c_addIcon',    
		    handler:function(){
		    	openWin();
		    }    
		  },{   
		    text:'取消关联', 
		    iconCls:'c_delIcon',    
		    handler:function(){
		    	delUserRole();
			}    
		  }]    
	});
	
});

function changeRoleInfo(){
	var roleId = $("input[name='roleId']:checked").val();
	 $.post("${ctx}/user/system_getRoleInfo.pt",{roleId:roleId},function(data){
		 var obj = eval("("+data+")"); 
		 $('#name').val(obj.name);
		 $('#remark').val(obj.remark);
	 });
	 
	 $('#dg').datagrid({  
		 loadMsg: "数据加载中，请稍后...",
		 url:'${ctx}/user/system_queryUserByRole.pt?roleId='+roleId, 
		 loadFilter : pagerFilter
	 });
}


function openWin(){
	$('#win').window({    
	    width:600,    
	    height:400,    
	    modal:true   
	});  
	var roleId = $("input[name='roleId']:checked").val();
	$('#win').window('open');
	$('#win_grid').datagrid({  
		 loadMsg: "数据加载中，请稍后...",
		 url:'${ctx}/user/system_queryOtherUser.pt?roleId='+roleId, 
		 loadFilter : pagerFilter
	});
}

function queryUser(){
	$('#win_grid').datagrid('reload',{
		account: $('#account').val()
	});
}

function selectUser(){
	var ids = '';
	var roleId = $("input[name='roleId']:checked").val();
	var rows = $('#win_grid').datagrid('getSelections');
	if(rows!=null && rows != ""){
		for(var i=0; i<rows.length; i++){
			ids+=rows[i].id+",";
    	}
		$.post("${ctx}/user/system_saveUserRole.pt",{ids:ids,roleId:roleId,roleType:'busiRole'},function(data){
			$('#win').window('close');
			$('#dg').datagrid('reload');
		});
	}
}

function delUserRole(){
	var ids = '';
	var rows = $('#dg').datagrid('getSelections');
	if(rows.length <= 0){
		$.messager.show({
			title:'提示消息',
			msg:'请选择数据！！！',
			timeout:5000,
			showType:'slide'
		});
	}else{
		for(var i=0; i<rows.length; i++){
			ids+=rows[i].rid+",";
    	}
		$.post("${ctx}/user/system_delUserRole.pt",{ids:ids},function(data){
			if(data=='success'){
				$.messager.alert('提示信息','取消关联成功！','info');
				$('#dg').datagrid('reload');
			}else{
				$.messager.alert('提示信息','取消关联失败！','info');
			}
		});
	}
}

