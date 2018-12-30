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
<title>用户登录</title>
<link href="css/login.css" rel='stylesheet' type='text/css' />
<script type="application/x-javascript">
	
	
	 addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } 


</script>
<script type="text/javascript">
	function check() {
		var nu = document.getElementById("oneuser.username").value;//获取username文本框值
		if (nu != null && nu != "" && nu != " ") {//用户名不为空验证
			var vali = document.getElementById("vali").value;
			if (vali == "验证码错误"||vali == ""||vali == " ") {
				alert("验证码错误");
				return false;
			}
			return true;
		} else {
			alert("请输入用户名！");
			var pm = document.getElementById("errowMesg");
			pm.innerHTML = "";//修改提示信息
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
	function clean(){
		document.getElementById("oneuser.password").value = "";
	}
</script>
</head>
<body onload="clean()">
	<br />
	<br />
	<h1>文化传媒有限公司</h1>
	<div class="main">
		<div class="login">
			<div class="inset">
				<!--start-main-->
				<form action="user_login" method="post" onsubmit="return check()">
					<div>
						<h2>用户登录</h2>
						<span><label>用户名</label></span> <span><input type="text"
							class="textbox" id="oneuser.username" name="oneuser.username"
							value="${oneuser.username}" onfocus="this.style.color = '#ababab';this.value = '';"
							onblur="if (this.value == '') {this.style.color = 'red';this.value = '请输入用户名';}"></span>
					</div>
					<div>
						<span><label>密码</label></span> <span><input type="password"
							class="password" id="oneuser.password" name="oneuser.password" value=""></span>
					</div>
					<div style="height: 20px;">
					<span id="errowMesg" style="color: red; font-weight: bold;">${errowMesg}</span>
					<!--<input type="hidden" id="errowMesg" name="errowMesg"value="test"/>-->
					<input type="hidden" id="usertype.id" name="usertype.id"value="1"/>
					
					</div>
					<table>
						<tr style="line-height: 30px;">
							<td><span style="color: #0091e6; font-size: 15px;">输入验证码：</span></td>
							<td><input type="text" name="ValiImage" id="vali"
								onblur="checknumber(this.value)" value=""
								onfocus="this.value = '';this.style.color = '#ababab'"
								style="width: 100px; height: 20px;" /></td>
						</tr>
						<tr style="line-height: 30px;">
							<td colspan="2"><p>
									<img src="user_validatenumber" onclick="changeValiImage(this)" />
								</p></td>
						</tr>
					</table>
					<div class="sign">
						<input type="submit" value="登录" /> 
							<input type="button"
							value="注册"
							onclick="javascript:window.location.href=window.location.hash='Usercustom/register.jsp'" />
							<input type="button"
							value="返回主页"
							onclick="javascript:window.location.href=window.location.hash='index.jsp'" />
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>