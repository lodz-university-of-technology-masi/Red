package com.masi.red.exception;

public class ResourceAccessForbiddenException extends Exception {
    public ResourceAccessForbiddenException() {
        super();
    }

    public ResourceAccessForbiddenException(String message) {
        super(message);
    }

    public ResourceAccessForbiddenException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResourceAccessForbiddenException(Throwable cause) {
        super(cause);
    }

    protected ResourceAccessForbiddenException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
