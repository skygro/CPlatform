var menuShowIndex = 0;
var path;
var centerTitle="首页";
//获取项目根目录
function getRootPath(){
	//获取当前网址，如： http://localhost:8080/CPlatform/main/index_indexInit.pt    
	var curWwwPath=window.document.location.href;    
	//获取主机地址之后的目录，如： CPlatform/main/index_indexInit.pt    
	var pathName=window.document.location.pathname;    
	var pos=curWwwPath.indexOf(pathName);    
	//获取主机地址，如： http://localhost:8080/    
	var localhostPaht=curWwwPath.substring(0,pos);    
	//获取带"/"的项目名，如：/CPlatform    
	var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1); 
	path = localhostPaht+projectName;
}

//创建横向菜单方法
function createMenuList() {
	$.post(path+"/main/index_menuList.pt", function(data){
		var result = [ '<ul>' ];
		var menu = eval('(' + data + ')');
		var menuIndex;
		$.each(menu, function(i, item) {
			result.push('<li>');
			result.push('<a href="');
			result.push('javascript:void(0)"');
			result.push(' onClick="getMenuStore(');
			result.push("'"+item.menu_id+"'");
			result.push(')" > ');
			result.push(item.name);
			result.push('</a>');
			result.push('</li>');
			if(i==0){
				menuIndex = item.menu_id;
			}
		});
		result.push('</ul>');
		var str = result.join("");
		$("#noLogoNgtmenus")[0].innerHTML = str;
		getMenuStore(menuIndex);
	});
}

//获取纵向菜单dataStore
function getMenuStore(menuIndex) {
	var treeStore;
	$.post(path+"/main/index_navList.pt",{id : menuIndex}, function(data){
		treeStore = eval('(' + data + ')');
		createFirstMenuTree(menuIndex,treeStore);
	});
	return treeStore;
}

//创建一级纵向菜单方法
function createFirstMenuTree(menuIndex,treeStore) {
		var result = [ '<ul class="sidebar_list">' ];
		$.each(treeStore, function(i, item) {
			result.push('<li class="sidebar_li" id="menu_' + item.menu_id + '">');
			if(item.leaf == 0){
				//树叶节点openMenu
				result.push('<a href="');
				result.push('javascript:void(0)"');
				result.push(' onClick="openMenu(');
				result.push(i + ',' + '0');
				result.push(',\'' + item.menu_id + '\')"> ');
				result.push('<img src="'+path+'/include/image/index/folder.png" style="border:0px;margin-right:5px;">');
				result.push( item.name);
				result.push('</a>');
			}else{
				//子叶节点
				result.push('<a href="');
				result.push('javascript:void(0)"');
				result.push(' onClick="chickMenu(');
				result.push(i);
				result.push(',\'' + item.menu_id + '\')"> ');
				result.push('<img src="'+path+'/include/image/index/folder.png" style="border:0px;margin-right:5px;">');
				result.push(item.name);
				result.push('</a>');	
			}
			
			result.push('</li>');
			
		});
		result.push('</ul>');
		var str = result.join("");
		$("#sidebar")[0].innerHTML = str;
}

//创建二级以下子菜单方法
function createMenuTree(parentId, level, treeStore) {
	if (treeStore == null) {
		return "";
	}
	var lenght = getStrLength(level);
	var result = [ '<div class="expanded"  id="submenu_'+parentId+'"> <ul class="submenu" >' ];
	$.each(treeStore, function(i, item) {
		result.push('<li id="menu_'+item.menu_id+'">');
		if(item.leaf == 0){
				//树叶节点
				result.push('<a href="');
				result.push('javascript:void(0)"');
				result.push(' onClick="openSubMenu(');
				result.push(i + ',' + level+',\''+item.menu_id+'\'');
				result.push(',\'' + item.menu_id + '\')"> ');
				result.push('<img src="'+path+'/include/image/index/folder.png" style="border:0px;margin-right:5px;margin-left:'+lenght+'px;">');
				result.push( item.name);
				result.push('</a>');
			}else{
				//子叶节点
				result.push('<a href="');
				result.push('javascript:void(0)"' );
				result.push(' onClick="chickChildPage(');
				result.push(i + ',' +level);
				result.push(',\'' + item.menu_id + '\')"> ');
				result.push('<img src="'+path+'/include/image/index/folder.png" style="border:0px;margin-right:5px;margin-left:'+lenght+'px;">');
				result.push(item.name);
				result.push('</a>');	
			}
			result.push('</li>');
	});
	result.push('</ul></div>');
	return result.join("");
}

