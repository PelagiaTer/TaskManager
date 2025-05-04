package com.taskmanager.exception;

import lombok.Getter;

@Getter
public class NotFoundException extends RuntimeException {

    private final static String ENTITY_NOT_FOUNT_ERROR_CODE = "ENTITY_NOT_FOUND";

    private final String errorCode;

    public NotFoundException(String message) {
        super(message);
        this.errorCode = ENTITY_NOT_FOUNT_ERROR_CODE;
    }

    public NotFoundException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
