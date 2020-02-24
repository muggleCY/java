<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>员工添加</title>
	
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="this is my page">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
	  <script type="text/javascript" src="<%=basePath%>js/jquery-1.8.3.min.js"></script>
	  <script type="text/javascript" src="<%=basePath%>js/layer/layer.js"></script>
	  <script type="text/javascript" src="<%=basePath%>js/laydate/laydate.js"></script>
	  <script type="text/javascript" src="<%=basePath%>js/reg.js"></script>
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
	  <script type="text/javascript">
		  var empNameEle;
		  var empSexELe;
		  var empMasterELe;
		  var empEntryTimeELe;
		  var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
		  function resetValue() {

		  }
		  function addEmpInfo(){
			  $.ajax({
				  type:"POST",
				  url:"<%=basePath%>employee/addEmp.do",
				  data:{"empName":empNameEle.val(),"empSex":empSexELe.val(),
				  "empMaster":empMasterELe.val(),"empEntryTime":empEntryTimeELe.val()},
				  success:function (message) {
					  mes = message;
					  if (mes.state=="200"){
						  //添加成功之后刷新父页面
						  parent.loadEmp(parent.currentPageNo);
						  parent.showMessageAddSuccess();
						  //关闭当前页面
						  parent.layer.close(index);
					  }else{
						  layer.msg(mes.state);
						  // alert(mes.state);
					  }
				  }
			  });
		  }

		  $(document).ready(function () {
		  	 empNameEle = $("#employeeName");
			  empSexELe = $("#empSex");
			  empMasterELe = $("#employeeMaster");
			  empEntryTimeELe = $("#entryTime");
		  });
	  </script>
  </head>
  
  <body>
   	<form action="">
   	
   	<table id = "deptEditTable">
   			<td>
   			姓名:
   			</td>
   			<td>
   				<input type = "text" id="employeeName" name="deptMaster " class="inputVal"/>
   			</td>
   		</tr>  

   		<tr>
   			<td>
   			性别:
   			</td>
   			<td>
<%--   				<input type = "text" name="deptLoc" id="empSex"/>--%>
				<select id="empSex">
					<option value="男">男</option>
					<option value="女">女</option>
				</select>
   			</td>
   		</tr>  

   		<tr>
   			<td>
   			所属部门:
   			</td>
   			<td>
   				<input type = "text" name="deptName" id="employeeMaster" class="inputVal"/>
   			</td>
   		</tr>
		<tr>
			<td>
				入职时间:
			</td>
			<td>
				<input type = "text" name="deptMaster" id="entryTime" class="form-control layer-date"/>
			</td>
			<script>
				//执行一个laydate实例
				laydate({
					elem: '#entryTime' //指定元素
				});
			</script>
		</tr>

		<tr>
   			<td colspan="2">
   				<input type = "button" value="添加" onclick="addEmpInfo()"/>
   				<input type = "reset" value="重置" onclick="resetValue()"/>
   			</td>
   		</tr>  	
   	</table>
   	
   	
   	</form>
  </body>
</html>
