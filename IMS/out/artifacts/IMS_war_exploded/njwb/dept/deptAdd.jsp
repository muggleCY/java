<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>部门添加</title>
	
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="this is my page">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    
    <!--<link rel="stylesheet" type="text/css" href="./styles.css">-->
	<style type="text/css">
		body,div,table,tr,td{
			margin: 0px;
			padding: 0px;
		}
	
		#deptEditTable{
			font-size: 15px;
			border-collapse: collapse;
			width: 350px;
			margin: 20px auto;
			
			
		}
		
		#deptEditTable td{
			height: 40px;
		}
	
	</style>
  </head>
  
  <body>
   	<form action="">
   	
   	<table id = "deptEditTable">
   		<tr>
   			<td>
   			部门编号:
   			</td>
   			<td>
   				<input type = "text" name="deptNo" id="deptNo"/>
   			</td>
   		</tr>
   		<tr>
   			<td>
   			部门名称:
   			</td>
   			<td>
   				<input type = "text" name="deptName" id="deptName"/>
   			</td>
   		</tr>  

   		<tr>
   			<td>
   			部门位置:
   			</td>
   			<td>
   				<input type = "text" name="deptLoc" id="deptLoc"/>
   			</td>
   		</tr>  

   		<tr>
   			<td>
   			部门负责人:
   			</td>
   			<td>
   				<input type = "text" name="deptMaster" id="deptMaster"/>
   			</td>
   		</tr>  
   		
   		<tr>
   			<td colspan="2">
   				<input type = "submit" value="添加"/>
   				<input type = "reset" value="重置"/>
				<a href="dept.jsp" target="contentPage"><input type="button" value="返回"></a>
   			</td>
   		</tr>  	
   	</table>
   	
   	
   	</form>
  </body>
</html>
