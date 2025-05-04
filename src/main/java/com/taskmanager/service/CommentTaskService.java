package com.taskmanager.service;

import com.taskmanager.converter.CommentTaskConverter;
import com.taskmanager.converter.CommentTaskDtoConverter;
import com.taskmanager.controllers.dto.AlertDto;
import com.taskmanager.controllers.dto.comment_task.CommentTaskCreateRequestDto;
import com.taskmanager.controllers.dto.comment_task.CommentTaskDeleteRequestDto;
import com.taskmanager.controllers.dto.comment_task.CommentTaskResponseDto;
import com.taskmanager.controllers.dto.comment_task.CommentTaskUpdateRequestDto;
import com.taskmanager.controllers.dto.ResponseDto;
import com.taskmanager.enams.Role;
import com.taskmanager.exception.ForbiddenException;
import com.taskmanager.exception.NotFoundException;
import com.taskmanager.model.Task;
import com.taskmanager.model.User;
import com.taskmanager.repository.CommentTaskRepository;
import com.taskmanager.repository.TaskRepository;
import com.taskmanager.utils.SecurityContextUtils;
import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CommentTaskService {

    private final CommentTaskRepository commentTaskRepository;
    private final TaskRepository taskRepository;
    private final CommentTaskConverter commentTaskConverter;
    private final CommentTaskDtoConverter commentTaskDtoConverter;

    public ResponseDto create(CommentTaskCreateRequestDto requestDto) {
        if (!taskRepository.existById(requestDto.getTaskId())) {
            throw new NotFoundException("Не удалось создать комментарий. Задача не найдена",
                    "COMMENT_TASK_CREATE_EXIST_ERROR");
        }
        checkRole(requestDto.getTaskId());
        commentTaskRepository.save(commentTaskConverter.convertCreate(requestDto));
        return new ResponseDto(new AlertDto("Комментарий успешно добавлен"));
    }

    public ResponseDto update(CommentTaskUpdateRequestDto requestDto) {
        if (!commentTaskRepository.existById(requestDto.getId())) {
            throw new NotFoundException("Комментарий не найден", "COMMENT_TASK_UPDATE_EXIST_ERROR");
        }
        checkRole(requestDto.getTaskId());
        commentTaskRepository.update(commentTaskConverter.convertUpdate(requestDto));
        return new ResponseDto(new AlertDto("Комментарий успешно изменен"));
    }

    public ResponseDto delete(CommentTaskDeleteRequestDto requestDto) {
        if (!commentTaskRepository.existById(requestDto.getId())) {
            throw new NotFoundException("Комментарий не найден", "COMMENT_TASK_UPDATE_EXIST_ERROR");
        }
        checkRoleDelete(requestDto.getTaskId());
        commentTaskRepository.deleteById(requestDto.getId());
        return new ResponseDto(new AlertDto("Комментарий успешно удален"));
    }

    public CommentTaskResponseDto getByTaskId(UUID taskId) {
        var commentTasks = commentTaskRepository.getByTaskId(taskId);
        return new CommentTaskResponseDto(commentTaskDtoConverter.convert(commentTasks));
    }


    private void checkRole(UUID taskId) {
        var user = SecurityContextUtils.getUser();
        if (Role.ROLE_ADMIN.name().equals(user.getRole())) {
            return;
        }
        var task = taskRepository.getById(taskId);
        if (Role.ROLE_USER.name().equals(user.getRole()) && !isRelateUserTask(task, user)) {
            throw new ForbiddenException("У пользователя недостаточно прав для создания/редактирования комментария");
        }
    }

    private void checkRoleDelete(@Nonnull UUID taskId) {
        var user = SecurityContextUtils.getUser();
        if (Role.ROLE_ADMIN.name().equals(user.getRole())) {
            return;
        }
        var task = taskRepository.getById(taskId);
        if (Role.ROLE_USER.name().equals(user.getRole()) &&
                !task.getCreateUserId().equals(user.getId())) {
            throw new ForbiddenException("У пользователя недостаточно прав для создания/редактирования комментария");
        }
    }

    private boolean isRelateUserTask(Task task, User user) {
        return task.getCreateUserId().equals(user.getId()) || task.getAssignedUserId().equals(user.getId());
    }

}
