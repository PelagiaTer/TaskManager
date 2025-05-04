package com.taskmanager.service;

import com.taskmanager.controllers.dto.autocomplete.AutocompleteDto;
import com.taskmanager.converter.AutocompleteDtoConverter;
import com.taskmanager.model.Autocomplete;
import com.taskmanager.repository.ProjectRepository;
import com.taskmanager.repository.UserRepository;
import com.taskmanager.utils.MyEasyRandom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AutocompleteServiceTest {

    @InjectMocks
    private AutocompleteService service;
    @Mock
    private UserRepository userRepository;
    @Mock
    private ProjectRepository projectRepository;
    @Mock
    private AutocompleteDtoConverter autocompleteDtoConverter;

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(service, "defaultLimit", 10);
    }

    @Test
    void findUsers() {
        when(userRepository.findUsers(any(), anyInt()))
                .thenReturn(List.of(MyEasyRandom.nextObject(Autocomplete.class)));
        var expected = MyEasyRandom.nextObject(AutocompleteDto.class);
        when(autocompleteDtoConverter.convert(any())).thenReturn(List.of(expected));

        var actual = service.findUsers("term", 10);

        assertEquals(expected, actual.getItems().get(0));
    }

    @Test
    void findProjects() {
        when(projectRepository.findProjects(any(), anyInt()))
                .thenReturn(List.of(MyEasyRandom.nextObject(Autocomplete.class)));
        var expected = MyEasyRandom.nextObject(AutocompleteDto.class);
        when(autocompleteDtoConverter.convert(any())).thenReturn(List.of(expected));

        var actual = service.findProjects("term", null);

        assertEquals(expected, actual.getItems().get(0));
    }
}