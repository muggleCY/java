<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>用户登录</title>
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="this is my page">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/reset.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/login.css">
   <style type="text/css">
   body{
   	 background-color: #0070A2;
   }
   </style>
	  <script src="<%=basePath%>js/jquery-1.8.3.min.js"></script>
	  <script src="<%=basePath%>js/login.js"></script>
	  <script src="<%=basePath%>js/layer/layer.js"></script>
	  <script src="<%=basePath%>js/reg.js"></script>
	  <script>
		  $(document).ready(function () {
			  var info_tip = "${sessionScope.info}";
			  if (info_tip!=""){
				  layer.msg(info_tip);
			  }
		  });
	  </script>
  </head>
  
  <body>
     <div id = "login">
     	  <div id = "title">
     	  		NJWB管理系统
     	  </div>
     	  
     	  <table id="loginTable">
     	  		<tr>
     	  			<td>用户名:&nbsp;</td>
     	  			<td>
     	  				<input type= "text" name = "userName" id = "userName" class="inputVal"/>
     	  			</td>
     	  			<td>&nbsp;</td>
     	  		</tr>
     	  		<tr>
     	  			<td>密&nbsp;&nbsp;&nbsp;码:&nbsp;</td>
     	  			<td>
     	  				<input type= "password" name = "password" id = "password" class="inputVal"/>
     	  			</td>
     	  			<td>&nbsp;</td>
     	  		</tr>
      	  		<tr>
     	  			<td>验证码:&nbsp;</td>
     	  			<td>
     	  				<input type= "text" name = "code" id = "return_code"/>
     	  			</td>
     	  			<td>
						<span id="code" oncopy="event.returnValue=false" onselectstart="event.returnValue=false"></span>
     	  			</td>
     	  		</tr>
     	  		
     	  		<tr>
     	  			<td>&nbsp;</td>
     	  			<td colspan="2">
     	  				<input type= "submit" value="登&nbsp;录" class="btn" onclick="login()"/>
     	  			</td>
     	  		</tr>
     	  </table>
     </div>
  </body>
</html>
