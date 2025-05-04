package com.taskmanager.exception;

import lombok.Getter;

@Getter
public class ValidateException extends RuntimeException {

    private final static String VALIDATE_ERROR_CODE = "VALIDATE_ERROR";

    private final String errorCode;

    public ValidateException(String message) {
        super(message);
        this.errorCode = VALIDATE_ERROR_CODE;
    }

    public ValidateException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
