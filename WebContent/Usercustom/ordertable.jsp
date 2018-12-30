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
<title>Insert title here</title>
<style type="text/css">
body {
	width: 1270px;
	color:#7F7F7F;
}

body,div,h1,ul,a,li,iframe,center,form {
	margin: 0px;
	padding: 0px;
}
/*--events--*/

h4 {
	color: #00B198;
	text-align: left;
	font-size: 1.2em;
	font-weight: 400;
	text-transform: uppercase;
	Margin: 0.5em 0 0.5em 0.5em;
}

.sizeh4 {
	color: white;
	text-align: left;
	font-size: 1.2em;
	font-weight: 400;
	text-transform: uppercase;
	Margin: 0.5em 0 0.5em 0.5em;
}

p {
	text-align: left;
	line-height: 1.8em;
	font-size: 0.9em;
	color: #2F3338;
	padding: 0 0.5em 0.5em;
}

img {
	width: 81.5%;
	height: 100%;
	float: left;
}

.img-responsive {
	width: 50%;
	height: 60%;
	margin-right: 10px;
	float: left;
}

.sumb {
	float: left;
	width: 18.5%;
	margin-bottom: 3px;
	border: 0px;
	float: left;
	font-style: normal;
	font-weight: bold;
	color: #fff;
	background: #60b000;
	text-align: center;
}

.sumb:hover {
	background: #080808;
}

.link {
	text-decoration: none;
}

.link7 {
	margin-left: 10px;
	text-decoration: none;
	font-size: 12px;
	color: #2F3338;
}

.link7:HOVER {
	color: blue;
}

.troeltable {
	width: 85%;
}

.tbodystyle{
	display: block;
	overflow-y: scroll;
}

#troeltablehead {
	border-bottom: 1px #ccc dashed;
	width: 1000px;
	
}

#intable {
	width: 96%;
}

#imgstyle {
	width: 100%;
	height: 100%;
}

hr {
	width: 80%;
	font-weight: bold;
}

b {
	float: right;
	margin-right: 10%;
}

b input {
	width: 45px;
	margin-left: 5px;
	margin-right: 5px;
	text-align: center;
}
.ordertitle{
	height:40px;
	border-bottom: 1px #ccc dashed;
	border-top: 1px black dashed;
	line-height: 40px;
}
</style>
<script type="text/javascript">
	function clean(it) {
		it.value = "";
	}
	function check(){
		var type = document.getElementById("searchtype");
		var typevalue = type.options[type.selectedIndex].value;
		var key = document.getElementById("key").value;
		if(key==""||key=="请输入关键字"){
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
	<h4>历史订单--></h4>
	<form action="usercustom_searchorder" method="post" onsubmit="return check()" style="float:right;margin-right:45px;margin-bottom: 10px;">
			<span style="margin-left: 48px">查询方式</span> <select name="searchtype"
				id="searchtype">
				<option value="1">订单号</option>
				<option value="2">关键字</option>
			</select> <input type="text" id="key" name="key" value="请输入关键字"
				onfocus="clean(this)" /> <input type="submit" value="查找" />
	</form>
	<form action="" method="post">
		<center>
			<table class="troeltable" cellspacing="0" border="0">
				<tbody class="tbodystyle" <c:if test="${ordertablelist.size()>5}">style="height:1400px"</c:if>>
				<c:if test="${ordertablelist.size()==0}">  
						<tr>
							<td style="text-align: center"><a href="mainpage_business" style="text-decoration: none;">还没有订单呢！点击浏览产品</a></td>
						</tr>
					</c:if>
					<s:iterator value="#request.ordertablelist" id="exa" status="num">
						<tr class="ordertitle">
							<td class="ordertitle">订单号：${exa.id}</td>
							<td class="ordertitle">订单时间：<fmt:formatDate value='${exa.time}' pattern='yyyy-MM-dd HH:mm:ss'/></td>
							<td class="ordertitle">订单状态：${exa.state.name }</td>
							<td class="ordertitle">操作：&nbsp;<c:if test="${exa.state.id==1}"><a href="usercustom_unorder?oneorder.id=${exa.id}" style="text-decoration: none;">取消订单</a></c:if>
							<c:if test="${exa.state.id!=1}"><font style="color:#ccc;">取消订单</font></c:if>&nbsp;
							<c:if test="${exa.state.id==1||exa.state.id==2}"><font style="color:#ccc;">删除订单</font></c:if>
							<c:if test="${exa.state.id!=1&&exa.state.id!=2}"><a href="usercustom_delorder?oneorder.id=${exa.id}" style="text-decoration: none;">删除订单</a></c:if></td>
						</tr>
						<tr>
							<td colspan="4">
								<table id="intable">
									<tbody>
										<s:iterator value="#exa.orderlistSet" id="exae"
											status="num">
											<tr style="height: 100px;">
												<td width="40px"></td>
												<td width="130px"><img
													src="${exae.service_product.picture }" class="imgstyle"></td>
												<td width="730px"><a href="mainpage_singleservice_product?service_product.id=${exae.service_product.id}">【${exae.service_product.name}】<s:if
															test="%{null!=#exae.service_product.intro&&#exae.service_product.intro.length()>20}">
															<s:property
																value="%{#exae.service_product.intro.substring(0,20)}" />……</s:if>
														<s:else>
															<s:property value="%{#exae.service_product.intro}"
																default="-" />
														</s:else></a><span style="font-weight: bold; color: orange;"> 单价：￥
														${exae.service_product.price}</span></td>
												<td width="130px">数量：${exae.amount}</td>
											</tr>
										</s:iterator>
									</tbody>
								</table>
							</td>
						</tr>
						<tr height="20px">
							<td colspan="4"></td>
						</tr>
					</s:iterator>
					<tr height="2px">
							<td colspan="4" style="border-bottom:1px black dashed; "></td>
					</tr>
				</tbody>
			</table>
		</center>
	</form>
</body>
</html>