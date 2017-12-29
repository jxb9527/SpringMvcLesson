<%@ tag language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%
	String ranStr =UUID.randomUUID().toString();
	String key="myToken";
	session.setAttribute(key,ranStr);
%>
<input type="hidden" name="<%= key%>" value="<%=ranStr%>"/>