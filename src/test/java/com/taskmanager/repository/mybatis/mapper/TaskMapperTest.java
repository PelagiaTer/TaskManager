package com.taskmanager.repository.mybatis.mapper;

import com.taskmanager.model.FilterTask;
import com.taskmanager.model.Task;
import com.taskmanager.model.User;
import com.taskmanager.utils.BasePersistTest;
import com.taskmanager.utils.MyEasyRandom;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class TaskMapperTest extends BasePersistTest {

    @Autowired
    private TaskMapper mapper;
    @Autowired
    private UserMapper userMapper;

    @Test
    void save_and_getById() {
        var user = MyEasyRandom.nextObject(User.class);
        userMapper.save(user);
        var expected = MyEasyRandom.nextObject(Task.class);
        expected.setCreateUserId(user.getId());
        expected.setAssignedUserId(user.getId());

        mapper.save(expected);

        var actual = mapper.getById(expected.getId());
        expected.setNumber(actual.getNumber());
        expected.setDateCreate(actual.getDateCreate());

        assertEquals(expected, actual);
    }

    @Test
    void update() {
        var user = MyEasyRandom.nextObject(User.class);
        userMapper.save(user);
        var expected = MyEasyRandom.nextObject(Task.class);
        expected.setCreateUserId(user.getId());
        expected.setAssignedUserId(user.getId());
        mapper.save(expected);
        var actual = mapper.getById(expected.getId());
        assertEquals(expected.getDescription(), actual.getDescription());

        expected.setDescription("new_description");
        mapper.update(expected);

        var actualUpdate = mapper.getById(expected.getId());
        assertEquals(expected.getDescription(), actualUpdate.getDescription());
    }

    @Test
    void existById() {
        var user = MyEasyRandom.nextObject(User.class);
        userMapper.save(user);
        var expected = MyEasyRandom.nextObject(Task.class);
        expected.setCreateUserId(user.getId());
        expected.setAssignedUserId(user.getId());

        mapper.save(expected);

        var actual = mapper.existById(expected.getId());

        assertTrue(actual);
    }

    @Test
    void deleteById() {
        var user = MyEasyRandom.nextObject(User.class);
        userMapper.save(user);
        var expected = MyEasyRandom.nextObject(Task.class);
        expected.setCreateUserId(user.getId());
        expected.setAssignedUserId(user.getId());
        mapper.save(expected);

        assertTrue(mapper.existById(expected.getId()));

        mapper.deleteById(expected.getId());

        var actual = mapper.existById(expected.getId());

        assertFalse(actual);
    }

    @Test
    void getByFilter() {
        var user = MyEasyRandom.nextObject(User.class);
        userMapper.save(user);
        var expected = MyEasyRandom.nextObject(Task.class);
        expected.setCreateUserId(user.getId());
        expected.setAssignedUserId(user.getId());

        mapper.save(expected);

        var actual = mapper.getByFilter(FilterTask.builder()
                .status(expected.getStatus())
                .offset(0)
                .limit(10)
                .build());

        expected.setNumber(actual.get(0).getNumber());
        expected.setDateCreate(actual.get(0).getDateCreate());

        assertEquals(1, actual.size());
        assertEquals(expected, actual.get(0));
    }
}