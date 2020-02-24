// 全局定义验证码
var code;
//显示验证码的部件
var showCodeEle;
// 生成验证码
//获取bathPath
var bathPath = window.location.origin+"/IMS/";
function getVeriCode() {
    code = "";
    var codeLength = 4;
    var radom =new Array(0,1,2,3,4,5,6,7,8,9,'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R',
        'S','T','U','V','W','X','Y','Z');
    for (var i = 0; i < codeLength; i++) {
        // 随机数
        var index = Math.floor(Math.random()*36);
        code+=radom[index];
    }
}

// 刷新验证码
function refeshCode() {
    getVeriCode();
    showCodeEle.text(code);
}
//校验验证码
function validate(){
    // 取得输入的验证码并转化为大写
    var inputEle = $("#return_code");
    var inputCode = inputEle.val().toUpperCase();
    if(inputCode.length <= 0) { //若输入的验证码长度为0
        layer.msg("请输入验证码！");
        return false;
        //则弹出请输入验证码
    }else if(inputCode != code ) { //若输入的验证码与产生的验证码不一致时
        layer.msg("验证码输入错误！@_@"); //则弹出验证码输入错误
       refeshCode();
        // 清空文本框
        inputEle.val("");
        return false;
    }
    return true;
}
function clearInput() {
    $("#userName").val("");
    $("#password").val("");
    $("#return_code").val("");
    refeshCode();
}

//登录功能
function login(){
    //如果是false，代表有错误的信息
    if (!submitState){
        layer.msg("请输入正确的信息");
        return;
    }
    if (!validate()){
        return;
    }

    //获取用户名和密码
    var username = $("#userName").val();
    var password = $("#password").val();
    var jsonData = {"username":username,"password":password};

    //校验验证码
    validate();
    //通过ajax与后台交换数据
    $.ajax({
        type:"POST",
        url:bathPath+"user/login.do",
        data:jsonData,
        success:function (message) {
            // 登录成功
            if (message.state=="true"){
                window.location.href=bathPath+"njwb/main.jsp";
                // 登录失败
            }else {
                layer.msg(message.state);
                //清空表单
                clearInput();
            }
        }
    });
}

$(document).ready(function () {
    showCodeEle= $("#code");
    // 生成验证码
    refeshCode();
});

