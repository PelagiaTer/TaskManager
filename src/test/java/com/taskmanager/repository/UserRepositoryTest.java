package com.taskmanager.repository;

import com.taskmanager.model.Autocomplete;
import com.taskmanager.model.User;
import com.taskmanager.repository.mybatis.mapper.UserMapper;
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
class UserRepositoryTest {

    @InjectMocks
    private UserRepository repository;
    @Mock
    private UserMapper mapper;

    @Test
    void save() {
        doNothing().when(mapper).save(any());

        repository.save(MyEasyRandom.nextObject(User.class));

        verify(mapper).save(any());
    }

    @Test
    void update() {
        doNothing().when(mapper).update(any());

        repository.update(MyEasyRandom.nextObject(User.class));

        verify(mapper).update(any());
    }

    @Test
    void getById() {
        var expected = MyEasyRandom.nextObject(User.class);
        when(mapper.getById(any())).thenReturn(expected);

        var actual = repository.getById(UUID.randomUUID());

        assertEquals(expected, actual);
    }

    @Test
    void getByLogin() {
        var expected = MyEasyRandom.nextObject(User.class);
        when(mapper.getByLogin(any())).thenReturn(expected);

        var actual = repository.getByLogin("userName");

        assertEquals(expected, actual);
    }

    @Test
    void findUsers() {
        var expected = MyEasyRandom.nextObject(Autocomplete.class);
        when(mapper.findUsers(any(), anyInt())).thenReturn(List.of(expected));

        var actual = repository.findUsers("userName", 10);

        assertEquals(expected, actual.get(0));
    }
}