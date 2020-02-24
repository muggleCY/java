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
    
    <title>修改游戏类型</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="description" content="">
	<meta name="author" content="">
	

	
	
	<link rel="stylesheet" href="<%=basePath%>css/bootstrap.min.css">
	<script src="<%=basePath%>js/jquery.js"></script>
	<script src="<%=basePath%>js/bootstrap.min.js"></script>
	<style type="text/css">
	#container {
		margin-top: -20px;
		margin-left: 10px;
	}
	</style>
	<script type="text/javascript">
	function check() {
		var oldName = $("#typeName").val();
		var oldStatus = $("#typeStatus").val();
		var typeName = $("#newName").val();
		var typeStatus = $("input[name='typeStatus']:checked").val();
		if(typeName == ""){
			alert("类型名不能为空");
			return false;
		}
		if(oldName == typeName&&oldStatus == typeStatus){
			alert("并没有修改任何信息噗");
			return false;
		}
		if((/[#%*._&^$@/\\()<>{}[\] ]/.test(typeName))){
			alert("类型名含有非法字符");
			return false;
		}
		return true;
	}
	</script>
  </head>
  
  <body>
    <div id="container">
		<div class="page-header text-center">
			<h2>修改游戏类型</h2>
		</div>
		<div class="row">
			<div class="col-xs-6 col-sm-4 col-md-offset-4">
				<form class="form-horizontal" action="<%=basePath%>gameType/updateGameType.do" >
					<table class="table table-bordered table-condensed ">
						<thead></thead>
						<tbody>
							<tr class="text-center">
								<td>类型名称</td>
								<td>
									<div class="col-sm-10">
										<input type="text" class="form-control" id="newName" name="typeName" value="${gameType.typeName}">
										<input type="hidden" class="form-control" name="id" value="${gameType.id}">
										<input type="hidden" class="form-control" id="typeName" value="${gameType.typeName}">
										<input type="hidden" class="form-control" id="typeStatus" value="${gameType.typeStatus}">
									</div></td>
							</tr>
							<tr class="text-center">
								<td>状态</td>
								<td>
									<div class="col-sm-4">
										<div class="radio">
											
													<label> <input type="radio" name="typeStatus"
														value="1" <c:if test="${gameType.typeStatus==1}"> checked='checked'</c:if> 
														> 商用 </label>
													<label> <input type="radio" name="typeStatus"
														value="2" <c:if test="${gameType.typeStatus==2}"> checked='checked'</c:if> 
														> 下线</label>
										</div>
									</div></td>
							</tr>
						</tbody>
					</table>
					<div class="row">
						<div class="col-xs-6 col-sm-7">
							<div class="form-group">
								<div class="col-sm-offset-4 col-sm-4">
									<input type="submit" class="btn btn-primary" onclick="return check()" value="确认修改" />
								</div>
							</div>
						</div>
						<div class="col-xs-6 col-sm-4">
							<div class="form-group">
								<div class=" col-sm-11">
									<a class="btn btn-warning" href="<%=basePath%>gameType/gameType.jsp">返回上一页</a>
								</div>
							</div>
						</div>
					</div>
				</form>

			</div>
		</div>
	</div>

  </body>
</html>
