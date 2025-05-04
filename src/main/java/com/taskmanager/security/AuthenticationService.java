package com.taskmanager.security;

import com.taskmanager.converter.UserConverter;
import com.taskmanager.controllers.dto.user.JwtAuthenticationResponseDto;
import com.taskmanager.controllers.dto.user.UserAuthRequestDto;
import com.taskmanager.controllers.dto.user.UserCreateRequestDto;
import com.taskmanager.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserService userService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserConverter userConverter;

    /**
     * Регистрация пользователя
     *
     * @param request данные пользователя
     * @return токен
     */
    public JwtAuthenticationResponseDto signUp(UserCreateRequestDto request) {
        var user = userConverter.convert(request);
        userService.create(user);
        return new JwtAuthenticationResponseDto(jwtService.generateToken(user));
    }

    /**
     * Аутентификация пользователя
     *
     * @param request данные пользователя
     * @return токен
     */
    public JwtAuthenticationResponseDto signIn(UserAuthRequestDto request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getLogin(),
                request.getPassword()
        ));
        var user = userService.userDetailsService()
                .loadUserByUsername(request.getLogin());
        return new JwtAuthenticationResponseDto(jwtService.generateToken(user));
    }
}
