package com.web.exception;

/**
 * @Author cy
 * @Time 19-12-15 下午3:22
 **/
public class RoleDeleteFailException extends Exception {
    public RoleDeleteFailException() {
    }

    public RoleDeleteFailException(String message) {
        super(message);
    }

    public RoleDeleteFailException(String message, Throwable cause) {
        super(message, cause);
    }

    public RoleDeleteFailException(Throwable cause) {
        super(cause);
    }
}
