package com.mtv.erp.dao;

import com.mtv.erp.exception.ServerException;
import com.mtv.erp.model.User;
import com.mtv.erp.mybatis.daoimpl.UserDaoImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestUserDao {
    private final UserDao userDao = new UserDaoImpl();

    @Test
    public void testInsert() throws ServerException {
        User user = userDao.insert(new User(13243435, "Михаил", "Баранцев", "misha@gmail.com"));
        Assertions.assertNotEquals(0, user.getId());
    }

}
