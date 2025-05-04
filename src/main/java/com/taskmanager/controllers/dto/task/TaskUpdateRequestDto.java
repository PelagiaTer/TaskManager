package com.taskmanager.controllers.dto.task;

import com.taskmanager.enams.TaskPriority;
import com.taskmanager.enams.TaskStatus;
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
@Schema(description = "Транспорт запроса обновления задачи")
public class TaskUpdateRequestDto {

    @NotNull
    @Schema(description = "Уникальный идентификатор задачи")
    private UUID id;

    @NotNull
    @Schema(description = "Название задачи")
    private String title;

    @Schema(description = "Описание задачи")
    private String description;

    @NotNull
    @Schema(description = "Статус задачи")
    private TaskStatus status;

    @NotNull
    @Schema(description = "Приоритет")
    private TaskPriority priority;

    @Schema(description = "Дата выполнения задачи предварительная/оценочная")
    private Instant dateDue;

    @Schema(description = "Идентификатор проекта к которому относиться задача")
    private UUID projectId;

    @Schema(description = "Идентификатор пользователя на которого назначена задача")
    private UUID assignedUserId;

}
