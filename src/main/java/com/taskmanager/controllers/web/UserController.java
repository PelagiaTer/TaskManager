package com.taskmanager.controllers.web;

import com.taskmanager.controllers.dto.user.JwtAuthenticationResponseDto;
import com.taskmanager.controllers.dto.user.UserAuthRequestDto;
import com.taskmanager.controllers.dto.user.UserCreateRequestDto;
import com.taskmanager.security.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Контроллер для работы с пользователем")
public class UserController {

    private final AuthenticationService authenticationService;

    @Operation(summary = "Регистрация пользователя")
    @PostMapping("/sign/up")
    public JwtAuthenticationResponseDto signUp(@RequestBody @Valid UserCreateRequestDto request) {
        return authenticationService.signUp(request);
    }

    @Operation(summary = "Авторизация пользователя")
    @PostMapping("/sign/in")
    public JwtAuthenticationResponseDto signIn(@RequestBody @Valid UserAuthRequestDto request) {
        return authenticationService.signIn(request);
    }

}
