<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>部门编辑</title>
	
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="this is my page">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">

	  <script type="text/javascript" src="<%=basePath%>js/jquery-1.8.3.min.js"></script>
	  <script type="text/javascript" src="<%=basePath%>js/layer/layer.js"></script>
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
  </head>
  <script type="text/javascript">
	  var id;
	  //在页面保留一份原来的值
	  var oldDeptName;
	  var oldDeptLoc ;
	  var oldDeptMaster;
	  var oldDeptDesc;
	  $(document).ready(function () {
		  //获取id
		  id = sessionStorage.getItem("id");
		  sessionStorage.removeItem("id");
		  if (id!=null){
			  getInfo();
		  }else {
		  	// 将修改按钮改成添加
			  //编号不可见
			  $("#deptNoTr").prop("hidden",true);
			  var changeBtn = $("#change");
			  changeBtn.attr("value","添加");
			  changeBtn.attr("onclick","addDept()");
			  $("#resetBtn").attr("onclick","resetInfoAll()");
		  }
	  });
	  function addDept() {
		  var deptNo = $("#deptNo").val();
		  var deptName = $("#deptName").val();
		  var deptLoc = $("#deptLoc").val();
		  var deptMaster = $("#deptMaster").val();
		  var deptDesc = $("#deptDesc").val();
	  // ,"deptMaster":deptMaster,"deptDesc":deptDesc
		  var addData = {"deptNo":deptNo,"deptName":deptName,"deptLoc":deptLoc,"deptMaster":deptMaster,"deptDesc":deptDesc};
		  console.log(addData);
		  $.ajax({
			  type: "POST",
			  url: "<%=basePath%>dept/addOneDept.do",
			  data:addData,
			  success:function(msg){
			  	mes = msg;
				  if (mes.state=="200"){
				  	parent.layer.msg('添加成功');
						//  alert("添加成功");
					  //修改成功之后,去刷新父页面
					  parent.loadDept();
					  //关闭当前页面
					  var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
					  parent.layer.close(index);
				  }else {
					  parent.layer.msg(mes.state);
					  // alert(mes.state);
					  parent.layer.close(index);
				  }
			  }
		  });
	  }
	  function getInfo() {
		  $.ajax({
			  type: "POST",
			  url: "<%=basePath%>dept/showOneDept.do",
			  data:{"id":id},
			  success:function(msg){
				  // dept = JSON.parse(msg);
				  dept = msg;
				  var deptNo = $("#deptNo");
				  deptNo.val(dept.deptNo);
				  // 编号不允许改动
				  deptNo.attr("disabled","disabled");
				  $("#deptName").val(dept.deptName);
				  $("#deptLoc").val(dept.deptLoc);
				  $("#deptMaster").val(dept.deptManagerName);
				  $("#deptDesc").val(dept.deptDesc);
				  oldDeptName = $("#deptName").val();
				  oldDeptLoc= $("#deptLoc").val();
				  oldDeptMaster= $("#deptMaster").val();
				  oldDeptDesc=$("#deptDesc").val();

			  }
		  });
	  }
	  function resetInfo() {
		  $("#deptName").val("");
		  $("#deptLoc").val("");
		  $("#deptMaster").val("");
		  $("#deptDesc").val("");
	  }
	  function resetInfoAll() {
	  	//reset所有input
		  $("#deptNo").val("");
		  resetInfo();
	  }
	  function changeDept() {
	  	var deptName = $("#deptName").val();
	  	var deptLoc = $("#deptLoc").val();
	  	var deptMaster = $("#deptMaster").val();
	  	var deptDesc = $("#deptDesc").val();
	  	//如果无任何提交,则弹出未修改任何信息
	  	if (deptName == oldDeptName && deptLoc == oldDeptLoc && deptMaster==oldDeptMaster && deptDesc==oldDeptDesc){
	  		alert("未修改任何信息");
	  		return;
		}
	  	var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
	  	var data = {"deptName":deptName,"deptLoc":deptLoc,"deptMaster":deptMaster,"id":id,"deptDesc":deptDesc};
		  $.ajax({
			  type: "POST",
			  url: "<%=basePath%>dept/updateDept.do",
			  data:data,
			  success:function(msg){
				  // var mes = JSON.parse(msg);
				  mes = msg;
				  if (mes.state=="200"){
					  parent.layer.msg("修改成功");
					  //修改成功之后,去刷新父页面
					  parent.loadDept();
					  //关闭当前页面
					  parent.layer.close(index);
				  }else {
					  alert(mes.state);
				  }
			  }
		  });
	  }
  </script>
  
  <body>
   	<form action="">
   	<table id = "deptEditTable">
   		<tr id="deptNoTr">
   			<td >
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
   				<input type = "text" class="inputVal" name="deptName" id="deptName"/>
   			</td>
   		</tr>  

   		<tr>
   			<td>
   			部门位置:
   			</td>
   			<td>
   				<input type = "text" class="inputVal" name="deptLoc" id="deptLoc"/>
   			</td>
   		</tr>  

   		<tr>
   			<td>
   			部门负责人:
   			</td>
   			<td>
   				<input type = "text" class="inputVal" name="deptMaster" id="deptMaster"/>
   			</td>
   		</tr>
		<tr>
			<td>
				部门描述:
			</td>
			<td>
				<textarea   id="deptDesc" name="deptDesc" class="inputArea"></textarea>
			</td>
		</tr>

		<tr>
   			<td colspan="2">
   				<input id="change" type = "button" value="修改" onclick="changeDept()"/>
   				<input id="resetBtn" type = "button" value="重置" onclick="resetInfo()"/>
   			</td>
   		</tr>  	
   	</table>
   	
   	
   	</form>
  </body>
</html>
