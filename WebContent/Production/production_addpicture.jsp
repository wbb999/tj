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
						<td class="title">作品图片添加</td>
					</tr>
					<tr height="30px;"></tr>
					<tr>
						<td>
							<p>
								<label for="name">作品名称：</label>${production.name}

							</p>
							<p>
								<label for="email">作品地点：</label>${production.address}

							</p>

							<p>
								<label for="email">作品时间：</label>
								<fmt:formatDate value='${production.time }'
									pattern='yyyy-MM-dd HH:mm:ss' />

							</p>
							<p>
								<label for="email">作品图片：</label>
								<s:form action="production_addpicture" method="post"
									enctype="multipart/form-data">
									<s:file id="myFile" name="myFile" style="margin-left:160px"></s:file>
									<input type="hidden" id="production.id" name="production.id"
										value="${production.id}" />
									<s:submit value="提交" style="margin-left:180px" />
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




