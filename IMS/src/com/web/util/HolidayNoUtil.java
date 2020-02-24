package com.web.util;

import com.web.dao.HolidayDao;
import com.web.dao.impl.HolidayDaoImpl;

/**
 * @Author cy
 * @Time 19-12-17 下午8:57
 **/
public class HolidayNoUtil {
    private static String nextHolidayNo;

    public static String getNexHolidayNo(){
        if(nextHolidayNo == null){
            HolidayDao holidayDao = new HolidayDaoImpl();
            String holidayNo = holidayDao.getMaxHolidayNo();
            nextHolidayNo = holidayNo.substring(3);
            Integer in = Integer.valueOf(nextHolidayNo)+1;
            int len = in.toString().length();
            if(len == 1){
                nextHolidayNo = "00"+in;
            }else if(len == 2){
                nextHolidayNo = "0"+in;
            }else if(len == 3){
                nextHolidayNo = in.toString();
            }
        }else{
            Integer in = Integer.valueOf(nextHolidayNo)+1;
            int len = in.toString().length();
            if(len == 1){
                nextHolidayNo = "00"+in;
            }else if(len == 2){
                nextHolidayNo = "0"+in;
            }else if(len == 3){
                nextHolidayNo = in.toString();
            }
        }
        return "QJ1"+nextHolidayNo;
    }
}
