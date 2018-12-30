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
<title>所有用户信息</title>
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
		if (confirm("确定删除用户吗？")) {
			location.href="user_deleteUser?&oneuser.id="+id;
		} else {
			return false;
		}
	}
	function adduser(usernamber){//之前限制管理员数量用的，现在不用了
		alert("userList"+usernamber);
		if(usernamber<13){
			alert("keyi");
			window.location.href="User/register.jsp";
		}else{
			alert("添加的管理员已达到上限！");
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
						<td class="title">关键字查询用户信息&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a href="user_toadduser"
							title="添加用户" style="font-size: 18px;">添加用户</a>
							<form action="user_keysearch" method="post"
								onsubmit="return check()">
								<span style="font-size: 12px; font-weight: normal; float: right">请输入关键字
									<input type="text" id="key" name="key" value="${key }"
									style="border: dotted 1px #ccc;" /> <input type="submit"
									value="查询" style="border: 1px #ccc solid; padding: 0px;" />
								</span>
							</form> <br /> <!--<span style="font-size: 8px; font-weight: normal">(最多添加12位用户)</span>-->
						</td>
					</tr>
					<tr height="20px;">
						<td style="color: green">${errowMesg}</td>

					</tr>
					<tr>
						<td>
							<form action="" method="post" onsubmit="return check()">
								<table width="95%" border="1" align="center" cellpadding="5"
									cellspacing="0" id="register_content">
									<thead>
										<tr style="background-color: #CECECE; height: 35px;">
											<td width="20px">序号</td>
											<td>登录名</td>
											<td>真实姓名</td>
											<td>类别</td>
											<td>联系电话</td>
											<td>联系邮箱</td>
											<td width="40px">用户状态</td>
											<td>操作</td>
										</tr>
									</thead>
									<tbody>
									<c:if test="${userList.size()==0}">
											<tr>
												<td colspan="8">对不起！未查询到用户</td>
											</tr>
										</c:if>
										<s:iterator value="#request.userList" id="u" status="num">
											<tr>
												<td><s:property value="#num.count" /></td>
												<td><s:property value="#u.username" /></td>
												<td><s:property value="#u.realName" /></td>
												<td><s:property value="#u.type.name" /></td>
												<td><s:property value="#u.phone" /></td>
												<td><s:property value="#u.mail" /></td>
												<td><c:if test="${u.state== 0}">
														<span style="color: green">正常</span>
													</c:if> <c:if test="${u.state ==1}">
														<span style="color: red">禁用</span>
													</c:if></td>
												<td><c:if test="${u.getType().getId()==1}">
														<span style="color: #ccc">重置密码 | 禁用 | 删除 </span>
													</c:if> <c:if test="${u.getType().getId()!=1}">
														<a
															href="user_resetpassword?&oneuser.id=<s:property value="#u.id"/>"
															title="把密码重置为6个1">重置密码 </a>|
													<c:if test="${u.state== 0}">
															<a
																href="user_disableUser?&oneuser.id=<s:property value="#u.id"/>">禁用</a>
														</c:if>
														<c:if test="${u.state ==1}">
															<a
																href="user_disableUser?&oneuser.id=<s:property value="#u.id"/>"
																style="color: green">恢复</a>
														</c:if>|<a onClick="del(<s:property value='#u.id'/>)"> 删除</a>
													</c:if></td>
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
											<a href="user_keysearch?pageNow=1&key=${key}">首页</a>
										</s:if>
										<s:if test="#page.hasPre">
											<a
												href="user_keysearch?pageNow=<s:property value="#page.pageNow-1"/>&key=${key}">上一页</a>
										</s:if>
										<s:if test="#page.hasNext">
											<a
												href="user_keysearch?pageNow=<s:property value="#page.pageNow+1"/>&key=${key}">下一页</a>
										</s:if>
										<s:if test="#page.hasLast">
											<a
												href="user_keysearch?pageNow=<s:property value="#page.totalPage"/>&key=${key}">尾页</a>
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