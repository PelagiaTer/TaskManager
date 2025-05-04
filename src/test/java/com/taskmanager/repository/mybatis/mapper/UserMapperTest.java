package com.taskmanager.repository.mybatis.mapper;

import com.taskmanager.model.User;
import com.taskmanager.utils.BasePersistTest;
import com.taskmanager.utils.MyEasyRandom;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserMapperTest extends BasePersistTest {

    @Autowired
    private UserMapper mapper;

    @Test
    void save_and_getById() {
        var expected = MyEasyRandom.nextObject(User.class);

        mapper.save(expected);

        var actual = mapper.getById(expected.getId());

        assertEquals(expected, actual);
    }

    @Test
    void update() {
        var expected = MyEasyRandom.nextObject(User.class);
        mapper.save(expected);
        var expectedUpdate = MyEasyRandom.nextObject(User.class);
        expectedUpdate.setId(expected.getId());
        mapper.update(expectedUpdate);

        var actualUpdate = mapper.getById(expected.getId());

        assertEquals(expectedUpdate, actualUpdate);
    }

    @Test
    void getByLogin() {
        var expected = MyEasyRandom.nextObject(User.class);

        mapper.save(expected);

        var actual = mapper.getByLogin(expected.getLogin());

        assertEquals(expected, actual);
    }

    @Test
    void findUsers() {
        var expected1 = MyEasyRandom.nextObject(User.class);
        expected1.setLogin("login1");
        var expected2 = MyEasyRandom.nextObject(User.class);
        expected2.setLogin("login2");
        var expected3 = MyEasyRandom.nextObject(User.class);
        expected3.setLogin("not_login3");

        mapper.save(expected1);
        mapper.save(expected2);
        mapper.save(expected3);

        var actual = mapper.findUsers("login", 10);

        assertEquals(2, actual.size());
        assertEquals(expected1.getId(), actual.get(0).getId());
        assertEquals(expected1.getLogin(), actual.get(0).getName());
        assertEquals(expected2.getId(), actual.get(1).getId());
        assertEquals(expected2.getLogin(), actual.get(1).getName());
    }

}