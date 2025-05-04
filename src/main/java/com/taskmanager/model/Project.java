package com.taskmanager.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * Информация о проекте
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Project {

    /**
     * Уникальный идентификатор проекта
     */
    private UUID id;

    /**
     * Название проекта
     */
    private String name;

    /**
     * Описание проекта
     */
    private String description;

    /**
     * Идентификатор пользователя создавшего проект
     */
    private UUID createUserId;

    /**
     * Идентификатор пользователя изменившего проект
     */
    private UUID updateUserId;
}
