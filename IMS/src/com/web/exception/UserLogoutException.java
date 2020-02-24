package com.web.exception;

/**
 * @Author cy
 * @Time 19-12-18 下午8:39
 **/
public class UserLogoutException extends Exception {
    public UserLogoutException() {
    }

    public UserLogoutException(String message) {
        super(message);
    }

    public UserLogoutException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserLogoutException(Throwable cause) {
        super(cause);
    }
}
