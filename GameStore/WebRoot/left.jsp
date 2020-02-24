<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="zh-CN">
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'left.jsp' starting page</title>
    
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script src="<%=basePath%>js/jquery.js"></script>
    <link href="<%=basePath%>css/bootstrap.min.css" rel="stylesheet">
    <script src="<%=basePath%>js/bootstrap.min.js"></script>
  </head>
  
  <body>
    <div class="lefter">
		<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true"><div class="panel panel-default">
    			<div class="panel-heading"  >
      				<h4 class="panel-title">
      					<span class="glyphicon glyphicon-th-large" ></span> 菜单列表
      				</h4>
    			</div>
    			
 		 	</div>
  			<div class="panel panel-default">
    			<div class="panel-heading"  >
      				<h4 class="panel-title">
      					<a href="<%=basePath%>user/user.jsp" target="right"><span class="glyphicon glyphicon-user" ></span> 用户管理</a>
      				</h4>
    			</div>
    			
 		 	</div>
  <div class="panel panel-default">
    <div class="panel-heading"  >
      <h4 class="panel-title">
      	<a href="<%=basePath%>gameType/gameType.jsp" target="right"><span class="glyphicon glyphicon-th"></span> 游戏类型</a>
      </h4>
    </div>
   
  </div>
  <div class="panel panel-default">
    <div class="panel-heading">
      <h4 class="panel-title">
      	<a href="<%=basePath%>game/game.jsp" target="right"><span class="glyphicon  glyphicon-list"></span> 游戏列表</a>
      </h4>
    </div>
  </div>
   <div class="panel panel-default">
    <div class="panel-heading"  >
      <h4 class="panel-title">
      	<a href="<%=basePath%>exchange/exchange.jsp" target="right"><span class="glyphicon glyphicon-transfer"></span>换算比例</a>
      </h4>
    </div>
   
  </div>
  
  
   <div class="panel panel-default">
    <div class="panel-heading"  >
      <h4 class="panel-title">
      	<a href="<%=basePath%>card/card.jsp" target="right"><span class="glyphicon glyphicon-barcode"></span>密保卡管理</a>
      </h4>
    </div>
   
  </div>
  
  
  
  <div style="display:none" class="panel panel-default">
    <div class="panel-heading" role="tab" id="headingThree">
      <h4 class="panel-title">
        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapse5" aria-expanded="false" aria-controls="collapseThree">
          <span class=""></span> 预留
        </a>
      </h4>
    </div>
    <div id="collapse5" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingThree">
      <div class="panel-body">
      <ul class="nav nav-sidebar"> 
            <li ><a href="" target="right">权限管理</a></li>
            <li ><a href="" target="right">登录日志</a></li>
            <li ><a href="" target="right">操作日志</a></li>
          </ul>
      </div>
    </div>
  </div>
</div>
	</div>
	<script>
   $(function(){
      $('a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
      // 获取已激活的标签页的名称
      var activeTab = $(e.target).text(); 
      // 获取前一个激活的标签页的名称
      var previousTab = $(e.relatedTarget).text(); 
      $(".active-tab span").html(activeTab);
      $(".previous-tab span").html(previousTab);
   });});</script>
  </body>
</html>
