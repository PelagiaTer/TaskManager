package com.taskmanager.converter;

import com.taskmanager.controllers.dto.task.TaskUpdateRequestDto;
import com.taskmanager.model.Task;
import jakarta.annotation.Nonnull;
import org.springframework.stereotype.Component;

@Component
public class TaskUpdateConverter {

    @Nonnull
    public Task convert(@Nonnull TaskUpdateRequestDto source) {
        return Task.builder()
                .id(source.getId())
                .title(source.getTitle())
                .description(source.getDescription())
                .status(source.getStatus().name())
                .priority(source.getPriority().name())
                .dateDue(source.getDateDue())
                .projectId(source.getProjectId())
                .assignedUserId(source.getAssignedUserId())
                .build();
    }
}
