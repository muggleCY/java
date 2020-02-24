package com.web.exception;

/**
 * @Author cy
 * @Time 19-12-16 下午3:19
 **/
public class PremissionAlreadyExistException extends Exception{
    public PremissionAlreadyExistException() {
    }

    public PremissionAlreadyExistException(String message) {
        super(message);
    }

    public PremissionAlreadyExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public PremissionAlreadyExistException(Throwable cause) {
        super(cause);
    }
}
