<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'saveFood.jsp' starting page</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
	<!-- 表单内容 -->
	<form action="${pageContext.request.contextPath }/food" method="post" enctype="multipart/form-data">
		<input type="hidden" name="_method" value="post">
			<table cellpadding="0" cellspacing="0" class="mainForm">
			<tr>
				<td width="80px">菜名</td>
				<td><input type="text" name="foodName" class="InputStyle" value=""/> *</td>
			</tr>
			<tr>
			<td>价格</td>
			<td><input type="text" name="price" class="InputStyle" value=""/> *</td>
			</tr>
						
			<tr>
				<td width="80px">菜品图片</td>
				<td>
					<input type="file" name="imageUrl"/> *
				</td>
			</tr>
		</table>
		<!-- 表单操作 -->
		<div>
            <input type="submit" value="添加" class="FunctionButtonInput">
            <a href="javascript:history.go(-1);" class="FunctionButton">返回</a>
        </div>
	</form>
  </body>
</html>
