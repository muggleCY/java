<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>请假管理</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/reset.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/main.css">
    <script type="text/javascript" src="<%=basePath%>js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/layer/layer.js"></script>

	<style type="text/css">
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

	<script type="text/javascript">
		var currentPage;
		var oldHolidayUser = 0;
		var oldHolidayType = 0;
		var oldHolidayStatus = 0;
		$(document).ready(function(){
			//loadDept();
			search(1);
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
						url:"<%=basePath%>holiday/deleteholiday.do",
						data:{"id":id},
						success:function (message) {
							var mes = JSON.parse(message);
							if (mes.state=="200"){
								layer.msg("删除成功");
								//alert("删除成功");
								//删除之后刷新页面
								search(1);
							}else{
								layer.msg(mes.state);
								//alert(mes.state);
							}
						}
					});
				}
			});
		}
		function search(pageNo) {
			var holidayUser = $("#holidayUser").val();
			var holidayType = $("#holidayType option:selected").val();
			var holidayStatus = $("#holidayStatus option:selected").val();
			if(holidayType == "请选择"){
				holidayType = 0;
			}
			if(holidayStatus == "请选择"){
				holidayStatus = 0;
			}
			if(oldHolidayUser != holidayUser||oldHolidayType != holidayType||oldHolidayStatus != holidayStatus){
				pageNo = 1;
			}

			oldHolidayUser = holidayUser;
			oldHolidayType = holidayType;
			oldHolidayStatus = holidayStatus;
			$.ajax({
				type: "POST",
				url: "<%=basePath%>holiday/getHolidaysByPage.do",
				data:{
					"pageNo": pageNo,
					"holidayUser": holidayUser,
					"holidayType": holidayType,
					"holidayStatus": holidayStatus
				},
				success:function(msg) {
					var pager = msg;
					currentPage = pager.pageNo;
					var datas = pager.list;
					console.log(datas);
					var resultTable = $("#deptInfo");
					resultTable.html($("table tr")[0]);
					for (var i = 0;i < datas.length;i++){
						var subDiv = $('<tr></tr>');
						subDiv.append('<td>' + datas[i].holidayNo + '</td>');
						subDiv.append('<td>' + datas[i].empName + '</td>');
						subDiv.append('<td>' + datas[i].configKey + '</td>');
						subDiv.append('<td >' + datas[i].holidayBz + '</td>');
						subDiv.append('<td>' + datas[i].startTime + '</td>');
						subDiv.append('<td>' + datas[i].endTime + '</td>');
						subDiv.append('<td>' + datas[i].configPageValue + '</td>');
						subDiv.append('<td>' + datas[i].createTime + '</td>');
						var iconTd = $("<td></td>");
						var delImg = $('<img alt="" src="<%=basePath%>img/delete.png" class="operateImg">');
						delImg.attr("onclick","del("+datas[i].id+")");
						var editImg = $('<img alt="" src="<%=basePath%>img/edit.png" class="operateImg" >');
						editImg.attr("onclick","edit("+datas[i].id+")");
						var detailImg = $('<img alt="" src="<%=basePath%>img/detail.png" class="operateImg">');
						detailImg.attr("onclick","detail("+datas[i].id+")");
						iconTd.append(delImg);
						iconTd.append(editImg);
						iconTd.append(detailImg);
						subDiv.append(iconTd);
						resultTable.append(subDiv);
					}
					for(;i<3;i++){
						var tr = $("<tr><td>&nbsp;</td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>")
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
			sessionStorage.setItem("id",id);
			layer.open({
				type: 2,
				area:['700px', '450px'],
				fixed: false, //不固定
				maxmin: true,
				content:"<%=basePath%>njwb/holiday/holidayEdit.jsp"
			})
			// 跳转到部门修改的地方
		}
		function detail(id) {
			sessionStorage.removeItem("id");
			sessionStorage.setItem("id",id);
			layer.open({
				type: 2,
				area:['700px', '450px'],
				fixed: false, //不固定
				maxmin: true,
				content:"<%=basePath%>njwb/holiday/holidayDetail.jsp"
			})
		}
		function add() {
			sessionStorage.removeItem("currentPage");
			sessionStorage.setItem("currentPage",currentPage);
			layer.open({
				type: 2,
				area:['700px', '450px'],
				fixed: false, //不固定
				maxmin: true,
				content:"<%=basePath%>njwb/holiday/holidayAdd.jsp"
			})
		}
	</script>
  </head>
  
  <body>
         	<h1 class="title">首页  &gt;&gt;员工管理 </h1>

			<div class="search">
				<span class="distance">申请人：</span><input type="text" id="holidayUser" >
				<span class="distance">请假类型：</span>
				<select id="holidayType">
					<option>请选择</option>
					<option value="11">事假</option>
					<option value="12">婚假</option>
					<option value="13">年假</option>
					<option value="14">调休</option>
					<option value="15">病假</option>
					<option value="16">丧假</option>
				</select>
				<span class="distance">申请状态：</span>
				<select id="holidayStatus">
					<option>请选择</option>
					<option value="4">草稿</option>
					<option value="5">提交</option>
				</select >
				<input class="distance" type="button" value="查询" onclick="search(1)">
			</div>
			<div class="add">
				<a target="contentPage" onclick="add()"><img alt="" src="<%=basePath%>img/add.png" width="18px" height="18px">添加请假</a>
			</div>

         	<table  id="deptInfo" class="deptInfo">
         		<tr class="titleRow">
         			<td>请假编号</td>
         			<td>申请人</td>
         			<td>请假类型</td>
         			<td>请假事由</td>
         			<td>开始时间</td>
         			<td>结束时间</td>
         			<td>申请状态</td>
         			<td>提交时间</td>
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
