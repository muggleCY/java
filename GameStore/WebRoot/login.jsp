<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>游币管理员后台登录页面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
	<!-- Le styles -->
	
	<link rel="stylesheet" href="<%=basePath%>css/style.css">
	<link rel="stylesheet" href="<%=basePath%>css/bootstrap.css">
	
	<script type="text/javascript" src="<%=basePath%>js/jquery.js"></script>
	<!-- MAIN EFFECT -->
	<script type="text/javascript" src="<%=basePath%>js/bootstrap.js"></script>
	
	<link rel="shortcut icon" href="assets/ico/minus.png">
	
	<script src="<%=basePath%>js/jquery-1.7.2.min.js"></script>
	<style type="text/css">
	#zhong {
		margin-top: 200px;
	}
	
	#yzmsj {
		display: none;
	}
	
	#1 {
		color: green;
	}
	</style>
	<script type="text/javascript">
	function refreshVali(){
		var codesStr = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";		
		var codesChar = [''];
		for (var i = 0; i < 4 ; i++){
			var index =parseInt(Math.random()*62);
			codesChar[i]=codesStr.charAt(index);
		}
		var codes = codesChar.join("");
		$("#valiCode").text(codes);
	}
	$(document).ready(function(){
		refreshVali();
	});	
	function checkLogin(){
		var username = $("#username").val();
		if(username==""){
			alert("用户名不能为空");
			return false;
		}
		if(/[<>\/\\*^]+/.test(username)){
			alert("用户名含有非法字符");
			return false;
		}
		var password = $("#password").val();
		if(password==""){
			alert("密码不能为空");
			return false;
		}
		if(password.length > 20){
			alert("密码过长");
			return false;
		}
		
		var codeTypeIn = $("#code").val();
		console.log(codeTypeIn);
		var code = $("#valiCode").text();
		console.log(code);
		if(codeTypeIn.toUpperCase()!=code.toUpperCase()){
			alert("验证码输入不正确");
			refreshVali();
			return false;
		}
			return true;
	}
	</script>
  </head>
  
  <body>
    <div id="preloader"></div>
	<div class="container">
		<div id="zhong">
			<div class="" id="login-wrapper">
				<div class="row">
					<div class="col-md-4 col-md-offset-4">
						<div id="logo-login">
							<h1>游币后台登录页面</h1>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-4 col-md-offset-4">
						<div class="account-box">
							<form action="<%=basePath%>manager/login.do"
								method="post" >

								<div class="row">
									<div class="col-sm-9">
										<label  for="inputUsername">用户名</label>
										<div class="row">
											<div class="col-xs-8 col-sm-12">
												<input name = "username" id = "username" type="text" 
												class="form-control">
											</div>
											<div class="col-xs-3 col-sm-5">
												<span id="1"></span>
											</div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-sm-9">
										<label  for="pwd">密码</label> 
											<input id = "password" name = "password"
											type="password" class="form-control">
									</div>
								</div>
								
								
								<div class="row">
									<div class="col-sm-9">
										<label for="inputverificationcode">验证码 </label>
										<div class="row">
											<div class="col-xs-8 col-sm-8">
												<input id = "code" type="text" class="form-control">
											</div>
											<div id = "valiDiv">
						     	  				<span  id = "valiCode" onclick= "refreshVali()" ></span>
					     	  				</div>
										</div>
									</div>
								</div>
								
								<div ></div>
								
								<div class="row">
									&nbsp;
								</div>
								<button id="start" class="btn btn btn-primary "  type="submit" onclick="return checkLogin()">登 录
								</button>
							</form>
							<a class="forgotLnk" href="index.jsp"></a>
							<div class="row-block">
								<div class="row"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
     <c:if test="${isError}">
     	 <script type="text/javascript">
     	 	alert("${errorMessage}");
     	 	<%
     	 		session.removeAttribute("isError");
     	 		session.removeAttribute("errorMessage");
     	 	%>
     	 </script>
     </c:if>
  </body>
</html>