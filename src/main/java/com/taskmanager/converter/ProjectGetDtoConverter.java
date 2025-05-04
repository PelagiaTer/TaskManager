package com.taskmanager.converter;

import com.taskmanager.controllers.dto.project.ProjectGetDto;
import com.taskmanager.model.Project;
import com.taskmanager.model.User;
import com.taskmanager.repository.UserRepository;
import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ProjectGetDtoConverter {

    private final UserRepository userRepository;

    @Nonnull
    public List<ProjectGetDto> convert(List<Project> sources) {
        return Optional.ofNullable(sources)
                .orElseGet(Collections::emptyList)
                .stream()
                .filter(Objects::nonNull)
                .map(this::convert)
                .toList();
    }

    @Nonnull
    private ProjectGetDto convert(@Nonnull Project source) {
        return ProjectGetDto.builder()
                .id(source.getId())
                .name(source.getName())
                .description(source.getDescription())
                .createUserId(source.getCreateUserId())
                .createUserLogin(getUserLogin(source.getCreateUserId()))
                .updateUserId(source.getUpdateUserId())
                .updateUserLogin(getUserLogin(source.getUpdateUserId()))
                .build();
    }

    private String getUserLogin(UUID userId) {
        return Optional.ofNullable(userId)
                .map(userRepository::getById)
                .map(User::getLogin)
                .orElse(null);
    }

}
