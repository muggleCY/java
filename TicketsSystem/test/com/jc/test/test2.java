package com.jc.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class test2 {
    public static void main(String[] args) {
        String str = "2019-11-11 01:01";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = null;
        Date date1 = new Date();
        try {
            date =format.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (date1.before(date)){
            System.out.println("1111");
        }
        System.out.println(date);
    }
}