//计算字符串实际长度
function getStrLength(level) {
    var realLength = 5;
    if(level != null || level != null){
    	realLength = level*10;
    }
    return realLength;
}

//展开一级纵向子菜单
//index一级菜单顺序,level层级
function openMenu(index, level, parentId) {
	//当前菜单ID
	var menuTitle = document.getElementById("menu_" + parentId);
	//当前菜单样式
	var menuClassName = menuTitle.className;
	//当前菜单HTML
	var str = menuTitle.innerHTML;
	var obj = document.getElementById("submenu_" + parentId);
	if(obj != "" && obj != null){
		var temp = (obj.className.toLowerCase() == "expanded"?"expanded":"collapsed");
		if(temp == "expanded"){
			document.getElementById("submenu_" + parentId).className = "collapsed";
		}else{
			document.getElementById("submenu_" + parentId).className = "expanded";
		}
	}else{
	   //创建二级以下子菜单
		$.post(path+"/main/index_navList.pt",{id : parentId}, function(data){
			treeStore = eval('(' + data + ')');
			var menustr = createMenuTree(parentId, level + 1, treeStore);
			menuTitle.innerHTML = str + menustr;
		});
	}
}

//展开二级以上纵向子菜单
function openSubMenu(index, level, parentId, id) {
	//当前菜单ID
	var menuTitle = document.getElementById("menu_" + id);
	//当前菜单样式
	var menuClassName = menuTitle.className;
	//当前菜单HTML
	var str = menuTitle.innerHTML;
	var obj = document.getElementById("submenu_" + id);
	if(obj != "" && obj != null){
		var temp = (obj.className.toLowerCase() == "expanded"?"expanded":"collapsed");
		if(temp == "expanded"){
			document.getElementById("submenu_" + id).className = "collapsed";
		}else{
			document.getElementById("submenu_" + id).className = "expanded";
		}
	}else{
	   //创建二级以下子菜单
		$.post(path+"/main/index_navList.pt",{id : id}, function(data){
			treeStore = eval('(' + data + ')');
			var menustr = createMenuTree(parentId, level + 1, treeStore);
			menuTitle.innerHTML = str + menustr;
		});
	}
}

//点击打开一级纵向菜单
function chickMenu(index, id) {
	 //创建二级以下子菜单
	$.post(path+"/main/index_getMenu.pt",{id : id}, function(data){
		treeStore = eval('(' + data + ')');
		openPage(treeStore);
	});
}

//点击打开二级以上纵向子菜单
function chickChildPage(index, level, id) {
	 //创建二级以下子菜单
	$.post(path+"/main/index_getMenu.pt",{id : id}, function(data){
		treeStore = eval('(' + data + ')');
		openPage(treeStore);
	});
}


//根据层级数获取纵向菜单的样式名称
function getUlStyle(level){
	var className = "sidebar_list";
	if(level == 0){
		className = "sidebar_list";
	}else if(level == 1){
		className = "submenu";
	}else{
		className = "thrmenu";
	}
	return className;
}

/**
* 获取菜单样式名称
* folder:未选中关闭的文件夹, folder_h:鼠标滑过的关闭文件夹
* open：打开的文件夹
* curr：未选中的叶子节点,curr_h:选中的叶子节点
* @param level
* @param checkType
* @param iconType
* @return
*/
function getLiStyle(checkType, leaf) {
	var className = "curr";
	if(leaf){
		if(checkType){
			className = "curr_h";
		}else{
			className = "curr";
		}
	}else{
		if(checkType){
			className = "expanded";
		}else{
			className = "collapsed";
		}
	}
	return className;
}

/************** add by longhongwen 2015091 左右移动菜单方法 *******************/
function moveLeft(objId){	
	if(menuShowIndex > 0){
		var currMenuLen = document.getElementById(objId).getElementsByTagName("ul")[0].getElementsByTagName("li").length;		
		menuShowIndex = menuShowIndex - menuShowLastLen - currMenuLen;
	}else{
		return;
	}
	createMenuList(objId);
}

