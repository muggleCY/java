<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>首页</title>
	
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="this is my page">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/reset.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/main.css">
    <script type="text/javascript" src="<%=basePath%>js/jquery-1.8.3.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/main.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/layer/layer.js"></script>
    <style type="text/css">
  	.hide{
  		display: none;
  	}
    </style>
   <script type="text/javascript">
   	$(function(){
   		  //找到所有的li,且class=menu
   		  //alert($("li[class='menu']").length);
   		  
   		  
   		  //存在的问题：一级菜单和二级菜单能正常的显示与隐藏，但是当点击二级菜单，发现二级菜单也跟着隐藏
   		  
   		  //吸取一个经验：在网页元素排版时，要兼顾后期的js操作
   		  //一个合理的网页布局，会让js获取元素时非常遍历，否则就很痛苦
   		  /**
   		  $("li[class='menu']").each(function(){
   		  	  $(this).click(function(){
   		  	      $(this).children(".hide").slideToggle();
   		  	  });
   		  
   		  });
   		  */
   		  
   		  //对所有的span标签设置单击事件
   		  
   		  //alert($("span").length);  4个
   		  
   		  //alert($("li[class='menu'] span").length);
   		  
   		  $("li[class='menu'] span").each(function(){
   		  		$(this).click(function(){
   		  			  //this代表的是span
   		  			  $(this).siblings(".hide").slideToggle();
   		  		
   		  		});
   		  
   		  });


   		  $(document).ready(function () {
   		  	//页面加载完成之后,去取得当前登录的用户的角色id
			  var roleId = "${sessionScope.user.roleId}";

			  //未登录的拦截交给过滤器

			  //清空主菜单下的子菜单

			  var perManager = $("#per_manager");
			  var fundManager = $("#fund_manager");
			  var sysManager = $("#sys_manager");
			  perManager.html("");
			  fundManager.html("");
			  sysManager.html("");

			  //去后端请求该用户应有的菜单
			  $.ajax({
				  type:"POST",
				  url:"<%=basePath%>system/getMenuById.do",
				  data:{"roleId":roleId},
				  success:function (message) {
				  	//转换成json对象
				  	var data = JSON.parse(message);
					  for (var i = 0; i < data.length; i++) {
					  	// 先找parentId为1的,去添加到人事管理下
						  if (data[i].parentId==1){
						    var menu = $('<a target="contentPage"></a>');
						    var li = $('<li class="menu-sub"></li>');
						    menu.text(data[i].menuName);
						    menu.attr("href","<%=basePath%>"+data[i].hrefUrl);
						    li.append(menu);
						    perManager.append(li);
						  }
						  if (data[i].parentId==2){
							  var menu = $('<a target="contentPage"></a>');
							  var li = $('<li class="menu-sub"></li>');
							  menu.text(data[i].menuName);
							  menu.attr("href","<%=basePath%>"+data[i].hrefUrl);
							  li.append(menu);
							  fundManager.append(li);
						  }
						  if (data[i].parentId==3){
							  var menu = $('<a target="contentPage"></a>');
							  var li = $('<li class="menu-sub"></li>');
						  	// 如果是退出系统,需要添加点击时间
							  menu.text(data[i].menuName);
							  if (data[i].id ==12){
							  	menu.attr("onclick",data[i].hrefUrl);
							  }else {
								  menu.attr("href","<%=basePath%>"+data[i].hrefUrl);
							  }
							  li.append(menu);
							  sysManager.append(li);
						  }
					  }
				  }
			  });
		  });
	});
   
   </script> 
    
   
  </head>
  
  <body>
  	<div id = "mainDiv">
	  	<div id = "header">
	    	<div id = "logoDiv" class="lft">
	    		南京网博教育集团
	    	</div>
	    	<div id = "userDiv" class="rft">
	    		${sessionScope.user.userAccount}
	    	</div>
	    </div>
	    <div id = "welcomeDiv">
	    	欢迎使用网博管理系统
	    </div>
	    
	    
	    <div id = "contentDiv">
	    	<div id = "content-left" class="lft">
	    		<ul>
	    			<li class="menu">
	    				<span>人事管理</span>
	    				<ul class="hide" id="per_manager">
	    				</ul>
	    			</li>
	    			
	    			<li class="menu">
	    				<span>财务管理</span>
	    				<ul  class="hide" id="fund_manager">
	    				</ul>
	    			
	    			</li>	    			
	    		    <li class="menu">
	    				<span>系统管理</span>
	    				<ul class="hide" id="sys_manager">
	    				</ul>
	    			</li>
	    		</ul>
	    	</div>
	    	
	    	<div id = "content-right" class="rft">
	    		<iframe src="" name="contentPage" scrolling="yes" frameborder="0" width="788px" height="470px">
	    		</iframe>
	    	</div>
	    </div>
	    
	    <div id = "footer">
	    	<span>&copy;版权归属南京网博江北总部</span>
	    </div>
	</div>
   
  </body>
</html>
