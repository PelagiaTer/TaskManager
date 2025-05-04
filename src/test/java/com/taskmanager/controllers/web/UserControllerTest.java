package com.taskmanager.controllers.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.taskmanager.controllers.dto.user.JwtAuthenticationResponseDto;
import com.taskmanager.controllers.dto.user.UserAuthRequestDto;
import com.taskmanager.controllers.dto.user.UserCreateRequestDto;
import com.taskmanager.security.AuthenticationService;
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

@WebMvcTest(controllers = UserController.class,
        properties = { "spring.cloud.bootstrap.enabled=false", "environment=test" })
@AutoConfigureMockMvc(addFilters = false)
@ContextConfiguration(classes = UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private AuthenticationService authenticationService;

    @Test
    void signUp() throws Exception {
        var request = MyEasyRandom.nextObject(UserCreateRequestDto.class);
        var expected = JwtAuthenticationResponseDto.builder().build();
        when(authenticationService.signUp(any())).thenReturn(expected);

        mockMvc.perform(post("/auth/sign/up")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(expected)));

        verify(authenticationService).signUp(any());
    }

    @Test
    void signIn() throws Exception {
        var request = MyEasyRandom.nextObject(UserAuthRequestDto.class);
        var expected = JwtAuthenticationResponseDto.builder().build();
        when(authenticationService.signIn(any())).thenReturn(expected);

        mockMvc.perform(post("/auth/sign/in")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(expected)));

        verify(authenticationService).signIn(any());
    }
}