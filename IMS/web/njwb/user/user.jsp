<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'user.jsp' starting page</title>
    
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
              top:65px;

          }
          .search{
              margin-left: 75px;

          }
          .distance{
              margin: 0 10px;
          }
      </style>

      <script type="text/javascript">
          var currentPage;
          var oldAccount = 0;
          var oldStatus = 0;
          var oldRoleId = 0;
          $(document).ready(function(){
              search(1);
              getDownListRole();
          });
          function del(id){
              layer.msg('确认要删除吗?', {
                  time: 0 //不自动关闭
                  ,btn: ['确定', '取消']
                  ,yes: function(index){
                      layer.close(index);
                      // 获取需要删除的部门的id
                      $.ajax({
                          type:"POST",
                          url:"<%=basePath%>user/deleteUser.do",
                          data:{"id":id},
                          success:function (message) {
                              console.log(message);
                              if (message.state=="200"){
                                  layer.msg("删除成功");
                                  //alert("删除成功");
                                  //删除之后刷新页面
                                  search(currentPage);
                              }else{
                                  layer.msg(message.state);
                                  //alert(mes.state);
                              }
                          }
                      });
                  }
              });
          }
          function search(pageNo) {
              var account = $("#userAccount").val();
              var status = $("#userStatus option:selected").val();
              var roleId = $("#roleId option:selected").val();
              console.log(status);
              console.log(roleId);
              if(status == "请选择"){
                  status = 0;
              }
              if(roleId == "请选择"){
                  roleId = 0;
              }
              if(oldAccount != account||oldStatus != status||oldRoleId != roleId){
                  pageNo = 1;
              }
              oldAccount = account;
              oldMenuType = status;
              oldRoleId = roleId;
              $.ajax({
                  type: "POST",
                  url: "<%=basePath%>user/showUsers.do",
                  data:{
                      "pageNo": pageNo,
                      "account": account,
                      "status": status,
                      "roleId": roleId
                  },
                  success:function (msg) {
                      var pager = $.parseJSON(msg);
                      currentPage = pager.pageNo;
                      var datas = pager.list;
                      var resultTable = $("#deptInfo");
                      resultTable.html($("table tr")[0]);
                      for (var i = 0;i < datas.length;i++){
                          var subDiv = $('<tr></tr>');
                          subDiv.append('<td>' + datas[i].userAccount + '</td>');
                          subDiv.append('<td>' + datas[i].empName + '</td>');
                          subDiv.append('<td>' + datas[i].configPageValue + '</td>');
                          subDiv.append('<td>' + datas[i].roleName + '</td>');
                          var iconTd = $("<td></td>");
                          var delImg = $('<img alt="" src="<%=basePath%>img/delete.png" class="operateImg">');
                          delImg.attr("onclick","del("+datas[i].id+")");
                          var editImg = $('<img alt="" src="<%=basePath%>img/edit.png" class="operateImg" >');
                          editImg.attr("onclick","edit("+datas[i].id+")");
                          iconTd.append(delImg);
                          iconTd.append(editImg);
                          subDiv.append(iconTd);
                          resultTable.append(subDiv);
                      }
                      for(;i<3;i++){
                          var tr = $("<tr><td>&nbsp;</td><td></td><td></td><td></td><td></td></tr>")
                          resultTable.append(tr);
                      }
                      var startPage = $("#startPage");
                      var lastPage = $("#lastPage");
                      lastPage.attr("onclick","search("+(pager.pageNo - 1)+")");
                      var nextPage = $("#nextPage");
                      nextPage.attr("onclick","search("+(pager.pageNo + 1)+")");
                      var endPage = $("#endPage");
                      endPage.attr("onclick","search("+pager.totalPage+")");
                      var state = false;
                      if(pager.pageNo == 1){
                          state = true;
                      }
                      startPage.prop("disabled",state);
                      lastPage.prop("disabled",state);
                      state = false;
                      if(pager.pageNo == pager.totalPage){
                          state = true;
                      }
                      nextPage.prop("disabled",state);
                      endPage.prop("disabled",state);
                  }
              });
          }
          function edit(id) {
              sessionStorage.removeItem("id");
              sessionStorage.removeItem("currentPage");
              sessionStorage.setItem("currentPage",currentPage);
              sessionStorage.setItem("id",id);
              layer.open({
                  type: 2,
                  area:['700px', '450px'],
                  fixed: false, //不固定
                  maxmin: true,
                  content:"<%=basePath%>njwb/user/userEdit.jsp"
              })
              // 跳转到部门修改的地方
          }
          function add() {
              sessionStorage.removeItem("currentPage");
              sessionStorage.setItem("currentPage",currentPage);
              layer.open({
                  type: 2,
                  area:['700px', '450px'],
                  fixed: false, //不固定
                  maxmin: true,
                  content:"<%=basePath%>njwb/user/userAdd.jsp"
              })
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
  <h1 class="title">首页  &gt;&gt;账户管理 </h1>
  <div class="search">
      <span class="distance">帐号：</span><input type="text" id="userAccount" >
      <span class="distance">账户状态：</span>
      <select id="userStatus">
          <option>请选择</option>
          <option value="6">正常</option>
          <option value="7">注销</option>
      </select>
      <span class="distance">角色：</span>
      <select id="roleId">
          <option>请选择</option>
      </select >
      <input class="distance" type="button" value="查询" onclick="search(1)">
  </div>
  <div class="add">
      <a  target="contentPage" onclick="add()"><img alt="" src="<%=basePath%>img/add.png" width="18px" height="18px">添加部门</a>
  </div>

  <table id="deptInfo" class="deptInfo">
      <tr class="titleRow">
          <td>帐号</td>
          <td>员工姓名</td>
          <td>状态</td>
          <td>角色</td>
          <td>操作列表</td>
      </tr>
  </table>
  <div style="width:250px;margin: 0 auto;">
      <input id="startPage" type="button" value="首页">
      <input id="lastPage" type="button" value="上一页">
      <input id="nextPage" type="button" value="下一页">
      <input id="endPage" type="button" value="末页">
  </div>
  </body>
</html>
