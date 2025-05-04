package com.taskmanager.converter;

import com.taskmanager.model.Project;
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
class ProjectGetDtoConverterTest {

    @InjectMocks
    private ProjectGetDtoConverter converter;
    @Mock
    private UserRepository userRepository;

    @Test
    void convert() {
        var expected = MyEasyRandom.nextObject(Project.class);
        var user = MyEasyRandom.nextObject(User.class);
        when(userRepository.getById(any())).thenReturn(user);

        var actual = converter.convert(List.of(expected)).get(0);

        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getDescription(), actual.getDescription());
        assertEquals(expected.getCreateUserId(), actual.getCreateUserId());
        assertEquals(user.getLogin(), actual.getCreateUserLogin());
        assertEquals(expected.getUpdateUserId(), actual.getUpdateUserId());
        assertEquals(user.getLogin(), actual.getUpdateUserLogin());
    }
}