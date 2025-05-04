package com.taskmanager.controllers.dto.project;

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
@Schema(description = "Транспорт получения проекта")
public class ProjectGetDto {

    @Schema(description = "Уникальный идентификатор проекта")
    private UUID id;

    @Schema(description = "Название проекта")
    private String name;

    @Schema(description = "Описание проекта")
    private String description;

    @Schema(description = "Идентификатор пользователя создавшего проект")
    private UUID createUserId;

    @Schema(description = "Логин пользователя создавшего проект")
    private String createUserLogin;

    @Schema(description = "Идентификатор пользователя изменившего проект")
    private UUID updateUserId;

    @Schema(description = "Логин пользователя изменившего проект")
    private String updateUserLogin;
}
