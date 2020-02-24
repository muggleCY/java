package com.web.exception;

/**
 * @Author cy
 * @Time 19-12-17 下午8:34
 **/
public class HolidayInsertFailException extends Exception {
    public HolidayInsertFailException() {
    }

    public HolidayInsertFailException(String message) {
        super(message);
    }

    public HolidayInsertFailException(String message, Throwable cause) {
        super(message, cause);
    }

    public HolidayInsertFailException(Throwable cause) {
        super(cause);
    }
}
