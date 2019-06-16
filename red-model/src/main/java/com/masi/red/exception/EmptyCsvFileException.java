package com.masi.red.exception;

public class EmptyCsvFileException extends Exception {
    public EmptyCsvFileException() {
        super();
    }

    public EmptyCsvFileException(String message) {
        super(message);
    }

    public EmptyCsvFileException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmptyCsvFileException(Throwable cause) {
        super(cause);
    }

    protected EmptyCsvFileException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
