package com.taskmanager.controllers.dto.project;

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
@Schema(description = "Транспорт ответа получения списка проектов")
public class ProjectGetAllResponseDto {

    @Schema(description = "Список проектов")
    private List<ProjectGetDto> items;
}
