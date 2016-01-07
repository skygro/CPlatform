<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>


<link rel="stylesheet" type="text/css" href="${ctx}/include/jquery/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${ctx}/include/jquery/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="${ctx}/include/css/style.css">
<script type="text/javascript" src="${ctx}/include/jquery/jquery-1.7.2.min.js"></script> 
<!-- 自定义 -->
<link rel="stylesheet" type="text/css" href="${ctx}/include/css/icon.css">
<script type="text/javascript" src="${ctx}/include/jquery/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctx}/include/jquery/easyui/easyui-lang-zh_CN.js"></script>

<script type="text/javascript" src="${ctx }/include/js/page.js"></script>
<!-- 页面样式 -->
<link rel="stylesheet" type="text/css" href="${ctx}/include/css/default.css">


