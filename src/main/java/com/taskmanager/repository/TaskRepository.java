package com.taskmanager.repository;

import com.taskmanager.model.FilterTask;
import com.taskmanager.model.Task;
import com.taskmanager.repository.mybatis.mapper.TaskMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class TaskRepository {

    private final TaskMapper taskMapper;

    /**
     * Сохранение задачи
     *
     * @param task задача
     */
    public void save(Task task) {
        taskMapper.save(task);
    }

    /**
     * Изменение задачи
     *
     * @param task задача
     */
    public void update(Task task) {
        taskMapper.update(task);
    }

    /**
     * Получение задачи по идентификатору
     *
     * @param id идентификатор задачи
     * @return задача
     */
    public Task getById(UUID id) {
        return taskMapper.getById(id);
    }

    /**
     * Проверка существования задачи
     *
     * @param id идентификатор задачи
     */
    public boolean existById(UUID id) {
        return taskMapper.existById(id);
    }

    /**
     * Удаление задачи по идентификатору
     *
     * @param id идентификатор задачи
     */
    public void deleteById(UUID id) {
        taskMapper.deleteById(id);
    }

    /**
     * Поиск задач по фильтру
     *
     * @param filter фильтр
     * @return список задач
     */
    public List<Task> getByFilter(FilterTask filter) {
        return taskMapper.getByFilter(filter);
    }
}
