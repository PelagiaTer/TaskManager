package com.taskmanager.repository;

import com.taskmanager.model.CommentTask;
import com.taskmanager.repository.mybatis.mapper.CommentTaskMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class CommentTaskRepository {

    private final CommentTaskMapper commentTaskMapper;

    /**
     * Сохранение комментария задачи
     *
     * @param entity комментарий
     */
    public void save(CommentTask entity) {
        commentTaskMapper.save(entity);
    }

    /**
     * Обновление комментария задачи
     *
     * @param entity комментарий
     */
    public void update(CommentTask entity) {
        commentTaskMapper.update(entity);
    }

    /**
     * Получение комментария задачи по уникальному идентификатору
     *
     * @param id уникальный идентификатор
     * @return комментарий задачи
     */
    public CommentTask getById(UUID id) {
        return commentTaskMapper.getById(id);
    }

    /**
     * Получение всех комментариев задачи
     *
     * @param taskId идентификатор задачи
     * @return список комментариев задачи
     */
    public List<CommentTask> getByTaskId(UUID taskId) {
        return commentTaskMapper.getByTaskId(taskId);
    }

    /**
     * Проверка существования комментария
     *
     * @param id идентификатор комментария
     */
    public boolean existById(UUID id) {
        return commentTaskMapper.existById(id);
    }

    /**
     * Удаление комментария
     *
     * @param id идентификатор комментария
     */
    public void deleteById(UUID id) {
        commentTaskMapper.deleteById(id);
    }

}
