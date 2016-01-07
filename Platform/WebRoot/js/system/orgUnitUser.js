$(function() {
     $('#tt').tree({     
	    url:'${ctx}/user/system_organizationTree.pt',
	    onClick: function(node){
			var root = $('#tt').tree('getRoot');
			if(root.id != node.id){
				setInfo(node);
			}
		},
		onLoadSuccess: function(node,data){
			 var root = $('#tt').tree('getRoot');
		     var objNode = $('#tt').tree('find',root.id); 
		     $(objNode.target).next().children().children("div.tree-node").each(function(){    
		        $("#tt").tree('expand',objNode.target);
		     }); 
		}
 	 });
     
	 $('#dg').datagrid({  
		 loadMsg: "数据加载中，请稍后...",
		 loadFilter : pagerFilter
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
		    	delOrgUnitUser();
		    }    
		  }]    
		});  
});

function setInfo(node){
	$.post("${ctx}/user/system_getOrgUnitInfo.pt",{id:node.id},function(data){
		var obj = eval("("+data+")"); 
		$('#name').val(obj.name);
		$('#remark').val(obj.remark);
		$('#orgId').val(obj.id);
		$('#dg').datagrid({  
			 loadMsg: "数据加载中，请稍后...",
			 url:'${ctx}/user/system_queryUserByOrgUnit.pt?orgId='+obj.id, 
			 loadFilter : pagerFilter
		});
	});
}

function openWin(){
	var orgId = $('#orgId').val();
	if(orgId !=null && orgId != ""){
		$('#win').window({    
		    width:600,    
		    height:400,    
		    modal:true   
		});
		$('#win').window('open');
		
		$('#win_grid').datagrid({  
			 loadMsg: "数据加载中，请稍后...",
			 url:'${ctx}/user/system_queryOtherOrgUser.pt?orgId='+orgId, 
			 loadFilter : pagerFilter
		});
	}else{
		$.messager.alert('提示信息','请选择组织单元！','info');
	}

}

function queryUser(){
	$('#win_grid').datagrid('reload',{
		account: $('#account').val()
	});
}

function selectUser(){
	var ids = '';
	var orgId = $('#orgId').val();
	var rows = $('#win_grid').datagrid('getSelections');
	if(rows!=null && rows != ""){
		for(var i=0; i<rows.length; i++){
			ids+=rows[i].id+",";
    	}
		$.post("${ctx}/user/system_saveOrgUnitUser.pt",{ids:ids,orgId:orgId},function(data){
			$('#dg').datagrid('reload');
		});
	}
	$('#win').window('close');
}

function delOrgUnitUser(){
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
		$.post("${ctx}/user/system_delOrgUnitUser.pt",{ids:ids},function(data){
			if(data=='success'){
				$.messager.alert('提示信息','取消关联成功！','info');
				$('#dg').datagrid('reload');
			}else{
				$.messager.alert('提示信息','取消关联失败！','info');
			}
		});
	}
}
