<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<link rel="stylesheet" type="text/css" href="${ctx }/include/style/default.css">
<link rel="stylesheet" type="text/css" href="${ctx }/include/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${ctx }/include/js/easyui/themes/icon.css">
<script type="text/javascript" src="${ctx }/include/js/easyui/jquery.min.js"></script>
<script type="text/javascript" src="${ctx }/include/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctx }/include/js/easyui/easyui-lang-zh_CN.js"></script>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>