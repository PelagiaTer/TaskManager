package com.taskmanager.controllers.dto.comment_task;

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
@Schema(description = "Транспорт информации о комментарии задачи")
public class CommentTaskDto {

    @Schema(description = "Уникальный идентификатор комментария")
    private UUID id;

    @Schema(description = "Идентификатор пользователя добавившего комментарий")
    private UUID createUserId;

    @Schema(description = "Имя пользователя добавившего комментарий")
    private String createUserName;

    @Schema(description = "Идентификатор пользователя изменившего комментарий")
    private UUID updateUserId;

    @Schema(description = "Идентификатор пользователя изменившего комментарий")
    private String updateUserName;

    @Schema(description = "Идентификатор задачи")
    private UUID taskId;

    @Schema(description = "Комментарий")
    private String content;

    @Schema(description = "Временная метка создания записи")
    private Instant dateCreate;

    @Schema(description = "Временная метка изменения записи")
    private Instant dateUpdate;
}
