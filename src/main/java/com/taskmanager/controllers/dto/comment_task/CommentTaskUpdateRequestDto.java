package com.taskmanager.controllers.dto.comment_task;

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
@Schema(description = "Транспорт запроса обновления комментария к задаче")
public class CommentTaskUpdateRequestDto {

    @NotNull
    @Schema(description = "Идентификатор комментария")
    private UUID id;

    @NotNull
    @Schema(description = "Идентификатор задачи")
    private UUID taskId;

    @NotNull
    @Schema(description = "Комментарий")
    private String content;
}
