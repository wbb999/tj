<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/Common/BasePath.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>添加产品图片</title>
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
						<td class="title">产品图片添加或更新 <input type="button" value="返回" onclick="history.go(-1)"
								style="margin-left:45px;border:none;color:blue"></td>
					</tr>
					<tr height="30px;"></tr>
					<tr>
						<td>
							<p>
									<label for="name">产品名称：</label> ${service_product.name }

								</p>
								<p>
									<label for="email">产品类别：</label> 
										<input type="radio" name="service_product.type.id" id="service_product.type.id" value="1" <c:if test="${service_product.type.id==1}">checked="checked"</c:if> disabled="disabled"/>实体产品
										<input type="radio" name="service_product.type.id" id="service_product.type.id" value="2" <c:if test="${service_product.type.id==2}">checked="checked"</c:if> disabled="disabled"/>服务产品
										<input type="radio" name="service_product.type.id" id="service_product.type.id" value="3" <c:if test="${service_product.type.id==3}">checked="checked"</c:if> disabled="disabled"/>租赁产品
								</p>

								<p>
									<label for="email">产品价格：</label>${service_product.price}&nbsp;元

								</p>
							<p>
								<label for="email">作品图片：</label><c:if test="${service_product.picture=='//8086'}">未上传</c:if>
								<img width="140px" height="100px" src="${service_product.picture}"/>
								<s:form action="service_product_addpicture" method="post"
									enctype="multipart/form-data">
									<s:file id="myFile" name="myFile" style="margin-left:160px"></s:file>
									<input type="hidden" id="service_product.id" name="service_product.id"
										value="${service_product.id}" />
									<s:submit value="更新图片信息" style="margin-left:180px" />
								</s:form>
							</p>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</body>
</html>




