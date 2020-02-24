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
		  var oldEmpName;
		  var oldEmpSex;
		  var oldEmpMaster;
		  var oldEmpEntryTime;

		  $(document).ready(function () {
		  	getEmpByid();
		  });

		  function updateEmp() {

		  		var empNo = $("#employeeNo").val();
			  var empName = $("#employeeName").val();
			  var empMaster = $("#employeeMaster").val();
			  var entryTime = $("#entryTime").val();
			  if (oldEmpEntryTime==empName&&oldEmpMaster==empMaster&&oldEmpSex==empSex&&oldEmpName==empName){
			  	layer.msg("未修改任何信息");
			  	return;
			  }
			  $.ajax({
				  type:"POST",
				  url:"<%=basePath%>employee/updateEmp.do",
				  data:{"empNo":empNo,"empName":empName,"empMaster":empMaster,"entryTime":entryTime},
				  success:function (message) {
				  	console.log(message.state);
				  	mes = message;
					  if (mes.state=="200"){
					    //添加成功之后刷新父页面
					    //关闭当前页面
						  parent.layer.msg("修改成功");
						  parent.loadEmp(1);
						  var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
					    parent.layer.close(index);
					  }else{
					    layer.msg(mes.state);
					    // alert(mes.state);
					  }
				  }
			  });

		  }

		  function resetEmp() {
			  $("#employeeName").val("");
			  $("#empSex").val("");
			  $("#employeeMaster").val("");
			  $("#entryTime").val("");
		  }

		  function getEmpByid(){
		  	var emp_id = sessionStorage.getItem("empId");
		  	sessionStorage.removeItem("empId");
			  $.ajax({
				  type:"POST",
				  url:"<%=basePath%>employee/getOneEmp.do",
				  data:{"id":emp_id},
				  success:function (message) {

					  var empNoEle = $("#employeeNo");
					  empNoEle.val(message.empNo);
					  empNoEle.attr("disabled","disabled")
					  $("#employeeName").val(message.empName);
					  var empSex = $("#empSex");
					  empSex.val(message.sex);
					  empSex.attr("disabled","disabled");
					  $("#employeeMaster").val(message.deptName);
					  $("#entryTime").val(message.entryTime);

					  oldEmpName = message.empName;
					  oldEmpSex = message.sex;
					  oldEmpMaster=message.deptName;
					  oldEmpEntryTime = message.entryTime;
					  // mes = message;
					  // if (mes.state=="200"){
						//   //添加成功之后刷新父页面
						//   parent.loadEmp(parent.currentPageNo);
						//   parent.showMessageAddSuccess();
						//   //关闭当前页面
						//   parent.layer.close(index);
					  // }else{
						//   layer.msg(mes.state);
						//   // alert(mes.state);
					  // }
				  }
			  });
		  }
	  </script>
  </head>
  
  <body>
   	<form action="">
   	
   	<table id = "deptEditTable">
   		<tr>
   			<td>
   			员工编号:
   			</td>
   			<td>
   				<input type = "text"  id="employeeNo"/>
   			</td>
   		</tr>
   		<tr>
   			<td>
   			姓名:
   			</td>
   			<td>
   				<input type = "text" id="employeeName" name="deptMaster" class="inputVal"/>
   			</td>
   		</tr>  

   		<tr>
   			<td>
   			性别:
   			</td>
   			<td>
   				<input type = "text" name="empSex" id="empSex"/>
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
				<input type = "text" name="deptMaster" id="entryTime"/>
			</td>
		</tr>

		<tr>
   			<td colspan="2">
   				<input type = "button" value="修改" onclick="updateEmp()"/>
   				<input type = "button" value="重置" onclick="resetEmp()"/>
   			</td>
   		</tr>  	
   	</table>
   	
   	
   	</form>
  </body>
</html>
