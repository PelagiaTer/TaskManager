package com.taskmanager.controllers.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Запрос на аутентификацию")
public class UserAuthRequestDto {

    @Schema(description = "Имя пользователя", example = "Ник")
    private String login;

    @Schema(description = "Пароль", example = "my_password")
    private String password;
}
