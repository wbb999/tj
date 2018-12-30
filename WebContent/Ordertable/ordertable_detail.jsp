<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/Common/BasePath.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
	color:#7F7F7F;
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

.ordertitle {
	height: 40px;
	border-bottom: 1px #ccc dashed;
	border-top: 1px black dashed;
	line-height: 40px;
}

.tbodystyle {
	display: block;
	overflow-y: scroll;
}

.imgstyle {
	width: 130px;
	height: 100px;
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
						<td class="title">订单详细查看</td>
					</tr>
					<tr height="30px;"></tr>
					<tr>
						<td>
							<form action="nomalquestion_toupdate" method="post">

								订单中用户的用户名：${oneorder.user.username} <input type="button" value="返回" onclick="history.go(-1)"
								style="margin-left:45px;border:none;color:blue">
									<br/><br/>
								<table width="100%" cellspacing="0" border="0">
									<tr class="ordertitle">
										<td class="ordertitle">订单号：${oneorder.id}</td>
										<td class="ordertitle">订单时间：<fmt:formatDate
												value='${oneorder.time}' pattern='yyyy-MM-dd HH:mm:ss' /></td>
										<td class="ordertitle">订单状态：${oneorder.state.name }</td>
										<td class="ordertitle">操作：&nbsp;<c:if
												test="${oneorder.state.id==1}">
												<a href="ordertable_unorder?&oneorder.id=${oneorder.id}"
													style="text-decoration: none;">取消订单</a>
											</c:if> <c:if test="${oneorder.state.id!=1}">
												<font style="color: #ccc;">取消订单</font>
											</c:if>&nbsp; <c:if
												test="${oneorder.state.id==1||oneorder.state.id==2}">
												<font style="color: #ccc;">删除订单</font>
											</c:if> <c:if test="${oneorder.state.id!=1&&oneorder.state.id!=2}">
												<a href="usercustom_delorder?oneorder.id=${exa.id}"
													style="text-decoration: none;">删除订单</a>
											</c:if></td>
									</tr>
									<tr>
										<td colspan="4">
											<table class="tbodystyle"
												<c:if test="${oneorder.orderlistSet.size()>3}">style="height:300px"</c:if>>
												<tbody>
													<c:if test="${ oneorder.orderlistSet.size()==0}">
														<tr>
															<td style="text-align: center;" colspan="4"><a>订单中没有商品，请添加！</a></td>
														</tr>
													</c:if>
													<c:forEach var="exae" items="${ oneorder.orderlistSet}">
														<tr style="height: 100px;">
															<td width="40px"></td>
															<td width="130px"><img
																src="${exae.service_product.picture }" class="imgstyle"></td>
															<td width="730px"><a
																href="mainpage_singleservice_product?service_product.id=${exae.service_product.id}">【${exae.service_product.name}】</a><span style="font-weight: bold; color: orange;">
																	单价：￥ ${exae.service_product.price}</span></td>
															<td width="130px">数量：${exae.amount}</td>
														</tr>
													</c:forEach>
												</tbody>
											</table>
										</td>
									</tr>
									<tr height="2px">
										<td colspan="4" style="border-bottom: 1px black dashed;"></td>
									</tr>
									</tbody>
								</table>
							</form>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</body>
</html>

