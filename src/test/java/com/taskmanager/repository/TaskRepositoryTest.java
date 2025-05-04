package com.taskmanager.repository;

import com.taskmanager.model.FilterTask;
import com.taskmanager.model.Task;
import com.taskmanager.repository.mybatis.mapper.TaskMapper;
import com.taskmanager.utils.MyEasyRandom;
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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TaskRepositoryTest {

    @InjectMocks
    private TaskRepository repository;
    @Mock
    private TaskMapper mapper;

    @Test
    void save() {
        doNothing().when(mapper).save(any());

        repository.save(MyEasyRandom.nextObject(Task.class));

        verify(mapper).save(any());
    }

    @Test
    void update() {
        doNothing().when(mapper).update(any());

        repository.update(MyEasyRandom.nextObject(Task.class));

        verify(mapper).update(any());
    }

    @Test
    void getById() {
        var expected = MyEasyRandom.nextObject(Task.class);
        when(mapper.getById(any())).thenReturn(expected);

        var actual = repository.getById(UUID.randomUUID());

        assertEquals(expected, actual);
    }

    @Test
    void existById() {
        when(mapper.existById(any())).thenReturn(true);

        var actual = repository.existById(UUID.randomUUID());

        assertTrue(actual);
    }

    @Test
    void deleteById() {
        doNothing().when(mapper).deleteById(any());

        repository.deleteById(UUID.randomUUID());

        verify(mapper).deleteById(any());
    }

    @Test
    void getByFilter() {
        var expected = MyEasyRandom.nextObject(Task.class);
        when(mapper.getByFilter(any())).thenReturn(List.of(expected));

        var actual = repository.getByFilter(FilterTask.builder().number(1L).build());

        assertEquals(expected, actual.get(0));
    }
}