function moveRight(objId){
	var store = globalDataCenter.getDataStore("appTree");
	if(menuShowIndex == store.getRowSet().getRowCount(0)){
		return;
	}
	createMenuList(objId);
}
/************** add by longhongwen 2015091 左右移动菜单方法-结束 *******************/


//打开菜单方法
function openPage(data) {
	var menuPath = data.url;
	if (menuPath == null) {
		return;
	}

	var url;
	if (menuPath.indexOf("http://") == 0 || menuPath.indexOf("https://") == 0){
		url = menuPath;
	}else{
		url = path + menuPath;
	}

	url = url + (menuPath.indexOf("?") == -1 ? "?CPMenuId=" + data.id: "&CPMenuId=" + data.id);
	var tabs = $("#tabs");
	var allTabs = tabs.tabs("tabs");
	var index = -1;
	for ( var i = 0; i < allTabs.length; i++) {
		var opt = allTabs[i].panel("options");
		if (opt.id == data.id) {
			index = i;
			break;
		}
	}

	if (index == -1) {
		tabs.tabs('add',{id : data.id,
						pathCl : '1',
						title : data.name,
						closeable : true,
						bodyCls : "noScroll",
						content : '<iframe frameborder="0" id="iframepage" width="100%" height="100%" src='+url+'></iframe>'
						//icon : 'userIcon' 
						});
	} else {
		tabs.tabs('select', index);
	}
}

//弹出异常页面信息方法
function showException(msg) {
	var index = msg.indexOf('<div id="exceptionDiv">');
	if (index == -1) {
		index = msg.indexOf('<div style="display: none;" id="exceptionDiv">');
	}
	if (index > -1) {
		var tempmsg = msg.substring(index, msg.lastIndexOf("</div>") + 6);
		msg = $(tempmsg).text();
	}
	msg = '<div style="font-size:13px;padding:10px">' + msg + '</div>';
	$('#moreInfo').bind('click', function() {
		showWindow({
			width : document.body.clientWidth - 300,
			height : document.body.clientHeight - 80,
			title : "系统运行时异常堆栈详细信息",
			iconCls : 'previewIcon',
			content : msg
		});
	});
	$("#errorWindow").window("open");
}

//关闭Tab页方法
function CloseTab(menu, type) {
	var curTabTitle = $(menu).data("tabTitle");
	var tabs = $("#tabs");
	if (type === "close") {
		var selpanel = $("#tabs").tabs('getSelected').panel("options");
		if (selpanel.id != "mainpage") {
			tabs.tabs("close", curTabTitle);			
		}
		return;				
	}
	var allTabs = tabs.tabs("tabs");
	var closeTabsTitle = [];
	for ( var i = 0; i < allTabs.length; i++) {
		var opt = allTabs[i].panel("options");
		if (opt.closeable && opt.title != curTabTitle && type == "Other") {
			closeTabsTitle.push(opt.title);
			tabs.tabs('select', i);
		} else if (opt.closeable && type == "All") {
			closeTabsTitle.push(opt.title);
		}
	}
	
	var j=closeTabsTitle.length;
	for ( var i = 0; i <j ; i++) {
		tabs.tabs("close", closeTabsTitle[i]);
	}
}

function exit(){
	var url=path+"/login/system_exit.pt"
	window.location.href = url;
}

function switchRole(){
	var url=path+"/login/system_switchRole.pt"
	window.location.href = url;
}

function changeColor(){
	$("input[type=text]").mouseenter(function(){ 
	   color=$(this).css('border-color'); 
	   $(this).css('border-color','red'); 
	  }).mouseleave(function(){ 
		 $(this).css('border-color',color); 
	  }); 
}

$(function() {
	getRootPath();
	createMenuList();
	$("#tabs").tabs({
		onSelect : function(title, index) {
			var selpanel = $("#tabs").tabs('getSelected').panel("options");
			if (selpanel.id == "mainpage") {
				$('#centerPanel').panel('setTitle', centerTitle);
			} else {
				$('#centerPanel').panel('setTitle', selpanel.pathCl);
			}
		},
		onContextMenu : function(e, title) {
			e.preventDefault();
			$('#tabsMenu').menu('show', {
				left : e.pageX,
				top : e.pageY
			}).data("tabTitle", title);
		}
	});
	/**关闭tab**/
	$("#tabsMenu").menu({
		onClick : function(item) {
			CloseTab(this, item.name);
		}
	});
});