package com.taskmanager.service;

import com.taskmanager.controllers.dto.task.FilterTaskRequestDto;
import com.taskmanager.controllers.dto.task.TaskCreateRequestDto;
import com.taskmanager.controllers.dto.task.TaskCreateResponseDto;
import com.taskmanager.controllers.dto.task.TaskDeleteRequestDto;
import com.taskmanager.controllers.dto.task.TaskDto;
import com.taskmanager.controllers.dto.task.TaskInfoResponseDto;
import com.taskmanager.controllers.dto.task.TaskUpdateRequestDto;
import com.taskmanager.controllers.dto.task.TaskUpdateResponseDto;
import com.taskmanager.converter.FilterTaskConverter;
import com.taskmanager.converter.TaskCreateConverter;
import com.taskmanager.converter.TaskCreateResponseDtoConverter;
import com.taskmanager.converter.TaskDtoConverter;
import com.taskmanager.converter.TaskInfoResponseDtoConverter;
import com.taskmanager.converter.TaskUpdateConverter;
import com.taskmanager.converter.TaskUpdateResponseDtoConverter;
import com.taskmanager.exception.ForbiddenException;
import com.taskmanager.exception.NotFoundException;
import com.taskmanager.model.FilterTask;
import com.taskmanager.model.Task;
import com.taskmanager.repository.TaskRepository;
import com.taskmanager.utils.MyEasyRandom;
import com.taskmanager.utils.SecurityContextExtension;
import com.taskmanager.utils.SecurityContextUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith({ MockitoExtension.class, SecurityContextExtension.class })
class TaskServiceTest {

    @InjectMocks
    private TaskService service;
    @Mock
    private TaskCreateConverter taskCreateConverter;
    @Mock
    private TaskUpdateConverter taskUpdateConverter;
    @Mock
    private TaskRepository taskRepository;
    @Mock
    private TaskCreateResponseDtoConverter taskCreateResponseDtoConverter;
    @Mock
    private TaskUpdateResponseDtoConverter taskUpdateResponseDtoConverter;
    @Mock
    private TaskInfoResponseDtoConverter taskInfoResponseDtoConverter;
    @Mock
    private FilterTaskConverter filterTaskConverter;
    @Mock
    private TaskDtoConverter taskDtoConverter;

    @Test
    void create() {
        var request = MyEasyRandom.nextObject(TaskCreateRequestDto.class);
        var task = MyEasyRandom.nextObject(Task.class);
        when(taskCreateConverter.convert(any())).thenReturn(task);
        doNothing().when(taskRepository).save(any());
        when(taskRepository.getById(any())).thenReturn(task);
        var expected = MyEasyRandom.nextObject(TaskCreateResponseDto.class);
        when(taskCreateResponseDtoConverter.convert(any())).thenReturn(expected);

        var actual = service.create(request);

        assertEquals(expected, actual);
    }

    @Test
    void update() {
        var request = MyEasyRandom.nextObject(TaskUpdateRequestDto.class);
        var task = MyEasyRandom.nextObject(Task.class);
        task.setCreateUserId(SecurityContextUtils.getUser().getId());
        when(taskUpdateConverter.convert(any())).thenReturn(task);
        doNothing().when(taskRepository).update(any());
        when(taskRepository.getById(any())).thenReturn(task);
        var expected = MyEasyRandom.nextObject(TaskUpdateResponseDto.class);
        when(taskUpdateResponseDtoConverter.convert(any())).thenReturn(expected);

        var actual = service.update(request);

        assertEquals(expected, actual);
    }

    @Test
    void update_notPermit() {
        var request = MyEasyRandom.nextObject(TaskUpdateRequestDto.class);
        var task = MyEasyRandom.nextObject(Task.class);
        when(taskRepository.getById(any())).thenReturn(task);

        assertThrows(ForbiddenException.class, () -> service.update(request));
    }

    @Test
    void update_notFound() {
        var request = MyEasyRandom.nextObject(TaskUpdateRequestDto.class);
        when(taskRepository.getById(any())).thenReturn(null);

        assertThrows(NotFoundException.class, () -> service.update(request));
    }

    @Test
    void delete() {
        var request = MyEasyRandom.nextObject(TaskDeleteRequestDto.class);
        var task = MyEasyRandom.nextObject(Task.class);
        task.setCreateUserId(SecurityContextUtils.getUser().getId());
        doNothing().when(taskRepository).deleteById(any());
        when(taskRepository.getById(any())).thenReturn(task);

        var actual = service.delete(request);

        assertEquals("Задача успешно удалена", actual.getAlerts().get(0).getMsg());
    }

    @Test
    void delete_notPermit() {
        var request = MyEasyRandom.nextObject(TaskDeleteRequestDto.class);
        var task = MyEasyRandom.nextObject(Task.class);
        when(taskRepository.getById(any())).thenReturn(task);

        assertThrows(ForbiddenException.class, () -> service.delete(request));
    }

    @Test
    void delete_notFound() {
        var request = MyEasyRandom.nextObject(TaskDeleteRequestDto.class);
        when(taskRepository.getById(any())).thenReturn(null);

        assertThrows(NotFoundException.class, () -> service.delete(request));
    }

    @Test
    void getTaskInfoById() {
        var request = UUID.randomUUID();
        var task = MyEasyRandom.nextObject(Task.class);
        task.setCreateUserId(SecurityContextUtils.getUser().getId());
        when(taskRepository.getById(any())).thenReturn(task);
        var expected = MyEasyRandom.nextObject(TaskInfoResponseDto.class);
        when(taskInfoResponseDtoConverter.convert(any())).thenReturn(expected);

        var actual = service.getTaskInfoById(request);

        assertEquals(expected, actual);
    }

    @Test
    void getTaskInfoById_notPermit() {
        var task = MyEasyRandom.nextObject(Task.class);
        when(taskRepository.getById(any())).thenReturn(task);

        assertThrows(ForbiddenException.class, () -> service.getTaskInfoById(UUID.randomUUID()));
    }

    @Test
    void getTaskInfoById_notFound() {
        when(taskRepository.getById(any())).thenReturn(null);

        assertThrows(NotFoundException.class, () -> service.getTaskInfoById(UUID.randomUUID()));
    }

    @Test
    void getTasks() {
        var request = MyEasyRandom.nextObject(FilterTaskRequestDto.class);
        when(filterTaskConverter.convert(any())).thenReturn(MyEasyRandom.nextObject(FilterTask.class));
        when(taskRepository.getByFilter(any())).thenReturn(List.of(MyEasyRandom.nextObject(Task.class)));
        var expected = MyEasyRandom.nextObject(TaskDto.class);
        when(taskDtoConverter.convert(any())).thenReturn(List.of(expected));

        var actual = service.getTasks(request);

        assertEquals(expected, actual.getItems().get(0));
    }
}