package com.taskmanager.converter;

import com.taskmanager.model.Task;
import com.taskmanager.utils.MyEasyRandom;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class TaskCreateResponseDtoConverterTest {

    @InjectMocks
    private TaskCreateResponseDtoConverter converter;

    @Test
    void convert() {
        var expected = MyEasyRandom.nextObject(Task.class);

        var actual = converter.convert(expected);

        assertEquals(expected.getId(), actual.getTaskId());
        assertEquals(expected.getNumber(), actual.getTaskNumber());
        assertEquals("Задача " + expected.getNumber() + " успешно создана", actual.getAlerts().get(0).getMsg());
    }
}