package com.web.exception;

/**
 * @Author cy
 * @Time 19-12-16 下午1:58
 **/
public class PermissionDeleteFailException extends Exception {
    public PermissionDeleteFailException() {
    }

    public PermissionDeleteFailException(String message) {
        super(message);
    }

    public PermissionDeleteFailException(String message, Throwable cause) {
        super(message, cause);
    }

    public PermissionDeleteFailException(Throwable cause) {
        super(cause);
    }
}
