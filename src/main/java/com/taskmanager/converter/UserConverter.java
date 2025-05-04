package com.taskmanager.converter;

import com.taskmanager.controllers.dto.user.UserCreateRequestDto;
import com.taskmanager.enams.Role;
import com.taskmanager.model.User;
import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserConverter {

    private final PasswordEncoder passwordEncoder;

    @Nonnull
    public User convert(@Nonnull UserCreateRequestDto requestDto) {
        return User.builder()
                .login(requestDto.getLogin())
                .email(requestDto.getEmail())
                .password(passwordEncoder.encode(requestDto.getPassword()))
                .role(Role.ROLE_USER.name())
                .build();
    }
}
