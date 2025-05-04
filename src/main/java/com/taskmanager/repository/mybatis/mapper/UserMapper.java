package com.taskmanager.repository.mybatis.mapper;

import com.taskmanager.model.User;
import com.taskmanager.model.Autocomplete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.UUID;

@Mapper
public interface UserMapper {

    /**
     * Сохранение пользователя
     *
     * @param entity пользователь
     */
    void save(@Param("entity") User entity);

    /**
     * Обновление пользователя
     *
     * @param entity пользователь
     */
    void update(@Param("entity") User entity);

    /**
     * Получение пользователя по уникальному идентификатору
     *
     * @param id уникальный идентификатор
     * @return пользователь
     */
    User getById(@Param("id") UUID id);

    /**
     * Получение пользователя по имени
     *
     * @param login логин пользователя
     * @return пользователь
     */
    User getByLogin(@Param("login") String login);

    /**
     * Поиск пользователей для автокомплита
     *
     * @param term искомое значение
     * @param limit количество записей
     * @return список пользователей
     */
    List<Autocomplete> findUsers(@Param("term") String term, @Param("limit") int limit);


}
