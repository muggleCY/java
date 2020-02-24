<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>员工管理</title>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/reset.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/main.css">
    <script type="text/javascript" src="<%=basePath%>js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/layer/layer.js"></script>
	<script type="text/javascript">

        function showMessageAddSuccess() {
            layer.msg('添加成功');
        }
        var empName;
        var currentPageNo;
		function del(id){
            layer.msg('确认要删除吗?', {
                time: 0 //不自动关闭
                ,btn: ['确定', '取消']
                ,yes: function(index){
                    layer.close(index);
                    // 获取需要删除的部门的id
                    $.ajax({
                        type:"POST",
                        url:"<%=basePath%>employee/deleteEmp.do",
                        data:{"id":id},
                        success:function (message) {
                            var mes = message;
                            if (mes.state=="200"){
                                layer.msg("删除成功");
                                //alert("删除成功");
                                //删除之后刷新页面
                                loadEmp(currentPageNo);
                            }else{
                                layer.msg(mes.state);
                            }
                        }
                    });
                }
            });
		}
		function addEmp() {
            layer.open({
                type: 2,
                area: ['700px', '500px'],
                fixed: false, //不固定
                maxmin: true,
                content: '<%=basePath%>njwb/emp/employeeAdd.jsp'
            });
        }
		function edit(id) {
            sessionStorage.removeItem("empId");
            sessionStorage.setItem("empId",id);
            layer.open({
                type: 2,
                area: ['700px', '450px'],
                fixed: false, //不固定
                maxmin: true,
                content: '<%=basePath%>njwb/emp/employeeModify.jsp'
            });
        }
        function detail(id) {

        }
		function loadEmp(pageNo){
		    //获取需要查询的员工的姓名
            empName = $("#emp_name");
            var table = $("#deptInfo");
            //将table的title写入table中
            table.html($("table tr")[0]);
            $.ajax({
                type: "POST",
                url: "<%=basePath%>employee/showAllEmp.do",
                data:{"pageNumber":pageNo,"empName":empName.val()},
                success:function(msg){
                    // var arrayPager = JSON.parse(msg);
                    console.log(msg);
                    var arrayPager = msg;
                    var array = arrayPager.list;
                    currentPageNo = arrayPager.pageNo;
                    for(var i = 0; i < array.length; i++){
                        var subDiv = $('<tr></tr>');
                        subDiv.append('<td>' + array[i].empNo + '</td>');
                        subDiv.append('<td>' + array[i].empName + '</td>');
                        subDiv.append('<td>' + array[i].sex + '</td>');
                        subDiv.append('<td>' + array[i].deptName + '</td>');
                        subDiv.append('<td>' + array[i].entryTime + '</td>');
                        var iconTd = $("<td></td>");
                        var delImg = $('<img alt="" src="<%=basePath%>img/delete.png" class="operateImg">');
                        delImg.attr("onclick","del("+array[i].id+")");
                        var editImg = $('<img alt="" src="<%=basePath%>img/edit.png" class="operateImg" >');
                        editImg.attr("onclick","edit("+array[i].id+")");
                        var detailImg = $('<img alt="" src="<%=basePath%>img/detail.png" class="operateImg">');
                        detailImg.attr("onclick","detail("+array[i].id+")");
                        iconTd.append(delImg);
                        iconTd.append(editImg);
                        iconTd.append(detailImg);
                        subDiv.append(iconTd);
                        table.append(subDiv);
                        table.append(subDiv);
                    }
                    for(;i<3;i++){
                        var tr = $("<tr><td>&nbsp;</td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>")
                        table.append(tr);
                    }
                    var startPage = $("#startPage");
                    var lastPage = $("#lastPage");
                    lastPage.attr("onclick","loadEmp("+(arrayPager.pageNo - 1)+")");
                    var nextPage = $("#nextPage");
                    nextPage.attr("onclick","loadEmp("+(arrayPager.pageNo + 1)+")");
                    var endPage = $("#endPage");
                    endPage.attr("onclick","loadEmp("+arrayPager.totalPage+")");
                    var state = false;
                    if(arrayPager.pageNo == 1){
                        state = true;
                    }
                    startPage.prop("disabled",state);
                    lastPage.prop("disabled",state);
                    state = false;
                    if(arrayPager.pageNo == arrayPager.totalPage){
                        state = true;
                    }
                    nextPage.prop("disabled",state);
                    endPage.prop("disabled",state);
                }
            });

            // //查询完成之后去清空输入框
            // empName.val("");
        }
        $(document).ready(function () {
            loadEmp(1);
        });
	</script>
      <style>
          .add{
              top:65px;
          }
          .search{
              margin-left: 50px;

          }
          .distance{
              margin: 0 10px;
          }
          .deptInfo{
              width: 750px;
              font-size: 14px;
          }
      </style>
  </head>
  <body>
  <h1 class="title">首页  &gt;&gt;员工管理 </h1>

  <div class="add">
      <img onclick="addEmp()" alt="" src="<%=basePath%>img/add.png" width="18px" height="18px" onclick="addDept()">添加员工
  </div>
  <div class="search">
      <span >姓名</span><input id="emp_name" type="text" onblur="loadEmp(1)" >
<%--      <span hidden>部门</span>--%>
<%--      <select hidden>--%>
<%--          <option>总经办</option>--%>
<%--          <option>渠道部</option>--%>
<%--          <option>市场营销部</option>--%>
<%--          <option>教质部</option>--%>
<%--          <option>教学部</option>--%>
<%--          <option>就业部</option>--%>
<%--      </select>--%>
  </div>
  <table id="deptInfo" class="deptInfo">
	  <tr class="titleRow">
		  <td>员工编号</td>
		  <td>员工姓名</td>
		  <td>性别</td>
		  <td>所属部门</td>
		  <td>入职时间</td>
		  <td>操作列表</td>
	  </tr>
  </table>
  <div style="width:250px;margin: 0 auto;">
      <input id="startPage" type="button" value="首页">
      <input id="lastPage"type="button" value="上一页">
      <input id="nextPage"type="button" value="下一页">
      <input id="endPage"type="button" value="末页">
  </div>
  </body>
</html>
