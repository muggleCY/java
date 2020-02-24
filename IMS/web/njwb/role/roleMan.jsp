<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'role.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
      <link rel="stylesheet" type="text/css" href="<%=basePath%>css/reset.css">
      <link rel="stylesheet" type="text/css" href="<%=basePath%>css/main.css">
      <script type="text/javascript" src="<%=basePath%>js/jquery-1.8.3.min.js"></script>
      <script type="text/javascript" src="<%=basePath%>js/layer/layer.js"></script>

      <style type="text/css">
          .add{
              top:45px;

          }

      </style>

      <script type="text/javascript">
          $(document).ready(function () {
              load();
          })
          function del(id){
              layer.msg('确认要删除吗?', {
                  time: 0 //不自动关闭
                  ,btn: ['确定', '取消']
                  ,yes: function(index){
                      layer.close(index);
                      // 获取需要删除的部门的id
                      $.ajax({
                          type:"POST",
                          url:"<%=basePath%>role/deleteRole.do",
                          data:{"id":id},
                          success:function (message) {
                              var mes = JSON.parse(message);
                              if (mes.state=="200"){
                                  layer.msg("删除成功");
                                  //alert("删除成功");
                                  //删除之后刷新页面
                                  load();
                              }else{
                                  layer.msg(mes.state);
                                  //alert(mes.state);
                              }
                          }
                      });
                  }
              });
          }
          function load() {
              $("deptInfo").text("");
              $("#deptInfo").html($("table tr")[0]);
              $.ajax({
                  type: "POST",
                  url: "<%=basePath%>role/showRoles.do",
                  success:function (msg) {
                      var array = eval(msg);
                      var table = $("#deptInfo");
                      for (var i = 0; i < array.length; i++){
                          var subDiv = $('<tr></tr>');
                          subDiv.append('<td>' + array[i].id + '</td>');
                          subDiv.append('<td>' + array[i].roleName + '</td>');
                          var iconTd = $("<td></td>");
                          var delImg = $('<img alt="" src="<%=basePath%>img/delete.png" class="operateImg">');
                          delImg.attr("onclick","del("+array[i].id+")");
                          var editImg = $('<img alt="" src="<%=basePath%>img/edit.png" class="operateImg" >');
                          editImg.attr("onclick","edit("+array[i].id+")");
                          iconTd.append(delImg);
                          iconTd.append(editImg);
                          subDiv.append(iconTd);
                          table.append(subDiv);
                      }
                  }
              });
          }
          function edit(id) {
              sessionStorage.removeItem("id");
              sessionStorage.setItem("id",id);
              layer.open({
                  type: 2,
                  area:['700px', '450px'],
                  fixed: false, //不固定
                  maxmin: true,
                  content:"<%=basePath%>njwb/role/roleEdit.jsp"
              })
          }
          function add() {
              layer.open({
                  type: 2,
                  area:['700px', '450px'],
                  fixed: false, //不固定
                  maxmin: true,
                  content:"<%=basePath%>njwb/role/roleAdd.jsp"
              })
          }
          function showMessageAddSuccess(message) {
              layer.msg(message);
          }
      </script>
  </head>

  <body>
  <h1 class="title">首页  &gt;&gt;角色管理 </h1>

  <div class="add">
<%--      <a href="<%=basePath%>role/roleAdd.jsp" target="contentPage"><img alt="" src="<%=basePath%>img/add.png" width="18px" height="18px">添加角色</a>--%>
          <img alt="" src="<%=basePath%>img/add.png" width="18px" height="18px" onclick="add()">添加角色</a>
  </div>
  <table id="deptInfo" class="deptInfo">
      <tr class="titleRow">
          <td>角色ID</td>
          <td>角色名称</td>
          <td>操作列表</td>
      </tr>
  </table>
  </body>
</html>
