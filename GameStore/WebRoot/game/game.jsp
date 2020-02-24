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
    
    <title>游戏列表</title>
    
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
		var oldname = "";
		var oldgametype = "";
		
		
  		function search(pageNo){
  			selected = document.getElementById("all");
  			selected.checked = false;
  			var gameName = $("#gameName").val();
  			var gameType=$("#gameType option:selected").val();
  			if(oldname != gameName||oldgametype != gameType){
  				pageNo = 1;
  			}
  			oldname = gameName;
  			oldgametype = gameType;
  			if((/[#%*._&^$@/\\()<>{}[\] ]/.test(gameName))){
  				alert("游戏名称含有非法字符");
  			}else{
  			$.ajax({
  				type:"POST",
  				url:"<%=basePath%>game/getGamesByPage.do",
  				data:{"pageNo":pageNo,
  					  "gameName": gameName,
  					  "gameType": gameType
  					  },
  				success:function(msg){
  					var pager = $.parseJSON(msg);
  					console.log(pager);
  					currentPage = pager;
  					var datas = pager.list;
  					console.log(datas);
  					var resultTable = $("#searchTable");
  					resultTable.html($("table tr")[0]);
  					for(var i = 0; i < datas.length;i++){
  						var tr = $("<tr></tr>");
  						var check = $("<td><input type='checkbox' id='"+datas[i].id+"' onclick='checkOne()' name='opt'/></td>");
  						
  						var id = $("<td>"+datas[i].id+"</td>");
  						var name = $("<td>"+datas[i].gameName+"</td>");
  						var gameType = $("<td>"+datas[i].typeName+"</td>");
  						var picture =$("<td><div class='col-md-offset-4'><img class='img-responsive' width='33' height='30' src='<%=basePath%>img/cover/"+datas[i].gameCover+"'></img></div></td>");
  						var state = datas[i].gameStatus;
  						var newState = "";
  						if(state == 1){
  							newState = "可用";
  						}else{
  							newState = "禁用";
  						}
  						var status = $("<td>"+newState+"</td>") 
  						
  						var currency = $("<td>"+datas[i].gameCurrency+"</td>");

  						var tariffe = $("<td>"+datas[i].gameTariffe+"</td>");
  						
  						
  						var date = datas[i].createTime;
  						var str = (date.year+1900)+"-"+(date.month+1 < 10 ? '0' + (date.month+1) : (date.month+1))+"-"+(date.date < 10 ? '0' + date.date : date.date)+" "+(date.hours < 10 ? '0' + date.hours : date.hours)+":"+ (date.minutes < 10 ? '0'+date.minutes : date.minutes) +":"+(date.seconds < 10 ? '0' + date.seconds : date.seconds);
						var createtime = $("<td>"+str+"</td>");
  						
  						date = datas[i].updateTime;
  						var str = (date.year+1900)+"-"+(date.month+1 < 10 ? '0' + (date.month+1) : (date.month+1))+"-"+(date.date < 10 ? '0' + date.date : date.date)+" "+(date.hours < 10 ? '0' + date.hours : date.hours)+":"+ (date.minutes < 10 ? '0'+date.minutes : date.minutes) +":"+(date.seconds < 10 ? '0' + date.seconds : date.seconds);
						var updatetime = $("<td>"+str+"</td>");
						var caoZuo = $('<td style="width: 200px"><div class="col-xs-6 col-sm-2 col-md-offset-1"><a class="btn btn-info btn-sm" href="<%=basePath%>game/gameDetail.do?id='+datas[i].id+'"><span class="glyphicon glyphicon-pencil"></span>详情</a></div><div class="col-xs-6 col-sm-2 col-md-offset-3"><a class="btn btn-info btn-sm" href="<%=basePath%>game/toModifyGame.do?id='+datas[i].id+'"><span class="glyphicon glyphicon-pencil"></span>修改</a></div></td>');
						
						tr.append(check).append(id).append(name).append(gameType).append(picture).append(status).append(currency).append(tariffe).append(createtime).append(updatetime).append(caoZuo);
  						resultTable.append(tr);
  					}
  					for(;i<4;i++){
  						var tr = $("<tr><td>&nbsp;</td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>");
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
  			//selectBox();
  			getDownListTypes();
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

  		
  		
  		function deleteGame(){
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
				url:"<%=basePath%>game/deleteGames.do",
				data:{
					"ids": ids
					},
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
  		 function getDownListTypes(){
  	    	$.ajax({
  	            type:"POST",
  	            url:"<%=basePath%>gameType/getTypes.do",
  	            success:function (message) {
  	                var list = message;
  	                var typeEle = $("#gameType");
  	                for (var i = 0; i < list.length; i++) {
  	                    var option = $('<option></option>');
  	                    option.attr("value",list[i].id);
  	                    option.text(list[i].typeName);
  	                  	typeEle.append(option);
  	                }
  	            }
  	        });
  	    }
  	</script>
	
  </head>
  
  <body>
    <div id="container">
		<div class="page-header">
			<h2>游戏列表</h2>
		</div>
		<form action="">
			<div class="row">
				<div class="col-xs-6 col-sm-3">
					<div class="row">
						<div class="col-xs-8 col-sm-3">
							<span class="btn btn-default">游戏名称</span>
						</div>
						<div class="col-xs-3 col-sm-8">
							<input type="text" name="gameName" id="gameName"
								class="form-control" />
						</div>
					</div>
				</div>
				<div class="col-xs-6 col-sm-3">
					<div class="row">
						<div class="col-xs-8 col-sm-3">
							<span class="btn btn-default">游戏类型</span>
						</div>
						<div class="col-xs-3 col-sm-8">
							<select name="gameType" id="gameType" class="form-control">
								
								<option value="0">请选择</option>
								
							</select>
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
				<a href="<%=basePath%>game/updateGame.jsp" class="btn btn-primary" > <span class="glyphicon glyphicon-plus"></span>
					新增</a>
				

				<a class="btn btn-danger"  onclick="deleteGame()" > <span
					class="glyphicon glyphicon-trash"></span> 删除</a>
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
							<td>游戏名称</td>
							<td>游戏类别</td>
							<td>游戏封面</td>
							<td>状态</td>
							<td>游币价格</td>
							<td>话费价格</td>
							<td>创建时间</td>
							<td>更新时间</td>
							<td style="width: 200px">操作</td>
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
