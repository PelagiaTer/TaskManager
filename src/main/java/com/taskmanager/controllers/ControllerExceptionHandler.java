package com.taskmanager.controllers;

import com.taskmanager.controllers.dto.AlertDto;
import com.taskmanager.controllers.dto.ResponseDto;
import com.taskmanager.exception.ForbiddenException;
import com.taskmanager.exception.NotFoundException;
import com.taskmanager.exception.ValidateException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ControllerExceptionHandler {

    @Nullable
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception exception, WebRequest request) {
        if (null == exception) {
            return null;
        }
        if (exception instanceof NotFoundException ex) {
            return handleNotFoundException(ex);
        } else if (exception instanceof ValidateException ex) {
            return handleValidateException(ex);
        } else if (exception instanceof ForbiddenException ex) {
            return handleForbiddenException(ex);
        }
        return handleDefaultException(exception);
    }

    private ResponseEntity<Object> handleNotFoundException(NotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ResponseDto(
                        new AlertDto(AlertDto.AlertType.DANGER, ex.getMessage(), ex.getErrorCode())));
    }

    private ResponseEntity<Object> handleValidateException(ValidateException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ResponseDto(
                        new AlertDto(AlertDto.AlertType.DANGER, ex.getMessage(), ex.getErrorCode())));
    }

    private ResponseEntity<Object> handleForbiddenException(ForbiddenException ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ResponseDto(
                        new AlertDto(AlertDto.AlertType.DANGER, ex.getMessage(), ex.getErrorCode())));
    }

    private ResponseEntity<Object> handleDefaultException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ResponseDto(
                        new AlertDto(AlertDto.AlertType.DANGER, ex.getMessage(), "INTERNAL_ERROR")));
    }
}
