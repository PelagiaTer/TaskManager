package com.taskmanager.service;

import com.taskmanager.controllers.dto.comment_task.CommentTaskCreateRequestDto;
import com.taskmanager.controllers.dto.comment_task.CommentTaskDeleteRequestDto;
import com.taskmanager.controllers.dto.comment_task.CommentTaskDto;
import com.taskmanager.controllers.dto.comment_task.CommentTaskUpdateRequestDto;
import com.taskmanager.converter.CommentTaskConverter;
import com.taskmanager.converter.CommentTaskDtoConverter;
import com.taskmanager.exception.ForbiddenException;
import com.taskmanager.exception.NotFoundException;
import com.taskmanager.model.CommentTask;
import com.taskmanager.model.Task;
import com.taskmanager.repository.CommentTaskRepository;
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
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith({ MockitoExtension.class, SecurityContextExtension.class })
class CommentTaskServiceTest {

    @InjectMocks
    private CommentTaskService service;
    @Mock
    private CommentTaskRepository commentTaskRepository;
    @Mock
    private TaskRepository taskRepository;
    @Mock
    private CommentTaskConverter commentTaskConverter;
    @Mock
    private CommentTaskDtoConverter commentTaskDtoConverter;

    @Test
    void create() {
        var request = MyEasyRandom.nextObject(CommentTaskCreateRequestDto.class);
        when(taskRepository.existById(any())).thenReturn(true);
        var task = MyEasyRandom.nextObject(Task.class);
        task.setCreateUserId(SecurityContextUtils.getUser().getId());
        when(taskRepository.getById(any())).thenReturn(task);
        when(commentTaskConverter.convertCreate(any())).thenReturn(MyEasyRandom.nextObject(CommentTask.class));
        doNothing().when(commentTaskRepository).save(any());

        var actual = service.create(request);

        assertEquals("Комментарий успешно добавлен", actual.getAlerts().get(0).getMsg());
    }

    @Test
    void create_notTask() {
        var request = MyEasyRandom.nextObject(CommentTaskCreateRequestDto.class);
        when(taskRepository.existById(any())).thenReturn(false);

        assertThrows(NotFoundException.class, () -> service.create(request));
    }

    @Test
    void create_notPermit() {
        var request = MyEasyRandom.nextObject(CommentTaskCreateRequestDto.class);
        when(taskRepository.existById(any())).thenReturn(true);
        var task = MyEasyRandom.nextObject(Task.class);
        when(taskRepository.getById(any())).thenReturn(task);

        assertThrows(ForbiddenException.class, () -> service.create(request));
    }

    @Test
    void create_admin() {
        SecurityContextExtension.createContextAdmin();
        var request = MyEasyRandom.nextObject(CommentTaskCreateRequestDto.class);
        when(taskRepository.existById(any())).thenReturn(true);
        when(commentTaskConverter.convertCreate(any())).thenReturn(MyEasyRandom.nextObject(CommentTask.class));

        var actual = service.create(request);

        assertEquals("Комментарий успешно добавлен", actual.getAlerts().get(0).getMsg());
    }

    @Test
    void update() {
        var request = MyEasyRandom.nextObject(CommentTaskUpdateRequestDto.class);
        when(commentTaskRepository.existById(any())).thenReturn(true);
        var task = MyEasyRandom.nextObject(Task.class);
        task.setAssignedUserId(SecurityContextUtils.getUser().getId());
        when(taskRepository.getById(any())).thenReturn(task);
        when(commentTaskConverter.convertUpdate(any())).thenReturn(MyEasyRandom.nextObject(CommentTask.class));
        doNothing().when(commentTaskRepository).update(any());

        var actual = service.update(request);

        assertEquals("Комментарий успешно изменен", actual.getAlerts().get(0).getMsg());
    }

    @Test
    void update_notComment() {
        var request = MyEasyRandom.nextObject(CommentTaskUpdateRequestDto.class);
        when(commentTaskRepository.existById(any())).thenReturn(false);

        assertThrows(NotFoundException.class, () -> service.update(request));
    }

    @Test
    void update_notPermit() {
        var request = MyEasyRandom.nextObject(CommentTaskUpdateRequestDto.class);
        when(commentTaskRepository.existById(any())).thenReturn(true);
        var task = MyEasyRandom.nextObject(Task.class);
        when(taskRepository.getById(any())).thenReturn(task);

        assertThrows(ForbiddenException.class, () -> service.update(request));
    }

    @Test
    void update_admin() {
        SecurityContextExtension.createContextAdmin();
        var request = MyEasyRandom.nextObject(CommentTaskUpdateRequestDto.class);
        when(commentTaskRepository.existById(any())).thenReturn(true);
        when(commentTaskConverter.convertUpdate(any())).thenReturn(MyEasyRandom.nextObject(CommentTask.class));

        var actual = service.update(request);

        assertEquals("Комментарий успешно изменен", actual.getAlerts().get(0).getMsg());
    }

    @Test
    void delete() {
        var request = MyEasyRandom.nextObject(CommentTaskDeleteRequestDto.class);
        when(commentTaskRepository.existById(any())).thenReturn(true);
        var task = MyEasyRandom.nextObject(Task.class);
        task.setCreateUserId(SecurityContextUtils.getUser().getId());
        when(taskRepository.getById(any())).thenReturn(task);
        doNothing().when(commentTaskRepository).deleteById(any());

        var actual = service.delete(request);

        assertEquals("Комментарий успешно удален", actual.getAlerts().get(0).getMsg());
    }

    @Test
    void delete_notComment() {
        var request = MyEasyRandom.nextObject(CommentTaskDeleteRequestDto.class);
        when(commentTaskRepository.existById(any())).thenReturn(false);

        assertThrows(NotFoundException.class, () -> service.delete(request));
    }

    @Test
    void delete_notPermit() {
        var request = MyEasyRandom.nextObject(CommentTaskDeleteRequestDto.class);
        when(commentTaskRepository.existById(any())).thenReturn(true);
        var task = MyEasyRandom.nextObject(Task.class);
        when(taskRepository.getById(any())).thenReturn(task);

        assertThrows(ForbiddenException.class, () -> service.delete(request));
    }

    @Test
    void delete_admin() {
        SecurityContextExtension.createContextAdmin();
        var request = MyEasyRandom.nextObject(CommentTaskDeleteRequestDto.class);
        when(commentTaskRepository.existById(any())).thenReturn(true);

        var actual = service.delete(request);

        assertEquals("Комментарий успешно удален", actual.getAlerts().get(0).getMsg());
    }

    @Test
    void getByTaskId() {
        when(commentTaskRepository.getByTaskId(any())).thenReturn(List.of(MyEasyRandom.nextObject(CommentTask.class)));
        var expected = MyEasyRandom.nextObject(CommentTaskDto.class);
        when(commentTaskDtoConverter.convert(anyList())).thenReturn(List.of(expected));

        var actual = service.getByTaskId(UUID.randomUUID());

        assertEquals(expected, actual.getItems().get(0));
    }
}