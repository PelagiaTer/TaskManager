package com.taskmanager.repository.mybatis.mapper;

import com.taskmanager.model.Project;
import com.taskmanager.model.User;
import com.taskmanager.utils.BasePersistTest;
import com.taskmanager.utils.MyEasyRandom;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class ProjectMapperTest extends BasePersistTest {

    @Autowired
    private ProjectMapper mapper;
    @Autowired
    private UserMapper userMapper;

    @Test
    void save_and_getById() {
        var user = MyEasyRandom.nextObject(User.class);
        userMapper.save(user);
        var expected = MyEasyRandom.nextObject(Project.class);
        expected.setCreateUserId(user.getId());
        expected.setUpdateUserId(null);

        mapper.save(expected);

        var actual = mapper.getById(expected.getId());

        assertEquals(expected, actual);
    }

    @Test
    void update() {
        var user = MyEasyRandom.nextObject(User.class);
        userMapper.save(user);
        var expected = MyEasyRandom.nextObject(Project.class);
        expected.setCreateUserId(user.getId());
        expected.setUpdateUserId(user.getId());
        mapper.save(expected);
        expected.setDescription("new_description");

        mapper.update(expected);

        var actualUpdate = mapper.getById(expected.getId());

        assertEquals(expected, actualUpdate);
    }

    @Test
    void getByName() {
        var user = MyEasyRandom.nextObject(User.class);
        userMapper.save(user);
        var expected = MyEasyRandom.nextObject(Project.class);
        expected.setCreateUserId(user.getId());
        expected.setUpdateUserId(null);

        mapper.save(expected);

        var actual = mapper.getByName(expected.getName());

        assertEquals(expected, actual);
    }

    @Test
    void existByName() {
        var user = MyEasyRandom.nextObject(User.class);
        userMapper.save(user);
        var expected = MyEasyRandom.nextObject(Project.class);
        expected.setCreateUserId(user.getId());
        expected.setUpdateUserId(null);

        mapper.save(expected);

        var actual = mapper.existByName(expected.getName());

        assertTrue(actual);
    }

    @Test
    void deleteById() {
        var user = MyEasyRandom.nextObject(User.class);
        userMapper.save(user);
        var expected = MyEasyRandom.nextObject(Project.class);
        expected.setCreateUserId(user.getId());
        expected.setUpdateUserId(null);

        mapper.save(expected);
        assertTrue(mapper.existByName(expected.getName()));

        mapper.deleteById(expected.getId());

        assertFalse(mapper.existByName(expected.getName()));
    }

    @Test
    void getByCreateUserId() {
        var user = MyEasyRandom.nextObject(User.class);
        userMapper.save(user);
        var expected = MyEasyRandom.nextObject(Project.class);
        expected.setCreateUserId(user.getId());
        expected.setUpdateUserId(null);

        mapper.save(expected);

        var actual = mapper.getByCreateUserId(expected.getCreateUserId());

        assertEquals(1, actual.size());
        assertEquals(expected, actual.get(0));
    }

    @Test
    void getAll() {
        var user = MyEasyRandom.nextObject(User.class);
        userMapper.save(user);
        var expected = MyEasyRandom.nextObject(Project.class);
        expected.setCreateUserId(user.getId());
        expected.setUpdateUserId(null);

        mapper.save(expected);

        var actual = mapper.getAll(10, 0);

        assertEquals(1, actual.size());
        assertEquals(expected, actual.get(0));
    }

    @Test
    void findProjects() {
        var user = MyEasyRandom.nextObject(User.class);
        userMapper.save(user);
        var expected1 = MyEasyRandom.nextObject(Project.class);
        expected1.setName("name1");
        expected1.setCreateUserId(user.getId());
        expected1.setUpdateUserId(null);
        mapper.save(expected1);

        var expected2 = MyEasyRandom.nextObject(Project.class);
        expected2.setName("name2");
        expected2.setCreateUserId(user.getId());
        expected2.setUpdateUserId(null);
        mapper.save(expected2);

        var expected3 = MyEasyRandom.nextObject(Project.class);
        expected3.setName("not_name3");
        expected3.setCreateUserId(user.getId());
        expected3.setUpdateUserId(null);
        mapper.save(expected3);

        var actual = mapper.findProjects("name", 10);

        assertEquals(2, actual.size());
        assertEquals(expected1.getId(), actual.get(0).getId());
        assertEquals(expected1.getName(), actual.get(0).getName());
        assertEquals(expected2.getId(), actual.get(1).getId());
        assertEquals(expected2.getName(), actual.get(1).getName());
    }
}