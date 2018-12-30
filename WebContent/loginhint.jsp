<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>未登录提示</title>
<style type="text/css">
body {
	font-size: 14px;
	color: #696968;
	width:95%;
	height:200px;
	margin: 10px auto;
	background-color:#fff;
	border: 1px solid #ccc;
	border-radius: 1em;
}
</style>
</head>
<body>
	<center>
		<form action="User/login.jsp" method="post" target="_top">
			<h1>对不起您还未登录，请登录</h1>
			<input type="submit" value="登录" /> &nbsp;&nbsp; <input type="button" value="返回" onclick="history.go(-1)"/>
		</form>
	</center>
</body>
</html>