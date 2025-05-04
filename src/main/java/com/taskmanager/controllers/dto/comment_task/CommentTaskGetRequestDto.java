package com.taskmanager.controllers.dto.comment_task;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Транспорт запроса для получения комментариев задачи")
public class CommentTaskGetRequestDto {

    @Schema(description = "Идентификатор задачи")
    private UUID taskId;
}
