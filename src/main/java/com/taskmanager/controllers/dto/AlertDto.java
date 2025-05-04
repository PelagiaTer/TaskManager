package com.taskmanager.controllers.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Schema(description = "Транспорт сообщений/предупреждений/ошибок")
public class AlertDto {

    @Schema(description = "Тип сообщения")
    public AlertType type;

    @Schema(description = "Сообщение для показа пользователю на экране")
    public String msg;

    @Schema(description = "Код ошибки")
    public String errorCode;

    public AlertDto(AlertType type, String msg, String errorCode) {
        this.type = type;
        this.msg = msg;
        this.errorCode = errorCode;
    }

    public AlertDto(String msg) {
        this.type = AlertType.SUCCESS;
        this.msg = msg;
    }


    @Schema(description = "Допустимые типы сообщений")
    public enum AlertType {
        SUCCESS,
        INFO,
        WARNING,
        DANGER
    }
}
