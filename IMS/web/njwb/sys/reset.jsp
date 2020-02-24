<%--
  Created by IntelliJ IDEA.
  User: soft01
  Date: 19-12-10
  Time: 下午2:44
  To change this template use File | Settings | File Templates.
--%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>重置密码</title>
    <script src="<%=basePath%>js/jquery-1.8.3.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/layer/layer.js"></script>
    <script type="text/javascript">
        var bathPath = window.location.origin+"/IMS/";
        function checkOldPwd() {
            var jsonPwd = {"oldPwd":$("#old_pwd").val()};
            var oldPwdTip = $("#old_pwd_tip");
            $.ajax({
                type:"POST",
                url:bathPath+"user/getOldPwd.do",
                data:jsonPwd,
                success:function (message) {
                    if (message.state!="200"){
                        layer.msg("原密码不正确");
                    }
                }
            });
        }
        function checkConfirm() {
            var newPwd = $("#new_pwd").val();
            var cofPwd = $("#cof_pwd").val();
            var conTip = $("#cof_pwd_tip");
            if (newPwd!=cofPwd){
                layer.msg("前后输入的密码不符合");
                // conTip.text("前后输入的密码不符合");
            }else {
                // conTip.text("");
            }
        }
        function changePwd() {
            $.ajax({
                type:"POST",
                url:bathPath+"user/reset.do",
                data:{"newPwd":$("#cof_pwd").val()},
                success:function (message) {
                    alert("修改密码成功,请从新登录");
                    // parent.layer.msg("修改密码成功,请从新登录");
                    // 清空登录状态,并返回主界面
                    $.ajax({
                        type:"POST",
                        url:bathPath+"user/clear.do",
                        success:function (message) {
                            if (message=="200"){
                                // layer.msg("退出成功");
                                alert("退出成功");
                                window.parent.location.href = bathPath+"njwb/login.jsp";
                            }
                        }
                    });
                }
            });
        }
    </script>
    <style type="text/css">
        .reset_div{
            width: 180px;
            margin: 0 auto;
            text-align: center;
        }
        .reset_input{
            margin: 10px 0;
        }
        .reset_btn{
            width: 80px;
            height: 25px;
            margin-top: 10px;
        }
    </style>
</head>
<body>

<div class="reset_div">
    <h4>密码重置</h4>
    <hr>
    <input class="reset_input" id="old_pwd" type="password" placeholder="原密码" required  onblur="checkOldPwd()">
    <br>
    <input class="reset_input" id="new_pwd" type="password" placeholder="新密码" required  onblur="">
    <br>
    <input class="reset_input" id="cof_pwd" type="password" placeholder="确认密码" required  onblur="checkConfirm()">
    <br>
    <input class="reset_btn" type="button" value="修改" onclick="changePwd()">
</div>

</body>
</html>
