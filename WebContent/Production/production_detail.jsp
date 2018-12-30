<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/Common/BasePath.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>作品详情</title>
<style type="text/css">
#container {
	width: 950px;
	height: 540px;
	background-color: #FFF;
	font-size: 14px;
	color: #666;
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
<script type="text/javascript">
	
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
						<td class="title">作品详情</td>
					</tr>
					<tr height="30px;"></tr>
					<tr>
						<td>
							<form action="production_toupdate" method="post">
								<input type="hidden" id="production.id" name="production.id" value="${production.id}"/>
								<p>
									<label for="name" style="float: left">作品图片：</label><img width="140px" height="100px" src="${production.picture}"/>

								</p>
								<p>
									<label for="name">作品名称：</label>${production.name}

								</p>
								<p>
									<label for="email">作品地点：</label>${production.address}

								</p>

								<p>
									<label for="email">作品时间：</label><fmt:formatDate value='${production.time }' pattern='yyyy-MM-dd HH:mm:ss'/>

								</p>
								<p>
									<label style="float: left">作品详情：<br /> <br /> <br />
										<br /> <br />
									</label>
									<textarea name="production.detail" id="production.detail"
										disabled="disabled">${production.detail }</textarea>
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

