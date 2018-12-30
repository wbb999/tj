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
<title></title>
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
	text-align: left;
}
</style>
<script type="text/javascript">
	var mailOK = true;
	function checkMail(str) {
		alert("checkMail!");
		var pm = document.getElementById("mailmsg");
		var strReg = "";
		var r;
		var strText = document.all(str).value;
		//strReg=/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/i;
		strReg = /^\w+((-\w+)|(\.\w+))*\@{1}\w+\.{1}\w{2,4}(\.{0,1}\w{2}){0,1}/ig;
		r = strText.search(strReg);
		if (r == -1) {
			pm.innerHTML = "<font color=red>邮箱格式错误</font>";
			document.all(str).focus();
			mailOK = false;
		} else {
			pm.innerHTML = "<font color=green>邮箱格式正确</font>";
			mailOK = true;
		}
	}
	function check() {
		if (!mailOK) {
			alert("请输入正确的邮箱");
			return false;
		} else {
			if (confirm("确认修改吗？")) {
				return true;
			} else {
				return false;
			}
		}
	}
</script>
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
							<form action="user_adminroot" method="post"
								onsubmit="return check()">
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
										<td><input type="text" value="${oneuser.username}"
											disabled="disabled" /> <input type="hidden"
											name="oneuser.username" id="oneuser.username"
											value="${oneuser.username}" /></td>
										<td>用户名不可修改</td>
									</tr>
									<tr height="35px">
										<td>真实姓名：</td>
										<td><input type="text" id="oneuser.realName"
											name="oneuser.realName" value="${oneuser.realName}" disabled="disabled"/></td>
										<td></td>
									</tr>
									<tr>
										<td>用户类别：</td>
										<td><c:if test="${adminuser.getType().getId()==1}">
												<input type="radio" name="oneuser.type.id" value="2"
													<c:if test="${oneuser.getType().getId()==2}">checked="checked"</c:if>/>&nbsp;&nbsp;管理员  </c:if> <input
											type="radio" name="oneuser.type.id" value="3"
											<c:if test="${oneuser.getType().getId()==3}">checked="checked"</c:if> />会员
											<input type="radio" name="oneuser.type.id" value="4"
											<c:if test="${oneuser.getType().getId()==4}">checked="checked"</c:if> />普通用户</td>
									</tr>
									<tr height="35px">
										<td>联系电话：</td>
										<td><input type="text" id="oneuser.phone"
											name="oneuser.phone" value="${oneuser.phone}" disabled="disabled"/></td>
										<td></td>
									</tr>
									<tr height="35px">
										<td>联系邮箱：</td>
										<td><input type="text" id="oneuser.mail"
											name="oneuser.mail" value="${oneuser.mail}"
											disabled="disabled"/></td>
										<td id="mailmsg"></td>
									</tr>
									<tr height="35px">
										<td><input type="submit" value="确认修改" /></td>
										<td><input type="button" onclick="history.go(-1)"
											value="返回"></td>
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