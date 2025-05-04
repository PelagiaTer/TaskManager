package com.taskmanager.converter;

import com.taskmanager.controllers.dto.comment_task.CommentTaskDto;
import com.taskmanager.model.CommentTask;
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
public class CommentTaskDtoConverter {

    private final UserRepository userRepository;

    @Nonnull
    public List<CommentTaskDto> convert(List<CommentTask> sources) {
        return Optional.ofNullable(sources)
                .orElseGet(Collections::emptyList)
                .stream()
                .filter(Objects::nonNull)
                .map(this::convert)
                .toList();
    }

    @Nonnull
    private CommentTaskDto convert(@Nonnull CommentTask source) {
        return CommentTaskDto.builder()
                .id(source.getId())
                .createUserId(source.getCreateUserId())
                .createUserName(getUserName(source.getCreateUserId()))
                .updateUserId(source.getUpdateUserId())
                .updateUserName(getUserName(source.getUpdateUserId()))
                .taskId(source.getTaskId())
                .content(source.getContent())
                .dateCreate(source.getDateCreate())
                .dateUpdate(source.getDateUpdate())
                .build();
    }

    private String getUserName(UUID userId) {
        return Optional.ofNullable(userId)
                .map(userRepository::getById)
                .map(User::getLogin)
                .orElse(null);
    }
}
