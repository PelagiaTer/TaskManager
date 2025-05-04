package com.taskmanager.controllers.dto.task;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "Транспорт информации о задачи")
public class TaskDto {

    @Schema(description = "Уникальный идентификатор задачи")
    private UUID id;

    @Schema(description = "Номер задачи")
    private Long number;

    @Schema(description = "Название задачи")
    private String title;

    @Schema(description = "Описание задачи")
    private String description;

    @Schema(description = "Статус задачи")
    private String status;

    @Schema(description = "Приоритет")
    private String priority;

    @Schema(description = "Дата создания")
    private Instant dateCreate;

    @Schema(description = "Дата выполнения задачи предварительная/оценочная")
    private Instant dateDue;

    @Schema(description = "Дата выполнения задачи фактическая, когда закрыли задачу")
    private Instant dateFact;

    @Schema(description = "Идентификатор проекта к которому относиться задача")
    private UUID projectId;

    @Schema(description = "Название проекта")
    private String projectName;

    @Schema(description = "Идентификатор пользователя на которого назначена задача")
    private UUID assignedUserId;

    @Schema(description = "Имя пользователя на которого назначена задача")
    private String assignedUserName;

    @Schema(description = "Идентификатор пользователя который создал задачу")
    private UUID createUserId;

    @Schema(description = "Имя пользователя который создал задачу")
    private String createUserName;

}
