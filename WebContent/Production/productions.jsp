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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>作品管理</title>
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
		if (confirm("确定删除作品吗？")) {
			location.href = "production_delete?&production.id=" + id;
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
						<td class="title">精彩作品信息&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a
							href="production_toadd"
							style="font-size: 18px">新增作品</a> <br />
							<form action="production_keysearch" method="post"
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
								<table width="98%" border="1" align="center" cellpadding="5"
									cellspacing="0" id="register_content">
									<thead>
										<tr style="background-color: #CECECE; height: 35px;">
											<td width="15px;">序号</td>
											<td>作品名</td><!-- width="210px;" -->
											<td>作品时间</td>
											<td>作品地点</td>
											<td>操作</td>
										</tr>
									</thead>
									<tbody>
										<c:if test="${productions.size()==0}">
											<tr>
												<td colspan="6">对不起！未添加任何作品</td>
											</tr>
										</c:if>
										<s:iterator value="#request.productions" id="exa" status="num">
											<tr>
												<td><s:property value="#num.count" /></td>
												<td><s:property value="#exa.name" /></td>
												<td><fmt:formatDate value='${exa.time }'
														pattern='yyyy-MM-dd HH:mm:ss' /></td>
												<td><s:property value="#exa.address" /></td>
												<td><a
													href="production_single?&production.id=<s:property value="#exa.id"/>">查看</a>
													| <a
													href="production_toaddpicture?&production.id=<s:property value="#exa.id"/>" title="添加图片/更新图片">图片</a>
													| <a
													href="production_toupdate?&production.id=<s:property value="#exa.id"/>">更新</a>
													| <a onclick="del('${exa.id}')">删除</a>
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
								<c:if test="${activities.size()!=0}">
									<p
										style="float: right; margin-right: 40px; margin-top: 10px; font-weight: normal">
										<s:if test="#page.hasFirst">
											<s:a href="production_allProductionByPage?pageNow=1">首页</s:a>
										</s:if>
										<s:if test="#page.hasPre">
											<a
												href="production_allProductionByPage?pageNow=<s:property value="#page.pageNow-1"/>">上一页</a>
										</s:if>
										<s:if test="#page.hasNext">
											<a
												href="production_allProductionByPage?pageNow=<s:property value="#page.pageNow+1"/>">下一页</a>
										</s:if>
										<s:if test="#page.hasLast">
											<a
												href="production_allProductionByPage?pageNow=<s:property value="#page.totalPage"/>">尾页</a>
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