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
	margin-top: 30px;
	padding-left:20px;
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
}

a {
	text-decoration: none;
}

tr {
	margin: 0px;
	padding: 0px;
}

td {
	text-align: left;
}
</style>
<script type="text/javascript">
	function check() {
		var pw = document.getElementById("oneuser.password").value;
		var rpw = document.getElementById("rpassword").value;
		if (pw.length > 8) {
			alert("密码长度超出上限！请重新输入");
			document.getElementById("oneuser.password").value = "";
			document.getElementById("rpassword").value = "";
			return false;
		}
		if (pw.length < 4) {
			alert("密码长度未达到标准！请重新输入");
			document.getElementById("oneuser.password").value = "";
			document.getElementById("rpassword").value = "";
			return false;
		}
		if (pw != rpw) {
			alert("密码不一致！请重新输入！");
			document.getElementById("oneuser.password").value = "";
			document.getElementById("rpassword").value = "";
			return false;
		}
	}
	function clean(){
		document.getElementById("errowMesg").value = "";
	}
</script>
</head>
<body onload="clean()">
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
							<form action="usercustom_userpswmodify" method="post"
								onsubmit="return check()">
								<table width="90%" border="0" align="center" cellpadding="5"
									cellspacing="0" id="register_content">

									<tr height="35px">
										<td style="width: 12%; text-align: center;"><img alt=""
											src="images/user.png"></td>
										<td width="30%"></td>
										<td width="18%"></td>
										<td width="40%"></td>
									</tr>
									<tr height="35px">
										<td>用户名称：</td>
										<td>${oneuser.username}</td>
										<td></td>
										<td></td>
									</tr>
									<tr height="35px">
										<td>真实姓名：</td>
										<td>${oneuser.realName}</td>
										<td></td>
										<td></td>
									</tr>
									<tr height="35px">
										<td>联系电话：</td>
										<td>${oneuser.phone}</td>
										<td></td>
										<td></td>
									</tr>
									<tr height="35px">
										<td>原始密码：</td>
										<td><input type="password" id="errowMesg"
											name="errowMesg" value="" /></td>
										<td></td>
										<td></td>
									</tr>
									<tr height="35px">
										<td>新密码：</td>
										<td><input type="password" id="oneuser.password"
											name="oneuser.password" value="" /></td>
										<td><input type="submit" value="修改"></td>
										<td></td>
									</tr>
									<tr height="35px">
										<td>重复密码：</td>
										<td><input type="password" id="rpassword"
											name="rpassword" value="" /></td>
										<td><input type="button" onclick="history.go(-1)"
											value="返回"></td>
										<td></td>
									</tr>
									<tr height="35px">
										<td colspan="3">密码为4~8位字母和数字组合</td>
										<td></td>
									</tr>
									<tr height="35px">
										<td colspan="3"><h2 style="color: red">${errowMesg}</h2></td>
										<td></td>
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