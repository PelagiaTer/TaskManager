package com.taskmanager.controllers.dto.autocomplete;

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
@Schema(description = "Транспорт автокомплита")
public class AutocompleteDto {

    @Schema(description = "Уникальный идентификатор")
    private UUID id;

    @Schema(description = "Имя")
    private String name;
}
