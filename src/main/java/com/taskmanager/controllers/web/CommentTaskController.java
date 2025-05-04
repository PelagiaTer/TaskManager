package com.taskmanager.controllers.web;

import com.taskmanager.controllers.dto.comment_task.CommentTaskCreateRequestDto;
import com.taskmanager.controllers.dto.comment_task.CommentTaskDeleteRequestDto;
import com.taskmanager.controllers.dto.comment_task.CommentTaskGetRequestDto;
import com.taskmanager.controllers.dto.comment_task.CommentTaskResponseDto;
import com.taskmanager.controllers.dto.comment_task.CommentTaskUpdateRequestDto;
import com.taskmanager.controllers.dto.ResponseDto;
import com.taskmanager.service.CommentTaskService;
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
@RequestMapping("/web/comment/task")
@RequiredArgsConstructor
@Tag(name = "Контроллер для работы с комментариями задачи")
@SecurityRequirement(name = "Auth")
public class CommentTaskController {

    private final CommentTaskService commentTaskService;

    @Operation(summary = "Апи создания нового комментария")
    @PostMapping("/create")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseDto create(@RequestBody @Valid CommentTaskCreateRequestDto requestDto) {
        return commentTaskService.create(requestDto);
    }

    @Operation(summary = "Апи обновления комментария")
    @PostMapping("/update")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseDto update(@RequestBody @Valid CommentTaskUpdateRequestDto requestDto) {
        return commentTaskService.update(requestDto);
    }

    @Operation(summary = "Апи удаления комментария")
    @PostMapping("/delete")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseDto delete(@RequestBody @Valid CommentTaskDeleteRequestDto requestDto) {
        return commentTaskService.delete(requestDto);
    }

    @Operation(summary = "Апи получения комментариев задачи")
    @PostMapping("/get")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public CommentTaskResponseDto get(@RequestBody @Valid CommentTaskGetRequestDto requestDto) {
        return commentTaskService.getByTaskId(requestDto.getTaskId());
    }
}
