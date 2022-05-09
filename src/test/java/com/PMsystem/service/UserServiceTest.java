package com.PMsystem.service;

import com.PMsystem.entity.Role;
import com.PMsystem.entity.UserEntity;
import com.PMsystem.exception.AlreadyExistsException;
import com.PMsystem.repository.UserRepo;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;


@RunWith(SpringRunner.class)
@SpringBootTest
class UserServiceTest {

    @MockBean
    private UserRepo userRepo;
    @Autowired
    private UserService userService;

    @Test
    public void addUserTest() throws AlreadyExistsException {
        UserEntity user = new UserEntity();
        user.setUsername("Alex");
        user.setPassword("1");
        userService.addUser(user);
        Mockito.verify(userRepo, Mockito.times(1)).save(user);
        Mockito.verify(userRepo, Mockito.times(1)).findByUsername(user.getUsername());

        Assertions.assertTrue(CoreMatchers.is(user.getRoles()).matches(Collections.singleton(Role.ROLE_USER)));
    }

    @Test
    public void addUserTestFail() {
        UserEntity user = new UserEntity();
        user.setUsername("Alex");
        user.setPassword("1");
        try {
            userService.addUser(user);
        } catch (AlreadyExistsException e) {
            Assertions.assertNotEquals("", e.getMessage());
        }
        Mockito.verify(userRepo, Mockito.times(1)).save(user);
        Mockito.verify(userRepo, Mockito.times(1)).findByUsername(user.getUsername());

        Assertions.assertTrue(CoreMatchers.is(user.getRoles()).matches(Collections.singleton(Role.ROLE_USER)));
    }
}