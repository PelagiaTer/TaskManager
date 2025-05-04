package com.taskmanager.converter;

import com.taskmanager.controllers.dto.project.ProjectCreateRequestDto;
import com.taskmanager.model.Project;
import com.taskmanager.utils.SecurityContextUtils;
import jakarta.annotation.Nonnull;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ProjectCreateConverter {

    @Nonnull
    public Project convert(@Nonnull ProjectCreateRequestDto source) {
        return Project.builder()
                .id(UUID.randomUUID())
                .name(source.getName())
                .description(source.getDescription())
                .createUserId(SecurityContextUtils.getUser().getId())
                .build();
    }
}
