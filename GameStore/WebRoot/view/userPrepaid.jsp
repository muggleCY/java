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
    
    <title>游币充值记录</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script src="<%=basePath%>js/jquery.js"></script>
  </head>
  <script type="text/javascript">
		function search(){
			var userId = $("#userId").val();
			$.ajax({
				type:"POST",
				url:"<%=basePath%>prepaid/selectPrepaidVo.do",
				data:{"id":userId},
				success: function(msg){
					var array = eval(msg);
					console.log(array);
						if(array.length != 0){
							for(var i = 0; i < array.length ;i++){
								
								var div = $("<div style='display:inline-table;margin:15px'></div>")
								var table=$("<table class='table table-striped table-hover'></table>");
								var tbody=$("<tbody></tbody>");
							
								
								
								
								var tr1=$("<tr></tr>");
								var username = $("<td><strong>用户名:</strong>"+array[i].uname+"</td>");
								tr1.append(username);
								
								var tr2=$("<tr></tr>");
								var cardId = $("<td><strong>卡号:</strong>"+array[i].cardNum+"</td>");
								tr2.append(cardId);
								
								var tr3=$("<tr></tr>");
								var cardPwd = $("<td><strong>密码:</strong>"+array[i].cardPwd+"</td> ");
								tr3.append(cardPwd);
								
								var tr4=$("<tr></tr>");
								var amount = $("<td><strong>密保额度:</strong>"+array[i].amount+"</td>");
								tr4.append(amount);
								
								
								tbody.append(tr1).append(tr2).append(tr3).append(tr4);
								table.append(tbody);
								div.append(table);
								$("#tableDiv").append(div);
							}
						}else{
							var tr = $("<tr><td>尚无充值记录，快去充值吧！</td></tr>");
							table.append(tr);
						}
				}
			});
		}
		$(document).ready(function(){
  			search();
  		});
	</script>
  <body>

  	<c:import url="../head.jsp">
  	</c:import>
	<div class="row">&nbsp;</div>
	<div class="container">
		<div class="row">
			<div class="col-md-4 col-md-offset-5" style="font-size:20px">
				充值记录
			</div>
			<div class="col-md-12">
				<div class="table-responsive" >
					<input type="hidden" id="userId" value="${user.id}">
					<div id="tableDiv"></div>
						<!-- 
					<table id="searchTable" class="table table-striped table-hover">
						<tbody>
							<tr>
								<td><strong>用户名:</strong>lufei</td>
							</tr>
							<tr>
								<td><strong>卡号:</strong>ELPV293NNBGP</td>
							</tr>
							<tr>
 								<td><strong>密码:</strong>LMdFhE</td> 
							</tr>
							<tr>
								<td><strong>密保额度:</strong>300</td>
							</tr>
							<tr>
								<td><strong>密保额度有效结束时间:</strong>
 								2017-05-01 12:16:19
								</td>
							</tr>
						
						</tbody>
					</table>
						 -->
					<!-- 该用户尚未充值 -->
					
       				<a class="btn btn-warning" href="javascript:window.history.go(-1)" >返回上页</a>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="container">
			<hr/>
			<div class="col-md-4 col-md-offset-5">
			&copy;2016-2017 portal.com 版权所有
			</div>
		</div>	
	</div>
  </body>
</html>
