$(function() {
	 $('#dg').datagrid({  
		 loadMsg: "数据加载中，请稍后...",
		 url:'${ctx}/user/system_queryUser.pt', 
		 loadFilter : pagerFilter,
		 onSelect:function(index, row){
			 var enabled = row.enabled;
			 var locked = row.locked;
			 if(enabled == '1'){
				 $("#c_doIcon").attr({"disabled":true});
				 $("#c_undoIcon").removeAttr("disabled");
			 }else{
				 $("#c_undoIcon").attr({"disabled":true});
				 $("#c_doIcon").removeAttr("disabled");
			 }
			 if(locked == '1'){
				 $("#c_lockIcon").attr({"disabled":true});
				 $("#c_unlockIcon").removeAttr("disabled");
			 }else{
				 $("#c_unlockIcon").attr({"disabled":true});
				 $("#c_lockIcon").removeAttr("disabled");
			 }
		 },
		 onClickRow:function(index, row){
			 $("input[name='radio_name']").each(function () {
                 if ($(this).val() == row.id) {
                     $(this).attr("checked", true);
                 }
             });

		 }
	 });
	 
	 $('#dgg').datagrid({  
		 loadMsg: "数据加载中，请稍后..."
	 });
	
	$('#f_panel').panel({    
		  tools: [{   
		    text:'查询', 
		    iconCls:'c_queryIcon',    
		    handler:function(){
		    	queryUser();
	    	}    
		  }]    
	});
	
	$('#g_panel').panel({    
		  tools: [{   
		    text:'新增', 
		    iconCls:'c_addIcon',    
		    handler:function(){
		    	addUser('add');
		    }    
		  },{   
		    text:'编辑', 
		    iconCls:'c_editIcon',    
		    handler:function(){
		    	addUser('edit');
	    	}    
		  },{   
		    text:'删除', 
		    iconCls:'c_delIcon',    
		    handler:function(){
		    	delUser();
		    }    
		  }]    
	});
	
	$('#status_grid').panel({    
		  tools: [{   
		    text:'启用', 
		    iconCls:'c_doIcon',    
		    handler:function(){
		    	doStatus('1');
		    }    
		  },{   
		    text:'停用', 
		    iconCls:'c_undoIcon',    
		    handler:function(){
		    	doStatus('0');
		    }    
		  },{   
		    text:'锁定', 
		    iconCls:'c_lockIcon',    
		    handler:function(){
		    	doLock('1');
		    }    
		  },{   
		    text:'解锁', 
		    iconCls:'c_unlockIcon',    
		    handler:function(){
		    	doLock('0');
		    }    
		  }]    
	});
	
});

function addUser(type){
	var id = null;
	if(type == 'edit'){
		var rows = $('#dg').datagrid('getSelections');
		if(rows.length <= 0){
			$.messager.show({
				title:'提示消息',
				msg:'请选择数据！！！',
				timeout:5000,
				showType:'slide'
			});

			return;
		}else{
			if(rows.length > 1){
				$.messager.show({
					title:'提示消息',
					msg:'不允许选择多条数据！！！',
					timeout:5000,
					showType:'slide'
				});
				return;
			}else{
				var id = rows[0].id;
			}
		}
	}
	$('#win').window({    
	    width:900,    
	    height:400,    
	    modal:true,
	    top:2,
	    cache:true,
	    href:'${ctx}/user/system_addOrUpdate.pt?id='+id,
	    left:(document.body.clientWidth-900)*0.5
	});  
	$('#win').window('open');
}

function saveOrUpdate(){
	var id = $('#userId').val();
	var account = $('#form_account').val();
	var password = $('#form_password').val();
	var username = $('#form_username').val();
	if(account==null || account==""){
		$.messager.show({
			title:'提示消息',
			msg:'账号不能为空！！！',
			timeout:5000,
			showType:'slide'
		});
	}else if(password==null || password==""){
		$.messager.show({
			title:'提示消息',
			msg:'密码不能为空！！！',
			timeout:5000,
			showType:'slide'
		});
	}else if(username==null || username==""){
		$.messager.show({
			title:'提示消息',
			msg:'姓名不能为空！！！',
			timeout:5000,
			showType:'slide'
		});
	}else{
		$('#f_form').form('submit', {    
		    url:'${ctx}/user/system_saveOrUpdate.pt', 
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
	    	    $('#win').window('close');
	    	    $("#dg").datagrid("reload"); 
		    }    
		});  
	}
}

function delUser(){
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
			ids+=rows[i].id+",";
    	}
		$.post("${ctx}/user/system_delUser.pt",{ids:ids},function(data){
			if(data=='success'){
				$.messager.alert('提示信息','删除成功！','info');
				$('#dg').datagrid('reload');
			}else{
				$.messager.alert('提示信息','删除失败！','error');
			}
		});
	}
}

function queryUser(){
	$('#dg').datagrid('reload',{
		account: $('#account').val(),
		username: $('#username').val()
	});
}

function doStatus(enabled){
	var rows = $('#dg').datagrid('getSelections');
	var id = null;
	if(rows == null || rows == "" || typeof(rows) == "undefined"){
		$.messager.alert('提示信息','请选择数据！！！！','info');
		return;
	}else{
		id = rows[0].id;
	}
	$.post("${ctx}/user/system_updateStatus.pt",{id:id,enabled:enabled},function(data){
		if(data=='success'){
			if(enabled == '1'){
				$.messager.alert('提示信息','启用成功！','info');
			}else{
				$.messager.alert('提示信息','停用成功！','info');
			}
		}else{
			$.messager.alert('提示信息','启用失败！','info');
		}
		$('#dg').datagrid('reload');
	});
}

function doLock(locked){
	var rows = $('#dg').datagrid('getSelections');
	var id = null;
	if(rows == null || rows == "" || typeof(rows) == "undefined"){
		$.messager.alert('提示信息','请选择数据！！！！','info');
		return;
	}else{
		id = rows[0].id;
	}
	$.post("${ctx}/user/system_updateLock.pt",{id:id,locked:locked},function(data){
		if(data=='success'){
			if(locked == '1'){
				$.messager.alert('提示信息','锁定成功！','info');
			}else{
				$.messager.alert('提示信息','激活成功！','info');
			}
		}else{
			$.messager.alert('提示信息','锁定失败！','info');
		}
		$('#dg').datagrid('reload');
	});
}

function updatePass(){
	var id = $('#userId').val();
	$('#f_form').form('submit', {    
	    url:'${ctx}/user/system_saveOrUpdate.pt', 
	    onSubmit: function(param){ 
	    	param.id=id; 
	    },    
	    success:function(type){
    	    if(type=="edit"){
    	    	$.messager.alert('提示信息','修改成功！','info');
    	    }else{
    	    	$.messager.alert('提示信息','操作失败！','error');
    	    }
	    }    
	});  
}


