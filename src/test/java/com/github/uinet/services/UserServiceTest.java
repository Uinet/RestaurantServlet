package com.github.uinet.services;

import com.github.uinet.dao.imp.UserDAOImp;
import com.github.uinet.exception.UserException;
import com.github.uinet.model.User;
import com.github.uinet.model.UserRole;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    private UserDAOImp userDAOImp;

    private UserService userService;

    private User user;

    @Before
    public void init(){
        user = User.builder()
                .id(1l)
                .username("TestUsername")
                .name("TestName")
                .role(UserRole.CLIENT)
                .money(BigDecimal.valueOf(100))
                .build();
        userService = new UserService(userDAOImp);
    }

    @Test
    public void loadUserByUsername() {
        String username = "TestUsername";
        when(userDAOImp.getUserByUsername(username)).thenReturn(user);

        assertEquals(user, userService.loadUserByUsername(username));
    }


    @Test(expected = UserException.class)
    public void registerNewUser() throws UserException {
        when(userDAOImp.create(user)).thenThrow();
        userService.registerNewUser(user);
    }

    @Test
    public void loadUserById() {
        long userId = 1l;
        when(userDAOImp.findById(userId)).thenReturn(user);
        assertEquals(user, userService.loadUserById(userId));
    }
}