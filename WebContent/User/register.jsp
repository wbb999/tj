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
<title>用户注册</title>
<script type="application/x-javascript">
	
	
	 addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } 


</script>
<style type="text/css">
body {
	background-image: url(images/bg.jpg);
}

.main {
	margin: auto;
	width: 400px;
	height: 345px;
	font-size: 1.1em;
	color: #eee;
	font-weight: 600;
}

h1 {
	font-size: 2.4em;
	padding-bottom: 10px;
	color: #fff;
	text-align: center;
}

h2 {
	font-size: 1.5em;
	padding-bottom: 28px;
	color: #0091e6;
	text-align: center;
}

.login {
	padding: 2em 0;
}

.inset {
	position: relative;
	padding: 2.5em;
	background: rgba(255, 255, 255, 0.1);
	border-radius: 0.3em;
	-webkit-border-radius: 0.3em;
	-o-border-radius: 0.3em;
	-moz-border-radius: 0.3em;
	box-shadow: 0px 0px 15px #545454;
}

table {
	width: 360px;
}

form span {
	display: block;
	font-size: .9em;
	color: #0091e6;
	font-weight: 400;
	font-family: 'Open Sans', sans-serif;
}

.sign input {
	height: 30px;
	weight: 55px;
	margin: 5px;
	border: none;
	color: #f0f0f0;
	background-color: #1DC51D;
}

.sign input:HOVER {
	color: #0091e6;
	background-color: #A0A5A0;
}

table input {
	padding: 3px;
	width: 93.4%;
	font-size: 14px;
	margin: 3px 0px 10px;
	color: #666666;
	background: #f0f0f0;
	border: none;
	font-family: 'Open Sans', sans-serif;
	font-weight: 400;
	outline: none;
	-webkit-transition: all 0.3s ease-out;
	-moz-transition: all 0.3s ease-out;
	-ms-transition: all 0.3s ease-out;
	-o-transition: all 0.3s ease-out;
	transition: all 0.3s ease-out;
	border-radius: 0.2em;
	-webkit-border-radius: 0.2em;
	-o-border-radius: 0.2em;
	-moz-border-radius: 0.2em;
}
.msg{
font-size:12px;
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
var usernameOK=false;
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
		} else if(valimessage=="验证码错误"||valimessage==" "||valimessage==""){
			alert("验证码错误");
			return false;
		}else if(!mailOK){
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
	xmlhttp
			.open("POST", "loginAjax_checkusername?username=" + value + "",
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
			if (fl ==2) { // 将服务器返回的数据转换为整数
				span.style.color = "red";
				span.innerHTML = "用户名已存在";
				usernameOK = false;
			} else {
				span.innerHTML = "用户名可用";
				usernameOK = true;
			}
		}
	}
	return true;
}
</script>
</head>
<body>
	<br />
	<br />
	<h1 style="margin: 0; padding: 0">文化传媒有限公司</h1>
	<div class="main">
		<div class="login">
			<div class="inset">
				<!--start-main-->
				<form action="user_register" method="post" onsubmit="return check()">
					<table>
						<tr>
							<td width="25%" align="right">用户名称：</td>
							<td width="40%"><input name="oneuser.username" type="text"
								class="input1" id="oneuser.username" onblur="checkname(this.value)"
								value="" /></td>
							<td width="25%" id="usernamemsg" class="msg"></td>
						</tr>
						<tr>
							<td align="right">创建密码：</td>
							<td><input name="oneuser.password" type="password" value=""
								class="input1" id="oneuser.password" onblur="checkPassword()" /></td>
							<td id="passwordmsg" class="msg">&nbsp;</td>
						</tr>
						<tr>
							<td align="right">确认密码：</td>
							<td><input name="rpassword" type="password" class="input1"
								id="rpassword" onblur="checkRePassword()" /></td>
							<td id="rpasswordmsg" class="msg">&nbsp;</td>
						</tr>
						<tr>
							<td align="right">真实姓名：</td>
							<td><input name="oneuser.realName" type="text" class="input1"
								id="oneuser.realName" onblur="checkRealname()" /></td>
							<td id="realNamemsg" class="msg"></td>
						</tr>
						<tr>
							<td align="right">联系电话：</td>
							<td><input name="oneuser.phone" type="text" class="input1"
								id="oneuser.phone" onblur="checkphnoe()" onchange="checkphnoe()" /></td>
							<td id="phonemsg" class="msg"></td>
						</tr>
						<tr>
							<td align="right">联系邮箱：</td>
							<td><input name="oneuser.mail" type="text" class="input1"
								id="oneuser.mail" onblur="checkMail('oneuser.mail')" onchange="checkMail('oneuser.mail')" /></td>
							<td id="mailmsg" class="msg"></td>
						</tr>


						<tr>
							<td align="right">验证码：</td>
							<td><input type="text" name="ValiImage" id="vali"
								onblur="checknumber(this.value)" value=""
								onfocus="this.value = '';this.style.color = '#ababab'"
								style="width: 90px; height: 20px; font-size: 14px;" /></td>
							<td>&nbsp;<input type="hidden" id="errowMesg"value="test"/></td>
						</tr>
						
						<tr>
							<td>&nbsp;</td>
							<td><img src="user_validatenumber"
								onclick="changeValiImage(this)" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a href=""
								onclick="changeValiImage(vali)" style="font-size: 12px;">看不清,换一张</a></td>
							<td>&nbsp;</td>
						</tr>
					</table>
					<div class="sign">
						<input type="submit" value="注册" /> <input type="button"
							value="返回登录"
							onclick="javascript:window.location.href=window.location.hash='User/login.jsp'" />
						<input type="button" value="返回主页"
							onclick="javascript:window.location.href=window.location.hash='index.jsp'" />
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>