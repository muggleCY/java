<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'roleAdd.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
      <script type="text/javascript" src="<%=basePath%>js/jquery-1.8.3.min.js"></script>
      <script type="text/javascript" src="<%=basePath%>js/layer/layer.js"></script>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
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
      <script type="text/javascript">

          $(document).on('click','#button',function () {

          })
          function add() {
              var roleName = $("#roleName").val();
              if(roleName == null || roleName == ""){
                  layer.msg("角色名称不能为空");
              }else{
                  $.ajax({
                      type:"POST",
                      url:"<%=basePath%>role/insertRole.do",
                      data:{"roleName":roleName},
                      success:function (message) {
                          console.log(message.state);
                          if(message.state == "200"){
                              layer.msg("添加成功");

                          }else{
                              layer.msg(message.state);
                          }
                          parent.load();
                          setInterval(function () {
                              var index = parent.layer.getFrameIndex(window.name);//获取窗口索引
                              parent.layer.close(index);
                          },1000);

                      }
                  });
              }

          }
      </script>
  </head>

  <body>
  <h4 class="title_center">角色添加</h4>
  <form action="">
      <hr style="width: 250px;margin: 0 auto 10px;">
      <table id = "deptEditTable" class="content_center">
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
                  <input type = "button" id="button" value="添加" onclick="add()"/>
                  <a href="<%=basePath%>njwb/role/roleMan.jsp" target="contentPage"><input type="button" value="返回"></a>
              </td>
          </tr>
      </table>
  </form>
  </body>
  </body>
</html>
