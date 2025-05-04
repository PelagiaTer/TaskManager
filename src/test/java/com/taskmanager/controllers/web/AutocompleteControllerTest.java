package com.taskmanager.controllers.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.taskmanager.controllers.dto.autocomplete.AutocompleteRequestDto;
import com.taskmanager.controllers.dto.autocomplete.AutocompleteResponseDto;
import com.taskmanager.service.AutocompleteService;
import com.taskmanager.utils.MyEasyRandom;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = AutocompleteController.class,
        properties = { "spring.cloud.bootstrap.enabled=false", "environment=test" })
@AutoConfigureMockMvc(addFilters = false)
@ContextConfiguration(classes = AutocompleteController.class)
class AutocompleteControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private AutocompleteService autocompleteService;

    @Test
    void findUser() throws Exception {
        var request = MyEasyRandom.nextObject(AutocompleteRequestDto.class);
        var expected = AutocompleteResponseDto.builder().build();
        when(autocompleteService.findUsers(any(), anyInt())).thenReturn(expected);

        mockMvc.perform(post("/web/autocomplete/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(expected)));

        verify(autocompleteService).findUsers(any(), anyInt());
    }

    @Test
    void findProject() throws Exception {
        var request = MyEasyRandom.nextObject(AutocompleteRequestDto.class);
        var expected = AutocompleteResponseDto.builder().build();
        when(autocompleteService.findProjects(any(), anyInt())).thenReturn(expected);

        mockMvc.perform(post("/web/autocomplete/project")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(expected)));

        verify(autocompleteService).findProjects(any(), anyInt());
    }
}