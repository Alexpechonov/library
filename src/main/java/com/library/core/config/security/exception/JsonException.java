package com.library.core.config.security.exception;

/**
 * Created by user on 17.07.2017.
 */
public class JsonException extends RuntimeException {

    public JsonException(final String message) {
        super(message);
    }

    public JsonException(final String message, Throwable exception) {
        super(message, exception);
    }
}
