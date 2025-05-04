package com.taskmanager.converter;

import com.taskmanager.controllers.dto.user.UserCreateRequestDto;
import com.taskmanager.enams.Role;
import com.taskmanager.utils.MyEasyRandom;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserConverterTest {

    @InjectMocks
    private UserConverter converter;
    @Mock
    private PasswordEncoder passwordEncoder;

    @Test
    void convert() {
        var expected = MyEasyRandom.nextObject(UserCreateRequestDto.class);
        when(passwordEncoder.encode(any())).thenReturn("password");

        var actual = converter.convert(expected);

        assertEquals(expected.getLogin(), actual.getLogin());
        assertEquals(expected.getEmail(), actual.getEmail());
        assertEquals("password", actual.getPassword());
        assertEquals(Role.ROLE_USER.name(), actual.getRole());
    }
}