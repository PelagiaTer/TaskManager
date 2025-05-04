package com.taskmanager.controllers.dto.task;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Транспорт ответа поиска задач")
public class TaskResponseDto {

    @Schema(description = "Список найденных задач")
    private List<TaskDto> items;
}
