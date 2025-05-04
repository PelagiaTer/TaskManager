package com.taskmanager.controllers.dto.project;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Транспорт запроса на создание проекта")
public class ProjectCreateRequestDto {

    @NotNull
    @Schema(description = "Название проекта")
    private String name;

    @Schema(description = "Описание проекта")
    private String description;
}
