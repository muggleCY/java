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
    
    <title>游戏详情</title>
    
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
		
	</script>
  </head>
  
  <body>
    <div id="container">
		<div class="page-header text-center">
			<h2>游戏详情</h2>
		</div>
		<div class="row">
			<div class="col-xs-6 col-sm-4 col-md-offset-4">
				<form class="form-horizontal text-center" >
					<table class="table table-bordered table-condensed ">
						<thead></thead>
						<tbody>
							<tr>
								<td>游戏id</td>
								<td>
									<div class="col-sm-10">
										${game.id}
									</div></td>
							</tr>
							
							<tr >
								<td>游戏名称</td>
								<td>
									<div class="col-sm-10">
										${game.gameName}
									</div></td>
							</tr>
							
							<tr >
								<td>游戏类型</td>
								<td>
									<div class="col-sm-10">
										${gameType.typeName}
									</div></td>
							</tr>
							
							<tr >
								<td>游戏状态</td>
								<td>
									<div class="col-sm-10">
										<c:choose>
	  										<c:when test="${game.gameStatus == 1 }">
	  											商用
	  										</c:when>
	  										<c:when test="${game.gameStatus == 2 }">
	  											下线
	  										</c:when>
	   									</c:choose>
									</div></td>
							</tr>
							
							<tr >
								<td>游戏开发商</td>
								<td>
									<div class="col-sm-10">
										${game.gameDevelopers}
									</div></td>
							</tr>
							
							<tr >
								<td>游戏备案号</td>
								<td>
									<div class="col-sm-10">
										${game.gameFiling}
									</div></td>
							</tr>
							
							<tr >
								<td>游戏简介</td>
								<td>
									<div class="col-sm-10">
										${game.gameIntroduction}
									</div></td>
							</tr>
							
							<tr >
								<td>游戏详情</td>
								<td>
									<div class="col-sm-10">
										${game.gameDetail}
									</div></td>
							</tr>
							
							<tr >
								<td>游戏币价格</td>
								<td>
									<div class="col-sm-10">
										${game.gameCurrency}
									</div></td>
							</tr>
							
							<tr >
								<td>话费价格</td>
								<td>
									<div class="col-sm-10">
										${game.gameTariffe}
									</div></td>
							</tr>
							
							<tr >
								<td>游戏封面</td>
								<td>
									<div class="col-sm-10  col-md-offset-4">
										<img class="img-responsive" width="80"
											src="<%=basePath%>img/cover/${game.gameCover}" />
									</div></td>
							</tr>
							
							<tr >
								<td>游戏画面截图</td>
								<td>
									 <div class="col-sm-5  col-md-4">
     									<div class="thumbnail">
        									 <img class="img-responsive"   width="100"
											src="<%=basePath%>img/screen/${game.gameScreen1}" />
      									</div>
 									 </div>
 									 <div class="col-sm-4  col-md-4">
     									<div class="thumbnail">
										<img class="img-responsive"   width="100"   src="<%=basePath%>img/screen/${game.gameScreen2}"/>
      									</div>
 									 </div>
 									 <div class="col-sm-4  col-md-4">
     									<div class="thumbnail">
        									 <img class="img-responsive"    width="100"  
											src="<%=basePath%>img/screen/${game.gameScreen3}" />
      										
      									</div>
 									 </div>
								</td>
							</tr>
							
							<tr >
								<td>创建时间</td>
								<td>
									<div class="col-sm-10">
									<fmt:formatDate value="${game.createTime}" pattern="yyyy-MM-dd  hh:mm:ss"/>
										
									</div></td>
							</tr>
							
							<tr >
								<td>修改时间</td>
								<td>
									<div class="col-sm-10">
									<fmt:formatDate value="${game.updateTime}" pattern="yyyy-MM-dd  hh:mm:ss"/>
									
									</div></td>
							</tr>
						</tbody>
					</table>
					<div class="row">
						<div class="col-xs-6 col-sm-4">
							<div class="form-group">
								<div class="col-sm-offset-10 col-sm-11">
									<a class="btn btn-warning" href="<%=basePath%>game/game.jsp">返回上一页</a>
								</div>
							</div>
						</div>
					</div>
				</form>

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
