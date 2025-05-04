package com.taskmanager.controllers.dto.task;

import com.taskmanager.enams.TaskPriority;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Транспорт запроса создания задачи")
public class TaskCreateRequestDto {

    @NotNull
    @Schema(description = "Название задачи", example = "Новая задача")
    private String title;

    @Schema(description = "Описание задачи", example = "Это описание новой задачи")
    private String description;

    @NotNull
    @Schema(description = "Приоритет", example = "LOW")
    private TaskPriority priority;

    @Schema(description = "Дата выполнения задачи предварительная/оценочная")
    private Instant dateDue;

    @Schema(description = "Идентификатор проекта к которому относиться задача")
    private UUID projectId;

    @Schema(description = "Идентификатор пользователя на которого назначена задача")
    private UUID assignedUserId;

}
