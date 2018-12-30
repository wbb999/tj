<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/Common/BasePath.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
/*register*/
#register {
	width: 90%;
	margin-top: 10px;
	padding: 20px;
}

#register .title {
	font-size: 16px;
	font-weight: bold;
	text-align: left;
	color: #09c;
	border-bottom: dotted 1px #ccc;
	line-height: 30px;
}

#register_content {
	font-size: 14px;
	color: #666;
	margin-top: 10px;
}

#container {
	width: 950px;
	height: 540px;
	background-color: #FFF;
}

a {
	text-decoration: none;
}

a:HOVER {
	
}

.tdbut {
	background-image: url(images/bdbg.gif);
}

.tdbut:HOVER {
	border: 2px blue solid;
}

tr {
	margin: 0px;
	padding: 0px;
}

td {
	text-align: center;
}
</style>
</head>
<body>
	<table border="0" align="center" cellpadding="0" cellspacing="0"
		id="container">
		<tr>
			<td width="670" valign="top">
				<table border="0" align="center" cellpadding="0" cellspacing="0"
					id="register">
					<tr>
						<td class="title">欢迎登陆</td>
					</tr>
					<tr height="30px;"></tr>
					<tr>
						<td>
							<form action="" method="post"></form>
							<table width="90%" border="0" align="center" cellpadding="5"
								cellspacing="0" id="register_content">
								<tr height="35px">
									<td width="12%" style="text-align: right"><img alt=""
										src="images/user.png"></td>
									<td width="88%" style="text-align: left"></td>
								</tr>
								<tr height="35px">
									<td style="text-align: right">用户名称：</td>
									<td style="text-align: left">${oneuser.username}</td>
								</tr>
								<tr height="35px">
									<td style="text-align: right">联系电话：</td>
									<td style="text-align: left">${oneuser.phone}</td>
								</tr>
								<tr height="35px">
									<td style="text-align: right">联系邮箱：</td>
									<td style="text-align: left">${oneuser.mail}</td>
								</tr>
								<tr height="35px">
									<td colspan="2" style="text-align: left">&nbsp; <a
										href="user_usermassage">编辑个人详细信息</a></td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</body>
</html>