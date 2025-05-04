package com.taskmanager.converter;

import com.taskmanager.controllers.dto.project.ProjectUpdateRequestDto;
import com.taskmanager.utils.MyEasyRandom;
import com.taskmanager.utils.SecurityContextExtension;
import com.taskmanager.utils.SecurityContextUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith({ MockitoExtension.class, SecurityContextExtension.class })
class ProjectUpdateConverterTest {

    @InjectMocks
    private ProjectUpdateConverter converter;

    @Test
    void convert() {
        var expected = MyEasyRandom.nextObject(ProjectUpdateRequestDto.class);

        var actual = converter.convert(expected);

        assertEquals(expected.getId(), actual.getId());
        assertEquals(SecurityContextUtils.getUser().getId(), actual.getUpdateUserId());
        assertEquals(expected.getDescription(), actual.getDescription());
    }
}