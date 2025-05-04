package com.taskmanager.controllers.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Транспорт запроса создания пользователя")
public class UserCreateRequestDto {

    @NotNull
    @Schema(description = "Логин пользователя", example = "i.ivanov")
    private String login;

    @NotNull
    @Schema(description = "Пароль пользователя", example = "12345")
    private String password;

    @NotNull
    @Schema(description = "email пользователя", example = "ivan@gmail.com")
    private String email;


}
