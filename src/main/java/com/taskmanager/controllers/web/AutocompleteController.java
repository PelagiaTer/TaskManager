package com.taskmanager.controllers.web;

import com.taskmanager.controllers.dto.autocomplete.AutocompleteRequestDto;
import com.taskmanager.controllers.dto.autocomplete.AutocompleteResponseDto;
import com.taskmanager.service.AutocompleteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/web/autocomplete")
@RequiredArgsConstructor
@SecurityRequirement(name = "Auth")
@Tag(name = "Контроллер для автокомплитов")
public class AutocompleteController {

    private final AutocompleteService autocompleteService;

    @Operation(summary = "Апи поиска пользователей")
    @PostMapping("/user")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public AutocompleteResponseDto findUser(@RequestBody @Valid AutocompleteRequestDto requestDto) {
        return autocompleteService.findUsers(requestDto.getTerm(), requestDto.getLimit());
    }

    @Operation(summary = "Апи поиска проектов")
    @PostMapping("/project")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public AutocompleteResponseDto findProject(@RequestBody @Valid AutocompleteRequestDto requestDto) {
        return autocompleteService.findProjects(requestDto.getTerm(), requestDto.getLimit());
    }
}
