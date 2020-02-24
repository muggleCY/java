<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>游币游戏详情</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
	<script src="<%=basePath%>js/jquery.js"></script>
	
		<script type="text/javascript">
		function search(){
			var userId = $("#userId").val();
			$.ajax({
				type:"POST",
				url:"<%=basePath%>user/getUserById.do",
				data:{"id":userId},
				success: function(msg){
					var array = eval(msg);
					console.log(array);
						var table = $("#searchTable");
						for(var i = 0; i < array.length ;i++){
							var tr1 = $("<tr></tr>");
							var username = $("<td><strong>用户名称:</strong>"+array[i].username+"</td>");
							tr1.append(username);
							var tr2 = $("<tr></tr>");
							var pwd = $("<td><strong>用户密码:</strong>"+array[i].password+"</td>");
							tr2.append(pwd);
							var tr3 = $("<tr></tr>");
							var userSex = "";
							if(array[i].sex == 0){
								userSex = "男";
							}else if(array[i].sex == 1){
								userSex = "女";
							}
							var sex = $("<td><strong>用户性别:</strong>"+userSex+"</td>");
							tr3.append(sex);
							var tr4 = $("<tr></tr>");
							var prov = $("<td><strong>用户省份:</strong>"+array[i].provinceName+"</td>");
							tr4.append(prov);
							var tr5 = $("<tr></tr>");
							var phone = $("<td><strong>手机号码:</strong>"+array[i].iphone+"</td>");
							tr5.append(phone);
							var tr6 = $("<tr></tr>");
							var tariffe = $("<td><strong>话费余额:</strong>"+array[i].tariffe+"</td>");
							tr6.append(tariffe);
							var tr7 = $("<tr></tr>");
							var currency = $("<td><strong>游币余额:</strong>"+array[i].currency+"</td>");
							tr7.append(currency);
							table.append(tr1).append(tr2).append(tr3).append(tr4).append(tr5).append(tr6).append(tr7);
						}
				}
			});
		}
		
		$(document).ready(function(){
  			search();
  		});
		
		
		function checkPwd() {
			var newPwd = $("#userPwd").val();
			var oldPwd = $("#oldPwd").val();
			if(newPwd == ""){
				alert("新密码不能为空");
				return false;
			}
			if(newPwd.length < 6){
				alert("密码过短");
				return false;
			}
			if(newPwd.length > 20){
				alert("密码过长");
				return false;
			}
			var userPwdConfirm = $("#userPwdConfirm").val();
			if(userPwdConfirm == ""){
				alert("确认密码不能为空");
				return false;
			}
			if(userPwdConfirm != newPwd){
				alert("确认密码与新密码不匹配");
				return false;
			}
			if(newPwd == oldPwd){
				alert("新密码不能和旧密码相同");
				return false;
			}
			var result = window.confirm("确认要修改吗?修改后需要重新登录");
			if(true == result){
				return true;
			}else{
				return false;
			}
		}
		
	</script>
	
	
  </head>
  
  <body>
    <c:import url="../head.jsp">
  	</c:import>

    
   
	<div class="row">&nbsp;</div>
	<div class="container">
		<div class="row">
			<div class="col-md-6">
				<div class="table-responsive">
					<input type="hidden" id="userId" value="${user.id}">
					<input type="hidden" id="oldPwd" value="${user.password}">
					<table id="searchTable" class="table table-striped table-hover">
						<caption>账户详情</caption>
						<tbody>
														
						</tbody>
					</table>
					
       				<a class="btn btn-warning"  onclick="javascript :history.back(-1);">返回上页</a>
       				<a class="btn btn-primary"  data-toggle="modal" data-target="#myModal2" >修改密码</a>
       				
       				
       				
       				<div class="modal fade" id="myModal2" tabindex="-1" >
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-hidden="true">&times;</button>
									<h4 class="modal-title" id="myModalLabel">修改密码</h4>
								</div>
								<div class="modal-body">
									<form class="form-horizontal" action="<%=basePath%>user/modifyPwd.do" 
									enctype="multipart/form-data" method="post"  >
										<div class="form-group">
											<label for="firstname" class="col-sm-2 control-label">新密码</label>
											<div class="col-sm-10">
												<input type="hidden" name="id" value="${user.id}">
												<input type="password" class="form-control" id="userPwd"
													name="userPwd" placeholder="请输入新密码">
											</div>
										</div>
										<div class="form-group">
											<label for="firstname" class="col-sm-2 control-label">确认密码</label>
											<div class="col-sm-10">
												<input type="password" class="form-control" id="userPwdConfirm"
													name="userPwdConfirm" placeholder="请输入新密码">
											</div>
										</div>
										<div class="form-group">
											<div class="col-sm-offset-2 col-sm-10">
												<button type="submit" onclick="return checkPwd()" class="btn btn-default">确认</button>
											</div>
										</div>
									</form>
								</div>
				
							</div>
						</div>
					</div>
       				
       				
       				
       				
       				
				</div>
			</div>
		</div>
	</div><div class="row">
	<div class="container">
		<hr/>
		<div class="col-md-4 col-md-offset-5">
		&copy;2016-2017 portal.com 版权所有
		</div>
	</div>	
</div>
	 <c:if test="${isError}">
	     	 <script type="text/javascript">
	     	 	alert("${errorMessage}")
	     	 </script>
     	</c:if>


  </body>
</html>
