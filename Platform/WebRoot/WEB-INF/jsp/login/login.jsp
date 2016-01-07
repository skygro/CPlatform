<%@page contentType="text/html;charset=UTF-8"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>云平台研发中心</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta HTTP-EQUIV="Pragma" CONTENT="no-cache" />
		<meta HTTP-EQUIV="Cache-Control" CONTENT="no-cache" />
		<meta HTTP-EQUIV="Expires" CONTENT="0" />
		<script type="text/javascript" src="<%=path%>/include/commonjs/cookie.js"></script>
		<link href="<%=path%>/include/css/login.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript">
		function login() {
			document.forms[0].submit();
		}
		
		document.onkeydown = function(e) {
			if (!e)
				e = window.event;
			if ((e.keyCode || e.which) == 13) {
				login();
			}
		}
		</script>
	</head>
	<body class="unieap body_bg" style="margin-top: 0px; overflow: hidden">
	<form action="<%=path%>/login/system_login.pt" method="post">
		<div class="login_header">
			<div class="login_main">
				<h1></h1>
				<div class="login_tab l_radius">
					<dl class="l_radius2">
						<dt class="ico01"></dt>
						<dd><input type="text" class="inp"  tabIndex="1" maxLength="32" id="account" name="account" value="developer" size="20"/></dd>
					</dl>
					<dl class="l_radius2">
						<dt class="ico02"></dt>
						<dd><input type="password" class="inp f10" input tabIndex="2" name="password" maxLength="32" value="1" autocomplete="off"/></dd>
						<div height="25px" class="error_div"><span >${msg}</span></div>
					</dl>
					<p class="log"><a onclick="login()"></a></p>
				</div>
				<div class="title"><h2></h2></div>
				<div class="login_footer">Copyright  &copy; 1968- 2013 Sky Group. All Right Reserved <br /> 云平台研发中心   版权所有</div>
		     </div>
		</div> 
	</form>
</html>