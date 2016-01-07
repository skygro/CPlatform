$(function() {
	$('#tt').tree({     
		url:'${ctx}/organization/system_organizationTree.pt',
		onLoadSuccess: function(node,data){
			 var root = $('#tt').tree('getRoot');
		     var objNode = $('#tt').tree('find',root.id); 
		     $(objNode.target).next().children().children("div.tree-node").each(function(){    
		        $("#tt").tree('expand',objNode.target);
		     }); 
		}
	});
	$('#org_panel').panel({    
		  tools: [{   
		    text:'保存', 
		    iconCls:'c_saveIcon',    
		    handler:function(){
		    	saveOrgToRole();
		    }    
		  }]    
	}); 
});

function changeRoleOrg(){
	var roleId = $("input[name='roleId']:checked").val();
	$('#tt').tree({     
	    url:'${ctx}/organization/system_organizationTree.pt?roleId='+roleId,
		onLoadSuccess: function(node,data){
			 var root = $('#tt').tree('getRoot');
		     var objNode = $('#tt').tree('find',root.id); 
		     $(objNode.target).next().children().children("div.tree-node").each(function(){    
		        $("#tt").tree('expand',objNode.target);
		     }); 
		}
 	 });
}

function saveOrgToRole(){
	var roleId = $("input[name='roleId']:checked").val();
	if(roleId != null && roleId != ""){
		//勾选中的所有子节点
		var nodes = $('#tt').tree('getChecked');
		var orgIds = '';
	    for (var i = 0; i < nodes.length; i++) {
	    	//判断是否为子节点
//	    	var b = $('#tt2').tree('isLeaf', nodes[i].target);
//	    	if(b){
	    		orgIds+=nodes[i].id+",";
//	    	}
	    }
		$.post("${ctx}/organization/system_saveOrgToRole.pt",{roleId:roleId,orgIds:orgIds},function(data){
			 if(data=="success"){
				 $.messager.alert('提示信息','保存成功！','info');
			 }else{
				 $.messager.alert('提示信息','保存失败！','error');
			 }
			 $("#tt").tree("reload"); 
	});
	}else{
		$.messager.alert('提示信息','请选择业务角色！','warning');
	}
}

