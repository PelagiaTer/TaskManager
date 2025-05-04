package com.taskmanager.converter;

import com.taskmanager.model.Task;
import com.taskmanager.utils.MyEasyRandom;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class TaskDtoConverterTest {

    @InjectMocks
    private TaskDtoConverter converter;

    @Test
    void convert() {
        var expected = MyEasyRandom.nextObject(Task.class);

        var actual = converter.convert(List.of(expected)).get(0);

        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getNumber(), actual.getNumber());
        assertEquals(expected.getTitle(), actual.getTitle());
        assertEquals(expected.getDescription(), actual.getDescription());
        assertEquals(expected.getStatus(), actual.getStatus());
        assertEquals(expected.getPriority(), actual.getPriority());
        assertEquals(expected.getDateCreate(), actual.getDateCreate());
        assertEquals(expected.getDateDue(), actual.getDateDue());
        assertEquals(expected.getDateFact(), actual.getDateFact());
        assertEquals(expected.getProjectId(), actual.getProjectId());
        assertEquals(expected.getAssignedUserId(), actual.getAssignedUserId());
        assertEquals(expected.getCreateUserId(), actual.getCreateUserId());
    }
}