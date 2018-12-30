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
#Copyright {
	width: 1270px;
	height: 35px;
	padding:7px;
	font-size: 14px;
	color: #666;
	background-color: #6BADF5;
}
</style>
<script type="text/javascript">
	var mailOK = true;
	function checkMail(str) {
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
	function checkphnoe() {
		var pm = document.getElementById("phonemsg");
		var phone = document.getElementById("oneuser.phone").value;
		var length = phone.length;
		if (length == 11) {
			pm.innerHTML = "<font color=green>手机号码格式正确</font>";
		} else {
			pm.innerHTML = "<font color=red>手机号码格式错误</font>";
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
							<form action="usercustom_domodify" method="post"
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
											name="oneuser.realName" value="${oneuser.realName}" /></td>
										<td></td>
									</tr>
									<tr height="35px">
										<td>联系电话：</td>
										<td><input type="text" id="oneuser.phone"
											name="oneuser.phone" value="${oneuser.phone}"
											onblur="checkphnoe()"
											onchange="checkphnoe()" /></td>
										<td id="phonemsg" class="msg"></td>
									</tr>
									<tr height="35px">
										<td>联系邮箱：</td>
										<td><input type="text" id="oneuser.mail"
											name="oneuser.mail" value="${oneuser.mail}"
											onblur="checkMail('oneuser.mail')"
											onchange="checkMail('oneuser.mail')" /></td>
										<td id="mailmsg"></td>
									</tr>
									<tr height="35px">
										<td><input type="submit" value="确认修改" /></td>
										<td><input type="button" onclick="history.go(-1)"
											value="返回"></td>
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
	<center>
	<div id="Copyright">
			Copyright 2015.All Rights Reserved 版权所有 天津博宇鸿雁文化传媒有限公司<br />
			地址：天津市武清区徐官屯街江源道4号 &nbsp; 联系电话：029-83658860  &nbsp; 闫经理：18602943481   &nbsp; 李经理：13630257906
			 &nbsp; 传真：029-83658860
		</div>
	</center>
</body>
</html>