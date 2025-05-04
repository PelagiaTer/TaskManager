package com.taskmanager.repository.mybatis.mapper;

import com.taskmanager.model.CommentTask;
import com.taskmanager.model.Task;
import com.taskmanager.model.User;
import com.taskmanager.utils.BasePersistTest;
import com.taskmanager.utils.MyEasyRandom;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class CommentTaskMapperTest extends BasePersistTest {

    @Autowired
    private CommentTaskMapper mapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private TaskMapper taskMapper;

    @Test
    void save() {
        var user = MyEasyRandom.nextObject(User.class);
        userMapper.save(user);
        var task = MyEasyRandom.nextObject(Task.class);
        task.setAssignedUserId(user.getId());
        task.setCreateUserId(user.getId());
        taskMapper.save(task);
        var expected = MyEasyRandom.nextObject(CommentTask.class);
        expected.setTaskId(task.getId());
        expected.setCreateUserId(user.getId());
        expected.setUpdateUserId(null);
        expected.setDateUpdate(null);

        mapper.save(expected);

        var actual = mapper.getById(expected.getId());
        expected.setDateCreate(actual.getDateCreate());

        assertEquals(expected, actual);
    }

    @Test
    void update() {
        var user = MyEasyRandom.nextObject(User.class);
        userMapper.save(user);
        var task = MyEasyRandom.nextObject(Task.class);
        task.setAssignedUserId(user.getId());
        task.setCreateUserId(user.getId());
        taskMapper.save(task);
        var expected = MyEasyRandom.nextObject(CommentTask.class);
        expected.setTaskId(task.getId());
        expected.setCreateUserId(user.getId());
        expected.setUpdateUserId(null);
        expected.setDateUpdate(null);
        mapper.save(expected);

        expected.setContent("new");
        mapper.update(expected);

        var actual = mapper.getById(expected.getId());

        assertEquals("new", actual.getContent());
    }

    @Test
    void getById() {
        var user = MyEasyRandom.nextObject(User.class);
        userMapper.save(user);
        var task = MyEasyRandom.nextObject(Task.class);
        task.setAssignedUserId(user.getId());
        task.setCreateUserId(user.getId());
        taskMapper.save(task);
        var expected = MyEasyRandom.nextObject(CommentTask.class);
        expected.setTaskId(task.getId());
        expected.setCreateUserId(user.getId());
        expected.setUpdateUserId(null);
        expected.setDateUpdate(null);
        mapper.save(expected);

        var actual = mapper.getById(expected.getId());
        expected.setDateCreate(actual.getDateCreate());

        assertEquals(expected, actual);
    }

    @Test
    void getByTaskId() {
        var user = MyEasyRandom.nextObject(User.class);
        userMapper.save(user);
        var task = MyEasyRandom.nextObject(Task.class);
        task.setAssignedUserId(user.getId());
        task.setCreateUserId(user.getId());
        taskMapper.save(task);
        var expected = MyEasyRandom.nextObject(CommentTask.class);
        expected.setTaskId(task.getId());
        expected.setCreateUserId(user.getId());
        expected.setUpdateUserId(null);
        expected.setDateUpdate(null);
        mapper.save(expected);

        var actual = mapper.getByTaskId(expected.getTaskId());
        expected.setDateCreate(actual.get(0).getDateCreate());

        assertEquals(expected, actual.get(0));
    }

    @Test
    void existById() {
        var user = MyEasyRandom.nextObject(User.class);
        userMapper.save(user);
        var task = MyEasyRandom.nextObject(Task.class);
        task.setAssignedUserId(user.getId());
        task.setCreateUserId(user.getId());
        taskMapper.save(task);
        var expected = MyEasyRandom.nextObject(CommentTask.class);
        expected.setTaskId(task.getId());
        expected.setCreateUserId(user.getId());
        expected.setUpdateUserId(null);
        expected.setDateUpdate(null);
        mapper.save(expected);

        var actual = mapper.existById(expected.getId());

        assertTrue(actual);
    }

    @Test
    void deleteById() {
        var user = MyEasyRandom.nextObject(User.class);
        userMapper.save(user);
        var task = MyEasyRandom.nextObject(Task.class);
        task.setAssignedUserId(user.getId());
        task.setCreateUserId(user.getId());
        taskMapper.save(task);
        var expected = MyEasyRandom.nextObject(CommentTask.class);
        expected.setTaskId(task.getId());
        expected.setCreateUserId(user.getId());
        expected.setUpdateUserId(null);
        expected.setDateUpdate(null);
        mapper.save(expected);
        assertTrue(mapper.existById(expected.getId()));

        mapper.deleteById(expected.getId());

        var actual = mapper.existById(expected.getId());

        assertFalse(actual);
    }
}