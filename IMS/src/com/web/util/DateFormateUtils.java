package com.web.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author zyb
 * @Date 2019/12/14 17:31
 **/
public class DateFormateUtils {
    static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    //        将date类型转为string
    public static String getStringTimeFromObj(Object obj){
        return sdf.format(obj);
    }
//    返回当前时间的字符串表达
    public static String getNowTime(){
        return sdf.format(new Date());
    }
}
