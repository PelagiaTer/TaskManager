package com.taskmanager.repository;

import com.taskmanager.model.User;
import com.taskmanager.model.Autocomplete;
import com.taskmanager.repository.mybatis.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    private final UserMapper userMapper;

    /**
     * Сохранение пользователя
     *
     * @param user пользователь
     */
    public void save(User user) {
        userMapper.save(user);
    }

    /**
     * Обновление пользователя
     *
     * @param user пользователь
     */
    public void update(User user) {
        userMapper.update(user);
    }

    /**
     * Получение пользователя по уникальному идентификатору
     *
     * @param id уникальный идентификатор
     * @return пользователь
     */
    public User getById(UUID id) {
        return userMapper.getById(id);
    }

    /**
     * Получение пользователя по имени
     *
     * @param login логин пользователя
     * @return пользователь
     */
    public User getByLogin(String login) {
        return userMapper.getByLogin(login);
    }

    /**
     * Поиск пользователей для автокомплита
     *
     * @param term  искомое значение
     * @param limit количество записей
     * @return список пользователей
     */
    public List<Autocomplete> findUsers(String term, int limit) {
        return userMapper.findUsers(term, limit);
    }

}
