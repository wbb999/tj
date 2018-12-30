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
<title>添加用户</title>
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
	color: #09c;
	border-bottom: dotted 1px #ccc;
	line-height: 30px;
}

#register_content {
	font-size: 14px;
	color: #666;
	margin-top: 10px;
}

#register .input1 {
	border: solid 1px #ccc;
	width: 260px;
	height: 25px;
	color: #666;
	font-weight: bold;
	font-size: 14px;
}

#register .input2 {
	border: solid 1px #ccc;
	width: 100px;
	height: 25px;
	color: #666;
	font-weight: bold;
	font-size: 14px;
}

#register .inputmsg {
	border: 0;
	color: red;
	width: 100px;
	height: 25px;
	font-size: 12px;
	height: 25px;
}

#register a {
	color: #09c;
	text-decoration: none;
}

#container {
	width: 950px;
	height: 540px;
	background-color: #FFF;
}
</style>
<script type="text/javascript">
var mailOK = false;
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
	var usernameOK = false;
	function checkPassword() {
		var pm = document.getElementById("passwordmsg");
		var password = document.getElementById("oneuser.password").value;
		var length = password.length;
		if (length > 8 || length < 4) {
			pm.innerHTML = "<font color=red>设置4~8位密码</font>";
		} else {
			pm.innerHTML = "<font color=green>密码长度符合</font>";
		}
	}
	function checkRePassword() {
		var pm = document.getElementById("rpasswordmsg");
		var password = document.getElementById("oneuser.password").value;
		var rpassword = document.getElementById("rpassword").value;
		if (password == rpassword) {
			pm.innerHTML = "<font color=green>密码一致</font>";
		} else {
			pm.innerHTML = "<font color=red>密码不一致</font>";
		}
	}
	function checkRealname() {
		var pm = document.getElementById("realNamemsg");
		var Realname = document.getElementById("oneuser.realName").value;
		var length = Realname.length;
		if (length == 0) {
			pm.innerHTML = "<font color=red>请输入真实姓名</font>";
		} else {
			pm.innerHTML = "<font color=green>符合要求</font>";
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
	function check() {
		alert("check!");
		var valimessage = document.getElementById("vali").value;
		if (usernameOK) {
			var password = document.getElementById("oneuser.password").value;
			var rpassword = document.getElementById("rpassword").value;
			var Realname = document.getElementById("oneuser.realName").value;
			var phone = document.getElementById("oneuser.phone").value;
			var length = password.length;
			if (length > 8 || length < 4) {
				alert("密码格式错误！请检查");
				return false;
			} else if (password != rpassword) {
				alert("密码不一致！请检查");
				return false;
			} else if (Realname.length == 0) {
				alert("请输入真实姓名");
				return false;
			} else if (phone.length != 11) {
				alert("电话号码格式错误");
				return false;
			} else if (valimessage == "验证码错误") {
				alert("验证码错误");
				return false;
			} else if(!mailOK){
				alert("邮箱格式错误");
				return false;
			}else {
				return true;
			}
		} else {
			alert("用户名错误，请检查");
			return false;
		}
	}

	function changeValiImage(img) {
		img.src = "user_validatenumber?time=" + new Date().getTime();
	}
	function checknumber(value) { // 输入框失去焦点

		var span = document.getElementById("vali");
		var xmlhttp;
		try {
			// code for IE7+, Firefox, Chrome, Opera, Safari
			xmlhttp = new XMLHttpRequest();
		} catch (e) {
			// code for IE6, IE5
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
		xmlhttp
				.open("POST", "loginAjax_checknumber?number=" + value + "",
						true);
		/* 	xmlhttp.setRequestHeader("Content-type",
					"application/x-www-form-urlencoded"); */
		xmlhttp.send(null);
		xmlhttp.onreadystatechange = function() {
			if (xmlhttp.readyState < 4) { // 正在交互
				span.style.color = "blue";
			}
			if (xmlhttp.readyState == 4 && xmlhttp.status == 200) { // 请求成功
				var fl = xmlhttp.responseText;
				if (fl == 1) { // 将服务器返回的数据转换为整数

				} else {
					span.style.color = "red";
					span.value = "验证码错误";
				}
			}
		};
		return true;
	}

	function checkname(value) { // 输入框失去焦点
		var length = value.length;
		var span = document.getElementById("usernamemsg");
		if (length > 8 || length < 3) {
			span.innerHTML = "<font color=red>设置您的用户名称3~8位</font>";
			usernameOK = false;
			return false;
		}

		var xmlhttp;
		try {
			// code for IE7+, Firefox, Chrome, Opera, Safari
			xmlhttp = new XMLHttpRequest();
		} catch (e) {
			// code for IE6, IE5
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
		xmlhttp.open("POST", "loginAjax_checkusername?username=" + value + "",
				true);
		/* 	xmlhttp.setRequestHeader("Content-type",
					"application/x-www-form-urlencoded"); */
		xmlhttp.send(null);
		xmlhttp.onreadystatechange = function() {
			if (xmlhttp.readyState < 4) { // 正在交互
				span.style.color = "blue";
			}
			if (xmlhttp.readyState == 4 && xmlhttp.status == 200) { // 请求成功
				var fl = xmlhttp.responseText;
				if (fl == 2) { // 将服务器返回的数据转换为整数
					span.style.color = "red";
					span.innerHTML = "用户名已存在";
					usernameOK = false;
				} else {
					span.innerHTML = "用户名可用";
					usernameOK = true;
				}
			}
		};
		return true;
	}
	function clean(){
		document.getElementById("oneuser.username").value = "";
		document.getElementById("oneuser.password").value = "";
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
						<td class="title">用户添加</td>
					</tr>
					<tr height="30px;"></tr>
					<tr>
						<td>
							<form action="user_adduser" method="post"
								onsubmit="return check()">
								<table width="100%" border="0" cellpadding="5" cellspacing="0"
									id="register_content">
									<tr>
										<td width="35%" align="right">用户名称：</td>
										<td width="40%"><input name="oneuser.username" type="text"
											class="input1" id="oneuser.username"
											onblur="checkname(this.value)" value="" /></td>
										<td width="25%" id="usernamemsg"></td>
									</tr>
									<tr>
										<td align="right">创建密码：</td>
										<td><input name="oneuser.password" type="password" value=""
											class="input1" id="oneuser.password" onblur="checkPassword()" /></td>
										<td id="passwordmsg">&nbsp;</td>
									</tr>
									<tr>
										<td align="right">确认密码：</td>
										<td><input name="rpassword" type="password"
											class="input1" id="rpassword" onblur="checkRePassword()" /></td>
										<td id="rpasswordmsg">&nbsp;</td>
									</tr>
									<tr>
										<td align="right">用户类别：</td>
										<td><c:if test="${oneuser.getType().getId()==1}">
										<input type="radio" name="oneuser.type.id" value="2"
											/>&nbsp;&nbsp;管理员  </c:if><input type="radio"
											name="oneuser.type.id" value="3" />会员  <input type="radio"
											name="oneuser.type.id" value="4" checked="checked" />普通用户 </td>
									</tr>
									<tr>
										<td align="right">真实姓名：</td>
										<td><input name="oneuser.realName" type="text"
											class="input1" id="oneuser.realName" onblur="checkRealname()" /></td>
										<td id="realNamemsg"></td>
									</tr>
									<tr>
										<td align="right">联系电话：</td>
										<td><input name="oneuser.phone" type="text" class="input1"
											id="oneuser.phone" onblur="checkphnoe()" onchange="checkphnoe()" /></td>
										<td id="phonemsg"></td>
									</tr>
									<tr>
										<td align="right">联系邮箱：</td>
										<td><input name="oneuser.mail" type="text" class="input1"
											id="oneuser.mail" onblur="checkMail('oneuser.mail')"
											onchange="checkMail('oneuser.mail')" /></td>
										<td id="mailmsg" class="msg"></td>
									</tr>
									<tr>
										<td align="right">验证码：</td>
										<td><input type="text" name="ValiImage" id="vali"
											onblur="checknumber(this.value)" value=""
											onfocus="this.value = '';this.style.color = '#ababab'"
											style="width: 70px; height: 20px;" /> <img
											src="user_validatenumber" onclick="changeValiImage(this)" />
											<a href="" onclick="changeValiImage(vali)">看不清,换一张</a></td>
										<td>&nbsp;</td>
									</tr>
									<tr>
										<td align="center">&nbsp;</td>
										<td align="left"><input type="submit" value="添加"
											width="70" height="30" /></td>
										<td align="center"><input type="button"
											onclick="history.go(-1)" value="返回" /></td>
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