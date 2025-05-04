package com.taskmanager.repository;

import com.taskmanager.model.Autocomplete;
import com.taskmanager.model.Project;
import com.taskmanager.repository.mybatis.mapper.ProjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class ProjectRepository {

    private final ProjectMapper projectMapper;

    /**
     * Сохранение проекта
     *
     * @param project проект
     */
    public void save(Project project) {
        projectMapper.save(project);
    }

    /**
     * Обновление проекта
     *
     * @param project проект
     */
    public void update(Project project) {
        projectMapper.update(project);
    }

    /**
     * Получение проекта по уникальному идентификатору
     *
     * @param id уникальный идентификатор
     * @return проект
     */
    public Project getById(UUID id) {
        return projectMapper.getById(id);
    }

    /**
     * Получение проекта по имени
     *
     * @param name имя проекта
     * @return проект
     */
    public Project getByName(String name) {
        return projectMapper.getByName(name);
    }

    /**
     * Проверка существования проекта
     *
     * @param name имя проекта
     * @return true - проект существует, false - проекта нет
     */
    public boolean existByName(String name) {
        return projectMapper.existByName(name);
    }

    /**
     * Удаление проекта по идентификатору
     *
     * @param id идентификатор
     */
    public void deleteById(UUID id) {
        projectMapper.deleteById(id);
    }

    /**
     * Получение проектов созданных пользователем и не удаленных
     *
     * @param userId идентификатор пользователя
     * @return список проектов пользователя
     */
    public List<Project> getByCreateUserId(UUID userId) {
        return projectMapper.getByCreateUserId(userId);
    }

    /**
     * Получение всех не удаленных проектов
     *
     * @param limit  лимит
     * @param offset сдвиг
     * @return список проектов
     */
    public List<Project> getAll(int limit, int offset) {
        return projectMapper.getAll(limit, offset);
    }

    /**
     * Поиск проектов для автокомплита
     *
     * @param term  искомое значение
     * @param limit количество записей
     * @return список проектов
     */
    public List<Autocomplete> findProjects(String term, int limit) {
        return projectMapper.findProjects(term, limit);
    }
}
