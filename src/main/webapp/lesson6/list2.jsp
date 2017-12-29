<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'list.jsp' starting page</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is mys page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript">

	function sendAjax(url, methodType, param, retnFunction) {
		//创建 简称XHR对象
		var xmlhttp = new XMLHttpRequest();

		//回调函数（当请求发送后自动调用）接收结果
		xmlhttp.onreadystatechange = function() {
			if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
				retnFunction(xmlhttp.responseText);
			}
		};
		if (methodType.toUpperCase() == "GET") {
			xmlhttp.open("GET", url + "?" + param, true);
			xmlhttp.send();
		} else {
			xmlhttp.open("POST", url, true);
			xmlhttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=utf-8");
			xmlhttp.send(param);
		}
	}

	/*
	 使用ajax
	 1.尽量使用 true 异步模式  （防假死）
	 2.尽量将获取数据之后的逻辑处理（页面渲染）放在回调函数中
	 */
	//在ajax中有两种状态分别是什么意思？
	/*
	 第一种叫readyState:表示浏览器跟服务器建立连接到数据返回时的状态码，
	 0: 请求未初始化 (没有调用send方法)
	 1: 服务器连接已建立 （socket已连接）
	 2: 请求已接收  （获取到了参数 没有执行 action方法）
	 3: 请求处理中  （已经在执行action方法 未执行完）
	 4: 请求已完成，且响应已就绪 （已经响应 并且能获取到最终的数据）
	 第二种叫status:表示http请求的状态码
	 200：成功
	 */

	function query() {
		//获取文本输入的值
		var foodName = document.getElementsByName("foodName")[0].value;
		sendAjax("${pageContext.request.contextPath}/queryFood","GET","foodName="+foodName,function(responseText){
		
			var resultJson = responseText;
			//转换为js对象
			var resultObj = JSON.parse(resultJson);
			//获取表格对象
			var table = document.getElementById("myTable");
			//将所有名字为dataTr的tr全部删除
			var allDataTr = document.getElementsByName("dataTr");
			//获取长度
			var length = allDataTr.length;
			//清除
			for ( var i = 0; i < length; i++) {
					table.removeChild(allDataTr[0]);
				}
			//根据json的行数追加多个tr
			for ( var i = 0; i < resultObj.length; i++){
				var obj = resultObj[i];
				var td = document.createElement("td");
				td.innerText = obj.foodname;
				var td1 = document.createElement("td");
				td1.innerText = obj.price;
				var td2 = document.createElement("td");
				//创建删除按钮
				var dbut = document.createElement("button");
				//创建修改按钮
				var ubut = document.createElement("button");

				dbut.innerText = "删除";
				ubut.innerText = "修改";

				td2.appendChild(dbut);
				td2.appendChild(ubut);

				var tr = document.createElement("tr");
				tr.setAttribute("name", "dataTr");
				tr.appendChild(td);
				tr.appendChild(td1);
				tr.appendChild(td2);
				table.appendChild(tr);

				//绑定对象到按钮中，将当前行的json对象绑定到当前按钮上
				dbut.foodObj = obj;

				//绑定tr到按钮中，将当前行的tr绑定到当前按钮上
				dbut.myLineTr = tr;
				
				//添加删除事件 事件类型（去掉前面的on）
				dbut.addEventListener("click",function(){
					//获取当前按钮
					var eventSrc = event.srcElement;
					//删除当前行
					table.removeChild(eventSrc.myLineTr);
					//发送ajax请求到后台，删除数据库
					
					sendAjax("${pageContext.request.contextPath}/food/"+eventSrc.foodObj.foodid,"POST","_method=delete",function(responseText){
						
						if(responseText==1){
							alert("删除成功");
						}else{
							alert("删除失败");
						}
				
					});
					
					
				});
				
				ubut.foodObj=obj;
				ubut.addEventListener("click",function(){
					//获取当前按钮
					var eventSrc = event.srcElement;
					document.getElementById('updateDiv').style.display='block';
					document.getElementsByName("umyFoodName")[0].value=eventSrc.foodObj.foodname;
					document.getElementsByName("umyFoodPrice")[0].value=eventSrc.foodObj.price;
					document.getElementsByName("umyFoodId")[0].value=eventSrc.foodObj.foodid;
				});
			}
		});
	}
	
	
	
	function saveFood(){
		var myFoodName = document.getElementsByName("myFoodName")[0].value;
		var myFoodPrice = document.getElementsByName("myFoodPrice")[0].value;
		sendAjax("${pageContext.request.contextPath}/food","POST","foodName="+myFoodName+"&price="+myFoodPrice,function(responseText){
			if(responseText==1){
				document.getElementById('addDiv').style.display='none';
				query();
				alert("插入成功");
			}else{
				alert("插入失败");
			}
		});
	}
	
	function updateFood(){
		var umyFoodName = document.getElementsByName("umyFoodName")[0].value;
		var umyFoodPrice = document.getElementsByName("umyFoodPrice")[0].value;
		var umyFoodId = document.getElementsByName("umyFoodId")[0].value;
		sendAjax("${pageContext.request.contextPath}/food/"+umyFoodId,"POST","foodName="+umyFoodName+"&price="+umyFoodPrice+"&_method=put",function(responseText){
			if(responseText==1){
				document.getElementById('updateDiv').style.display='none';
				query();
				alert("修改成功");
			}else{
				alert("修改失败");
			}
		});
	}
//open方法表示产生一个请求的关联，第一个参数表示请求的类型是用get提交，第二个表示文件在服务器上的位置请求到哪里，第三个参数表示true（异步）或 false（同步）
/*
	一个ajax线程是否执行完成，可以通过回调函数xmlhttp.onreadystatechange是否执行完成来判断
	异步：多个线程同时执行，无法判断谁先执行，true表示异步
	同步:一次只允许一个线程执行  false表示同步 ，页面会出现假死
 */
//get提交
//xmlhttp.open("GET", "${pageContext.request.contextPath}/queryFood?foodName="+foodName, true);
//xmlhttp.send();
//post提交
</script>

</head>

<body>
	<div align="left"><input type="text" name="foodName"> 
	<input type="button" value="查询" onclick="query()"> <input type="button" value="新增" onclick="document.getElementById('addDiv').style.display='block';">
	</div><table id="myTable">
		<tr><th>菜品名</th><th>菜品价格</th><th align="left">操作</th></tr>
	</table>
</body>

<div id="addDiv" style="display:none;position: absolute;left:40%;top:40%;z-index: 100;border:1px solid black; width:250px;height:100px ">
		菜品名：<input type="text" name="myFoodName"><br />
		价&nbsp;&nbsp;&nbsp;格：<input type="text" name="myFoodPrice"><br />
		<input type="button" value="保存" onclick="saveFood()">
		&nbsp;&nbsp;&nbsp;<input type="button" value="关闭" onclick="document.getElementById('addDiv').style.display='none';"><br />
</div>

<div id="updateDiv" style="display:none;position: absolute;left:40%;top:40%;z-index: 100;border:1px solid black; width:250px;height:100px ">
		<input type="hidden" name="umyFoodId" value=""/>
		 菜品名：<input type="text" name="umyFoodName"><br />
		 价&nbsp;&nbsp;&nbsp;格：<input type="text" name="umyFoodPrice"><br />
		<input type="button" value="修改" onclick="updateFood()">
		&nbsp;&nbsp;&nbsp;<input type="button" value="关闭" onclick="document.getElementById('updateDiv').style.display='none';"><br />
	</div>

</html>
