package com.taskmanager.converter;

import com.taskmanager.controllers.dto.comment_task.CommentTaskCreateRequestDto;
import com.taskmanager.controllers.dto.comment_task.CommentTaskUpdateRequestDto;
import com.taskmanager.utils.MyEasyRandom;
import com.taskmanager.utils.SecurityContextExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith({ MockitoExtension.class, SecurityContextExtension.class })
class CommentTaskConverterTest {

    @InjectMocks
    private CommentTaskConverter converter;

    @Test
    void convertCreate() {
        var expected = MyEasyRandom.nextObject(CommentTaskCreateRequestDto.class);

        var actual = converter.convertCreate(expected);

        assertNotNull(actual.getId());
        assertEquals(SecurityContextExtension.USER_CONTEXT_ID_DEFAULT, actual.getCreateUserId());
        assertEquals(expected.getTaskId(), actual.getTaskId());
        assertEquals(expected.getContent(), actual.getContent());
    }

    @Test
    void convertUpdate() {
        var expected = MyEasyRandom.nextObject(CommentTaskUpdateRequestDto.class);

        var actual = converter.convertUpdate(expected);

        assertNotNull(actual.getId());
        assertEquals(SecurityContextExtension.USER_CONTEXT_ID_DEFAULT, actual.getUpdateUserId());
        assertEquals(expected.getTaskId(), actual.getTaskId());
        assertEquals(expected.getContent(), actual.getContent());
    }
}