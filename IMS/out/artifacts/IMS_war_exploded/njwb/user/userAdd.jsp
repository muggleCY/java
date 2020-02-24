<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>添加账户</title>
    
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
          var currentPage = sessionStorage.getItem("currentPage");
          $(document).ready(function () {
              getDownListRole();
          })
          function getDownListRole() {
              $.ajax({
                  type:"POST",
                  url:"<%=basePath%>role/getRoleList.do",
                  success:function (message) {
                      var roleList = message;
                      var roleTypeEle = $("#roleType");
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
          function add() {
                var roleId = $("#roleType option:selected").val();
                var status = $("#userStatus option:selected").val();
                var account = $("#account").val();
                var empNo = $("#empNo").val();
                var empName = $("#empName").val();

                if(roleId == "0"||status == ""||account == ""||empNo == ""||empName == ""){
                    layer.msg("信息不完整");
                }else{
                    $.ajax({
                        type:"POST",
                        url:"<%=basePath%>user/addUser.do",
                        data:{
                            "roleId":roleId,
                            "status":status,
                            "account":account,
                            "empNo":empNo,
                            "empName":empName
                        },
                        success:function (message) {
                            console.log(message.state);
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
                    });
                }

          }
      </script>
  </head>

  <body>
  <h4 class="title_center">添加账户</h4>
  <form >
      <hr style="width: 250px;margin: 0 auto 10px;">
      <table id = "deptEditTable" class="content_center">
          <tr>
              <td>
                  角色:
              </td>
              <td>
                  <%--<input type = "text" name="deptNo" id="deptNo"/>--%>
                  <select id="roleType">
                      <option value="0">请选择</option>
                      <option value="0">请选择</option>
                  </select>
              </td>
          </tr>
          <tr>
              <td>
                  账户状态:
              </td>
              <td>
                  <select id="userStatus">
                      <option value="6">正常</option>
                      <option value="7">注销</option>
                  </select>
              </td>
          </tr>
          <tr>
              <td>
                  帐号:
              </td>
              <td>
                  <input type="text" id="account">
              </td>
          </tr>
          <tr>
              <td>
                  员工编号:
              </td>
              <td>
                  <input type="text" id="empNo">
              </td>
          </tr>
          <tr>
              <td>
                  员工姓名:
              </td>
              <td>
                  <input type="text" id="empName">
              </td>
          </tr>
          <tr>
              <td colspan="2" id="holidayStatus">
                  <input type = "button" value="保存" onclick="add()"/>
                  <a href="<%=basePath%>njwb/user/user.jsp" target="contentPage"><input type="button" value="返回"></a>
              </td>
          </tr>
      </table>


  </form>
  </body>
</html>
