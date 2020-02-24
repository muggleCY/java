<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'roleEdit.jsp' starting page</title>
    
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
      <script type="text/javascript">
          $(document).ready(function () {
             var id = sessionStorage.getItem("id");
             console.log(id);
             $.ajax({
                 type: "POST",
                 url: "<%=basePath%>role/showOneRole.do",
                 data:{
                     "id":id
                 },
                 success:function (msg) {
                     var array = JSON.parse(msg);
                     $("#roleId").val(array.id);
                     $("#roleName").val(array.roleName);
                 }
             })
          });
          function updateRole() {
              var index = parent.layer.getFrameIndex(window.name);//获取窗口索引
              var roleId = $("#roleId").val();
              var roleName = $("#roleName").val();
              $.ajax({
                  type: "POST",
                  url: "<%=basePath%>role/updateRole.do",
                  data:{
                      "roleId":roleId,
                      "roleName":roleName
                  },
                  success:function (message) {
                      console.log(message);
                      if(message.state == "200"){
                          layer.msg("修改成功");
                          parent.load();
                          setInterval(function () {
                              var index = parent.layer.getFrameIndex(window.name);//获取窗口索引
                              parent.layer.close(index);
                          },1000);
                      }else{
                          layer.msg(message.state);
                      }
                  }
              });
          }
          function resetH() {
              location.reload();
          }
      </script>
      <style type="text/css">
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
  </head>
  
  <body>
  <h4 class="title_center">角色修改</h4>
  <form action="">
      <hr style="width: 250px;margin: 0 auto 10px;">
      <table id = "deptEditTable" class="content_center">
          <tr>
              <td>
                  角色ID:
              </td>
              <td>
                  <input type = "text" name="roleId" id="roleId" readonly/>
              </td>
          </tr>

          <tr>
              <td>
                  角色名称:
              </td>
              <td>
                  <input type = "text" name="roleName" id="roleName"/>
              </td>
          </tr>
          <tr>
              <td colspan="2" class="btn">
                  <input type = "button" value="修改" onclick="updateRole()"/>
                  <input type = "reset" value="重置" onclick="resetH()"/>
                  <a href="<%=basePath%>njwb/role/roleMan.jsp" target="contentPage"><input type="button" value="返回"></a>
              </td>
          </tr>
      </table>



  </form>
  </body>
</html>
