package com.taskmanager.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

/**
 * Информация о комментариях задачи
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentTask {

    /**
     * Уникальный идентификатор комментария
     */
    private UUID id;

    /**
     * Пользователь добавивший комментарий
     */
    private UUID createUserId;

    /**
     * Пользователь изменивший комментарий
     */
    private UUID updateUserId;

    /**
     * Идентификатор задачи
     */
    private UUID taskId;

    /**
     * Комментарий
     */
    private String content;

    /**
     * Временная метка создания записи
     */
    private Instant dateCreate;

    /**
     * Временная метка изменения записи
     */
    private Instant dateUpdate;

}
