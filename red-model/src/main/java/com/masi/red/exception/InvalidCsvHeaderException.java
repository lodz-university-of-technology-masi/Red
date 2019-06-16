package com.masi.red.exception;

public class InvalidCsvHeaderException extends Exception {
    public InvalidCsvHeaderException() {
        super();
    }

    public InvalidCsvHeaderException(String message) {
        super(message);
    }

    public InvalidCsvHeaderException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidCsvHeaderException(Throwable cause) {
        super(cause);
    }

    protected InvalidCsvHeaderException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
