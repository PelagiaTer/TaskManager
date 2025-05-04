package com.taskmanager.controllers.dto.task;

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
@Schema(description = "Транспорт запроса для фильтра поиска задач")
public class FilterTaskRequestDto {

    @Schema(description = "Номер задачи")
    private Long number;

    @Schema(description = "Статус задачи")
    private String status;

    @Schema(description = "Приоритет")
    private String priority;

    @Schema(description = "Идентификатор пользователя на которого назначена задача")
    private UUID assignedUserId;

    @Schema(description = "Идентификатор пользователя который создал задачу")
    private UUID createUserId;

    @Schema(description = "Лимит")
    private Integer limit;

    @Schema(description = "Сдвиг")
    private Integer offset;
}
