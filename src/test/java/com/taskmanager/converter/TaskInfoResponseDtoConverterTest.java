package com.taskmanager.converter;

import com.taskmanager.model.Project;
import com.taskmanager.model.Task;
import com.taskmanager.model.User;
import com.taskmanager.repository.ProjectRepository;
import com.taskmanager.repository.UserRepository;
import com.taskmanager.utils.MyEasyRandom;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TaskInfoResponseDtoConverterTest {

    @InjectMocks
    private TaskInfoResponseDtoConverter converter;
    @Mock
    private ProjectRepository projectRepository;
    @Mock
    private UserRepository userRepository;

    @Test
    void convert() {
        var expected = MyEasyRandom.nextObject(Task.class);
        var user = MyEasyRandom.nextObject(User.class);
        when(userRepository.getById(any())).thenReturn(user);
        var project = MyEasyRandom.nextObject(Project.class);
        when(projectRepository.getById(any())).thenReturn(project);

        var actual = converter.convert(expected);

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
        assertEquals(project.getName(), actual.getProjectName());
        assertEquals(expected.getAssignedUserId(), actual.getAssignedUserId());
        assertEquals(user.getLogin(), actual.getAssignedUserName());
        assertEquals(expected.getCreateUserId(), actual.getCreateUserId());
        assertEquals(user.getLogin(), actual.getCreateUserName());
    }
}