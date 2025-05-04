package com.taskmanager.controllers.dto.autocomplete;

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
@Schema(description = "Транспорт запроса для автокомплитов")
public class AutocompleteRequestDto {

    @NotNull
    @Schema(description = "Искомое значение")
    private String term;

    @Schema(description = "Количество записей для поиска")
    private Integer limit;
}
