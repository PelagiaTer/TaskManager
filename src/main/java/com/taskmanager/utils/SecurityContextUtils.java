package com.taskmanager.utils;

import com.taskmanager.model.User;
import jakarta.annotation.Nullable;
import lombok.experimental.UtilityClass;
import org.springframework.security.core.context.SecurityContextHolder;

@UtilityClass
public class SecurityContextUtils {

    @Nullable
    public User getUser() {
        return SecurityContextHolder.getContext().getAuthentication() == null ? null :
                (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
