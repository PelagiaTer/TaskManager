package com.taskmanager.converter;

import com.taskmanager.model.CommentTask;
import com.taskmanager.model.User;
import com.taskmanager.repository.UserRepository;
import com.taskmanager.utils.MyEasyRandom;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CommentTaskDtoConverterTest {

    @InjectMocks
    private CommentTaskDtoConverter converter;
    @Mock
    private UserRepository userRepository;

    @Test
    void convert() {
        var expected = MyEasyRandom.nextObject(CommentTask.class);
        var user = MyEasyRandom.nextObject(User.class);
        when(userRepository.getById(any())).thenReturn(user);

        var actual = converter.convert(List.of(expected)).get(0);

        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getCreateUserId(), actual.getCreateUserId());
        assertEquals(user.getLogin(), actual.getCreateUserName());
        assertEquals(expected.getUpdateUserId(), actual.getUpdateUserId());
        assertEquals(user.getLogin(), actual.getUpdateUserName());
        assertEquals(expected.getTaskId(), actual.getTaskId());
        assertEquals(expected.getContent(), actual.getContent());
        assertEquals(expected.getDateCreate(), actual.getDateCreate());
        assertEquals(expected.getDateUpdate(), actual.getDateUpdate());
    }
}