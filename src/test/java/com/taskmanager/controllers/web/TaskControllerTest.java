package com.taskmanager.controllers.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.taskmanager.controllers.dto.ResponseDto;
import com.taskmanager.controllers.dto.task.FilterTaskRequestDto;
import com.taskmanager.controllers.dto.task.TaskCreateRequestDto;
import com.taskmanager.controllers.dto.task.TaskCreateResponseDto;
import com.taskmanager.controllers.dto.task.TaskDeleteRequestDto;
import com.taskmanager.controllers.dto.task.TaskInfoRequestDto;
import com.taskmanager.controllers.dto.task.TaskInfoResponseDto;
import com.taskmanager.controllers.dto.task.TaskResponseDto;
import com.taskmanager.controllers.dto.task.TaskUpdateRequestDto;
import com.taskmanager.controllers.dto.task.TaskUpdateResponseDto;
import com.taskmanager.service.TaskService;
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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = TaskController.class,
        properties = { "spring.cloud.bootstrap.enabled=false", "environment=test" })
@AutoConfigureMockMvc(addFilters = false)
@ContextConfiguration(classes = TaskController.class)
class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private TaskService taskService;

    @Test
    void create() throws Exception {
        var request = MyEasyRandom.nextObject(TaskCreateRequestDto.class);
        var expected = new TaskCreateResponseDto();
        when(taskService.create(any())).thenReturn(expected);

        mockMvc.perform(post("/web/task/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(expected)));

        verify(taskService).create(any());
    }

    @Test
    void update() throws Exception {
        var request = MyEasyRandom.nextObject(TaskUpdateRequestDto.class);
        var expected = new TaskUpdateResponseDto();
        when(taskService.update(any())).thenReturn(expected);

        mockMvc.perform(post("/web/task/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(expected)));

        verify(taskService).update(any());
    }

    @Test
    void delete() throws Exception {
        var request = MyEasyRandom.nextObject(TaskDeleteRequestDto.class);
        var expected = new ResponseDto();
        when(taskService.delete(any())).thenReturn(expected);

        mockMvc.perform(post("/web/task/delete")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(expected)));

        verify(taskService).delete(any());
    }

    @Test
    void getTaskInfoById() throws Exception {
        var request = MyEasyRandom.nextObject(TaskInfoRequestDto.class);
        var expected = new TaskInfoResponseDto();
        when(taskService.getTaskInfoById(any())).thenReturn(expected);

        mockMvc.perform(post("/web/task/get")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(expected)));

        verify(taskService).getTaskInfoById(any());
    }

    @Test
    void getTasks() throws Exception {
        var request = MyEasyRandom.nextObject(FilterTaskRequestDto.class);
        var expected = new TaskResponseDto();
        when(taskService.getTasks(any())).thenReturn(expected);

        mockMvc.perform(post("/web/task/filter")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(expected)));

        verify(taskService).getTasks(any());
    }
}