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
@Schema(description = "Транспорт запроса создания комментария к задаче")
public class CommentTaskCreateRequestDto {

    @NotNull
    @Schema(description = "Идентификатор задачи")
    private UUID taskId;

    @NotNull
    @Schema(description = "Комментарий")
    private String content;

}
