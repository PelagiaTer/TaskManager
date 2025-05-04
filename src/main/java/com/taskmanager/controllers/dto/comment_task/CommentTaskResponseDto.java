package com.taskmanager.controllers.dto.comment_task;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Транспорт ответа получения комментариев задачи")
public class CommentTaskResponseDto {

    @Schema(description = "Список комментариев")
    private List<CommentTaskDto> items;
}
