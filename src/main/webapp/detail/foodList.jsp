<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'foodList.jsp' starting page</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<style type="text/css">
		a{
			text-decoration: none;
		}
	</style>
  </head>
  
  <body>
    <div>
		<form action="${pageContext.request.contextPath }/showFood" method="get">
			<input type="hidden" name="method" value="search">
			<input type="text" name="foodName" title="请输入菜品名称">
			<input type="submit" value="搜索">
		</form>
	</div>
	
	<div>
    <table cellspacing="0" cellpadding="0" border="1px" width="800" height="251">
        <!-- 表头-->
        <thead>
            <tr align="center">
				<td>菜编号</td>
				<td>菜名</td>
				<td>价格</td>
				<td>操作</td>
			</tr>
		</thead>	
		<!--显示数据列表 -->
        <tbody>
			<c:forEach var="temp" items="${requestScope.pt.data}">
				<tr>
					<td>${pageScope.temp.foodid }</td>
					<td><a href="${pageContext.request.contextPath }/food/${pageScope.temp.foodid }">${pageScope.temp.foodname }</a></td>
					<td>${pageScope.temp.price }</td>
					<td>
						<a href="${pageContext.request.contextPath }/detail/updateFood.jsp?foodId=${pageScope.temp.foodid}&foodName=${pageScope.temp.foodname}&price=${pageScope.temp.price }&imagepath=${pageScope.temp.imagepath }" ><input type="button" value="更新"></a>
						<form action="${pageContext.request.contextPath }/food/${pageScope.temp.foodid }" name="operation" method="post" style="display:inline-block;">
							<input type="hidden" name="_method" value="DELETE"/> 		
							<input type="submit" value="删除"/>
						</form>				
					</td>
				</tr>
			</c:forEach>
			<tr>
			<td colspan="4">
				<a href="${pageContext.request.contextPath}/showFood?curPage=1">首页</a>
				<a href="${pageContext.request.contextPath}/showFood?curPage=${requestScope.pt.prePage}">上一页</a>
				<c:forEach var="i" begin="1" end="${requestScope.pt.totalPage}" step="1">
					<a href="${pageContext.request.contextPath}/showFood?curPage=${pageScope.i}">${pageScope.i}</a>
				</c:forEach>
				<form action="${pageContext.request.contextPath}/showFood" style="display:inline;">
					总共${requestScope.pt.totalPage }页，<input type="text" name="curPage" value="${requestScope.pt.curPage }" size="1">
				</form>
				<a href="${pageContext.request.contextPath}/showFood?curPage=${requestScope.pt.nextPage}">下一页</a>
				<a href="${pageContext.request.contextPath}/showFood?curPage=${requestScope.pt.totalPage}">尾页</a>
			</td>
		</tr>
        </tbody>
    </table>
	
   <!-- 其他功能超链接 -->
	<div>
		<div class="FunctionButton"><a href="${pageContext.request.contextPath }/detail/saveFood.jsp">添加</a></div>
    </div> 
</div>
  </body>
</html>
