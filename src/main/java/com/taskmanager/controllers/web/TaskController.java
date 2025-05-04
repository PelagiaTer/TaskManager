package com.taskmanager.controllers.web;

import com.taskmanager.controllers.dto.task.FilterTaskRequestDto;
import com.taskmanager.controllers.dto.ResponseDto;
import com.taskmanager.controllers.dto.task.TaskCreateRequestDto;
import com.taskmanager.controllers.dto.task.TaskCreateResponseDto;
import com.taskmanager.controllers.dto.task.TaskDeleteRequestDto;
import com.taskmanager.controllers.dto.task.TaskInfoRequestDto;
import com.taskmanager.controllers.dto.task.TaskInfoResponseDto;
import com.taskmanager.controllers.dto.task.TaskResponseDto;
import com.taskmanager.controllers.dto.task.TaskUpdateRequestDto;
import com.taskmanager.controllers.dto.task.TaskUpdateResponseDto;
import com.taskmanager.service.TaskService;
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
@RequestMapping("/web/task")
@RequiredArgsConstructor
@Tag(name = "Контроллер для работы с задачами")
@SecurityRequirement(name = "Auth")
public class TaskController {

    private final TaskService taskService;

    @Operation(summary = "Апи создания новой задачи")
    @PostMapping("/create")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public TaskCreateResponseDto create(@RequestBody @Valid TaskCreateRequestDto requestDto) {
        return taskService.create(requestDto);
    }

    @Operation(summary = "Апи обновления задачи")
    @PostMapping("/update")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public TaskUpdateResponseDto update(@RequestBody @Valid TaskUpdateRequestDto requestDto) {
        return taskService.update(requestDto);
    }

    @Operation(summary = "Апи удаления задачи")
    @PostMapping("/delete")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseDto delete(@RequestBody @Valid TaskDeleteRequestDto requestDto) {
        return taskService.delete(requestDto);
    }

    @Operation(summary = "Апи получения информации о задаче")
    @PostMapping("/get")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public TaskInfoResponseDto getTaskInfoById(@RequestBody @Valid TaskInfoRequestDto requestDto) {
        return taskService.getTaskInfoById(requestDto.getId());
    }

    @Operation(summary = "Апи поиска задач по фильтру")
    @PostMapping("/filter")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public TaskResponseDto getTasks(@RequestBody @Valid FilterTaskRequestDto requestDto) {
        return taskService.getTasks(requestDto);
    }

}
