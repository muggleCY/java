<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">

	<title>用户管理</title>
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
	#container {
		margin-top: -20px;
		margin-left: 10px;
	}
	</style>
	
	<script type="text/javascript">
		var currentPage;
		var oldUsername = "";
		var oldIphone = "";
  		function search(pageNo){
  			selected = document.getElementById("all");
  			selected.checked = false;
  			var username = $("#username").val();
  			var iphone=$("#iphone").val();
  			if((/[#%*._&^$@/\\()<>{}[\] ]/.test(username))||(/[#%*._&^$@/\\()<>{}[\] ]/.test(iphone))){
  				alert("用户名含有非法字符");
  			}else{
	  			if(oldUsername != username||oldIphone != iphone){
	  				pageNo = 1;
	  			}
	  			oldUsername = username;
	  			oldIphone = iphone;
	  			$.ajax({
	  				type:"POST",
	  				url:"<%=basePath%>user/getUsersByPage.do",
	  				data:{
	  					  "pageNo":pageNo,
	  					  "username": username,
	  					  "iphone": iphone
	  					  },
	  				success:function(msg){
	  					var pager = $.parseJSON(msg);
	  					currentPage = pager;
	  					var datas = pager.list;
	  					console.log(datas);
	  					var resultTable = $("#searchTable");
	  					resultTable.html($("table tr")[0]);
	  					for(var i = 0; i < datas.length;i++){
	  						var tr = $("<tr></tr>");
	  						var check = $("<td><input type='checkbox' id='"+datas[i].id+"' onclick='checkOne()' name='opt'/></td>");
	  						
	  						var id = $("<td>"+datas[i].id+"</td>");
	  						var username = $("<td>"+datas[i].username+"</td>");
	  						var phone = $("<td>"+datas[i].iphone+"</td>") 
	  						var state = datas[i].status;
	  						var newState = "";
	  						if(state == 1){
	  							newState = "可用";
	  						}else{
	  							newState = "禁用";
	  						}
	  						var status = $("<td>"+newState+"</td>") 
	  						
	  						var date = datas[i].createTime;
	  						var str = (date.year+1900)+"-"+(date.month+1 < 10 ? '0' + (date.month+1) : (date.month+1))+"-"+(date.date < 10 ? '0' + date.date : date.date)+" "+(date.hours < 10 ? '0' + date.hours : date.hours)+":"+ (date.minutes < 10 ? '0'+date.minutes : date.minutes) +":"+(date.seconds < 10 ? '0' + date.seconds : date.seconds);
							var createtime = $("<td>"+date+"</td>")
	  						
	  						
	  						
							tr.append(check).append(id).append(username).append(phone).append(status).append(createtime);
	  						resultTable.append(tr);
	  					}
	  					for(;i<4;i++){
	  						var tr = $("<tr><td>&nbsp;</td><td></td><td></td><td></td><td></td><td></td></tr>");
	  						resultTable.append(tr);
	  					}
	  					var startPage = $("#startPage");
	  					var lastPage = $("#lastPage");
	  					lastPage.attr("onclick","search("+(pager.pageNo - 1)+")");
	  					var nextPage = $("#nextPage");
	  					nextPage.attr("onclick","search("+(pager.pageNo + 1)+")");
	  					var endPage = $("#endPage");
	  					endPage.attr("onclick","search("+pager.totalPage+")");
						var state = false;  					
	  					if(pager.pageNo == 1){
	  						state = true;
	  					}
	  					if(pager.totalPage == 0){
	  						state = true;
	  					}
	  					startPage.prop("disabled",state);
	  					lastPage.prop("disabled",state);
	  					state = false;
	  					if(pager.pageNo == pager.totalPage){
	  						state = true;
	  					}
	  					if(pager.totalPage == 0){
	  						state = true;
	  					}
	  					nextPage.prop("disabled",state);
	  					endPage.prop("disabled",state);
	  				}
	  			});
  			}
  		}
  		$(document).ready(function(){
  			search(1);
  		});
  		function checkOne(){
  			opt = document.getElementsByName("opt");
  			selected = document.getElementById("all");
  			var count =0;
  			for(var i=0;i<opt.length;i++){
  				if(opt[i].checked == true){
  					count++;
  				}
  			}
  			console.log(count);
  			if(count == opt.length){
  				selected.checked = true;
  			}else{
  				selected.checked = false;
  			}
  		}
  		function selectAll(){
  			opt = document.getElementsByName("opt");
  			selected = document.getElementById("all");
  			if(selected.checked == true){
	  			for(var i=0;i<opt.length;i++){
	  				opt[i].checked = true;
	  			}
  			}else{
	  			for(var i=0;i<opt.length;i++){
	  				opt[i].checked = false;
	  			}
  			}
  		}
  		function changeStatus(status){
  			var opt = document.getElementsByName("opt");
			var ids = new Array();
			var j = 0;
			for(var i=0;i<opt.length;i++){
				if(opt[i].checked == true){
					ids[j]=opt[i].getAttribute("id");
					j++;
				}
			}
  			if(j==0){
  				alert("还没有选择要改变的项");
  				return false;
  			}
  			var result = window.confirm("确认要改变吗?");
  			if(true == result){
  			console.log(ids);
  				$.ajax({
  				type:"POST",
  				url:"<%=basePath%>user/changeStatus.do",
  				data:{
  					  "ids": ids,
  					  "status": status
  					  },
  				traditional: true,
  				success:function(msg){
					if(msg=="success"){
						search(1);
					}else{
						alert("修改失败");
					}
  				}
  				});
  			}
  		}
  	</script>
  </head>
  
  <body>
    <div id="container"  >
		<div class="page-header">
			<h2>用户列表</h2>
		</div>
		<form action="" >
			<div class="row">
				<div class="col-xs-6 col-sm-3">
					<div class="row">
						<div class="col-xs-8 col-sm-3">
							<span class="btn btn-default">用户名</span>
						</div>
						<div class="col-xs-3 col-sm-8">
							<input id="username" type="text" class="form-control" />
						</div>
					</div>
				</div>
				<div class="col-xs-6 col-sm-3">
					<div class="row">
						<div class="col-xs-8 col-sm-4">
							<span class="btn btn-default">手机号码</span>
						</div>
						<div class="col-xs-3 col-sm-8">
							<input id="iphone" type="text" class="form-control" />
						</div>
					</div>

				</div>
				<div class="col-xs-6 col-sm-4">
					<input type="button" class="btn btn-primary"  value="查询" onclick="search(1)" />
				</div>
			</div>
		</form>
		
		
		
		
		<div class="row">&nbsp;</div>
		<div class="row">
			<div class="col-xs-6 col-sm-4">
				<a class="btn btn-success" onclick="changeStatus(1)"> <span class="glyphicon glyphicon-ok-sign"></span>
					恢复使用</a>
				
				<a class="btn btn-danger" onclick="changeStatus(2)"> <span
					class="glyphicon glyphicon-trash"></span> 禁止使用</a>
			</div>
		</div>

		<div class="row">&nbsp;</div>
		<div class="row">
			<div class="col-xs-6 col-sm-11">
				<div class="table-responsive"
					style="vertical-align: middle;text-align: center;">
					<table id="searchTable"
						class="table table-bordered table-hover table-condensed  ">
						<tr>
							<td><input type="checkbox" id="all" onclick="selectAll()" value="全选"
								/> 全选</td>
							<td>ID</td>
							<td>用户名</td>
							<td>手机账号</td>
							<td>状态</td>
							<td>创建时间</td>
						</tr>
<%--					<tr>
							<td><input type='checkbox' name="opt"/></td>
							<td>1</td>
							<td>111</td>
							<td>111</td>
							<td>keyong</td>
							<td>2017-05-01 12:06:09</td>
							</tr>
--%>
					</table>
		<input id="startPage" type="button" value="首页" onclick="search(1)">
  		<input id="lastPage" type="button" value="上一页">
  		<input id="nextPage" type="button" value="下一页">
  		<input id="endPage" type="button" value="末页">
				</div>
			</div>
		</div>
	</div>
  </body>
  <style>
td {
	vertical-align: middle !important;
}
</style>
</html>