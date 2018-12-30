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
/*register*/
#register {
	width: 90%;
	margin-top: 10px;
	padding: 20px;
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
	background-color: #FFF;
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
	text-align: center;
}
</style>
<script type="text/javascript">
	function del(id) {
		if (confirm("确定删除常见问题吗？")) {
			return true;
		} else {
			return false;
		}
	}
	function check() {
		var key = document.getElementById("key").value;
		if (key == "" || key == " " || key == null) {
			alert("请输入关键字！");
			return false;
		}
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
						<td class="title">历史常见问题信息&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a
							href="Question/question_add.jsp" title="添加常见问题"
							style="font-size: 18px;">添加常见问题</a> <br />
							<form action="nomalquestion_keysearch" method="post"
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
						<td style="color: green">${errowMesg}${activities.size()}</td>
					</tr>
					<tr>
						<td>
							<form action="" method="post" onsubmit="return check()">
								<table width="90%" border="1" align="center" cellpadding="5"
									cellspacing="0" id="register_content">
									<thead>
										<tr style="background-color: #CECECE; height: 35px;">
											<td style="width:30px">序号</td>
											<td style="width:300px">问题</td>
											<td style="width:150px">回答</td>
											<td>有用统计</td>
											<td>操作</td>
										</tr>
									</thead>
									<tbody>
										<c:if test="${questions.size()==0}">
											<tr>
												<td colspan="5">对不起！未查询到问题</td>
											</tr>
										</c:if>
										<s:iterator value="#request.questions" id="exa" status="num">
											<tr>
												<td><s:property value="#num.count" /></td>
												<td style="text-align:left"><s:if test="%{null!=#exa.question&&#exa.question.length()>20}">
														<s:property value="%{#exa.question.substring(0, 20)}" />……</s:if>
													<s:else>
														<s:property value="%{#exa.question}" default="-" />
													</s:else></td>
												<td style="text-align:left"><s:if test="%{null!=#exa.answer&&#exa.answer.length()>10}">
														<s:property value="%{#exa.answer.substring(0, 10)}" />……</s:if>
													<s:else>
														<s:property value="%{#exa.answer}" default="-" />
													</s:else></td>
												<td><s:property value="#exa.account" /></td>
												<td><a
													href="nomalquestion_single?&question.id=<s:property value="#exa.id"/>">查看</a>
													| <a
													href="nomalquestion_toupdate?&question.id=<s:property value="#exa.id"/>">修改</a>
													| <a href="nomalquestion_delete?&question.id=${exa.id}" onclick="return del()">删除</a>
											</tr>
										</s:iterator>
									</tbody>
								</table>
							</form>
						</td>
					</tr>
					<tr>
						<td>
							<div>
								<!-- 分页查询 -->
								<s:set name="page" value="#request.page"></s:set>
								<p
									style="float: left; margin-left: 40px; margin-top: 10px; font-weight: normal">
									每页显示：8/总共 <a>【<s:property value="#page.totalSize" />】
									</a> 条记录！当前页 <a>【<s:property value="#page.pageNow" />】
									</a> /共 <a>【<s:property value="#page.totalPage" />】
									</a> 页！
								</p>
								<c:if test="${questions.size()!=0}">
									<p
										style="float: right; margin-right: 40px; margin-top: 10px; font-weight: normal">
										<s:if test="#page.hasFirst">
											<s:a href="nomalquestion_allnomalquestionByPage?pageNow=1">首页</s:a>
										</s:if>
										<s:if test="#page.hasPre">
											<a
												href="nomalquestion_allnomalquestionByPage?pageNow=<s:property value="#page.pageNow-1"/>">上一页</a>
										</s:if>
										<s:if test="#page.hasNext">
											<a
												href="nomalquestion_allnomalquestionByPage?pageNow=<s:property value="#page.pageNow+1"/>">下一页</a>
										</s:if>
										<s:if test="#page.hasLast">
											<a
												href="nomalquestion_allnomalquestionByPage?pageNow=<s:property value="#page.totalPage"/>">尾页</a>
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