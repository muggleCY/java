<%--
  Created by IntelliJ IDEA.
  User: soft01
  Date: 19-12-12
  Time: 下午3:23
  To change this template use File | Settings | File Templates.
--%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>报销详细</title>
    <script type="text/javascript" src="<%=basePath%>js/jquery-1.8.3.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            var id = sessionStorage.getItem("expenseId");
            showOneDetail(id);
        });
        function showOneDetail(id){
            $.ajax({
                type: "POST",
                url: "<%=basePath%>expense/showOneRecById.do",
                data:{"id":id},
                success:function(msg){
                    $("#content").html("");
                    // console.log("报销编号:"+msg.expenseNum);
                    // console.log("申请人:"+msg.reqPerName);
                    // console.log("报销类型:"+msg.expenseTypeName);
                    // console.log("摘要:"+msg.summaryExp);
                    // console.log("金额:"+msg.expenseMoney);
                    // console.log("申请时间:"+msg.reqTime);
                    // console.log("申请状态:"+msg.reqStateName);
                    var $span = $('<span></span><br>');
                    $span.text("报销编号:"+msg.expenseNum);
                    var $span1 = $('<span></span><br>');
                    $span1.text("申请人:"+msg.reqPerName);
                    var $span2 = $('<span></span><br>');
                    $span2.text("报销类型:"+msg.expenseTypeName);
                    var $span3 = $('<span></span><br>');
                    $span3.text("摘要:"+msg.summaryExp);
                    var $span4 = $('<span></span><br>');
                    $span4.text("金额:"+msg.expenseMoney);
                    var $span5 = $('<span></span><br>');
                    $span5.text("申请时间:"+msg.reqTime);
                    var $span6 = $('<span></span><br>');
                    $span6.text("申请状态:"+msg.reqStateName);
                    $("#content").append($span).append($span1).append($span2).append($span3).append($span4)
                        .append($span5).append($span6);
                }
            });
        }

    </script>
</head>
<body>
<div id="content"></div>
</body>
</html>
