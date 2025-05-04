package com.taskmanager.converter;

import com.taskmanager.controllers.dto.autocomplete.AutocompleteDto;
import com.taskmanager.model.Autocomplete;
import jakarta.annotation.Nonnull;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class AutocompleteDtoConverter {

    @Nonnull
    public List<AutocompleteDto> convert(List<Autocomplete> sources) {
        return Optional.ofNullable(sources)
                .orElseGet(Collections::emptyList)
                .stream()
                .filter(Objects::nonNull)
                .map(this::convert)
                .toList();
    }

    @Nonnull
    private AutocompleteDto convert(@Nonnull Autocomplete source) {
        return AutocompleteDto.builder()
                .id(source.getId())
                .name(source.getName())
                .build();
    }
}
