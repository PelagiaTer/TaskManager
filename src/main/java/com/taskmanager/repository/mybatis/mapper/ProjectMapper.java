package com.taskmanager.repository.mybatis.mapper;

import com.taskmanager.model.Autocomplete;
import com.taskmanager.model.Project;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.UUID;

@Mapper
public interface ProjectMapper {

    /**
     * Сохранение проекта
     *
     * @param entity проект
     */
    void save(@Param("entity") Project entity);

    /**
     * Обновление проекта
     *
     * @param entity проект
     */
    void update(@Param("entity") Project entity);

    /**
     * Получение проекта по уникальному идентификатору
     *
     * @param id уникальный идентификатор
     * @return проект
     */
    Project getById(@Param("id") UUID id);


    /**
     * Получение проекта по имени
     *
     * @param name имя проекта
     * @return проект
     */
    Project getByName(@Param("name") String name);

    /**
     * Проверка существования проекта
     *
     * @param name имя проекта
     * @return true - проект существует, false - проекта нет
     */
    boolean existByName(@Param("name") String name);

    /**
     * Удаление проекта по идентификатору
     *
     * @param id идентификатор
     */
    void deleteById(@Param("id") UUID id);

    /**
     * Получение проектов созданных пользователем и не удаленных
     *
     * @param userId идентификатор пользователя
     * @return список проектов пользователя
     */
    List<Project> getByCreateUserId(@Param("userId") UUID userId);

    /**
     * Получение всех не удаленных проектов
     *
     * @param limit лимит
     * @param offset сдвиг
     * @return список проектов
     */
    List<Project> getAll(@Param("limit") int limit, @Param("offset") int offset);

    /**
     * Поиск проектов для автокомплита
     *
     * @param term искомое значение
     * @param limit количество записей
     * @return список проектов
     */
    List<Autocomplete> findProjects(@Param("term") String term, @Param("limit") int limit);

}
