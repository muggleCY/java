package com.jc.constant;

public class Constants {
    public static final Integer PAGE_SIZE_1= 1;
    public static final Integer PAGE_SIZE_3= 3;
    public static final Integer PAGE_SIZE_5= 5;
    public static final String REGEX_PHONENUMBER = "^1[3|4|5|7|8][0-9]\\d{8}$";
    public static final String REGEX_IDCARD = "^[1-9]\\d{5}(19|20)\\d{2}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([\\d|x|X]{1})$";
    public static final String REGEX_MONEY = "^[0-9]+([.]{1}[0-9]{0,2}){0,1}$";
    public static final String REGEX_NUMBER = "[0-9]+";
    public static final String REGEX_DATETIME = "\\d{4}\\-(0?[1-9]|[1][012])\\-(0?[1-9]|[12][0-9]|3[01])\\s+(20|21|22|23|[0-1]\\d):[0-5]\\d:[0-5]\\d";
}
