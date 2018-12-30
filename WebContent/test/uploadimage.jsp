<%@ page language="java" contentType="text/html; charset=UTF-8"
		pageEncoding="UTF-8"%>
<%@ page import="com.jspsmart.upload.*" %>
 <html>
 <head><title>smartupload组件上传01</title></head>
 
<body>
  <%
     SmartUpload smart = new SmartUpload() ;
     smart.initialize(pageContext) ;    // 初始化上传操作
     smart.upload();        // 上传准备
     smart.save("upload") ;    // 文件保存
     out.print("上传成功");
 %>
</body>
 </html>
