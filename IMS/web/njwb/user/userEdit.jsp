<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'userEdit.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
      <script type="text/javascript" src="<%=basePath%>js/jquery-1.8.3.min.js"></script>
      <script type="text/javascript" src="<%=basePath%>js/layer/layer.js"></script>
      <style type="text/css">
          body,div,table,tr,td{
              margin: 0px;
              padding: 0px;
          }

          #deptEditTable{
              font-size: 15px;
              border-collapse: collapse;
              width: 250px;
              margin: 20px auto;


          }
          #deptEditTable td{
              height: 40px;
          }
          .title_center{
              text-align: center;
          }
          .content_center{
              margin: 0 auto;
          }
          .btn{
              padding-left: 50px;
          }

      </style>
      <script type="text/javascript">
          //编号，员工编号，姓名都是不可修改的
          //表单验证——员工验证匹配，数据是否修改过
          var id;
          var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
          var currentPage = sessionStorage.getItem("currentPage");
          var oldAccount;
          var oldEmpNo ;
          var olEmpName;
          var oldStatus;
          var oldRoleId;
          $(document).ready(function () {
              id = sessionStorage.getItem("id");
              getDownListRole();
              $("#hid").val(id);
              $.ajax({
                  type: "POST",
                  url: "<%=basePath%>user/showOneUser.do",
                  data:{
                      "id":id
                  },
                  success:function (msg) {
                      var array = JSON.parse(msg);
                      console.log(array);
                      $("#account").val(array.userAccount);
                      $("#empNo").val(array.empNoo);
                      $("#empName").val(array.empName);
                      //让array.holidayType的value值处于被选择状态
                      $("#userStatus option[value='"+array.userStatusId+"']" ).prop("selected","selected");
                      $("#roleId option[value='"+array.roleId+"']" ).prop("selected","selected");
                      oldAccount = array.userAccount;
                      oldEmpNo = array.empNoo;
                      olEmpName = array.empName;
                      oldStatus = array.userStatusId;
                      oldRoleId = array.roleId;
                  }
              });
          });
          function modify() {
              var status = $("#userStatus option:selected").val();
              var role =  $("#roleId option:selected").val();
              if($("#empNoAgain").val() == ""||$("#empNameAgain").val() == ""){
                  layer.msg("信息不能为空");
              }else if($("#empNo").val() != $("#empNoAgain").val() ||$("#empName").val()!=$("#empNameAgain").val()){
                  layer.msg("验证不正确");
              }else if(oldAccount == $("#account").val()&&oldEmpNo ==$("#empNo").val()&&olEmpName == $("#empName").val()&&oldStatus == status &&oldRoleId == role){
                  layer.msg("未修改，不需要提交修改");
              }else{
                  $.ajax({
                      type: "POST",
                      url: "<%=basePath%>user/updateUser.do",
                      data:{
                          "id":id,
                          "status":status,
                          "role":role
                      },
                      success:function (msg) {
                          console.log(msg);
                          console.log(msg.state);
                          if(msg.state == "200"){
                              layer.msg("修改成功");
                              //alert("修改成功");
                              parent.search(currentPage);
                              setInterval(function () {
                                  var index = parent.layer.getFrameIndex(window.name);//获取窗口索引
                                  parent.layer.close(index);
                              },1000);
                          }else {
                              layer.msg(msg.state);
                          }
                      }
                  });
              }

          }
          function resetH() {
              location.reload();
          }
          function getDownListRole() {
              $.ajax({
                  type:"POST",
                  url:"<%=basePath%>role/getRoleList.do",
                  success:function (message) {
                      var roleList = message;
                      var roleTypeEle = $("#roleId");
                      // roleTypeEle.html($("select option")[0]);
                      for (var i = 0; i < roleList.length; i++) {
                          var option = $('<option></option>');
                          option.attr("value",roleList[i].id);
                          option.text(roleList[i].roleName);
                          roleTypeEle.append(option);
                      }
                  }
              });
          }
      </script>
  </head>
  
  <body>
  <h3 class="title_center">账户修改</h3>
  <form action="">
      <hr style="width: 250px;margin: 0 auto 10px;">
      <input type="hidden" id="hid" name="hid">
      <table id = "deptEditTable" class="content_center">
          <tr>
              <td>
                  账户:
              </td>
              <td>
                  <input type = "text" name="account" id="account" readonly/>
              </td>
          </tr>

          <tr>
              <td>
                  员工编码:
              </td>
              <td>
                  <input type = "text" name="empNo" id="empNo" readonly/>
              </td>
          </tr>
          <tr>
              <td>
                  员工验证编码:
              </td>
              <td>
                  <input type = "text" name="empNoAgain" id="empNoAgain"/>
              </td>
          </tr>
          <tr>
              <td>
                  员工姓名:
              </td>
              <td>
                  <input type = "text" name="empName" id="empName" readonly/>
              </td>
          </tr>
          <tr>
              <td>
                  员工验证姓名:
              </td>
              <td>
                  <input type = "text" name="empNameAgain" id="empNameAgain"/>
              </td>
          </tr>

          <tr>
              <td>
                  状态:
              </td>
              <td>
                  <select id="userStatus">
                      <option value="6">正常</option>
                      <option value="7">注销</option>
                  </select>
                  <input type="hidden" id="status" name="status">
              </td>
          </tr>

          <tr>
              <td>
                  角色:
              </td>
              <td>
                  <select id="roleId">
                  </select >
                  <input type="hidden" id="role" name="role">
              </td>
          </tr>
          <tr>
              <td colspan="2" class="btn">
                  <input type = "button" value="修改" onclick="modify()"/>
                  <input type = "reset" value="重置" onclick="resetH()"/>
                  <a href="<%=basePath%>njwb/user/user.jsp" target="contentPage"><input type="button" value="返回"></a>
              </td>
          </tr>
      </table>



  </form>
  </body>
</html>
