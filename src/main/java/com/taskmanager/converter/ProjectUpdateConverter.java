package com.taskmanager.converter;

import com.taskmanager.controllers.dto.project.ProjectUpdateRequestDto;
import com.taskmanager.model.Project;
import com.taskmanager.utils.SecurityContextUtils;
import jakarta.annotation.Nonnull;
import org.springframework.stereotype.Component;

@Component
public class ProjectUpdateConverter {

    @Nonnull
    public Project convert(@Nonnull ProjectUpdateRequestDto source) {
        return Project.builder()
                .id(source.getId())
                .updateUserId(SecurityContextUtils.getUser().getId())
                .description(source.getDescription())
                .build();
    }
}
