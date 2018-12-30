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
	function checkprice() {
		var r = document.getElementsByName("proids");
		var allprice = 0;
		var checkprice = 0;
		var intamount = 0;
		var doubleprice = 0;
		var amount = "";
		var price = "";
		for ( var i = 0; i < r.length; i++) {
			amount = "amount" + r[i].value;
			intamount = parseInt(document.getElementById(amount).value);
			price = "price" + r[i].value;
			doubleprice = parseInt(document.getElementById(price).value);
			allprice = parseInt(allprice + intamount * doubleprice);
			if (r[i].checked) {
				checkprice = parseInt(checkprice + intamount * doubleprice);
			}
			;
		}
		document.getElementById("allprice").value = allprice;
		document.getElementById("checkprice").value = checkprice;
	}
	function check() {
		checkprice();
		var price = document.getElementById("checkprice").value;
		if (price == 0) {
			alert("请选择要下单的产品！");
			return false;
		} else {
			if (confirm("选中" + price + "元！确定下单吗？")) {
				return true;
			} else {
				return false;
			}
		}
	}
	function del(proid) {
		if (confirm("是否确认删除?")) {
			window.location.href = "usercustom_deltrolley?trolley.id=" + proid;
			return true;
		} else {
			return false;
		}
	}
	function dorwn(proid) {
		var tag = "amount" + proid;
		var span = document.getElementById(tag);
		if (span.value == 1) {
			alert("数量已经最低了哦！");
			return false;
		} else {
			var xmlhttp;
			try {
				// code for IE7+, Firefox, Chrome, Opera, Safari
				xmlhttp = new XMLHttpRequest();
			} catch (e) {
				// code for IE6, IE5
				xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
			}
			xmlhttp.open("POST", "trolleyAjax_down?trolley.id=" + proid + "",
					true);
			/* 	xmlhttp.setRequestHeader("Content-type",
						"application/x-www-form-urlencoded"); */
			xmlhttp.send(null);
			xmlhttp.onreadystatechange = function() {
				if (xmlhttp.readyState < 4) { // 正在交互

				}
				if (xmlhttp.readyState == 4 && xmlhttp.status == 200) { // 请求成功
					var fl = xmlhttp.responseText;
					if (fl == 1) { // 将服务器返回的数据转换为整数
						span.value = parseInt(span.value) - 1;
						checkprice();
					} else if (fl == 2) {
						span.style.color = "red";
						span.value = "jianyi失败";
					} else if (fl == 3) {
						span.style.color = "red";
						span.value = "请先登录";
					} else {
						span.style.color = "red";
						span.value = "caozuo失败";
					}
				}
			};
			return true;
		}
	}
	function plus(proid) {
		var tag = "amount" + proid;
		var span = document.getElementById(tag);
		var xmlhttp;
		try {
			// code for IE7+, Firefox, Chrome, Opera, Safari
			xmlhttp = new XMLHttpRequest();
		} catch (e) {
			// code for IE6, IE5
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
		xmlhttp.open("POST", "trolleyAjax_plus?trolley.id=" + proid + "", true);
		/* 	xmlhttp.setRequestHeader("Content-type",
					"application/x-www-form-urlencoded"); */
		xmlhttp.send(null);
		xmlhttp.onreadystatechange = function() {
			if (xmlhttp.readyState < 4) { // 正在交互

			}
			if (xmlhttp.readyState == 4 && xmlhttp.status == 200) { // 请求成功
				var fl = xmlhttp.responseText;
				if (fl == 1) { // 将服务器返回的数据转换为整数
					span.value = parseInt(span.value) + 1;
					checkprice();
				} else if (fl == 2) {
					span.style.color = "red";
					span.value = "jiayi失败";
				} else if (fl == 3) {
					span.style.color = "red";
					span.value = "请先登录";
				} else {
					span.style.color = "red";
					span.value = "caozuo失败";
				}
			}
		};
		return true;
	}
	$(document).ready(function() {
		$("#proids").click(function() {
			checkprice();//do some thing
		});
	})
</script>
</head>
<body onload="checkprice()">
	<h4>购物车--></h4>
	<a href="usercustom_removetrolley"
		style="margin-right: 128px; float: right; text-decoration: none;">清空购物车</a>
	<br />
	<form action="usercustom_addtoorder" method="post"
		onsubmit="return check()">
		<center>
			<hr />
			<table id="troeltable">
				<tbody>
				<c:if test="${trolleylist.size()==0}">  
						<tr>
							<td style="text-align: center"><a href="mainpage_business" style="text-decoration: none;">购物车里还没有物品呢！点击浏览产品</a></td>
						</tr>
					</c:if>
					<s:iterator value="#request.trolleylist" id="exa" status="num">
						<tr style="height: 100px;">
							<td onclick="checkprice()"><input type="checkbox"
								id="proids" name="proids" checked="checked" value="${exa.id}"
								onchange="checkprice()" onmouseover="checkprice()"
								onclick="checkprice()" /> <input type="hidden"
								id="price${exa.id}" name="price${exa.id}"
								value="${exa.service_product.price}" /></td>
							<td width="130px"><img src="${exa.service_product.picture }"
								class="imgstyle"></td>
							<td><a
								href="mainpage_singleservice_product?service_product.id=${exa.service_product.id}">【${exa.service_product.name}】<s:if
										test="%{null!=#exa.service_product.intro&&#exa.service_product.intro.length()>20}">
										<s:property
											value="%{#exa.service_product.intro.substring(0,20)}" />……</s:if> <s:else>
										<s:property value="%{#exa.service_product.intro}" default="-" />
									</s:else></a><span style="font-weight: bold; color: orange;"> ￥
									${exa.service_product.price}</span></td>
							<td><input type="button" value="-"
								onclick="dorwn('${exa.id}')" /><input type="text"
								value="${exa.amount}" id="amount${exa.id}"
								name="amount${exa.id}"
								style="width: 20px; margin-left: 5px; margin-right: 5px; text-align: center" />
								<input type="button" value="+" style="margin-right: 10px"
								onclick="plus('${exa.id}')" /> <input type="button" value="删除"
								onclick="del('${exa.id}')" /></td>
						</tr>
					</s:iterator>
				</tbody>
			</table>
			<hr />
		</center>
		<b>总价<input type="text" value="${allprice}" id="allprice"
			name="allprice">元
		</b><br /> <br /> <b>选中<input type="text" value="${allprice}"
			id="checkprice" name="checkprice">元
		</b><br /> <br /> <b><input type="submit" value="下单"></b>
	</form>
</body>
</html>