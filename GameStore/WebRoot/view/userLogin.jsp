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
    
    <title>登录界面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" href="<%=basePath%>css/jq22.css">
	<link rel="stylesheet" href="<%=basePath%>css/bootstrap.min.css">
	<link rel="stylesheet" href="<%=basePath%>css/bootstrap.css">
	<script type="text/javascript" src="<%=basePath%>js/canvas-particle.js"></script>
	<script src="<%=basePath%>js/jquery.min.js"></script>
	<style type="text/css">
		#msg2{
			color:red;
		}
	</style>
    <script type="text/javascript">
		window.onload = function(){
			var config = {
				vx: 4,
				vy:  4,
				height: 2,
				width: 2,
				count: 300,
				color: "121, 162, 185",
				stroke: "100,200,180",
				dist: 6000,
				e_dist: 20000,
				max_conn: 10
			};
			CanvasParticle(config);
		};
	    $(function() {
	
	        $('#login #userPwd').focus(function() {
	            $('#owl-login').addClass('password');
	        }).blur(function() {
	            $('#owl-login').removeClass('password');
	        });
	    });
	    function getUser(){
			var username = $("#username").val();
			var password = $("#password").val();
			$.ajax({
				type:"POST",
				url:"<%=basePath%>user/getUser.do",
				data:{
					"username":username,
					"password":password,
				},
				success:function(msg){
					var result = $("#result");
					result.html("错误");
				}
			});
	    }
	    function check(){
			var username=$("#name").val();
			var password=$("#userPwd").val();
			var errorInfo="";
			if(username.trim()==""||password.trim()==""){
	   			errorInfo+="用户名或密码不能为空\r\n";
	   		}
			if(/^\w*[<>\\\/\*\^]+\w*$/.test(username)){
				errorInfo+="用户名不能包含非法字符\r\n";
			}
			if (password.length < 6 && password.length > 16) {
				errorInfo += "密码长度在6~16位之间\r\n";
			}
			if(errorInfo.length>0){
				alert(errorInfo);
				return false;
	   		}
			return true;
		}
    </script>
  </head>
  
  <body>
    <div id="login">
	    <div class="wrapper">
	        <div class="login">
	            <form action="<%=basePath%>user/getUser.do" onclick="return check()" method="post" class="container offset1 loginform">
	                <div id="owl-login">
	                    <div class="hand"></div>
	                    <div class="hand hand-r"></div>
	                    <div class="arms">
	                        <div class="arm"></div>
	                        <div class="arm arm-r"></div>
	                    </div>
	                </div>
	                <div class="pad">
	                    <div class="control-group">
	                        <div class="controls text-center">
	                            <h4>游币客户登录</h4>
	                        </div>
	                    </div>
	                    <div class="control-group">
	                        &nbsp;
	                    </div>
	                    <div class="control-group">
	                        <div class="controls">
	                            <label for="name" id="nameMsg" class="glyphicon glyphicon-user"></label>
	                            <input id="username" type="text" name="username" placeholder="用户名" required="required"  tabindex="1" autofocus="autofocus" class="form-control input-medium">
	                        </div>
	                    </div>
	                    <div class="control-group">
	                        <div class="controls">
	                            <label for="userPwd" id="userPwdMsg" class="glyphicon glyphicon-th"></label>
	                            <input id="password" type="password" name="password" required="required" placeholder="密码(6-16个字符)" tabindex="2" class="form-control input-medium">
	                        </div>
	                    </div>
			   
	                </div>
	                <div class="form-actions">
	                	<span id="result"></span>
	                	<a href="<%=basePath%>view/userRegister.jsp" tabindex="4" class="btn btn-link text-muted"  style="color:red">新用户注册</a>
	                    <button type="submit" tabindex="3"  class="btn btn-primary">登录</button>
	                </div>
	            </form>
	        </div>
	    </div>
	</div>
	<c:if test="${isError}">
		<script type="text/javascript">
			alert("${errorMsg}");
		</script>
	</c:if>
  </body>
</html>
