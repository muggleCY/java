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
	<script src="<%=basePath%>js/jquery.js"></script>
	<script src="<%=basePath%>js/My97DatePicker/WdatePicker.js"></script>
	<script src="<%=basePath%>js/laydate/laydate.js"></script>
	<link href="<%=basePath%>css/bootstrap.min.css" rel="stylesheet">
	<script src="<%=basePath%>js/bootstrap.min.js"></script>
	<style type="text/css">
	#container {
		margin-top: -20px;
		margin-left: 10px;
	}
	</style>

<link href="css/doubleDate.css" rel="stylesheet" type="text/css" />

<style type="text/css">
*{margin:0;padding:0;list-style-type:none;}
a,img{border:0;}
body{font:12px/180% Arial, Helvetica, sans-serif, "新宋体";}


.iptgroup{width:620px;height:60px;margin:20px auto 0 auto;}
.iptgroup li{float:left;height:30px;line-height:30px;padding:5px;}
.iptgroup li .ipticon{background:url(blue/date_icon.gif) 98% 50% no-repeat;border:1px #CFCFCF solid;padding:3px;}
</style>
	
<script type="text/javascript">
	function check(){
		var number = $("#number").val();
		if(number==""){
			alert("数量不能为空");
			return false;
		}
		if(!/^[1-9]\d*$/.test(number)){
			alert("数量必须是大于0的整数");
			return false;
		}
		
		var provs = $("#provId option:selected").val();
		console.log(provs);
		if(provs == undefined){
			alert("省份不能为空");
			return false;
		}
		
		var amount = $("#cardAmount").val();
		if(amount==""){
			alert("金额不能为空");
			return false;
		}
		if(!/^[1-9]\d*$/.test(amount)){
			alert("金额必须是大于0的正整数");
			return false;
		}
		
		var startTime = $("#startTime").val();
		if(startTime==""){
			alert("时间不能为空");
			return false;
		}
		var endTime = $("#endTime").val();
		if(endTime==""){
			alert("时间不能为空");
			return false;
		}
		var startDate = new Date(startTime);
		var endDate = new Date(endTime);
		var nowDate = new Date();
		if(startDate.getTime() ==  endDate.getTime()){
			alert("结束时间不能等于开始时间");
			return false;
		}
		if(startDate.getTime()>endDate.getTime()){
			alert("结束时间不能早于开始时间");
			return false;
		}
		if(startDate.getTime() < nowDate.getTime()-24*60*60*1000){
			alert("开始时间不能晚于今天");
			return false;
		}
		if(endDate.getTime() < nowDate.getTime()){
			alert("结束时间不能早于今天");
			return false;
		}
		return true;
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
	 $(document).ready(function(){
			getDownListProvs();
		});
	</script>
	
	
	
	
</head>
  
  <body>
    <div>
		<div id="container">
			<div class="page-header ">
				<h2>密保卡生成</h2>
			</div>
			<div class="row">
				<div class="col-xs-6 col-sm-4">
								<div class="modal-body">
									<form class="form-horizontal" action="<%=basePath%>card/addCard.do">
										
										<table class="table table-bordered table-hover table-condensed  ">
											<tbody>
												<tr>
													<td>数量(张)</td>
													<td><input id="number" name="number" type="text"  placeholder="必须输入整数"  /></td>
												</tr>
												<tr>
													<td>选择需要生成卡的省份</td>
													<td>
													<select id="provId" name="provId"  
														class="selectpicker bla bla bli" multiple data-live-search="true">
													<optgroup>
														
													</optgroup>
												</select></td>
												</tr>
												<tr>
													<td>金额(游币数量)</td>
													<td><input id="cardAmount" name="cardAmount" type="text"  placeholder="必须输入整数"
														/></td>
												</tr>
												<tr class="iptgroup">
												  	<td>有效期开始时间<input id="startTime" name="startTime" type="text"  readonly="readonly" class="doubledate ipticon"  onclick="laydate()"/></td>
												  	<td>有效期结束时间<input id="endTime" name="endTime" type="text"  readonly="readonly" class="doubledate ipticon"  onclick="laydate()"/></td>
												</tr>
											</tbody>
										</table>


										<div class="form-group">
											<div class="col-sm-offset-2 col-sm-10">
												<button type="submit" class="btn btn-primary" onclick="return check()">生成密保卡</button>
												
									<a href="<%=basePath%>card/card.jsp" class="btn btn-warning" >返回上一页</a>
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
