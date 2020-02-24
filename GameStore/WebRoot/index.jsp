<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
if(session.getAttribute("manager")==null||session.getAttribute("manager").equals("")){
	request.setAttribute("isError", true);
	request.setAttribute("errorMessage", "请先登录!");
	request.getRequestDispatcher("login.jsp").forward(request,response);
}
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>游币后台管理系统</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<script src="<%=basePath%>js/jquery-2.2.3.min.js"></script>
    <link href="<%=basePath%>css/ie10-viewport-bug-workaround.css" rel="stylesheet">
    <link href="<%=basePath%>css/dashboard.css" rel="stylesheet">
    <script src="<%=basePath%>js/ie-emulation-modes-warning.js"></script>
    <link href="<%=basePath%>css/bootstrap.min.css" rel="stylesheet">
    <script src="<%=basePath%>js/bootstrap.min.js"></script>
  </head>
  
   <frameset rows="8%,*">
		<frame src="<%=basePath%>top.jsp" noresize="no"></frame>
		<frameset cols="10%,*">
			<frame src="<%=basePath%>left.jsp" noresize="no"></frame>
			<frame src="<%=basePath%>user/user.jsp" name="right"></frame>
		</frameset>	
   </frameset>
  <c:if test="${isError}">
   	 <script type="text/javascript">
   	 	alert("${errorMessage}")
   	 </script>
   </c:if>
</html>
