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
<title>常见订单管理</title>
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
	function del() {
		if (confirm("确定删除订单吗？")) {
			return true;
		} else {
			return false;
		}
	}
	function check(){
		var type = document.getElementById("searchtype");
		var typevalue = type.options[type.selectedIndex].value;
		var key = document.getElementById("key").value;
		if(key == "" || key == " " || key == null||key=="请输入关键字"){
			alert("请输入关键字！");
			return false;
		}
		if(typevalue ==1){
			if(isNaN(key)){
				alert("请输入数字！");
				document.getElementById("key").value = "";
				return false;
			}else{
				return true;
			}
		}
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
						<td class="title">订单信息&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <!-- <a
							href="Ordertable/ordertable_add.jsp" title="添加常见订单"
							style="font-size: 18px;">新建订单</a> <br /> -->
							<form action="ordertable_keysearch" method="post"
								onsubmit="return check()">
								<span style="font-size: 12px; font-weight: normal; float: right">查询方式
									<select name="searchtype" id="searchtype" style="border: dotted 1px #ccc;">
										<option value="1">订单号</option>
										<option value="3">订单状态</option>
										<option value="4">用户名</option>
										<option value="2">产品关键字</option>
									</select>
										<input type="text" id="key" name="key" value="${key }"
										style="border: dotted 1px #ccc;"/>
										<input type="submit" value="查询"
										style="border: 1px #ccc solid; padding: 0px;" />
								</span>
							</form>
						</td>
					</tr>
					<tr height="20px;">
						<td style="color: green">${errowMesg}<c:if test="${flag==0}">订单状态更新失败
										</c:if>
							<c:if test="${flag==1}">订单状态更新成功，已邮件通知用户！
										</c:if></td>
					</tr>
					<tr>
						<td>
							<form action="" method="post" onsubmit="return check()">
								<table width="90%" border="1" align="center" cellpadding="5"
									cellspacing="0" id="register_content">
									<thead>
										<tr style="background-color: #CECECE; height: 35px;">
											<td>序号</td>
											<td>订单号</td>
											<td>订单用户</td>
											<td>订单时间</td>
											<td>订单状态</td>
											<td>操作</td>
										</tr>
									</thead>
									<tbody>
										<c:if test="${ordertables.size()==0}">
											<tr>
												<td colspan="6">对不起！未查询到订单</td>
											</tr>
										</c:if>
										<s:iterator value="#request.ordertables" id="exa" status="num">
											<tr>
												<td><s:property value="#num.count" /></td>
												<td><s:property value="#exa.id" /></td>
												<td><s:property value="#exa.user.username" /></td>
												<td><fmt:formatDate value='${exa.time }'
														pattern='yyyy-MM-dd HH:mm:ss' /></td>
												<td><s:property value="#exa.state.name" /></td>
												<td><c:if test="${exa.state.id==1}"><a
													href="ordertable_receorder?&oneorder.id=<s:property value="#exa.id"/>">接单</a></c:if>
													<c:if test="${exa.state.id!=1}">接单</c:if>
													| <c:if test="${exa.state.id==1}"><a
													href="ordertable_unorder?&oneorder.id=<s:property value="#exa.id"/>">取消订单</a></c:if>
													<c:if test="${exa.state.id!=1}">取消订单</c:if>
													| <a
													href="ordertable_single?&oneorder.id=<s:property value="#exa.id"/>">详情</a>
													| <c:if test="${exa.state.id!=1&&exa.state.id!=2}"><a
													href="ordertable_delorder?&oneorder.id=<s:property value="#exa.id"/>" onclick="return del()">删除</a></c:if>
													<c:if test="${exa.state.id==1||exa.state.id==2}">删除</c:if>
													| <c:if test="${exa.state.id==2}"><a
													href="ordertable_downorder?&oneorder.id=<s:property value="#exa.id"/>">结束订单</a></c:if>
													<c:if test="${exa.state.id!=2}">结束订单</c:if>
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
								<c:if test="${ordertables.size()!=0}">
									<p
										style="float: right; margin-right: 40px; margin-top: 10px; font-weight: normal">
										<s:if test="#page.hasFirst">
											<s:a href="ordertable_allordertableByPage?pageNow=1">首页</s:a>
										</s:if>
										<s:if test="#page.hasPre">
											<a
												href="ordertable_allordertableByPage?pageNow=<s:property value="#page.pageNow-1"/>">上一页</a>
										</s:if>
										<s:if test="#page.hasNext">
											<a
												href="ordertable_allordertableByPage?pageNow=<s:property value="#page.pageNow+1"/>">下一页</a>
										</s:if>
										<s:if test="#page.hasLast">
											<a
												href="ordertable_allordertableByPage?pageNow=<s:property value="#page.totalPage"/>">尾页</a>
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