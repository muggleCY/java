<%@ page  language="java" import="java.util.*"  pageEncoding="UTF-8" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

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
      <script type="text/javascript" src="<%=basePath%>js/laydate/laydate.js"></script>
      <script type="text/javascript" src="<%=basePath%>js/reg.js"></script>
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
        var oldHolidayNo;
        var oldHolidayUser;
        var oldHolidayType;
        var oldHolidayBz;
        var oldStartTime;
        var oldEndTime;
        var oldStatus;
        var id;
        $(document).ready(function () {
           id = sessionStorage.getItem("id");
            $("#hid").attr("value",id);
            var index = $('#leave')[0].selectedIndex;
           $.ajax({
              type: "POST",
              url: "<%=basePath%>holiday/showOneHoliday.do",
              data:{
                  "id":id
              },
              success:function (msg) {
                  console.log(msg);
                  var array = msg;
                  $("#holidayNo").val(array.holidayNo);
                  $("#holidayUserId").val(array.holidayUser);
                  $("#holidayUser").val(array.empName);
                  //让array.holidayType的value值处于被选择状态
                  $("#leave option[value='"+array.holidayType+"']" ).prop("selected","selected");
                  $("#holidayBz").val(array.holidayBz);
                  $("#startTime").val(array.startTime);
                  $("#endTime").val(array.endTime);
                  $("#status option[value='"+array.holidayStatus+"']" ).prop("selected","selected");
                  oldHolidayNo = array.holidayNo;
                  oldHolidayUser = array.empName;
                  oldHolidayType =  array.holidayType;
                  oldHolidayBz = array.holidayBz;
                  oldStartTime = array.startTime;
                  oldEndTime = array.endTime;
                  oldStatus = array.holidayStatus;
              }
           });
        });
        function modify() {
            var option = $("#leave").val();
            $("#holidayType").val(option);
            var status = $("#status").val()
            $("#holidayStatus").val(status);
        }
        function updateHoliday() {
            var holidayNo = $("#holidayNo").val();
            var holidayUser = $("#holidayUserId").val();
            var holidayType = $("#leave option:selected").val();
            var holidayBzOrigin = $("#holidayBz").val();
            var holidayBz = removeSpecialCode(holidayBzOrigin);
            var startTime = $("#startTime").val();
            var endTime = $("#endTime").val();
            var holidayStatus = $("#status option:selected").val();
            if(oldHolidayNo ==  $("#holidayNo").val()&& oldHolidayUser == $("#holidayUser").val() && oldHolidayType == $("#leave option:selected").val()&&
            oldHolidayBz == $("#holidayBz").val() && oldStartTime == $("#startTime").val() && oldEndTime == $("#endTime").val() && oldStatus ==$("#status option:selected").val()){
                layer.msg("未修改数据，不用修改");
                return false;
            }else if(oldStatus == 5){
                layer.msg("数据已经提交，不能修改");
                return false;
            }else{
                $.ajax({
                    type: "POST",
                    url: "<%=basePath%>holiday/updateHoliday.do",
                    data:{
                        "hid":id,
                        "holidayNo":holidayNo,
                        "holidayUserId":holidayUser,
                        "holidayType":holidayType,
                        "holidayBz":holidayBz,
                        "startTime":startTime,
                        "endTime":endTime,
                        "holidayStatus":holidayStatus
                    },
                    success:function (message) {
                        console.log(message);
                        if(message.state == "200"){
                            layer.msg("修改成功");
                            parent.search(1);
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
            var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
            return true;
        }
        function resetH() {
            location.reload();
        }
    </script>
  </head>
  
  <body>
      <h3 class="title_center">请假修改</h3>
      <form action="" onsubmit="return updateHoliday()">
          <hr style="width: 250px;margin: 0 auto 10px;">
          <input type="hidden" id="hid" name="hid">
          <table id = "deptEditTable" class="content_center">
              <tr>
                  <td>
                      请假编号:
                  </td>
                  <td>
                      <input type = "text" name="holidayNo" id="holidayNo" readonly/>
                  </td>
              </tr>

              <tr>
                  <td>
                      申请人:
                  </td>
                  <td>
                      <input type = "text" name="holidayUser" id="holidayUser" readonly/>
                      <input type="hidden" name="holidayUserId" id="holidayUserId">
                  </td>
              </tr>

              <tr>
                  <td>
                      请假类型:
                  </td>
                  <td>
                      <%--<input type = "text" name="holidayType" id="holidayType"/>--%>
                      <select id="leave">
                          <option value="11">事假</option>
                          <option value="12">婚假</option>
                          <option value="13">年假</option>
                          <option value="14">调休</option>
                          <option value="15">病假</option>
                          <option value="16">丧假</option>
                      </select>
                      <input type="hidden" name="holidayType" id="holidayType">
                  </td>
              </tr>

              <tr>
                  <td>
                      请假事由:
                  </td>
                  <td>
                      <textarea rows="3" cols="22" name="holidayBz" id="holidayBz"></textarea>
                      <%--<input type = "" name="holidayBz" id="holidayBz"/>--%>
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
                  <td>
                      申请状态:
                  </td>
                  <td>
                      <%--<input type = "text" name="holidayStatus" id="holidayStatus"/>--%>
                      <select id="status">
                          <option value="4">草稿</option>
                          <option value="5">提交</option>
                      </select >
                      <input type="hidden" name="holidayStatus" id="holidayStatus">
                  </td>
              </tr>

              <tr>
                  <td colspan="2" class="btn">
                      <input type = "button" value="修改" onclick="updateHoliday()"/>
                      <input type = "button" value="重置" onclick="resetH()"/>
                      <a href="<%=basePath%>njwb/holiday/holiday.jsp" target="contentPage"><input type="button" value="返回"></a>
                  </td>
              </tr>
          </table>



      </form>
  </body>
</html>
