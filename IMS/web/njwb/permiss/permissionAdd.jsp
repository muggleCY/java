<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>添加权限</title>
    
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

          .title_center{
              text-align: center;
          }
          .content_center{
              margin: 0 auto;
          }
      </style>
      <script type="text/javascript">
          //添加 首先两个下拉框不能为“请选择”
          //后端判断这个权限是否已经存在
          var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
          var currentPage = sessionStorage.getItem("currentPage");
          function add() {
              var roleId = $("#roleType option:selected").val();
              var menuId = $("#menuType option:selected").val();
              if(roleId == "请选择" || menuId == "请选择"){
                  layer.msg("请选择");
                  //alert("请选择")
                  return false;
              }
              $("#roleId").val(roleId);
              $("#menuId").val(menuId);
              $.ajax({
                  type:"POST",
                  url:"<%=basePath%>per/insertPer.do",
                  data:{
                      "roleId": roleId,
                      "menuId": menuId
                  },
                  success:function (message) {
                      // console.log(message);
                      // var mes = JSON.parse(message);
                      mes = message;
                      if(message.state == "200"){
                          layer.msg("添加成功");

                      }else{
                          layer.msg(message.state);
                      }
                      parent.search(currentPage);
                      setInterval(function () {
                          var index = parent.layer.getFrameIndex(window.name);//获取窗口索引
                          parent.layer.close(index);
                      },1000);
                  }
              })
          }
          function getDownListRole() {
              $.ajax({
                  type:"POST",
                  url:"<%=basePath%>role/getRoleList.do",
                  success:function (message) {
                      var roleList = message;
                      var roleTypeEle = $("#roleType");
                      roleTypeEle.empty();
                      roleTypeEle.html($("select option")[0]);
                      for (var i = 0; i < roleList.length; i++) {
                          var option = $('<option></option>');
                          option.attr("value",roleList[i].id);
                          option.text(roleList[i].roleName);
                          roleTypeEle.append(option);
                      }
                  }
              });
          }
          $(document).ready(function () {
              getDownListRole();
          })
      </script>
  </head>

  <body>
  <h4 class="title_center">添加权限</h4>
  <form  onsubmit="return add()">
      <hr style="width: 250px;margin: 0 auto 10px;">
      <table id = "deptEditTable" class="content_center">
          <tr>
              <td>
                  角色:
              </td>
              <td>
                  <%--<input type = "text" name="deptNo" id="deptNo"/>--%>
                  <select id="roleType">
                      <option>请选择</option>
                      <option value="1">管理员</option>
                      <option value="2">普通用户</option>
                      <option value="3">人事专员</option>
                  </select>
                  <input type="hidden" id="roleId" name="roleId">
              </td>
          </tr>
          <tr>
              <td>
                  菜单:
              </td>
              <td>
                  <select id="menuType">
                      <option>请选择</option>
                      <option>请选择</option>
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
              <td colspan="2" id="holidayStatus">
                  <input type = "button" value="保存" onclick="add()"/>
                  <a href="" target="contentPage"><input type="button" value="返回"></a>
              </td>
          </tr>
      </table>


  </form>
  </body>
</html>
