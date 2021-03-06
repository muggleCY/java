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
    
    <title>游币门户网站</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="description" content="">
	<meta name="author" content="">

	<link rel="stylesheet" href="<%=basePath%>css/bootstrap.min.css">
<link href="<%=basePath%>css/jquery-accordion-menu.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>css/font-awesome.css" rel="stylesheet" type="text/css" />
<script src="<%=basePath%>js/jquery.js"></script>
<script src="<%=basePath%>js/bootstrap.min.js"></script>
<script src="<%=basePath%>js/particles.js"></script>
<script src="<%=basePath%>js/app.js"></script>
<style type="text/css">
*{box-sizing:border-box;-moz-box-sizing:border-box;-webkit-box-sizing:border-box;}
.content{width:260px;margin:100px auto;}
.filterinput{
	background-color:rgba(249, 244, 244, 0);
	border-radius:15px;
	width:90%;
	height:30px;
	border:thin solid #FFF;
	text-indent:0.5em;
	font-weight:bold;
	color:#FFF;
}
#demo-list a{
	overflow:hidden;
	text-overflow:ellipsis;
	-o-text-overflow:ellipsis;
	white-space:nowrap;
	width:100%;
}
</style>
<style type="text/css">

* {
	padding: 0;
	margin: 0;
}

ul {
	list-style: none;
}

.out {
	width: 350px;
	height: 245px;
	margin: 25px auto;
	position: relative;
}

.img li {
	position: absolute;
	top: 0px;
	left: 0px;
	display: none
}

.out .num {
	position: absolute;
	bottom: 0px;
	left: 0px;
	font-size: 0px;
	text-align: center;
	width: 100%;
}

.num li {
	width: 20px;
	height: 20px;
	background: #666666;
	color: #FFFFFF;
	text-align: center;
	line-height: 20px;
	display: inline-block;
	font-size: 16px;
	border-radius: 50%;
	margin-right: 10px;
	cursor: pointer;
}

.out .btn {
	position: absolute;
	top: 52%;
	margin-top: -30px;
	width: 45px;
	height: 60px;
	background: rgba(0, 0, 0, 0.5);
	color: #FFFFFF;
	text-align: center;
	line-height: 60px;
	font-size: 40px;
	display: none;
	cursor: pointer;
}

.out .num li.active-1 {
	background: #AA0000;
	margin-left:150px;
}

.out:hover .btn {
	display: block
}

.out .left {
	left: 0px;
}

