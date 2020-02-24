<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'top.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

	<link rel="stylesheet" href="<%=basePath%>css/bootstrap.min.css">
	<script src="<%=basePath%>js/jquery.js"></script>
	<script src="<%=basePath%>js/bootstrap.min.js"></script>
	
	
	
	
	<style type="text/css">
	</style>
	
	<script type="text/javascript">
		function clearManager() {
			$.ajax({
				type:"POST",
				url:"<%=basePath%>manager/clearManager.do",
				success: function(msg){
					window.location.href ="javascript:parent.location.href='/GameStore/login.jsp'";
				}
			});
		}
		function getTime(){
			var date=new Date();
			var time=document.getElementById("time");
			time.innerHTML=date.toLocaleString();
			console.log
		}
		$(document).ready(function() {
			window.setInterval("getTime()",1000);
		});
	</script>
  </head>
  
  <body>
    <nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar" >
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand">游币后台管理系统</a>
			</div>
			<div id="navbar" class="collapse navbar-collapse ">
				<ul class="nav navbar-nav ">
					<li><a>${manager.username}管理员登录</a></li>
				</ul>
				<ul class="nav navbar-nav ">
					<li><a id="time" style="color: #9d9d9d;"></a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li><a onclick="clearManager()" target="_self">退出</a></li>
				</ul>
			</div>
		</div>
		
	</nav>
		
  </body>
</html>
