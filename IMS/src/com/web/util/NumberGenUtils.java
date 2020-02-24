package com.web.util;

import com.web.constant.Constants;

import java.util.UUID;

/**
 * @Author zyb
 * @TIME 19-12-12
 **/
public class NumberGenUtils {
    /**
     * 生成报销单的编号
     * @return
     */
    public static String getExpenseNumber(){
        return Constants.EXPENSE_SUFFIX+ getUUIDInOrderId();
    }
    public static String getEmpNumber(){
        return Constants.EMP_SUFFIX+getUUIDInOrderId();
    }

    public static String getUUID(){
        return UUID.randomUUID().toString().replace("-","");
    }

    public static Integer getUUIDInOrderId(){
        Integer orderId=UUID.randomUUID().toString().hashCode();
        orderId = orderId < 0 ? -orderId : orderId; //String.hashCode() 值会为空
        return orderId;
    }
}
