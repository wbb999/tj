<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="/Common/BasePath.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>创建订单</title>
<script type="text/javascript"
	src="<%=basePath%>My97DatePicker/WdatePicker.js"></script>
<style type="text/css">
#container {
	width: 950px;
	height: 540px;
	background-color: #FFF;
}

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

P,div {
	border: 0;
	margin: 10px 100px 10px 100px;
}

.inputstyle {
	margin: 0px;
	padding: 9px;
	border: solid 1px #E5E5E5;
	outline: 0;
	font-family: Microsoft YaHei;
	width: 200px;
	background: #FFFFFF url('bg_form.png') left top repeat-x;
	background: -webkit-gradient(linear, left top, left 25, from(#FFFFFF),
		color-stop(4%, #EEEEEE), to(#FFFFFF));
	background: -moz-linear-gradient(top, #FFFFFF, #EEEEEE 1px, #FFFFFF 25px);
	box-shadow: rgba(0, 0, 0, 0.1) 0px 0px 8px;
	-moz-box-shadow: rgba(0, 0, 0, 0.1) 0px 0px 8px;
	-webkit-box-shadow: rgba(0, 0, 0, 0.1) 0px 0px 8px;
	color: #666;
}

label {
	margin-left: 10px;
	color: #0091e6;
	font-family: "Agency FB" Helvetica, sans-serif;
	font-size: 15px;
	font-weight: bold;
}

textarea {
	width: 400px;
	max-width: 400px;
	height: 150px;
	line-height: 150%;
	color: #999;
}

font {
	font-size: 14px;
}
</style>
<script type="text/javascript">
	function checkname(value) { // 输入框失去焦点
		var span = document.getElementById("tag");
		var xmlhttp;
		try {
			// code for IE7+, Firefox, Chrome, Opera, Safari
			xmlhttp = new XMLHttpRequest();
		} catch (e) {
			// code for IE6, IE5
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
		xmlhttp.open("POST", "loginAjax_checkusername?username=" + value.value + "",
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
					span.value = "用户名存在";
					usernameOK = true;
				} else if(fl == 1){
					span.style.color = "red";
					span.value = "用户名不存在";
					usernameOK = false;
				}
			}
		};
		return true;
	}
	function checksize(item, len) {
		if (item.value.length > len) {
			item.value = (item.value).substring(0, len);
			alert("您输入的超过" + len + "个字符了！");
		}
	}

	function checksub() {
		alert("check!");
		var productionname = document.getElementById("production.name").value;
		var productiontime = document.getElementById("production.time").value;
		var productionaddress = document.getElementById("production.address").value;
		var detail = document.getElementById("production.detail").value;
		if (productionname == "" || productionname == " "
				|| productionname == null) {
			alert("请输入订单名！");
			return false;
		} else if (productionaddress == "" || productionaddress == " "
				|| productionaddress == null) {
			alert("请输入订单地址！");
			return false;
		} else if (productiontime == "" || productiontime == " "
				|| productiontime == null) {
			alert("请输入订单时间！");
			return false;
		} else if (detail == "" || detail == " " || detail == null) {
			alert("请输入订单详情！");
			return false;
		} else {
			return true;
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
						<td class="title">订单添加</td>
					</tr>
					<tr height="30px;"></tr>
					<tr>
						<td>
							<form action="ordertable_add" method="post"
								onsubmit="return checksub()">
								<p>
									<label for="name">订单中用户的用户名：</label> <input class="inputstyle"
										type="text" name="oneorder.user.username" id="oneorder.user.username"
										onblur="checkname(this)" onchange="checkname(this)" />
										<input type="text" id="tag" name="tag" style="border: 0px;text-align: center;color:#ccc;background-color: #fff;" value="" disabled="disabled"/>

								</p>
								<p>
									<label for="name">订单中商品：暂无(后面可在订单详情中或者修订中添加)</label>
								</p>
								<p>
									<input type="submit" value="添加" style="margin-left:50px;"/>
									<input type="button" onclick="history.go(-1)" value="返回" />
								</p>

							</form>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</body>
</html>

