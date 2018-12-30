<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/Common/BasePath.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>创建作品</title>
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
	height: 110px;
	line-height: 150%;
	color: #999;
}

font {
	font-size: 14px;
}
</style>
<script type="text/javascript">
	function check(fieldname, usename, remname, len) {
		if (eval(fieldname.value.length) > len) {
			fieldname.value = (fieldname.value).substring(0, len);
			alert("您输入的超过" + len + "个字符了！");
			fieldisOK = false;
		} else {
			usename.value = eval(fieldname.value.length);
			remname.value = len - usename.value;
			fieldisOK = true;
		}
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
		if (productionname == "" || productionname == " " || productionname == null) {
			alert("请输入作品名！");
			return false;
		} else if (productionaddress == "" || productionaddress == " "
				|| productionaddress == null) {
			alert("请输入作品地址！");
			return false;
		} else if (productiontime == "" || productiontime == " "
				|| productiontime == null) {
			alert("请输入作品时间！");
			return false;
		} else if (detail == "" || detail == " " || detail == null) {
			alert("请输入作品详情！");
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
						<td class="title">作品更新</td>
					</tr>
					<tr>
						<td>
							<form action="production_update" method="post"
								onsubmit="return checksub()">
								<input type="hidden" id="production.id" name="production.id"
									value="${production.id}">
								<p>
									<label for="name" style="float: left">作品图片：</label><img width="140px" height="100px" src="${production.picture}"/>

								</p>
								<p>
									<label for="name">作品名称：</label> <input class="inputstyle"
										type="text" name="production.name"
										id="production.name" value="${ production.name}"
										onblur="checksize(this,15)" onchange="checksize(this,15)" /><font
										color="#7F7F7F"> [15个字以内]</font>

								</p>
								<p>
									<label for="email">作品地点：</label> <input class="inputstyle"
										type="text" name="production.address" id="production.address"
										value="${production.address }" onblur="checksize(this,15)"
										onchange="checksize(this,15)" /><font color="#7F7F7F">
										[15个字以内]</font>

								</p>

								<p>
									<label for="email">作品时间：</label> <input type="text"
										class="inputstyle" name="production.time"
										id="production.time"
										value="<fmt:formatDate value='${production.time }' pattern='yyyy-MM-dd HH:mm:ss'/>"
										onFocus="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd HH:mm:ss'})" />

								</p>
								<center>
									<font color="#7F7F7F"> [信息内容最多不得超过254个字符]</font> &nbsp; <font
										color="#7F7F7F"> 已用：<input type="text"
										name="ContentUse" value="0" size="4" disabled
										style="text-align: center; border: 0;"> 个&nbsp;&nbsp;
										剩余：<input type="text" name="ContentRem" value="254" size="4"
										disabled style="text-align: center; border: 0;"> 个
									</font>
								</center>
								<p>
									<label style="float: left">作品简介：</label>
									<textarea name="production.detail" id="production.detail"
										onkeydown="check(this,ContentUse,ContentRem,254)"
										onkeyup="check(this,ContentUse,ContentRem,254)"
										onchange="check(this,ContentUse,ContentRem,254)">${production.detail}</textarea>
								</p>

								<p>
									<input type="submit" value="修改" />&nbsp; &nbsp;&nbsp; &nbsp;<input
										type="button" value="返回" onclick="history.go(-1)">
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

