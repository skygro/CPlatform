$(function() {
     $('#tt').tree({     
         url:'${ctx}/role/system_getRoleTree.pt',
         onClick: function(node){
        	 var root = $('#tt').tree('getRoot');
 			 if(root.id != node.id){
 				setInfo(node);
 			 }
         },
		 onContextMenu: function(e, node){
			e.preventDefault();
			// 查找节点
			$('#tt').tree('select', node.target);
			// 显示快捷菜单
	       	 var root = $('#tt').tree('getRoot');
			 if(root.id == node.id){
				$('#treeMenu').menu('show', {
						left: e.pageX,
						top: e.pageY
					});
				}
			 }
	 	 });
     
	 $('#dg').datagrid({  
		 loadMsg: "数据加载中，请稍后...",
		 loadFilter : pagerFilter
	 });
	 
	  $('#c_form').panel({    
		  tools: [{   
		    text:'保存', 
		    iconCls:'c_saveIcon',    
		    handler:function(){
		    	saveOrUpdate();
		    }    
		  }]    
	 }); 
	 
	 $('#c_grid').panel({    
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

function setInfo(node){
	$.post("${ctx}/role/system_getRoleInfo.pt",{roleId:node.id},function(data){
		var obj = eval("("+data+")"); 
		$('#rolename').val(obj.name);
		$('#remark').val(obj.remark);
		$('#roleId').val(obj.id);
		$('#dg').datagrid({  
			 loadMsg: "数据加载中，请稍后...",
			 url:'${ctx}/role/system_queryUserByRole.pt?roleId='+obj.id, 
			 loadFilter : pagerFilter
		});
	});
}

function openWin(){
	var roleId = $('#roleId').val();
	if(roleId !=null && roleId != ""){
		$('#win').window({    
		    width:600,    
		    height:400,    
		    modal:true   
		});
		$('#win').window('open');
		
		$('#win_grid').datagrid({  
			 loadMsg: "数据加载中，请稍后...",
			 url:'${ctx}/role/system_queryOtherUser.pt?roleId='+roleId, 
			 loadFilter : pagerFilter
		});
	}else{
		$.messager.alert('提示信息','请选择管理角色！！！','info');
	}

}

function queryUser(){
	$('#win_grid').datagrid('reload',{
		account: $('#account').val()
	});
}

function selectUser(){
	var ids = '';
	var roleId = $('#roleId').val();
	var rows = $('#win_grid').datagrid('getSelections');
	if(rows!=null && rows != ""){
		for(var i=0; i<rows.length; i++){
			ids+=rows[i].id+",";
    	}
		$.post("${ctx}/role/system_saveUserRole.pt",{ids:ids,roleId:roleId,roleType:'adminRole'},function(data){
			$('#dg').datagrid('reload');
		});
	}
	$('#win').window('close');
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
		$.post("${ctx}/role/system_delUserRole.pt",{ids:ids},function(data){
			if(data=='success'){
				$.messager.alert('提示信息','取消关联成功！','info');
				$('#dg').datagrid('reload');
			}else{
				$.messager.alert('提示信息','取消关联失败！','info');
			}
		});
	}
}

function addRole(){
	var node = $('#tt').tree('getSelected');
	$('#rolename').attr("value","");
	$('#roleId').attr("value","");
	$('#remark').attr("value","");
	$('#parentId').attr("value",node.id);
	$('#dg').datagrid({  
		 loadMsg: "数据加载中，请稍后...",
		 url:'${ctx}/role/system_queryUserByRole.pt?roleId=', 
		 loadFilter : pagerFilter
	});
}

function saveOrUpdate(){
	var id = $('#roleId').val();
	var parentId = $('#parentId').val();
	$('#f_xm').form('submit', {    
	    url:'${ctx}/role/system_saveOrUpdate.pt?type=admin&parentId='+parentId, 
	    onSubmit: function(param){ 
	    	param.id=id; 
	    },    
	    success:function(type){
    	    if(type=="add"){
    	    	$.messager.alert('提示信息','添加管理角色成功！','info');
    	    }else if(type=="edit"){
    	    	$.messager.alert('提示信息','修改管理角色成功！','info');
    	    }else{
    	    	$.messager.alert('提示信息','添加管理角色失败！','error');
    	    }
    	    $("#tt").tree("reload"); 
	    }    
	});  
}
