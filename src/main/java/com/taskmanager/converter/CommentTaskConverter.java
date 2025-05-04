package com.taskmanager.converter;

import com.taskmanager.controllers.dto.comment_task.CommentTaskCreateRequestDto;
import com.taskmanager.controllers.dto.comment_task.CommentTaskUpdateRequestDto;
import com.taskmanager.model.CommentTask;
import com.taskmanager.utils.SecurityContextUtils;
import jakarta.annotation.Nonnull;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CommentTaskConverter {

    @Nonnull
    public CommentTask convertCreate(@Nonnull CommentTaskCreateRequestDto requestDto) {
        return CommentTask.builder()
                .id(UUID.randomUUID())
                .createUserId(SecurityContextUtils.getUser().getId())
                .taskId(requestDto.getTaskId())
                .content(requestDto.getContent())
                .build();
    }

    @Nonnull
    public CommentTask convertUpdate(@Nonnull CommentTaskUpdateRequestDto requestDto) {
        return CommentTask.builder()
                .id(requestDto.getId())
                .updateUserId(SecurityContextUtils.getUser().getId())
                .taskId(requestDto.getTaskId())
                .content(requestDto.getContent())
                .build();
    }
}
