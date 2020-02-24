<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'holidayDetail.jsp' starting page</title>
    
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
              $.ajax({
                  type: "POST",
                  url: "<%=basePath%>holiday/showOneHoliday.do",
                  data:{
                      "id":id
                  },
                  success:function (msg) {
                      console.log(msg);
                      var array = msg;
                      console.log(array.holidayUser);
                      $("#holidayNo").text(array.holidayNo);
                      $("#holidayUser").text(array.empName);
                      $("#holidayType").text(array.configKey);
                      $("#holidayBz").text(array.holidayBz);
                      $("#startTime").text(array.startTime);
                      $("#endTime").text(array.endTime);
                      $("#holidayStatus").text(array.configPageValue);
                      $("#createTime").text(array.createTime);
                  }
              });
          });

      </script>
      <style type="text/css">
          .detail_main{
              width: 250px;
              height: 300px;
              text-align: center;
              margin: 0 auto;
          }
          .detail_content{
              text-align: left;
          }
      </style>
  </head>
  
  <body>
  <div class="detail_main">
      <h4>请假明细</h4>
      <hr>
      <div class="detail_content">
        <span>请假编号：</span><span id="holidayNo"></span><br>
        <span>申请人：</span><span id="holidayUser"></span><br>
        <span>请假类型：</span><span id="holidayType"></span><br>
        <span>请假事由：</span><span id="holidayBz"></span><br>
        <span>开始时间：</span><span id="startTime"></span><br>
        <span>结束时间：</span><span id="endTime"></span><br>
        <span>申请状态：</span><span id="holidayStatus"></span><br>
        <span>申请提交时间：</span><span id="createTime"></span><br>
      </div>
      <br>
      <a href="<%=basePath%>njwb/holiday/holiday.jsp" target="contentPage"><input type="button" value="返回"></a>
  </div>
  </body>
</html>
