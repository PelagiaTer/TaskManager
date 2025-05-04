package com.taskmanager.controllers.dto.task;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Транспорт запроса на получение информации о задаче")
public class TaskInfoRequestDto {

    @NotNull
    @Schema(description = "Идентификатор задачи")
    private UUID id;
}
