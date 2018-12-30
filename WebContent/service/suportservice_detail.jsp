<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="/Common/BasePath.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>产品详细信息展示</title>
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
						<td class="title">产品详情</td>
					</tr>
					<tr>
						<td>
							<form action="service_product_toupdate" method="post">
								<input  type="hidden" name="service_product.id" id="service_product.id" value="${service_product.id }"/>
								<p>
									<label for="name" style="float: left">产品图片：</label><img width="140px" height="100px" src="${service_product.picture}"/>

								</p>
								<p>
									<label for="name">产品名称：</label> <input class="inputstyle"
										type="text" name="service_product.name"
										id="service_product.name" value="${service_product.name }" disabled="disabled"/>

								</p>
								<p>
									<label for="email">产品类别：</label>
										<input type="radio" name="service_product.type.id" id="service_product.type.id" value="1"
										 <c:if test="${service_product.type.id==1}">checked="checked"</c:if>  disabled="disabled"/>实体产品
										<input type="radio" name="service_product.type.id" id="service_product.type.id" value="2"
										 <c:if test="${service_product.type.id==2}">checked="checked"</c:if>  disabled="disabled"/>产品产品
										<input type="radio" name="service_product.type.id" id="service_product.type.id" value="3" 
										<c:if test="${service_product.type.id==3}">checked="checked"</c:if>  disabled="disabled"/>租赁产品

								</p>

								<p>
									<label for="email">产品价格：</label> <input type="text"
										class="inputstyle" name="service_product.price"
										id="service_product.price" value="${service_product.price}"  disabled="disabled"/> &nbsp;元

								</p>
								<p>
									<label style="float: left">产品详情：<br /> <br /> <br />
										<br /> <br /> <b style="font-size: 12px; color: #0091e6">300字以内</b></label>
									<textarea name="service_product.intro" id="service_product.intro" disabled="disabled">${service_product.intro } </textarea>
								</p>

								<p>
									<input type="submit" value="修改" />&nbsp; &nbsp;&nbsp; &nbsp;<input type="button"
										value="返回" onclick="history.go(-1)">
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

