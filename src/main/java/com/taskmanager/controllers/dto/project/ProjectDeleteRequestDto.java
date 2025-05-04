package com.taskmanager.controllers.dto.project;

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
@Schema(description = "Транспорт запроса на удаление проекта")
public class ProjectDeleteRequestDto {

    @NotNull
    @Schema(description = "Идентификатор проекта")
    private UUID id;
}
