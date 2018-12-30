<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="/Common/BasePath.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>创建产品</title>
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
	height: 130px;
	line-height: 150%;
	color: #999;
}
</style>
<script type="text/javascript">
	function checksub() {
		alert("check!");
		var activityname = document.getElementById("service_product.name").value;
		var activityprice = document.getElementById("service_product.price").value;
		var intro = document.getElementById("service_product.intro").value;
		if (activityname == "" || activityname == " " || activityname == null) {
			alert("请输入产品名！");
			return false;
		} else if (activityprice == "" || activityprice == " "
				|| activityprice == null) {
			alert("请输入产品价格！");
			return false;
		} else if (isNaN(activityprice)) {
			alert("请在价格栏输入数字！");
			return false;
		} else if (intro == "" || intro == " " || intro == null) {
			alert("请输入产品详情！");
			return false;
		} else {
			return true;
		}
	}
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
</script>
</head>
<body onload="check(service_product.intro,ContentUse,ContentRem,254)">
	<table border="0" align="center" cellpadding="0" cellspacing="0"
		id="container">
		<tr>
			<td width="670" valign="top">
				<table border="0" align="center" cellpadding="0" cellspacing="0"
					id="register">
					<tr>
						<td class="title">产品修改</td>
					</tr>
					<tr>
						<td>
							<form action="service_product_update" method="post"
								onsubmit="return checksub()">
								<input type="hidden" name="service_product.id"
									id="service_product.id" value="${service_product.id }" />
								<p>
									<label for="name" style="float: left">产品图片：</label><img width="140px" height="100px" src="${service_product.picture}"/>

								</p>
								<p>
									<label for="name">产品名称：</label> <input class="inputstyle"
										type="text" name="service_product.name" id="service_product.name"
										value="${service_product.name}" onblur="checksize(this,10)"
										onchange="checksize(this,10)" /><font color="#7F7F7F">
										[10个字以内]</font>

								</p>
								<p>
									<label for="email">产品类别：</label> <input type="radio"
										name="service_product.type.id" id="service_product.type.id" value="1"
										<c:if test="${service_product.type.id==1}">checked="checked"</c:if> />实体产品
									<input type="radio" name="service_product.type.id"
										id="service_product.type.id" value="2"
										<c:if test="${service_product.type.id==2}">checked="checked"</c:if> />服务产品
										<input type="radio" name="service_product.type.id"
										id="service_product.type.id" value="3"
										<c:if test="${service_product.type.id==3}">checked="checked"</c:if> />服务产品

								</p>

								<p>
									<label for="email">产品价格：</label> <input type="text"
										class="inputstyle" name="service_product.price"
										id="service_product.price" value="${service_product.price }" />
									&nbsp;元

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
									<label style="float: left">产品简介：<br /> <br /> <br />
										<br /> <br /> <b style="font-size: 12px; color: #0091e6">300字以内</b></label>
									<textarea name="service_product.intro" id="service_product.intro"
										onkeydown="check(this,ContentUse,ContentRem,254)"
										onkeyup="check(this,ContentUse,ContentRem,254)"
										onchange="check(this,ContentUse,ContentRem,254)"
									>${service_product.intro } </textarea>
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

