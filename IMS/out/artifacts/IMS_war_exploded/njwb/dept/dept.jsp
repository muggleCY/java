<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>部门管理</title>
	
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="this is my page">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/reset.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/main.css">
    <script type="text/javascript" src="<%=basePath%>js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/layer/layer.js"></script>

      <style>
          .add{
              top:45px;
          }
      </style>
	
	<script type="text/javascript">
        $(document).ready(function () {
            loadDept();
        });
        //打开修改页面
        function openEdit() {
            // 采用layerjs跳出页面
            layer.open({
                type: 2,
                area: ['700px', '450px'],
                fixed: false, //不固定
                maxmin: true,
                content: '<%=basePath%>njwb/dept/deptEdit.jsp'
            });
        }
        function addDept() {
            // 采用layerjs跳出页面
            openEdit();
        }

        function detail(id) {
            //根据id展示详细信息(待续)
        }

        function edit(id) {
            // 跳转到部门修改的地方
            sessionStorage.removeItem("id");
            sessionStorage.setItem("id",id);
            openEdit();
        }
		function del(id){
            layer.msg('确认要删除吗?', {
                time: 0 //不自动关闭
                ,btn: ['确定', '取消']
                ,yes: function(index){
                    layer.close(index);
                        // 获取需要删除的部门的id
                        $.ajax({
                            type:"POST",
                            url:"<%=basePath%>dept/deleteOneDept.do",
                            data:{"id":id},
                            success:function (message) {
                                mes = message;
                                if (mes.state=="200"){
                                    layer.msg("删除成功");
                                    //alert("删除成功");
                                    //删除之后刷新页面
                                    loadDept();
                                }else{
                                    layer.msg(mes.state);
                                    // alert(mes.state);
                                }
                            }
                        });
                    }
            });
			<%--var result = window.confirm("确认要删除吗?");--%>
			<%--if(true == result){--%>
			<%--    // 获取需要删除的部门的id--%>
            <%--    $.ajax({--%>
            <%--        type:"POST",--%>
            <%--        url:"<%=basePath%>dept/deleteOneDept.do",--%>
            <%--        data:{"id":id},--%>
            <%--        success:function (message) {--%>
            <%--            var mes = JSON.parse(message);--%>
            <%--            if (mes.state=="200"){--%>
            <%--                layer.msg("删除成功");--%>
            <%--                //alert("删除成功");--%>
            <%--                //删除之后刷新页面--%>
            <%--                loadDept();--%>
            <%--            }else{--%>
            <%--                layer.msg(mes.state);--%>
            <%--                //alert(mes.state);--%>
            <%--            }--%>
            <%--        }--%>
            <%--    });--%>
			<%--}--%>
		}
		// 重新绘制表格
		function loadDept(){
		    // 清空表格
		    $("#deptInfo").text("");
            $.ajax({
                type: "POST",
                url: "<%=basePath%>dept/showDepts.do",
                success:function(msg){
                    var array = eval(msg);
                    var table = $("#deptInfo");
                    var title = $('\t  <tr class="titleRow">\n' +
                        '\t\t  <td>部门编号</td>\n' +
                        '\t\t  <td>部门名称</td>\n' +
                        '\t\t  <td>部门位置</td>\n' +
                        '\t\t  <td>部门负责人</td>\n' +
                        '\t\t  <td>操作列表</td>\n' +
                        '\t  </tr>');
                    table.append(title);
                    for(var i = 0; i < array.length; i++){
                        var subDiv = $('<tr></tr>');
                        subDiv.append('<td>' + array[i].deptNo + '</td>');
                        subDiv.append('<td>' + array[i].deptName + '</td>');
                        subDiv.append('<td>' + array[i].deptLoc + '</td>');
                        subDiv.append('<td>' + array[i].deptManagerName + '</td>');
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
                    }
                }
            });
        }
	</script>
  </head>
  <body>
  <h1 class="title">首页  &gt;&gt;部门管理 </h1>

  <div class="add">
	  <img alt="" src="<%=basePath%>img/add.png" width="18px" height="18px" onclick="addDept()">添加部门
  </div>
  <table id="deptInfo" class="deptInfo">
  </table>
  </body>
</html>
