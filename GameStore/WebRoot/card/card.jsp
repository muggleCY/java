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
	<meta charset="utf-8">
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
	
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="description" content="">
	<meta name="author" content="">
	
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/bootstrap-select.css">

	<!-- 3.0 -->

	<link href="<%=basePath%>css/bootstrap.min.css" rel="stylesheet">
	<script src="<%=basePath%>js/jquery.js"></script>
	<script src="<%=basePath%>js/My97DatePicker/WdatePicker.js"></script>
	<script src="<%=basePath%>js/laydate/laydate.js"></script>
	<script src="<%=basePath%>js/bootstrap.min.js"></script>
	<style type="text/css">
	#container {
		margin-top: -20px;
		margin-left: 10px;
	}
</style>

<script type="text/javascript">
		var currentPage;
		var oldNum = "";
		var oldStartTime = "";
		var oldEndTime = "";
		var oldprovs = "";
		
		
  		function search(pageNo){
  			selected = document.getElementById("all");
  			selected.checked = false;
  			var cardNum = $("#cardNum").val();
  			var startTime = $("#cardTime1").val();
  			var endTime = $("#cardTime2").val();
  			var provId=$("#provId option:selected").val();
  			if(oldprovs != provId||oldNum != cardNum||oldStartTime!=startTime||oldEndTime!=endTime){
  				pageNo = 1;
  			}
  			oldprovs = provId;
  			oldNum = cardNum;
  			oldStartTime = startTime;
  			oldEndTime = endTime;
  			console.log("startTime :" + startTime);
  			$.ajax({
  				type:"POST",
  				url:"<%=basePath%>card/getCardsByPage.do",
  				data:{"pageNo":pageNo,
  					  "provId": provId,
  					  "cardNum": cardNum,
  					  "startTime": startTime,
  					  "endTime": endTime},
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
  						var num = $("<td>"+datas[i].cardNum+"</td>");
  						var pwd = $("<td>"+datas[i].cardPwd+"</td>");
  						var amount = $("<td>"+datas[i].cardAmount+"</td>");
  						var prov = $("<td>"+datas[i].provinceName+"</td>");
  						
  						var date = datas[i].startTime;
  						var str = (date.year+1900)+"-"+(date.month+1 < 10 ? '0' + (date.month+1) : (date.month+1))+"-"+(date.date < 10 ? '0' + date.date : date.date)+" "+(date.hours < 10 ? '0' + date.hours : date.hours)+":"+ (date.minutes < 10 ? '0'+date.minutes : date.minutes) +":"+(date.seconds < 10 ? '0' + date.seconds : date.seconds);
						var startTime = $("<td>"+str+"</td>");
  						
  						date = datas[i].endTime;
  						var str = (date.year+1900)+"-"+(date.month+1 < 10 ? '0' + (date.month+1) : (date.month+1))+"-"+(date.date < 10 ? '0' + date.date : date.date)+" "+(date.hours < 10 ? '0' + date.hours : date.hours)+":"+ (date.minutes < 10 ? '0'+date.minutes : date.minutes) +":"+(date.seconds < 10 ? '0' + date.seconds : date.seconds);
						var endTime = $("<td>"+str+"</td>");
  						
  						var state = datas[i].cardStatus;
						var newState = "";
  						if(state == 1){
  							newState = "正常";
  						}else if (state == 2) {
  							newState = "过期";
						}else if (state == 3){
  							newState = "未生效";
  						}else{
  							newState = "已使用";
  						}
  						var status = $("<td>"+newState+"</td>");
  						
  						date = datas[i].createTime;
  						var str = (date.year+1900)+"-"+(date.month+1 < 10 ? '0' + (date.month+1) : (date.month+1))+"-"+(date.date < 10 ? '0' + date.date : date.date)+" "+(date.hours < 10 ? '0' + date.hours : date.hours)+":"+ (date.minutes < 10 ? '0'+date.minutes : date.minutes) +":"+(date.seconds < 10 ? '0' + date.seconds : date.seconds);
						var createTime = $("<td>"+str+"</td>");
  						
  						tr.append(check).append(id).append(num).append(pwd).append(amount).append(prov).append(startTime).append(endTime).append(status).append(createTime);
  						resultTable.append(tr);
  					}
  					for(;i<4;i++){
  						var tr = $("<tr><td>&nbsp;</td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>");
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
		 function getDownListProvs(){
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
	  	            }
	  	        });
	  	    }
  	</script>



	
  </head>
  
  <body>
    <div>
		<div id="container">
			<div class="page-header ">
				<h2>密保卡管理</h2>
			</div>
			<form action="">
				<div class="row">
					<div class="col-xs-4 col-sm-12 ">
						<div class="row">
							<div class="col-xs-8 col-sm-2">
								<span class="btn btn-default">卡号 <input type="text" id="cardNum"/> </span>
							</div>
							<div class="col-xs-4 col-sm-3 ">
								<div class="row">
									<div class="col-xs-6 col-sm-4 ">
										<span  class="btn btn-default">有效期开始时间 <input
											type="text" id="cardTime1"  onclick="laydate()"/> <img
											src="<%=basePath%>js/My97DatePicker/skin/datePicker.gif" width="16"
											height="22" /> </span>
									</div>
								</div>
							</div>

							<div class="col-xs-4 col-sm-3 ">
								<div class="row">
									<div class="col-xs-8 col-sm-4">
										<span  class="btn btn-default">有效期结束时间 <input
											type="text" id="cardTime2" onclick="laydate()"/> <img
											src="<%=basePath%>js/My97DatePicker/skin/datePicker.gif" width="16"
											height="22"/> </span>
									</div>
								</div>
							</div>

							<div class="col-xs-2 col-sm-2 ">
								<div class="row">
									<div class="col-xs-8 col-sm-3">
										<span class="btn btn-default">省份</span>
									</div>
									<div class="col-xs-2 col-sm-8">
										<select id="provId" class="form-control">
												<option value="0">请选择</option>
												
										</select>
									</div>
								</div>
							</div>
							<div class="col-xs-4 col-sm-1 ">
								<div class="row">
									<div class="col-xs-6 col-sm-4 ">
										<input type="button" class="btn btn-primary" onclick="search(1)"
											value="查询" />
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				</form>
			<div class="row">&nbsp;</div>
			<div class="row">
				<div class="col-xs-6 col-sm-4">
					<a href="<%=basePath%>card/addCard.jsp" class="btn btn-primary">批量生成密保卡</a>
					
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
								<td><input type="checkbox" onclick="selectAll()" id="all" value="全选"
									 /> 全选</td>
								<td>ID</td>
								<td>卡号</td>
								<td>密码</td>
								<td>金额</td>
								<td>省份</td>
								<td>有效期开始时间</td>
								<td>有效期结束时间</td>
								<td>使用状态</td>
								<td>创建时间</td>
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
	</div>
  </body>
  <style>
		td {
			vertical-align: middle !important;
		}
   </style>
</html>
