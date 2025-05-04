package com.taskmanager.converter;

import com.taskmanager.controllers.dto.AlertDto;
import com.taskmanager.controllers.dto.task.TaskCreateResponseDto;
import com.taskmanager.model.Task;
import jakarta.annotation.Nonnull;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TaskCreateResponseDtoConverter {

    @Nonnull
    public TaskCreateResponseDto convert(@Nonnull Task task) {
        var taskDto = new TaskCreateResponseDto();
        taskDto.setTaskId(task.getId());
        taskDto.setTaskNumber(task.getNumber());
        taskDto.setAlerts(List.of(new AlertDto("Задача " + task.getNumber() + " успешно создана")));
        return taskDto;
    }
}
