package com.library.core.mvc.service.exception;

/**
 * Created by user on 25.07.2017.
 */
public class AccessException extends RuntimeException{

    public AccessException() {
    }

    public AccessException(String message) {
        super(message);
    }

    public AccessException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccessException(Throwable cause) {
        super(cause);
    }
}
