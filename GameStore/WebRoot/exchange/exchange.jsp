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
    
   <title>游币比例管理页面</title>
    
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
		var currentPage;
		var oldprovs = "";
		
		
  		function search(pageNo){
  			selected = document.getElementById("all");
  			selected.checked = false;
  			var provs=$("#province option:selected").val();
  			if(oldprovs != provs){
  				pageNo = 1;
  			}
  			oldprovs = provs;
  			$.ajax({
  				type:"POST",
  				url:"<%=basePath%>exchange/getExchangesByPage.do",
  				data:{"pageNo":pageNo,
  					  "prov": provs},
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
  						var prov = $("<td>"+datas[i].provinceName+"</td>");
  						var charge = $("<td>"+datas[i].charge+"</td>");
  						var date = datas[i].createTime;
  						var str = (date.year+1900)+"-"+(date.month+1 < 10 ? '0' + (date.month+1) : (date.month+1))+"-"+(date.date < 10 ? '0' + date.date : date.date)+" "+(date.hours < 10 ? '0' + date.hours : date.hours)+":"+ (date.minutes < 10 ? '0'+date.minutes : date.minutes) +":"+(date.seconds < 10 ? '0' + date.seconds : date.seconds);
						var createtime = $("<td>"+str+"</td>");
  						
  						date = datas[i].updateTime;
  						var str = (date.year+1900)+"-"+(date.month+1 < 10 ? '0' + (date.month+1) : (date.month+1))+"-"+(date.date < 10 ? '0' + date.date : date.date)+" "+(date.hours < 10 ? '0' + date.hours : date.hours)+":"+ (date.minutes < 10 ? '0'+date.minutes : date.minutes) +":"+(date.seconds < 10 ? '0' + date.seconds : date.seconds);
						var updatetime = $("<td>"+str+"</td>");
  						
  						var state = datas[i].exchangeStatus;
						var newState = "";
  						if(state == 1){
  							newState = "商用";
  						}else{
  							newState = "下线";
  						}
  						var status = $("<td>"+newState+"</td>");
  						
						var xiuGai = $('<td><a class="btn btn-info btn-sm" href="<%=basePath%>exchange/toModifyExchange.do?id='+datas[i].id+'"><span class="glyphicon glyphicon-pencil"></span>修改</a></td>');
						
  						tr.append(check).append(id).append(prov).append(charge).append(createtime).append(updatetime).append(status).append(xiuGai);
  						resultTable.append(tr);
  					}
  					for(;i<4;i++){
  						var tr = $("<tr><td>&nbsp;</td><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>");
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
  		$(document).ready(function(){
  			search(1);
  			getDownListProvs();
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
  		
  		
  		function deleteExchange(){
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
  				alert("还没有选择要删除的项");
  				return false;
  			}
  			var result = window.confirm("确认要删除吗?");
  			if(true == result){
  				$.ajax({
  				type:"POST",
  				url:"<%=basePath%>exchange/deleteExchanges.do",
  				data:{"ids": ids},
  				traditional: true,
  				success:function(msg){
					if(msg=="success"){
						search(1);
					}else{
						alert("删除失败");
					}
  				}
  			});
  			}
  		}
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
  	</script>
	
	
  </head>
  
  <body>
    <div id="container">
		<div class="page-header ">
			<h2>游币换算比例</h2>
		</div>
			<form action="" >
			<div class="row">
				<div class="col-xs-4 col-sm-3 " >
					<div class="row">
						<div class="col-xs-8 col-sm-2">
							<span class="btn btn-default">省份</span>
						</div>
						<div class="col-xs-3 col-sm-5">
							<select id="province" class="form-control">
									<option value="0">请选择</option>
										
							</select>
						</div>
						<div class="col-xs-6 col-sm-4 ">
					<input type="button" class="btn btn-primary"  onclick="search(1)" value="查询" />
				</div>
					</div>
				</div>
				
			</div>
		</form>
		<div class="row">&nbsp;</div>
		<div class="row">
			<div class="col-xs-6 col-sm-4">
				<a class="btn btn-primary" data-toggle="modal" href="<%=basePath%>exchange/updateExchange.jsp"
					> <span class="glyphicon glyphicon-plus"></span>
					新增</a>
				
				<a class="btn btn-danger" onclick="deleteExchange()"> <span
					class="glyphicon glyphicon-trash"></span> 删除</a>
				
			</div>
		</div>
		<div class="row">&nbsp;</div>
		<div class="row">
			<div class="col-xs-6 col-sm-11">
				<div class="table-responsive"
					style="vertical-align: middle;text-align: center;">
					<div class="panel-body"></div>
					<table id="searchTable"
						class="table table-bordered table-hover table-condensed  ">
						<tr>
							<td><input type="checkbox" id="all" onclick="selectAll()" value="全选"
								 /> 全选</td>
							<td>省份代码</td>
							<td>省份</td>
							<td>兑换比例</td>
							<td>创建时间</td>
							<td>修改时间</td>
							<td>规则状态</td>
							<td>操作</td>
						</tr>
						
					</table>
		<input id="startPage" type="button" value="首页" onclick="search(1)">
  		<input id="lastPage" type="button" value="上一页">
  		<input id="nextPage" type="button" value="下一页">
  		<input id="endPage" type="button" value="末页">
					
				</div>
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
