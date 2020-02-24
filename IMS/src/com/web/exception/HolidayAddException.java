package com.web.exception;

/**
 * @Author zyb
 * @TIME 19-12-12
 **/
public class HolidayAddException extends Exception {
    public HolidayAddException() {
    }

    public HolidayAddException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }

    public HolidayAddException(String arg0) {
        super(arg0);
    }

    public HolidayAddException(Throwable arg0) {
        super(arg0);
    }

}
