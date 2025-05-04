package com.taskmanager.repository.mybatis.mapper;

import com.taskmanager.model.FilterTask;
import com.taskmanager.model.Task;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.UUID;

@Mapper
public interface TaskMapper {

    /**
     * Сохранение задачи
     *
     * @param entity задача
     */
    void save(@Param("entity") Task entity);

    /**
     * Изменение задачи
     *
     * @param entity задача
     */
    void update(@Param("entity") Task entity);

    /**
     * Получение задачи по идентификатору
     *
     * @param id идентификатор задачи
     * @return задача
     */
    Task getById(@Param("id") UUID id);

    /**
     * Проверка существования задачи
     *
     * @param id идентификатор задачи
     */
    boolean existById(@Param("id") UUID id);

    /**
     * Удаление задачи по идентификатору
     *
     * @param id идентификатор задачи
     */
    void deleteById(@Param("id") UUID id);

    /**
     * Поиск задач по фильтру
     *
     * @param filter фильтр
     * @return список задач
     */
    List<Task> getByFilter(@Param("filter") FilterTask filter);

}
