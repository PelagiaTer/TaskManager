package com.taskmanager.converter;

import com.taskmanager.controllers.dto.task.TaskUpdateRequestDto;
import com.taskmanager.utils.MyEasyRandom;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class TaskUpdateConverterTest {

    @InjectMocks
    private TaskUpdateConverter converter;

    @Test
    void convert() {
        var expected = MyEasyRandom.nextObject(TaskUpdateRequestDto.class);

        var actual = converter.convert(expected);

        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getTitle(), actual.getTitle());
        assertEquals(expected.getDescription(), actual.getDescription());
        assertEquals(expected.getStatus().name(), actual.getStatus());
        assertEquals(expected.getPriority().name(), actual.getPriority());
        assertEquals(expected.getDateDue(), actual.getDateDue());
        assertEquals(expected.getProjectId(), actual.getProjectId());
        assertEquals(expected.getAssignedUserId(), actual.getAssignedUserId());
    }
}