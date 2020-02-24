<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>报销管理</title>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/reset.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/main.css">
    <script type="text/javascript" src="<%=basePath%>js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/layer/layer.js"></script>
	<script type="text/javascript">
        var currentPage;
		function del(index){
            layer.msg('确认要删除吗?', {
                time: 0 //不自动关闭
                ,btn: ['确定', '取消']
                ,yes: function(index){
                    layer.close(index);
                    // 获取需要删除的部门的id
                    var reqName = $(document.getElementById(index+"req")).text();
                    if (reqName=="已提交"){
                        layer.msg("不予删除已经提交的数据");
                        //alert("不予删除已经提交的数据");
                    }else {
                        $.ajax({
                            type: "POST",
                            url: "<%=basePath%>expense/deleteOneRecById.do",
                            data:{"id":index},
                            success:function(msg){
                                if (msg.state="200"){
                                    layer.msg("删除成功");
                                    //alert("删除成功");
                                    //删除成功之后更新表单
                                    repaintTable(1);
                                }else {
                                    layer.msg(msg.state);
                                    //alert(msg.state);
                                }
                            }
                        });
                    }
                }
            });
		}
        function showDetail(id) {
            sessionStorage.removeItem("expenseId");
            sessionStorage.setItem("expenseId",id);
            layer.open({
                type: 2,
                area: ['700px', '450px'],
                fixed: false, //不固定
                maxmin: true,
                content: '<%=basePath%>njwb/fund/expenseDetail.jsp'
            });
        }
        function editOrAdd(index) {
		    // 编辑页面和添加页面共用一个页面
            //如果传进来的inde是-1就代表是新增的
            // 如果是新增的不需要数据回显
                //删除session栈里可能有的id信息
            var reqName = $(document.getElementById(index+"req")).text();
            if (reqName=="已提交"){
                layer.msg("已提交不允许修改");
                return;
            }
            if (index!="-1"){
                sessionStorage.removeItem("EXPID");
                //将当前请假信息的id信息添加到session中
                sessionStorage.setItem("EXPID",index);
            }
            layer.open({
                type: 2,
                area: ['700px', '450px'],
                fixed: false, //不固定
                maxmin: true,
                content: '<%=basePath%>njwb/fund/modifyexpense.jsp'
            });
        }
        // 重新绘制表格
        function repaintTable(pageNo){
		    var indexBut = $("#indexBut");
		    var previousBut = $("#previousBut");
		    var nextBut = $("#nextBut");
		    var endPageBut = $("#endPageBut");
		    var typeNo = $("#type_no").val();
		    var stateNo = $("#state_no").val();

		    //如果是第一页数,首页和前一页不能点击
		    if (pageNo==1){
		        indexBut.attr("disabled",true);
                previousBut.attr("disabled",true);
            }else {
                indexBut.attr("disabled",false);
                previousBut.attr("disabled",false);
            }
            $("#expenseInfo").text("");
            $.ajax({
                type: "POST",
                    url: "<%=basePath%>expense/showAllExpense.do",
                data:{"pageNo":pageNo,"stateNo":stateNo,"typeNo":typeNo},
                success:function(msg){
                    currentPage=msg.pageNo;
                    if (msg.list.length ==0){
                        console.log(11);
                        indexBut.attr("disabled",false);
                        previousBut.attr("disabled",false);
                        nextBut.attr("disabled",false);
                        endPageBut.attr("disabled",false);
                    }
                    if (pageNo==msg.totalPag){
                        // 如果是最后一页,最后一页和下一页不能点击
                        nextBut.attr("disabled",true);
                        endPageBut.attr("disabled",true);
                    }else {
                        nextBut.attr("disabled",false);
                        endPageBut.attr("disabled",false);
                    }
                    //取得message后,给endbutton添加click时间
                    indexBut.attr("onclick","repaintTable(1)");
                    endPageBut.attr("onclick","repaintTable("+msg.totalPage+")");
                    previousBut.attr("onclick","repaintTable("+(msg.pageNo-1)+")");
                    nextBut.attr("onclick","repaintTable("+(msg.pageNo+1)+")");
                    var array = msg.list;
                    var table = $("#expenseInfo");
                    var title = $('  <tr class="titleRow">\n' +
                        '\t\t  <td>报销编号</td>\n' +
                        '\t\t  <td>申请人</td>\n' +
                        '\t\t  <td>报销类型</td>\n' +
                        '\t\t  <td>金额</td>\n' +
                        '\t\t  <td>申请时间</td>\n' +
                        '\t\t  <td>申请状态</td>\n' +
                        '\t\t  <td>操作列表</td>\n' +
                        '\t  </tr>');
                    table.append(title);
                    for(var i = 0; i < array.length; i++){
                        var subDiv = $('<tr></tr>');
                        subDiv.append('<td>' + array[i].expenseNum + '</td>');
                        subDiv.append('<td>' + array[i].reqPerName + '</td>');
                        var expenseTypeName = $('<td>' + array[i].expenseTypeName + '</td>');
                        subDiv.append(expenseTypeName);
                        subDiv.append('<td>' + array[i].expenseMoney + '</td>');
                        subDiv.append('<td>' + array[i].reqTime + '</td>');
                        var reqStateName = $('<td>' + array[i].reqStateName + '</td>');
                        reqStateName.attr("id",array[i].id+"req");
                        subDiv.append(reqStateName);
                        // 给图片添加点击事件
                        var iconTd = $("<td></td>");
                        var delImg = $('<img alt="" src="<%=basePath%>img/delete.png" class="operateImg">');
                        delImg.attr("onclick","del("+array[i].id+")");
                        var editImg = $('<img alt="" src="<%=basePath%>img/edit.png" class="operateImg" >');
                        editImg.attr("onclick","editOrAdd("+array[i].id+")");
                        var detailImg = $('<img alt="" src="<%=basePath%>img/detail.png" class="operateImg">');
                        detailImg.attr("onclick","showDetail("+array[i].id+")");
                        iconTd.append(delImg);
                        iconTd.append(editImg);
                        iconTd.append(detailImg);
                        subDiv.append(iconTd);
                        table.append(subDiv);
                    }
                }
            });
        }

        function searchByCondi(){
		    repaintTable(currentPage);
        }
        $(document).ready(function () {
            // 刚开始显示第一页
           repaintTable(1);
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
  <h1 class="title">首页  &gt;&gt;报销管理 </h1>

  <div class="add">
<%--	  <a  href="<%=basePath%>njwb/dept/employeeModify.jsp" target="contentPage"></a>--%>
          <img alt="" src="<%=basePath%>img/add.png" width="18px" height="18px" onclick="editOrAdd(-1)">申请报销
  </div>
  <div class="search">
      <span>报销类型</span>
      <select id="type_no">
          <option value="-1">请选择</option>
          <option value="1">差旅费</option>
          <option value="2">招待费用</option>
          <option value="3">办公费用</option>
      </select>
      <span>申请状态</span>
      <select id="state_no">
          <option value="-1">请选择</option>
          <option value="4">草稿</option>
          <option value="5">已提交</option>
      </select>
      <input type="button" value="查询" onclick="searchByCondi()">
  </div>
  <table id="expenseInfo" class="deptInfo">
  </table>
  <div style="width:250px;margin: 0 auto;">
      <input id="indexBut" type="button" value="首页">
      <input id="previousBut"type="button" value="上一页">
      <input id="nextBut"type="button" value="下一页">
      <input id="endPageBut"type="button" value="末页">
  </div>
  <button  hidden  id="r_r_r" onclick="repaintTable(3)"></button>
  </body>
</html>
