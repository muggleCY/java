package com.web.exception;

/**
 * @Author cy
 * @Time 19-12-12 下午1:31
 **/
public class HolidayIsSubmitException extends Exception {
    public HolidayIsSubmitException() {
    }

    public HolidayIsSubmitException(String message) {
        super(message);
    }

    public HolidayIsSubmitException(String message, Throwable cause) {
        super(message, cause);
    }

    public HolidayIsSubmitException(Throwable cause) {
        super(cause);
    }
}
