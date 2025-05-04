package com.taskmanager.service;

import com.taskmanager.converter.AutocompleteDtoConverter;
import com.taskmanager.controllers.dto.autocomplete.AutocompleteResponseDto;
import com.taskmanager.repository.ProjectRepository;
import com.taskmanager.repository.UserRepository;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AutocompleteService {

    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;
    private final AutocompleteDtoConverter autocompleteDtoConverter;

    @Value("${project.automplete.limit}")
    private int defaultLimit;

    @Nonnull
    public AutocompleteResponseDto findUsers(@Nonnull String term, @Nullable Integer limitRequest) {
        var limit = limitRequest == null ? defaultLimit : limitRequest;
        var items = autocompleteDtoConverter.convert(userRepository.findUsers(term, limit));
        return new AutocompleteResponseDto(items);
    }

    @Nonnull
    public AutocompleteResponseDto findProjects(@Nonnull String term, @Nullable Integer limitRequest) {
        var limit = limitRequest == null ? defaultLimit : limitRequest;
        var items = autocompleteDtoConverter.convert(projectRepository.findProjects(term, limit));
        return new AutocompleteResponseDto(items);
    }
}
