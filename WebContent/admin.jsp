<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/Common/BasePath.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<base href="<%=basePath%>" />
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>后台管理</title>
<style type="text/css">
body {
	width: 1366px;
}

body,div,h1,ul,a,li,center {
	margin: 0px;
	padding: 0px;
}

#top_bg {
	width: 100%;
	height: 35px;
	color: #ccc;
	font-weight: normal;
}

.top_right {
	float: right;
	margin-right: 45px;
	margin-top: 10px;
}

.top_left {
	float: left;
	margin-top: 10px;
	margin-left: 45px;
}

#top_bg a {
	color: #ccc;
	text-decoration: none;
}
#top_bg a:HOVER {
	color: blue;
}

a:hover {
	color: #333;
	text-decoration: none;
}

#logoa {
	font-weight: bold;
	padding: 10px 15px;
	float: left;
	margin-left: 30px;
	color: #fff;
	text-decoration: none;
}

#header-bottom {
	width: 100%;
	height: 85px;
	background-image: url(images/bdbg.gif);
}

#header-bottom ul li a {
	font-size: 15px;
	color: #CACCCE;
	text-decoration: none;
	font-weight: bold;
}

#header-bottom ul li a:HOVER {
	color: #00A0DC;
}

#header-bottom ul li {
	float: left;
	padding-left: 70px;
	padding-top: 25px;
	list-style: none;
}
</style>
</head>
<body>
	<!--header-->
		<div id="top_bg">
			<a href="user_usermassage" target="admin_main" class="top_left">${oneuser.getType().getName()}：${oneuser.username} 欢迎登陆管理系统！</a><a href="User/login.jsp"
				class="top_right">退出登陆</a>
		</div>
		<div id="header-bottom">
			<br /> 
			<h1>
				<a href="index.jsp?oneuser.username=${oneuser.username}" id="logoa">文化传媒有限公司</a>
			</h1>
			<div>
				<ul>
					<li><a href="user_getuser" target="admin_main">后台主页</a></li>
					<li><a href="user_allUserByPage" target="admin_main">用户管理</a></li>
					<li><a href="ordertable_allordertableByPage" target="admin_main">订单管理</a></li>
					<li><a href="service_product_allservice_productByPage" target="admin_main">上传产品</a></li>
					<li><a href="production_allProductionByPage" target="admin_main">上传作品</a></li>
					<li><a href="nomalquestion_allnomalquestionByPage" target="admin_main">上传问题</a></li>
				</ul>
			</div>
		</div>
	<div
		style="width: 100%; height: 580px; background-image: url(images/bgw.jpg);">
		<center>
			<iframe name="admin_main" src="user_getuser" scrolling="no"
				width="1366px" height="540px" frameborder="0"> </iframe>
		</center>
		<div style="width: 100%; height: 8px;"></div>
		<div
			style="width: 100%; height: 35px; background-image: url(images/bdbg.gif);">
		</div>
	</div>

</body>
</html>