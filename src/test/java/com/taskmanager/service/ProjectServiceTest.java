package com.taskmanager.service;

import com.taskmanager.controllers.dto.project.ProjectCreateRequestDto;
import com.taskmanager.controllers.dto.project.ProjectGetDto;
import com.taskmanager.controllers.dto.project.ProjectUpdateRequestDto;
import com.taskmanager.converter.ProjectCreateConverter;
import com.taskmanager.converter.ProjectGetDtoConverter;
import com.taskmanager.converter.ProjectUpdateConverter;
import com.taskmanager.exception.ForbiddenException;
import com.taskmanager.exception.NotFoundException;
import com.taskmanager.exception.ValidateException;
import com.taskmanager.model.Project;
import com.taskmanager.repository.ProjectRepository;
import com.taskmanager.utils.MyEasyRandom;
import com.taskmanager.utils.SecurityContextExtension;
import com.taskmanager.utils.SecurityContextUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith({ MockitoExtension.class, SecurityContextExtension.class })
class ProjectServiceTest {

    @InjectMocks
    private ProjectService service;
    @Mock
    private ProjectRepository projectRepository;
    @Mock
    private ProjectCreateConverter projectCreateConverter;
    @Mock
    private ProjectUpdateConverter projectUpdateConverter;
    @Mock
    private ProjectGetDtoConverter projectGetDtoConverter;

    @Test
    void create() {
        var request = MyEasyRandom.nextObject(ProjectCreateRequestDto.class);
        when(projectRepository.existByName(any())).thenReturn(false);
        var project = MyEasyRandom.nextObject(Project.class);
        when(projectCreateConverter.convert(any())).thenReturn(project);
        doNothing().when(projectRepository).save(any());

        var actual = service.create(request);

        assertEquals("Проект " + project.getName() + " успешно создан", actual.getAlerts().get(0).getMsg());
    }

    @Test
    void create_exist() {
        var request = MyEasyRandom.nextObject(ProjectCreateRequestDto.class);
        when(projectRepository.existByName(any())).thenReturn(true);

        assertThrows(ValidateException.class, () -> service.create(request));
    }

    @Test
    void update() {
        var request = MyEasyRandom.nextObject(ProjectUpdateRequestDto.class);
        var persistProject = MyEasyRandom.nextObject(Project.class);
        persistProject.setCreateUserId(SecurityContextUtils.getUser().getId());
        when(projectRepository.getById(any())).thenReturn(persistProject);
        var project = MyEasyRandom.nextObject(Project.class);
        when(projectUpdateConverter.convert(any())).thenReturn(project);
        doNothing().when(projectRepository).update(any());

        var actual = service.update(request);

        assertEquals("Проект успешно обновлен", actual.getAlerts().get(0).getMsg());
    }

    @Test
    void update_notPermit() {
        var request = MyEasyRandom.nextObject(ProjectUpdateRequestDto.class);
        when(projectRepository.getById(any())).thenReturn(MyEasyRandom.nextObject(Project.class));

        assertThrows(ForbiddenException.class, () -> service.update(request));
    }

    @Test
    void update_notFound() {
        var request = MyEasyRandom.nextObject(ProjectUpdateRequestDto.class);
        when(projectRepository.getById(any())).thenReturn(null);

        assertThrows(NotFoundException.class, () -> service.update(request));
    }

    @Test
    void delete() {
        var request = UUID.randomUUID();
        var persistProject = MyEasyRandom.nextObject(Project.class);
        persistProject.setCreateUserId(SecurityContextUtils.getUser().getId());
        when(projectRepository.getById(any())).thenReturn(persistProject);
        doNothing().when(projectRepository).deleteById(any());

        var actual = service.delete(request);

        assertEquals("Проект успешно удален", actual.getAlerts().get(0).getMsg());
    }

    @Test
    void delete_notPermit() {
        var request = UUID.randomUUID();
        when(projectRepository.getById(any())).thenReturn(MyEasyRandom.nextObject(Project.class));

        assertThrows(ForbiddenException.class, () -> service.delete(request));
    }

    @Test
    void delete_notFound() {
        var request = UUID.randomUUID();
        when(projectRepository.getById(any())).thenReturn(null);

        assertThrows(NotFoundException.class, () -> service.delete(request));
    }

    @Test
    void getProjects_admin_isNotMyCreateProject() {
        SecurityContextExtension.createContextAdmin();
        when(projectRepository.getAll(anyInt(), anyInt())).thenReturn(List.of(MyEasyRandom.nextObject(Project.class)));
        var expected = MyEasyRandom.nextObject(ProjectGetDto.class);
        when(projectGetDtoConverter.convert(any())).thenReturn(List.of(expected));

        var actual = service.getProjects(false, 10, 10);

        assertEquals(expected, actual.getItems().get(0));
    }

    @Test
    void getProjects_admin_isMyCreateProject() {
        SecurityContextExtension.createContextAdmin();
        when(projectRepository.getByCreateUserId(any())).thenReturn(List.of(MyEasyRandom.nextObject(Project.class)));
        var expected = MyEasyRandom.nextObject(ProjectGetDto.class);
        when(projectGetDtoConverter.convert(any())).thenReturn(List.of(expected));

        var actual = service.getProjects(true, 10, 10);

        assertEquals(expected, actual.getItems().get(0));
    }

    @Test
    void getProjects_user() {
        when(projectRepository.getByCreateUserId(any())).thenReturn(List.of(MyEasyRandom.nextObject(Project.class)));
        var expected = MyEasyRandom.nextObject(ProjectGetDto.class);
        when(projectGetDtoConverter.convert(any())).thenReturn(List.of(expected));

        var actual = service.getProjects(false, 10, 10);

        assertEquals(expected, actual.getItems().get(0));
    }
}