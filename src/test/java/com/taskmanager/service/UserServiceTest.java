package com.taskmanager.service;

import com.taskmanager.exception.NotFoundException;
import com.taskmanager.exception.ValidateException;
import com.taskmanager.model.User;
import com.taskmanager.repository.UserRepository;
import com.taskmanager.utils.MyEasyRandom;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    private UserService service;
    @Mock
    private UserRepository userRepository;

    @Test
    void save() {
        doNothing().when(userRepository).save(any());
        var user = MyEasyRandom.nextObject(User.class);
        when(userRepository.getById(any())).thenReturn(user);

        var actual = service.save(user);

        assertEquals(user, actual);
    }

    @Test
    void create() {
        var user = MyEasyRandom.nextObject(User.class);
        when(userRepository.getByLogin(any())).thenReturn(null);
        doNothing().when(userRepository).save(any());
        when(userRepository.getById(any())).thenReturn(user);

        var actual = service.create(user);

        assertEquals(user, actual);
    }

    @Test
    void create_exist() {
        var user = MyEasyRandom.nextObject(User.class);
        when(userRepository.getByLogin(any())).thenReturn(user);

        assertThrows(ValidateException.class, () -> service.create(user));
    }

    @Test
    void getByUsername() {
        var expected = MyEasyRandom.nextObject(User.class);
        when(userRepository.getByLogin(any())).thenReturn(expected);

        var actual = service.getByUsername("login");

        assertEquals(expected, actual);
    }

    @Test
    void getByUsername_notFound() {
        when(userRepository.getByLogin(any())).thenReturn(null);

        assertThrows(NotFoundException.class, () -> service.getByUsername("login"));
    }

}