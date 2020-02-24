package com.web.util;

import com.web.constant.Constants;

/**
 * @Author zyb
 * @Date 2019/12/17 20:47
 **/
public class GetNoUtils {
    /**
     * 获得员工编号
     * @param preMaxNo
     * @return
     */
    public static String  getEmpNo(Integer preMaxNo){
//        第一个数字代表前面补充0，第二个数字代表长度为4，d代表参数为正数
        return Constants.EMP_SUFFIX + String.format("%04d", ++preMaxNo);
    }

    /**
     * 部门编号
     * @param preMaxNo
     * @return
     */
    public static String getDeptNo(Integer preMaxNo){
        return Constants.DEPT_SUFFIX+String.format("%04d", ++preMaxNo);
    }

    public static String getExpNO(Integer preMaxNo){
        return Constants.EXPENSE_SUFFIX+String.format("%04d", ++preMaxNo);
    }
}
