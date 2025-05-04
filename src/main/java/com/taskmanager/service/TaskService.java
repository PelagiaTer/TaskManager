package com.taskmanager.service;

import com.taskmanager.converter.FilterTaskConverter;
import com.taskmanager.converter.TaskCreateConverter;
import com.taskmanager.converter.TaskCreateResponseDtoConverter;
import com.taskmanager.converter.TaskDtoConverter;
import com.taskmanager.converter.TaskInfoResponseDtoConverter;
import com.taskmanager.converter.TaskUpdateConverter;
import com.taskmanager.converter.TaskUpdateResponseDtoConverter;
import com.taskmanager.controllers.dto.AlertDto;
import com.taskmanager.controllers.dto.task.FilterTaskRequestDto;
import com.taskmanager.controllers.dto.ResponseDto;
import com.taskmanager.controllers.dto.task.TaskCreateRequestDto;
import com.taskmanager.controllers.dto.task.TaskCreateResponseDto;
import com.taskmanager.controllers.dto.task.TaskDeleteRequestDto;
import com.taskmanager.controllers.dto.task.TaskInfoResponseDto;
import com.taskmanager.controllers.dto.task.TaskResponseDto;
import com.taskmanager.controllers.dto.task.TaskUpdateRequestDto;
import com.taskmanager.controllers.dto.task.TaskUpdateResponseDto;
import com.taskmanager.enams.Role;
import com.taskmanager.exception.ForbiddenException;
import com.taskmanager.exception.NotFoundException;
import com.taskmanager.model.Task;
import com.taskmanager.model.User;
import com.taskmanager.repository.TaskRepository;
import com.taskmanager.utils.SecurityContextUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskCreateConverter taskCreateConverter;
    private final TaskUpdateConverter taskUpdateConverter;
    private final TaskRepository taskRepository;
    private final TaskCreateResponseDtoConverter taskCreateResponseDtoConverter;
    private final TaskUpdateResponseDtoConverter taskUpdateResponseDtoConverter;
    private final TaskInfoResponseDtoConverter taskInfoResponseDtoConverter;
    private final FilterTaskConverter filterTaskConverter;
    private final TaskDtoConverter taskDtoConverter;

    public TaskCreateResponseDto create(TaskCreateRequestDto requestDto) {
        var task = taskCreateConverter.convert(requestDto);
        taskRepository.save(task);
        task = taskRepository.getById(task.getId());
        return taskCreateResponseDtoConverter.convert(task);
    }

    public TaskUpdateResponseDto update(TaskUpdateRequestDto requestDto) {
        var taskPersist = taskRepository.getById(requestDto.getId());
        if (taskPersist == null) {
            throw new NotFoundException("Задача не найдена");
        }
        checkRoleUpdate(taskPersist);
        var task = taskUpdateConverter.convert(requestDto);
        taskRepository.update(task);
        task = taskRepository.getById(task.getId());
        return taskUpdateResponseDtoConverter.convert(task);
    }

    public ResponseDto delete(TaskDeleteRequestDto requestDto) {
        var taskPersist = taskRepository.getById(requestDto.getId());
        if (taskPersist == null) {
            throw new NotFoundException("Задача не найдена");
        }
        checkRoleDelete(taskPersist);
        taskRepository.deleteById(taskPersist.getId());
        return new ResponseDto(new AlertDto("Задача успешно удалена"));
    }

    public TaskInfoResponseDto getTaskInfoById(UUID taskId) {
        var taskPersist = taskRepository.getById(taskId);
        if (taskPersist == null) {
            throw new NotFoundException("Задача не найдена");
        }
        checkRoleGet(taskPersist);
        return taskInfoResponseDtoConverter.convert(taskPersist);
    }

    public TaskResponseDto getTasks(FilterTaskRequestDto requestDto) {
        var filter = filterTaskConverter.convert(requestDto);
        var tasks = taskRepository.getByFilter(filter);
        var items = taskDtoConverter.convert(tasks);
        return new TaskResponseDto(items);
    }


    private void checkRoleUpdate(Task task) {
        var user = SecurityContextUtils.getUser();
        if (Role.ROLE_ADMIN.name().equals(user.getRole())) {
            return;
        }
        if (Role.ROLE_USER.name().equals(user.getRole()) && !isRelateUserTask(task, user)) {
            throw new ForbiddenException("У пользователя недостаточно прав для редактирования задачи");
        }
    }

    private void checkRoleDelete(Task task) {
        var user = SecurityContextUtils.getUser();
        if (Role.ROLE_ADMIN.name().equals(user.getRole())) {
            return;
        }
        if (Role.ROLE_USER.name().equals(user.getRole()) &&
                !task.getCreateUserId().equals(user.getId())) {
            throw new ForbiddenException("У пользователя недостаточно прав для удаления задачи");
        }
    }

    private void checkRoleGet(Task task) {
        var user = SecurityContextUtils.getUser();
        if (Role.ROLE_ADMIN.name().equals(user.getRole())) {
            return;
        }
        if (Role.ROLE_USER.name().equals(user.getRole()) && !isRelateUserTask(task, user)) {
            throw new ForbiddenException("У пользователя недостаточно прав для просмотра задачи");
        }
    }

    private boolean isRelateUserTask(Task task, User user) {
        return task.getCreateUserId().equals(user.getId()) || task.getAssignedUserId().equals(user.getId());
    }
}
