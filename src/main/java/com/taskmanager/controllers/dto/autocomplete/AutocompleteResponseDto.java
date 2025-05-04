package com.taskmanager.controllers.dto.autocomplete;

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
@Schema(description = "Транспорт ответа поиска автокомплита")
public class AutocompleteResponseDto {

    @Schema(description = "Найденные записи")
    private List<AutocompleteDto> items;
}
