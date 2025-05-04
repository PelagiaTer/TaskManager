package com.taskmanager.converter;

import com.taskmanager.controllers.dto.task.FilterTaskRequestDto;
import com.taskmanager.enams.Role;
import com.taskmanager.model.FilterTask;
import com.taskmanager.utils.SecurityContextUtils;
import jakarta.annotation.Nonnull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class FilterTaskConverter {

    @Value("${project.task.get.limit}")
    private int defaultLimit;

    @Nonnull
    public FilterTask convert(@Nonnull FilterTaskRequestDto source) {
        return FilterTask.builder()
                .number(source.getNumber())
                .status(source.getStatus())
                .priority(source.getPriority())
                .createUserId(getUserId(source.getCreateUserId()))
                .assignedUserId(getUserId(source.getAssignedUserId()))
                .limit(source.getLimit() == null ? defaultLimit : source.getLimit())
                .offset(source.getOffset() == null ? 0 : source.getOffset())
                .build();
    }

    private UUID getUserId(UUID userId) {
        var user = SecurityContextUtils.getUser();
        if (Role.ROLE_ADMIN.name().equals(user.getRole())) {
            return userId;
        }
        return user.getId();
    }
}