.out .right {
	right: -150px;
}
#leftNav{
	margin-left:87px;
}
</style>
<script src="<%=basePath%>js/jquery-accordion-menu.js" type="text/javascript"></script>
	<script type="text/javascript">
	
	$(document).ready(function(){
		searchGameTypes();
		searchGames(1,"");
		});
	
	function searchGameTypes(){
		$.ajax({
			type:"POST",
			url:"<%=basePath%>gameType/getTypesForUser.do",
			success:function(msg){
				var datas = msg;
				var demoList=$("#demo-list");
				var nav=$("#nav");
				var li1 = $("<li class=><a href='<%=basePath%>view/userProtal.jsp' class='btn btn-primary'>首页</a></li>");
				nav.append(li1);
				for(var i = 0; i < datas.length;i++){
					var li3=$("<li><a class='btn btn-primary' onclick=searchGames(1,\""+datas[i].id+"\")"+">"+datas[i].typeName+"</a></li>");
					nav.append(li3);
				}
				var li4=$("<li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</li>");
				nav.append(li4);
				
				var li5=$("<li></li>");
				var select=$("<select id='gameType' class='form-control'></select>");
				var option=$("<option name='gameType' value='0' >"+"请选择"+"</option>");
				select.append(option);
				for(var i=0;i<datas.length;i++){
					var noTd =$("<option name='gameType' value="+datas[i].id+">"+datas[i].typeName+"</option>");
					select.append(noTd);
				}
				li5.append(select);
				nav.append(li5);
				
				var li6=$("<li><input id='gameName' name='gameName' type='text' class='form-control'/></li>");
				nav.append(li6);
				
				var li7=$("<li><a class='btn btn-primary' onclick=searchGames(1,\"\")"+">查询</a></li>");
				nav.append(li7);
				
				for(var i = 0; i < datas.length;i++){
					if(datas.length < 4){
						var li8=$("<li><a onclick=searchGames(1,\""+datas[i].id+"\")><img width='50' src = '<%=basePath%>img/cover/"+datas[i].typePicture+"'>"+datas[i].typeName+"</img></a></li>");
						demoList.append(li8);
					}else{
						if(i == 3){
							var demoLi = $("#demo-li");
							demoLi.append($("<a href='javascript:void(0)'><i class='fa fa-suitcase'></i>更多类型<span class='submenu-indicator'>+</span></a>"));
							var submenu = $("#submenu");
							for(var i = 3; i < datas.length;i++){
								var li10=$("<li><a onclick=searchGames(1,\""+datas[i].id+"\")><img width='50' src = '<%=basePath%>img/cover/"+datas[i].typePicture+"'>"+datas[i].typeName+"</img></a></li>");
								submenu.append(li10);
							}
							demoLi.append(submenu);
							demoList.append(demoLi);
							return false;
						}
						var li8=$("<li><a onclick=searchGames(1,\""+datas[i].id+"\")><img width='50' src = '<%=basePath%>img/cover/"+datas[i].typePicture+"'>"+datas[i].typeName+"</img></a></li>");
						demoList.append(li8);
					}
				}
				
			}
		});
	}
	var oldName = "";
	function searchGames(pageNo,type){
		var gameShow=$("#gameShow");
		gameShow.empty();
		var gameType=$("#gameType").val();
		var gameName=$("#gameName").val();
		//当是搜索按钮触发此函数，type为下拉框选中的游戏类型
		if(type==undefined){
			type=0;
		}
		if(gameType == undefined){
			gameType=0;
		}
		if(type==""){
			type=gameType;
		}
		//搜索框的值发生改变时，换页不受影响，换页时仍旧采用之前的搜索条件
		//只有点击搜索按钮，新条件才生效!!!
		if(type!=""&& oldName != gameName){
			gameName=oldName;
		}
		oldName=gameName;
		console.log(gameName);
		var imgLi1 = $("#imgLi1");
		var imgLi2 = $("#imgLi2");
		var imgLi3 = $("#imgLi3");
		var imgLi4 = $("#imgLi4");
		$.ajax({
			type:"POST",
			url:"<%=basePath%>game/getGameForUser.do",
			data:{
				"pageNo":pageNo,
				"gameType":type,
				"gameName":gameName,
			},
			success:function(msg){
				var pager = $.parseJSON(msg);
				console.log(pager);
				var datas = pager.list;
				if(datas.length == 0){
					var div = $("<div class='col-md-4 col-sm-6' style='height: 300px;margin-left:300px;line-height:300px;'>该类型下无游戏</div>");
					gameShow.append(div);
				}
				for(var i=0;i<datas.length;i++){
					if(i == 0){
						imgLi1.empty();
						imgLi2.empty();
						imgLi3.empty();
						imgLi4.empty();
						var imgA1 = $("<a href='<%=basePath%>game/gameDetailForUser.do?id="+datas[i].id+"'></a>");
						var imgI = $("<img src='<%=basePath%>img/cover/"+datas[i].gameCover+"' width='500' height='300' alt='' />");
						imgA1.append(imgI);
						imgLi1.append(imgA1);
						
						var imgA2 = $("<a href='<%=basePath%>game/gameDetailForUser.do?id="+datas[i].id+"'></a>");
						var imgI2 = $("<img src='<%=basePath%>img/screen/"+datas[i].gameScreen1+"' width='500' height='300' alt='' />");
						imgA2.append(imgI2);
						imgLi2.append(imgA2);
						
						var imgA3 = $("<a href='<%=basePath%>game/gameDetailForUser.do?id="+datas[i].id+"'></a>");
						var imgI3 = $("<img src='<%=basePath%>img/screen/"+datas[i].gameScreen2+"' width='500' height='300' alt='' />");
						imgA3.append(imgI3);
						imgLi3.append(imgA3);
						
						var imgA4 = $("<a href='<%=basePath%>game/gameDetailForUser.do?id="+datas[i].id+"'></a>");
						var imgI4 = $("<img src='<%=basePath%>img/screen/"+datas[i].gameScreen3+"' width='500' height='300' alt='' />");
						imgA4.append(imgI4);
						imgLi4.append(imgA4);
						
					}
					var resultDiv = $('<div id="resultDiv" style="display: inline-block;" class="col-md-3 col-sm-6 "></div>');
					var div = $('<div class="thumbnail" style="display: inline-block;"></div>');
					//var div=$("<div class='thumbnail' class='col-md-4 col-sm-6'></div>");
					var img=$("<img src=<%=basePath%>img/cover/"+datas[i].gameCover+" width=150px height=150px></img>");
					var subDiv=$("<div class='caption'></div>");
					var h3=$("<h3>"+datas[i].gameName+"</h3>");
					var p=$("<p></p>");
					var a=$("<a class='btn btn-primary' href='<%=basePath%>game/gameDetailForUser.do?id="+datas[i].id+"'>游戏详情</a>");
					p.append(a);
					subDiv.append(h3).append(p);
					div.append(img).append(subDiv);
					resultDiv.append(div);
					gameShow.append(resultDiv);
				}
				var startPage = $("#startPage");
				startPage.attr("onclick","searchGames("+1+",'"+gameType+"')");
				var lastPage = $("#lastPage");
				lastPage.attr("onclick","searchGames("+(pager.pageNo - 1)+",'"+gameType+"')");
				var nextPage = $("#nextPage");
				nextPage.attr("onclick","searchGames("+(pager.pageNo + 1)+",'"+gameType+"')");
				var endPage = $("#endPage");
				endPage.attr("onclick","searchGames("+pager.totalPage+",'"+gameType+"')");
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
				//清空的目的，防止按钮查询后，条件仍然存在，妨碍其他两处的点击查询
				$("#gameType").val(0);
				$("#gameName").val("");
			}
		})
	}
	function check(){
		var userId = $("#userId").val();
		console.log(userId);
		var cardNum = $("#cardNum").val();
		var cardPwd = $("#cardPwd").val();
		if(cardNum==""){
			alert("卡号不能为空");
		}else if(cardPwd==""){
			alert("密码不能为空");
		}else{
			$.ajax({
				type:"POST",
				data:{"userId": userId,
					  "cardNum":cardNum,
				      "cardPwd":cardPwd},
				url:"<%=basePath%>prepaid/addPrepaid.do",
				success: function(msg){
					if(msg == "success"){
						var close = $("#close");
						alert("充值成功！可到用户消费记录中查看本次充值，祝您购物愉快");						
						close.click();
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
	 <div class="container">
		<nav class="navbar navbar-default" >
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#example-navbar-collapse">
					<span class="sr-only">切换导航</span> <span class="icon-bar"></span> <span
					class="icon-bar"></span> <span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="<%=basePath%>view/userProtal.jsp">游币手机下载门户网站</a>
			</div>
			<div class="collapse navbar-collapse" id="example-navbar-collapse">
				<ul class="nav navbar-nav">
					
				</ul>
				<ul class="nav navbar-nav navbar-right">
				<c:if test="${not empty user.username}">
					<li class="dropdown"><a href="#">欢迎${user.username }</a>
					</li>
				</c:if>
				<c:if test="${empty user.username}">
					<li class="dropdown"><a href="<%=basePath%>view/userLogin.jsp" >登录</a>
					</li>
					<li class="dropdown"><a href="<%=basePath%>view/userRegister.jsp" >注册</a>
					</li>
				</c:if>
				
					<li class="dropdown"><a href="#" class="dropdown-toggle "
						data-toggle="dropdown">个人中心<b class="caret"></b> </a>
						<ul class="dropdown-menu">
						<c:if test="${not empty user.username}">
							<li><a data-toggle="modal" data-target="#myModal">密保卡充值</a></li>
							<li><a href="<%=basePath%>view/userPrepaid.jsp">充值记录</a></li>
							<li><a href="<%=basePath%>view/Detail.jsp">账户信息</a></li>
							<li><a href="<%=basePath%>view/userExpense.jsp">消费记录</a></li>
							<li class="divider"></li>
							<li><a href="<%=basePath%>user/exit.do">退出</a></li>
						</c:if>
						<c:if test="${empty user.username}">
							<li class="divider"></li>
							<li><a href="<%=basePath%>user/exit.do">退出</a></li>
						</c:if>
						</ul>
					</li>
					
				</ul>
			</div>
		</nav>
	</div>
	<div class="modal fade" id="myModal" tabindex="-1" >
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close"  id="close" data-dismiss="modal"
									aria-hidden="true">&times;</button>
								<h4 class="modal-title" id="myModalLabel">密保卡充值</h4>
							</div>
							<div class="modal-body">
								<form class="form-horizontal" action="" 
								enctype="multipart/form-data" method="post"  >
									<div class="form-group">
										<label for="firstname" class="col-sm-2 control-label">卡号</label>
										<div class="col-sm-10">
											<input type="text" class="form-control" id="cardNum"
												name="cardNum" placeholder="请输入卡号">
												<input type="hidden" id="userId" name="userId" value="${user.id}"/>
										</div>
									</div>
									<div class="form-group">
										<label for="lastname" class="col-sm-2 control-label">密码</label>
										<div class="col-sm-10">
												<input type="text" class="form-control"  placeholder="请输入卡密码" id="cardPwd" name="cardPwd" />
										</div>
									</div>
									<div class="form-group">
										<div class="col-sm-offset-2 col-sm-10">
											<button type="button" class="btn btn-default" onclick="check()">确认充值</button>
										</div>
									</div>
								</form>
							</div>

						</div>
					</div>
				</div>
				
				
	<div class="row" style="background-color:#CCC; 
	overflow:hidden;" onLoad="init()">
		<div class="col-md-12">
			<ul class="nav nav-pills col-md-offset-3" id="nav" >
			
				
    		</ul>
		</div>
    </div>
    
    
	<div id="nail1" class="container">
		<div class="row">
			<div class="col-md-6 ">
				<div class="out">
					<!--上面-->
					<ul class="img" id="img-ul">
						<li id="imgLi1"></li>
						<li id="imgLi2"></li>
						<li id="imgLi3"></li>
						<li id="imgLi4"></li>
						<!-- 
							<li>
								<a href="">
								<img src="../images/cover/1430727532556.jpg" width="500"
								height="300" alt="" />
								</a>
							</li>
							<li>
								<a href="">
								<img src="../images/cover/1430727532556.jpg" width="500"
								height="300" alt="" />
								</a>
							</li>
							<li>
								<a href="">
								<img src="../images/cover/1430727532556.jpg" width="500"
								height="300" alt="" />
								</a>
							</li>
							<li>
								<a href="">
								<img src="../images/cover/1430727532556.jpg" width="500"
								height="300" alt="" />
								</a>
							</li>
						 -->
					</ul>
					<!--下面-->
					<ul class="num">
					</ul> 
					<div class="left btn">&lt;</div>
					<div class="right btn">&gt;</div>
				</div>
			</div>
			<div class="col-md-5 col-md-offset-1">
				
				<div class="row">&nbsp;</div>
				<table class="table">
   					<thead>
     				 	<tr>
         			 		<th>新闻公告</th>
      				 	</tr>
   					</thead>
   					<tbody>
      					<tr>
        					<td><a href="#">劳动节优惠先知</a></td>
      					</tr>
      					<tr>
         					<td><a href="#">情人节优惠放送</a></td>
      					</tr>
      					<tr>
         					<td><a href="#">定时充值返现活动</a></td>
      					</tr>
      					<tr>
         					<td><a href="#">体育竞技类游戏更新</a></td>
      					</tr>
      					<tr>
         					<td><a href="#">棋牌类游戏折扣活动</a></td>
      					</tr>
      					<tr>
         					<td><a href="#">直辖市优惠活动</a></td>
      					</tr>
      					<tr>
         					<td>&nbsp;</td>
         					<td><a href="#">更多</a></td>
      					</tr>
   					</tbody>
   				</table>
			</div>
		</div>
		<div class="row">&nbsp;</div>
		<div class="row">&nbsp;</div>
		<div  class="row">
			<div class="col-md-12 ">
				<div id="leftNav" class="col-md-2">
				<div id="jquery-accordion-menu" class="jquery-accordion-menu red">
		<div class="jquery-accordion-menu-header" id="form"></div>
		<ul id="demo-list">
		
			<li id="demo-li">
				<ul id="submenu" class="submenu"></ul>
			</li>
		</ul>
		
		<div class="jquery-accordion-menu-footer">
			Footer
		</div>
	</div>
				</div>
				<div id="nail2" class="col-md-8 col-md-offset-1">
						<div class="row" id="gameShow"><div class="col-md-6"><h2>&nbsp;&nbsp;</h2></div></div>
						<!-- 
						<div class="col-md-4 col-sm-6 ">
							<div class="thumbnail">
								<img src="${pageContext.request.contextPath}/images/cover/" width="150" height="150px" alt="">
								<div class="caption"><h3>密室逃脱</h3>
									<p><a href="gameDetail.html" class="btn btn-primary">游戏详情</a></p>
								</div>
							</div>
						</div>
						 -->
					
							<!-- <div class="col-md-4 col-sm-6 ">该游戏类型下没有提供可下载游戏</div> -->
						
					
				</div>
					<div style="padding-left:600px;">
						<input id="startPage" type="button" value="首页" onclick="search(1)">
					  	<input id="lastPage" type="button" value="上一页">
					  	<input id="nextPage" type="button" value="下一页">
						<input id="endPage" type="button" value="末页">
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


<script type="text/javascript">
$(function(){
	
	$("#123").click(function(){
		$("#myModal").modal('hide');
	});
});

(function($) {
$.expr[":"].Contains = function(a, i, m) {
	return (a.textContent || a.innerText || "").toUpperCase().indexOf(m[3].toUpperCase()) >= 0;
};
function filterList(header, list) {
	//@header 头部元素
	//@list 无需列表
	//创建一个搜素表单
	var form = $("<form>").attr({
		"class":"filterform",
		action:"#"
	}), input = $("<input>").attr({
		"class":"filterinput",
		type:"text"
	});
	$(form).append(input).appendTo(header);
	$(input).change(function() {
		var filter = $(this).val();
		if (filter) {
			$matches = $(list).find("a:Contains(" + filter + ")").parent();
			$("li", list).not($matches).slideUp();
			$matches.slideDown();
		} else {
			$(list).find("li").slideDown();
		}
		return false;
	}).keyup(function() {
		$(this).change();
	});
}
$(function() {
	filterList($("#form"), $("#demo-list"));
});
})(jQuery);	
jQuery(document).ready(function () {
	jQuery("#jquery-accordion-menu").jqueryAccordionMenu();
	
});

$(function(){	
	//顶部导航切换
	$("#demo-list li").click(function(){
		$("#demo-list li.active").removeClass("active");
		$(this).addClass("active");
	});	
})	;
$(".classname img").addClass("carousel-inner img-responsive img-rounded");

</script>

</body>

</html>
