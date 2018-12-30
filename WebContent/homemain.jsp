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
	width: 1275px;
}

body,div,h1,ul,a,li,iframe,center,form {
	margin: 0px;
	padding: 0px;
}
/*--events--*/
.event-section {
	padding:0;
	text-align: center;
}

.event-grids {
	margin-top: 2px;
}

.event-section h3 {
	font-family: 'Niconne', cursive;
	color: #00B198;
	font-size: 4em;
	font-weight: 400;
	text-align: center;
}

.event-grid {
	background-color: #EEE;
	width: 32.6682%;
	height:437px;
	margin-bottom:50px;
	margin-right: 0.666%;
	padding: 0;
	float: left;
	position: relative;
}

.event-grid.lost {
	margin-right: 0%;
}

.event-grid h4 {
	color: #00B198;
	text-align: center;
	font-size: 1.2em;
	font-weight: 400;
	text-transform: uppercase;
	Margin: 1.5em 0 0.5em 0;
}
h4 {
	color: #00B198;
	text-align:left;
	font-size: 1.2em;
	font-weight: 400;
	text-transform: uppercase;
	Margin: 0.5em 0 0.5em 0.5em;
}

.event-grid p {
	text-align: left;
	line-height: 1.8em;
	font-size: 0.9em;
	color: #2F3338;
	padding: 0 2em 2em;
}

.pink .event-date {
	background: #FA4B74;
}

.event-date {
	position: absolute;
	left: 39%;
	bottom: -40px;
	width: 70px;
	height: 25px;
	background: #00B198;
	color: #fff;
	font-size: 0.8em;
	border-radius: 50%;
	-webkit-border-radius: 50%;
	-o-border-radius: 50%;
	-moz-border-radius: 50%;
	-ms-border-radius: 50%;
	text-align: center;
	padding: 16px 16px;
}

.event-year {
	color: #fff;
	display: block;
	text-decoration: none;
}

.img-responsive {
	width: 359px;
	height: 221px;
}
</style>
</head>
<body>
	<h4>作品展示 --></h4>
	<div class="event-section">
		<div class="container">
		<s:iterator value="#request.productions" id="exa" status="num">
			<div class="event-grids">
				<div class="col-md-4 event-grid">
					<img src="${exa.picture}" class="img-responsive" alt="" />
					<h4>${exa.name}</h4>
					<p>
						[
						<fmt:formatDate value='${exa.time }'
							pattern='yyyy-MM-dd HH:mm:ss' />
						]
						<s:if test="%{null!=#exa.detail&&#exa.detail.length()>100}">
														<s:property value="%{#exa.detail.substring(0,100)}" />……</s:if>
													<s:else>
														<s:property value="%{#exa.detail}" default="-" />
													</s:else>
					</p>
					<span class="event-date"><a href="mainpage_singleproduction?production.id=${exa.id}" class="event-year">查看详情</a>点击</span>
				</div>
			</div>
		</s:iterator>
		</div>
	</div>
</body>
</html>