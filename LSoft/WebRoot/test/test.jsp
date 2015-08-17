<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/include/jsp/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>龙讯综合信息平台</title>
    <script type="text/javascript">
        //加载列表数据
        $(function() {
				/* $('#dg').datagrid( {
					loadMsg: "数据加载中，请稍后...",
					url:'${ctx}/jcgl/cjhy_getList.pt', 
					loadFilter : pagerFilter
				}); */
				alert();
				
		});
		</script>
</head>
<body class="easyui-layout">
<h2>Add and Remove Layout</h2>
	<p>Click the buttons below to add or remove region panel of layout.</p>
	<div id="cc" class="easyui-layout" style="width:700px;height:350px;">
		<div data-options="region:'north'" style="height:50px"></div>
		<div data-options="region:'south',split:true" style="height:50px;"></div>
		<div data-options="region:'east',split:true" title="East" style="width:100px;"></div>
		<div data-options="region:'west',split:true" title="West" style="width:100px;"></div>
		<div data-options="region:'center',title:'Center'"></div>
	</div>
</body>
</html>