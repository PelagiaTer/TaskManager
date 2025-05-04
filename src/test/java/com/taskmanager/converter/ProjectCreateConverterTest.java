package com.taskmanager.converter;

import com.taskmanager.controllers.dto.project.ProjectCreateRequestDto;
import com.taskmanager.utils.MyEasyRandom;
import com.taskmanager.utils.SecurityContextExtension;
import com.taskmanager.utils.SecurityContextUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith({ MockitoExtension.class, SecurityContextExtension.class })
class ProjectCreateConverterTest {

    @InjectMocks
    private ProjectCreateConverter converter;

    @Test
    void convert() {
        var expected = MyEasyRandom.nextObject(ProjectCreateRequestDto.class);

        var actual = converter.convert(expected);

        assertNotNull(actual.getId());
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getDescription(), actual.getDescription());
        assertEquals(SecurityContextUtils.getUser().getId(), actual.getCreateUserId());
    }
}