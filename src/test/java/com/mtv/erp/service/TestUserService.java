package com.mtv.erp.service;

import com.mtv.erp.exception.ServerException;
import org.junit.jupiter.api.Test;

public class TestUserService {
    UserService userService = new UserService();

    @Test
    public void testUpdate() throws ServerException {
        userService.update();
    }
}
