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
<title>Contacts</title>
<style type="text/css">
body {
	font-size: 14px;
	color: #696968;
	width:95%;
	height:620px;
	margin: 10px auto;
	border: 1px solid #ccc;
	border-radius: 1em;
}

.col1,.col2,.cols {
	float: left;
}

.cols {
	width: 190px;
}

.col1 {
	width: 670px;
}

.col2 {
	width: 220px;
}

#page5 #content {
	padding-top: 4px
}

h2 {
	font-size: 40px;
	font-style: normal;
	font-weight: 400;
	line-height: 1.2em;
	padding: 28px 0 11px 0;
	color: #000;
	letter-spacing: -1px
}

h2.under {
	border-bottom: 1px solid #e5e5e5;
}

.pad_left1 {
	padding-left: 50px
}

.main {
	margin: 0 auto;
	width: 940px
}

.wrapper {
	width: 100%;
	overflow: hidden
}

#address span {
	float: left;
	width: 55px
}

#ContactForm {
	margin-top: -4px
}

#ContactForm span {
	width: 109px;
	float: left;
	line-height: 26px
}

#ContactForm .wrapper {
	min-height: 30px
}

#ContactForm .textarea_box {
	min-height: 275px;
	width: 100%;
	padding-bottom: 6px
}

#ContactForm {
	
}

#ContactForm {
	
}

#sumb {
	margin-left: 10px;
	border: 0px; float : right;
	width: 62px;
	font-style: normal;
	font-weight: bold;
	color: #fff;
	height: 32px;
	line-height: 32px;
	background: #60b000;
	text-align: center;
	float: right;
}

#sumb:hover {
	background: #080808;
}

#ContactForm .input {
	width: 269px;
	height: 18px;
	border: 1px solid #e5e5e5;
	padding: 3px 5px;
	color: #696968;
	margin: 0
}

#ContactForm textarea {
	overflow: auto;
	width: 549px;
	height: 288px;
	border: 1px solid #e5e5e5;
	padding: 3px 5px;
	color: #696968;
	font-size: 14px;
	margin: 0
}
</style>
<script type="text/javascript">
	var mailOK = false;
	function check(fieldname, usename, remname, len) {
		if (eval(fieldname.value.length) > len) {
			fieldname.value = (fieldname.value).substring(0, len);
			alert("您输入的超过" + len + "个字符了！");
			fieldisOK = false;
		} else {
			usename.value = eval(fieldname.value.length);
			remname.value = len - usename.value;
			fieldisOK = true;
		}
	}
	function checkMail(str) {
		var strReg = "";
		var r;
		var strText = document.all(str).value;
		//strReg=/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/i;
		strReg = /^\w+((-\w+)|(\.\w+))*\@{1}\w+\.{1}\w{2,4}(\.{0,1}\w{2}){0,1}/ig;
		r = strText.search(strReg);
		if (r == -1) {
			alert("邮箱格式错误!");
			document.all(str).focus();
			mailOK = false;
		} else {
			mailOK = true;
		}
	}

	function checksubmit() {
		var name = document.getElementById("name").value;
		var title = document.getElementById("title").value;
		var content = document.getElementById("content").value;
		if (name.length == 0) {
			alert("请输入姓名！");
			return false;
		} else if (title.length == 0) {
			alert("请输入标题！");
			return false;
		} else if (!mailOK) {
			alert("请检查邮箱格式！");
			return false;
		} else if (content.length == 0) {
			alert("请输入内容！");
			return false;
		} else {
			return true;
		}
	}
</script>
</head>
<body id="page5">
	<div class="body3">
		<div class="main">
			<!-- content部分 -->
			<div id="content">
				<div class="wrapper">
					<div class="col1">
						<h2 class="under">Contacts Us</h2>
						<h3 style="color: blue">
							<c:if test="${flag==0}">发送失败!
										</c:if>
							<c:if test="${flag==1}">发送成功!
										</c:if>
						</h3>
						<form action="validate_sendEmail" id="ContactForm" method="post"
							onsubmit="return checksubmit()">
							<div>
								<div class="wrapper">
									<span>姓 名:</span> <input type="text" class="input" name="name"
										id="name">
								</div>
								<div class="wrapper">
									<span>标题:</span><input type="text" class="input" id="title"
										name="title"> &nbsp; &nbsp; <font color="#7F7F7F">
										[信息内容最多不得超过254个字符]</font>
								</div>
								<div class="wrapper">
									<span>联系邮箱：</span><input type="text" class="input" id="mail"
										name="mail" onchange="checkMail('mail')"> &nbsp;
									&nbsp; <font color="#7F7F7F"> 已用：<input type="text"
										name="ContentUse" value="0" size="4" disabled
										style="text-align: center; border: 0;"> 个&nbsp;&nbsp;
										剩余：<input type="text" name="ContentRem" value="254" size="4"
										disabled style="text-align: center; border: 0;"> 个
									</font>
								</div>
								<div class="textarea_box">
									<span>系统留言:</span>
									<textarea name="content" cols="75" rows="10" id="content"
										onkeydown="check(content,ContentUse,ContentRem,254)"
										onkeyup="check(content,ContentUse,ContentRem,254)"
										onchange="check(content,ContentUse,ContentRem,254)"></textarea>
								</div>
								<input type="submit" id="sumb" value="发送"> <input
									type="reset" id="sumb" value="清空">
							</div>
						</form>
					</div>
					<div class="col2 pad_left1">
						<h2 class="under">联系我们</h2>
						<div id="address">
							<span>国家<br> 城市:<br> 电话:<br> E-mail:
							</span> 中国<br> 南京<br> +01-182********<br> <a
								href="mailto:" class="color2">test_nju_edu2@163.com</a>
						</div>
						<div style="margin-bottom:0px;">
						<span style="float:left">微信：</span><img src="images/wechat.png" width="150px" height="150px"/>
						</div>
						<h2 class="under" style="margin-top:0px;">Other Introduction</h2>
						<p>公司地址：南京市********道8号</p>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>