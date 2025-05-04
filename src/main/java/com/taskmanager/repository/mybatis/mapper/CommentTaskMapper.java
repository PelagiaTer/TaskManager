package com.taskmanager.repository.mybatis.mapper;

import com.taskmanager.model.CommentTask;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.UUID;

@Mapper
public interface CommentTaskMapper {

    /**
     * Сохранение комментария задачи
     *
     * @param entity комментарий
     */
    void save(@Param("entity") CommentTask entity);

    /**
     * Обновление комментария задачи
     *
     * @param entity комментарий
     */
    void update(@Param("entity") CommentTask entity);

    /**
     * Получение комментария задачи по уникальному идентификатору
     *
     * @param id уникальный идентификатор
     * @return комментарий задачи
     */
    CommentTask getById(@Param("id") UUID id);

    /**
     * Получение всех комментариев задачи
     *
     * @param taskId идентификатор задачи
     * @return список комментариев задачи
     */
    List<CommentTask> getByTaskId(@Param("taskId") UUID taskId);

    /**
     * Проверка существования комментария
     *
     * @param id идентификатор комментария
     */
    boolean existById(@Param("id") UUID id);

    /**
     * Удаление комментария
     *
     * @param id идентификатор комментария
     */
    void deleteById(@Param("id") UUID id);
}
