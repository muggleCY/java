<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">

    <title>My JSP 'holidayEdit.jsp' starting page</title>

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
        var oldRoleID;
        var oldMenuName;
        var id;

        var currentPage = sessionStorage.getItem("currentPage");
        $(document).ready(function () {
           id = sessionStorage.getItem("id");
           $.ajax({
              type: "POST",
              url: "<%=basePath%>per/showOnePer.do",
              data:{
                  "id":id
              },
              success:function (msg) {
                  console.log(msg);
                  $("#roleId").val(msg.roleId);
                  $("#menuType option[value='"+msg.menuId+"']" ).prop("selected","selected");

                  oldRoleID = msg.roleId;
                  oldMenuName = msg.menuId;
              }
           });
        });
        function modify() {
            // var roleId = $("#roleId").val();
            // var menuId = $("#menuType option:selected").val();
            // $("#hid").val(id);
            // $("#menuId").val(id);
            // if(oldRoleID == roleId && oldMenuName == menuId){
            //     layer.msg("未修改数据，不用修改");
            //     return false;
            // }

            var roleId = $("#roleId").val();
            var menuId = $("#menuType option:selected").val();
            if(oldRoleID == roleId && oldMenuName == menuId){
                layer.msg("未修改数据，不用修改");
            }else {
                $.ajax({
                    type: "POST",
                    url: "<%=basePath%>per/updatePer.do",
                    data:{
                        "id":id,
                        "roleId":roleId,
                        "menuId":menuId,
                    },
                    success:function (message) {
                        console.log(message);
                        if(message.state == "200"){
                            layer.msg("修改成功");
                            parent.search(currentPage);
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

        }
        function resetH() {
            location.reload();
        }
    </script>
  </head>

  <body>
      <h3 class="title_center">权限修改</h3>
      <form action="<%=basePath%>per/updatePer.do">
          <hr style="width: 250px;margin: 0 auto 10px;">
          <input type="hidden" id="hid" name="hid">
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
                      菜单名称:
                  </td>
                  <td>
                      <select id="menuType">
                          <option value="4">部门管理</option>
                          <option value="5">员工管理</option>
                          <option value="6">请假管理</option>
                          <option value="7">报销管理</option>
                          <option value="8">账户维护</option>
                          <option value="9">密码重置</option>
                          <option value="10">角色管理</option>
                          <option value="11">权限管理</option>
                          <option value="12">系统退出</option>
                      </select >
                      <input type="hidden" id="menuId" name="menuId">
                  </td>
              </tr>
              <tr>
                  <td colspan="2" class="btn">
                      <input type = "button" value="修改" onclick="modify()"/>
                      <input type = "button" value="重置"  onclick="resetH()"/>
                      <a href="<%=basePath%>njwb/permiss/permission.jsp" target="contentPage"><input type="button" value="返回"></a>
                  </td>
              </tr>
          </table>



      </form>
  </body>
</html>
