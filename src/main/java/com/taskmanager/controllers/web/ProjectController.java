package com.taskmanager.controllers.web;

import com.taskmanager.controllers.dto.project.ProjectCreateRequestDto;
import com.taskmanager.controllers.dto.project.ProjectDeleteRequestDto;
import com.taskmanager.controllers.dto.project.ProjectGetAllResponseDto;
import com.taskmanager.controllers.dto.project.ProjectGetRequestDto;
import com.taskmanager.controllers.dto.project.ProjectUpdateRequestDto;
import com.taskmanager.controllers.dto.ResponseDto;
import com.taskmanager.service.ProjectService;
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
@RequestMapping("/web/project")
@RequiredArgsConstructor
@SecurityRequirement(name = "Auth")
@Tag(name = "Контроллер для работы с проектами")
public class ProjectController {

    private final ProjectService projectService;

    @Operation(summary = "Апи создания нового проекта")
    @PostMapping("/create")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseDto create(@RequestBody @Valid ProjectCreateRequestDto requestDto) {
        return projectService.create(requestDto);
    }

    @Operation(summary = "Апи изменения проекта")
    @PostMapping("/update")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseDto update(@RequestBody @Valid ProjectUpdateRequestDto requestDto) {
        return projectService.update(requestDto);
    }

    @Operation(summary = "Апи удаления проекта")
    @PostMapping("/delete")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseDto delete(@RequestBody @Valid ProjectDeleteRequestDto requestDto) {
        return projectService.delete(requestDto.getId());
    }

    @Operation(summary = "Апи получения проектов")
    @PostMapping("/getAll")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ProjectGetAllResponseDto getAll(@RequestBody @Valid ProjectGetRequestDto requestDto) {
        return projectService.getProjects(requestDto.getIsMyCreateProject(), requestDto.getLimit(),
                requestDto.getOffset());
    }
}
