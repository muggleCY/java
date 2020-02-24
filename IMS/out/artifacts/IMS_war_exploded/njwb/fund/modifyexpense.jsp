<%--
  Created by IntelliJ IDEA.
  User: soft01
  Date: 19-12-12
  Time: 下午2:00
  To change this template use File | Settings | File Templates.
--%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改报销</title>
    <script type="text/javascript" src="<%=basePath%>js/jquery-1.8.3.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/layer/layer.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/reg.js"></script>
    <script type="text/javascript">
        var expType;
        var expCont;
        var expMoney;
        var addBtn;
        var resetBtn;
        var id;
        $(document).ready(function () {
            // 报销类型的选择框
             expType = $("#exp_type_no");
            // 摘要信息
             expCont = $("#exp_cont");
            //金额
             expMoney = $("#exp_money");
            // 添加按钮
             addBtn = $("#add_btn");
            //重置按钮
             resetBtn = $("#reset_btn");
             id = sessionStorage.getItem("EXPID");
            if (id!=null){
                // 如果id不是-1,需要数据回显,代表是修改信息,修改按钮的值为修改
                addBtn.val("修改");
                getInfoToRec(id);
            }
        });
        function getInfoToRec(id) {
            $.ajax({
                type: "POST",
                url: "<%=basePath%>expense/showOneRecById.do",
                data:{"id":id},
                success:function(msg){
                    expType.val(msg.expenseTypeId);
                    expCont.val(msg.summaryExp);
                    expMoney.val(msg.expenseMoney);
                }
            });
        }
        function clearInput() {
            expType.val(-1);
            expCont.val("");
            expMoney.val("");
        }

        function changeOrAdd(type) {
            $.ajax({
                type: "POST",
                url: "<%=basePath%>expense/addOneExpense.do",
                data:{"expType":expType.val(),
                    "expCont":expCont.val(),"expMoney":expMoney.val(),"state":type},
                success:function(msg){
                    mes = msg;
                    if (mes.state=="200"){
                        //添加成功之后刷新父页面
                        //关闭当前页面
                        var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
                        parent.repaintTable(1);
                        parent.layer.msg("添加成功");
                        parent.layer.close(index);
                    }else{
                        layer.msg(mes.state);
                    }
                }
            });


            // var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
            // parent.repaintTable(2);
            // parent.layer.close(index);
        }
    </script>
</head>
<body>
<span>报销类型</span>
<select id="exp_type_no">
    <option value="-1">请选择</option>
    <option value="1">差旅费</option>
    <option value="2">招待费用</option>
    <option value="3">办公费用</option>
</select>

<br>

<sapn>摘要</sapn>
<textarea id="exp_cont" class="inputArea" ></textarea>

<br>

<span>金额</span>
<input type="text" id="exp_money" class="inputVal" name="money">

<br>
<input id="add_btn" type = "submit" value="添加" onclick="changeOrAdd(5)"/>
<input type = "button" value="草稿" onclick="changeOrAdd(4)"/>
<input id="reset_btn" type = "reset" value="重置" onclick="clearInput()"/>
</body>
</html>
