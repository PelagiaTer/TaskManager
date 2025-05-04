package com.taskmanager.controllers.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.taskmanager.controllers.dto.ResponseDto;
import com.taskmanager.controllers.dto.project.ProjectCreateRequestDto;
import com.taskmanager.controllers.dto.project.ProjectDeleteRequestDto;
import com.taskmanager.controllers.dto.project.ProjectGetAllResponseDto;
import com.taskmanager.controllers.dto.project.ProjectGetRequestDto;
import com.taskmanager.controllers.dto.project.ProjectUpdateRequestDto;
import com.taskmanager.service.ProjectService;
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
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ProjectController.class,
        properties = { "spring.cloud.bootstrap.enabled=false", "environment=test" })
@AutoConfigureMockMvc(addFilters = false)
@ContextConfiguration(classes = ProjectController.class)
class ProjectControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private ProjectService projectService;

    @Test
    void create() throws Exception {
        var request = MyEasyRandom.nextObject(ProjectCreateRequestDto.class);
        var expected = new ResponseDto();
        when(projectService.create(any())).thenReturn(expected);

        mockMvc.perform(post("/web/project/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(expected)));

        verify(projectService).create(any());
    }

    @Test
    void update() throws Exception {
        var request = MyEasyRandom.nextObject(ProjectUpdateRequestDto.class);
        var expected = new ResponseDto();
        when(projectService.update(any())).thenReturn(expected);

        mockMvc.perform(post("/web/project/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(expected)));

        verify(projectService).update(any());
    }

    @Test
    void delete() throws Exception {
        var request = MyEasyRandom.nextObject(ProjectDeleteRequestDto.class);
        var expected = new ResponseDto();
        when(projectService.delete(any())).thenReturn(expected);

        mockMvc.perform(post("/web/project/delete")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(expected)));

        verify(projectService).delete(any());
    }

    @Test
    void getAll() throws Exception {
        var request = MyEasyRandom.nextObject(ProjectGetRequestDto.class);
        var expected = ProjectGetAllResponseDto.builder().build();
        when(projectService.getProjects(anyBoolean(), anyInt(), anyInt())).thenReturn(expected);

        mockMvc.perform(post("/web/project/getAll")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(expected)));

        verify(projectService).getProjects(anyBoolean(), anyInt(), anyInt());
    }
}