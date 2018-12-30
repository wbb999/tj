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
<title>用户个人信息</title>
<style type="text/css">
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

td a {
	text-decoration: none;
	coler: blue;
}

tr {
	margin: 0px;
	padding: 0px;
}

td {
	text-align: left;
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
						<td class="title">个人信息</td>
					</tr>
					<tr height="30px;"></tr>
					<tr>
						<td>
							<form action="" method="post">
								<table width="90%" border="0" align="center" cellpadding="5"
									cellspacing="0" id="register_content">
									<tr height="35px">
										<td style="width: 12%; text-align: center;"><img alt=""
											src="images/user.png"></td>
										<td width="30%"></td>
										<td width="58%"></td>
									</tr>
									<tr height="35px">
										<td>用户名称：</td>
										<td>${oneuser.username}</td>
										<td></td>
									</tr>
									<tr height="35px">
										<td>用户类别：</td>
										<td>${oneuser.getType().getName()}</td>
										<td><a
											href="user_usermassagemodify">更新个人信息</a></td>
									</tr>
									<tr height="35px">
										<td>真实姓名：</td>
										<td>${oneuser.realName}</td>
										<td><a
											href="user_userpwdmodify">更新个人密码</a></td>
									</tr>
									<tr height="35px">
										<td>联系电话：</td>
										<td>${oneuser.phone}</td>
										<td><input type="button" onclick="history.go(-1)"
											value="返回"></td>
									</tr>
									<tr height="35px">
										<td>联系邮箱：</td>
										<td>${oneuser.mail}</td>
										<td></td>
									</tr>
									<tr height="35px">
										<td colspan="3"><h2 style="color: green">${errowMesg}</h2></td>
									</tr>
								</table>
							</form>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>

</body>
</html>