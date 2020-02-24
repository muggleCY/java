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
    
    <title>注册界面</title>
    
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
	<script src="<%=basePath%>js/jquery-1.7.2.min.js"></script>
	<style type="text/css">
		.red{
			color:red;
		}
		.green{
			color:green;
		}
	</style>
    <script type="text/javascript" >
    $(document).ready(function(){
        getDownListProvs();
    });
    $(function() {

        $('#login #password,#login #userPwd2').focus(function() {
            $('#owl-login').addClass('password');
        }).blur(function() {
            $('#owl-login').removeClass('password');
        });
        var config = {
				vx: 4,
				vy:  4,
				height: 2,
				width: 2,
				count: 300,
				color: "121, 162, 185",
				stroke: "100,200,180",
				dist: 8000,
				e_dist: 60000,
				max_conn: 10
			};
			CanvasParticle(config);
         
    });

    function getDownListProvs(){
    	$.ajax({
            type:"POST",
            url:"<%=basePath%>prov/getProvs.do",
            success:function (message) {
                var provList = message;
                var provTypeEle = $("#province");
                for (var i = 0; i < provList.length; i++) {
                    var option = $('<option></option>');
                    option.attr("value",provList[i].id);
                    option.text(provList[i].provinceName);
                    provTypeEle.append(option);
                }
            }
        });
    }
    function userForm(){
    	var username = $("#username").val();
    	console.log(username);
    	var password = $("#password").val();
    	var userPwd2 = $("#userPwd2").val();
    	var iphone = $("#iphone").val();
    	//  /^[a-zA-Z0-9]{4,7}$/  /(?!([a-zA-Z0-9]).*?\1+$)^[a-zA-Z0-9]{2，16}$/
    	if(!(/^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{2,16}$/.test(username))){
    		alert("用户名格式不正确");
    		return false;
    	}
    	if(!(/^[0-9a-zA-Z]{6,12}$/.test(password))){
    		alert("密码格式不正确");
    	}
    	if(password != userPwd2){
    		alert("确认密码与原密码不匹配");
    		return false;
    	}
    	if(iphone == " "||iphone.size() != 11){
    		alert("手机号格式不正确")
    	}
    }
    function usernameForm(){
    	var username = $("#username").val();
    	if(!(/^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{2,16}$/.test(username))){
    		alert("用户名不符合格式");
    	}
    }
    function passwordForm(){
    	var password = $("#password").val();
    	if(!(/^[0-9a-zA-Z]{6,12}$/.test(password))){
    		alert("密码格式不正确");
    	}
    }
    function userPwd2Form(){
    	var password = $("#password").val();
    	var userPwd2 = $("#userPwd2").val();
    	if(password != userPwd2){
    		alert("确认密码与原密码不匹配");
    	}
    }
    function iphoneForm(){
    	var iphone = $("#iphone").val();
    	if(iphone.length != 11){
    		alert("手机号格式不正确")
    	}
    }
    
    </script>
  </head>
  
  <body>
    <div id="login">
	    <div class="wrapper">
	        <div class="login">
	            <form action="<%=basePath%>user/addUser.do" method="post" class="container offset1 loginform">
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
	                            <h4>游币用户注册</h4>
	                        </div>
	                    </div>
	                    <div class="control-group">
	                        &nbsp;
	                    </div>
	                    
	                    <div class="control-group">
	                        <div class="controls">
	                            <label for="userName" id="userNameMsg" class="glyphicon glyphicon-user"></label>
	                            <input id="username" type="text" name="username"  placeholder="姓名(超过两个字符)" autofocus="autofocus" required="required" tabindex="1"  class="form-control input-medium" onblur="usernameForm()">
	                        </div>
	                    </div>
	                    <div class="control-group">
	                        <div class="controls">
	                            <label for="password"  id="userPwdMsg" class="glyphicon glyphicon-th"></label>
	                            <input id="password" type="password" name="password" placeholder="密码(6-16个字符)" required="required" tabindex="2" class="form-control input-medium" onblur="passwordForm()">
	                        </div>
	                    </div>
	                    <div class="control-group">
	                        <div class="controls">
	                            <label for="password" id="userPwdMsg2"  class="glyphicon glyphicon-th"></label>
	                            <input id="userPwd2" type="password"  placeholder="确认密码" required="required" tabindex="3" class="form-control input-medium" onblur="userPwd2Form()">
	                        </div>
	                    </div>
	                     
	                    <div class="control-group">
	                        <div class="controls">
	                            <label for="Number"  class="glyphicon glyphicon-user"></label>
	                            <input id="iphone" name="iphone"  type="text"  placeholder="手机号码 " required="required"  tabindex="4"  class="form-control input-medium" onblur="iphoneForm()">
	                        </div>
	                    </div>
			    	<div class="control-group">
	                        <div class="controls">
	                        <input id="sex" type="hidden">
	                            <input type="radio" name="sex" value="0"  tabindex="5" checked="checked"/>男
	                            <input type="radio" name="sex" value="1"  tabindex="6"/>女
	                        </div>
	                    </div>
			    	<div class="control-group">
	                        <div class="controls">
	                        <input id="provinceId" type="hidden">
	                            <label for="birthday" id="birthdayMsg" class="glyphicon glyphicon-user"></label>
	                            <select name="provinceId" tabindex="7"  class="form-control input-medium" id="province" style="height: auto;padding: 9px 6px 9px 40px;">
	                            </select>
	                        </div>
	                    </div>
	                </div>
	                <div class="form-actions">
	                    <a href="<%=basePath%>view/userLogin.jsp" tabindex="9" class="btn btn-link text-muted " style="color:red">返回登录</a>
	                    <button type="submit" tabindex="8" class="btn btn-primary">点击注册</button>
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
