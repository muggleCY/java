//获取bathPath
var basePath = window.location.origin+"/IMS/";
function clearLoginState() {
    // 清空登录状态
    $.ajax({
        type:"POST",
        url:basePath+"user/clear.do",
        success:function (message) {
            if (message=="200"){
                alert("退出成功");
                window.location.href = basePath+"njwb/login.jsp";
            }
        }
    });
}
