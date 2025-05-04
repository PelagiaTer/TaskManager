package com.taskmanager.utils;

import com.taskmanager.enams.Role;
import com.taskmanager.model.User;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.Extension;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

import java.util.UUID;

@NoArgsConstructor
public class SecurityContextExtension implements Extension, BeforeEachCallback, AfterEachCallback {

    public static final String USER_CONTEXT_TOKEN_DEFAULT = "token";
    public static final String USER_CONTEXT_LOGIN_DEFAULT = "login";
    public static final String USER_CONTEXT_PASSWORD_DEFAULT = "password";
    public static final String USER_CONTEXT_EMAIL_DEFAULT = "email";
    public static final UUID USER_CONTEXT_ID_DEFAULT = UUID.randomUUID();

    @Override
    public void afterEach(ExtensionContext extensionContext) {
        clearContext();
    }

    @Override
    public void beforeEach(ExtensionContext extensionContext) {
        createContextUser();
    }

    public static void clearContext() {
        SecurityContextHolder.clearContext();
    }

    public static void createContextUser() {
        Authentication auth = new PreAuthenticatedAuthenticationToken(createDefaultUser(Role.ROLE_USER.name()),
                USER_CONTEXT_TOKEN_DEFAULT);
        SecurityContextHolder.setContext(new SecurityContextImpl(auth));
    }

    public static void createContextAdmin() {
        Authentication auth = new PreAuthenticatedAuthenticationToken(createDefaultUser(Role.ROLE_ADMIN.name()),
                USER_CONTEXT_TOKEN_DEFAULT);
        SecurityContextHolder.setContext(new SecurityContextImpl(auth));
    }

    public static User createDefaultUser(String role) {
        return User.builder()
                .id(USER_CONTEXT_ID_DEFAULT)
                .login(USER_CONTEXT_LOGIN_DEFAULT)
                .password(USER_CONTEXT_PASSWORD_DEFAULT)
                .email(USER_CONTEXT_EMAIL_DEFAULT)
                .role(role)
                .build();
    }
}
