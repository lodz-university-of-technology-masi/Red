package com.masi.red.exception;

public class NoTestsAvailableException extends Exception {
    public NoTestsAvailableException() {
    }

    public NoTestsAvailableException(String message) {
        super(message);
    }

    public NoTestsAvailableException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoTestsAvailableException(Throwable cause) {
        super(cause);
    }

    public NoTestsAvailableException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
