<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
 <head><title>smartupload组件上传</title>
 <meta http-equiv="Content-Type" content="text/html; charset=utf-8" /></head>
 <body>
 <form action="uploadimage.jsp" method="post" enctype="multipart/form-data">
     图片<input type="file" name="pic">
     <input type="submit" value="上传">
 </form>
 </body>
 </html>