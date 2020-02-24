<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
    <base href="<%=basePath%>">
    
   	<title>游戏类型后台管理系统</title>
    
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
   $(document).ready(function(){
		search();
	});
	function search(){
		var typeName = $("#typeName").val();
		var typeStatus = $("#typeStatus option:selected").val();
		if(typeStatus == 0){
			typeStatus = null;
		}
		$.ajax({
			type:"POST",
			url:"<%=basePath%>gameType/getTypes.do",
			data:{
				"typeName":typeName,
				"typeStatus":typeStatus,
			},
			success:function(msg){
				console.log(msg);
				var datas =msg;
				var searchTable = $("#searchTable");
				searchTable.html($("table tr")[0]);
					for(var i = 0; i < datas.length;i++){
						var tr = $("<tr></tr>");
						var checkbox=$("<td><input type='checkbox' value="+datas[i].id+" name='opt' onclick="+"checkOne() /></td>");
						var id = $("<td>"+datas[i].id+"</td>");
						var pic=$("<td><div class='col-xs-6 col-md-offset-4'><img class='img-responsive' width='33' height='30' src='<%=basePath%>img/cover/"+datas[i].typePicture+"'></img></div></td>");
						var date=datas[i].createTime;
						var str = (date.year+1900)+"-"+(date.month+1 < 10 ? '0' + (date.month+1) : (date.month+1))+"-"+(date.date < 10 ? '0' + date.date : date.date)+" "+(date.hours < 10 ? '0' + date.hours : date.hours)+":"+ (date.minutes < 10 ? '0'+date.minutes : date.minutes) +":"+(date.seconds < 10 ? '0' + date.seconds : date.seconds);
						var createTime=$("<td>"+str+"</td>");
						var date2 =datas[i].updateTime;
						var str2 = (date2.year+1900)+"-"+(date2.month+1 < 10 ? '0' + (date2.month+1) : (date2.month+1))+"-"+(date2.date < 10 ? '0' + date2.date : date2.date)+" "+(date2.hours < 10 ? '0' + date2.hours : date2.hours)+":"+ (date2.minutes < 10 ? '0'+date2.minutes : date2.minutes) +":"+(date2.seconds < 10 ? '0' + date2.seconds : date2.seconds);
						var updateTime=$("<td>"+str2+"</td>");
						var typeName = $("<td>"+datas[i].typeName+"</td>"); 
						var status = datas[i].typeStatus;
  						var newState = "";
  						if(status == 1){
  							newState = "商用";
  						}else{
  							newState = "下线";
  						}
						var typeStatus = $("<td>"+newState+"</td>"); 
						var href=$("<td><a class='btn btn-info' href='<%=basePath%>gameType/selectGameType.do?id="+datas[i].id+"'><span class='glyphicon glyphicon-pencil'>修改</span></a></td>");
						tr.append(checkbox).append(id).append(pic).append(typeName).append(typeStatus).append(createTime).append(updateTime).append(href);
						searchTable.append(tr);
						}
			}
		});
	}
	
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
	function check() {
		var name = $("#name").val();
		var file = $("#file").val();
		if(name == ""){
			alert("类型名不能为空");
			return false;
		}
		if(name.length > 20){
			alert("类型名过长");
			return false;
		}
		if((/[#%*._&^$@/\\()<>{}[\] ]/.test(name))){
			alert("类型名含有非法字符");
			return false;
		}
		if(file == null){
			alert("请上传文件");
			return false;
		}
		var type = file.substring(file.lastIndexOf(".")+1);
		console.log(type);
		console.log(type !="jpg");
		if(type !="jpg"&&type !="png"&&type !="bmp"){
			alert("请上传图片文件");
			return false;
		}
		return true;
	}
	function deleteGameType(){
		var opt = document.getElementsByName("opt");
		var ids = new Array();
		var j = 0;
		for(var i=0;i<opt.length;i++){
			if(opt[i].checked == true){
				ids[j]=opt[i].getAttribute("value");
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
	  				url:"<%=basePath%>gameType/deleteGameTypes.do",
	  				async:false,
	  				data:{
	  					"ids": ids
	  					},
	  				traditional: true,
	  				success:function(msg){
	  					console.log(msg);
						if(msg=="success"){
							search();
						}else{
							alert(msg);
						}
	  				}
	  			});
	  		}
		
		
	}
</script>
	</head>
	<body>
		<div id="container" class="col-sm-12">
			<div class="page-header">
				<h2>
					游戏类型
				</h2>
			</div>
			<form action="">
				<div class="row">
					<div class="col-xs-6 col-sm-3">
						<div class="row">
							<div class="col-xs-8 col-sm-3">
								<span class="btn btn-default">类型名称</span>
							</div>
							<div class="col-xs-3 col-sm-8">
								<input type="text"id="typeName" class="form-control" />
							</div>
						</div>
					</div>
					<div class="col-xs-6 col-sm-2">
						<div class="row">
							<div class="col-xs-8 col-sm-3">
								<span class="btn btn-default">状态</span>
							</div>
							<div class="col-xs-3 col-sm-8">
								<select id="typeStatus" class="form-control">
									<option value="0">请选择</option>
									<option value="1">商用</option>
									<option value="2">下线</option>
								</select>
							</div>
						</div>

					</div>
					<div class="col-xs-6 col-sm-4">
						<input type="button" class="btn btn-primary" value="查询"
							onclick="search()" />
					</div>
				</div>
			</form>
			<div class="row">
				&nbsp;
			</div>
			<div class="row">
				<div class="col-xs-6 col-sm-4">
					<a id ="addType" class="btn btn-primary" data-toggle="modal"
					data-target="#myModal"> <span class="glyphicon glyphicon-plus"></span>
					新增</a>
					<a class="btn btn-danger"  onclick="deleteGameType()">
						<span class="glyphicon glyphicon-trash"></span>
					删除</a>
				</div>
			</div>
			<div class="row">
				&nbsp;
			</div>
			<div class="row">
				<div class="col-xs-6 col-sm-12">
					<div class="table-responsive "
						style="vertical-align: middle; text-align: center;">
						<table id="searchTable"
							class="table table-bordered table-condensed ">
							<tr>
								<td>
									<input type="checkbox" id="all" value="全选" onclick="selectAll()" />
									全选
								</td>
								<td>
									ID
								</td>
								<td>
									类型图片
								</td>
								<td>
									类型名称
								</td>
								<td>
									状态
								</td>
								<td>
									创建时间
								</td>
								<td>
									修改时间
								</td>
								<td>
									操作
								</td>
							</tr>
						</table>
					</div>
				</div>
			</div>
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	  		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
			        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
			          <span aria-hidden="true">&times;</span>
			        </button>
					<h5 class="modal-title" id="exampleModalLabel">新增游戏类型</h5>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" action="<%=basePath%>gameType/insertGameType.do" 
					enctype="multipart/form-data" method="post"  >
						<div class="form-group">
							<label for="firstname" class="col-sm-2 control-label">类型名</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="name"
									name="typeName" placeholder="请输入游戏类型名称">
							</div>
						</div>
						<div class="form-group">
							<label for="firstname" class="col-sm-2 control-label">状态</label>
							<div class="col-sm-10">
								<label><input type="radio" name="typeStatus" value="1" checked>商用</label>
								<label><input type="radio" name="typeStatus" value="2">下线</label>
							</div>
						</div>
						<div class="form-group">
							<label for="lastname" class="col-sm-2 control-label">缩略图</label>
							<div class="col-sm-10">
								<div class="caption">
									<p>
										<input type="file" name="imgFile" id="file"/>
									</p>
								</div>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
								<button type="submit" class="btn btn-default" onclick="return check()">确认添加</button>
							</div>
						</div>
					</form>
				</div>

			</div>
		</div>
	</div>
   </div>
		<c:if test="${isError}">
			<script type="text/javascript">
				alert("${errorMessage}");
			</script>
		</c:if>
		
	</body>
	<style>
td {
	vertical-align: middle !important;
}
</style>

</html>
