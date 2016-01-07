var Common = {
	/**性别格式化**/
	SexFormatter : function (value, rec, index) {
        if (value == undefined) {
            return "";
        }
        if(value == 1){
        	return "男";
        }else if(value == 2){
        	return "女";
        }else{
        	return "未知";
        }
    },
    /**启用状态格式化**/
    Enabled : function (value, rec, index) {
        if (value == undefined) {
            return "";
        }
        if(value == 0){
        	return "停用";
        }else if(value == 1){
        	return "启用";
        }else{
        	return "";
        }
    },
    /**锁定标志格式化**/
    Locked : function (value, rec, index) {
        if (value == undefined) {
            return "";
        }
        if(value == 0){
        	return "激活";
        }else if(value == 1){
        	return "锁定";
        }else{
        	return "";
        }
    },

	RadioFormatter : function (value, rec, index) {
	    return "<input id='radio_id' name='radio_name' type='radio' value='" + rec.id + "'/>";
	}
}

$(function(){ 
    $("input[type=text]").mouseenter(function(){ 
		color=$(this).css('border-color'); 
		$(this).css('border-color','#2a9ad4'); 
	}).mouseleave(function(){ 
		$(this).css('border-color',color); 
	}); 
    $("input[type=password]").mouseenter(function(){ 
		color=$(this).css('border-color'); 
		$(this).css('border-color','#2a9ad4'); 
	}).mouseleave(function(){ 
		$(this).css('border-color',color); 
	}); 
}); 


function reset(){
	$('#f_form')[0].reset();
}

document.onkeydown = function(e) {
	if (!e)
		e = window.event;
	if ((e.keyCode || e.which) == 13) {
		login();
	}
}


