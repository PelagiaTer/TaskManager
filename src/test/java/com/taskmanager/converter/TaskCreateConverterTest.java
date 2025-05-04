package com.taskmanager.converter;

import com.taskmanager.controllers.dto.task.TaskCreateRequestDto;
import com.taskmanager.enams.TaskStatus;
import com.taskmanager.utils.MyEasyRandom;
import com.taskmanager.utils.SecurityContextExtension;
import com.taskmanager.utils.SecurityContextUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith({ MockitoExtension.class, SecurityContextExtension.class })
class TaskCreateConverterTest {

    @InjectMocks
    private TaskCreateConverter converter;

    @Test
    void convert() {
        var expected = MyEasyRandom.nextObject(TaskCreateRequestDto.class);

        var actual = converter.convert(expected);

        assertNotNull(actual.getId());
        assertEquals(expected.getTitle(), actual.getTitle());
        assertEquals(expected.getDescription(), actual.getDescription());
        assertEquals(TaskStatus.NEW.name(), actual.getStatus());
        assertEquals(expected.getPriority().name(), actual.getPriority());
        assertEquals(expected.getDateDue(), actual.getDateDue());
        assertEquals(expected.getProjectId(), actual.getProjectId());
        assertEquals(expected.getAssignedUserId(), actual.getAssignedUserId());
        assertEquals(SecurityContextUtils.getUser().getId(), actual.getCreateUserId());
    }
}