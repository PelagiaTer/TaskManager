package com.taskmanager.converter;

import com.taskmanager.controllers.dto.task.FilterTaskRequestDto;
import com.taskmanager.utils.MyEasyRandom;
import com.taskmanager.utils.SecurityContextExtension;
import com.taskmanager.utils.SecurityContextUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith({ MockitoExtension.class, SecurityContextExtension.class })
class FilterTaskConverterTest {

    @InjectMocks
    private FilterTaskConverter converter;

    @Test
    void convert() {
        var expected = MyEasyRandom.nextObject(FilterTaskRequestDto.class);

        var actual = converter.convert(expected);

        assertEquals(expected.getNumber(), actual.getNumber());
        assertEquals(expected.getStatus(), actual.getStatus());
        assertEquals(expected.getPriority(), actual.getPriority());
        assertEquals(SecurityContextUtils.getUser().getId(), actual.getCreateUserId());
        assertEquals(SecurityContextUtils.getUser().getId(), actual.getAssignedUserId());
        assertEquals(expected.getLimit(), actual.getLimit());
        assertEquals(expected.getOffset(), actual.getOffset());
    }

    @Test
    void convert_admin_def() {
        SecurityContextExtension.createContextAdmin();
        ReflectionTestUtils.setField(converter, "defaultLimit", 10);
        var expected = MyEasyRandom.nextObject(FilterTaskRequestDto.class);
        expected.setLimit(null);
        expected.setOffset(null);

        var actual = converter.convert(expected);

        assertEquals(expected.getNumber(), actual.getNumber());
        assertEquals(expected.getStatus(), actual.getStatus());
        assertEquals(expected.getPriority(), actual.getPriority());
        assertEquals(expected.getCreateUserId(), actual.getCreateUserId());
        assertEquals(expected.getAssignedUserId(), actual.getAssignedUserId());
        assertEquals(10, actual.getLimit());
        assertEquals(0, actual.getOffset());
    }
}