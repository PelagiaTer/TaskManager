package com.taskmanager.controllers.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.taskmanager.controllers.dto.ResponseDto;
import com.taskmanager.controllers.dto.comment_task.CommentTaskCreateRequestDto;
import com.taskmanager.controllers.dto.comment_task.CommentTaskDeleteRequestDto;
import com.taskmanager.controllers.dto.comment_task.CommentTaskGetRequestDto;
import com.taskmanager.controllers.dto.comment_task.CommentTaskResponseDto;
import com.taskmanager.controllers.dto.comment_task.CommentTaskUpdateRequestDto;
import com.taskmanager.service.CommentTaskService;
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

@WebMvcTest(controllers = CommentTaskController.class,
        properties = { "spring.cloud.bootstrap.enabled=false", "environment=test" })
@AutoConfigureMockMvc(addFilters = false)
@ContextConfiguration(classes = CommentTaskController.class)
class CommentTaskControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private CommentTaskService commentTaskService;

    @Test
    void create() throws Exception {
        var request = MyEasyRandom.nextObject(CommentTaskCreateRequestDto.class);
        var expected = new ResponseDto();
        when(commentTaskService.create(any())).thenReturn(expected);

        mockMvc.perform(post("/web/comment/task/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(expected)));

        verify(commentTaskService).create(any());
    }

    @Test
    void update() throws Exception {
        var request = MyEasyRandom.nextObject(CommentTaskUpdateRequestDto.class);
        var expected = new ResponseDto();
        when(commentTaskService.update(any())).thenReturn(expected);

        mockMvc.perform(post("/web/comment/task/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(expected)));

        verify(commentTaskService).update(any());
    }

    @Test
    void delete() throws Exception {
        var request = MyEasyRandom.nextObject(CommentTaskDeleteRequestDto.class);
        var expected = new ResponseDto();
        when(commentTaskService.delete(any())).thenReturn(expected);

        mockMvc.perform(post("/web/comment/task/delete")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(expected)));

        verify(commentTaskService).delete(any());
    }

    @Test
    void get() throws Exception {
        var request = MyEasyRandom.nextObject(CommentTaskGetRequestDto.class);
        var expected = CommentTaskResponseDto.builder().build();
        when(commentTaskService.getByTaskId(any())).thenReturn(expected);

        mockMvc.perform(post("/web/comment/task/get")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(expected)));

        verify(commentTaskService).getByTaskId(any());
    }
}