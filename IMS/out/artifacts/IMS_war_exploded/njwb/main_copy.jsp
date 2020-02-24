﻿<%
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
			  <%--console.log(${sessionScope.user.roleId});--%>
			  var roleId = "${sessionScope.user.roleId}";
			  console.log(roleId);
			  var deptManagerHref = $("#dept_manager_href");
			  var emp_href = $("#emp_href");
			  console.log(deptManagerHref);
			  console.log(emp_href);
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
	    				<ul class="hide">
	    					<li class="menu-sub" ><a id="dept_manager_href" href="<%=basePath%>njwb/dept/dept.jsp" id="deptManager" target="contentPage">部门管理</a></li>
	    					<li class="menu-sub"><a  id="emp_href" href="<%=basePath%>njwb/dept/employee.jsp" id="empManager" target="contentPage">员工管理</a></li>
	    					<li class="menu-sub">请假管理</li>
	    				</ul>
	    			
	    			</li>
	    			
	    			<li class="menu">
	    				<span>财务管理</span>
	    				<ul  class="hide">
	    					<li class="menu-sub"><a href="<%=basePath%>njwb/fund/expense.jsp" target="contentPage">报销管理</a></li>
	    				</ul>
	    			
	    			</li>	    			
	    		    <li class="menu">
	    				<span>系统管理</span>
	    				<ul class="hide">
	    					<li class="menu-sub">账户维护</li>
	    					<li class="menu-sub">角色管理</li>
	    					<li class="menu-sub">权限管理</li>
	    					<li class="menu-sub"><a href="sys/reset.jsp" target="contentPage">密码重置</a></li>
	    					<li class="menu-sub" onclick="clearLoginState()">系统退出</li>
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
