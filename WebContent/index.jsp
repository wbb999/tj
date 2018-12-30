<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/Common/BasePath.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>文化传媒有限公司</title>
<style type="text/css">
body {
	width: 1366px;;
}

body,div,h1,ul,a,li,center {
	margin: 0px;
	padding: 0px;
}

#top_bg {
	width: 100%;
	height: 30px;
	color: #ccc;
	outline: 0;
	font-family: Microsoft YaHei;
	background: -webkit-gradient(linear, left top, left 25, from(#FFFFFF),
		color-stop(4%, #EEEEEE), to(#FFFFFF));
	background: -moz-linear-gradient(bottom, #FFFFFF, #EEEEEE 1px, #FFFFFF 25px);
	box-shadow: rgba(0, 0, 0, 0.05) 0px 0px 8px;
	-moz-box-shadow: rgba(0, 0, 0, 0.05) 0px 0px 8px;
	-webkit-box-shadow: rgba(0, 0, 0, 0.05) 0px 0px 8px;
}

#bg_bg {
	width: 1270px;
	height: 20px;
	border-left: 1px #eee solid;
	border-right: 1px #eee solid;
	outline: 0;
	font-family: Microsoft YaHei;
	background: -webkit-gradient(linear, left top, left 25, from(#FFFFFF),
		color-stop(4%, #EEEEEE), to(#FFFFFF));
	background: -moz-linear-gradient(bottom, #FFFFFF, #EEEEEE 1px, #FFFFFF 20px);
	box-shadow: rgba(0, 0, 0, 0.1) 0px 0px 2px;
}

.nav-menu {
	float: right;
	margin-right: 45px;
	margin-top: 5px;
}

.top_left {
	float: left;
	margin-top: 5px;
	margin-left: 45px;
}

#top_bg ul li {
	padding-right: 10px;
	list-style: none;
	float: left;
}

#top_bg ul li a {
	color: #ccc;
	text-decoration: none;
}
#top_bg ul li a:HOVER{
	color: blue;
}

#logoa {
	font-weight: bold;
	margin-left: 45px;
	color: #fff;
	text-decoration: none;
}

#header-bottom {
	width: 100%;
	height: 70px;
}

#header_bar {
	width: 100%;
	height: 150px;
	background-image: url(images/top_top1.png);
	background-repeat: repeat-x;
}
#bottom-right{
	margin-left:15px;
}
#header-bottom ul li a {
	padding: 5px 30px 5px 30px;
	font-size: 20px;
	/* color: #CACCCE; */
	color: #fff;
	text-decoration: none;
	font-weight: bold;
}

#header-bottom ul li a:HOVER {
	color: #00A0DC;
	background-color: #080808;
	border: 1px #fff solid;
}

#header-bottom ul li {
	float: left;
	padding-top: 25px;
	list-style: none;
}

#Copyright {
	width: 1270px;
	height: 35px;
	padding: 7px;
	font-size: 14px;
	color: #666;
	background-color: #6BADF5;
}
</style>
<script type="text/javascript">
	function clean(it) {
		it.value = "";
	}
	function check(){
		var key = document.getElementById("key").value;
		if(key==""||key=="请输入关键字"){
			alert("请输入关键字！");
			return false;
		}else{
			return true;
		}
		
	}
</script>
</head>
<body>
	<!--header-->
	<div id="top_bg">
		<div class="top_left">
			<ul class="menu">
				<li><a href="search_question" target="main">帮助</a></li>
				<li>|</li>
				<li><a href="home/contacts.jsp" target="main">联系我们</a></li>
				<c:if test="${oneuser.username!=null}">
				<li>|</li>
				<li><a href="usercustom_trolley" target="main">购物车</a></li>
				<li>|</li>
				<li><a href="usercustom_orderlist" target="main">历史订单</a></li>
				</c:if>
			</ul>
		</div>
		<div class="nav-menu">
			<ul class="menu">
				<li>服务热线:029-8365****</li>
				<li>|</li>
				<li><c:if test="${oneuser.username==null}">
						<a href="User/login.jsp">登陆</a>
						<a href="Usercustom/register.jsp">注册</a>
					</c:if> <c:if test="${oneuser.username!=null}">
						<a href="usercustom_cusmassage" target="main">${oneuser.username}</a>
						<a href="user_logout">退出</a>
					</c:if></li>
			</ul>
		</div>
	</div>
	<div id="header_bar">
		<div style="height: 30px; width: 100%"></div>
		<div id="header-bottom">
			<h1>
				<a id="logoa">文化传媒有限公司</a>
			</h1>
			<div id="bottom-right">
				<ul>
					<li><a href="mainpage_homeindex" target="main">主页</a></li>
					<li><a href="mainpage_business" target="main">主营业务</a></li>
					<li><a href="mainpage_homemain" target="main">作品展示</a></li>
					<li><a href="search_question" target="main">常见问题</a></li>
					<li><a href="home/contacts.jsp" target="main">联系我们</a></li>
				</ul>
			</div>
		</div>
		<form action="search_search" method="post" target="main" onsubmit="return check()" style="float:right;margin-right:45px">
			<span style="margin-left: 48px">查询域</span> <select name="searchtype"
				id="searchtype">
				<option value="1">主营产品</option>
				<option value="2">常见问题</option>
				<option value="3">精彩作品</option>
			</select> <input type="text" id="key" name="key" value="请输入关键字"
				onfocus="clean(this)" /> <input type="submit" value="查找" />
		</form>
	</div>


	<center>
		<div id="bg_bg"></div>
		<iframe name="main" src="mainpage_homeindex" scrolling="no"
			width="1270px" height="1500px" frameborder="0"> </iframe>



		<div id="Copyright">
			Copyright 2015.All Rights Reserved 版权所有 **文化传媒有限公司<br />
			地址：天津市***道8号 &nbsp; 联系电话：029-8365**** &nbsp; 闫经理：186********
			&nbsp; 李经理：136******** &nbsp; 传真：029-8365****
		</div>
	</center>

</body>
</html>