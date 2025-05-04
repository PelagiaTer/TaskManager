package com.taskmanager.controllers.dto.project;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Транспорт запроса на получение списка проектов")
public class ProjectGetRequestDto {

    @Schema(description = "Признак, получения проектов созданных данным пользователем")
    private Boolean isMyCreateProject;

    @Schema(description = "Лимит")
    private int limit;

    @Schema(description = "Сдвиг")
    private int offset;
}
