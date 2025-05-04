package com.taskmanager.converter;

import com.taskmanager.controllers.dto.task.TaskCreateRequestDto;
import com.taskmanager.enams.TaskStatus;
import com.taskmanager.model.Task;
import com.taskmanager.utils.SecurityContextUtils;
import jakarta.annotation.Nonnull;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class TaskCreateConverter {

    @Nonnull
    public Task convert(@Nonnull TaskCreateRequestDto source) {
        return Task.builder()
                .id(UUID.randomUUID())
                .title(source.getTitle())
                .description(source.getDescription())
                .status(TaskStatus.NEW.name())
                .priority(source.getPriority().name())
                .dateDue(source.getDateDue())
                .projectId(source.getProjectId())
                .assignedUserId(source.getAssignedUserId())
                .createUserId(SecurityContextUtils.getUser().getId())
                .build();
    }
}
