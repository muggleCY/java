package com.web.exception;

/**
 * @Author cy
 * @Time 19-12-18 下午2:56
 **/
public class RoleAlreadyExistException extends Exception {
    public RoleAlreadyExistException() {
    }

    public RoleAlreadyExistException(String message) {
        super(message);
    }

    public RoleAlreadyExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public RoleAlreadyExistException(Throwable cause) {
        super(cause);
    }
}
