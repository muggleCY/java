package com.web.exception;

/**
 * @Author cy
 * @Time 19-12-18 下午8:36
 **/
public class UserExistException extends Exception {
    public UserExistException() {
    }

    public UserExistException(String message) {
        super(message);
    }

    public UserExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserExistException(Throwable cause) {
        super(cause);
    }
}
