<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'reg.jsp' starting page</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript">
		function checkSubmit() {
		
			//校验
			/* var userName=document.getElementsByName("userName")[0].value;
			if(userName==null || userName==""){
				alert("用户名不能为空");
				return;
			}
			
			var password=document.getElementsByName("password")[0].value;
			var repassword=document.getElementsByName("repassword")[0].value;
			if(password==null || password=="" ){
				alert("密码不能为空");
				return;
			}
			if(repassword!=password){
				alert("两次输入密码不正确");
				return;
			}
			
			var email=document.getElementsByName("email")[0].value;
			if(email!=null){
				alert("请输入正确的邮箱地址");
				return;
			} */
			document.forms[0].submit();
		}
	</script>
  </head>

<body>
	<h2>用户注册</h2>
	<a href="<%=path%>/mid?locale=zh_CN" >中文</a><a href="<%=path%>/mid?locale=en_GB">英文</a>
	<form action="<%=path%>/reg" method="post">
		<s:message code="userName"></s:message>:<input type="text" name="userName" /><font color="red"><form:errors path="userInfo.userName"></form:errors></font><br /><br />
		<s:message code="password"></s:message>:<input type="password" name="password" /><font color="red"><form:errors path="userInfo.password"></form:errors></font><br><br />
		<s:message code="repassword"></s:message>:<input type="password" name="repassword" /><font color="red"><form:errors path="userInfo.repassword"></form:errors></font><br><br />
		<s:message code="email"></s:message>:<input type="text" name="email" /><font color="red"><form:errors path="userInfo.email"></form:errors></font><br /><br />
		<s:message code="age"></s:message>:<input type="text" name="age" /><font color="red"><form:errors path="userInfo.age"></form:errors></font><br /><br />
		<s:message code="phone"></s:message>:<input type="text" name="phone" /><font color="red"><form:errors path="userInfo.phone"></form:errors></font><br /><br />
		<input type="button" value="注册" onclick="checkSubmit()" /><br /><br />
		<br />
	</form>
</body>
</html>