package com.taskmanager.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FilterTask {

    /**
     * Номер задачи
     */
    private Long number;

    /**
     * Статус задачи
     */
    private String status;

    /**
     * Приоритет
     */
    private String priority;

    /**
     * Идентификатор пользователя на которого назначена задача
     */
    private UUID assignedUserId;

    /**
     * Идентификатор пользователя который создал задачу
     */
    private UUID createUserId;

    /**
     * Лимит
     */
    private Integer limit;

    /**
     * Сдвиг
     */
    private Integer offset;
}
