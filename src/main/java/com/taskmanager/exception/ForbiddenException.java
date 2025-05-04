package com.taskmanager.exception;

import lombok.Getter;

@Getter
public class ForbiddenException extends RuntimeException {

    private final static String FORBIDDEN_ERROR_CODE = "FORBIDDEN_ERROR";

    private final String errorCode;

    public ForbiddenException(String message) {
        super(message);
        this.errorCode = FORBIDDEN_ERROR_CODE;
    }

    public ForbiddenException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
