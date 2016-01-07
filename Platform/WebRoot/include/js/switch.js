$(function() {
	 $(function() {
		 $('#win').window({    
		    width:600,    
		    height:180,    
		    modal:true,
		    draggable:true,
		    left:(window.screen.width-600)/2,
			top:(window.screen.height-380)/2
		});
	 });
	
	$('#adminRole').combobox({    
	    url:'${ctx}/login/system_queryAdminRole.pt',    
	    valueField:'id',    
	    textField:'name',
	    onLoadSuccess: function (data) {
            if (data != 0) {
                var data1 = $('#adminRole').combobox('getData');  //赋默认值
                if (data1.length > 0) {
                    $("#adminRole").combobox('select', data1[0].id);
                }
            }
	    }
	});  
	
	$('#orgId').combobox({    
	    url:'${ctx}/login/system_queryOrgUnit.pt',    
	    valueField:'id',    
	    textField:'name',
	    onLoadSuccess: function (data) {
            if (data != 0) {
                var data1 = $('#orgId').combobox('getData');  //赋默认值
                if (data1.length > 0) {
                    $("#orgId ").combobox('select', data1[0].id);
                    setRole(data1[0].id);
                }
            }
	    },
	    onSelect: function(record){
	    	setRole(record.id);
	    }
	});  
	$("#msg").hide();
});

function setRole(id){
	$('#busiRole').combobox({    
	    url:'${ctx}/login/system_queryBusiRole.pt?orgId='+id,    
	    valueField:'id',    
	    textField:'name',
	    onLoadSuccess: function (data) {
	        if (data != 0) {
	            var data1 = $('#busiRole').combobox('getData');  //赋默认值
	            if (data1.length > 0) {
	                $("#busiRole").combobox('select', data1[0].id);
	            }
	        }
	    }
	});  
}

function login(roleType) {
	var roleId='';
	var orgId='';
	var rolename = '';
	var orgname = '';
	var path = '';
	if(roleType =='busiRole'){
		roleId = $('#busiRole').combobox('getValue');
		orgname = $('#orgId').combobox('getText');
		orgId = $('#orgId').combobox('getValue');
		if(roleId == null || roleId == "" || typeof(roleId) == undefined){
			$("#msg").show();
		}else{
			path = "${ctx}/login/system_switchLogin.pt?roleId="+roleId+"&orgId="+orgId+"&orgname="+orgname;
			$('#s_form').attr("action", path).submit();;  
		}
	}else if(roleType =='adminRole'){
		roleId = $('#adminRole').combobox('getValue');
		orgId = $('#adminOrgId').combobox('getValue');alert();
		path = "${ctx}/login/system_switchLogin.pt?roleId="+roleId+"&orgId="+orgId;  
		$('#s_form').attr("action", path).submit();;  
	}else{
		roleId = $('#adminRole').combobox('getValue');
		path = "${ctx}/login/system_switchLogin.pt?roleId="+roleId;  
		$('#s_form').attr("action", path).submit();;  
	}
}

function exit(){
	window.location.href="${ctx}/login/system_exit.pt";
}
