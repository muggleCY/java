//正确的汉字正则，只有chorme浏览器支持
// var Reg = /\p{Unified_Ideograph}/u;
//错误的汉字正则，只能匹配中文


var submitState=true;

//正则数组
var regArray = new Array();
//提示信息数组
var tipsArray = new Array();
//员工姓名
var empNameReg = /^[\u4e00-\u9fa5]{2,4}$/;
var empNameTip = "姓名为2到4位汉字";
regArray[0]=empNameReg;
tipsArray[0]=empNameTip;

//部门名称
var deptNameReg = /^[\u4e00-\u9fa5]{3,5}$/;
var deptNameTip = "部门名称是3到5位汉字";
regArray[1]=deptNameReg;
tipsArray[1]=deptNameTip;

var deptLocReg = /^[0-9a-zA-Z]{3,5}$/;
var deptLocTip = "部门位置是3到5位字母和数字";
regArray[2]= deptLocReg;
tipsArray[2]=deptLocTip;


//请假理由
var holidayReasonReg = /^[\u4e00-\u9fa5]{2,10}$/;
var holidayReasonTip = "请假理由是2到10位汉字";
regArray[3]=holidayReasonReg;
tipsArray[3]=holidayReasonTip;


var passwordReg = /^[0-9a-zA-Z]{3,10}$/;
regArray[4]=passwordReg;

var passwordTip = "密码为3-10位数字和字母";
tipsArray[4]=passwordTip;

var usernameReg = /^[a-zA-Z0-9]{4,7}$/;
regArray[5]=usernameReg;
var usernameTip = "用户名为4-7位字母和数字";
tipsArray[5]=usernameTip;

var moneyReg = /^\d+(\.\d{1,2})?$/;
regArray[6]=moneyReg;
var moneyTips = "请输入正确的金额";
tipsArray[6]=moneyTips;

var inputAreaTips = "除了逗号之外的特殊字符将不会被记录";

function removeSpecialCode(s)
{
    var pattern = new RegExp("[%--`~!@#$^&*()=|{}':;'\\[\\].<>/?~！@#￥……&*（）——|{}【】‘；：”“'。、？]")        //格式 RegExp("[在中间定义特殊过滤字符]")
    var rs = "";
    for (var i = 0; i < s.length; i++) {
        rs = rs+s.substr(i, 1).replace(pattern, '');
    }
    return rs;
}

//批量给所有的Input标签，和textArea标签，添加onblur事件
function addOnblur() {
    //给Input标签做验证
    $(".inputVal").each(
        function(i){
            $(this).blur(
                function(){
                    var ele = $(this);
                    var name = ele.attr("name");
                    //如果是用户名
                    if (name=="userName"){
                        if (!regArray[5].test(ele.val())){
                            layer.msg(tipsArray[5]);
                            submitState =false;
                        }else {
                            submitState = true;
                        }
                        return;
                    }
                    //如果是密码
                    if (name=="password"){
                        if (!regArray[4].test(ele.val())){
                            layer.msg(tipsArray[4]);
                        }
                        return;
                    }
                    //如果是deptName
                    if (name=="deptName"){
                        if (!regArray[1].test(ele.val())){
                            layer.msg(tipsArray[1]);
                        }
                        return;
                    }
                    //如果是deptLoc
                    if (name=="deptLoc"){
                        if (!regArray[2].test(ele.val())){
                            layer.msg(tipsArray[2]);
                        }
                        return;
                    }
                    //如果是deptMaster,deptMaster表示部门负责人,使用姓名的正则
                    if (name=="deptMaster"){
                        if (!regArray[0].test(ele.val())){
                            layer.msg(tipsArray[0]);
                        }
                        return;
                    }
                    //如果是金额
                    if (name=="money"){
                        if (!regArray[6].test(ele.val())){
                            layer.msg(tipsArray[6]);
                        }
                    }

                }
            );
        }
    );
    //给textArea做验证
    $(".inputArea").each(
        function () {
            //给textArea添加placeHolder
            var ele = $(this);
                ele.attr("placeHolder",inputAreaTips);
        }
    );
}
$(document).ready(function () {
    addOnblur();
});