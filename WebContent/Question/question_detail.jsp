<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/Common/BasePath.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>创建活动</title>
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
						<td class="title">常见问题解答详细查看</td>
					</tr>
					<tr height="30px;"></tr>
					<tr>
						<td>
							<form action="nomalquestion_toupdate" method="post">
								<input type="hidden" id="question.id" name="question.id" value="${ question.id}"/>
								<input type="hidden" id="question.account" name="question.account" value="${question.account }"/>
								<p>
									<label style="float: left">问题：</label>
									<textarea name="question.question" id="question.question" disabled="disabled">${question.question } </textarea>
								</p>
								<p>
									<label style="float: left">回答：</label>
									<textarea name="question.answer" id="question.answer" disabled="disabled">${question.answer } </textarea>
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

