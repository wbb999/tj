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
<title>天津博宇鸿雁文化传媒有限公司</title>
<style type="text/css">
body {
	width: 1366px;;
}

body,div,h1,ul,a,li,center {
	margin: 0px;
	padding: 0px;
}
*{margin:0;padding:0;list-style:none;}
/*主导航菜单*/
#nav-menu {
	width: 100%;
	height: 30px;
	color: #ccc;
	outline: 0;
	font-family: Microsoft YaHei;
	background: -webkit-gradient(linear, left top, left 25, from(#FFFFFF),
		color-stop(4%, #EEEEEE), to(#FFFFFF));
	background: -moz-linear-gradient(bottom, #FFFFFF, #EEEEEE 1px, #FFFFFF 25px);
	box-shadow: rgba(0, 0, 0, 0.05) 0px 0px 8px;
	-moz-box-shadow: rgba(0, 0, 0, 0.05) 0px 0px 8px;
	-webkit-box-shadow: rgba(0, 0, 0, 0.05) 0px 0px 8px;
}

#nav-menu .container{position:relative;overflow:visible; z-index:99;}
#nav-menu .menu li{position:relative;float:left;padding:0 8px 0 5px;display:inline;font-size:14px;}
#nav-menu .menu li h3{font-weight:normal;display:inline-block;float:left;}
#nav-menu .menu li a.xialaguang{margin-top:10px;display:inline-block;color:#ccc;height:30px;text-decoration:none;font-size:14px;width:100px;text-align:center;}
#nav-menu .menu li a.selected,#nav-menu .menu li a.navhover,#nav-menu .menu li a.xialaguang:hover{color:#1a529c;}
#nav-menu .menu li a.navhover, #nav-menu .menu li a.xialaguang:hover{background-color:#FFF;}
#nav-menu .menu li a.xialaguang span{height:30px;line-height:25px;display:inline-block;font-weight:bold;}
#nav-menu .menu li a.selected span,#nav-menu .menu li a.navhover span,#nav-menu .menu li a.xialaguang:hover span{cursor:pointer;}
#nav-menu .menu li a.navhover span, #nav-menu .menu li a.xialaguang:hover span{width:100px;}
#nav-menu .menu ul.children{display:none;position:absolute;top:40px;left:3px;width:100px;background:#FFF;border:2px solid #1a529c;border-top:0;line-height:normal;}
#nav-menu .menu ul.children li{width:100px;padding:0px;display:inline-block;font-size:12px;border-top:1px solid #ccc;}
#nav-menu .menu ul.children li h3{display:block; width:100%;}
#nav-menu .menu ul.children li a{width:100%;height:16px;line-height:16px;overflow:hidden;padding:3px 0;display:block;color:#1a529c;text-align:center;
text-decoration:none; font-size:12px;}
#nav-menu .menu ul.children li a:hover{background-color:#eee;text-decoration:none;}



#bg_bg {
	width: 1270px;
	height: 20px;
	border-left: 1px #eee solid;
	border-right: 1px #eee solid;
	outline: 0;
	font-family: Microsoft YaHei;
	background: -webkit-gradient(linear, left top, left 25, from(#FFFFFF),
		color-stop(4%, #EEEEEE), to(#FFFFFF));
	background: -moz-linear-gradient(bottom, #FFFFFF, #EEEEEE 1px, #FFFFFF 20px);
	box-shadow: rgba(0, 0, 0, 0.1) 0px 0px 2px;
}
</style>

</head>
<body>

	<div id="nav-menu">
		<ul class="menu">
			<li class="stmenu">
				<h3><a href="#" class="xialaguang"><span>网页教程</span></a></h3>    
				<ul class="children">    
					<li><h3><a href="#"><span>基础知识</span></a></h3></li>
					<li><h3><a href="#"><span>优秀教程</span></a></h3></li>
					<li><h3><a href="#"><span>文字效果</span></a></h3></li>
					<li><h3><a href="#"><span>按钮制作</span></a></h3></li>
				</ul>
			</li>
		</ul>
	</div>	
<div id="top_bg">
		<div class="top_left">
			<ul class="menu">
				<li><a href="#">帮助</a></li>
				<li>|</li>
				<li><a href="home/contacts.jsp" target="main">联系我们</a></li>
				<li>|</li>
				<li><a href="usercustom_trolley" target="main">购物车</a></li>
			</ul>
		</div>
		<div class="nav-menu">
			<ul class="menu">
				<li>服务热线:029-83658860</li>
				<li>|</li>
				<li class="stmenu">
					<h3>
						<a href="#" class="xialaguang"><span>网页教程</span></a>
					</h3>
					<ul class="children">
						<li><h3>
								<a href="#"><span>基础知识</span></a>
							</h3></li>
						<li><h3>
								<a href="#"><span>优秀教程</span></a>
							</h3></li>
						<li><h3>
								<a href="#"><span>文字效果</span></a>
							</h3></li>
						<li><h3>
								<a href="#"><span>按钮制作</span></a>
							</h3></li>
					</ul>
				</li>
				<%-- <li><c:if test="${oneuser.username==null}">
						<a href="User/login.jsp">登陆</a>
					</c:if> <c:if test="${oneuser.username!=null}">
						<a href="usercustom_cusmassage" target="main">${oneuser.username}</a>
					</c:if></li> --%>
			</ul>
		</div>
	</div>
	<div id="header_bar">
		<div style="height: 30px; width: 100%"></div>
		<div id="header-bottom">
			<h1>
				<a href="indextest.jsp" id="logoa">天津博宇鸿雁文化传媒有限公司</a>
			</h1>
			<div>
				<ul>
					<li><a href="index.jsp">主页</a></li>
					<li><a href="mainpage_business" target="main">主营业务</a></li>
					<li><a href="mainpage_homemain" target="main">作品展示</a></li>
					<li><a href="search_question" target="main">常见问题</a></li>
					<li><a href="home/contacts.jsp" target="main">联系我们</a></li>
				</ul>
			</div>
		</div>
		<form action="search_search" method="post" target="main">
			<span style="margin-left: 48px">查询域</span> <select name="searchtype"
				id="searchtype">
				<option value="1">服务/产品</option>
				<option value="2">常见问题</option>
				<option value="3">历史活动</option>
			</select> <input type="text" id="key" name="key" value="请输入关键字"
				onfocus="clean(this)" /> <input type="submit" value="查找" />
		</form>
	</div>


	<center>
		<div id="bg_bg"></div>
		<iframe name="main" src="mainpage_homemain" scrolling="no"
			width="1270px" height="1500px" frameborder="0"> </iframe>



		<div id="Copyright">
			Copyright 2015.All Rights Reserved 版权所有 天津博宇鸿雁文化传媒有限公司<br />
			地址：天津市武清区徐官屯街江源道4号 &nbsp; 联系电话：029-83658860 &nbsp; 闫经理：18602943481
			&nbsp; 李经理：13630257906 &nbsp; 传真：029-83658860
		</div>
	</center>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript">
$('#nav-menu .menu > li').hover(function(){
	$(this).find('.children').animate({ opacity:'show', height:'show' },200);
	$(this).find('.xialaguang').addClass('navhover');
}, function() {
	$('.children').stop(true,true).hide();
	$('.xialaguang').removeClass('navhover');
});
</script>
</body>
</html>