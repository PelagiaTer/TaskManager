package com.taskmanager.converter;

import com.taskmanager.controllers.dto.task.TaskInfoResponseDto;
import com.taskmanager.model.Project;
import com.taskmanager.model.Task;
import com.taskmanager.model.User;
import com.taskmanager.repository.ProjectRepository;
import com.taskmanager.repository.UserRepository;
import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class TaskInfoResponseDtoConverter {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    @Nonnull
    public TaskInfoResponseDto convert(@Nonnull Task task) {
        return TaskInfoResponseDto.builder()
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
                .projectName(getProjectName(task.getProjectId()))
                .assignedUserId(task.getAssignedUserId())
                .assignedUserName(getUserName(task.getAssignedUserId()))
                .createUserId(task.getCreateUserId())
                .createUserName(getUserName(task.getCreateUserId()))
                .build();
    }

    private String getProjectName(UUID projectId) {
        return Optional.ofNullable(projectId)
                .map(projectRepository::getById)
                .map(Project::getName)
                .orElse(null);
    }

    private String getUserName(UUID userId) {
        return Optional.ofNullable(userId)
                .map(userRepository::getById)
                .map(User::getLogin)
                .orElse(null);
    }
}
