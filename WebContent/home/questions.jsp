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
<title>常见问题管理</title>
<style type="text/css">
body {
	background-image: url(images/zuoshang.png);
	background-repeat: no-repeat;
}
/*register*/
#register {
	width: 90%;
	margin-top: 10px;
	padding: 20px;
	color: #666;
}

#register .title {
	font-size: 16px;
	font-weight: bold;
	text-align: left;
	color: #09c;
	border-bottom: dotted 1px #ccc;
	line-height: 30px;
}

#register_content {
	font-size: 14px;
	color: #666;
	margin-top: 10px;
}

#container {
	width: 950px;
	height: 540px;
	font-size: 14px;
}

a {
	text-decoration: none;
}

tr {
	margin: 0px;
	padding: 0px;
}

td {
	text-align: left;
}
</style>
<script type="text/javascript">
	function check() {
		var key = document.getElementById("key").value;
		if (key == "" || key == " " || key == null) {
			alert("请输入关键字！");
			return false;
		}
		return true;
	}
	function zan(id) {
		var tag = "tag" + id;
		var span = document.getElementById(tag);
		if (span.value == "+1") {
			alert("谢谢您的支持，请勿重复点赞！");
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
		xmlhttp.open("POST", "loginAjax_zan?questionid=" + id + "", true);
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
					span.value = "+1";
					var account = "account" + id;
					document.getElementById(account).value = parseInt(document
							.getElementById(account).value) + 1;

				} else {
					span.style.color = "red";
					span.value = "点赞失败";
				}
			}
		};
		return true;
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
						<td class="title">常见问题解答<br />
							<form action="search_search?searchtype=2" method="post"
								onsubmit="return check()">
								<span style="font-size: 12px; font-weight: normal; float: right">请输入关键字
									<input type="text" id="key" name="key" value="${key }"
									style="border: dotted 1px #ccc;" /> <input type="submit"
									value="查询" style="border: 1px #ccc solid; padding: 0px;" />
								</span>
							</form>
						</td>
					</tr>
					<tr height="20px;">
						<td></td>
					</tr>
					<tr>
						<td>
							<table width="90%" border="0px" align="center" cellpadding="0"
								cellspacing="0" id="register_content">
								<tbody>
									<s:iterator value="#request.questions" id="exa" status="num">
										<tr>
											<td>问： <s:property value="#num.count" />、<s:property
													value="#exa.question" /> &nbsp; <img src="images/zan.png"
												onclick="zan('${exa.id}')"> <input type="text"
												id="account${exa.id}"
												value="<s:property value="#exa.account" />"
												style="width: 20px; border: 0px;"> 有用 <input
												type="text" id="tag${exa.id}"
												style="width: 20px; border: 0px;" />
											</td>
										</tr>
										<tr style="height: 20px">
											<td></td>
										</tr>
										<tr>
											<td>答：<s:property value="#exa.answer" /></td>
										</tr>
										<tr style="height: 30px">
											<td></td>
										</tr>
									</s:iterator>
								</tbody>
							</table>
						</td>
					</tr>
					<tr>
						<td>
							<div>
								<!-- 分页查询 -->
								<s:set name="page" value="#request.page"></s:set>
								<p
									style="float: left; margin-left: 40px; margin-top: 10px; font-weight: normal">
									每页显示：8/总共 【
									<s:property value="#page.totalSize" />
									】 条记录！当前页 【
									<s:property value="#page.pageNow" />
									】 /共 【
									<s:property value="#page.totalPage" />
									】 页！
								</p>
								<c:if test="${questions.size()!=0}">
									<p
										style="float: right; margin-right: 40px; margin-top: 10px; font-weight: normal">
										<s:if test="#page.hasFirst">
											<s:a href="search_question?pageNow=1">首页</s:a>
										</s:if>
										<s:if test="#page.hasPre">
											<a
												href="search_question?pageNow=<s:property value="#page.pageNow-1"/>">上一页</a>
										</s:if>
										<s:if test="#page.hasNext">
											<a
												href="search_question?pageNow=<s:property value="#page.pageNow+1"/>">下一页</a>
										</s:if>
										<s:if test="#page.hasLast">
											<a
												href="search_question?pageNow=<s:property value="#page.totalPage"/>">尾页</a>
										</s:if>
									</p>
								</c:if>
							</div>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</body>
</html>