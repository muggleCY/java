package com.web.exception;

/**
 * @Author cy
 * @Time 19-12-18 下午8:27
 **/
public class EmpNoNotEXISTException extends Exception {
    public EmpNoNotEXISTException() {
    }

    public EmpNoNotEXISTException(String message) {
        super(message);
    }

    public EmpNoNotEXISTException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmpNoNotEXISTException(Throwable cause) {
        super(cause);
    }
}
