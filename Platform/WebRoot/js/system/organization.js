$(function() {
	  var roleType = $('#roleType').val();
	  $('#tt').tree({     
	    url:'${ctx}/organization/system_organizationTree.pt',
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
			if(roleType=='superAdmin'){
				$('#treePanel').menu('show', {
					left: e.pageX,
					top: e.pageY
				});
			}else{
				var root = $('#tt').tree('getRoot');
				if(root.id != node.id){
					$('#treePanel').menu('show', {
						left: e.pageX,
						top: e.pageY
					});
				}
			}
		}
 	 });
});

function setInfo(node){
	$.post("${ctx}/organization/system_getOrganization.pt",{id:node.id},function(data){
		var obj = eval("("+data+")"); 
		$('#name').val(obj.name);
		$('#sortno').val(obj.sortno);
		$('#remark').val(obj.remark);
		$('#organizationId').val(node.id);
		var enabled = obj.enabled;  
		$("#enabled").val(enabled);
	});
	var parentNode = $('#tt').tree("getParent", node.target);
	$('#parentname').val(parentNode.text);
	$('#parentid').val(parentNode.id);
}

function add(){
	var node = $('#tt').tree('getSelected');
	$('#parentname').val(node.text);
	$('#organizationId').attr("value","");
	$('#remark').attr("value","");
	$('#sortno').attr("value","");
	$('#name').attr("value","");
	$('#parentid').val(node.id);
	var root = $('#tt').tree('getRoot');
	if(root.id == node.id){
		$('#type').val('unit');
	}
}

function del(){
	var node = $('#tt').tree('getSelected');
	$.post("${ctx}/organization/system_delOrganization.pt",{id:node.id},function(data){
			 if(data=="success"){
				 $.messager.alert('提示信息','删除组织单元成功！','info');
			 }else{
				 $.messager.alert('提示信息','删除组织单元失败！','error');
			 }
			 $("#tt").tree("reload"); 
	});
}

function reset(){
	$('#f_xm')[0].reset();
	var node = $('#tt').tree('getSelected');
	$('#parentname').val(node.text);
}

function saveOrUpdate(){
	var id = $('#organizationId').val();
	$('#f_xm').form('submit', {    
	    url:'${ctx}/organization/system_saveOrUpdate.pt', 
	    onSubmit: function(param){ 
	    	param.id=id; 
	    },    
	    success:function(type){
    	    if(type=="add"){
    	    	$.messager.alert('提示信息','添加组织单元成功！','info');
    	    }else if(type=="edit"){
    	    	$.messager.alert('提示信息','修改组织单元成功！','info');
    	    }else{
    	    	$.messager.alert('提示信息','添加组织单元失败！','error');
    	    }
    	    $("#tt").tree("reload"); 
	    }    
	});  
}

