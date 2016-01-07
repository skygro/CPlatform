$(function() {
	$('#tt').tree({     
	    url:'${ctx}/menu/system_menuTree.pt',
	    cascadeCheck: false,
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
		},
		onCheck: function(node,checked){
//			if (checked) {
//                var parentNode = $("#tt").tree('getParent', node.target);
//                if (parentNode != null) {
//                    $("#tt").tree('check', parentNode.target);
//                }
//            } else {
//                var childNode = $("#tt").tree('getChildren', node.target);
//                if (childNode.length > 0) {
//                    for (var i = 0; i < childNode.length; i++) {
//                        $("#tt").tree('uncheck', childNode[i].target);
//                    }
//                }
//            }
//
			var parentNode = $('#tt').tree("getParent", node.target);
			if(checked){
//				if($('#tt').tree('isLeaf',node.target)){ 
					if(parentNode!=null && parentNode!=""){
						$("#tt").tree('check',parentNode.target); 
					}
//				}  
			}
		},
		onContextMenu: function(e, node){
			e.preventDefault();
			// 查找节点
			$('#tt').tree('select', node.target);
			// 显示快捷菜单
			var root = $('#tt').tree('getRoot');
			alert(root.id);
			if(root.id != node.id){
				$("#delMenu").removeAttr("disabled");
				$('#treePanel').menu('show', {
					left: e.pageX,
					top: e.pageY
				});
			}else{
				$("#delMenu").attr({"disabled":true});
				$('#treePanel').menu('show', {
					left: e.pageX,
					top: e.pageY
				});
			}
		}
 	 });
	  $('#menu_panel').panel({    
		  tools: [{   
		    text:'保存', 
		    iconCls:'c_saveIcon',    
		    handler:function(){
		    	saveMenuToRole();
		    }    
		  }]    
	 }); 
});

function setInfo(node){
	$.post("${ctx}/menu/system_getMenuInfo.pt",{id:node.id},function(data){
		var obj = eval("("+data+")"); 
		$('#name').val(obj.name);
		$('#url').val(obj.url);
		$('#sortno').val(obj.sortno);
		$('#remark').val(obj.remark);
		$('#menuId').val(obj.id);
		$("#status").val(status);
	});
	var parentNode = $('#tt').tree("getParent", node.target);
	if(parentNode!=null && parentNode!=""){
		$('#parentname').val(parentNode.text);
		$('#parentid').val(parentNode.id);
	}
	
	var children = $('#tt').tree("getChildren", node.target);
	if(children !=null && children != ""){
		$("#leaf").val("0");
	}else{
		$("#leaf").val("1");
	}
}

function addMenu(){
	var node = $('#tt').tree('getSelected');
	$('#parentname').val(node.text);
	$('#menuId').attr("value","");
	$('#remark').attr("value","");
	$('#sortno').attr("value","");
	$('#url').attr("value","");
	$('#name').attr("value","");
	$('#parentid').val(node.id);
	var children = $('#tt').tree("getChildren", node.target);
	if(children !=null && children != ""){
		$("#leaf").val("0");
	}else{
		$("#leaf").val("1");
	}
}

function delMenu(){
	var node = $('#tt').tree('getSelected');
	$.post("${ctx}/menu/system_delMenu.pt",{id:node.id},function(data){
			 if(data=="success"){
				 alert("删除成功！");
			 }else{
				 alert("删除失败！");
			 }
			 $("#tt").tree("reload"); 
	});
}

function saveOrUpdate(){
	var id = $('#menuId').val();
	$('#f_xm').form('submit', {    
	    url:'${ctx}/menu/system_saveOrUpdate.pt', 
	    onSubmit: function(param){ 
	    	param.id=id; 
	    },    
	    success:function(type){
    	    if(type=="add"){
    	    	$.messager.alert('提示信息','添加成功！','info');
    	    }else if(type=="edit"){
    	    	$.messager.alert('提示信息','修改成功！','info');
    	    }else{
    	    	$.messager.alert('提示信息','操作失败！','error');
    	    }
    	    $("#tt").tree("reload"); 
	    }    
	});  
}

function changeRoleMenu(){
	var roleId = $("input[name='roleId']:checked").val();
	$('#tt').tree({     
	    url:'${ctx}/menu/system_menuTree.pt?roleId='+roleId,
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
		},
		onContextMenu: function(e, node){
			e.preventDefault();
			// 查找节点
			$('#tt').tree('select', node.target);
			// 显示快捷菜单
			var root = $('#tt').tree('getRoot');
			if(root.id != node.id){
				$('#treePanel').menu('show', {
					left: e.pageX,
					top: e.pageY
				});
			}
		}
 	 });
}

function saveMenuToRole(){
	var roleId = $("input[name='roleId']:checked").val();
	if(roleId != null && roleId != ""){
		var nodes = $('#tt').tree('getChecked');
		var menuIds = '';
	    for (var i = 0; i < nodes.length; i++) {
	    		menuIds+=nodes[i].id+",";
	    }

		$.post("${ctx}/menu/system_saveMenuToRole.pt",{roleId:roleId,menuIds:menuIds},function(data){
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