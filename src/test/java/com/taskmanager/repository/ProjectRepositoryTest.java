package com.taskmanager.repository;

import com.taskmanager.model.Autocomplete;
import com.taskmanager.model.Project;
import com.taskmanager.repository.mybatis.mapper.ProjectMapper;
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
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProjectRepositoryTest {

    @InjectMocks
    private ProjectRepository repository;
    @Mock
    private ProjectMapper mapper;

    @Test
    void save() {
        doNothing().when(mapper).save(any());

        repository.save(MyEasyRandom.nextObject(Project.class));

        verify(mapper).save(any());
    }

    @Test
    void update() {
        doNothing().when(mapper).update(any());

        repository.update(MyEasyRandom.nextObject(Project.class));

        verify(mapper).update(any());
    }

    @Test
    void getById() {
        var expected = MyEasyRandom.nextObject(Project.class);
        when(mapper.getById(any())).thenReturn(expected);

        var actual = repository.getById(UUID.randomUUID());

        assertEquals(expected, actual);
    }

    @Test
    void getByName() {
        var expected = MyEasyRandom.nextObject(Project.class);
        when(mapper.getByName(any())).thenReturn(expected);

        var actual = repository.getByName("name");

        assertEquals(expected, actual);
    }

    @Test
    void existByName() {
        when(mapper.existByName(any())).thenReturn(true);

        var actual = repository.existByName("name");

        assertTrue(actual);
    }

    @Test
    void deleteById() {
        doNothing().when(mapper).deleteById(any());

        repository.deleteById(UUID.randomUUID());

        verify(mapper).deleteById(any());
    }

    @Test
    void getByCreateUserId() {
        var expected = MyEasyRandom.nextObject(Project.class);
        when(mapper.getByCreateUserId(any())).thenReturn(List.of(expected));

        var actual = repository.getByCreateUserId(UUID.randomUUID());

        assertEquals(expected, actual.get(0));
    }

    @Test
    void getAll() {
        var expected = MyEasyRandom.nextObject(Project.class);
        when(mapper.getAll(anyInt(), anyInt())).thenReturn(List.of(expected));

        var actual = repository.getAll(10, 0);

        assertEquals(expected, actual.get(0));
    }

    @Test
    void findProjects() {
        var expected = MyEasyRandom.nextObject(Autocomplete.class);
        when(mapper.findProjects(any(), anyInt())).thenReturn(List.of(expected));

        var actual = repository.findProjects("term", 10);

        assertEquals(expected, actual.get(0));
    }
}