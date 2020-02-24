<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>游币游戏详情</title>
    
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
	  function buyGame() {
		  if($("#userId").val() == ""){
			  alert("请登录再购买");
		  }else{
			var result = window.confirm("确认要购买吗?");
			if(true == result){
				var payWay = $("#payWay option:selected").val();
				var userId = $("#userId").val();	
				var gameId = $("#gameId").val();
				$.ajax({
					type:"POST",
					url:"<%=basePath%>expense/buyGame.do",
					data:{"payWay":payWay,
						  "userId":userId,
						  "gameId":gameId},
					success: function(msg){
						if(msg == "success"){
							alert("购买成功");
							window.location.reload();
						}else{
							alert(msg);
						}
					}
				});
			}
		  }
		}
	  function download(){
			var result = window.confirm("确认要下载吗");
			if(true == result){
				var userId = $("#userId").val();	
				var gameId = $("#gameId").val();
				$.ajax({
					type:"POST",
					url:"<%=basePath%>game/download.do",
					data:{
						  "userId":userId,
						  "gameId":gameId
						  },
					success: function(msg){
						if(msg == "success"){
							alert("下载成功");
							window.location.reload();
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
  <c:import url="../head.jsp"></c:import>
    <div class="modal fade" id="myModal" tabindex="-1" >
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal"
									>&times;</button>
								<h4 class="modal-title" id="myModalLabel">密保卡充值</h4>
							</div>
						</div>
					</div>
				</div>
    
   
	<div class="row">&nbsp;</div>
		<div class="container">
		<form action="<%=basePath%>user/buyGame.do" method="post">
		<div class="row">
			<div class="col-md-4 col-sm-4">
				<img
					src="<%=basePath%>img/cover/${game.gameCover}"
					width="450px;" alt="" />
			</div>
			<div class="col-md-6 col-md-offset-1">
				<p>
					<strong>游戏名称:</strong>${game.gameName}
				</p>
				<p>
					<strong>游戏类型:</strong>${gameType.typeName}
				</p>
				<p>
					<strong>话费价格:</strong>${game.gameTariffe}
				</p>
				<p>
					<strong>游币价格:</strong>${game.gameCurrency}(单位:游币)
				</p>
				<p>
					<strong>开发商:</strong>${game.gameDevelopers}
				</p>
				<p>
					<strong>备案号:</strong>${game.gameFiling}
				</p>
				<p>
					<strong>游戏简介:</strong>${game.gameIntroduction}
				</p>
				<p>
					<strong>游戏状态:</strong>
					<c:choose>
	  					<c:when test="${game.gameStatus == 1 }">
	  							商用
	  					</c:when>
	  					<c:when test="${game.gameStatus == 2 }">
	  							下线
	  					</c:when>
	   			    </c:choose>	
				</p>
				<p>
					<strong>最新发布时间:</strong>
					<fmt:formatDate value="${game.updateTime}" pattern="yyyy-MM-dd : hh mm ss"/>
				</p>
				
				<p>
					<strong>支付方式:</strong>
						<select id="payWay" name="payWay">
							<option value="1" selected="selected">话费支付</option>
							<option value="2">游币支付</option>
						</select>
						<input type="hidden" id="userId" name="userId" value="${user.id}">
						<input type="hidden" id="gameId" name="gameId" value="${game.id}">
				</p>
				<p>
					<c:if test="${empty expense.id}">
						<input type="button"  onclick="buyGame()" class="btn btn-success" value="确认购买">
					</c:if>
					<c:if test="${not empty expense.id}">
						<input type="button"  onclick="download()" class="btn btn-success" value="点击下载">
					</c:if>
					<!-- 
							<c:forEach items="${expenses}" var="expense">
								<c:if test="${game.id == expense.gameId}">
									<c:if test="${user.id == expense.userId}">
										style="display: none;"	
						  			</c:if>
					  			</c:if>
  							</c:forEach>
					 class="btn btn-success" value="确认购买">		


							<c:forEach items="${expenses}" var="expense">
								<c:if test="${game.id == expense.gameId}">
									<c:if test="${user.id == expense.userId}">
										<a href="<%=basePath %>game/download.do?gameId=${game.id}" class="btn btn-success">点击下载</a>
						  			</c:if>
					  			</c:if>
  							</c:forEach>

					 -->

					<a class="btn btn-warning" href="<%=basePath %>view/userProtal.jsp">返回首页</a>
				</p>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12">
				<div class="panel panel-info">
					<div class="panel-heading">
						<h3 class="panel-title">游戏简介</h3>
					</div>
					<div class="panel-body">${game.gameIntroduction}</div>
				</div>
			</div>

		</div>
		<div class="row col-md-12">
				<div class="panel panel-warning "style="height: 600">
			<div class="panel-heading" >
						<h3 class="panel-title">游戏画面截图</h3>
					</div><div class="panel-body">
<!-- 			<div class="carousel content-main"> -->
				
			
				
			<div class="col-md-6 ">
				<div class="out">
					<!--上面-->
					<ul class="img">
						
							<li>
								<a href="">
								<img src="<%=basePath%>img/screen/${game.gameScreen1}" width="900"
								height="500" alt="" />
								</a>
							</li>
							<li>
								<a href="">
								<img src="<%=basePath%>img/screen/${game.gameScreen2}" width="900"
								height="500" alt="" />
								</a>
							</li>
							<li>
								<a href="">
								<img src="<%=basePath%>img/screen/${game.gameScreen3}" width="900"
								height="500" alt="" />
								</a>
							</li>
					</ul>
					<!--下面-->
					<ul style="margin-bottom: -250px;margin-left: 200px" class="num">
					</ul> 
					<div style="margin-top: 80px" class="left btn">&lt;</div>
					<div style="margin-top: 80px;margin-right: -400px" class="right btn">&gt;</div>
				</div>
			</div>
	</div>
</div>
		</div>
</form>
	</div>
	<div class="row">
	<div class="container">
		<hr/>
		<div class="col-md-4 col-md-offset-5">
		&copy;2016-2017 portal.com 版权所有
		</div>
	</div>	
</div>
  <c:if test="${isError}">
     	 <script type="text/javascript">
     	 	alert("${errorMessage}");
     	 </script>
     </c:if>   


  </body>
</html>

