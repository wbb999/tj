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
}

body,div,h1,ul,a,li,iframe,center,form {
	margin: 0px;
	padding: 0px;
}
/*--events--*/
#divrent {
	width: 66%;
	height: 500px;
	background-color: #6BADF5;
	margin-bottom: 15px;
	margin-right: 0.666%;
	border: 1px #ccc solid;
	float: left;
}

#rentitem {
	width: 49.5%;
	height: 44.1%;
	margin: 2px;
	float: left;
}

#divser {
	width: 66%;
	height: 500px;
	background-color: #FF51FF;
	margin-bottom: 15px;
	margin-right: 0.666%;
	border: 1px #ccc solid;
	float: left;
}

#divpro {
	width: 32.5%;
	height: 1019px;
	border: 1px #ccc solid;
	background-color: #8B14FE;
	margin-bottom: 15px;
	float: right;
}

#proitem {
	width: 99%;
	height: 23.68%;
	margin: 2px;
}

#divproduction {
	width: 32.8%;
	height: 100%;
	background-color: #EEE;
	margin-right: 0.33%;
	float: left;
	border-left: 1px #ccc solid;
	border-right: 1px #ccc solid;
}

.event-grid {
	background-color: #EEE;
	width: 32.6682%;
	height: 442px;
	margin-bottom: 50px;
	margin-right: 0.666%;
	padding: 0;
	float: left;
	position: relative;
}

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

#troeltable {
	width: 80%;
}

#troeltablehead {
	border-bottom: 1px #ccc dashed;
}

#intable {
	width: 90%;
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
</style>
<script type="text/javascript">
	
</script>
</head>
<body>
	<h4 style="float:left">站内搜索-->主营产品搜索</h4>
	<input type="button" value="返回" onclick="history.go(-1)" style="float:right;margin-right:45px;margin-top:15px;border:none;border-bottom-color: #fff;color:blue"><br/><br/>
		<center>
			<hr />
			<table id="troeltable">
				<tbody>
					<c:if test="${service_products.size()==0}">  
						<tr>
							<td style="text-align: center">对不起！未查询到信息</td>
						</tr>
					</c:if>
					<s:iterator value="#request.service_products" id="exa" status="num">
						<tr style="height: 100px;">
							<td width="40px">
							</td>
							<td width="130px"><img src="${exa.picture }"
								class="imgstyle"></td>
							<td><a
								href="mainpage_singleservice_product?service_product.id=${exa.id}">【${exa.name}】<s:if
										test="%{null!=#exa.intro&&#exa.intro.length()>20}">
										<s:property
											value="%{#exa.intro.substring(0,20)}" />……</s:if> <s:else>
										<s:property value="%{#exa.intro}" default="-" />
									</s:else></a><span style="font-weight: bold; color: orange;"> ￥
									${exa.price}</span></td>
							<td><a href="mainpage_singleservice_product?service_product.id=${exa.id}">查看详情</a></td>
						</tr>
					</s:iterator>
				</tbody>
			</table>
			<hr />
		</center>
</body>
</html>