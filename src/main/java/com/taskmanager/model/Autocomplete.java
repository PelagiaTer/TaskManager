package com.taskmanager.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * Информация для автокомплита
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Autocomplete {

    /**
     * Идентификатор
     */
    private UUID id;

    /**
     * Имя
     */
    private String name;
}
