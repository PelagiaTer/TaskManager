package com.taskmanager.controllers.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Schema(description = "Базовый транспорт ответа")
public class ResponseDto {

    @Schema(description = "Сообщения для вывода на экран")
    private List<AlertDto> alerts;


    public ResponseDto(AlertDto alert) {
        if (alert != null) {
            this.alerts = List.of(alert);
        }
    }
}
