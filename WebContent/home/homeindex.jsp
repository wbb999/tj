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
	width: 100%;
	height: 550px;
	background-color: #6BADF5;
	margin-bottom: 15px;
	margin-right: 0.666%;
}

#divser {
	width: 66%;
	height: 500px;
	color:#666;
	margin-bottom: 15px;
	margin-right: 0.666%;
	border: 1px #ccc solid;
	float: left;
}

#divpro {
	width: 32.5%;
	height: 535px;
	border: 1px #ccc solid;
	margin-bottom: 15px;
	float: right;
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

h4 {
	color: #00B198;
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
#proitem{
	width: 50%;
	height: 31%;
	float: left;
}
#proitem img{
 	width: 100%;
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
#about{
   	width:90%; 
   	margin-top:20px;
   	text-align: left;
}
#title{
	margin-top:90px;
	color:#555;
}
</style>
<script type="text/javascript">
function seedetail(proid){
	window.location.href = "mainpage_singleservice_product?service_product.id=" + proid;
	return true;
}
</script>
</head>
<body>
	<div style="width: 100%; height: 804px">
		<div id="divrent">
			<img src="images/mainindex.jpg" height="100%" width="100%">
		</div>
		<div id="divpro">
			<h4>最新发布</h4>
			<s:iterator value="#request.service_products" id="exa" status="num">
				<div id="proitem">
					<img src="${exa.picture }" onclick="seedetail('${exa.id}')"
						title="【${exa.name}】<s:if test="%{null!=#exa.intro&&#exa.intro.length()>100}">
						<s:property value="%{#exa.intro.substring(0,100)}" />……</s:if>
					<s:else>
						<s:property value="%{#exa.intro}" default="-" />
					</s:else>">
				</div>
			</s:iterator>
		</div>
		<div id="divser">
			<center>
			<h1 style="margin-top:20px">关于我们</h1>
				<div id="about">
					&nbsp; &nbsp;&nbsp;&nbsp;
					&nbsp;天津博宇鸿雁文化传媒有限公司成立于15年01月30日，继承传统文化，发展企业文化，进步视觉传达与影视文化、开发科技、旅游、农业文化。
					公司以创新求发展；以质量求生存；以奉献求价值；以服务求支持。诚信、务实、勤奋、创新，崭新的设计理念，优秀的工作团队和完善的售后服务体系，用卓越的品味和全方面的服务，赢得您的认可，让我们携手同行。
					企业要以诚信为本，涵韵文化始终对客户诚信、对合作伙伴诚信、对政府诚信、对涵韵人诚信。珍视企业品牌，视品牌如眼睛，视质量为生命，承担公民责任，做公益事业来回报社会。
					公司最初从印刷行业为主体运营机制逐步成长为：大型活动策划实施、模特培训推广、企业策划文案、广告服务及制作、文化体育用品、工艺礼品、玻璃制品、农副产品、政府采购指定印刷供应商。主要服务于京津地区、市级、局级、企业、事业、金融、教育等各企事业单位及合资企业。
					公司在文化事业中加大投资发展，融入博士学位、学士学位人才，专业化设计团队，资深的技术专业员工，专业科学管理方式，完善的服务体系机制。
					秉承：“诚信致远、卓越明智、自信自强、合作共赢”的经营理念，与您共谋发展。 <br /> <br />
					经营范围：影视节目制作项目筹建，广告业务，企业形象策划，市场营销策划，舞台设计，企业管理咨询，影视策划，商务信息咨询，计算机图文设计、制作，会议服务，展览展示服务，庆典礼仪服务，摄影服务，服装、
					鞋帽、家具、玩具、日用百货、木制品、塑料制品、工艺品批发兼零售。 <br /> <br /> 公司地址：天津市武清区徐官屯街江源道4号

				</div>
			</center>
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