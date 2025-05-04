package com.taskmanager.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

/**
 * Информация о задаче
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Task {

    /**
     * Уникальный идентификатор задачи
     */
    private UUID id;

    /**
     * Номер задачи
     */
    private Long number;

    /**
     * Название задачи
     */
    private String title;

    /**
     * Описание задачи
     */
    private String description;

    /**
     * Статус задачи
     */
    private String status;

    /**
     * Приоритет
     */
    private String priority;

    /**
     * Дата создания
     */
    private Instant dateCreate;

    /**
     * Дата выполнения задачи предварительная/оценочная
     */
    private Instant dateDue;

    /**
     * Дата выполнения задачи фактическая, когда закрыли задачу
     */
    private Instant dateFact;

    /**
     * Идентификатор проекта к которому относиться задача
     */
    private UUID projectId;

    /**
     * Идентификатор пользователя на которого назначена задача
     */
    private UUID assignedUserId;

    /**
     * Идентификатор пользователя который создал задачу
     */
    private UUID createUserId;

}
