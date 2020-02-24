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
    
    <title>游币消费记录</title>
    
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
			url:"<%=basePath%>expense/showExpenses.do",
			data:{"userId":userId},
			success: function(msg){
				var array = eval(msg);
				console.log(array);
					var table = $("#searchTable");
					table.html($("table tr")[0]);
					if(array.length != 0){
						for(var i = 0; i < array.length ;i++){
							console.log(array[i].userName + "!!!");
							console.log(array[i].gameName + "!!!");
							var tr = $("<tr></tr>");
							var userName = $("<td>"+array[i].username+"</td>");
							var gameName = $("<td>"+array[i].gameName+"</td>");
							var oprations = array[i].oprations;
							var oprationsForUser;
							if(oprations == 1){
								oprationsForUser = "话费";
							}else{
								oprationsForUser = "游币";
							}
							var opra = $("<td></td>");
							opra.html(oprationsForUser);
							
							var monetary = $("<td>"+array[i].monetary+"</td>");
							
							var date = array[i].createTime;
							var str = (date.year+1900)+"-"+(date.month+1)+"-"+date.date+" "+date.hours+":"+date.minutes+":"+date.seconds
							var createTime = $("<td>"+str+"</td>");
							var downloads = $("<td>"+array[i].downloads+"</td>");
							tr.append(userName).append(gameName).append(opra).append(monetary).append(createTime).append(downloads);		
							table.append(tr);
						}
					}else{
						var tr = $("<tr><td colspan='5'>机不可失失不再来，快去消费吧！</td></tr>");
						table.append(tr);
					}
			}
		});
	}
	
	$(document).ready(function(){
			search();
		});
	</script>	
	
  </head>
  
  <body>
  	<c:import url="../head.jsp"></c:import>
	<div class="row">&nbsp;</div>
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="table-responsive">
					
							<table class="table table-striped table-hover" id="searchTable">
						<caption>消费</caption>
						<thead>
							<tr>
								<td>用户名</td>
								<td>游戏名</td>
								<td>购买方式</td>
								<td>购买价格</td>
								<td>购买时间</td>
								<td>下载次数</td>
							</tr>
						</thead>
						</tbody>
					</table>
							<!--  该用户尚未购买过游戏-->
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
