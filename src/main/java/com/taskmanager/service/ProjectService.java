package com.taskmanager.service;

import com.taskmanager.converter.ProjectCreateConverter;
import com.taskmanager.converter.ProjectGetDtoConverter;
import com.taskmanager.converter.ProjectUpdateConverter;
import com.taskmanager.controllers.dto.AlertDto;
import com.taskmanager.controllers.dto.project.ProjectCreateRequestDto;
import com.taskmanager.controllers.dto.project.ProjectGetAllResponseDto;
import com.taskmanager.controllers.dto.project.ProjectUpdateRequestDto;
import com.taskmanager.controllers.dto.ResponseDto;
import com.taskmanager.enams.Role;
import com.taskmanager.exception.ForbiddenException;
import com.taskmanager.exception.NotFoundException;
import com.taskmanager.exception.ValidateException;
import com.taskmanager.model.Project;
import com.taskmanager.repository.ProjectRepository;
import com.taskmanager.utils.SecurityContextUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final ProjectCreateConverter projectCreateConverter;
    private final ProjectUpdateConverter projectUpdateConverter;
    private final ProjectGetDtoConverter projectGetDtoConverter;

    public ResponseDto create(ProjectCreateRequestDto requestDto) {
        if (projectRepository.existByName(requestDto.getName())) {
            throw new ValidateException("Проект " + requestDto.getName() + " уже существует",
                    "PROJECT_CREATE_EXIST_ERROR");
        }
        var project = projectCreateConverter.convert(requestDto);
        projectRepository.save(project);
        return new ResponseDto(new AlertDto("Проект " + project.getName() + " успешно создан"));
    }

    public ResponseDto update(ProjectUpdateRequestDto requestDto) {
        var projectPersist = projectRepository.getById(requestDto.getId());
        if (projectPersist == null) {
            throw new NotFoundException("Проект не найден");
        }
        checkRole(projectPersist);
        var project = projectUpdateConverter.convert(requestDto);
        projectRepository.update(project);
        return new ResponseDto(new AlertDto("Проект успешно обновлен"));
    }

    public ResponseDto delete(UUID projectId) {
        var projectPersist = projectRepository.getById(projectId);
        if (projectPersist == null) {
            throw new NotFoundException("Проект не найден");
        }
        checkRole(projectPersist);

        projectRepository.deleteById(projectId);
        return new ResponseDto(new AlertDto("Проект успешно удален"));
    }

    public ProjectGetAllResponseDto getProjects(boolean isMyCreateProject, int limit, int offset) {
        var user = SecurityContextUtils.getUser();
        if (Role.ROLE_ADMIN.name().equals(user.getRole()) && !isMyCreateProject) {
            var projects = projectRepository.getAll(limit, offset);
            var projectsDto = projectGetDtoConverter.convert(projects);
            return new ProjectGetAllResponseDto(projectsDto);
        }
        var projects = projectRepository.getByCreateUserId(user.getId());
        var projectsDto = projectGetDtoConverter.convert(projects);
        return new ProjectGetAllResponseDto(projectsDto);
    }


    private void checkRole(Project projectPersist) {
        var user = SecurityContextUtils.getUser();
        if (Role.ROLE_ADMIN.name().equals(user.getRole())) {
            return;
        }
        if (Role.ROLE_USER.name().equals(user.getRole()) &&
                !projectPersist.getCreateUserId().equals(user.getId())) {
            throw new ForbiddenException("У пользователя недостаточно прав для редактирования/удаления проекта");
        }
    }


}
