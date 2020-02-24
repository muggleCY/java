<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>添加请假</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
      <script type="text/javascript" src="<%=basePath%>js/jquery-1.8.3.min.js"></script>
      <script type="text/javascript" src="<%=basePath%>js/layer/layer.js"></script>
      <script type="text/javascript" src="<%=basePath%>js/laydate/laydate.js"></script>

      <!--
      <link rel="stylesheet" type="text/css" href="styles.css">
      -->
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
          var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
          var currentPage = sessionStorage.getItem("currentPage");
          function submitH(obj) {

              if(obj.value == "草稿"){
                  obj = 4;
              }else if(obj.value == "提交"){
                  obj = 5;
              }
              var holidayType = $("#holidayType option:selected").val();
              var holidayBz = $("#holidayBz").val();
              var startTime = $("#startTime").val();
              var endTime = $("#endTime").val();
              if(holidayType == "请选择"||holidayBz == ""||startTime == ""||endTime == ""){
                  layer.msg("请填写完整");
              }else{
                  $.ajax({
                      type: "POST",
                      url: "<%=basePath%>holiday/insertHoliday.do",
                      data:{
                          "holidayType": holidayType,
                          "holidayBz": holidayBz,
                          "startTime": startTime,
                          "endTime": endTime,
                          "obj": obj
                      },
                      success:function(mes) {
                          console.log(mes);
                          if (mes.state=="200"){
                              layer.msg("添加成功");
                              //alert("删除成功");
                              //删除之后刷新页面
                              parent.search(currentPage);
                              parent.layer.close(index);
                          }else{
                              layer.msg(mes.state);
                              //alert(mes.state);
                          }
                      }
                  });
              }

          }
      </script>
  </head>

  <body>
  <form action="">

      <table id = "deptEditTable">
          <tr>
              <td>
                  请假类型:
              </td>
              <td>
                  <%--<input type = "text" name="deptNo" id="deptNo"/>--%>
                  <select id="holidayType">
                      <option>请选择</option>
                      <option value="11">事假</option>
                      <option value="12">婚假</option>
                      <option value="13">年假</option>
                      <option value="14">调休</option>
                      <option value="15">病假</option>
                      <option value="16">丧假</option>
                  </select>
              </td>
          </tr>
          <tr>
              <td>
                  请假事由:
              </td>
              <td>
                  <input type = "text" name="holidayBz" id="holidayBz"/>
              </td>
          </tr>

          <tr>
              <td>
                  开始时间:
              </td>
              <td>
                  <input type = "text" name="startTime" id="startTime"/>
                  <script>
                      //执行一个laydate实例
                      laydate({
                          elem: '#startTime' //指定元素
                      });
                  </script>
              </td>
          </tr>

          <tr>
              <td>
                  结束时间:
              </td>
              <td>
                  <input type = "text" name="endTime" id="endTime"/>
                  <script>
                      //执行一个laydate实例
                      laydate({
                          elem: '#endTime' //指定元素
                      });
                  </script>
              </td>
          </tr>

          <tr>
              <td colspan="2" id="holidayStatus">
                  <input type = "button" value="草稿" onclick="submitH(this)"/>
                  <input type = "button" value="提交" onclick="submitH(this)"/>
                  <a href="<%=basePath%>njwb/holiday/holiday.jsp" target="contentPage"><input type="button" value="返回"></a>
              </td>
          </tr>
      </table>


  </form>
  </body>
</html>
