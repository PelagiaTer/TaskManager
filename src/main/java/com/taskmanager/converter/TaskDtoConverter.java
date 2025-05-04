package com.taskmanager.converter;

import com.taskmanager.controllers.dto.task.TaskDto;
import com.taskmanager.model.Task;
import jakarta.annotation.Nonnull;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class TaskDtoConverter {

    @Nonnull
    public List<TaskDto> convert(List<Task> sources) {
        return Optional.ofNullable(sources)
                .orElseGet(Collections::emptyList)
                .stream()
                .filter(Objects::nonNull)
                .map(this::convert)
                .toList();
    }

    @Nonnull
    private TaskDto convert(@Nonnull Task task) {
        return TaskDto.builder()
                .id(task.getId())
                .number(task.getNumber())
                .title(task.getTitle())
                .description(task.getDescription())
                .status(task.getStatus())
                .priority(task.getPriority())
                .dateCreate(task.getDateCreate())
                .dateDue(task.getDateDue())
                .dateFact(task.getDateFact())
                .projectId(task.getProjectId())
                .assignedUserId(task.getAssignedUserId())
                .createUserId(task.getCreateUserId())
                .build();
    }
}
