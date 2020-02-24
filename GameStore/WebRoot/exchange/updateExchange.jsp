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
		function checkLogin(){
			var charge = $("#charge").val();
			if(charge==""){
				alert("兑换比例不能为空");
				return false;
			}
			if(!/^([1-9]\d*(\.\d*[1-9])?)|(0\.\d*[1-9])$/.test(charge)){
				alert("兑换比例只能为大于0的整数或小数");
				return false;
			}
				return true;
		}
		function getDownListProvs(){
			var id = $("#id").val();
			if(id == ""){
				$.ajax({
	  	            type:"POST",
	  	            url:"<%=basePath%>prov/getProvId.do",
	  	            success:function (message) {
	  	                var provList = message;
	  	                var provTypeEle = $("#provId");
	  	                for (var i = 0; i < provList.length; i++) {
	  	                    var option = $('<option></option>');
	  	                    option.attr("value",provList[i].id);
	  	                    option.text(provList[i].provinceName);
	  	                    provTypeEle.append(option);
	  	                }
	  	            }
	  	        });
			}else{
	  	    	$.ajax({
	  	            type:"POST",
	  	            url:"<%=basePath%>prov/getProvs.do",
	  	            success:function (message) {
	  	                var provList = message;
	  	                var provTypeEle = $("#provId");
	  	                for (var i = 0; i < provList.length; i++) {
	  	                    var option = $('<option></option>');
	  	                    option.attr("value",provList[i].id);
	  	                    option.text(provList[i].provinceName);
	  	                    provTypeEle.append(option);
	  	                }
	  	              var provId = $("#hidden").val();
		  	  	      console.log(provId);
		  	  	      $("#provId").find("option[value='"+provId+"']").attr("selected",true);
	  	            }
	  	        });
			}
  	    }
		$(document).ready(function(){
  			getDownListProvs();
  		});
	</script>
  </head>
  
  <body>
    <div id="container">
		<div class="page-header text-center">
			<h2>添加或修改对应省份游币兑换比例</h2>
		</div>
		<div class="row">
			<div class="col-xs-6 col-sm-4 col-md-offset-4">
				<form class="form-horizontal" action="<%=basePath%>exchange/addOrModifyExchange.do" >
				<input type="hidden" name="id" value="${exchange.id }" id="id">
					<table class="table table-bordered table-condensed ">
						<thead></thead>
						<tbody>
							<tr class="text-center">
								<td>省份名</td>
								<td>
									<div class="col-sm-8">
										<div class="radio">
											<input type="hidden" value="${exchange.provId}" id = "hidden">
											<select id="provId" name="provId" class="form-control" <c:if test="${exchange.id != null}">disabled = disabled</c:if>>
											</select>
										</div>
									</div>
								</td>
							</tr>
							<tr class="text-center">
								<td>兑换状态</td>
								<td>
									<div class="col-sm-6">
										<div class="radio">
											<c:if test="${exchange.id != null}">
												<label><input type="radio" name="exchangeStatus" value="1" checked="checked" 
														 <c:if test="${exchange.exchangeStatus==1}"> checked='checked'</c:if> 
														> 商用 
														</label> 
												<label><input type="radio" name="exchangeStatus" value="2"
														 <c:if test="${exchange.exchangeStatus==2}"> checked='checked'</c:if> 
														> 下线
														</label>
											</c:if>
											<c:if test="${exchange.id == null}">
												<label><input type="radio" name="exchangeStatus" value="1" checked="checked" checked='checked' > 商用 
														</label> 
												<label><input type="radio" name="exchangeStatus" value="2" checked='checked'> 下线
														</label>
											</c:if>
										</div>
									</div></td>
							</tr>
							<tr class="text-center">
								<td>兑换比例</td>
								<td>
									<div class="col-sm-4">
										<div class="radio">
											<input type="text" id="charge" name="charge" value="${exchange.charge}" />
										</div>
									</div></td>
							</tr>
						</tbody>
					</table>
	
					<div class="row">
						<div class="col-xs-6 col-sm-7">
							<div class="form-group">
								<div class="col-sm-offset-4 col-sm-4">
									<input type="submit" class="btn btn-primary" onclick="return checkLogin()" value="确认" />
								</div>
							</div>
						</div>
						<div class="col-xs-6 col-sm-4">
							<div class="form-group">
								<div class=" col-sm-11">
									<a href="<%=basePath%>exchange/exchange.jsp" class="btn btn-warning" >返回上一页</a>
								</div>
							</div>
						</div>
					</div>
				</form>

			</div>
		</div>
	</div>
				 <c:if test="${isError}">
	     	 <script type="text/javascript">
	     	 	alert("${errorMessage}")
	     	 </script>
     	</c:if>
  </body>
  <style>
td {
	vertical-align: middle !important;
}
</style>
</html>
