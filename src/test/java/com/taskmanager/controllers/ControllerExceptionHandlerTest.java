package com.taskmanager.controllers;

import com.taskmanager.controllers.dto.AlertDto;
import com.taskmanager.controllers.dto.ResponseDto;
import com.taskmanager.exception.ForbiddenException;
import com.taskmanager.exception.NotFoundException;
import com.taskmanager.exception.ValidateException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ControllerExceptionHandlerTest.TestConfiguration.class)
class ControllerExceptionHandlerTest {

    @Autowired
    private ControllerExceptionHandler controllerExceptionHandler;

    @Test
    void handleException_notFound() {
        var exception = new NotFoundException("msg");
        var expected = ResponseEntity.status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ResponseDto(
                        new AlertDto(AlertDto.AlertType.DANGER, "msg", "ENTITY_NOT_FOUND")));

        var actual = controllerExceptionHandler.handleException(exception, null);

        assertNotNull(actual);
        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    void handleException_validate() {
        var exception = new ValidateException("msg");
        var expected = ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ResponseDto(
                        new AlertDto(AlertDto.AlertType.DANGER, "msg", "VALIDATE_ERROR")));

        var actual = controllerExceptionHandler.handleException(exception, null);

        assertNotNull(actual);
        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    void handleException_forbidden() {
        var exception = new ForbiddenException("msg");
        var expected = ResponseEntity.status(HttpStatus.FORBIDDEN)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ResponseDto(
                        new AlertDto(AlertDto.AlertType.DANGER, "msg", "FORBIDDEN_ERROR")));

        var actual = controllerExceptionHandler.handleException(exception, null);

        assertNotNull(actual);
        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    void handleException_default() {
        var exception = new Exception("msg");
        var expected = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ResponseDto(
                        new AlertDto(AlertDto.AlertType.DANGER, "msg", "INTERNAL_ERROR")));

        var actual = controllerExceptionHandler.handleException(exception, null);

        assertNotNull(actual);
        assertEquals(expected.toString(), actual.toString());
    }

    @Configuration
    public static class TestConfiguration {
        @Bean
        public ControllerExceptionHandler controllerExceptionHandler() {
            return new ControllerExceptionHandler();
        }
    }
}