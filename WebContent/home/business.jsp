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
	margin-left:10px;
	text-decoration: none;
	font-size: 12px;
	color:#2F3338;
}

.link7:HOVER {
	color: blue;
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
	function add(proid) {
		var tag = "tag" + proid;
		var span = document.getElementById(tag);
		var xmlhttp;
		try {
			// code for IE7+, Firefox, Chrome, Opera, Safari
			xmlhttp = new XMLHttpRequest();
		} catch (e) {
			// code for IE6, IE5
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
		xmlhttp.open("POST", "trolleyAjax_add?pro.id=" + proid + "", true);
		/* 	xmlhttp.setRequestHeader("Content-type",
					"application/x-www-form-urlencoded"); */
		xmlhttp.send(null);
		xmlhttp.onreadystatechange = function() {
			if (xmlhttp.readyState < 4) { // 正在交互
				span.style.visibility ="visible";
			}
			if (xmlhttp.readyState == 4 && xmlhttp.status == 200) { // 请求成功
				var fl = xmlhttp.responseText;
				if (fl == 1) { // 将服务器返回的数据转换为整数
					span.style.color = "blue";
					span.value = "添加成功";
				}else if(fl == 2){
					span.style.color = "red";
					span.value = "加入失败";
				}else if(fl == 3){
					span.style.color = "red";
					span.value = "请先登录";
				}else{
					span.style.color = "red";
					span.value = "caozuo失败";
				}
			}
		};
		return true;
	}
	function addandgotoorder(proid){
		window.location.href = "usercustom_addandgotoorder?pro.id=" + proid;
		return true;
	}
	function seedetail(proid){
		window.location.href = "mainpage_singleservice_product?service_product.id=" + proid;
		return true;
	}
</script>
</head>
<body>
	<h4>主营业务 --></h4>
	<div style="width: 100%; height: 804px">
	<form action="" method="post" ></form>
		<div id="divrent">
			<h4 class="sizeh4">租赁产品</h4>
			<s:iterator value="#request.rentpro" id="exa" status="num">
				<div id="rentitem">
					<img src="${exa.picture }" title="【${exa.name}】<s:if test="%{null!=#exa.intro&&#exa.intro.length()>100}">
						<s:property value="%{#exa.intro.substring(0,100)}" />……</s:if>
					<s:else>
						<s:property value="%{#exa.intro}" default="-" />
					</s:else>"> <input type="button"
						value="加入购物车" class="sumb" onclick="add('${exa.id}')" /><input type="button" value="直接下单"
						class="sumb" onclick="addandgotoorder('${exa.id}')" /> <input type="button" value="查看详情" class="sumb" onclick="seedetail('${exa.id}')"/>
					<input type="text" id="tag${exa.id}" style="border: 0px;width:74.4px;float:left;background-color: #60b000;text-align: center;color:white;margin-top:20px;visibility: hidden;" value="提示框" disabled="disabled"/>
				</div>
			</s:iterator>
		</div>
		<div id="divpro">
			<h4 class="sizeh4">实体产品</h4>
			<s:iterator value="#request.products" id="exa" status="num">
				<div id="proitem">
					<img src="${exa.picture }"  title="【${exa.name}】<s:if test="%{null!=#exa.intro&&#exa.intro.length()>100}">
						<s:property value="%{#exa.intro.substring(0,100)}" />……</s:if>
					<s:else>
						<s:property value="%{#exa.intro}" default="-" />
					</s:else>"> <input type="button"
						value="加入购物车" class="sumb" onclick="add('${exa.id}')"/><input type="button" value="直接下单"
						class="sumb" /> <input type="button" value="查看详情" class="sumb" onclick="seedetail('${exa.id}')"/>
					<input type="text" id="tag${exa.id}"  style="border: 0px;width:73.5px;float:left;background-color: #60b000;text-align: center;color:white;margin-top:20px; visibility: hidden;" value="提示框" disabled="disabled"/>
				</div>
			</s:iterator>
		</div>
		<div id="divser">
			<h4 class="sizeh4">服务产品</h4>
			<s:iterator value="#request.services" id="exa" status="num">
				<div id="rentitem">
					<img src="${exa.picture }"  title="【${exa.name}】<s:if test="%{null!=#exa.intro&&#exa.intro.length()>100}">
						<s:property value="%{#exa.intro.substring(0,100)}" />……</s:if>
					<s:else>
						<s:property value="%{#exa.intro}" default="-" />
					</s:else>"> <input type="button"
						value="加入购物车" class="sumb" onclick="add('${exa.id}')"/> <input type="button" value="直接下单"
						class="sumb" /> <input type="button" value="查看详情" class="sumb" onclick="seedetail('${exa.id}')" />
					<input type="text" id="tag${exa.id}" style="border: 0px;width:74.4px;float:left;background-color: #60b000;text-align: center;color:white;margin-top:20px;visibility: hidden;" value="提示框" disabled="disabled"/>
				</div>
			</s:iterator>
		</div>
	</div>
	<div style="width: 100%; height: 250px">
		<h4>■优秀作品展示链接</h4>
		<s:iterator value="#request.productions" id="exa" status="num">
			<div id="divproduction" style="border-top: 1px #ccc solid;">
				<img src="${exa.picture}" class="img-responsive" alt="" />
				<h4>${exa.name}</h4>
				<p>
					[
					<fmt:formatDate value='${exa.time }' pattern='yyyy-MM-dd HH:mm:ss' />
					]
					<s:if test="%{null!=#exa.detail&&#exa.detail.length()>100}">
						<s:property value="%{#exa.detail.substring(0,100)}" />……</s:if>
					<s:else>
						<s:property value="%{#exa.detail}" default="-" />
					</s:else>
					<<a href="mainpage_singleproduction?production.id=${exa.id}" class="link">点击查看详情</a>>
				</p>
			</div>
		</s:iterator>
	</div>
	<div style="width: 100%; height: 125px">
		<div id="divproduction" style="border-bottom: 1px #ccc solid;">
			<s:iterator value="#request.productionslink" id="exa" status="num">
				<a href="mainpage_singleproduction?production.id=${exa.id}" class="link7" title="点击查看详情">■[${exa.name}]<s:if test="%{null!=#exa.detail&&#exa.detail.length()>20}">
						<s:property value="%{#exa.detail.substring(0,20)}" />……</s:if>
					<s:else>
						<s:property value="%{#exa.detail}" default="-" />
					</s:else></a>
				<br />
			</s:iterator>
		</div>
		<div id="divproduction" style="border-bottom: 1px #ccc solid;">
			<s:iterator value="#request.productionslink" id="exa" status="num">
				<a href="mainpage_singleproduction?production.id=${exa.id}" class="link7" title="点击查看详情">■[${exa.name}]<s:if test="%{null!=#exa.detail&&#exa.detail.length()>20}">
						<s:property value="%{#exa.detail.substring(0,20)}" />……</s:if>
					<s:else>
						<s:property value="%{#exa.detail}" default="-" />
					</s:else></a>
				<br />
			</s:iterator>
		</div>
		<div id="divproduction" style="border-bottom: 1px #ccc solid;">
			<s:iterator value="#request.productionslink" id="exa" status="num">
				<a href="mainpage_singleproduction?production.id=${exa.id}" class="link7" title="点击查看详情">■[${exa.name}]<s:if test="%{null!=#exa.detail&&#exa.detail.length()>20}">
						<s:property value="%{#exa.detail.substring(0,20)}" />……</s:if>
					<s:else>
						<s:property value="%{#exa.detail}" default="-" />
					</s:else></a>
				<br />
			</s:iterator>
		</div>
	</div>
</body>
</html>